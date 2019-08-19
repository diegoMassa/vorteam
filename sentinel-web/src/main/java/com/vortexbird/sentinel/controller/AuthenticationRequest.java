package com.vortexbird.sentinel.controller;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable{

	private static final long serialVersionUID = 6401839620477697703L;
	private String login;
	private String password;
	private String dominio;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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
	
	
	
}
