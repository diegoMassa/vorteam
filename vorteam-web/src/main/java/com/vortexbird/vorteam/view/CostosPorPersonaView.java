package com.vortexbird.vorteam.view;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.sentinel.dto.UsuarioDTO;
import com.vortexbird.vorteam.domain.VtPersonas;
import com.vortexbird.vorteam.dto.VtProyectoDTO;
import com.vortexbird.vorteam.dto.VtTipoActividadDTO;
import com.vortexbird.vorteam.utility.Constantes;
import com.vortexbird.vorteam.utility.FacesUtils;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class CostosPorPersonaView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CostosPorPersonaView.class);

	@ManagedProperty(value = "#{businessDelegator}")
	private BusinessDelegator businessDelegatorView;
	private UsuarioDTO usuario_session;

	private Calendar calFechaInicial, calFechaFinal;
	private List<SelectItem> siPersonas, siProyectos, siTiposActividad, siEstadosProyecto;
	private SelectOneMenu somPersona, somProyecto, somTipoActividad, somEstadoProyecto;

	private StreamedContent fileReporteGenerado;

	private Date fechaInicio, fechaFin;
	private Long persId, proyId, tiacId;

	public Boolean validarGenerarReporte() {
		Boolean isValidate = false;
		if (FacesUtils.checkDate(calFechaFinal).before(FacesUtils.checkDate(calFechaInicial))) {
			isValidate = true;
			FacesUtils.addErrorMessage("calFechaFinal", "msgFechaInicioMayor");
			FacesUtils.addErrorMessage("calFechaInicial", "msgFechaInicioMayor");
		}
		return isValidate;
	}

	public void buscarProyectos() {
		try {
			setSiProyectos(null);
			if (!FacesUtils.checkString(somEstadoProyecto).equals(Constantes.SOM_OPCION_SELECCIONAR.toString())) {
				siProyectos = new ArrayList<SelectItem>();
				List<VtProyectoDTO> proyectoDTOs = businessDelegatorView
						.consultaProyectosClientes(FacesUtils.checkString(somEstadoProyecto));
				for (VtProyectoDTO vtProyectoDTO : proyectoDTOs) {
					siProyectos.add(new SelectItem(vtProyectoDTO.getProyId(),
							vtProyectoDTO.getNombreProyecto().trim().toUpperCase() + " - "
									+ vtProyectoDTO.getNombreCliente().trim().toUpperCase() + " - "
									+ vtProyectoDTO.getLineaNegocio().trim().toUpperCase()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
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
	 * @version sept. 20, 2018
	 * @since 1.8
	 *
	 */
	public void setBusinessDelegatorView(BusinessDelegator businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
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
	 * @version sept. 20, 2018
	 * @since 1.8
	 *
	 */
	public void setUsuario_session(UsuarioDTO usuario_session) {
		this.usuario_session = usuario_session;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 * @since 1.8
	 * @return La entidad calFechaInicial
	 *
	 */
	public Calendar getCalFechaInicial() {
		return calFechaInicial;
	}

	/**
	 *
	 * @param calFechaInicial
	 *            El/La calFechaInicial a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 * @since 1.8
	 *
	 */
	public void setCalFechaInicial(Calendar calFechaInicial) {
		this.calFechaInicial = calFechaInicial;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 * @since 1.8
	 * @return La entidad calFechaFinal
	 *
	 */
	public Calendar getCalFechaFinal() {
		return calFechaFinal;
	}

	/**
	 *
	 * @param calFechaFinal
	 *            El/La calFechaFinal a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 * @since 1.8
	 *
	 */
	public void setCalFechaFinal(Calendar calFechaFinal) {
		this.calFechaFinal = calFechaFinal;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 * @since 1.8
	 * @return La entidad siPersonas
	 *
	 */
	public List<SelectItem> getSiPersonas() {
		try {
			if (siPersonas == null) {
				siPersonas = new ArrayList<SelectItem>();
				Object[] variablesBusquedaPersonas = { "activo", true, Constantes.ESTADO_ACTIVO, "=" };
				List<VtPersonas> personas = businessDelegatorView.findByCriteriaInVtPersonas(variablesBusquedaPersonas,
						null, null);
				String nombreCompleto;
				for (VtPersonas persona : personas) {
					nombreCompleto = (persona.getNombre() != null ? persona.getNombre() : "") + " "
							+ (persona.getApellidos() != null ? persona.getApellidos() : "");
					nombreCompleto = nombreCompleto.trim().toUpperCase();
					siPersonas.add(new SelectItem(persona.getPersId(), nombreCompleto));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return siPersonas;
	}

	/**
	 *
	 * @param siPersonas
	 *            El/La siPersonas a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 * @since 1.8
	 *
	 */
	public void setSiPersonas(List<SelectItem> siPersonas) {
		this.siPersonas = siPersonas;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 * @since 1.8
	 * @return La entidad siProyectos
	 *
	 */
	public List<SelectItem> getSiProyectos() {
		return siProyectos;
	}

	/**
	 *
	 * @param siProyectos
	 *            El/La siProyectos a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 * @since 1.8
	 *
	 */
	public void setSiProyectos(List<SelectItem> siProyectos) {
		this.siProyectos = siProyectos;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 * @since 1.8
	 * @return La entidad siTiposActividad
	 *
	 */
	public List<SelectItem> getSiTiposActividad() {
		try {
			if (siTiposActividad == null) {
				siTiposActividad = new ArrayList<>();
				List<VtTipoActividadDTO> tiposActividad = businessDelegatorView.getDataVtTipoActividad();
				for (VtTipoActividadDTO vtTipoActividadDTO : tiposActividad) {
					siTiposActividad.add(new SelectItem(vtTipoActividadDTO.getTiacId(),
							vtTipoActividadDTO.getDescripcion().trim().toUpperCase()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siTiposActividad;
	}

	/**
	 *
	 * @param siTiposActividad
	 *            El/La siTiposActividad a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 * @since 1.8
	 *
	 */
	public void setSiTiposActividad(List<SelectItem> siTiposActividad) {
		this.siTiposActividad = siTiposActividad;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 * @since 1.8
	 * @return La entidad somPersona
	 *
	 */
	public SelectOneMenu getSomPersona() {
		return somPersona;
	}

	/**
	 *
	 * @param somPersona
	 *            El/La somPersona a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 * @since 1.8
	 *
	 */
	public void setSomPersona(SelectOneMenu somPersona) {
		this.somPersona = somPersona;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 * @since 1.8
	 * @return La entidad somProyecto
	 *
	 */
	public SelectOneMenu getSomProyecto() {
		return somProyecto;
	}

	/**
	 *
	 * @param somProyecto
	 *            El/La somProyecto a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 * @since 1.8
	 *
	 */
	public void setSomProyecto(SelectOneMenu somProyecto) {
		this.somProyecto = somProyecto;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 * @since 1.8
	 * @return La entidad somTipoActividad
	 *
	 */
	public SelectOneMenu getSomTipoActividad() {
		return somTipoActividad;
	}

	/**
	 *
	 * @param somTipoActividad
	 *            El/La somTipoActividad a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 * @since 1.8
	 *
	 */
	public void setSomTipoActividad(SelectOneMenu somTipoActividad) {
		this.somTipoActividad = somTipoActividad;
	}

	public void cargarDatosParaInforme() {
		fechaInicio = FacesUtils.checkDate(calFechaInicial);
		fechaFin = FacesUtils.checkDate(calFechaFinal);
		persId = FacesUtils.checkLong(somPersona).equals(Constantes.SOM_OPCION_SELECCIONAR) ? null
				: FacesUtils.checkLong(somPersona);
		proyId = FacesUtils.checkLong(somProyecto).equals(Constantes.SOM_OPCION_SELECCIONAR) ? null
				: FacesUtils.checkLong(somProyecto);
		tiacId = FacesUtils.checkLong(somTipoActividad).equals(Constantes.SOM_OPCION_SELECCIONAR) ? null
				: FacesUtils.checkLong(somTipoActividad);
	}

	public StreamedContent getReporteXlsx() {
		try {
			cargarDatosParaInforme();
			ByteArrayInputStream bais = businessDelegatorView.generarReporteCostosPorPersona(fechaInicio, fechaFin,
					persId, proyId, tiacId, Constantes.REPORT_OUTPUT_TYPE.XLSX);
			fileReporteGenerado = new DefaultStreamedContent(bais,
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "CostosPorPersona.xlsx");
			return fileReporteGenerado;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			return null;
		}
	}

	public StreamedContent getReporteXls() {
		try {
			cargarDatosParaInforme();
			ByteArrayInputStream bais = businessDelegatorView.generarReporteCostosPorPersona(fechaInicio, fechaFin,
					persId, proyId, tiacId, Constantes.REPORT_OUTPUT_TYPE.XLS);
			fileReporteGenerado = new DefaultStreamedContent(bais, "application/vnd.ms-excel", "CostosPorPersona.xls");
			return fileReporteGenerado;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			return null;
		}
	}

	public StreamedContent getReportePdf() {
		try {
			cargarDatosParaInforme();
			ByteArrayInputStream bais = businessDelegatorView.generarReporteCostosPorPersona(fechaInicio, fechaFin,
					persId, proyId, tiacId, Constantes.REPORT_OUTPUT_TYPE.PDF);
			fileReporteGenerado = new DefaultStreamedContent(bais, "application/pdf", "CostosPorPersona.pdf");
			return fileReporteGenerado;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			return null;
		}
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 18, 2018
	 * @since 1.8
	 * @return La entidad siEstadosProyecto
	 *
	 */
	public List<SelectItem> getSiEstadosProyecto() {
		try {
			if (siEstadosProyecto == null) {
				siEstadosProyecto = new ArrayList<>();
				for (String estado : Constantes.ESTADOS_PROYECTO) {
					siEstadosProyecto.add(
							new SelectItem(estado.trim().substring(0, 1).toUpperCase(), estado.trim().toUpperCase()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siEstadosProyecto;
	}

	/**
	 *
	 * @param siEstadosProyecto
	 *            El/La siEstadosProyecto a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 18, 2018
	 * @since 1.8
	 *
	 */
	public void setSiEstadosProyecto(List<SelectItem> siEstadosProyecto) {
		this.siEstadosProyecto = siEstadosProyecto;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 18, 2018
	 * @since 1.8
	 * @return La entidad somEstadoProyecto
	 *
	 */
	public SelectOneMenu getSomEstadoProyecto() {
		return somEstadoProyecto;
	}

	/**
	 *
	 * @param somEstadoProyecto
	 *            El/La somEstadoProyecto a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 18, 2018
	 * @since 1.8
	 *
	 */
	public void setSomEstadoProyecto(SelectOneMenu somEstadoProyecto) {
		this.somEstadoProyecto = somEstadoProyecto;
	}

}
