package com.vortexbird.sentinel.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.vortexbird.sentinel.modelo.dto.UsuarioDTO;

public class AuthenticationResponse implements Serializable{

	private static final long serialVersionUID = 4159000598763506588L;

	private UsuarioDTO usuarioDTO;
	private List<SistemaDTO> sistemas;
	private String token;
	private Date fechaInicioSesion;
	
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
	public List<SistemaDTO> getSistemas() {
		return sistemas;
	}
	public void setSistemas(List<SistemaDTO> sistemas) {
		this.sistemas = sistemas;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getFechaInicioSesion() {
		return fechaInicioSesion;
	}
	public void setFechaInicioSesion(Date fechaInicioSesion) {
		this.fechaInicioSesion = fechaInicioSesion;
	}
}
