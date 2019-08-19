package com.vortexbird.vorteam.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.vorteam.domain.VtCliente;
import com.vortexbird.vorteam.domain.VtLineaNegocio;
import com.vortexbird.vorteam.domain.VtParametro;
import com.vortexbird.vorteam.domain.VtPersonas;
import com.vortexbird.vorteam.domain.VtProyecto;
import com.vortexbird.vorteam.domain.VtTipoActividad;
import com.vortexbird.vorteam.utility.Constantes;
import com.vortexbird.vorteam.utility.Constantes.REPORT_OUTPUT_TYPE;
import com.vortexbird.vorteam.utility.Fechas;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service
public class VtReportesServiceImpl implements VtReportesService {
    private static final Logger log = LoggerFactory.getLogger(VtReportesServiceImpl.class);

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtReportesService#generarReporteTiemposEntreFechas(java.util.Date, java.util.Date, com.vortexbird.vorteam.utility.Constantes.REPORT_OUTPUT_TYPE)
	 *
	 */
    
    @Autowired
    VtParametroService vtParametroService;
    @Autowired
    VtPersonasService vtPersonasService;
    @Autowired
    VtProyectoService vtProyectoService;
    @Autowired
    VtTipoActividadService vtTipoActividadService;
    @Autowired
    VtLineaNegocioService vtLineaNegocioService;
    @Autowired
    VtClienteService vtClienteService;
    
