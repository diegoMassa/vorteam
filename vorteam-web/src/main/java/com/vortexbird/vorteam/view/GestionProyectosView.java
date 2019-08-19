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

import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.sentinel.dto.UsuarioDTO;
import com.vortexbird.vorteam.domain.VtCliente;
import com.vortexbird.vorteam.domain.VtLineaNegocio;
import com.vortexbird.vorteam.domain.VtProyecto;
import com.vortexbird.vorteam.domain.VtTipoIdentificacion;
import com.vortexbird.vorteam.dto.VtClienteDTO;
import com.vortexbird.vorteam.dto.VtLineaNegocioDTO;
import com.vortexbird.vorteam.dto.VtPersonasDTO;
import com.vortexbird.vorteam.dto.VtProyectoDTO;
import com.vortexbird.vorteam.utility.Constantes;
import com.vortexbird.vorteam.utility.FacesUtils;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class GestionProyectosView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(GestionProyectosView.class);

	@ManagedProperty(value = "#{businessDelegator}")
	private BusinessDelegator businessDelegatorView;

	// Componentes Proyecto
	private List<VtProyectoDTO> proyectos;

	private InputText txtNombreProyecto;
	private SelectOneMenu somCliente;
	private List<SelectItem> siClientes;
	private List<VtPersonasDTO> personas;
	private List<VtPersonasDTO> personasSeleccionadas;
	private boolean esVisibleDlgProyecto;
	private List<SelectItem> siLineasNegocio, siEstadosProyecto;
	private SelectOneMenu somLineaNegocio, somEstadoProyecto;
	private InputNumber txtCostoTotal;

	private VtProyecto proyectoModificar;

	private UsuarioDTO usuario_session;
	/**
	 *
	 *
	 * 
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 * @since 1.8
	 * @return <b>{@code }</b> Start here...
	 *
	 */

	public GestionProyectosView() {
		super();
		this.usuario_session = (UsuarioDTO) FacesUtils.getfromSession("usuarioDTOLogin");
	}

	/**
	 * Acciones Proyecto
	 */

	public void limpiarFormularioProyecto() {
		txtNombreProyecto.setValue(null);
		somCliente.setValue(Constantes.SOM_OPCION_SELECCIONAR);
		somLineaNegocio.setValue(Constantes.SOM_OPCION_SELECCIONAR);
		somEstadoProyecto.setValue(Constantes.ESTADO_ACTIVO);
		txtCostoTotal.setValue(null);
		setPersonasSeleccionadas(null);
		setPersonas(null);
	}

	public String nuevoProyecto() {
		limpiarFormularioProyecto();
		setEsVisibleDlgProyecto(true);
		setProyectoModificar(null);
		return "";
	}

	public String cerrarDlgProyecto() {
		limpiarFormularioProyecto();
		setEsVisibleDlgProyecto(false);
		return "";
	}

	public Boolean validarGuardarProyecto() {
		Boolean isValidate = false;
		if (FacesUtils.checkString(txtNombreProyecto) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtNombreProyecto", "msgCampoNoNulo");
		}
		if (FacesUtils.checkLong(somCliente).equals(Constantes.SOM_OPCION_SELECCIONAR)) {
			isValidate = true;
			FacesUtils.addErrorMessage("somCliente", "msgSeleccione");
		}
		if(FacesUtils.checkLong(somLineaNegocio).equals(Constantes.SOM_OPCION_SELECCIONAR)) {
			isValidate = true;
			FacesUtils.addErrorMessage("somLineaNegocio", "msgSeleccione");
		}
		if(FacesUtils.checkString(somEstadoProyecto).equals(Constantes.SOM_OPCION_SELECCIONAR+"")) {
			isValidate = true;
			FacesUtils.addErrorMessage("somEstadoProyecto", "msgSeleccione");
		}
		if(FacesUtils.checkLong(txtCostoTotal) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtCostoTotal", "msgCampoNoNulo");
		}
		return isValidate;
	}
	
	public Boolean validarUsuariosSeleccionados() {
		Boolean isValidate = false;
		for(VtPersonasDTO persona : personasSeleccionadas) {
			if(persona.getValorHora() == null) {
				isValidate = true;
				FacesUtils.addErrorMessage("dtPersonasProyecto","msgIngresarValorHora");
			}
		}
		return isValidate;
	}

	public String guardarProyecto() {
		try {
			if (validarGuardarProyecto()) {
				return "";
			}
			if(validarUsuariosSeleccionados()) {
				return "";
			}
			VtCliente cliente = businessDelegatorView.getVtCliente(FacesUtils.checkLong(somCliente));
			VtLineaNegocio lineaNegocio = businessDelegatorView.getVtLineaNegocio(FacesUtils.checkLong(somLineaNegocio));

			if (proyectoModificar == null) {
				VtProyecto proyecto = new VtProyecto();
				proyecto.setActivo(Constantes.ESTADO_ACTIVO);
				proyecto.setCostoTotal(FacesUtils.checkLong(txtCostoTotal));
				proyecto.setFechaCreacion(new Date());
				proyecto.setNombreProyecto(FacesUtils.checkString(txtNombreProyecto));
				proyecto.setUsuaCreador(usuario_session.getUsu_login());
				proyecto.setVtCliente(cliente);
				proyecto.setVtLineaNegocio(lineaNegocio);

				businessDelegatorView.guardarProyectoConCostosPersonas(proyecto, personasSeleccionadas);
			} else {
				proyectoModificar.setNombreProyecto(FacesUtils.checkString(txtNombreProyecto));
				proyectoModificar.setVtCliente(cliente);
				proyectoModificar.setVtLineaNegocio(lineaNegocio);
				proyectoModificar.setUsuaModificador(usuario_session.getUsu_login());
				proyectoModificar.setFechaModificacion(new Date());
				proyectoModificar.setActivo(FacesUtils.checkString(somEstadoProyecto));
				proyectoModificar.setCostoTotal(FacesUtils.checkLong(txtCostoTotal));
				businessDelegatorView.actualizarProyectoConCostosPersonas(proyectoModificar, personasSeleccionadas);
			}

			limpiarFormularioProyecto();
			FacesUtils.addInfoMessage(null, "msgGuardado");

			setProyectos(null);
			getProyectos();

			setEsVisibleDlgProyecto(false);
		} catch (Exception e) {
			FacesUtils.addErrorOnlyMessage(e.getMessage());
		}
		return "";
	}

	public String modificarProyecto(ActionEvent actionEvent) {
		try {
			setEsVisibleDlgProyecto(true);
			Long proyId = Long.parseLong(actionEvent.getComponent().getAttributes().get("proyId").toString());
			proyectoModificar = businessDelegatorView.getVtProyecto(proyId);
			personasSeleccionadas = businessDelegatorView.consultaPersonasProyecto(Constantes.ESTADO_ACTIVO,
					proyectoModificar.getProyId());
			
			for(VtPersonasDTO personaSeleccionada : personasSeleccionadas) {
				for(VtPersonasDTO personaDTO : personas) {
					if(personaDTO.getPersId().equals(personaSeleccionada.getPersId())) {
						personaDTO.setValorHora(personaSeleccionada.getValorHora());
					}
				}
			}
			
			txtNombreProyecto.setValue(proyectoModificar.getNombreProyecto());
			somCliente.setValue(proyectoModificar.getVtCliente().getClieId());
			somLineaNegocio.setValue(proyectoModificar.getVtLineaNegocio().getLineId());
			somEstadoProyecto.setValue(proyectoModificar.getActivo());
			txtCostoTotal.setValue(proyectoModificar.getCostoTotal());
		} catch (Exception e) {
			FacesUtils.addErrorMessage(null, e.getMessage());
		}
		return "";
	}

	/**
	 * Getters and Setters
	 */

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
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
	 * @version ago. 02, 2018
	 * @since 1.8
	 *
	 */
	public void setBusinessDelegatorView(BusinessDelegator businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 * @return La entidad txtNombreProyecto
	 *
	 */
	public InputText getTxtNombreProyecto() {
		return txtNombreProyecto;
	}

	/**
	 *
	 * @param txtNombreProyecto
	 *            El/La txtNombreProyecto a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtNombreProyecto(InputText txtNombreProyecto) {
		this.txtNombreProyecto = txtNombreProyecto;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 * @return La entidad somCliente
	 *
	 */
	public SelectOneMenu getSomCliente() {
		return somCliente;
	}

	/**
	 *
	 * @param somCliente
	 *            El/La somCliente a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 *
	 */
	public void setSomCliente(SelectOneMenu somCliente) {
		this.somCliente = somCliente;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 * @return La entidad siClientes
	 *
	 */
	public List<SelectItem> getSiClientes() {
		try {
			if (siClientes == null) {
				siClientes = new ArrayList<>();
				List<VtClienteDTO> clientes = businessDelegatorView.getDataVtCliente();
				for (VtClienteDTO vtClienteDTO : clientes) {
					siClientes.add(new SelectItem(vtClienteDTO.getClieId(),
							vtClienteDTO.getNombreRazonSocial().trim().toUpperCase()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siClientes;
	}

	/**
	 *
	 * @param siClientes
	 *            El/La siClientes a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 *
	 */
	public void setSiClientes(List<SelectItem> siClientes) {
		this.siClientes = siClientes;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 * @return La entidad personas
	 *
	 */
	public List<VtPersonasDTO> getPersonas() {
		try {
			if (personas == null) {
				personas = businessDelegatorView.consultaPersonasProyectos(Constantes.ESTADO_ACTIVO);
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
	 * @version ago. 02, 2018
	 * @since 1.8
	 *
	 */
	public void setPersonas(List<VtPersonasDTO> personas) {
		this.personas = personas;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 * @return La entidad proyectos
	 *
	 */
	public List<VtProyectoDTO> getProyectos() {
		try {
			if (proyectos == null) {
				proyectos = businessDelegatorView.consultaProyectosClientesRecursos(Constantes.ESTADO_ACTIVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proyectos;
	}

	/**
	 *
	 * @param proyectos
	 *            El/La proyectos a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 *
	 */
	public void setProyectos(List<VtProyectoDTO> proyectos) {
		this.proyectos = proyectos;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 * @since 1.8
	 * @return La entidad esVisibleDlgProyecto
	 *
	 */
	public boolean isEsVisibleDlgProyecto() {
		return esVisibleDlgProyecto;
	}

	/**
	 *
	 * @param esVisibleDlgProyecto
	 *            El/La esVisibleDlgProyecto a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 * @since 1.8
	 *
	 */
	public void setEsVisibleDlgProyecto(boolean esVisibleDlgProyecto) {
		this.esVisibleDlgProyecto = esVisibleDlgProyecto;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
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
	 * @version ago. 03, 2018
	 * @since 1.8
	 *
	 */
	public void setPersonasSeleccionadas(List<VtPersonasDTO> personasSeleccionadas) {
		this.personasSeleccionadas = personasSeleccionadas;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 * @since 1.8
	 * @return La entidad proyectoModificar
	 *
	 */
	public VtProyecto getProyectoModificar() {
		return proyectoModificar;
	}

	/**
	 *
	 * @param proyectoModificar
	 *            El/La proyectoModificar a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 * @since 1.8
	 *
	 */
	public void setProyectoModificar(VtProyecto proyectoModificar) {
		this.proyectoModificar = proyectoModificar;
	}
	
	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 * @since 1.8
	 * @return La entidad siLineasNegocio
	 *
	 */
	public List<SelectItem> getSiLineasNegocio() {
		try {
			if(siLineasNegocio == null) {
				siLineasNegocio = new ArrayList<>();
				List<VtLineaNegocioDTO> lineaNegocioDTOs = businessDelegatorView.getDataVtLineaNegocio();
				for (VtLineaNegocioDTO vtLineaNegocioDTO : lineaNegocioDTOs) {
					siLineasNegocio.add(new SelectItem(vtLineaNegocioDTO.getLineId(), vtLineaNegocioDTO.getDescripcion()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siLineasNegocio;
	}

	/**
	 *
	 * @param siLineasNegocio El/La siLineasNegocio a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 * @since 1.8
	 *
	 */
	public void setSiLineasNegocio(List<SelectItem> siLineasNegocio) {
		this.siLineasNegocio = siLineasNegocio;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 * @since 1.8
	 * @return La entidad somLineaNegocio
	 *
	 */
	public SelectOneMenu getSomLineaNegocio() {
		return somLineaNegocio;
	}

	/**
	 *
	 * @param somLineaNegocio El/La somLineaNegocio a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 * @since 1.8
	 *
	 */
	public void setSomLineaNegocio(SelectOneMenu somLineaNegocio) {
		this.somLineaNegocio = somLineaNegocio;
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
			if(siEstadosProyecto == null) {
				siEstadosProyecto = new ArrayList<>();
				for (String estado : Constantes.ESTADOS_PROYECTO) {
					siEstadosProyecto.add(new SelectItem(estado.trim().substring(0, 1).toUpperCase(), estado.trim().toUpperCase()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siEstadosProyecto;
	}

	/**
	 *
	 * @param siEstadosProyecto El/La siEstadosProyecto a setear
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
	 * @param somEstadoProyecto El/La somEstadoProyecto a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 18, 2018
	 * @since 1.8
	 *
	 */
	public void setSomEstadoProyecto(SelectOneMenu somEstadoProyecto) {
		this.somEstadoProyecto = somEstadoProyecto;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 21, 2018
	 * @since 1.8
	 * @return La entidad txtCostoTotal
	 *
	 */
	public InputNumber getTxtCostoTotal() {
		return txtCostoTotal;
	}

	/**
	 *
	 * @param txtCostoTotal El/La txtCostoTotal a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 21, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtCostoTotal(InputNumber txtCostoTotal) {
		this.txtCostoTotal = txtCostoTotal;
	}

}
