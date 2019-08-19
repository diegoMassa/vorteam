package com.vortexbird.vorteam.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.vorteam.domain.VtParametro;
import com.vortexbird.vorteam.domain.VtPersonas;
import com.vortexbird.vorteam.utility.Constantes;
import com.vortexbird.vorteam.utility.Fechas;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("VtTareasProgramadasServiceImpl")
public class VtTareasProgramadasServiceImpl implements VtTareasProgramadasService {
	private static final Logger log = LoggerFactory.getLogger(VtTareasProgramadasServiceImpl.class);
	public final static String TIME_ZONE = "America/Bogota", NOTIFICACION_TIEMPO_REPORTADO = "";

	@Autowired
	private VtParametroService vtParametroService;
	@Autowired
	private VtPersonasService vtPersonasService;
	@Autowired
	private VtReporteTiempoService vtReporteTiempoService;
	@Autowired
	private VtFestivosService vtFestivosService;
	
	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtTareasProgramadasService#executreSpringScheduleTask()
	 *
	 */
	@Override
	@SuppressWarnings("deprecation")
	@Transactional(readOnly = true)
	@Scheduled(cron = "${cron.notificacionTiempo}", zone = TIME_ZONE)
	public void executeSpringScheduleTask() throws Exception {
		try {
			Date fechaActual = new Date();
			log.info("### Tarea Programada para enviar notificación de registro de tiempos. Hora Actual es :: "
					+ fechaActual);

			boolean esFestivo = vtFestivosService.esFestivo(fechaActual);
			if (!esFestivo) {
				// Consultar los parámetros de porcentajes y tiempo
				VtParametro parametroHorasDiarias = vtParametroService
						.obtenerParametroPorCodigo(Constantes.HORAS_DIARIAS, Constantes.ESTADO_ACTIVO);
				if (parametroHorasDiarias == null || parametroHorasDiarias.getValor() == null
						|| parametroHorasDiarias.getValor().trim().equals("")) {
					throw new Exception("No se ha encontrado el parámetro " + Constantes.HORAS_DIARIAS);
				}

				VtParametro parametroPorcentajeHoras = vtParametroService.obtenerParametroPorCodigo(
						Constantes.PORCENTAJE_HORAS_MINIMAS_REPORTAR, Constantes.ESTADO_ACTIVO);
				if (parametroPorcentajeHoras == null || parametroPorcentajeHoras.getValor() == null
						|| parametroPorcentajeHoras.getValor().trim().equals("")) {
					throw new Exception("No se ha encontrado el parámetro " + Constantes.HORAS_DIARIAS);
				}

				Double horasLaboralesDiarias = Double.parseDouble(parametroHorasDiarias.getValor().trim());
				Double porcentajeHoras = Double.parseDouble(parametroPorcentajeHoras.getValor().trim());

				Double calculoHorasPorcentaje = (porcentajeHoras * horasLaboralesDiarias) / 100;

				BigDecimal horasReportadasBD;
				Double horasReportadasD;

				// Consultar los usuarios activos del sistema
				Object[] variablesBusquedaUsuariosActivos = { "activo", true, Constantes.ESTADO_ACTIVO, "=" };
				List<VtPersonas> usuariosActivos = vtPersonasService.findByCriteria(variablesBusquedaUsuariosActivos,
						null, null);
				for (VtPersonas usuario : usuariosActivos) {
					horasReportadasBD = vtReporteTiempoService.horasReportadasHoy(fechaActual, usuario.getEmail());
					horasReportadasD = horasReportadasBD.doubleValue();
					if (horasReportadasD < calculoHorasPorcentaje) {
						// Envia Correo de Notificacion al usuario
						log.info("Se va a enviar notificación a: " + usuario.getEmail() + " ha reportado "
								+ horasReportadasD + " de " + calculoHorasPorcentaje + " mínimas ");
						String mensaje = "Hola " + usuario.getNombre().trim().toUpperCase()
								+ " hoy has reportado menos del " + porcentajeHoras
								+ "% del tiempo laboral. Has reportado en total " + horasReportadasD + " hora(s).";
						vtReporteTiempoService.enviarCorreoNotificacionNoHaReportadoHoy(usuario.getEmail(), mensaje);
					}
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	/**
	 * 
	 * @author Daniel Pareja Londoño
	 * @version ene. 25, 2019
	 *
	 * @see com.vortexbird.vorteam.service.VtTareasProgramadasService#tareaCorreoOcupacionPlanta()
	 *
	 */
	@Override
	@SuppressWarnings("deprecation")
	@Transactional(readOnly = true)
	@Scheduled(cron = "${cron.notificacionOcupacionPlanta}", zone = TIME_ZONE)
	public void tareaCorreoOcupacionPlanta() throws Exception {
		Connection conn = null;
		try {
			Date fechaActual = new Date();
			log.info("### Tarea Programada para enviar notificación de tareaCorreoOcupacionPlanta. Hora Actual es :: "
					+ fechaActual);
			
			boolean esFestivo = vtFestivosService.esFestivo(fechaActual);
			if (!esFestivo) {
				//Consultar el reporte de ocupación de planta en el día hábil anterior
				Date fechaReporte = fechaReporte(Fechas.sumar(fechaActual, -1, 0, 0, 0));
				
				
				//Exportar reporte
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
				Date fechaInicial = fechaReporte, fechaFinal = fechaReporte;
				if(fechaReporte != null) {
					fechaInicial = Fechas.moverHastaUnaHoraMinutoSegundo(fechaReporte, 0, 0, 0);
					fechaFinal = Fechas.moverHastaUnaHoraMinutoSegundo(fechaReporte, 23, 59, 59);
				}
				

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
				params.put("P_PERS_ID", null);
				params.put("P_NOMBRE_COMPLETO_PERSONA", null);
				params.put("P_PROY_ID", null);
				params.put("P_NOMBRE_PROYECTO", null);
				params.put("P_TIAC_ID", null);
				params.put("P_NOMBRE_TIPO_ACTIVIDAD", null);
				
				
				JasperPrint print = JasperFillManager.fillReport(inputStream, params, conn);
				
				String fechaFormato = Fechas.dateToStr(fechaReporte, Constantes.PATTERN_ANHO_MES_DIA_JUNTOS);
				
				JRExporter exporter = new JRPdfExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(fRutaBaseReportes+"/export/ocupacionPlanta_"+fechaFormato+".pdf"));
				
				exporter.exportReport();
				
				File adjunto = new File(fRutaBaseReportes+"/export/ocupacionPlanta_"+fechaFormato+".pdf");
				
				VtParametro parametrosDestinatariosOcupacionPlanta = vtParametroService.obtenerParametroPorCodigo(Constantes.P_DESTINATARIOS_INFORME_OCUPACION_PLANTA, Constantes.ESTADO_ACTIVO);
				if(parametrosDestinatariosOcupacionPlanta == null) {
					throw new Exception("No se ha encontrado el parámetro "+Constantes.P_DESTINATARIOS_INFORME_OCUPACION_PLANTA);
				}
				VtParametro caracterSeparacion = vtParametroService.obtenerParametroPorCodigo(Constantes.P_CARACTER_SEPARACION_CORREOS, Constantes.ESTADO_ACTIVO);
				if(caracterSeparacion == null) {
					throw new Exception("No se ha encontrado el parámetro "+Constantes.P_CARACTER_SEPARACION_CORREOS);
				}
				String[] destinatarios = parametrosDestinatariosOcupacionPlanta.getValor().trim().split(caracterSeparacion.getValor());
				if(destinatarios.length==0) {
					throw new Exception("No hay destinatarios para el correo");
				}
				String mensaje = "Hola, adjunto encontrarás el informe de ocupación de la planta referente al día "+fechaFormato+".";
				
				vtReporteTiempoService.enviarCorreoNotificacionOcupacionPlantaDiaAnterior(destinatarios, mensaje, adjunto);
				
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public Date fechaReporte(Date fecha) throws Exception {
		// Validar que la fecha no sea un festivo
		if (vtFestivosService.esFestivo(fecha)) {
			Date fechaNuevaReporte = Fechas.sumar(fecha, -1, 0, 0, 0);

			Date fechaLunes = Fechas.moverHastaDiaDeLaSemana(fecha, 2, true);

			String fechaStr = Fechas.dateToStr(fecha, Constantes.PATTERN_ANHO_MES_DIA_JUNTOS),
					fechaLunesStr = Fechas.dateToStr(fechaLunes, Constantes.PATTERN_ANHO_MES_DIA_JUNTOS);
			if(fechaLunesStr.trim().equals(fechaStr.trim())) {
				fechaNuevaReporte = Fechas.sumar(fecha, -3, 0, 0, 0);
			}
			return fechaReporte(fechaNuevaReporte);
		}
		return fecha;
	}
}
