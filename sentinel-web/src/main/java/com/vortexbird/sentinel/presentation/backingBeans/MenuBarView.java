package com.vortexbird.sentinel.presentation.backingBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.vortexbird.sentinel.modelo.dto.GrupoDTO;
import com.vortexbird.sentinel.modelo.dto.OpcionDTO;
import com.vortexbird.sentinel.presentation.businessDelegate.IBusinessDelegatorView;
import com.vortexbird.sentinel.utilities.FacesUtils;

@ManagedBean
@ViewScoped
public class MenuBarView {

	private MenuModel model;
	private DefaultSubMenu subMenu;

	private static String SUCURSAL = "";
	private static String SISTEMA = "";
	private static String COMPANIA = "";

	private String mensajeBienvenida = "Bienvenido ";

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	@PostConstruct
	public void menuBarViewPC() {

		model = new DefaultMenuModel();

		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			String login = session.getAttribute("loginSession") != null ? session.getAttribute("loginSession").toString() : null;
			//Long usuSession = session.getAttribute("codigoLogin") != null ? Long.parseLong(session.getAttribute("codigoLogin").toString()) : null;

			SISTEMA = session.getAttribute("sistema").toString();
			COMPANIA = session.getAttribute("compania").toString();
			
			mensajeBienvenida += login;

			List<GrupoDTO> grupos = businessDelegatorView.consultarOpcionesDeUsuarioPorSistemaSucursalYCompania(login, SISTEMA, SUCURSAL, COMPANIA);

			if (grupos != null && grupos.isEmpty() == false) {

				for (GrupoDTO grupo : grupos) {

					subMenu = new DefaultSubMenu();
					subMenu.setLabel(grupo.getGru_nombre());
					subMenu.setIcon("ui-icon-help");

					List<OpcionDTO> opciones = grupo.getOpciones();

					for (OpcionDTO opcion : opciones) {

						DefaultMenuItem menuItem = new DefaultMenuItem();
						menuItem.setAjax(false);
						menuItem.setValue(opcion.getOpc_nombre());
						menuItem.setTitle(opcion.getOpc_nombre());
						menuItem.setUrl(opcion.getOpc_llave_acceso());
						subMenu.addElement(menuItem);

					}
				}
				model.addElement(subMenu);

			}						

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public String salir_action(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		if (session!=null) {
			session.invalidate();	
		}
		return "/login2.xhtml?faces-redirect=true";
	}


	public DefaultSubMenu getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(DefaultSubMenu subMenu) {
		this.subMenu = subMenu;
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public String getMensajeBienvenida() {
		return mensajeBienvenida;
	}

	public void setMensajeBienvenida(String mensajeBienvenida) {
		this.mensajeBienvenida = mensajeBienvenida;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

}
