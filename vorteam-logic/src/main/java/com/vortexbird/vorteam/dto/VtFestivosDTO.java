package com.vortexbird.vorteam.dto;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
public class VtFestivosDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(VtFestivosDTO.class);
	
	private Long festId;
	private Date fecha, fechaCreacion, fechaModificacion;
	private String activo, usuaCreador, usuaModificador;
	/**
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	public VtFestivosDTO() {
		super();
	}
	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 * @param festId
	 * @param fecha
	 * @param activo
	 * @param usuaCreador
	 * @param fechaCreacion
	 * @param usuaModificador
	 * @param fechaModificacion
	 * @return <b>{@code }</b> Start here...
	 * esFestivo
	 */
	public VtFestivosDTO(Long festId, Date fecha, String activo, String usuaCreador, 
			Date fechaCreacion, String usuaModificador, Date fechaModificacion) {
		super();
		this.festId = festId;
		this.fecha = fecha;
		this.activo = activo;
		this.usuaCreador = usuaCreador;
		this.fechaCreacion = fechaCreacion;
		this.usuaModificador = usuaModificador;
		this.fechaModificacion = fechaModificacion;
	}
	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 * @return La entidad festId
	 *
	 */
	public Long getFestId() {
		return festId;
	}
	/**
	 *
	 * @param festId El/La festId a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 *
	 */
	public void setFestId(Long festId) {
		this.festId = festId;
	}
	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 * @return La entidad fecha
	 *
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 *
	 * @param fecha El/La fecha a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 *
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 * @return La entidad fechaCreacion
	 *
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 *
	 * @param fechaCreacion El/La fechaCreacion a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 *
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 * @return La entidad fechaModificacion
	 *
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	/**
	 *
	 * @param fechaModificacion El/La fechaModificacion a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 *
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 * @return La entidad activo
	 *
	 */
	public String getActivo() {
		return activo;
	}
	/**
	 *
	 * @param activo El/La activo a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 *
	 */
	public void setActivo(String activo) {
		this.activo = activo;
	}
	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 * @return La entidad usuaCreador
	 *
	 */
	public String getUsuaCreador() {
		return usuaCreador;
	}
	/**
	 *
	 * @param usuaCreador El/La usuaCreador a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 *
	 */
	public void setUsuaCreador(String usuaCreador) {
		this.usuaCreador = usuaCreador;
	}
	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 * @return La entidad usuaModificador
	 *
	 */
	public String getUsuaModificador() {
		return usuaModificador;
	}
	/**
	 *
	 * @param usuaModificador El/La usuaModificador a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 *
	 */
	public void setUsuaModificador(String usuaModificador) {
		this.usuaModificador = usuaModificador;
	}
}
