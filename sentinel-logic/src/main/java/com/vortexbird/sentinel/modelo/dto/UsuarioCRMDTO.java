package com.vortexbird.sentinel.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import org.nfunk.jep.function.Str;

public class UsuarioCRMDTO implements Serializable{
	
	private static final long serialVersionUID = 3700496186215743116L;
	
	private String nombres;
	private String apellidos;
	private String login;
	private String Password;
	private String cedula;
	private String correo;
	private String sellout;
	private String rol;
	private String sucursales;
	private String celular;
	private String codigoConcesionario;
	
	private String status;
    private String status_message;
    private String data;
	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getSellout() {
		return sellout;
	}
	public void setSellout(String sellout) {
		this.sellout = sellout;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getSucursales() {
		return sucursales;
	}
	public void setSucursales(String sucursales) {
		this.sucursales = sucursales;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus_message() {
		return status_message;
	}
	public void setStatus_message(String status_message) {
		this.status_message = status_message;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCodigoConcesionario() {
		return codigoConcesionario;
	}
	public void setCodigoConcesionario(String codigoConcesionario) {
		this.codigoConcesionario = codigoConcesionario;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "UsuarioCRMDTO ["
				+ (nombres != null ? "nombres=" + nombres + ", " : "")
				+ (apellidos != null ? "apellidos=" + apellidos + ", " : "")
				+ (login != null ? "login=" + login + ", " : "")
				+ (Password != null ? "Password=" + Password + ", " : "")
				+ (cedula != null ? "cedula=" + cedula + ", " : "")
				+ (correo != null ? "correo=" + correo + ", " : "")
				+ (sellout != null ? "sellout=" + sellout + ", " : "")
				+ (rol != null ? "rol=" + rol + ", " : "")
				+ (sucursales != null ? "sucursales=" + sucursales + ", " : "")
				+ (celular != null ? "celular=" + celular + ", " : "")
				+ (codigoConcesionario != null ? "codigoConcesionario="
						+ codigoConcesionario + ", " : "")
				+ (status != null ? "status=" + status + ", " : "")
				+ (status_message != null ? "status_message=" + status_message
						+ ", " : "") + (data != null ? "data=" + data : "")
				+ "]";
	}
	
	
	
	
	
	
			
}
