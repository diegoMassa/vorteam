package com.vortexbird.sentinel.presentation.backingBeans;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vortexbird.sentinel.modelo.SegCompania;
import com.vortexbird.sentinel.modelo.SegSistema;
import com.vortexbird.sentinel.modelo.dto.UsuarioDTO;
import com.vortexbird.sentinel.presentation.businessDelegate.IBusinessDelegatorView;
import com.vortexbird.sentinel.utilities.FacesUtils;

@ManagedBean
@ViewScoped
public class LoginView {

	private String login = "";
	private String password = "";
	private String dominio = "";

	private UsuarioDTO usuarioDTO;

	private boolean usuarioAutenticado = false;

	private List<SelectItem> listSistemas;
	private List<SelectItem> listCompanias;
	private List<SelectItem> listSucursales;

	private Long selectedSistema;
	private Long selectedCompania;

	private static final Logger logger = LoggerFactory.getLogger(LoginView.class);

	@ManagedProperty(value="#{authenticationManager}")
	private AuthenticationManager authenticationManager = null;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;


	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@PostConstruct
	public void loginViewPC(){
		try {

			if (FacesUtils.getManagedBeanFromSession("sistemas") != null){
				listSistemas = (List<SelectItem>)FacesUtils.getManagedBeanFromSession("sistemas");
			}

			if (FacesUtils.getManagedBeanFromSession("companias") != null){
				listCompanias = (List<SelectItem>)FacesUtils.getManagedBeanFromSession("companias");
			}

			if (FacesUtils.getManagedBeanFromSession("sistema") != null){
				selectedSistema = (Long)FacesUtils.getManagedBeanFromSession("sistema");
			}

		} catch (Exception e) {
			Log.error(e.getMessage(), e);
		}
	}

	public String login() {

		try {

			if(login.trim().equals("") || password.trim().equals("")
					|| dominio.trim().equals("")) {
				throw new Exception("Ingrese login, password y dominio.");
			}

			usuarioAutenticado = false;
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			usuarioDTO = businessDelegatorView.autenticar(login, password, dominio);

			if (usuarioDTO==null){
				throw new Exception("No existe el usuario. Intente de nuevo");
			}else{

				logger.info("Estado de autenticación: "+usuarioDTO.getEstado());

				//Los únicos estados válidos para autenticar son: 1 = Usuario LDAP y 2 = Usuario de BD de seguridad
				if (!usuarioDTO.getEstado().equals("1") && !usuarioDTO.getEstado().equals("2")){
					throw new Exception("No existe el usuario, la clave es incorrecta o no tiene acceso a las aplicaciones.");
				}else{

					usuarioAutenticado = true;

					session.setAttribute("loginSession", usuarioDTO.getUsu_login());
					//session.setAttribute("cia", "");
					session.setAttribute("usuActivo", usuarioDTO.getUsu_activo());
					//session.setAttribute("sucursal", "");
					session.setAttribute("codigoLogin", usuarioDTO.getUsu_codigo());

					//Se consultan los sistemas de los cuales el usuario es el Administrador
					Long usuCodigo = Long.parseLong(usuarioDTO.getUsu_codigo().toString());
					List<SegSistema> sistemas = businessDelegatorView.consultarSistemasDeUsuarioAdministrador(usuCodigo);

					//Se consultan las companias a los que tiene permiso el usuario administrador
					//					List<SegCompania> companias = businessDelegatorView.consultarCompaniasDeDeusuarioAdministrador(usuCodigo, sisCodigo);

					listSistemas = new ArrayList<SelectItem>();
					listCompanias = new ArrayList<SelectItem>();

					if (sistemas!=null && sistemas.size()>0) {

						//El usurio existe y es administrador de algún sistema. Se otorga el acceso
						Authentication request = new UsernamePasswordAuthenticationToken(login, password);
						FacesUtils.getHttpSession(true).setAttribute("dominio", this.getDominio());
						Authentication result = authenticationManager.authenticate(request);
						SecurityContext securityContext = SecurityContextHolder.getContext();
						securityContext.setAuthentication(result);

						FacesUtils.getHttpSession(true).setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

						for (int i = 0; i < sistemas.size(); i++) {
							SegSistema sis = sistemas.get(i);
							Long codigoSistema = sis.getSisCodigo();
							String nombreSistema = sis.getSisNombre();

							SelectItem si = new SelectItem(codigoSistema, nombreSistema);
							listSistemas.add(si);
						}
					}else {
						throw new Exception("El usuario "+ usuarioDTO.getUsu_login() +" no es administrador de ningun sistema");
					}

					if (listSistemas!=null && listSistemas.size()>0){
						return action_seleccionarSistema();
					}
				}
			}


		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			return "error";
		}

		return "exito";
	}

