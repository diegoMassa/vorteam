package com.vortexbird.vorteam.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vortexbird.vorteam.audit.EntityAuditEventListener;

/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@NamedNativeQueries({
    @NamedNativeQuery(name="consultaActividadesOrdenadaFechaDescendiente", query = "", resultSetMapping = "consultaActividadesOrdenadaFechaDescendiente"),
    @NamedNativeQuery(name="consultaMisActividades", query = "", resultSetMapping = "consultaMisActividades"),
    @NamedNativeQuery(name="consultaMisActividadesLazy", query = "", resultSetMapping = "consultaMisActividadesLazy"),
    @NamedNativeQuery(name="consultaTotalMisActividadesLazy", query = "", resultSetMapping = "consultaTotalMisActividadesLazy")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name="consultaActividadesOrdenadaFechaDescendiente", 
        classes = { @ConstructorResult(targetClass = com.vortexbird.vorteam.dto.VtActividadDTO.class,
            columns = { 
            			@ColumnResult(name = "actiId", type = Long.class),
					@ColumnResult(name = "nombreProyecto", type = String.class),
					@ColumnResult(name = "nombre", type = String.class),
					@ColumnResult(name = "descripcion", type = String.class),
					@ColumnResult(name = "asignaciones", type = String.class),
					@ColumnResult(name = "fechaLimite", type = Date.class),
					@ColumnResult(name = "horasPresupuestadas", type = BigDecimal.class),
					@ColumnResult(name = "sprint", type = String.class),
					@ColumnResult(name = "casoSoporte", type = String.class),
					@ColumnResult(name = "facturable", type = String.class),
					@ColumnResult(name = "valorFacturable", type = Long.class),
					@ColumnResult(name = "usuaCreador", type = String.class),
					@ColumnResult(name = "usuaModificador", type = String.class),
					@ColumnResult(name = "fechaModificacion", type = Date.class),
					@ColumnResult(name = "fechaCreacion", type = Date.class),
            			@ColumnResult(name = "controlCambios", type = String.class)
    })}),
    	@SqlResultSetMapping(name="consultaMisActividades", 
    			classes = { @ConstructorResult(targetClass = com.vortexbird.vorteam.dto.VtActividadDTO.class,
    			columns = { 
    					@ColumnResult(name = "actiId", type = Long.class),
    					@ColumnResult(name = "nombreProyecto", type = String.class),
    					@ColumnResult(name = "nombreCliente", type = String.class),
    					@ColumnResult(name = "nombre", type = String.class),
    					@ColumnResult(name = "descripcion", type = String.class),
    					@ColumnResult(name = "estado", type = String.class),
    					@ColumnResult(name = "tipoActividad", type = String.class),
    					@ColumnResult(name = "fechaLimite", type = Date.class),
    					@ColumnResult(name = "horasPresupuestadas", type = BigDecimal.class),
    					@ColumnResult(name = "horasEjecutadas", type = BigDecimal.class),
    					@ColumnResult(name = "ultimaFechaRegistro", type = Date.class),
    					@ColumnResult(name = "sprint", type = String.class),
    					@ColumnResult(name = "casoSoporte", type = String.class),
    					@ColumnResult(name = "controlCamios", type = String.class),
    					@ColumnResult(name = "asigId", type = Long.class),
    					@ColumnResult(name = "porcentajeAvance", type = BigDecimal.class)    					
    			})}),
    @SqlResultSetMapping(name="consultaMisActividadesLazy", 
    classes = { @ConstructorResult(targetClass = com.vortexbird.vorteam.dto.VtActividadDTO.class,
    columns = { 
    		@ColumnResult(name = "actiId", type = Long.class),
    		@ColumnResult(name = "nombreProyecto", type = String.class),
    		@ColumnResult(name = "nombreCliente", type = String.class),
    		@ColumnResult(name = "nombre", type = String.class),
    		@ColumnResult(name = "descripcion", type = String.class),
    		@ColumnResult(name = "estado", type = String.class),
    		@ColumnResult(name = "tipoActividad", type = String.class),
    		@ColumnResult(name = "fechaLimite", type = Date.class),
    		@ColumnResult(name = "horasPresupuestadas", type = BigDecimal.class),
    		@ColumnResult(name = "horasEjecutadas", type = BigDecimal.class),
    		@ColumnResult(name = "ultimaFechaRegistro", type = Date.class),
    		@ColumnResult(name = "sprint", type = String.class),
    		@ColumnResult(name = "casoSoporte", type = String.class),
    		@ColumnResult(name = "controlCamios", type = String.class),
    		@ColumnResult(name = "asigId", type = Long.class),
    		@ColumnResult(name = "porcentajeAvance", type = BigDecimal.class)    					
    })}),
    @SqlResultSetMapping(name = "consultaTotalMisActividadesLazy",  columns = { @ColumnResult(name = "total", type=Long.class)})
})
@Entity
@Table(name = "vt_actividad", schema = "public")
@EntityListeners(EntityAuditEventListener.class)
public class VtActividad extends AbstractAuditingEntity  implements java.io.Serializable {
    private Long actiId;
    @NotNull
    private VtEstado vtEstado;
    @NotNull
    private VtProyecto vtProyecto;
    @NotNull
    private VtTipoActividad vtTipoActividad;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private String activo;
    private String casoSoporte;
    private String controlCambios;
    private String descripcion;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private String facturable;
    @NotNull
    private Date fechaCreacion;
    private Date fechaLimite;
    private Date fechaModificacion;
    private BigDecimal horasPresupuestadas;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String nombre;
    private String sprint;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String usuaCreador;
    private String usuaModificador;
    private Long valorFacturable;
    private BigDecimal porcentajeAvance;
    private Set<VtAsignacion> vtAsignacions = new HashSet<VtAsignacion>(0);

