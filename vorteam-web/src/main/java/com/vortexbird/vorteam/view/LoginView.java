package com.vortexbird.vorteam.view;

import com.vortexbird.sentinel.dto.UsuarioDTO;
import com.vortexbird.vorteam.utility.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@ViewScoped
@ManagedBean(name = "loginView")
public class LoginView {
	private String userId;
	private String password;

	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager = null;

	@ManagedProperty(value = "#{businessDelegator}")
	private BusinessDelegator businessDelegator;

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String login() {
		try {
			
			UsuarioDTO usuarioDTO = businessDelegator.autenticar(userId+"@VORTEXBIRD.COM", password); 
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			
			if (usuarioDTO != null && usuarioDTO.getEstado() != null 
					&& usuarioDTO.getEstado().equals("2")) {
				session.setAttribute("usuarioDTOLogin", usuarioDTO);				
				FacesUtils.putinSession("loginSession", usuarioDTO.getUsu_login().trim());				
				FacesUtils.putinSession("sistema", "1");				
				FacesUtils.putinSession("compania", "1");				
				Authentication request = new UsernamePasswordAuthenticationToken(this.getUserId(),
						this.getPassword());
				Authentication result = authenticationManager.authenticate(request);
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(result);

				FacesUtils.getHttpSession(true).setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
			}else {
				throw new Exception("Login o password incorrecto.");
			}
			
		} catch (Exception e) {
			FacesUtils.addErrorOnlyMessage("Login o password incorrecto.");

			return "/login.xhtml";
		}

		return "/XHTML/initialMenu.xhtml";
	}
	
	public String restaurarClave() {
		return "/restaurarClave.xhtml";
	}

	public BusinessDelegator getBusinessDelegator() {
		return businessDelegator;
	}

	public void setBusinessDelegator(BusinessDelegator businessDelegator) {
		this.businessDelegator = businessDelegator;
	}


}
