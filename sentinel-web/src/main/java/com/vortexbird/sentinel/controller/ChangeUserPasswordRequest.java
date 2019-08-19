package com.vortexbird.sentinel.controller;


public class ChangeUserPasswordRequest {

	private String usuCodigoInterno;
	private String usuConstrasena; 
	private String usuLogin;
	
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
	public String getUsuLogin() {
		return usuLogin;
	}
	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}

	
}
