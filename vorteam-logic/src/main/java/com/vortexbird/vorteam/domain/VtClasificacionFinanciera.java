package com.vortexbird.vorteam.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Entity
@Table(name = "vt_clasificacion_financiera", schema = "public")
public class VtClasificacionFinanciera implements java.io.Serializable {

	private static final long serialVersionUID = -2151518108958786328L;
	
	private Long clfiId;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private String activo;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String descripcion;
    @NotNull
    private Date fechaCreacion;
    private Date fechaModificacion;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String usuaCreador;
    private String usuaModificador;
    private Set<VtTipoActividad> vtTipoActividads = new HashSet<VtTipoActividad>(0);

    public VtClasificacionFinanciera() {
    }

    public VtClasificacionFinanciera(Long clfiId, String activo,
        String descripcion, Date fechaCreacion, Date fechaModificacion,
        String usuaCreador, String usuaModificador,
        Set<VtTipoActividad> vtTipoActividads) {
        this.clfiId = clfiId;
        this.activo = activo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuaCreador = usuaCreador;
        this.usuaModificador = usuaModificador;
        this.vtTipoActividads = vtTipoActividads;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "clfi_id", unique = true)
    public Long getClfiId() {
        return this.clfiId;
    }

    public void setClfiId(Long clfiId) {
        this.clfiId = clfiId;
    }

    @Column(name = "activo", nullable = false)
    public String getActivo() {
        return this.activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Column(name = "descripcion", nullable = false)
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "fecha_creacion", nullable = false)
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Column(name = "fecha_modificacion")
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Column(name = "usua_creador", nullable = false)
    public String getUsuaCreador() {
        return this.usuaCreador;
    }

    public void setUsuaCreador(String usuaCreador) {
        this.usuaCreador = usuaCreador;
    }

    @Column(name = "usua_modificador")
    public String getUsuaModificador() {
        return this.usuaModificador;
    }

    public void setUsuaModificador(String usuaModificador) {
        this.usuaModificador = usuaModificador;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vtClasificacionFinanciera")
    public Set<VtTipoActividad> getVtTipoActividads() {
        return this.vtTipoActividads;
    }

    public void setVtTipoActividads(Set<VtTipoActividad> vtTipoActividads) {
        this.vtTipoActividads = vtTipoActividads;
    }
}
