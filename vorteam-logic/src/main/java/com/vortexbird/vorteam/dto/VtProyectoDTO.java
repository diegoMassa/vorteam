package com.vortexbird.vorteam.dto;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
public class VtProyectoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(VtProyectoDTO.class);
	private String activo;
	private Long costoTotal;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String nombreProyecto;
	private Long proyId;
	private String usuaCreador;
	private String usuaModificador;
	private Long clieId_VtCliente;
	private Long lineId_VtLineaNegocio;

	private String tipoIdentificacionCliente, identificacionCliente, nombreCliente, lineaNegocio;
	private Long clieId;

	private String recursosHumanos;
	
	private Long valorHoraRecurso;
	
	private String activoDescripcion;
	/**
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	public VtProyectoDTO() {
		super();
	}

	/**
	 * 
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 * @param tipoIdentificacionCliente
	 * @param identificacionCliente
	 * @param nombreCliente
	 * @param nombreProyecto
	 * @param clieId
	 * @param proyId
	 * @param lineaNegocio
	 * @param activo
	 * @param activoDescripcion
	 * @return <b>{@code }</b> Start here...
	 * Constructor consulta SQL consultaProyectosClientes
	 */
	public VtProyectoDTO(String tipoIdentificacionCliente, String identificacionCliente, String nombreCliente,
			String nombreProyecto, Long clieId, Long proyId, String lineaNegocio, String activo, String activoDescripcion) {
		super();
		this.nombreProyecto = nombreProyecto;
		this.tipoIdentificacionCliente = tipoIdentificacionCliente;
		this.identificacionCliente = identificacionCliente;
		this.nombreCliente = nombreCliente;
		this.clieId = clieId;
		this.proyId = proyId;
		this.lineaNegocio = lineaNegocio;
		this.activo = activo;
		this.activoDescripcion = activoDescripcion;
	}
	
	/**
	 * 
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 * @param proyId
	 * @param nombreProyecto
	 * @param nombreCliente
	 * @param lineaNegocio
	 * @param recursosHumanos
	 * @return <b>{@code }</b> Start here...
	 * consultaProyectosClientesRecursos
	 */
	public VtProyectoDTO(Long proyId, String nombreProyecto, String nombreCliente, String lineaNegocio, String recursosHumanos, String activo, String activoDescripcion, Long costoTotal) {
		super();
		this.nombreProyecto = nombreProyecto;
		this.proyId = proyId;
		this.nombreCliente = nombreCliente;
		this.lineaNegocio = lineaNegocio;
		this.recursosHumanos = recursosHumanos;
		this.activo = activo;
		this.activoDescripcion = activoDescripcion;
		this.costoTotal = costoTotal;
	}
	
	/**
	 * 
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @param nombreCliente
	 * @param nombreProyecto
	 * @param clieId
	 * @param proyId
	 * @param lineaNegocio
	 * @return <b>{@code }</b> Start here...
	 * consultaProyectos
	 * consultaProyectosActivosPersona
	 * consultaProyectosTodosPersona
	 */
	public VtProyectoDTO(String nombreCliente, String nombreProyecto, Long clieId, Long proyId, String lineaNegocio) {
		super();
		this.nombreCliente = nombreCliente;
		this.nombreProyecto = nombreProyecto;
		this.clieId = clieId;
		this.proyId = proyId;
		this.lineaNegocio = lineaNegocio;
	}



	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public Long getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(Long costoTotal) {
		this.costoTotal = costoTotal;
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

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public Long getProyId() {
		return proyId;
	}

	public void setProyId(Long proyId) {
		this.proyId = proyId;
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

	public Long getClieId_VtCliente() {
		return clieId_VtCliente;
	}

	public void setClieId_VtCliente(Long clieId_VtCliente) {
		this.clieId_VtCliente = clieId_VtCliente;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 * @return La entidad identificacionCliente
	 *
	 */
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	/**
	 *
	 * @param identificacionCliente
	 *            El/La identificacionCliente a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 *
	 */
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 * @return La entidad nombreCliente
	 *
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 *
	 * @param nombreCliente
	 *            El/La nombreCliente a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 *
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 * @return La entidad tipoIdentificacionCliente
	 *
	 */
	public String getTipoIdentificacionCliente() {
		return tipoIdentificacionCliente;
	}

	/**
	 *
	 * @param tipoIdentificacionCliente
	 *            El/La tipoIdentificacionCliente a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 *
	 */
	public void setTipoIdentificacionCliente(String tipoIdentificacionCliente) {
		this.tipoIdentificacionCliente = tipoIdentificacionCliente;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 * @return La entidad clieId
	 *
	 */
	public Long getClieId() {
		return clieId;
	}

	/**
	 *
	 * @param clieId
	 *            El/La clieId a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 *
	 */
	public void setClieId(Long clieId) {
		this.clieId = clieId;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 * @return La entidad recursosHumanos
	 *
	 */
	public String getRecursosHumanos() {
		return recursosHumanos;
	}

	/**
	 *
	 * @param recursosHumanos El/La recursosHumanos a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 * @since 1.8
	 *
	 */
	public void setRecursosHumanos(String recursosHumanos) {
		this.recursosHumanos = recursosHumanos;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 * @since 1.8
	 * @return La entidad lineId_VtLineaNegocio
	 *
	 */
	public Long getLineId_VtLineaNegocio() {
		return lineId_VtLineaNegocio;
	}

	/**
	 *
	 * @param lineId_VtLineaNegocio El/La lineId_VtLineaNegocio a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 * @since 1.8
	 *
	 */
	public void setLineId_VtLineaNegocio(Long lineId_VtLineaNegocio) {
		this.lineId_VtLineaNegocio = lineId_VtLineaNegocio;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 * @since 1.8
	 * @return La entidad lineaNegocio
	 *
	 */
	public String getLineaNegocio() {
		return lineaNegocio;
	}

	/**
	 *
	 * @param lineaNegocio El/La lineaNegocio a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 * @since 1.8
	 *
	 */
	public void setLineaNegocio(String lineaNegocio) {
		this.lineaNegocio = lineaNegocio;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 * @return La entidad valorHoraRecurso
	 *
	 */
	public Long getValorHoraRecurso() {
		return valorHoraRecurso;
	}

	/**
	 *
	 * @param valorHoraRecurso El/La valorHoraRecurso a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 * @since 1.8
	 *
	 */
	public void setValorHoraRecurso(Long valorHoraRecurso) {
		this.valorHoraRecurso = valorHoraRecurso;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 18, 2018
	 * @since 1.8
	 * @return La entidad activoDescripcion
	 *
	 */
	public String getActivoDescripcion() {
		return activoDescripcion;
	}

	/**
	 *
	 * @param activoDescripcion El/La activoDescripcion a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 18, 2018
	 * @since 1.8
	 *
	 */
	public void setActivoDescripcion(String activoDescripcion) {
		this.activoDescripcion = activoDescripcion;
	}
}
