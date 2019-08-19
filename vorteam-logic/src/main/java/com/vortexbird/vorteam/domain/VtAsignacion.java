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
@Table(name = "vt_asignacion", schema = "public")
@EntityListeners(EntityAuditEventListener.class)
public class VtAsignacion extends AbstractAuditingEntity  implements java.io.Serializable {
    private Long asigId;
    @NotNull
    private VtActividad vtActividad;
    @NotNull
    private VtPersonas vtPersonas;
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
    private Set<VtReporteTiempo> vtReporteTiempos = new HashSet<VtReporteTiempo>(0);

    public VtAsignacion() {
    }

    public VtAsignacion(Long asigId, String activo, Date fechaCreacion,
        Date fechaModificacion, String usuaCreador, String usuaModificador,
        VtActividad vtActividad, VtPersonas vtPersonas,
        Set<VtReporteTiempo> vtReporteTiempos) {
        this.asigId = asigId;
        this.vtActividad = vtActividad;
        this.vtPersonas = vtPersonas;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuaCreador = usuaCreador;
        this.usuaModificador = usuaModificador;
        this.vtReporteTiempos = vtReporteTiempos;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "asig_id", unique = true)
    public Long getAsigId() {
        return this.asigId;
    }

    public void setAsigId(Long asigId) {
        this.asigId = asigId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acti_id")
    public VtActividad getVtActividad() {
        return this.vtActividad;
    }

    public void setVtActividad(VtActividad vtActividad) {
        this.vtActividad = vtActividad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pers_id")
    public VtPersonas getVtPersonas() {
        return this.vtPersonas;
    }

    public void setVtPersonas(VtPersonas vtPersonas) {
        this.vtPersonas = vtPersonas;
    }

    @Column(name = "activo", nullable = false)
    public String getActivo() {
        return this.activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vtAsignacion")
    public Set<VtReporteTiempo> getVtReporteTiempos() {
        return this.vtReporteTiempos;
    }

    public void setVtReporteTiempos(Set<VtReporteTiempo> vtReporteTiempos) {
        this.vtReporteTiempos = vtReporteTiempos;
    }
}