	@Override
	@Transactional(readOnly = true)
	public ByteArrayInputStream generarReporteTiemposEntreFechas(Date fechaInicial, Date fechaFinal,
			REPORT_OUTPUT_TYPE reportOutputTye) throws Exception {
		Connection conn = null;
		
		try {

			if (fechaInicial == null) {
				throw new Exception("Seleccionar la fecha inicial");
			}
			if (fechaFinal == null) {
				throw new Exception("Seleccionar la fecha final");
			}
			if (fechaInicial.after(fechaFinal)) {
				throw new Exception("La fecha inicial no puede estar después de la fecha final");
			}

			// SE CONSULTA LA RUTA BASE DE REPORTES
			VtParametro parametro = vtParametroService.obtenerParametroPorCodigo(Constantes.RUTA_BASE_REPORTES,
					Constantes.ESTADO_ACTIVO);

			if (parametro == null) {
				throw new Exception("No existe el parámetro " + Constantes.RUTA_BASE_REPORTES);
			}

			String rutaBaseReportes = parametro.getValor();

			// Se valida si la ruta existe
			File fRutaBaseReportes = new File(rutaBaseReportes);
			if (!fRutaBaseReportes.exists() || !fRutaBaseReportes.isDirectory() || !fRutaBaseReportes.canRead()) {
				throw new Exception(
						"No existe la ruta base de reportes, no es un directorio o no se tiene acceso de lectura al directorio: "
								+ fRutaBaseReportes.getPath());
			}

			// Se valida la ruta del reporte
			File fReporte = new File(fRutaBaseReportes,
					"/reporteTiemposEntreFechas.jasper");

			if (!fReporte.exists() || !fReporte.isFile() || !fReporte.canRead()) {
				throw new Exception(
						"No existe la ruta del reporte, no es un archivo o no se tiene acceso de lectura al mismo: "
								+ fReporte.getPath());
			}

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			// Se abre el reporte
			InputStream inputStream = new FileInputStream(fReporte);

			// Se obtiene la conexion a la BD
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/vorteam");
			
			conn = ds.getConnection();

			// Se llena el reporte
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("P_FECHA_INICIAL", Fechas.dateToStr(fechaInicial, Constantes.PATTERN_ANHO_MES_DIA_JUNTOS));
			params.put("P_FECHA_FINAL", Fechas.dateToStr(fechaFinal, Constantes.PATTERN_ANHO_MES_DIA_JUNTOS));
			params.put("P_PATTERN_FECHAS", Constantes.PATTERN_ANHO_MES_DIA_JUNTOS);

			JasperPrint print = JasperFillManager.fillReport(inputStream, params, conn);

			// Se exporta el reporte
			if (reportOutputTye.equals(Constantes.REPORT_OUTPUT_TYPE.PDF)) {
				JRPdfExporter jrPdfExporter = new JRPdfExporter();

				jrPdfExporter.setExporterInput(new SimpleExporterInput(print));
				jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
				SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
				jrPdfExporter.setConfiguration(configuration);
				jrPdfExporter.exportReport();

			} else if (reportOutputTye.equals(Constantes.REPORT_OUTPUT_TYPE.XLS)) {

				JRXlsExporter exporter = new JRXlsExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setOnePagePerSheet(false);
				configuration.setDetectCellType(true);
				configuration.setCollapseRowSpan(false);
				exporter.setConfiguration(configuration);
				exporter.exportReport();

			} else if (reportOutputTye.equals(Constantes.REPORT_OUTPUT_TYPE.XLSX)) {

				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
				configuration.setOnePagePerSheet(false);
				configuration.setDetectCellType(true);
				configuration.setCollapseRowSpan(false);
				exporter.setConfiguration(configuration);
				exporter.exportReport();
			}

			ByteArrayInputStream is = new ByteArrayInputStream(bos.toByteArray());
			return is;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtReportesService#generarReporteCostosPorProyectos(java.util.Date, java.util.Date, java.lang.Long, java.lang.Long, java.lang.Long, com.vortexbird.vorteam.utility.Constantes.REPORT_OUTPUT_TYPE)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public ByteArrayInputStream generarReporteCostosPorProyectos(Date fechaInicial, Date fechaFinal, Long persId,
			Long proyId, Long tiacId, REPORT_OUTPUT_TYPE reportOutputTye) throws Exception {
		Connection conn = null;
		
		try {

			
			
			// SE CONSULTA LA RUTA BASE DE REPORTES
			VtParametro parametro = vtParametroService.obtenerParametroPorCodigo(Constantes.RUTA_BASE_REPORTES,
					Constantes.ESTADO_ACTIVO);

			if (parametro == null) {
				throw new Exception("No existe el parámetro " + Constantes.RUTA_BASE_REPORTES);
			}

			String rutaBaseReportes = parametro.getValor();

			// Se valida si la ruta existe
			File fRutaBaseReportes = new File(rutaBaseReportes);
			if (!fRutaBaseReportes.exists() || !fRutaBaseReportes.isDirectory() || !fRutaBaseReportes.canRead()) {
				throw new Exception(
						"No existe la ruta base de reportes, no es un directorio o no se tiene acceso de lectura al directorio: "
								+ fRutaBaseReportes.getPath());
			}

			// Se valida la ruta del reporte
			File fReporte = new File(fRutaBaseReportes,
					"/costosPorProyectos.jasper");

			if (!fReporte.exists() || !fReporte.isFile() || !fReporte.canRead()) {
				throw new Exception(
						"No existe la ruta del reporte, no es un archivo o no se tiene acceso de lectura al mismo: "
								+ fReporte.getPath());
			}
			if(fechaInicial != null) {
				fechaInicial = Fechas.moverHastaUnaHoraMinutoSegundo(fechaInicial, 0, 0, 0);
			}
			if(fechaFinal != null) {
				fechaFinal = Fechas.moverHastaUnaHoraMinutoSegundo(fechaFinal, 23, 59, 59);
			}
			String nombreCompletoPersona = null, nombreProyecto = null, nombreTipoActividad = null;
			if(persId != null) {
				VtPersonas persona = vtPersonasService.getVtPersonas(persId);
				nombreCompletoPersona = (persona.getNombre()!=null?persona.getNombre():"")+" "+
						(persona.getApellidos()!=null?persona.getApellidos():"");
				nombreCompletoPersona = nombreCompletoPersona.trim().toUpperCase();
			}
			if(proyId != null) {
				VtProyecto proyecto = vtProyectoService.getVtProyecto(proyId);
				VtCliente cliente = vtClienteService.getVtCliente(proyecto.getVtCliente().getClieId());
				VtLineaNegocio lineaNegocio = vtLineaNegocioService.getVtLineaNegocio(proyecto.getVtLineaNegocio().getLineId());
				nombreProyecto = 
						proyecto.getNombreProyecto().trim().toUpperCase() + " - "
						+ cliente.getNombreRazonSocial().trim().toUpperCase() + " - "
						+ lineaNegocio.getDescripcion().trim().toUpperCase();
			}
			if(tiacId != null) {
				VtTipoActividad tipoActividad = vtTipoActividadService.getVtTipoActividad(tiacId);
				nombreTipoActividad = tipoActividad.getDescripcion().trim().toUpperCase();
			}

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			// Se abre el reporte
			InputStream inputStream = new FileInputStream(fReporte);

			// Se obtiene la conexion a la BD
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/vorteam");
			
			conn = ds.getConnection();

			// Se llena el reporte
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("P_SUBREPORT_DIR", rutaBaseReportes);
			params.put("P_FECHA_INICIO", fechaInicial);
			params.put("P_FECHA_FIN", fechaFinal);
			params.put("P_PERS_ID", persId);
			params.put("P_NOMBRE_COMPLETO_PERSONA", nombreCompletoPersona);
			params.put("P_PROY_ID", proyId);
			params.put("P_NOMBRE_PROYECTO", nombreProyecto);
			params.put("P_TIAC_ID", tiacId);
			params.put("P_NOMBRE_TIPO_ACTIVIDAD", nombreTipoActividad);
			
			
			JasperPrint print = JasperFillManager.fillReport(inputStream, params, conn);

			// Se exporta el reporte
			if (reportOutputTye.equals(Constantes.REPORT_OUTPUT_TYPE.PDF)) {
				JRPdfExporter jrPdfExporter = new JRPdfExporter();

				jrPdfExporter.setExporterInput(new SimpleExporterInput(print));
				jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
				SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
				jrPdfExporter.setConfiguration(configuration);
				jrPdfExporter.exportReport();

			} else if (reportOutputTye.equals(Constantes.REPORT_OUTPUT_TYPE.XLS)) {

				JRXlsExporter exporter = new JRXlsExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setOnePagePerSheet(false);
				configuration.setDetectCellType(true);
				configuration.setCollapseRowSpan(false);
				exporter.setConfiguration(configuration);
				exporter.exportReport();

			} else if (reportOutputTye.equals(Constantes.REPORT_OUTPUT_TYPE.XLSX)) {

				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
				configuration.setOnePagePerSheet(false);
				configuration.setDetectCellType(true);
				configuration.setCollapseRowSpan(false);
				exporter.setConfiguration(configuration);
				exporter.exportReport();
			}

			ByteArrayInputStream is = new ByteArrayInputStream(bos.toByteArray());
			return is;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 21, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtReportesService#generarReporteOcupacionPlanta(java.util.Date, java.util.Date, java.lang.Long, java.lang.Long, java.lang.Long, com.vortexbird.vorteam.utility.Constantes.REPORT_OUTPUT_TYPE)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public ByteArrayInputStream generarReporteOcupacionPlanta(Date fechaInicial, Date fechaFinal, Long persId,
			Long proyId, Long tiacId, REPORT_OUTPUT_TYPE reportOutputTye) throws Exception {
		Connection conn = null;
		
		try {

			
			
			// SE CONSULTA LA RUTA BASE DE REPORTES
			VtParametro parametro = vtParametroService.obtenerParametroPorCodigo(Constantes.RUTA_BASE_REPORTES,
					Constantes.ESTADO_ACTIVO);

			if (parametro == null) {
				throw new Exception("No existe el parámetro " + Constantes.RUTA_BASE_REPORTES);
			}

			String rutaBaseReportes = parametro.getValor();

			// Se valida si la ruta existe
			File fRutaBaseReportes = new File(rutaBaseReportes);
			if (!fRutaBaseReportes.exists() || !fRutaBaseReportes.isDirectory() || !fRutaBaseReportes.canRead()) {
				throw new Exception(
						"No existe la ruta base de reportes, no es un directorio o no se tiene acceso de lectura al directorio: "
								+ fRutaBaseReportes.getPath());
			}

			// Se valida la ruta del reporte
			File fReporte = new File(fRutaBaseReportes,
					"/ocupacionPlanta.jasper");

			if (!fReporte.exists() || !fReporte.isFile() || !fReporte.canRead()) {
				throw new Exception(
						"No existe la ruta del reporte, no es un archivo o no se tiene acceso de lectura al mismo: "
								+ fReporte.getPath());
			}
			if(fechaInicial != null) {
				fechaInicial = Fechas.moverHastaUnaHoraMinutoSegundo(fechaInicial, 0, 0, 0);
			}
			if(fechaFinal != null) {
				fechaFinal = Fechas.moverHastaUnaHoraMinutoSegundo(fechaFinal, 23, 59, 59);
			}
			String nombreCompletoPersona = null, nombreProyecto = null, nombreTipoActividad = null;
			if(persId != null) {
				VtPersonas persona = vtPersonasService.getVtPersonas(persId);
				nombreCompletoPersona = (persona.getNombre()!=null?persona.getNombre():"")+" "+
						(persona.getApellidos()!=null?persona.getApellidos():"");
				nombreCompletoPersona = nombreCompletoPersona.trim().toUpperCase();
			}
			if(proyId != null) {
				VtProyecto proyecto = vtProyectoService.getVtProyecto(proyId);
				VtCliente cliente = vtClienteService.getVtCliente(proyecto.getVtCliente().getClieId());
				VtLineaNegocio lineaNegocio = vtLineaNegocioService.getVtLineaNegocio(proyecto.getVtLineaNegocio().getLineId());
				nombreProyecto = 
						proyecto.getNombreProyecto().trim().toUpperCase() + " - "
						+ cliente.getNombreRazonSocial().trim().toUpperCase() + " - "
						+ lineaNegocio.getDescripcion().trim().toUpperCase();
			}
			if(tiacId != null) {
				VtTipoActividad tipoActividad = vtTipoActividadService.getVtTipoActividad(tiacId);
				nombreTipoActividad = tipoActividad.getDescripcion().trim().toUpperCase();
			}

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			// Se abre el reporte
			InputStream inputStream = new FileInputStream(fReporte);

			// Se obtiene la conexion a la BD
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/vorteam");
			
			conn = ds.getConnection();

			// Se llena el reporte
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("P_SUBREPORT_DIR", rutaBaseReportes);
			params.put("P_FECHA_INICIO", fechaInicial);
			params.put("P_FECHA_FIN", fechaFinal);
			params.put("P_PERS_ID", persId);
			params.put("P_NOMBRE_COMPLETO_PERSONA", nombreCompletoPersona);
			params.put("P_PROY_ID", proyId);
			params.put("P_NOMBRE_PROYECTO", nombreProyecto);
			params.put("P_TIAC_ID", tiacId);
			params.put("P_NOMBRE_TIPO_ACTIVIDAD", nombreTipoActividad);
			
			
			JasperPrint print = JasperFillManager.fillReport(inputStream, params, conn);

			// Se exporta el reporte
			if (reportOutputTye.equals(Constantes.REPORT_OUTPUT_TYPE.PDF)) {
				JRPdfExporter jrPdfExporter = new JRPdfExporter();

				jrPdfExporter.setExporterInput(new SimpleExporterInput(print));
				jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
				SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
				jrPdfExporter.setConfiguration(configuration);
				jrPdfExporter.exportReport();

			} else if (reportOutputTye.equals(Constantes.REPORT_OUTPUT_TYPE.XLS)) {

				JRXlsExporter exporter = new JRXlsExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setOnePagePerSheet(false);
				configuration.setDetectCellType(true);
				configuration.setCollapseRowSpan(false);
				exporter.setConfiguration(configuration);
				exporter.exportReport();

			} else if (reportOutputTye.equals(Constantes.REPORT_OUTPUT_TYPE.XLSX)) {

				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
				configuration.setOnePagePerSheet(false);
				configuration.setDetectCellType(true);
				configuration.setCollapseRowSpan(false);
				exporter.setConfiguration(configuration);
				exporter.exportReport();
			}

			ByteArrayInputStream is = new ByteArrayInputStream(bos.toByteArray());
			return is;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 21, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtReportesService#generarReporteCostosPorPersona(java.util.Date, java.util.Date, java.lang.Long, java.lang.Long, java.lang.Long, com.vortexbird.vorteam.utility.Constantes.REPORT_OUTPUT_TYPE)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public ByteArrayInputStream generarReporteCostosPorPersona(Date fechaInicial, Date fechaFinal, Long persId,
			Long proyId, Long tiacId, REPORT_OUTPUT_TYPE reportOutputTye) throws Exception {
		Connection conn = null;
		
		try {

			
			
			// SE CONSULTA LA RUTA BASE DE REPORTES
			VtParametro parametro = vtParametroService.obtenerParametroPorCodigo(Constantes.RUTA_BASE_REPORTES,
					Constantes.ESTADO_ACTIVO);

			if (parametro == null) {
				throw new Exception("No existe el parámetro " + Constantes.RUTA_BASE_REPORTES);
			}

			String rutaBaseReportes = parametro.getValor();

			// Se valida si la ruta existe
			File fRutaBaseReportes = new File(rutaBaseReportes);
			if (!fRutaBaseReportes.exists() || !fRutaBaseReportes.isDirectory() || !fRutaBaseReportes.canRead()) {
				throw new Exception(
						"No existe la ruta base de reportes, no es un directorio o no se tiene acceso de lectura al directorio: "
								+ fRutaBaseReportes.getPath());
			}

			// Se valida la ruta del reporte
			File fReporte = new File(fRutaBaseReportes,
					"/costosPorPersona.jasper");

			if (!fReporte.exists() || !fReporte.isFile() || !fReporte.canRead()) {
				throw new Exception(
						"No existe la ruta del reporte, no es un archivo o no se tiene acceso de lectura al mismo: "
								+ fReporte.getPath());
			}
			if(fechaInicial != null) {
				fechaInicial = Fechas.moverHastaUnaHoraMinutoSegundo(fechaInicial, 0, 0, 0);
			}
			if(fechaFinal != null) {
				fechaFinal = Fechas.moverHastaUnaHoraMinutoSegundo(fechaFinal, 23, 59, 59);
			}
			String nombreCompletoPersona = null, nombreProyecto = null, nombreTipoActividad = null;
			if(persId != null) {
				VtPersonas persona = vtPersonasService.getVtPersonas(persId);
				nombreCompletoPersona = (persona.getNombre()!=null?persona.getNombre():"")+" "+
						(persona.getApellidos()!=null?persona.getApellidos():"");
				nombreCompletoPersona = nombreCompletoPersona.trim().toUpperCase();
			}
			if(proyId != null) {
				VtProyecto proyecto = vtProyectoService.getVtProyecto(proyId);
				VtCliente cliente = vtClienteService.getVtCliente(proyecto.getVtCliente().getClieId());
				VtLineaNegocio lineaNegocio = vtLineaNegocioService.getVtLineaNegocio(proyecto.getVtLineaNegocio().getLineId());
				nombreProyecto = 
						proyecto.getNombreProyecto().trim().toUpperCase() + " - "
						+ cliente.getNombreRazonSocial().trim().toUpperCase() + " - "
						+ lineaNegocio.getDescripcion().trim().toUpperCase();
			}
			if(tiacId != null) {
				VtTipoActividad tipoActividad = vtTipoActividadService.getVtTipoActividad(tiacId);
				nombreTipoActividad = tipoActividad.getDescripcion().trim().toUpperCase();
			}

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			// Se abre el reporte
			InputStream inputStream = new FileInputStream(fReporte);

			// Se obtiene la conexion a la BD
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/vorteam");
			
			conn = ds.getConnection();

			// Se llena el reporte
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("P_SUBREPORT_DIR", rutaBaseReportes);
			params.put("P_FECHA_INICIO", fechaInicial);
			params.put("P_FECHA_FIN", fechaFinal);
			params.put("P_PERS_ID", persId);
			params.put("P_NOMBRE_COMPLETO_PERSONA", nombreCompletoPersona);
			params.put("P_PROY_ID", proyId);
			params.put("P_NOMBRE_PROYECTO", nombreProyecto);
			params.put("P_TIAC_ID", tiacId);
			params.put("P_NOMBRE_TIPO_ACTIVIDAD", nombreTipoActividad);
			
			
			JasperPrint print = JasperFillManager.fillReport(inputStream, params, conn);

			// Se exporta el reporte
			if (reportOutputTye.equals(Constantes.REPORT_OUTPUT_TYPE.PDF)) {
				JRPdfExporter jrPdfExporter = new JRPdfExporter();

				jrPdfExporter.setExporterInput(new SimpleExporterInput(print));
				jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
				SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
				jrPdfExporter.setConfiguration(configuration);
				jrPdfExporter.exportReport();

			} else if (reportOutputTye.equals(Constantes.REPORT_OUTPUT_TYPE.XLS)) {

				JRXlsExporter exporter = new JRXlsExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setOnePagePerSheet(false);
				configuration.setDetectCellType(true);
				configuration.setCollapseRowSpan(false);
				exporter.setConfiguration(configuration);
				exporter.exportReport();

			} else if (reportOutputTye.equals(Constantes.REPORT_OUTPUT_TYPE.XLSX)) {

				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
				configuration.setOnePagePerSheet(false);
				configuration.setDetectCellType(true);
				configuration.setCollapseRowSpan(false);
				exporter.setConfiguration(configuration);
				exporter.exportReport();
			}

			ByteArrayInputStream is = new ByteArrayInputStream(bos.toByteArray());
			return is;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 21, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtReportesService#generarReporteCostoAcumuladoProyecto(java.lang.Long, com.vortexbird.vorteam.utility.Constantes.REPORT_OUTPUT_TYPE)
	 *
	 */
	@Override
	public ByteArrayInputStream generarReporteCostoAcumuladoProyecto(Long proyId, REPORT_OUTPUT_TYPE reportOutputTye)
			throws Exception {
		Connection conn = null;
		
		try {
			
			// SE CONSULTA LA RUTA BASE DE REPORTES
			VtParametro parametro = vtParametroService.obtenerParametroPorCodigo(Constantes.RUTA_BASE_REPORTES,
					Constantes.ESTADO_ACTIVO);

			if (parametro == null) {
				throw new Exception("No existe el parámetro " + Constantes.RUTA_BASE_REPORTES);
			}

			String rutaBaseReportes = parametro.getValor();

			// Se valida si la ruta existe
			File fRutaBaseReportes = new File(rutaBaseReportes);
			if (!fRutaBaseReportes.exists() || !fRutaBaseReportes.isDirectory() || !fRutaBaseReportes.canRead()) {
				throw new Exception(
						"No existe la ruta base de reportes, no es un directorio o no se tiene acceso de lectura al directorio: "
								+ fRutaBaseReportes.getPath());
			}

			// Se valida la ruta del reporte
			File fReporte = new File(fRutaBaseReportes,
					"/costoAcumuladoProyecto.jasper");

			if (!fReporte.exists() || !fReporte.isFile() || !fReporte.canRead()) {
				throw new Exception(
						"No existe la ruta del reporte, no es un archivo o no se tiene acceso de lectura al mismo: "
								+ fReporte.getPath());
			}
			String nombreProyecto = null;
			if(proyId != null) {
				VtProyecto proyecto = vtProyectoService.getVtProyecto(proyId);
				VtCliente cliente = vtClienteService.getVtCliente(proyecto.getVtCliente().getClieId());
				VtLineaNegocio lineaNegocio = vtLineaNegocioService.getVtLineaNegocio(proyecto.getVtLineaNegocio().getLineId());
				nombreProyecto = 
						proyecto.getNombreProyecto().trim().toUpperCase() + " - "
						+ cliente.getNombreRazonSocial().trim().toUpperCase() + " - "
						+ lineaNegocio.getDescripcion().trim().toUpperCase();
			}

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			// Se abre el reporte
			InputStream inputStream = new FileInputStream(fReporte);

			// Se obtiene la conexion a la BD
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/vorteam");
			
			conn = ds.getConnection();

			// Se llena el reporte
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("P_SUBREPORT_DIR", rutaBaseReportes);
			params.put("P_PROY_ID", proyId);
			params.put("P_NOMBRE_PROYECTO", nombreProyecto);
			
			
			JasperPrint print = JasperFillManager.fillReport(inputStream, params, conn);

			// Se exporta el reporte
			if (reportOutputTye.equals(Constantes.REPORT_OUTPUT_TYPE.PDF)) {
				JRPdfExporter jrPdfExporter = new JRPdfExporter();

				jrPdfExporter.setExporterInput(new SimpleExporterInput(print));
				jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
				SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
				jrPdfExporter.setConfiguration(configuration);
				jrPdfExporter.exportReport();

			} else if (reportOutputTye.equals(Constantes.REPORT_OUTPUT_TYPE.XLS)) {

				JRXlsExporter exporter = new JRXlsExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setOnePagePerSheet(false);
				configuration.setDetectCellType(true);
				configuration.setCollapseRowSpan(false);
				exporter.setConfiguration(configuration);
				exporter.exportReport();

			} else if (reportOutputTye.equals(Constantes.REPORT_OUTPUT_TYPE.XLSX)) {

				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
				configuration.setOnePagePerSheet(false);
				configuration.setDetectCellType(true);
				configuration.setCollapseRowSpan(false);
				exporter.setConfiguration(configuration);
				exporter.exportReport();
			}

			ByteArrayInputStream is = new ByteArrayInputStream(bos.toByteArray());
			return is;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

}
