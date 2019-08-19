package com.vortexbird.vorteam.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
public class VtActividadDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(VtActividadDTO.class);
	private Long actiId;
	private String activo;
	private String casoSoporte;
	private String descripcion;
	private String facturable;
	private Date fechaCreacion;
	private Date fechaLimite;
	private Date fechaModificacion;
	private BigDecimal horasPresupuestadas;
	private String nombre;
	private String sprint;
	private String usuaCreador;
	private String usuaModificador;
	private Long valorFacturable;
	private Long estaId_VtEstado;
	private Long proyId_VtProyecto;
	private Long tiacId_VtTipoActividad;
	private String controlCambios;
	private BigDecimal porcentajeAvance;

	private String nombreProyecto, asignaciones, estado, tipoActividad, nombreCliente;
	private Long asigId;
	private BigDecimal horasEjecutadas;
	private Date ultimaFechaRegistro;

	/**
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	public VtActividadDTO() {
		super();
	}

	/**
	 * 
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 * @param actiId
	 * @param nombreProyecto
	 * @param nombre
	 * @param descripcion
	 * @param asignaciones
	 * @param fechaLimite
	 * @param horasPresupuestadas
	 * @param sprint
	 * @param casoSoporte
	 * @param facturable
	 * @param valorFacturable
	 * @param usuaCreador
	 * @param usuaModificador
	 * @param fechaModificacion
	 * @param fechaCreacion
	 * @return <b>{@code }</b> Start here...
	 *         consultaActividadesOrdenadaFechaDescendiente
	 */
	public VtActividadDTO(Long actiId, String nombreProyecto, String nombre, String descripcion, String asignaciones,
			Date fechaLimite, BigDecimal horasPresupuestadas, String sprint, String casoSoporte, String facturable,
			Long valorFacturable, String usuaCreador, String usuaModificador, Date fechaModificacion,
			Date fechaCreacion, String controlCambios) {
		super();
		this.actiId = actiId;
		this.nombreProyecto = nombreProyecto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.asignaciones = asignaciones;
		this.fechaLimite = fechaLimite;
		this.horasPresupuestadas = horasPresupuestadas;
		this.sprint = sprint;
		this.casoSoporte = casoSoporte;
		this.facturable = facturable;
		this.valorFacturable = valorFacturable;
		this.usuaCreador = usuaCreador;
		this.usuaModificador = usuaModificador;
		this.fechaModificacion = fechaModificacion;
		this.fechaCreacion = fechaCreacion;
		this.controlCambios = controlCambios;
	}

	/**
	 * 
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 * @param actiId
	 * @param nombreProyecto
	 * @param nombreCliente
	 * @param nombre
	 * @param descripcion
	 * @param estado
	 * @param tipoActividad
	 * @param fechaLimite
	 * @param horasPresupuestadas
	 * @param horasEjecutadas
	 * @param sprint
	 * @param casoSoporte
	 * @param asigId
	 * @return <b>{@code }</b> Start here...
	 * consultaMisActividades
	 * consultaMisActividadesLazy
	 */
	public VtActividadDTO(Long actiId, String nombreProyecto, String nombreCliente, String nombre, String descripcion,
			String estado, String tipoActividad, Date fechaLimite, BigDecimal horasPresupuestadas, BigDecimal horasEjecutadas, Date ultimaFechaRegistro, String sprint,
			String casoSoporte, String controlCambios, Long asigId, BigDecimal porcentajeAvance) {
		super();
		this.actiId = actiId;
		this.nombreProyecto = nombreProyecto;
		this.nombreCliente = nombreCliente;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.tipoActividad = tipoActividad;
		this.fechaLimite = fechaLimite;
		this.horasPresupuestadas = horasPresupuestadas;
		this.horasEjecutadas = horasEjecutadas;
		this.ultimaFechaRegistro = ultimaFechaRegistro;
		this.sprint = sprint;
		this.casoSoporte = casoSoporte;
		this.controlCambios = controlCambios;
		this.asigId = asigId;
		this.porcentajeAvance = porcentajeAvance;
	}

	public Long getActiId() {
		return actiId;
	}

	public void setActiId(Long actiId) {
		this.actiId = actiId;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getCasoSoporte() {
		return casoSoporte;
	}

	public void setCasoSoporte(String casoSoporte) {
		this.casoSoporte = casoSoporte;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFacturable() {
		return facturable;
	}

	public void setFacturable(String facturable) {
		this.facturable = facturable;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public BigDecimal getHorasPresupuestadas() {
		return horasPresupuestadas;
	}

	public void setHorasPresupuestadas(BigDecimal horasPresupuestadas) {
		this.horasPresupuestadas = horasPresupuestadas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSprint() {
		return sprint;
	}

	public void setSprint(String sprint) {
		this.sprint = sprint;
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

	public Long getValorFacturable() {
		return valorFacturable;
	}

	public void setValorFacturable(Long valorFacturable) {
		this.valorFacturable = valorFacturable;
	}

	public Long getEstaId_VtEstado() {
		return estaId_VtEstado;
	}

	public void setEstaId_VtEstado(Long estaId_VtEstado) {
		this.estaId_VtEstado = estaId_VtEstado;
	}

	public Long getProyId_VtProyecto() {
		return proyId_VtProyecto;
	}

	public void setProyId_VtProyecto(Long proyId_VtProyecto) {
		this.proyId_VtProyecto = proyId_VtProyecto;
	}

	public Long getTiacId_VtTipoActividad() {
		return tiacId_VtTipoActividad;
	}

	public void setTiacId_VtTipoActividad(Long tiacId_VtTipoActividad) {
		this.tiacId_VtTipoActividad = tiacId_VtTipoActividad;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 * @return La entidad nombreProyecto
	 *
	 */
	public String getNombreProyecto() {
		return nombreProyecto;
	}

	/**
	 *
	 * @param nombreProyecto
	 *            El/La nombreProyecto a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 * @return La entidad asignaciones
	 *
	 */
	public String getAsignaciones() {
		return asignaciones;
	}

	/**
	 *
	 * @param asignaciones
	 *            El/La asignaciones a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 * @since 1.8
	 *
	 */
	public void setAsignaciones(String asignaciones) {
		this.asignaciones = asignaciones;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 * @return La entidad estado
	 *
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 *
	 * @param estado
	 *            El/La estado a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 *
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 * @return La entidad tipoActividad
	 *
	 */
	public String getTipoActividad() {
		return tipoActividad;
	}

	/**
	 *
	 * @param tipoActividad
	 *            El/La tipoActividad a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 *
	 */
	public void setTipoActividad(String tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
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
	 * @version ago. 09, 2018
	 * @since 1.8
	 *
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 * @return La entidad asigId
	 *
	 */
	public Long getAsigId() {
		return asigId;
	}

	/**
	 *
	 * @param asigId El/La asigId a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 * @since 1.8
	 *
	 */
	public void setAsigId(Long asigId) {
		this.asigId = asigId;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 * @return La entidad horasEjecutadas
	 *
	 */
	public BigDecimal getHorasEjecutadas() {
		return horasEjecutadas;
	}

	/**
	 *
	 * @param horasEjecutadas El/La horasEjecutadas a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 *
	 */
	public void setHorasEjecutadas(BigDecimal horasEjecutadas) {
		this.horasEjecutadas = horasEjecutadas;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 31, 2018
	 * @since 1.8
	 * @return La entidad controlCambios
	 *
	 */
	public String getControlCambios() {
		return controlCambios;
	}

	/**
	 *
	 * @param controlCambios El/La controlCambios a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 31, 2018
	 * @since 1.8
	 *
	 */
	public void setControlCambios(String controlCambios) {
		this.controlCambios = controlCambios;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 31, 2018
	 * @since 1.8
	 * @return La entidad ultimaFechaRegistro
	 *
	 */
	public Date getUltimaFechaRegistro() {
		return ultimaFechaRegistro;
	}

	/**
	 *
	 * @param ultimaFechaRegistro El/La ultimaFechaRegistro a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 31, 2018
	 * @since 1.8
	 *
	 */
	public void setUltimaFechaRegistro(Date ultimaFechaRegistro) {
		this.ultimaFechaRegistro = ultimaFechaRegistro;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad porcentajeAvance
	 *
	 */
	public BigDecimal getPorcentajeAvance() {
		return porcentajeAvance;
	}

	/**
	 *
	 * @param porcentajeAvance El/La porcentajeAvance a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setPorcentajeAvance(BigDecimal porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
	}
}
