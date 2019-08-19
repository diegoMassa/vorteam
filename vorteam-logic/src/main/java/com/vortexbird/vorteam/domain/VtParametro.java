package com.vortexbird.vorteam.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vortexbird.vorteam.audit.EntityAuditEventListener;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Entity
@Table(name = "vt_parametro", schema = "public")
@EntityListeners(EntityAuditEventListener.class)
public class VtParametro extends AbstractAuditingEntity implements java.io.Serializable {
	private Long paraId;
	@NotEmpty
	@Size(max = 255)
	private String nombreCorto;
	@NotEmpty
	@Size(max = 255)
	private String descripcion;
	@NotEmpty
	@Size(max = 255)
	private String valor;
	@NotNull
	@NotEmpty
	@Size(max = 1)
	private String activo;
	@NotNull
	private Date fechaCreacion;
	private Date fechaModificacion;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String usuaCreador;
	private String usuaModificador;

	public VtParametro() {
	}

	/**
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 * @param paraId
	 * @param nombreCorto
	 * @param descripcion
	 * @param valor
	 * @param activo
	 * @param fechaCreacion
	 * @param usuaCreador
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	public VtParametro(Long paraId, String nombreCorto, String descripcion, String valor, String activo,
			Date fechaCreacion, String usuaCreador) {
		super();
		this.paraId = paraId;
		this.nombreCorto = nombreCorto;
		this.descripcion = descripcion;
		this.valor = valor;
		this.activo = activo;
		this.fechaCreacion = fechaCreacion;
		this.usuaCreador = usuaCreador;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 * @return La entidad paraId
	 *
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "para_id", unique = true)
	public Long getParaId() {
		return paraId;
	}

	/**
	 *
	 * @param paraId
	 *            El/La paraId a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 *
	 */
	public void setParaId(Long paraId) {
		this.paraId = paraId;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 * @return La entidad nombreCorto
	 *
	 */
	@Column(name = "nombre_corto", nullable = false)
	public String getNombreCorto() {
		return nombreCorto;
	}

	/**
	 *
	 * @param nombreCorto
	 *            El/La nombreCorto a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 *
	 */
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 * @return La entidad descripcion
	 *
	 */
	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 *
	 * @param descripcion
	 *            El/La descripcion a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 *
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 * @return La entidad valor
	 *
	 */
	@Column(name = "valor", nullable = false)
	public String getValor() {
		return valor;
	}

	/**
	 *
	 * @param valor
	 *            El/La valor a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 *
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 * @return La entidad activo
	 *
	 */
	@Column(name = "activo", nullable = false)
	public String getActivo() {
		return activo;
	}

	/**
	 *
	 * @param activo
	 *            El/La activo a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 *
	 */
	public void setActivo(String activo) {
		this.activo = activo;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 * @return La entidad fechaCreacion
	 *
	 */
	@Column(name = "fecha_creacion", nullable = false)
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 *
	 * @param fechaCreacion
	 *            El/La fechaCreacion a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 *
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 * @return La entidad fechaModificacion
	 *
	 */
	@Column(name = "fecha_modificacion")
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 *
	 * @param fechaModificacion
	 *            El/La fechaModificacion a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 *
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 * @return La entidad usuaCreador
	 *
	 */
	@Column(name = "usua_creador", nullable = false)
	public String getUsuaCreador() {
		return usuaCreador;
	}

	/**
	 *
	 * @param usuaCreador
	 *            El/La usuaCreador a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 *
	 */
	public void setUsuaCreador(String usuaCreador) {
		this.usuaCreador = usuaCreador;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 * @return La entidad usuaModificador
	 *
	 */
	@Column(name = "usua_modificador")
	public String getUsuaModificador() {
		return usuaModificador;
	}

	/**
	 *
	 * @param usuaModificador
	 *            El/La usuaModificador a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 * @since 1.8
	 *
	 */
	public void setUsuaModificador(String usuaModificador) {
		this.usuaModificador = usuaModificador;
	}

}
