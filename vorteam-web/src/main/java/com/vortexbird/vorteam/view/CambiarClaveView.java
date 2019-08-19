package com.vortexbird.vorteam.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.password.Password;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.sentinel.dto.UsuarioDTO;
import com.vortexbird.vorteam.domain.VtPersonas;
import com.vortexbird.vorteam.dto.VtPersonasDTO;
import com.vortexbird.vorteam.utility.FacesUtils;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class CambiarClaveView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CambiarClaveView.class);

	@ManagedProperty(value = "#{businessDelegator}")
	private BusinessDelegator businessDelegatorView;
	UsuarioDTO usuario_session;

	// Componentes cambio de clave
	private Password pwdClaveActual, pwdConfirmaNuevaClave, pwdNuevaClave;

	public CambiarClaveView() {
		super();
		this.usuario_session = (UsuarioDTO) FacesUtils.getfromSession("usuarioDTOLogin");
	}

	public Boolean validarCambioClave() {
		Boolean isValidate = false;
		try {
			if (FacesUtils.checkString(pwdClaveActual) == null) {
				isValidate = true;
				FacesUtils.addErrorMessage("pwdClaveActual", "msgCampoNoNulo");
			}
			if(FacesUtils.checkString(pwdNuevaClave) == null) {
				isValidate = true;
				FacesUtils.addErrorMessage("pwdNuevaClave", "msgCampoNoNulo");
			}
			if (FacesUtils.checkString(pwdConfirmaNuevaClave) == null) {
				isValidate = true;
				FacesUtils.addErrorMessage("pwdConfirmaNuevaClave", "msgCampoNoNulo");
			}
			if (FacesUtils.checkString(pwdNuevaClave) != null
					&& FacesUtils.checkString(pwdConfirmaNuevaClave) != null) {
				if (!FacesUtils.checkString(pwdNuevaClave).trim()
						.equals(FacesUtils.checkString(pwdConfirmaNuevaClave).trim())) {
					isValidate = true;
					FacesUtils.addErrorMessage("pwdNuevaClave", "msgPasswordDistintos");
					FacesUtils.addErrorMessage("pwdConfirmaNuevaClave", "msgPasswordDistintos");
				} else {
					UsuarioDTO validarClaveActual = businessDelegatorView.autenticar(
							usuario_session.getUsu_login().trim().toUpperCase(),
							FacesUtils.checkString(pwdClaveActual));
					if (validarClaveActual.getEstado().trim().equals("3")) {
						isValidate = true;
						FacesUtils.addErrorMessage("pwdClaveActual", "msgPasswordActualIncorrecto");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isValidate;
	}

	public void limpiarFormulario() {
		this.pwdClaveActual.setValue(null);
		this.pwdConfirmaNuevaClave.setValue(null);
		this.pwdNuevaClave.setValue(null);
	}

	public String cambiarClave() {
		try {
			if (validarCambioClave()) {
				return "";
			}
			String nuevaClave = FacesUtils.checkString(pwdNuevaClave).trim();
			VtPersonas persona = businessDelegatorView.consultarPersonaPorCorreo(usuario_session.getUsu_login().trim());
			if (persona != null) {
				VtPersonasDTO personaDTO = new VtPersonasDTO();
				personaDTO.setApellidos(persona.getApellidos().trim());
				personaDTO.setPassword(nuevaClave);
				personaDTO.setEmail(persona.getEmail().trim());
				personaDTO.setNombre(persona.getNombre().trim());
				
				businessDelegatorView.cambiarClave(personaDTO, usuario_session);
				limpiarFormulario();
				FacesUtils.addInfoMessage("msgActualizaClave");
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtils.addErrorOnlyMessage(e.getMessage());
		}
		return "";
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad pwdClaveActual
	 *
	 */
	public Password getPwdClaveActual() {
		return pwdClaveActual;
	}

	/**
	 *
	 * @param pwdClaveActual
	 *            El/La pwdClaveActual a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setPwdClaveActual(Password pwdClaveActual) {
		this.pwdClaveActual = pwdClaveActual;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad pwdNuevaClave
	 *
	 */
	public Password getPwdNuevaClave() {
		return pwdNuevaClave;
	}

	/**
	 *
	 * @param pwdNuevaClave
	 *            El/La pwdNuevaClave a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setPwdNuevaClave(Password pwdNuevaClave) {
		this.pwdNuevaClave = pwdNuevaClave;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad businessDelegatorView
	 *
	 */
	public BusinessDelegator getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	/**
	 *
	 * @param businessDelegatorView El/La businessDelegatorView a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setBusinessDelegatorView(BusinessDelegator businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version oct. 01, 2018
	 * @since 1.8
	 * @return La entidad pwdConfirmaNuevaClave
	 *
	 */
	public Password getPwdConfirmaNuevaClave() {
		return pwdConfirmaNuevaClave;
	}

	/**
	 *
	 * @param pwdConfirmaNuevaClave El/La pwdConfirmaNuevaClave a setear
	 * @author Daniel Pareja Londoño
	 * @version oct. 01, 2018
	 * @since 1.8
	 *
	 */
	public void setPwdConfirmaNuevaClave(Password pwdConfirmaNuevaClave) {
		this.pwdConfirmaNuevaClave = pwdConfirmaNuevaClave;
	}

}
