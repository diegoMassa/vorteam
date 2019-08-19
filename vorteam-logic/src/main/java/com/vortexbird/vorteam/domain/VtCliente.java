package com.vortexbird.vorteam.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import javax.validation.constraints.*;

import com.vortexbird.vorteam.audit.EntityAuditEventListener;

@Entity
@Table(name = "vt_cliente", schema = "public")
@EntityListeners(EntityAuditEventListener.class)
public class VtCliente extends AbstractAuditingEntity implements java.io.Serializable {
    private Long clieId;
    @NotNull
    private VtTipoIdentificacion vtTipoIdentificacion;
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
    private String identificacion;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String nombreRazonSocial;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String usuaCreador;
    private String usuaModificador;
    private Set<VtProyecto> vtProyectos = new HashSet<VtProyecto>(0);

    public VtCliente() {
    }

    public VtCliente(Long clieId, String activo, Date fechaCreacion,
        Date fechaModificacion, String identificacion,
        String nombreRazonSocial, String usuaCreador, String usuaModificador,
        Set<VtProyecto> vtProyectos, VtTipoIdentificacion vtTipoIdentificacion) {
        this.clieId = clieId;
        this.vtTipoIdentificacion = vtTipoIdentificacion;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.identificacion = identificacion;
        this.nombreRazonSocial = nombreRazonSocial;
        this.usuaCreador = usuaCreador;
        this.usuaModificador = usuaModificador;
        this.vtProyectos = vtProyectos;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "clie_id", unique = true)
    public Long getClieId() {
        return this.clieId;
    }

    public void setClieId(Long clieId) {
        this.clieId = clieId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tiid_id")
    public VtTipoIdentificacion getVtTipoIdentificacion() {
        return this.vtTipoIdentificacion;
    }

    public void setVtTipoIdentificacion(
        VtTipoIdentificacion vtTipoIdentificacion) {
        this.vtTipoIdentificacion = vtTipoIdentificacion;
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

    @Column(name = "identificacion", nullable = false)
    public String getIdentificacion() {
        return this.identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    @Column(name = "nombre_razon_social", nullable = false)
    public String getNombreRazonSocial() {
        return this.nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vtCliente")
    public Set<VtProyecto> getVtProyectos() {
        return this.vtProyectos;
    }

    public void setVtProyectos(Set<VtProyecto> vtProyectos) {
        this.vtProyectos = vtProyectos;
    }
}
