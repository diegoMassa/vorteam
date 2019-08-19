package com.vortexbird.vorteam.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import javax.validation.constraints.*;

import com.vortexbird.vorteam.audit.EntityAuditEventListener;

@NamedNativeQueries({
    @NamedNativeQuery(name="consultaPersonasProyectos", query = "", resultSetMapping = "consultaPersonasProyectos"),
    @NamedNativeQuery(name="consultaPersonasProyecto", query = "", resultSetMapping = "consultaPersonasProyecto"),
    @NamedNativeQuery(name="consultaPersonas", query = "", resultSetMapping = "consultaPersonas"),
    @NamedNativeQuery(name="consultaPersonasActividad", query = "", resultSetMapping = "consultaPersonasActividad")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name="consultaPersonasProyectos", 
        classes = { @ConstructorResult(targetClass = com.vortexbird.vorteam.dto.VtPersonasDTO.class,
            columns = { 
            			@ColumnResult(name = "persId", type = Long.class),
					@ColumnResult(name = "nombreCompleto", type = String.class),
					@ColumnResult(name = "email", type = String.class),
					@ColumnResult(name = "proyectos", type = String.class)
    })}),
    @SqlResultSetMapping(name="consultaPersonasProyecto", 
    classes = { @ConstructorResult(targetClass = com.vortexbird.vorteam.dto.VtPersonasDTO.class,
    columns = { 
    		@ColumnResult(name = "persId", type = Long.class),
    		@ColumnResult(name = "nombreCompleto", type = String.class),
    		@ColumnResult(name = "email", type = String.class),
    		@ColumnResult(name = "valorHora", type = Long.class)
    })}),
    @SqlResultSetMapping(name="consultaPersonas", 
    classes = { @ConstructorResult(targetClass = com.vortexbird.vorteam.dto.VtPersonasDTO.class,
    columns = { 
    		@ColumnResult(name = "persId", type = Long.class),
    		@ColumnResult(name = "nombreCompleto", type = String.class),
    		@ColumnResult(name = "email", type = String.class)
    })}),
    @SqlResultSetMapping(name="consultaPersonasActividad", 
    classes = { @ConstructorResult(targetClass = com.vortexbird.vorteam.dto.VtPersonasDTO.class,
    columns = { 
    		@ColumnResult(name = "persId", type = Long.class),
    		@ColumnResult(name = "nombreCompleto", type = String.class),
    		@ColumnResult(name = "email", type = String.class)
    })})
})
@Entity
@Table(name = "vt_personas", schema = "public")
@EntityListeners(EntityAuditEventListener.class)
public class VtPersonas extends AbstractAuditingEntity  implements java.io.Serializable {
    private Long persId;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private String activo;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String apellidos;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String email;
    @NotNull
    private Date fechaCreacion;
    private Date fechaModificacion;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String nombre;
    @NotNull
    private Long salario;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String usuaCreador;
    private String usuaModificador;
    @NotNull
    private Long valorHora;
    private Set<VtAsignacion> vtAsignacions = new HashSet<VtAsignacion>(0);
    private Set<VtProyCosto> vtProyCostos = new HashSet<VtProyCosto>(0);

    public VtPersonas() {
    }

    public VtPersonas(Long persId, String activo, String apellidos,
        String email, Date fechaCreacion, Date fechaModificacion,
        String nombre, Long salario, String usuaCreador,
        String usuaModificador, Long valorHora,
        Set<VtAsignacion> vtAsignacions, Set<VtProyCosto> vtProyCostos) {
        this.persId = persId;
        this.activo = activo;
        this.apellidos = apellidos;
        this.email = email;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.nombre = nombre;
        this.salario = salario;
        this.usuaCreador = usuaCreador;
        this.usuaModificador = usuaModificador;
        this.valorHora = valorHora;
        this.vtAsignacions = vtAsignacions;
        this.vtProyCostos = vtProyCostos;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "pers_id", unique = true)
    public Long getPersId() {
        return this.persId;
    }

    public void setPersId(Long persId) {
        this.persId = persId;
    }

    @Column(name = "activo", nullable = false)
    public String getActivo() {
        return this.activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Column(name = "apellidos", nullable = false)
    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Column(name = "nombre", nullable = false)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "salario", nullable = false)
    public Long getSalario() {
        return this.salario;
    }

    public void setSalario(Long salario) {
        this.salario = salario;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vtPersonas")
    public Set<VtAsignacion> getVtAsignacions() {
        return this.vtAsignacions;
    }

    public void setVtAsignacions(Set<VtAsignacion> vtAsignacions) {
        this.vtAsignacions = vtAsignacions;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vtPersonas")
    public Set<VtProyCosto> getVtProyCostos() {
        return this.vtProyCostos;
    }

    public void setVtProyCostos(Set<VtProyCosto> vtProyCostos) {
        this.vtProyCostos = vtProyCostos;
    }
}