    public VtActividad() {
    }

    public VtActividad(Long actiId, String activo, String casoSoporte,
        String descripcion, String facturable, Date fechaCreacion,
        Date fechaLimite, Date fechaModificacion, BigDecimal horasPresupuestadas,
        String nombre, String sprint, String usuaCreador,
        String usuaModificador, Long valorFacturable,
        Set<VtAsignacion> vtAsignacions, VtEstado vtEstado,
        VtProyecto vtProyecto, VtTipoActividad vtTipoActividad, String controlCambios) {
        this.actiId = actiId;
        this.vtEstado = vtEstado;
        this.vtProyecto = vtProyecto;
        this.vtTipoActividad = vtTipoActividad;
        this.activo = activo;
        this.casoSoporte = casoSoporte;
        this.descripcion = descripcion;
        this.facturable = facturable;
        this.fechaCreacion = fechaCreacion;
        this.fechaLimite = fechaLimite;
        this.fechaModificacion = fechaModificacion;
        this.horasPresupuestadas = horasPresupuestadas;
        this.nombre = nombre;
        this.sprint = sprint;
        this.usuaCreador = usuaCreador;
        this.usuaModificador = usuaModificador;
        this.valorFacturable = valorFacturable;
        this.vtAsignacions = vtAsignacions;
        this.controlCambios = controlCambios;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "acti_id", unique = true)
    public Long getActiId() {
        return this.actiId;
    }

    public void setActiId(Long actiId) {
        this.actiId = actiId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "esta_id")
    public VtEstado getVtEstado() {
        return this.vtEstado;
    }

    public void setVtEstado(VtEstado vtEstado) {
        this.vtEstado = vtEstado;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proy_id")
    public VtProyecto getVtProyecto() {
        return this.vtProyecto;
    }

    public void setVtProyecto(VtProyecto vtProyecto) {
        this.vtProyecto = vtProyecto;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tiac_id")
    public VtTipoActividad getVtTipoActividad() {
        return this.vtTipoActividad;
    }

    public void setVtTipoActividad(VtTipoActividad vtTipoActividad) {
        this.vtTipoActividad = vtTipoActividad;
    }

    @Column(name = "activo", nullable = false)
    public String getActivo() {
        return this.activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Column(name = "caso_soporte")
    public String getCasoSoporte() {
        return this.casoSoporte;
    }

    public void setCasoSoporte(String casoSoporte) {
        this.casoSoporte = casoSoporte;
    }

    @Column(name = "descripcion")
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "facturable", nullable = false)
    public String getFacturable() {
        return this.facturable;
    }

    public void setFacturable(String facturable) {
        this.facturable = facturable;
    }

    @Column(name = "fecha_creacion", nullable = false)
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Column(name = "fecha_limite")
    public Date getFechaLimite() {
        return this.fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    @Column(name = "fecha_modificacion")
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Column(name = "horas_presupuestadas")
    public BigDecimal getHorasPresupuestadas() {
        return this.horasPresupuestadas;
    }

    public void setHorasPresupuestadas(BigDecimal horasPresupuestadas) {
        this.horasPresupuestadas = horasPresupuestadas;
    }

    @Column(name = "nombre", nullable = false)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "sprint")
    public String getSprint() {
        return this.sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
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

    @Column(name = "valor_facturable")
    public Long getValorFacturable() {
        return this.valorFacturable;
    }

    public void setValorFacturable(Long valorFacturable) {
        this.valorFacturable = valorFacturable;
    }

    /**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 31, 2018
	 * @since 1.8
	 * @return La entidad controlCambios
	 *
	 */
    @Column(name = "control_cambios")
	public String getControlCambios() {
		return controlCambios;
	}

	/**
	 *
	 * @param controlCambios El/La controlCambios a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 31, 2018
	 * @since 1.8
	 *
	 */
	public void setControlCambios(String controlCambios) {
		this.controlCambios = controlCambios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vtActividad")
    public Set<VtAsignacion> getVtAsignacions() {
        return this.vtAsignacions;
    }

    public void setVtAsignacions(Set<VtAsignacion> vtAsignacions) {
        this.vtAsignacions = vtAsignacions;
    }
    
	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad porcentajeAvance
	 *
	 */
   @Column(name = "porcentaje_avance")
	public BigDecimal getPorcentajeAvance() {
		return porcentajeAvance;
	}

	/**
	 *
	 * @param porcentajeAvance El/La porcentajeAvance a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setPorcentajeAvance(BigDecimal porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
	}

}
