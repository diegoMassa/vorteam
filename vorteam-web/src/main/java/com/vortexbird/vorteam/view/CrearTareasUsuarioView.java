package com.vortexbird.vorteam.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

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
import com.vortexbird.vorteam.domain.VtProyecto;
import com.vortexbird.vorteam.domain.VtReporteTiempo;
import com.vortexbird.vorteam.domain.VtTipoActividad;
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
public class CrearTareasUsuarioView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CrearTareasUsuarioView.class);

	@ManagedProperty(value = "#{businessDelegator}")
	private BusinessDelegator businessDelegatorView;

	private InputText txtNombreActividad, txtSprint, txtCasoSoporte, txtControlCambios;
	private InputTextarea txtDescripcionActividad;
	private SelectOneMenu somTipoActividad, somProyecto, somEstado;
	private List<SelectItem> siTipoActividad, siProyecto, siEstados;
	private Calendar calFecha;
	private Date fechaActual, fechaActual1;
	private InputNumber txtHorasEjecutadas;
	private InputNumber txtPorcentajeAvance;

	private VtActividad actividadModificar;

	private UsuarioDTO usuario_session;

	public CrearTareasUsuarioView() {
		super();
		this.usuario_session = (UsuarioDTO) FacesUtils.getfromSession("usuarioDTOLogin");
		this.fechaActual = new Date();
		this.fechaActual1 = fechaActual;
	}

	public Boolean validarGuardarActividad() {
		Boolean isValidate = false;
		if (FacesUtils.checkLong(somTipoActividad).equals(Constantes.SOM_OPCION_SELECCIONAR)) {
			isValidate = true;
			FacesUtils.addErrorMessage("somTipoActividad", "msgSeleccione");
		}
		if (FacesUtils.checkLong(somProyecto).equals(Constantes.SOM_OPCION_SELECCIONAR)) {
			isValidate = true;
			FacesUtils.addErrorMessage("somProyecto", "msgSeleccione");
		}
		if (FacesUtils.checkLong(somEstado).equals(Constantes.SOM_OPCION_SELECCIONAR)) {
			isValidate = true;
			FacesUtils.addErrorMessage("somEstado", "msgSeleccione");
		}
		if (FacesUtils.checkString(txtNombreActividad) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtNombreActividad", "msgCampoNoNulo");
		}
		if (FacesUtils.checkString(txtDescripcionActividad) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtDescripcionActividad", "msgCampoNoNulo");
		}
		if (FacesUtils.checkDate(calFecha) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("calFecha", "msgCampoNoNulo");
		}
		if (fechaActual1.before(FacesUtils.checkDate(calFecha))) {
			isValidate = true;
			FacesUtils.addErrorMessage("calFecha", "msgFechaMayorHoy");
		}
		if (FacesUtils.checkBigDecimal(txtHorasEjecutadas) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtHorasEjecutadas", "msgCampoNoNulo");
		}

		// Validar fechas
		double horasEjecutadas = FacesUtils.checkBigDecimal(txtHorasEjecutadas).doubleValue();
		if (horasEjecutadas <=0 || horasEjecutadas > 24) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtHorasEjecutadas", "msgHorasReportadasError");
		}

		// Validar si se selecciono Control de Cambios
		if (FacesUtils.checkLong(somTipoActividad).equals(Constantes.CONTROL_CAMBIOS_ID)) {
			if (FacesUtils.checkString(txtControlCambios) == null) {
				isValidate = true;
				FacesUtils.addErrorMessage("txtControlCambios", "msgCampoNoNulo");
			}
		}

		// Validar si se selecciono Caso Soporte
		if (FacesUtils.checkLong(somTipoActividad).equals(Constantes.SOPORTE_ID)) {
			if (FacesUtils.checkString(txtCasoSoporte) == null) {
				isValidate = true;
				FacesUtils.addErrorMessage("txtCasoSoporte", "msgCampoNoNulo");
			}
		}

		return isValidate;
	}

	public String guardarActividad() {
		try {
			if (validarGuardarActividad()) {
				return "";
			}
			VtTipoActividad tipoActividad = businessDelegatorView
					.getVtTipoActividad(FacesUtils.checkLong(somTipoActividad));
			VtProyecto proyecto = businessDelegatorView.getVtProyecto(FacesUtils.checkLong(somProyecto));
			VtEstado estado = businessDelegatorView.getVtEstado(FacesUtils.checkLong(somEstado));
			Date fechaActual = new Date();
			BigDecimal porcentajeAvance = estado.getEstaId().equals(Constantes.ESTADO_REALIZADO)?Constantes.CIEN:FacesUtils.checkBigDecimal(txtPorcentajeAvance);
			if (actividadModificar == null) {
				VtActividad actividad = new VtActividad();
				actividad.setVtTipoActividad(tipoActividad);
				actividad.setVtProyecto(proyecto);
				actividad.setVtEstado(estado);
				actividad.setNombre(FacesUtils.checkString(txtNombreActividad));
				actividad.setDescripcion(FacesUtils.checkString(txtDescripcionActividad));
				actividad.setSprint(FacesUtils.checkString(txtSprint));
				actividad.setCasoSoporte(FacesUtils.checkString(txtCasoSoporte));
				actividad.setControlCambios(FacesUtils.checkString(txtControlCambios));
				actividad.setFacturable(Constantes.NO);
				actividad.setActivo(Constantes.ESTADO_ACTIVO);
				actividad.setUsuaCreador(usuario_session.getUsu_login());
				actividad.setFechaCreacion(fechaActual);
				actividad.setPorcentajeAvance(porcentajeAvance);

				businessDelegatorView.saveVtActividad(actividad);

				Object[] variablesBusquedaUsuario = { "email", true,
						usuario_session.getUsu_login().trim().toLowerCase(), "=" };
				List<VtPersonas> lstPersonas = businessDelegatorView
						.findByCriteriaInVtPersonas(variablesBusquedaUsuario, null, null);
				if (lstPersonas.isEmpty()) {
					throw new Exception(
							"No existe la persona con usuario " + usuario_session.getUsu_login().trim().toLowerCase());
				}
				VtPersonas persona = lstPersonas.get(0);

				VtAsignacion asignacion = new VtAsignacion(null, Constantes.ESTADO_ACTIVO, fechaActual, null,
						usuario_session.getUsu_login().trim(), null, actividad, persona, null);
				businessDelegatorView.saveVtAsignacion(asignacion);
				
				

				VtReporteTiempo reporteTiempo = new VtReporteTiempo(null, Constantes.ESTADO_ACTIVO, fechaActual,
						FacesUtils.checkDate(calFecha), null, FacesUtils.checkBigDecimal(txtHorasEjecutadas),
						FacesUtils.checkString(txtDescripcionActividad), usuario_session.getUsu_login().trim(), null,
						asignacion, estado, porcentajeAvance);

				businessDelegatorView.saveVtReporteTiempo(reporteTiempo);

				FacesUtils.addInfoMessage(null, "msgGuardado");

			} else {

			}
			limpiarFormlarioActividad();

		} catch (Exception e) {
			FacesUtils.addErrorMessage(null, e.getMessage());
		}
		return "";
	}

	public void limpiarFormlarioActividad() {
		txtNombreActividad.setValue(null);
		txtSprint.setValue(null);
		txtCasoSoporte.setValue(null);
		txtDescripcionActividad.setValue(null);
		somTipoActividad.setValue(Constantes.SOM_OPCION_SELECCIONAR);
		somProyecto.setValue(Constantes.SOM_OPCION_SELECCIONAR);
		somEstado.setValue(Constantes.ESTADO_REALIZADO);
		txtDescripcionActividad.setValue(null);
		calFecha.setValue(null);
		txtHorasEjecutadas.setValue(null);
		actividadModificar = null;
		txtControlCambios.setValue(null);
		txtPorcentajeAvance.setValue(null);
	}

	/**
	 * Getters and Setters
	 */

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
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
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setBusinessDelegatorView(BusinessDelegator businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 * @return La entidad txtNombreActividad
	 *
	 */
	public InputText getTxtNombreActividad() {
		return txtNombreActividad;
	}

	/**
	 *
	 * @param txtNombreActividad
	 *            El/La txtNombreActividad a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtNombreActividad(InputText txtNombreActividad) {
		this.txtNombreActividad = txtNombreActividad;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 * @return La entidad txtSprint
	 *
	 */
	public InputText getTxtSprint() {
		return txtSprint;
	}

	/**
	 *
	 * @param txtSprint
	 *            El/La txtSprint a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtSprint(InputText txtSprint) {
		this.txtSprint = txtSprint;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 * @return La entidad txtCasoSoporte
	 *
	 */
	public InputText getTxtCasoSoporte() {
		return txtCasoSoporte;
	}

	/**
	 *
	 * @param txtCasoSoporte
	 *            El/La txtCasoSoporte a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtCasoSoporte(InputText txtCasoSoporte) {
		this.txtCasoSoporte = txtCasoSoporte;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 * @return La entidad txtDescripcionActividad
	 *
	 */
	public InputTextarea getTxtDescripcionActividad() {
		return txtDescripcionActividad;
	}

	/**
	 *
	 * @param txtDescripcionActividad
	 *            El/La txtDescripcionActividad a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtDescripcionActividad(InputTextarea txtDescripcionActividad) {
		this.txtDescripcionActividad = txtDescripcionActividad;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
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
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setSomTipoActividad(SelectOneMenu somTipoActividad) {
		this.somTipoActividad = somTipoActividad;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
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
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setSomProyecto(SelectOneMenu somProyecto) {
		this.somProyecto = somProyecto;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 * @return La entidad siTipoActividad
	 *
	 */
	public List<SelectItem> getSiTipoActividad() {
		try {
			if (siTipoActividad == null) {
				siTipoActividad = new ArrayList<>();
				List<VtTipoActividadDTO> tiposActividad = businessDelegatorView.tiposActividadesPorEstado(Constantes.ESTADO_ACTIVO);
				for (VtTipoActividadDTO vtTipoActividadDTO : tiposActividad) {
					siTipoActividad.add(new SelectItem(vtTipoActividadDTO.getTiacId(),
							vtTipoActividadDTO.getDescripcion().trim().toUpperCase()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siTipoActividad;
	}

	/**
	 *
	 * @param siTipoActividad
	 *            El/La siTipoActividad a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setSiTipoActividad(List<SelectItem> siTipoActividad) {
		this.siTipoActividad = siTipoActividad;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 * @return La entidad siProyecto
	 *
	 */
	public List<SelectItem> getSiProyecto() {
		try {
			if (siProyecto == null) {
				siProyecto = new ArrayList<>();

				VtPersonas persona = businessDelegatorView.consultarPersonaPorCorreo(usuario_session.getUsu_correo());

				List<VtProyectoDTO> proyectos = businessDelegatorView.consultaProyectosActivosPersona(persona.getPersId(),
						Constantes.ESTADO_ACTIVO);
				for (VtProyectoDTO vtProyectoDTO : proyectos) {
					siProyecto.add(new SelectItem(vtProyectoDTO.getProyId(),
							vtProyectoDTO.getNombreProyecto().trim().toUpperCase() + " - "
									+ vtProyectoDTO.getNombreCliente().trim().toUpperCase() + " - "
									+ vtProyectoDTO.getLineaNegocio().trim().toUpperCase()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siProyecto;
	}

	/**
	 *
	 * @param siProyecto
	 *            El/La siProyecto a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setSiProyecto(List<SelectItem> siProyecto) {
		this.siProyecto = siProyecto;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 * @return La entidad actividadModificar
	 *
	 */
	public VtActividad getActividadModificar() {
		return actividadModificar;
	}

	/**
	 *
	 * @param actividadModificar
	 *            El/La actividadModificar a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setActividadModificar(VtActividad actividadModificar) {
		this.actividadModificar = actividadModificar;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 * @return La entidad calFechaFin
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
	 * @return La entidad txtHorasEjecutadas
	 *
	 */
	public InputNumber getTxtHorasEjecutadas() {
		return txtHorasEjecutadas;
	}

	/**
	 *
	 * @param txtHorasEjecutadas
	 *            El/La txtHorasEjecutadas a setear
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
	 * @version ago. 16, 2018
	 * @since 1.8
	 * @return La entidad somEstado
	 *
	 */
	public SelectOneMenu getSomEstado() {
		if (somEstado == null) {
			somEstado = new SelectOneMenu();
			getSiEstados();
		}
		somEstado.setValue(Constantes.ESTADO_REALIZADO);
		return somEstado;
	}

	/**
	 *
	 * @param somEstado
	 *            El/La somEstado a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 16, 2018
	 * @since 1.8
	 *
	 */
	public void setSomEstado(SelectOneMenu somEstado) {
		this.somEstado = somEstado;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 16, 2018
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
	 *            El/La siEstado a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 16, 2018
	 * @since 1.8
	 *
	 */
	public void setSiEstados(List<SelectItem> siEstados) {
		this.siEstados = siEstados;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 31, 2018
	 * @since 1.8
	 * @return La entidad txtControlCambios
	 *
	 */
	public InputText getTxtControlCambios() {
		return txtControlCambios;
	}

	/**
	 *
	 * @param txtControlCambios
	 *            El/La txtControlCambios a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 31, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtControlCambios(InputText txtControlCambios) {
		this.txtControlCambios = txtControlCambios;
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

}
