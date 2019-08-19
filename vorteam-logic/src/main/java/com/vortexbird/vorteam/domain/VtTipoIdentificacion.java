package com.vortexbird.vorteam.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import javax.validation.constraints.*;

import org.hibernate.annotations.Generated;

import com.vortexbird.vorteam.audit.EntityAuditEventListener;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Entity
@Table(name = "vt_tipo_identificacion", schema = "public")
@EntityListeners(EntityAuditEventListener.class)
public class VtTipoIdentificacion extends AbstractAuditingEntity  implements java.io.Serializable {
    private Long tiidId;
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
    private Set<VtCliente> vtClientes = new HashSet<VtCliente>(0);

    public VtTipoIdentificacion() {
    }

    public VtTipoIdentificacion(Long tiidId, String activo, String descripcion,
        Date fechaCreacion, Date fechaModificacion, String usuaCreador,
        String usuaModificador, Set<VtCliente> vtClientes) {
        this.tiidId = tiidId;
        this.activo = activo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuaCreador = usuaCreador;
        this.usuaModificador = usuaModificador;
        this.vtClientes = vtClientes;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "tiid_id", unique = true)
    public Long getTiidId() {
        return this.tiidId;
    }

    public void setTiidId(Long tiidId) {
        this.tiidId = tiidId;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vtTipoIdentificacion")
    public Set<VtCliente> getVtClientes() {
        return this.vtClientes;
    }

    public void setVtClientes(Set<VtCliente> vtClientes) {
        this.vtClientes = vtClientes;
    }
}
