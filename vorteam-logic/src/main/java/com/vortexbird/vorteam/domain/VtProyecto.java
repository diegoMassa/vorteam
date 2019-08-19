package com.vortexbird.vorteam.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import javax.validation.constraints.*;

import com.vortexbird.vorteam.audit.EntityAuditEventListener;


@NamedNativeQueries({
    @NamedNativeQuery(name="consultaProyectosClientes", query = "", resultSetMapping = "consultaProyectosClientes"),
    @NamedNativeQuery(name="consultaProyectosClientesRecursos", query = "", resultSetMapping = "consultaProyectosClientesRecursos"),
    @NamedNativeQuery(name="consultaProyectos", query = "", resultSetMapping = "consultaProyectos"),
    @NamedNativeQuery(name="consultaProyectosActivosPersona", query = "", resultSetMapping = "consultaProyectosActivosPersona"),
    @NamedNativeQuery(name="consultaProyectosTodosPersona", query = "", resultSetMapping = "consultaProyectosTodosPersona")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name="consultaProyectosClientes", 
        classes = { @ConstructorResult(targetClass = com.vortexbird.vorteam.dto.VtProyectoDTO.class,
            columns = { @ColumnResult(name = "tipoIdentificacionCliente", type = String.class),
                        @ColumnResult(name = "identificacionCliente", type = String.class),
                        @ColumnResult(name = "nombreCliente", type = String.class),
                        @ColumnResult(name = "nombreProyecto", type = String.class),
            			   	@ColumnResult(name = "clieId", type = Long.class),
        			   		@ColumnResult(name = "proyId", type = Long.class),
        			   		@ColumnResult(name = "lineaNegocio", type = String.class),
        			   		@ColumnResult(name = "activo", type = String.class),
        			   		@ColumnResult(name = "activoDescripcion", type = String.class)
    })}),
    @SqlResultSetMapping(name="consultaProyectosClientesRecursos", 
    classes = { @ConstructorResult(targetClass = com.vortexbird.vorteam.dto.VtProyectoDTO.class,
        columns = {
				@ColumnResult(name = "proyId", type = Long.class),
				@ColumnResult(name = "nombreProyecto", type = String.class),
				@ColumnResult(name = "nombreCliente", type = String.class),
				@ColumnResult(name = "lineaNegocio", type = String.class),
				@ColumnResult(name = "recursosHumanos", type = String.class),
				@ColumnResult(name = "activo", type = String.class),
		   		@ColumnResult(name = "activoDescripcion", type = String.class),
		   		@ColumnResult(name = "costoTotal", type = Long.class)		   		
    })}),
    @SqlResultSetMapping(name="consultaProyectos", 
    classes = { @ConstructorResult(targetClass = com.vortexbird.vorteam.dto.VtProyectoDTO.class,
        columns = {
	        		@ColumnResult(name = "nombreCliente", type = String.class),
	        		@ColumnResult(name = "nombreProyecto", type = String.class),
				@ColumnResult(name = "clieId", type = Long.class),
				@ColumnResult(name = "proyId", type = Long.class),
				@ColumnResult(name = "lineaNegocio", type = String.class)
    })}),
    @SqlResultSetMapping(name="consultaProyectosActivosPersona", 
    classes = { @ConstructorResult(targetClass = com.vortexbird.vorteam.dto.VtProyectoDTO.class,
    columns = {
    		@ColumnResult(name = "nombreCliente", type = String.class),
    		@ColumnResult(name = "nombreProyecto", type = String.class),
    		@ColumnResult(name = "clieId", type = Long.class),
    		@ColumnResult(name = "proyId", type = Long.class),
    		@ColumnResult(name = "lineaNegocio", type = String.class)
    })}),
    @SqlResultSetMapping(name="consultaProyectosTodosPersona", 
    classes = { @ConstructorResult(targetClass = com.vortexbird.vorteam.dto.VtProyectoDTO.class,
    columns = {
    		@ColumnResult(name = "nombreCliente", type = String.class),
    		@ColumnResult(name = "nombreProyecto", type = String.class),
    		@ColumnResult(name = "clieId", type = Long.class),
    		@ColumnResult(name = "proyId", type = Long.class),
    		@ColumnResult(name = "lineaNegocio", type = String.class)
    })})
})
@Entity
@Table(name = "vt_proyecto", schema = "public")
@EntityListeners(EntityAuditEventListener.class)
public class VtProyecto extends AbstractAuditingEntity  implements java.io.Serializable {
    private Long proyId;
    @NotNull
    private VtCliente vtCliente;
    @NotNull
    private VtLineaNegocio vtLineaNegocio;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private String activo;
    @NotNull
    private Long costoTotal;
    @NotNull
    private Date fechaCreacion;
    private Date fechaModificacion;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String nombreProyecto;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String usuaCreador;
    private String usuaModificador;
    private Set<VtActividad> vtActividads = new HashSet<VtActividad>(0);
    private Set<VtProyCosto> vtProyCostos = new HashSet<VtProyCosto>(0);

    public VtProyecto() {
    }

    public VtProyecto(Long proyId, String activo, Long costoTotal,
        Date fechaCreacion, Date fechaModificacion, String nombreProyecto,
        String usuaCreador, String usuaModificador,
        Set<VtActividad> vtActividads, VtCliente vtCliente, VtLineaNegocio vtLineaNegocio,
        Set<VtProyCosto> vtProyCostos) {
        this.proyId = proyId;
        this.vtCliente = vtCliente;
        this.activo = activo;
        this.costoTotal = costoTotal;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.nombreProyecto = nombreProyecto;
        this.usuaCreador = usuaCreador;
        this.usuaModificador = usuaModificador;
        this.vtActividads = vtActividads;
        this.vtProyCostos = vtProyCostos;
        this.vtLineaNegocio = vtLineaNegocio;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "proy_id", unique = true)
    public Long getProyId() {
        return this.proyId;
    }

    public void setProyId(Long proyId) {
        this.proyId = proyId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clie_id")
    public VtCliente getVtCliente() {
        return this.vtCliente;
    }

    public void setVtCliente(VtCliente vtCliente) {
        this.vtCliente = vtCliente;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id")
    public VtLineaNegocio getVtLineaNegocio() {
        return this.vtLineaNegocio;
    }

    public void setVtLineaNegocio(VtLineaNegocio vtLineaNegocio) {
        this.vtLineaNegocio = vtLineaNegocio;
    }

    @Column(name = "activo", nullable = false)
    public String getActivo() {
        return this.activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Column(name = "costo_total", nullable = false)
    public Long getCostoTotal() {
        return this.costoTotal;
    }

    public void setCostoTotal(Long costoTotal) {
        this.costoTotal = costoTotal;
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

    @Column(name = "nombre_proyecto", nullable = false)
    public String getNombreProyecto() {
        return this.nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vtProyecto")
    public Set<VtActividad> getVtActividads() {
        return this.vtActividads;
    }

    public void setVtActividads(Set<VtActividad> vtActividads) {
        this.vtActividads = vtActividads;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vtProyecto")
    public Set<VtProyCosto> getVtProyCostos() {
        return this.vtProyCostos;
    }

    public void setVtProyCostos(Set<VtProyCosto> vtProyCostos) {
        this.vtProyCostos = vtProyCostos;
    }
}
