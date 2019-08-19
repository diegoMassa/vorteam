package com.vortexbird.vorteam.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.sentinel.dto.UsuarioDTO;
import com.vortexbird.vorteam.domain.VtActividad;
import com.vortexbird.vorteam.domain.VtEstado;
import com.vortexbird.vorteam.domain.VtProyecto;
import com.vortexbird.vorteam.domain.VtTipoActividad;
import com.vortexbird.vorteam.dto.VtActividadDTO;
import com.vortexbird.vorteam.dto.VtPersonasDTO;
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
public class CrearTareasAdministradorView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CrearTareasAdministradorView.class);

	@ManagedProperty(value = "#{businessDelegator}")
	private BusinessDelegator businessDelegatorView;

	private InputText txtNombreActividad, txtSprint, txtCasoSoporte, txtControlCambios;
	private InputTextarea txtDescripcionActividad;
	private Calendar calFechaLimite;
	private InputNumber txtHorasPresupuestadas, txtValorFacturable;
	private SelectOneMenu somTipoActividad, somProyecto;
	private List<SelectItem> siFacturable, siTipoActividad, siProyecto;
	private List<VtPersonasDTO> personas, personasSeleccionadas;
	private InputTextarea txtPersonasSeleccionadas;
	private boolean esVisibleDlgAsignacion;
	private String esFacturable; // Por defecto se va a dejar en N (No) el usuario por pantalla puede cambiarlo a
									// Si
	private VtActividad actividadModificar;
	private List<VtActividadDTO> actividadesOrdenadasDescendientes;

	private UsuarioDTO usuario_session;

	public CrearTareasAdministradorView() {
		super();
		this.usuario_session = (UsuarioDTO) FacesUtils.getfromSession("usuarioDTOLogin");
		esFacturable = Constantes.NO;
	}

	/**
	 * Acciones y Métodos
	 */
	public String asignarUsuarios() {
		setEsVisibleDlgAsignacion(true);
		return "";
	}

	public String cerrarDialogoAsignarPersonas() {
		setEsVisibleDlgAsignacion(false);
		return "";
	}

	public String seleccionarUsuarios() {
		try {
			if (!personasSeleccionadas.isEmpty()) {
				String strPersonasSeleccionadas = "";
				for (int i = 0; i < personasSeleccionadas.size(); i++) {
					if (i == (personasSeleccionadas.size() - 1)) {
						strPersonasSeleccionadas += personasSeleccionadas.get(i).getNombreCompleto() + ".";
					} else {
						strPersonasSeleccionadas += personasSeleccionadas.get(i).getNombreCompleto() + ", ";
					}
				}
				txtPersonasSeleccionadas.setValue(strPersonasSeleccionadas);
			} else {
				txtPersonasSeleccionadas.setValue("");
			}
			cerrarDialogoAsignarPersonas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
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
		if (FacesUtils.checkString(txtNombreActividad) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtNombreActividad", "msgCampoNoNulo");
		}
		if (esFacturable == null || esFacturable.trim().equals("")) {
			isValidate = true;
			FacesUtils.addErrorMessage("sorFacturable", "msgSeleccione");
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
			VtEstado estado = businessDelegatorView.getVtEstado(Constantes.ESTADO_CREADO_ID);
			String mensajeGuardado = "";
			if (actividadModificar == null) {
				VtActividad actividad = new VtActividad();
				actividad.setVtTipoActividad(tipoActividad);
				actividad.setVtProyecto(proyecto);
				actividad.setVtEstado(estado);
				actividad.setNombre(FacesUtils.checkString(txtNombreActividad));
				actividad.setDescripcion(FacesUtils.checkString(txtDescripcionActividad));
				actividad.setFechaLimite(FacesUtils.checkDate(calFechaLimite));
				actividad.setHorasPresupuestadas(FacesUtils.checkBigDecimal(txtHorasPresupuestadas));
				actividad.setSprint(FacesUtils.checkString(txtSprint));
				actividad.setCasoSoporte(FacesUtils.checkString(txtCasoSoporte));
				actividad.setFacturable(esFacturable.trim().toUpperCase());
				actividad.setValorFacturable(FacesUtils.checkLong(txtValorFacturable));
				actividad.setActivo(Constantes.ESTADO_ACTIVO);
				actividad.setUsuaCreador(usuario_session.getUsu_login());
				actividad.setFechaCreacion(new Date());
				actividad.setControlCambios(FacesUtils.checkString(txtControlCambios));

				mensajeGuardado = businessDelegatorView.guardarActividadConAsignacionesPersonas(actividad,
						personasSeleccionadas);
			} else {

			}
			limpiarFormlarioActividad();
			setActividadesOrdenadasDescendientes(null);
			getActividadesOrdenadasDescendientes();
			if (mensajeGuardado.trim().equals("")) {
				FacesUtils.addInfoMessage(null, "msgGuardado");
			} else {
				FacesUtils.addInfoOnlyMessage(mensajeGuardado);
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(null, e.getMessage());
		}
		return "";
	}

	public void limpiarFormlarioActividad() {
		txtNombreActividad.setValue(null);
		somProyecto.setValue(Constantes.SOM_OPCION_SELECCIONAR);
		somTipoActividad.setValue(Constantes.SOM_OPCION_SELECCIONAR);
		personasSeleccionadas = null;
		txtPersonasSeleccionadas.setValue(null);
		txtDescripcionActividad.setValue(null);
		calFechaLimite.setValue(null);
		txtHorasPresupuestadas.setValue(null);
		txtSprint.setValue(null);
		txtCasoSoporte.setValue(null);
		esFacturable = Constantes.NO;
		txtValorFacturable.setValue(null);
		setPersonas(null);
		actividadModificar = null;
		txtControlCambios.setValue(null);
	}

	public String modificarActividad(ActionEvent actionEvent) {
		try {
			Long actiId = Long.parseLong(actionEvent.getComponent().getAttributes().get("actiId").toString());
			actividadModificar = businessDelegatorView.getVtActividad(actiId);
			personasSeleccionadas = businessDelegatorView.consultaPersonasActividad(actiId, Constantes.ESTADO_ACTIVO);
			txtNombreActividad.setValue(actividadModificar.getNombre());
			somProyecto.setValue(actividadModificar.getVtProyecto().getProyId());
			somTipoActividad.setValue(actividadModificar.getVtTipoActividad().getTiacId());
			txtDescripcionActividad.setValue(actividadModificar.getDescripcion());
			calFechaLimite.setValue(actividadModificar.getFechaLimite());
			txtHorasPresupuestadas.setValue(actividadModificar.getHorasPresupuestadas());
			txtSprint.setValue(actividadModificar.getSprint());
			txtCasoSoporte.setValue(actividadModificar.getCasoSoporte());
			esFacturable = actividadModificar.getFacturable();
			txtValorFacturable.setValue(actividadModificar.getValorFacturable());
			txtControlCambios.setValue(actividadModificar.getControlCambios());
			seleccionarUsuarios();
		} catch (Exception e) {
			FacesUtils.addErrorOnlyMessage(e.getMessage());
		}
		return "";
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
	 * @return La entidad calFechaLimite
	 *
	 */
	public Calendar getCalFechaLimite() {
		return calFechaLimite;
	}

	/**
	 *
	 * @param calFechaLimite
	 *            El/La calFechaLimite a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setCalFechaLimite(Calendar calFechaLimite) {
		this.calFechaLimite = calFechaLimite;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 * @return La entidad txtHorasPresupuestadas
	 *
	 */
	public InputNumber getTxtHorasPresupuestadas() {
		return txtHorasPresupuestadas;
	}

	/**
	 *
	 * @param txtHorasPresupuestadas
	 *            El/La txtHorasPresupuestadas a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtHorasPresupuestadas(InputNumber txtHorasPresupuestadas) {
		this.txtHorasPresupuestadas = txtHorasPresupuestadas;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 * @return La entidad txtValorFacturable
	 *
	 */
	public InputNumber getTxtValorFacturable() {
		return txtValorFacturable;
	}

	/**
	 *
	 * @param txtValorFacturable
	 *            El/La txtValorFacturable a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtValorFacturable(InputNumber txtValorFacturable) {
		this.txtValorFacturable = txtValorFacturable;
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
	 * @return La entidad personas
	 *
	 */
	public List<VtPersonasDTO> getPersonas() {
		try {
			if (personas == null) {
				personas = businessDelegatorView.consultaPersonas(Constantes.ESTADO_ACTIVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personas;
	}

	/**
	 *
	 * @param personas
	 *            El/La personas a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setPersonas(List<VtPersonasDTO> personas) {
		this.personas = personas;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 * @return La entidad personasSeleccionadas
	 *
	 */
	public List<VtPersonasDTO> getPersonasSeleccionadas() {
		return personasSeleccionadas;
	}

	/**
	 *
	 * @param personasSeleccionadas
	 *            El/La personasSeleccionadas a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setPersonasSeleccionadas(List<VtPersonasDTO> personasSeleccionadas) {
		this.personasSeleccionadas = personasSeleccionadas;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 * @return La entidad siFacturable
	 *
	 */
	public List<SelectItem> getSiFacturable() {
		return siFacturable;
	}

	/**
	 *
	 * @param siFacturable
	 *            El/La siFacturable a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setSiFacturable(List<SelectItem> siFacturable) {
		this.siFacturable = siFacturable;
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
				List<VtProyectoDTO> proyectos = businessDelegatorView.consultaProyectos(Constantes.ESTADO_ACTIVO);
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
	 * @return La entidad esVisibleDlgAsignacion
	 *
	 */
	public boolean isEsVisibleDlgAsignacion() {
		return esVisibleDlgAsignacion;
	}

	/**
	 *
	 * @param esVisibleDlgAsignacion
	 *            El/La esVisibleDlgAsignacion a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setEsVisibleDlgAsignacion(boolean esVisibleDlgAsignacion) {
		this.esVisibleDlgAsignacion = esVisibleDlgAsignacion;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 * @return La entidad txtPersonasSeleccionadas
	 *
	 */
	public InputTextarea getTxtPersonasSeleccionadas() {
		return txtPersonasSeleccionadas;
	}

	/**
	 *
	 * @param txtPersonasSeleccionadas
	 *            El/La txtPersonasSeleccionadas a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtPersonasSeleccionadas(InputTextarea txtPersonasSeleccionadas) {
		this.txtPersonasSeleccionadas = txtPersonasSeleccionadas;
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
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad actividadesOrdenadasDescendientes
	 *
	 */
	public List<VtActividadDTO> getActividadesOrdenadasDescendientes() {
		try {
			if (actividadesOrdenadasDescendientes == null) {
				actividadesOrdenadasDescendientes = businessDelegatorView
						.consultaActividadesOrdenadaFechaDescendiente(Constantes.ESTADO_ACTIVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actividadesOrdenadasDescendientes;
	}

	/**
	 *
	 * @param actividadesOrdenadasDescendientes
	 *            El/La actividadesOrdenadasDescendientes a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setActividadesOrdenadasDescendientes(List<VtActividadDTO> actividadesOrdenadasDescendientes) {
		this.actividadesOrdenadasDescendientes = actividadesOrdenadasDescendientes;
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
	 * @version sept. 17, 2018
	 * @since 1.8
	 * @return La entidad esFacturable
	 *
	 */
	public String getEsFacturable() {
		return esFacturable;
	}

	/**
	 *
	 * @param esFacturable El/La esFacturable a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 *
	 */
	public void setEsFacturable(String esFacturable) {
		this.esFacturable = esFacturable;
	}

}
