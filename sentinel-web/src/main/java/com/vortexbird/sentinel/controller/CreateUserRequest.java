package com.vortexbird.sentinel.controller;

import java.util.List;

public class CreateUserRequest {

	private String usuApellidos; 
	private String usuCodigoInterno;
	private String usuConstrasena; 
	private String usuCorreo; 
	private String usuLogin; 
	private String usuNombres;
	private List<String> rolesAsignados; 
	private String compania; 
	private String sistema;
	public String getUsuApellidos() {
		return usuApellidos;
	}
	public void setUsuApellidos(String usuApellidos) {
		this.usuApellidos = usuApellidos;
	}
	public String getUsuCodigoInterno() {
		return usuCodigoInterno;
	}
	public void setUsuCodigoInterno(String usuCodigoInterno) {
		this.usuCodigoInterno = usuCodigoInterno;
	}
	public String getUsuConstrasena() {
		return usuConstrasena;
	}
	public void setUsuConstrasena(String usuConstrasena) {
		this.usuConstrasena = usuConstrasena;
	}
	public String getUsuCorreo() {
		return usuCorreo;
	}
	public void setUsuCorreo(String usuCorreo) {
		this.usuCorreo = usuCorreo;
	}
	public String getUsuLogin() {
		return usuLogin;
	}
	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}
	public String getUsuNombres() {
		return usuNombres;
	}
	public void setUsuNombres(String usuNombres) {
		this.usuNombres = usuNombres;
	}
	public List<String> getRolesAsignados() {
		return rolesAsignados;
	}
	public void setRolesAsignados(List<String> rolesAsignados) {
		this.rolesAsignados = rolesAsignados;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public String getSistema() {
		return sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	
	
}
