package com.vortexbird.vorteam.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.password.Password;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.sentinel.dto.UsuarioDTO;
import com.vortexbird.vorteam.dto.VtPersonasDTO;
import com.vortexbird.vorteam.dto.VtProyectoDTO;
import com.vortexbird.vorteam.utility.Constantes;
import com.vortexbird.vorteam.utility.FacesUtils;
import com.vortexbird.vorteam.utility.Utilities;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class GestionPersonasView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(GestionPersonasView.class);

	@ManagedProperty(value = "#{businessDelegator}")
	private BusinessDelegator businessDelegatorView;

	//Componentes Usuarios
	private InputText txtNombres, txtApellidos, txtEmail;
	private InputTextarea txtProyectosSeleccionados;
	private InputNumber txtSalario, txtValorHora;
	private List<VtProyectoDTO> lstProyectos, lstProyectosSeleccionados;
	private Password pwdPassword, pwdConfirmarPassword;
	private boolean esVisibleDlgProyectos, esAdministrador;
	private VtPersonasDTO personaModificar;
	
	private List<VtPersonasDTO> personas;

	private UsuarioDTO usuario_session;
	
	public GestionPersonasView() {
		super();
		this.usuario_session = (UsuarioDTO) FacesUtils.getfromSession("usuarioDTOLogin");
	}

	/*
	 * Acciones
	 */
	public String asignarProyectos() {
		setEsVisibleDlgProyectos(true);
		return "";
	}

	public String cerrarDialogoAsignarProyectos() {
		setEsVisibleDlgProyectos(false);
		return "";
	}

	public String seleccionarProyectos() {
		try {
			if (!lstProyectosSeleccionados.isEmpty()) {
				String strProyectosSeleccionados = "";
				for (int i = 0; i < lstProyectosSeleccionados.size(); i++) {
					if (i == (lstProyectosSeleccionados.size() - 1)) {
						strProyectosSeleccionados += lstProyectosSeleccionados.get(i).getNombreProyecto() + " - "
								+ lstProyectosSeleccionados.get(i).getLineaNegocio() + ".";
					} else {
						strProyectosSeleccionados += lstProyectosSeleccionados.get(i).getNombreProyecto() + " - "
								+ lstProyectosSeleccionados.get(i).getLineaNegocio() + ", ";
					}
				}
				txtProyectosSeleccionados.setValue(strProyectosSeleccionados);
			} else {
				txtProyectosSeleccionados.setValue("");
			}
			cerrarDialogoAsignarProyectos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public Boolean validarGuardarPersona() {
		Boolean isValidate = false;
		if(FacesUtils.checkString(txtNombres) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtNombres","msgCampoNoNulo");
		}
		if(FacesUtils.checkString(txtApellidos) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtApellidos","msgCampoNoNulo");
		}
		if(FacesUtils.checkString(txtEmail) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtEmail","msgCampoNoNulo");
		}
		if(FacesUtils.checkString(pwdPassword) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("pwdPassword","msgCampoNoNulo");
		}
		if(FacesUtils.checkString(pwdConfirmarPassword) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("pwdConfirmarPassword","msgCampoNoNulo");
		}
		if(FacesUtils.checkLong(txtSalario) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtSalario","msgCampoNoNulo");
		}
		if(FacesUtils.checkLong(txtValorHora) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtValorHora","msgCampoNoNulo");
		}
		if(!FacesUtils.checkString(pwdPassword).trim().equals(FacesUtils.checkString(pwdConfirmarPassword).trim())) {
			isValidate = true;
			FacesUtils.addErrorMessage("pwdPassword","msgPasswordDistintos");
			FacesUtils.addErrorMessage("pwdConfirmarPassword","msgPasswordDistintos");
		}
		if(!Utilities.isValidEmail(FacesUtils.checkString(txtEmail))) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtEmail","msgEmailNoValido");
		}
		if(lstProyectosSeleccionados != null) {
			for (VtProyectoDTO vtProyectoDTO : lstProyectosSeleccionados) {
				if(vtProyectoDTO.getValorHoraRecurso() == null || vtProyectoDTO.getValorHoraRecurso().equals(0L)) {
					isValidate = true;
					FacesUtils.addErrorMessage("txtProyectosSeleccionados","msgAgregarValorHora");
				}
			}
		}
		return isValidate;
	}
	
	public void limpiarFormularioPersonas() {
		txtNombres.setValue(null);
		txtApellidos.setValue(null);
		txtEmail.setValue(null);
		txtProyectosSeleccionados.setValue(null);
		txtSalario.setValue(null);
		txtValorHora.setValue(null);
		setLstProyectos(null);
		getLstProyectos();
		setLstProyectosSeleccionados(null);
		getLstProyectosSeleccionados();
		pwdPassword.setValue(null);
		pwdConfirmarPassword.setValue(null);
		setEsAdministrador(false);
	}
	
	public String guardarPersona() {
		try {
			if(validarGuardarPersona()) {
				return "";
			}
			if(personaModificar == null) {
				VtPersonasDTO persona = new VtPersonasDTO();
				persona.setNombre(FacesUtils.checkString(txtNombres));
				persona.setApellidos(FacesUtils.checkString(txtApellidos));
				persona.setEmail(FacesUtils.checkString(txtEmail));
				persona.setSalario(FacesUtils.checkLong(txtSalario));
				persona.setValorHora(FacesUtils.checkLong(txtValorHora));
				persona.setPassword(FacesUtils.checkString(pwdPassword));
				persona.setActivo(Constantes.ESTADO_ACTIVO);
				persona.setUsuaCreador(usuario_session.getUsu_login());
				persona.setFechaCreacion(new Date());
				persona.setEsAdministrador(esAdministrador);
				
				businessDelegatorView.guardarPersonasConCostosProyecto(persona, lstProyectosSeleccionados);
			}else {
				personaModificar.setEmail(FacesUtils.checkString(txtEmail));
				personaModificar.setSalario(FacesUtils.checkLong(txtSalario));
				personaModificar.setValorHora(FacesUtils.checkLong(txtValorHora));
				personaModificar.setEsAdministrador(esAdministrador);
				personaModificar.setFechaModificacion(new Date());
				personaModificar.setUsuaModificador(usuario_session.getUsu_login());
				businessDelegatorView.actualizarPersonasConCostosProyecto(personaModificar, lstProyectosSeleccionados);
			}
			
			limpiarFormularioPersonas();
			FacesUtils.addInfoMessage(null, "msgGuardado");
			
			setLstProyectos(null);
			getLstProyectos();
			
			setPersonas(null);
			getPersonas();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(null, e.getMessage());
		}
		return "";
	}

	/*
	 * Getters and Setters
	 */

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
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
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setBusinessDelegatorView(BusinessDelegator businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad txtNombres
	 *
	 */
	public InputText getTxtNombres() {
		return txtNombres;
	}

	/**
	 *
	 * @param txtNombres
	 *            El/La txtNombres a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtNombres(InputText txtNombres) {
		this.txtNombres = txtNombres;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad txtApellidos
	 *
	 */
	public InputText getTxtApellidos() {
		return txtApellidos;
	}

	/**
	 *
	 * @param txtApellidos
	 *            El/La txtApellidos a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtApellidos(InputText txtApellidos) {
		this.txtApellidos = txtApellidos;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad txtEmail
	 *
	 */
	public InputText getTxtEmail() {
		return txtEmail;
	}

	/**
	 *
	 * @param txtEmail
	 *            El/La txtEmail a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtEmail(InputText txtEmail) {
		this.txtEmail = txtEmail;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad txtSalario
	 *
	 */
	public InputNumber getTxtSalario() {
		return txtSalario;
	}

	/**
	 *
	 * @param txtSalario
	 *            El/La txtSalario a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtSalario(InputNumber txtSalario) {
		this.txtSalario = txtSalario;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad txtValorHora
	 *
	 */
	public InputNumber getTxtValorHora() {
		return txtValorHora;
	}

	/**
	 *
	 * @param txtValorHora
	 *            El/La txtValorHora a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtValorHora(InputNumber txtValorHora) {
		this.txtValorHora = txtValorHora;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad lstProyectos
	 *
	 */
	public List<VtProyectoDTO> getLstProyectos() {
		try {
			if (lstProyectos == null) {
				lstProyectos = businessDelegatorView.consultaProyectos(Constantes.ESTADO_ACTIVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstProyectos;
	}

	/**
	 *
	 * @param lstProyectos
	 *            El/La lstProyectos a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setLstProyectos(List<VtProyectoDTO> lstProyectos) {
		this.lstProyectos = lstProyectos;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad lstProyectosSeleccionados
	 *
	 */
	public List<VtProyectoDTO> getLstProyectosSeleccionados() {
		return lstProyectosSeleccionados;
	}

	/**
	 *
	 * @param lstProyectosSeleccionados
	 *            El/La lstProyectosSeleccionados a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setLstProyectosSeleccionados(List<VtProyectoDTO> lstProyectosSeleccionados) {
		this.lstProyectosSeleccionados = lstProyectosSeleccionados;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad pwdPassword
	 *
	 */
	public Password getPwdPassword() {
		return pwdPassword;
	}

	/**
	 *
	 * @param pwdPassword
	 *            El/La pwdPassword a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setPwdPassword(Password pwdPassword) {
		this.pwdPassword = pwdPassword;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad pwdConfirmarPassword
	 *
	 */
	public Password getPwdConfirmarPassword() {
		return pwdConfirmarPassword;
	}

	/**
	 *
	 * @param pwdConfirmarPassword
	 *            El/La pwdConfirmarPassword a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setPwdConfirmarPassword(Password pwdConfirmarPassword) {
		this.pwdConfirmarPassword = pwdConfirmarPassword;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad txtProyectosSeleccionados
	 *
	 */
	public InputTextarea getTxtProyectosSeleccionados() {
		return txtProyectosSeleccionados;
	}

	/**
	 *
	 * @param txtProyectosSeleccionados
	 *            El/La txtProyectosSeleccionados a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtProyectosSeleccionados(InputTextarea txtProyectosSeleccionados) {
		this.txtProyectosSeleccionados = txtProyectosSeleccionados;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad esVisibleDlgProyectos
	 *
	 */
	public boolean isEsVisibleDlgProyectos() {
		return esVisibleDlgProyectos;
	}

	/**
	 *
	 * @param esVisibleDlgProyectos
	 *            El/La esVisibleDlgProyectos a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setEsVisibleDlgProyectos(boolean esVisibleDlgProyectos) {
		this.esVisibleDlgProyectos = esVisibleDlgProyectos;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad personaModificar
	 *
	 */
	public VtPersonasDTO getPersonaModificar() {
		return personaModificar;
	}

	/**
	 *
	 * @param personaModificar El/La personaModificar a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setPersonaModificar(VtPersonasDTO personaModificar) {
		this.personaModificar = personaModificar;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad esAdministrador
	 *
	 */
	public boolean isEsAdministrador() {
		return esAdministrador;
	}

	/**
	 *
	 * @param esAdministrador El/La esAdministrador a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setEsAdministrador(boolean esAdministrador) {
		this.esAdministrador = esAdministrador;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
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
	 * @param personas El/La personas a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setPersonas(List<VtPersonasDTO> personas) {
		this.personas = personas;
	}

}