	private  String action_seleccionarSistema(){
		try {
			if (listSistemas != null && listSistemas.size() > 0){

				FacesUtils.setManagedBeanInSession("sistemas", listSistemas);

				if (listSistemas.size()==0) {

					//Si no administra nisngún sistema
					throw new Exception("El usuario no administra ningún sistema");

				}else if (listSistemas.size()==1) {

					//Si solo hay un sistema, se escoge
					selectedSistema = (Long)listSistemas.get(0).getValue();

					FacesUtils.setManagedBeanInSession("sistema", selectedSistema);

					//Se consultan las companias, en la que el usuario administra el sistema
					List<SegCompania> companias = businessDelegatorView.consultarCompaniasDeDeusuarioAdministrador(Long.parseLong(this.usuarioDTO.getUsu_codigo()), selectedSistema);

					//Si no administra ninguna compania o solo una, esa es la comania por defecto
					if (companias == null || companias.size()==0 ){
						throw new Exception("El usuario no se encuentra asignado a ninguna compañía. Verifique Sistema_Compañía y Permisos a ese Sistema_Compañía (Permisos para el usuario 0, para el sistema_compañía)");
					}else if (companias != null && companias.size()==1 ){
						FacesUtils.setManagedBeanInSession("compania", companias.get(0).getCiaCodigo().toString());
						return "/dashboard.xhtml";
					} else if (companias != null && companias.size()>1 ){

						listCompanias = new ArrayList<SelectItem>();
						for (SegCompania segCompania : companias) {
							listCompanias.add(new SelectItem(segCompania.getCiaCodigo(), segCompania.getCiaNombre()));
						}
						FacesUtils.setManagedBeanInSession("companias", listCompanias);

						return "/XHTML/selectSisYCias.xhtml";
					}

				}else if (listSistemas.size()>1) {
					FacesUtils.setManagedBeanInSession("sistema", null);
					FacesUtils.setManagedBeanInSession("compania", null);
					FacesUtils.setManagedBeanInSession("companias", null);

					return "/XHTML/selectSisYCias.xhtml"; 
				}
			}
			return "";

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			return "";
		}
	}

	public void listener_sistema(AjaxBehaviorEvent evt){
		try {

			String codigoLogin = (String)FacesUtils.getManagedBeanFromSession("codigoLogin");
			Long usuSession = Long.parseLong(codigoLogin);

			listCompanias = null;
			selectedCompania = -1L;

			List<SegCompania> companias = businessDelegatorView.consultarCompaniasDeDeusuarioAdministrador(usuSession, selectedSistema);

			if (companias!=null && companias.size()>0) {
				listCompanias=new ArrayList<SelectItem>();
				for (SegCompania segCompania : companias) {
					listCompanias.add(new SelectItem(segCompania.getCiaCodigo(), segCompania.getCiaNombre().toString()));
				}
			}else {
				throw new Exception("El sistema no tiene compañías parametrizadas");
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public String irMenu(){

		try {

			if (selectedSistema==null || selectedSistema == -1L) {
				throw new Exception("Debe seleccionar un sistema");
			}

			if (selectedCompania==null || selectedCompania == -1L) {
				throw new Exception("Debe seleccionar una compañia");
			}
			
			FacesUtils.setManagedBeanInSession("sistema", selectedSistema);
			FacesUtils.setManagedBeanInSession("compania", selectedCompania);
			

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			return "";
		}

		return "/dashboard.xhtml";
	}



	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public List<SelectItem> getListSistemas() {
		return listSistemas;
	}

	public void setListSistemas(List<SelectItem> listSistemas) {
		this.listSistemas = listSistemas;
	}

	public List<SelectItem> getListCompanias() {
		return listCompanias;
	}

	public void setListCompanias(List<SelectItem> listCompanias) {
		this.listCompanias = listCompanias;
	}

	public List<SelectItem> getListSucursales() {
		return listSucursales;
	}

	public void setListSucursales(List<SelectItem> listSucursales) {
		this.listSucursales = listSucursales;
	}

	public Long getSelectedSistema() {
		return selectedSistema;
	}

	public void setSelectedSistema(Long selectedSistema) {
		this.selectedSistema = selectedSistema;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Long getSelectedCompania() {
		return selectedCompania;
	}

	public void setSelectedCompania(Long selectedCompania) {
		this.selectedCompania = selectedCompania;
	}

}
