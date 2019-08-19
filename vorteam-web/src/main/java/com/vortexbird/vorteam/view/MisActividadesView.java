package com.vortexbird.vorteam.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.sentinel.dto.UsuarioDTO;
import com.vortexbird.vorteam.domain.VtActividad;
import com.vortexbird.vorteam.domain.VtAsignacion;
import com.vortexbird.vorteam.domain.VtEstado;
import com.vortexbird.vorteam.domain.VtPersonas;
import com.vortexbird.vorteam.domain.VtReporteTiempo;
import com.vortexbird.vorteam.dto.VtProyectoDTO;
import com.vortexbird.vorteam.dto.VtTipoActividadDTO;
import com.vortexbird.vorteam.utility.Constantes;
import com.vortexbird.vorteam.utility.FacesUtils;
import com.vortexbird.vorteam.view.lazydatamodel.MisActividadesDataModeler;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class MisActividadesView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MisActividadesView.class);

	@ManagedProperty(value = "#{businessDelegator}")
	private BusinessDelegator businessDelegatorView;
	//private List<VtActividadDTO> misActividades;
	
	private MisActividadesDataModeler misActividadesDataModeler;

	// Reporte de tiempos
	private boolean esVisibleDlgReportarTiempos;
	private Calendar calFecha;
	private InputNumber txtHorasEjecutadas;
	private InputTextarea txtObservacion;
	private SelectOneMenu somEstado;
	private List<SelectItem> siEstados;
	private VtAsignacion asignacionReporteTiempos;
	private Date fechaActual;

	private UsuarioDTO usuario_session;

	// Componentes Reporte de Tiempo de cada Asignacion
	private List<VtReporteTiempo> lstReporteTiempo;
	private boolean esVisibleDlgReportesTiempos;

	// Componentes Ver descripcion actividad
	private String strDescripcionActividad;
	private boolean esVisibleDlgVerdescripcionActividad;

	// Componentes Modificar Reporte de Tiempo
	private VtReporteTiempo reporteTiempoModificar;
	private InputNumber txtPorcentajeAvance;
	
	//Componentes Filtros de Mis Actividades: Proyecto, Estado, Tipo Actividad, Sprint, Caso Soporte, Control Cambios
	private SelectOneMenu somProyectoFiltro, somEstadoFiltro, somTipoActividadFiltro;
	private List<SelectItem> siProyectosFiltro, siTiposActividadFiltro;
	private InputText txtSprintFiltro, txtCasoSoporteFiltro, txtControlCambiosFiltro;
	
	

	
	public MisActividadesView() {
		super();
		this.usuario_session = (UsuarioDTO) FacesUtils.getfromSession("usuarioDTOLogin");
		this.fechaActual = new Date();
	}
	
	@PostConstruct
	private void init() {
		try {
			misActividadesDataModeler = new MisActividadesDataModeler(usuario_session.getUsu_login(),
					Constantes.ESTADO_ACTIVO, null, null, null, null, null, null, businessDelegatorView);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public String buscarMisActividades() {
		try {
			String sprintFiltro, casoSoporteFiltro, controlCambiosFiltro;
			sprintFiltro = (FacesUtils.checkString(txtSprintFiltro) == null)? "-1":FacesUtils.checkString(txtSprintFiltro);
			casoSoporteFiltro = (FacesUtils.checkString(txtCasoSoporteFiltro) == null)? "-1":FacesUtils.checkString(txtCasoSoporteFiltro);
			controlCambiosFiltro = (FacesUtils.checkString(txtControlCambiosFiltro) == null)? "-1":FacesUtils.checkString(txtControlCambiosFiltro);
			
			Long proyId, estaId, tiacId;
			proyId = (FacesUtils.checkLong(somProyectoFiltro).equals(Constantes.SOM_OPCION_SELECCIONAR))?-1L:FacesUtils.checkLong(somProyectoFiltro);
			estaId = (FacesUtils.checkLong(somEstadoFiltro).equals(Constantes.SOM_OPCION_SELECCIONAR))?-1L:FacesUtils.checkLong(somEstadoFiltro);
			tiacId = (FacesUtils.checkLong(somTipoActividadFiltro).equals(Constantes.SOM_OPCION_SELECCIONAR))?-1L:FacesUtils.checkLong(somTipoActividadFiltro);
			
			misActividadesDataModeler = new MisActividadesDataModeler(usuario_session.getUsu_login(),
				Constantes.ESTADO_ACTIVO, proyId, estaId, tiacId, sprintFiltro, casoSoporteFiltro, controlCambiosFiltro, businessDelegatorView);
			
		} catch (Exception e) {
			FacesUtils.addErrorOnlyMessage(e.getMessage());
		}
		return "";
	}
	
	public String reportarTiempo(ActionEvent actionEvent) {
		try {
			setEsVisibleDlgReportarTiempos(true);
			limpiarRegistroTiempos();
			Long asigId = Long.parseLong(actionEvent.getComponent().getAttributes().get("asigId").toString());
			asignacionReporteTiempos = businessDelegatorView.getVtAsignacion(asigId);
			VtActividad actividad = businessDelegatorView
					.getVtActividad(asignacionReporteTiempos.getVtActividad().getActiId());
			somEstado.setValue(actividad.getVtEstado().getEstaId());
		} catch (Exception e) {
			FacesUtils.addErrorOnlyMessage(e.getMessage());
		}
		return "";
	}

	public String cerrarDialogoReportarTiempos() {
		setEsVisibleDlgReportarTiempos(false);
		asignacionReporteTiempos = null;
		limpiarRegistroTiempos();
		return "";
	}

	public String guardarReporte() {
		try {
			if (validarGuardarReporteTiempos()) {
				return "";
			}
			VtEstado estado = businessDelegatorView.getVtEstado(FacesUtils.checkLong(somEstado));
			BigDecimal porcentajeAvance = estado.getEstaId().equals(Constantes.ESTADO_REALIZADO)?Constantes.CIEN:FacesUtils.checkBigDecimal(txtPorcentajeAvance);
			if (reporteTiempoModificar == null) {
				VtReporteTiempo reporteTiempo = new VtReporteTiempo();
				reporteTiempo.setActivo(Constantes.ESTADO_ACTIVO);
				reporteTiempo.setFechaCreacion(new Date());
				reporteTiempo.setFecha(FacesUtils.checkDate(calFecha));
				reporteTiempo.setHorasEjecutadas(FacesUtils.checkBigDecimal(txtHorasEjecutadas));
				reporteTiempo.setObservacion(FacesUtils.checkString(txtObservacion));
				reporteTiempo.setUsuaCreador(usuario_session.getUsu_login());
				reporteTiempo.setVtAsignacion(asignacionReporteTiempos);
				reporteTiempo.setVtEstado(estado);
				reporteTiempo.setPorcentajeAvance(porcentajeAvance);

				businessDelegatorView.saveVtReporteTiempo(reporteTiempo);

				VtActividad actividad = businessDelegatorView
						.getVtActividad(asignacionReporteTiempos.getVtActividad().getActiId());
				actividad.setVtEstado(estado);
				actividad.setPorcentajeAvance(porcentajeAvance);

				businessDelegatorView.updateVtActividad(actividad);
			} else {
				reporteTiempoModificar.setFechaModificacion(new Date());
				reporteTiempoModificar.setFecha(FacesUtils.checkDate(calFecha));
				reporteTiempoModificar.setHorasEjecutadas(FacesUtils.checkBigDecimal(txtHorasEjecutadas));
				reporteTiempoModificar.setObservacion(FacesUtils.checkString(txtObservacion));
				reporteTiempoModificar.setUsuaModificador(usuario_session.getUsu_login());
				reporteTiempoModificar.setVtEstado(estado);
				reporteTiempoModificar.setPorcentajeAvance(porcentajeAvance);

				businessDelegatorView.updateVtReporteTiempo(reporteTiempoModificar);
				VtAsignacion asignacion = businessDelegatorView
						.getVtAsignacion(reporteTiempoModificar.getVtAsignacion().getAsigId());
				VtActividad actividad = businessDelegatorView.getVtActividad(asignacion.getVtActividad().getActiId());
				actividad.setVtEstado(estado);
				actividad.setPorcentajeAvance(porcentajeAvance);

				businessDelegatorView.updateVtActividad(actividad);
			}

			FacesUtils.addInfoMessage(null, "msgGuardado");

			cerrarDialogoReportarTiempos();
		} catch (Exception e) {
			FacesUtils.addErrorOnlyMessage(e.getMessage());
		}
		return "";
	}

	public String modificarRegistroTiempo(ActionEvent actionEvent) {
		try {
			setEsVisibleDlgReportarTiempos(true);
			Long retiId = Long.parseLong(actionEvent.getComponent().getAttributes().get("retiId").toString());
			reporteTiempoModificar = businessDelegatorView.getVtReporteTiempo(retiId);

			calFecha.setValue(reporteTiempoModificar.getFecha());
			txtHorasEjecutadas.setValue(reporteTiempoModificar.getHorasEjecutadas());
			txtObservacion.setValue(reporteTiempoModificar.getObservacion());
			somEstado.setValue(reporteTiempoModificar.getVtEstado().getEstaId());
			txtPorcentajeAvance.setValue(reporteTiempoModificar.getPorcentajeAvance());
		} catch (Exception e) {
			FacesUtils.addErrorOnlyMessage(e.getMessage());
		} // dialogReporte
		return "";
	}

	public Boolean validarGuardarReporteTiempos() {
		Boolean isValidate = false;
		if (FacesUtils.checkDate(calFecha) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("calFechaFin", "msgCampoNoNulo");
		}
		if (FacesUtils.checkBigDecimal(txtHorasEjecutadas) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtHorasEjecutadas", "msgCampoNoNulo");
		}
		if (FacesUtils.checkString(txtObservacion) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtObservacion", "msgCampoNoNulo");
		}
		if (new Date().before(FacesUtils.checkDate(calFecha))) {
			isValidate = true;
			FacesUtils.addErrorMessage("calFecha", "msgFechaMayorHoy");
		}

		// Validar fechas
		double horasEjecutadas = FacesUtils.checkBigDecimal(txtHorasEjecutadas).doubleValue();
		if (horasEjecutadas <=0 || horasEjecutadas > 24) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtHorasEjecutadas", "msgHorasReportadasError");
		}
		
		return isValidate;
	}

	public String limpiarRegistroTiempos() {
		reporteTiempoModificar = null;
		calFecha.setValue(null);
		txtHorasEjecutadas.setValue(null);
		txtObservacion.setValue(null);
		somEstado.setValue(null);
		txtPorcentajeAvance.setValue(null);
		this.misActividadesDataModeler = new MisActividadesDataModeler(usuario_session.getUsu_login(),
				Constantes.ESTADO_ACTIVO, null, null, null, null, null, null, businessDelegatorView);
		return "";
	}

	public String verReportesTiempo(ActionEvent actionEvent) {
		try {
			setEsVisibleDlgReportesTiempos(true);
			Long asigId = Long.parseLong(actionEvent.getComponent().getAttributes().get("asigId").toString());

			Object[] variablesBusquedaReportes = { "vtAsignacion.asigId", false, asigId, "=", "activo", true,
					Constantes.ESTADO_ACTIVO, "=" };

			lstReporteTiempo = businessDelegatorView.findByCriteriaInVtReporteTiempo(variablesBusquedaReportes, null,
					null);

		} catch (Exception e) {
			FacesUtils.addErrorOnlyMessage(e.getMessage());
		}
		return "";
	}

	public String cerrarDlgReportesTiempo() {
		setEsVisibleDlgReportesTiempos(false);
		setLstReporteTiempo(null);
		return "";
	}

	public String verDescripcionActividad(ActionEvent actionEvent) {
		try {
			Long actiId = Long.parseLong(actionEvent.getComponent().getAttributes().get("actiId").toString());
			VtActividad actividad = businessDelegatorView.getVtActividad(actiId);
			strDescripcionActividad = actividad.getDescripcion();

			setEsVisibleDlgVerdescripcionActividad(true);
		} catch (Exception e) {
			FacesUtils.addErrorOnlyMessage(e.getMessage());
		}
		return "";
	}

	public String cerrarDlgVerDescripcionActividad() {
		setEsVisibleDlgVerdescripcionActividad(false);
		setStrDescripcionActividad(null);
		return "";
	}
	
	 public boolean filterByDate(Object value, Object filter, Locale locale) {

	        if( filter == null ) {
	            return true;
	        }

	        if( value == null ) {
	            return false;
	        }

	        return DateUtils.truncatedEquals((Date) filter, (Date) value, java.util.Calendar.DATE);
	    }
	
	/**
	 * Getters and Setters
	 */

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 * @return La entidad businessDelegatorView
	 *
	 */
	public BusinessDelegator getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	/**
	 *
	 * @param businessDelegatorView
	 *            El/La businessDelegatorView a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 *
	 */
	public void setBusinessDelegatorView(BusinessDelegator businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 * @return La entidad usuario_session
	 *
	 */
	public UsuarioDTO getUsuario_session() {
		return usuario_session;
	}

	/**
	 *
	 * @param usuario_session
	 *            El/La usuario_session a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 *
	 */
	public void setUsuario_session(UsuarioDTO usuario_session) {
		this.usuario_session = usuario_session;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 * @return La entidad esVisibleDlgReportarTiempos
	 *
	 */
	public boolean isEsVisibleDlgReportarTiempos() {
		return esVisibleDlgReportarTiempos;
	}

	/**
	 *
	 * @param esVisibleDlgReportarTiempos
	 *            El/La esVisibleDlgReportarTiempos a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 *
	 */
	public void setEsVisibleDlgReportarTiempos(boolean esVisibleDlgReportarTiempos) {
		this.esVisibleDlgReportarTiempos = esVisibleDlgReportarTiempos;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 * @return La entidad calFecha
	 *
	 */
	public Calendar getCalFecha() {
		return calFecha;
	}

	/**
	 *
	 * @param calFechaFin
	 *            El/La calFechaFin a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 *
	 */
	public void setCalFecha(Calendar calFecha) {
		this.calFecha = calFecha;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 * @return La entidad horasEjecutadas
	 *
	 */
	public InputNumber getTxtHorasEjecutadas() {
		return txtHorasEjecutadas;
	}

	/**
	 *
	 * @param horasEjecutadas
	 *            El/La horasEjecutadas a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtHorasEjecutadas(InputNumber txtHorasEjecutadas) {
		this.txtHorasEjecutadas = txtHorasEjecutadas;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 * @return La entidad txtObservacion
	 *
	 */
	public InputTextarea getTxtObservacion() {
		return txtObservacion;
	}

	/**
	 *
	 * @param txtObservacion
	 *            El/La txtObservacion a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtObservacion(InputTextarea txtObservacion) {
		this.txtObservacion = txtObservacion;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 * @return La entidad somEstado
	 *
	 */
	public SelectOneMenu getSomEstado() {
		return somEstado;
	}

	/**
	 *
	 * @param somEstado
	 *            El/La somEstado a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 *
	 */
	public void setSomEstado(SelectOneMenu somEstado) {
		this.somEstado = somEstado;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 * @return La entidad siEstados
	 *
	 */
	public List<SelectItem> getSiEstados() {
		try {
			if (siEstados == null) {
				siEstados = new ArrayList<>();
				List<VtEstado> estados = businessDelegatorView.getVtEstado();
				for (VtEstado vtEstado : estados) {
					siEstados.add(new SelectItem(vtEstado.getEstaId(), vtEstado.getDescripcion().trim().toUpperCase()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siEstados;
	}

	/**
	 *
	 * @param siEstados
	 *            El/La siEstados a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 *
	 */
	public void setSiEstados(List<SelectItem> siEstados) {
		this.siEstados = siEstados;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 15, 2018
	 * @since 1.8
	 * @return La entidad asignacionReporteTiempos
	 *
	 */
	public VtAsignacion getAsignacionReporteTiempos() {
		return asignacionReporteTiempos;
	}

	/**
	 *
	 * @param asignacionReporteTiempos
	 *            El/La asignacionReporteTiempos a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 15, 2018
	 * @since 1.8
	 *
	 */
	public void setAsignacionReporteTiempos(VtAsignacion asignacionReporteTiempos) {
		this.asignacionReporteTiempos = asignacionReporteTiempos;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 15, 2018
	 * @since 1.8
	 * @return La entidad lstReporteTiempo
	 *
	 */
	public List<VtReporteTiempo> getLstReporteTiempo() {
		return lstReporteTiempo;
	}

	/**
	 *
	 * @param lstReporteTiempo
	 *            El/La lstReporteTiempo a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 15, 2018
	 * @since 1.8
	 *
	 */
	public void setLstReporteTiempo(List<VtReporteTiempo> lstReporteTiempo) {
		this.lstReporteTiempo = lstReporteTiempo;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 15, 2018
	 * @since 1.8
	 * @return La entidad esVisibleDlgReportesTiempos
	 *
	 */
	public boolean isEsVisibleDlgReportesTiempos() {
		return esVisibleDlgReportesTiempos;
	}

	/**
	 *
	 * @param esVisibleDlgReportesTiempos
	 *            El/La esVisibleDlgReportesTiempos a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 15, 2018
	 * @since 1.8
	 *
	 */
	public void setEsVisibleDlgReportesTiempos(boolean esVisibleDlgReportesTiempos) {
		this.esVisibleDlgReportesTiempos = esVisibleDlgReportesTiempos;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 16, 2018
	 * @since 1.8
	 * @return La entidad strDescripcionActividad
	 *
	 */
	public String getStrDescripcionActividad() {
		return strDescripcionActividad;
	}

	/**
	 *
	 * @param strDescripcionActividad
	 *            El/La strDescripcionActividad a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 16, 2018
	 * @since 1.8
	 *
	 */
	public void setStrDescripcionActividad(String strDescripcionActividad) {
		this.strDescripcionActividad = strDescripcionActividad;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 16, 2018
	 * @since 1.8
	 * @return La entidad esVisibleDlgVerdescripcionActividad
	 *
	 */
	public boolean isEsVisibleDlgVerdescripcionActividad() {
		return esVisibleDlgVerdescripcionActividad;
	}

	/**
	 *
	 * @param esVisibleDlgVerdescripcionActividad
	 *            El/La esVisibleDlgVerdescripcionActividad a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 16, 2018
	 * @since 1.8
	 *
	 */
	public void setEsVisibleDlgVerdescripcionActividad(boolean esVisibleDlgVerdescripcionActividad) {
		this.esVisibleDlgVerdescripcionActividad = esVisibleDlgVerdescripcionActividad;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 16, 2018
	 * @since 1.8
	 * @return La entidad fechaActual
	 *
	 */
	public Date getFechaActual() {
		return fechaActual;
	}

	/**
	 *
	 * @param fechaActual
	 *            El/La fechaActual a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 16, 2018
	 * @since 1.8
	 *
	 */
	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 * @return La entidad reporteTiempoModificar
	 *
	 */
	public VtReporteTiempo getReporteTiempoModificar() {
		return reporteTiempoModificar;
	}

	/**
	 *
	 * @param reporteTiempoModificar
	 *            El/La reporteTiempoModificar a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 *
	 */
	public void setReporteTiempoModificar(VtReporteTiempo reporteTiempoModificar) {
		this.reporteTiempoModificar = reporteTiempoModificar;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad txtPorcentajeAvance
	 *
	 */
	public InputNumber getTxtPorcentajeAvance() {
		return txtPorcentajeAvance;
	}

	/**
	 *
	 * @param txtPorcentajeAvance El/La txtPorcentajeAvance a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtPorcentajeAvance(InputNumber txtPorcentajeAvance) {
		this.txtPorcentajeAvance = txtPorcentajeAvance;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 * @return La entidad somProyectoFiltro
	 *
	 */
	public SelectOneMenu getSomProyectoFiltro() {
		return somProyectoFiltro;
	}

	/**
	 *
	 * @param somProyectoFiltro El/La somProyectoFiltro a setear
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 *
	 */
	public void setSomProyectoFiltro(SelectOneMenu somProyectoFiltro) {
		this.somProyectoFiltro = somProyectoFiltro;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 * @return La entidad somEstadoFiltro
	 *
	 */
	public SelectOneMenu getSomEstadoFiltro() {
		return somEstadoFiltro;
	}

	/**
	 *
	 * @param somEstadoFiltro El/La somEstadoFiltro a setear
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 *
	 */
	public void setSomEstadoFiltro(SelectOneMenu somEstadoFiltro) {
		this.somEstadoFiltro = somEstadoFiltro;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 * @return La entidad somTipoActividadFiltro
	 *
	 */
	public SelectOneMenu getSomTipoActividadFiltro() {
		return somTipoActividadFiltro;
	}

	/**
	 *
	 * @param somTipoActividadFiltro El/La somTipoActividadFiltro a setear
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 *
	 */
	public void setSomTipoActividadFiltro(SelectOneMenu somTipoActividadFiltro) {
		this.somTipoActividadFiltro = somTipoActividadFiltro;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 * @return La entidad siProyectosFiltro
	 *
	 */
	public List<SelectItem> getSiProyectosFiltro() {
		try {
			if (siProyectosFiltro == null) {
				siProyectosFiltro = new ArrayList<>();

				VtPersonas persona = businessDelegatorView.consultarPersonaPorCorreo(usuario_session.getUsu_correo());

				List<VtProyectoDTO> proyectos = businessDelegatorView.consultaProyectosTodosPersona(persona.getPersId(),
						Constantes.ESTADO_ACTIVO);
				for (VtProyectoDTO vtProyectoDTO : proyectos) {
					siProyectosFiltro.add(new SelectItem(vtProyectoDTO.getProyId(),
							vtProyectoDTO.getNombreProyecto().trim().toUpperCase() + " - "
									+ vtProyectoDTO.getNombreCliente().trim().toUpperCase() + " - "
									+ vtProyectoDTO.getLineaNegocio().trim().toUpperCase()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siProyectosFiltro;
	}

	/**
	 *
	 * @param siProyectosFiltro El/La siProyectosFiltro a setear
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 *
	 */
	public void setSiProyectosFiltro(List<SelectItem> siProyectosFiltro) {
		this.siProyectosFiltro = siProyectosFiltro;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 * @return La entidad siTiposActividadFiltro
	 *
	 */
	public List<SelectItem> getSiTiposActividadFiltro() {
		try {
			if (siTiposActividadFiltro == null) {
				siTiposActividadFiltro = new ArrayList<>();
				List<VtTipoActividadDTO> tiposActividad = businessDelegatorView.tiposActividadesPorEstado(Constantes.ESTADO_ACTIVO);
				for (VtTipoActividadDTO vtTipoActividadDTO : tiposActividad) {
					siTiposActividadFiltro.add(new SelectItem(vtTipoActividadDTO.getTiacId(),
							vtTipoActividadDTO.getDescripcion().trim().toUpperCase()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siTiposActividadFiltro;
	}

	/**
	 *
	 * @param siTiposActividadFiltro El/La siTiposActividadFiltro a setear
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 *
	 */
	public void setSiTiposActividadFiltro(List<SelectItem> siTiposActividadFiltro) {
		this.siTiposActividadFiltro = siTiposActividadFiltro;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 * @return La entidad txtSprintFiltro
	 *
	 */
	public InputText getTxtSprintFiltro() {
		return txtSprintFiltro;
	}

	/**
	 *
	 * @param txtSprintFiltro El/La txtSprintFiltro a setear
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtSprintFiltro(InputText txtSprintFiltro) {
		this.txtSprintFiltro = txtSprintFiltro;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 * @return La entidad txtCasoSoporteFiltro
	 *
	 */
	public InputText getTxtCasoSoporteFiltro() {
		return txtCasoSoporteFiltro;
	}

	/**
	 *
	 * @param txtCasoSoporteFiltro El/La txtCasoSoporteFiltro a setear
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtCasoSoporteFiltro(InputText txtCasoSoporteFiltro) {
		this.txtCasoSoporteFiltro = txtCasoSoporteFiltro;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 * @return La entidad txtControlCambiosFiltro
	 *
	 */
	public InputText getTxtControlCambiosFiltro() {
		return txtControlCambiosFiltro;
	}

	/**
	 *
	 * @param txtControlCambiosFiltro El/La txtControlCambiosFiltro a setear
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtControlCambiosFiltro(InputText txtControlCambiosFiltro) {
		this.txtControlCambiosFiltro = txtControlCambiosFiltro;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 * @return La entidad misActividadesDataModeler
	 *
	 */
	public MisActividadesDataModeler getMisActividadesDataModeler() {
		return misActividadesDataModeler;
	}

	/**
	 *
	 * @param misActividadesDataModeler El/La misActividadesDataModeler a setear
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 * @since 1.8
	 *
	 */
	public void setMisActividadesDataModeler(MisActividadesDataModeler misActividadesDataModeler) {
		this.misActividadesDataModeler = misActividadesDataModeler;
	}

}
