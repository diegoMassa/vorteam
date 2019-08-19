package com.vortexbird.vorteam.domain;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;

import com.vortexbird.vorteam.audit.EntityAuditEventListener;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Entity
@Table(name = "vt_proy_costo", schema = "public")
@EntityListeners(EntityAuditEventListener.class)
public class VtProyCosto extends AbstractAuditingEntity  implements java.io.Serializable {
    private Long prcoId;
    @NotNull
    private VtPersonas vtPersonas;
    @NotNull
    private VtProyecto vtProyecto;
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
    @NotNull
    private Long valorHora;

    public VtProyCosto() {
    }

    public VtProyCosto(Long prcoId, String activo, Date fechaCreacion,
        Date fechaModificacion, String usuaCreador, String usuaModificador,
        Long valorHora, VtPersonas vtPersonas, VtProyecto vtProyecto) {
        this.prcoId = prcoId;
        this.vtPersonas = vtPersonas;
        this.vtProyecto = vtProyecto;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuaCreador = usuaCreador;
        this.usuaModificador = usuaModificador;
        this.valorHora = valorHora;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "prco_id", unique = true)
    public Long getPrcoId() {
        return this.prcoId;
    }

    public void setPrcoId(Long prcoId) {
        this.prcoId = prcoId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pers_id")
    public VtPersonas getVtPersonas() {
        return this.vtPersonas;
    }

    public void setVtPersonas(VtPersonas vtPersonas) {
        this.vtPersonas = vtPersonas;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proy_id")
    public VtProyecto getVtProyecto() {
        return this.vtProyecto;
    }

    public void setVtProyecto(VtProyecto vtProyecto) {
        this.vtProyecto = vtProyecto;
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

    @Column(name = "valor_hora", nullable = false)
    public Long getValorHora() {
        return this.valorHora;
    }

    public void setValorHora(Long valorHora) {
        this.valorHora = valorHora;
    }
}
