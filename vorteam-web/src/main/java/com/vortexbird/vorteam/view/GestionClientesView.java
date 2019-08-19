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
import com.vortexbird.vorteam.dto.VtProyectoDTO;
import com.vortexbird.vorteam.utility.Constantes;
import com.vortexbird.vorteam.utility.FacesUtils;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class GestionClientesView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(GestionClientesView.class);

	@ManagedProperty(value = "#{businessDelegator}")
	private BusinessDelegator businessDelegatorView;

	//Componentes Cliente
	private InputText txtNombreRazonSocial;
	private SelectOneMenu somTipoIdentificacion;
	private InputText txtIdentificacion;
	private List<SelectItem> siTiposIdentificacion;
	private boolean esVisibleDlgCliente;
	private VtCliente clienteModificar;
	private List<VtProyectoDTO> proyectosClientesDto;

	//Componentes Proyecto
	private boolean esVisibleDlgProyecto;
	private VtCliente clienteProyecto;
	private InputText txtNombreProyecto;
	private VtProyecto proyectoModificar;
	private List<SelectItem> siLineasNegocio, siEstadosProyecto;
	private SelectOneMenu somLineaNegocio, somEstadoProyecto;
	private InputNumber txtCostoTotal;
	
	private UsuarioDTO usuario_session; 
	
	public GestionClientesView() {
		super();
		this.usuario_session = (UsuarioDTO) FacesUtils.getfromSession("usuarioDTOLogin");
	}

	/**
	 * Acciones Cliente
	 */
	
	public void limpiarFormularioClientes() {
		txtNombreRazonSocial.setValue(null);
		somTipoIdentificacion.setValue(Constantes.SOM_OPCION_SELECCIONAR);
		txtIdentificacion.setValue(null);
	}

	public String nuevoCliente() {
		limpiarFormularioClientes();
		setEsVisibleDlgCliente(true);
		setClienteModificar(null);
		return "";
	}
	
	public String cerrarDlgCliente() {
		limpiarFormularioClientes();
		setEsVisibleDlgCliente(false);
		return "";
	}

	public Boolean validarGuardarCliente() {
		Boolean isValidate = false;
		if (FacesUtils.checkString(txtNombreRazonSocial) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtNombreRazonSocial", "msgCampoNoNulo");
		}
		if (FacesUtils.checkLong(somTipoIdentificacion).equals(Constantes.SOM_OPCION_SELECCIONAR)) {
			isValidate = true;
			FacesUtils.addErrorMessage("somTipoIdentificacion", "msgSeleccione");
		}
		if (FacesUtils.checkString(txtIdentificacion) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtIdentificacion", "msgCampoNoNulo");
		}
		return isValidate;
	}

	public String guardarCliente() {
		try {

			if (validarGuardarCliente()) {
				return "";
			}
			VtTipoIdentificacion tipoIdentificacion = businessDelegatorView
					.getVtTipoIdentificacion(FacesUtils.checkLong(somTipoIdentificacion));
			if(clienteModificar == null) {
				
				VtCliente cliente = new VtCliente();
				cliente.setActivo(Constantes.ESTADO_ACTIVO);
				cliente.setFechaCreacion(new Date());
				cliente.setIdentificacion(FacesUtils.checkString(txtIdentificacion));
				cliente.setNombreRazonSocial(FacesUtils.checkString(txtNombreRazonSocial));
				cliente.setUsuaCreador(usuario_session.getUsu_login());
				cliente.setVtTipoIdentificacion(tipoIdentificacion);
				
				
				businessDelegatorView.saveVtCliente(cliente);
			}
			else {
				clienteModificar.setFechaModificacion(new Date());
				clienteModificar.setIdentificacion(FacesUtils.checkString(txtIdentificacion));
				clienteModificar.setNombreRazonSocial(FacesUtils.checkString(txtNombreRazonSocial));
				clienteModificar.setUsuaModificador(usuario_session.getUsu_login());
				clienteModificar.setVtTipoIdentificacion(tipoIdentificacion);
				
				businessDelegatorView.updateVtCliente(clienteModificar);
			}
			limpiarFormularioClientes();
			FacesUtils.addInfoMessage(null, "msgGuardado");
			
			setProyectosClientesDto(null);
			getProyectosClientesDto();
			
			setEsVisibleDlgCliente(false);

		} catch (Exception e) {
			FacesUtils.addErrorMessage(null, e.getMessage());
		}
		return "";
	}
	
	public String modificarCliente(ActionEvent actionEvent) {
		try {
			setEsVisibleDlgCliente(true);
			Long clieId = Long.parseLong(actionEvent.getComponent().getAttributes().get("clieId").toString());
			clienteModificar = businessDelegatorView.getVtCliente(clieId);

			txtNombreRazonSocial.setValue(clienteModificar.getNombreRazonSocial());
			txtIdentificacion.setValue(clienteModificar.getIdentificacion());
			somTipoIdentificacion.setValue(clienteModificar.getVtTipoIdentificacion().getTiidId());
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(null, e.getMessage());
		}
		return "";
	}
	
	/**
	 * Acciones Proyecto
	 */
	
	public void limpiarFormularioProyecto() {
		txtNombreProyecto.setValue(null);
		somLineaNegocio.setValue(Constantes.SOM_OPCION_SELECCIONAR);
		somEstadoProyecto.setValue(Constantes.ESTADO_ACTIVO);
		txtCostoTotal.setValue(null);
	}
	
	public String cerrarDlgProyecto() {
		limpiarFormularioProyecto();
		setEsVisibleDlgProyecto(false);
		return "";
	}
	
	public String nuevoProyecto(ActionEvent actionEvent) {
		try {
			setEsVisibleDlgProyecto(true);
			Long clieId = Long.parseLong(actionEvent.getComponent().getAttributes().get("clieId").toString());
			clienteProyecto = businessDelegatorView.getVtCliente(clieId);
			proyectoModificar = null;
			limpiarFormularioProyecto();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(null, e.getMessage());
		}
		return "";
	}
	
	public Boolean validarGuardarProyecto() {
		Boolean isValidate = false;
		if (FacesUtils.checkString(txtNombreProyecto) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtNombreProyecto", "msgCampoNoNulo");
		}
		if(FacesUtils.checkLong(somLineaNegocio).equals(Constantes.SOM_OPCION_SELECCIONAR)) {
			isValidate = true;
			FacesUtils.addErrorMessage("somLineaNegocio", "msgSeleccione");
		}
		if(FacesUtils.checkString(somEstadoProyecto).equals(Constantes.SOM_OPCION_SELECCIONAR+"")) {
			isValidate = true;
			FacesUtils.addErrorMessage("somEstadoProyecto", "msgSeleccione");
		}
		if(FacesUtils.checkLong(txtCostoTotal)==null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtCostoTotal", "msgCampoNoNulo");
		}
		return isValidate;
	}

	
	public String guardarProyecto() {
		try {

			if (validarGuardarProyecto()) {
				return "";
			}
			
			VtLineaNegocio lineaNegocio = businessDelegatorView.getVtLineaNegocio(FacesUtils.checkLong(somLineaNegocio));
			
			if(proyectoModificar == null) {
				VtProyecto proyecto = new VtProyecto();
				proyecto.setActivo(Constantes.ESTADO_ACTIVO);
				proyecto.setCostoTotal(FacesUtils.checkLong(txtCostoTotal));
				proyecto.setFechaCreacion(new Date());
				proyecto.setNombreProyecto(FacesUtils.checkString(txtNombreProyecto));
				proyecto.setUsuaCreador(usuario_session.getUsu_login());
				proyecto.setVtCliente(clienteProyecto);
				proyecto.setVtLineaNegocio(lineaNegocio);
				
				businessDelegatorView.saveVtProyecto(proyecto);
			}
			else {
				proyectoModificar.setNombreProyecto(FacesUtils.checkString(txtNombreProyecto));
				proyectoModificar.setVtLineaNegocio(lineaNegocio);
				proyectoModificar.setUsuaModificador(usuario_session.getUsu_login());
				proyectoModificar.setFechaModificacion(new Date());
				proyectoModificar.setActivo(FacesUtils.checkString(somEstadoProyecto));
				proyectoModificar.setCostoTotal(FacesUtils.checkLong(txtCostoTotal));
				businessDelegatorView.updateVtProyecto(proyectoModificar);
			}
			limpiarFormularioProyecto();
			FacesUtils.addInfoMessage(null, "msgGuardado");
			
			setProyectosClientesDto(null);
			getProyectosClientesDto();
			
			setEsVisibleDlgProyecto(false);

		} catch (Exception e) {
			FacesUtils.addErrorMessage(null, e.getMessage());
		}
		return "";
	}
	
	public String modificarProyecto(ActionEvent actionEvent) {
		try {
			setEsVisibleDlgProyecto(true);
			Long proyId = Long.parseLong(actionEvent.getComponent().getAttributes().get("proyId").toString());
			proyectoModificar = businessDelegatorView.getVtProyecto(proyId);

			txtNombreProyecto.setValue(proyectoModificar.getNombreProyecto());
			somLineaNegocio.setValue(proyectoModificar.getVtLineaNegocio().getLineId());
			somEstadoProyecto.setValue(proyectoModificar.getActivo());
			txtCostoTotal.setValue(proyectoModificar.getCostoTotal());
		} catch (Exception e) {
			FacesUtils.addErrorMessage(null, e.getMessage());
		}
		return "";
	}
	
	/**
	 * Getters And Setters
	 */

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
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
	 * @version ago. 01, 2018
	 * @since 1.8
	 *
	 */
	public void setBusinessDelegatorView(BusinessDelegator businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 * @return La entidad txtNombreRazonSocial
	 *
	 */
	public InputText getTxtNombreRazonSocial() {
		return txtNombreRazonSocial;
	}

	/**
	 *
	 * @param txtNombreRazonSocial
	 *            El/La txtNombreRazonSocial a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtNombreRazonSocial(InputText txtNombreRazonSocial) {
		this.txtNombreRazonSocial = txtNombreRazonSocial;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 * @return La entidad somTipoIdentificacion
	 *
	 */
	public SelectOneMenu getSomTipoIdentificacion() {
		return somTipoIdentificacion;
	}

	/**
	 *
	 * @param somTipoIdentificacion
	 *            El/La somTipoIdentificacion a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 *
	 */
	public void setSomTipoIdentificacion(SelectOneMenu somTipoIdentificacion) {
		this.somTipoIdentificacion = somTipoIdentificacion;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 * @return La entidad txtIdentificacion
	 *
	 */
	public InputText getTxtIdentificacion() {
		return txtIdentificacion;
	}

	/**
	 *
	 * @param txtIdentificacion
	 *            El/La txtIdentificacion a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtIdentificacion(InputText txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 * @return La entidad esVisibleDlgCliente
	 *
	 */
	public boolean isEsVisibleDlgCliente() {
		return esVisibleDlgCliente;
	}

	/**
	 *
	 * @param esVisibleDlgCliente
	 *            El/La esVisibleDlgCliente a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 *
	 */
	public void setEsVisibleDlgCliente(boolean esVisibleDlgCliente) {
		this.esVisibleDlgCliente = esVisibleDlgCliente;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 * @return La entidad siTiposIdentificacion
	 *
	 */
	public List<SelectItem> getSiTiposIdentificacion() {
		try {
			if (siTiposIdentificacion == null) {
				siTiposIdentificacion = new ArrayList<>();
				List<VtTipoIdentificacion> vtTipoIdentificaciones = businessDelegatorView.getVtTipoIdentificacion();
				if (vtTipoIdentificaciones != null && !vtTipoIdentificaciones.isEmpty()) {
					for (VtTipoIdentificacion vtTipoIdentificacion : vtTipoIdentificaciones) {
						siTiposIdentificacion.add(new SelectItem(vtTipoIdentificacion.getTiidId(),
								vtTipoIdentificacion.getDescripcion().toUpperCase()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siTiposIdentificacion;
	}

	/**
	 *
	 * @param siTiposIdentificacion
	 *            El/La siTiposIdentificacion a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 *
	 */
	public void setSiTiposIdentificacion(List<SelectItem> siTiposIdentificacion) {
		this.siTiposIdentificacion = siTiposIdentificacion;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 * @return La entidad clienteModificar
	 *
	 */
	public VtCliente getClienteModificar() {
		return clienteModificar;
	}

	/**
	 *
	 * @param clienteModificar El/La clienteModificar a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 *
	 */
	public void setClienteModificar(VtCliente clienteModificar) {
		this.clienteModificar = clienteModificar;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 * @return La entidad esVisibleDlgProyecto
	 *
	 */
	public boolean isEsVisibleDlgProyecto() {
		return esVisibleDlgProyecto;
	}

	/**
	 *
	 * @param esVisibleDlgProyecto El/La esVisibleDlgProyecto a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 *
	 */
	public void setEsVisibleDlgProyecto(boolean esVisibleDlgProyecto) {
		this.esVisibleDlgProyecto = esVisibleDlgProyecto;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 * @return La entidad clienteProyecto
	 *
	 */
	public VtCliente getClienteProyecto() {
		return clienteProyecto;
	}

	/**
	 *
	 * @param clienteProyecto El/La clienteProyecto a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 *
	 */
	public void setClienteProyecto(VtCliente clienteProyecto) {
		this.clienteProyecto = clienteProyecto;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 * @return La entidad txtNombreProyecto
	 *
	 */
	public InputText getTxtNombreProyecto() {
		return txtNombreProyecto;
	}

	/**
	 *
	 * @param txtNombreProyecto El/La txtNombreProyecto a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
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
	 * @return La entidad proyectosClientesDto
	 *
	 */
	public List<VtProyectoDTO> getProyectosClientesDto() {
		try {
			if(proyectosClientesDto == null) {
				proyectosClientesDto = businessDelegatorView.consultaProyectosClientes(Constantes.ESTADO_ACTIVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proyectosClientesDto;
	}

	/**
	 *
	 * @param proyectosClientesDto El/La proyectosClientesDto a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 *
	 */
	public void setProyectosClientesDto(List<VtProyectoDTO> proyectosClientesDto) {
		this.proyectosClientesDto = proyectosClientesDto;
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
	 * @param proyectoModificar El/La proyectoModificar a setear
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
