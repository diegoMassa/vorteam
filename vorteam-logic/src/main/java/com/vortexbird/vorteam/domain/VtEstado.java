package com.vortexbird.vorteam.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import javax.validation.constraints.*;

import com.vortexbird.vorteam.audit.EntityAuditEventListener;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Entity
@Table(name = "vt_estado", schema = "public")
@EntityListeners(EntityAuditEventListener.class)
public class VtEstado extends AbstractAuditingEntity implements java.io.Serializable {
    private Long estaId;
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
    private Set<VtActividad> vtActividads = new HashSet<VtActividad>(0);
    private Set<VtReporteTiempo> vtReporteTiempos = new HashSet<VtReporteTiempo>(0);

    public VtEstado() {
    }

    public VtEstado(Long estaId, String activo, String descripcion,
        Date fechaCreacion, Date fechaModificacion, String usuaCreador,
        String usuaModificador, Set<VtActividad> vtActividads,
        Set<VtReporteTiempo> vtReporteTiempos) {
        this.estaId = estaId;
        this.activo = activo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuaCreador = usuaCreador;
        this.usuaModificador = usuaModificador;
        this.vtActividads = vtActividads;
        this.vtReporteTiempos = vtReporteTiempos;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "esta_id", unique = true)
    public Long getEstaId() {
        return this.estaId;
    }

    public void setEstaId(Long estaId) {
        this.estaId = estaId;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vtEstado")
    public Set<VtActividad> getVtActividads() {
        return this.vtActividads;
    }

    public void setVtActividads(Set<VtActividad> vtActividads) {
        this.vtActividads = vtActividads;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vtEstado")
    public Set<VtReporteTiempo> getVtReporteTiempos() {
        return this.vtReporteTiempos;
    }

    public void setVtReporteTiempos(Set<VtReporteTiempo> vtReporteTiempos) {
        this.vtReporteTiempos = vtReporteTiempos;
    }
}
