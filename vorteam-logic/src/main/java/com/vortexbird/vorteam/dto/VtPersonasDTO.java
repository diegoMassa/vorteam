package com.vortexbird.vorteam.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
public class VtPersonasDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(VtPersonasDTO.class);
	private String activo;
	private String apellidos;
	private String email;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String nombre;
	private Long persId;
	private Long salario;
	private String usuaCreador;
	private String usuaModificador;
	private Long valorHora;

	private String nombreCompleto, proyectos, password, login;
	private boolean esAdministrador;

	/**
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	public VtPersonasDTO() {
		super();
	}

	/**
	 * 
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 * @since 1.8
	 * @param persId
	 * @param nombreCompleto
	 * @param email
	 * @param proyectos
	 * @return <b>{@code }</b> Start here...
	 *consultaPersonasProyectos
	 */
	public VtPersonasDTO(Long persId, String nombreCompleto, String email, String proyectos) {
		super();
		this.persId = persId;
		this.nombreCompleto = nombreCompleto;
		this.email = email;
		this.proyectos = proyectos;
	}

	/**
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 * @since 1.8
	 * @param email
	 * @param persId
	 * @param nombreCompleto
	 * @return <b>{@code }</b> Start here...
	 *consultaPersonas
	 */
	public VtPersonasDTO(Long persId, String nombreCompleto, String email) {
		super();
		this.nombreCompleto = nombreCompleto;
		this.persId = persId;
		this.email = email;
	}
	
	/**
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 * @since 1.8
	 * @param persId
	 * @param nombreCompleto
	 * @param email
	 * @param valorHora
	 * @return <b>{@code }</b> Start here...
	 *consultaPersonasProyecto
	 */
	public VtPersonasDTO(Long persId, String nombreCompleto, String email, Long valorHora) {
		super();
		this.nombreCompleto = nombreCompleto;
		this.persId = persId;
		this.email = email;
		this.valorHora = valorHora;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getPersId() {
		return persId;
	}

	public void setPersId(Long persId) {
		this.persId = persId;
	}

	public Long getSalario() {
		return salario;
	}

	public void setSalario(Long salario) {
		this.salario = salario;
	}

	public String getUsuaCreador() {
		return usuaCreador;
	}

	public void setUsuaCreador(String usuaCreador) {
		this.usuaCreador = usuaCreador;
	}

	public String getUsuaModificador() {
		return usuaModificador;
	}

	public void setUsuaModificador(String usuaModificador) {
		this.usuaModificador = usuaModificador;
	}

	public Long getValorHora() {
		return valorHora;
	}

	public void setValorHora(Long valorHora) {
		this.valorHora = valorHora;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 * @return La entidad nombreCompleto
	 *
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 *
	 * @param nombreCompleto
	 *            El/La nombreCompleto a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 *
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 * @return La entidad proyectos
	 *
	 */
	public String getProyectos() {
		return proyectos;
	}

	/**
	 *
	 * @param proyectos
	 *            El/La proyectos a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 *
	 */
	public void setProyectos(String proyectos) {
		this.proyectos = proyectos;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad password
	 *
	 */
	public String getPassword() {
		return password;
	}

	/**
	 *
	 * @param password El/La password a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad login
	 *
	 */
	public String getLogin() {
		return login;
	}

	/**
	 *
	 * @param usuario El/La usuario a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad esAdministrador
	 *
	 */
	public boolean isEsAdministrador() {
		return esAdministrador;
	}

	/**
	 *
	 * @param esAdministrador El/La esAdministrador a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setEsAdministrador(boolean esAdministrador) {
		this.esAdministrador = esAdministrador;
	}
}
