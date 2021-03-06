package com.vortexbird.sentinel.modelo.dto;

import java.io.Serializable;
import java.util.Date;

public class UsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = 3700496186215743116L;
	private String usu_codigo;
    private String usu_nombres;
    private String usu_apellidos;
    private String usu_login;
    private String usu_password;
    private String usu_dominio;
    private String usu_activo;
    private String usu_correo;
    private String usu_codigo_interno;
    private String estado;
    private String usu_ultmima_modificacion_pass;
    private String usu_intentos_fallidos;
    private String dias_caducidad;
    private String nombre_rol;
    private String contrasenaMD5;
    private String mensajeExcepcion;
    private String emailFueCertificado;
    private String usuValidarPrimeraVez;
    private String usu_celular;
    private String tieneWhatapp;
    private Date fechaNacimiento;        
    private Date fechaIngreso;
    
	public String getDias_caducidad() {
		return dias_caducidad;
	}
	

	@Override
	public String toString() {
		return "UsuarioDTO [usu_codigo=" + usu_codigo + ", usu_nombres="
				+ usu_nombres + ", usu_apellidos=" + usu_apellidos
				+ ", usu_login=" + usu_login + ", usu_activo=" + usu_activo
				+ ", usu_correo=" + usu_correo + ", usu_codigo_interno="
				+ usu_codigo_interno + ", estado=" + estado
				+ ", usu_ultmima_modificacion_pass="
				+ usu_ultmima_modificacion_pass + ", usu_intentos_fallidos="
				+ usu_intentos_fallidos + ", dias_caducidad=" + dias_caducidad
				+ ", nombre_rol=" + nombre_rol + ", contrasenaMD5="
				+ contrasenaMD5 + "]";
	}

	public String getNombre_rol() {
		return nombre_rol;
	}
	public void setNombre_rol(String nombre_rol) {
		this.nombre_rol = nombre_rol;
	}
	public void setDias_caducidad(String diasCaducidad) {
		dias_caducidad = diasCaducidad;
	}
	public String getUsu_codigo() {
		return usu_codigo;
	}
	public void setUsu_codigo(String usu_codigo) {
		this.usu_codigo = usu_codigo;
	}
	public String getUsu_nombres() {
		return usu_nombres;
	}
	public void setUsu_nombres(String usu_nombres) {
		this.usu_nombres = usu_nombres;
	}
	public String getUsu_apellidos() {
		return usu_apellidos;
	}
	public void setUsu_apellidos(String usu_apellidos) {
		this.usu_apellidos = usu_apellidos;
	}
	public String getUsu_login() {
		return usu_login;
	}
	public void setUsu_login(String usu_login) {
		this.usu_login = usu_login;
	}
	public String getUsu_activo() {
		return usu_activo;
	}
	public void setUsu_activo(String usu_activo) {
		this.usu_activo = usu_activo;
	}
	public String getUsu_codigo_interno() {
		return usu_codigo_interno;
	}
	public void setUsu_codigo_interno(String usu_codigo_interno) {
		this.usu_codigo_interno = usu_codigo_interno;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUsu_correo() {
		return usu_correo;
	}
	public void setUsu_correo(String usuCorreo) {
		usu_correo = usuCorreo;
	}
	public String getUsu_ultmima_modificacion_pass() {
		return usu_ultmima_modificacion_pass;
	}
	public void setUsu_ultmima_modificacion_pass(String usuUltmimaModificacionPass) {
		usu_ultmima_modificacion_pass = usuUltmimaModificacionPass;
	}
	public String getUsu_intentos_fallidos() {
		return usu_intentos_fallidos;
	}
	public void setUsu_intentos_fallidos(String usuIntentosFallidos) {
		usu_intentos_fallidos = usuIntentosFallidos;
	}

	public String getContrasenaMD5() {
		return contrasenaMD5;
	}

	public void setContrasenaMD5(String contrasenaMD5) {
		this.contrasenaMD5 = contrasenaMD5;
	}


	public String getMensajeExcepcion() {
		return mensajeExcepcion;
	}


	public void setMensajeExcepcion(String mensajeExcepcion) {
		this.mensajeExcepcion = mensajeExcepcion;
	}


	public String getEmailFueCertificado() {
		return emailFueCertificado;
	}


	public void setEmailFueCertificado(String emailFueCertificado) {
		this.emailFueCertificado = emailFueCertificado;
	}


	public String getUsuValidarPrimeraVez() {
		return usuValidarPrimeraVez;
	}


	public void setUsuValidarPrimeraVez(String usuValidarPrimeraVez) {
		this.usuValidarPrimeraVez = usuValidarPrimeraVez;
	}


	public String getUsu_celular() {
		return usu_celular;
	}


	public void setUsu_celular(String usu_celular) {
		this.usu_celular = usu_celular;
	}


	public String getTieneWhatapp() {
		return tieneWhatapp;
	}


	public void setTieneWhatapp(String tieneWhatapp) {
		this.tieneWhatapp = tieneWhatapp;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public Date getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	public String getUsu_password() {
		return usu_password;
	}


	public void setUsu_password(String usu_password) {
		this.usu_password = usu_password;
	}


	public String getUsu_dominio() {
		return usu_dominio;
	}


	public void setUsu_dominio(String usu_dominio) {
		this.usu_dominio = usu_dominio;
	}
	
	
	
		
}
