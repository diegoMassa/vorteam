package com.vortexbird.vorteam.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.validator.ISBNValidator;
import org.primefaces.component.inputtext.InputText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.vorteam.utility.FacesUtils;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class RestaurarClaveView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(RestaurarClaveView.class);

	@ManagedProperty(value = "#{businessDelegator}")
	private BusinessDelegator businessDelegatorView;
	
	private InputText txtLogin;
	
	public RestaurarClaveView() {
		super();
	}

	public Boolean validarRestaurarClave() {
		Boolean isValidate = false;
		if(FacesUtils.checkString(txtLogin) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtLogin","msgCampoNoNulo");
		}
		return isValidate;
	}
	
	public void limpiarFormulario() {
		this.txtLogin.setValue(null);
	}
	
	public String restaurarClave() {
		try {
			if(validarRestaurarClave()) {
				return "";
			}
			businessDelegatorView.restaurarClave(FacesUtils.checkString(txtLogin));
			FacesUtils.addInfoMessage("msgCorreoClave");
			limpiarFormulario();
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtils.addErrorOnlyMessage(e.getMessage());
		}
		return "";
	}
	
	public String regresar() {
		return "/login.xhtml";
	}
	
	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version oct. 01, 2018
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
	 * @version oct. 01, 2018
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
	 * @return La entidad txtLogin
	 *
	 */
	public InputText getTxtLogin() {
		return txtLogin;
	}

	/**
	 *
	 * @param txtLogin El/La txtLogin a setear
	 * @author Daniel Pareja Londoño
	 * @version oct. 01, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtLogin(InputText txtLogin) {
		this.txtLogin = txtLogin;
	}

}
