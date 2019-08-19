package com.vortexbird.vorteam.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vortexbird.vorteam.audit.EntityAuditEventListener;

@NamedNativeQueries({
    @NamedNativeQuery(name="esFestivo", query = "", resultSetMapping = "esFestivo")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name="esFestivo", 
        classes = { @ConstructorResult(targetClass = com.vortexbird.vorteam.dto.VtFestivosDTO.class,
            columns = { @ColumnResult(name = "festId", type = Long.class),
                        @ColumnResult(name = "fecha", type = Date.class),
                        @ColumnResult(name = "activo", type = String.class),
                        @ColumnResult(name = "usuaCreador", type = String.class),
                        @ColumnResult(name = "fechaCreacion", type = Date.class),
                        @ColumnResult(name = "usuaModificador", type = String.class),
                        @ColumnResult(name = "fechaModificacion", type = Date.class)
    })})
})
@Entity
@Table(name = "vt_festivos", schema = "public")
@EntityListeners(EntityAuditEventListener.class)
public class VtFestivos extends AbstractAuditingEntity implements java.io.Serializable {
	private Long festId;
	@NotNull
	private Date fecha;
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

	public VtFestivos() {
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 * @since 1.8
	 * @return La entidad festId
	 *
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "fest_id", unique = true)
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
	@Column(name = "fecha", nullable = false)
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
	 * @return La entidad activo
	 *
	 */
	@Column(name = "activo", nullable = false)
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
	 * @return La entidad fechaCreacion
	 *
	 */
	@Column(name = "fecha_creacion", nullable = false)
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
	@Column(name = "fecha_modificacion")
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
	 * @return La entidad usuaCreador
	 *
	 */
	@Column(name = "usua_creador", nullable = false)
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
	@Column(name = "usua_modificador")
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
