package com.vortexbird.vorteam.domain;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vortexbird.vorteam.audit.EntityAuditEventListener;


@NamedNativeQueries({
    @NamedNativeQuery(name="reporteTiemposEntreFechas", query = "", resultSetMapping = "reporteTiemposEntreFechas"),
    @NamedNativeQuery(name="horasEjecutadasDia", query = "", resultSetMapping = "horasEjecutadasDia")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name="reporteTiemposEntreFechas", 
        classes = { @ConstructorResult(targetClass = com.vortexbird.vorteam.dto.VtReporteTiempoDTO.class,
            columns = { @ColumnResult(name = "semana", type = String.class),
                        @ColumnResult(name = "nombreCompleto", type = String.class),
                        @ColumnResult(name = "horas", type = BigDecimal.class)
    })}),
    @SqlResultSetMapping(name="horasEjecutadasDia", 
    classes = { @ConstructorResult(targetClass = com.vortexbird.vorteam.dto.VtReporteTiempoDTO.class,
    columns = { 
    		@ColumnResult(name = "horas", type = BigDecimal.class)
    })})
})
@Entity
@Table(name = "vt_reporte_tiempo", schema = "public")
@EntityListeners(EntityAuditEventListener.class)
public class VtReporteTiempo extends AbstractAuditingEntity  implements java.io.Serializable {
    private Long retiId;
    @NotNull
    private VtAsignacion vtAsignacion;
    @NotNull
    private VtEstado vtEstado;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private String activo;
    @NotNull
    private Date fechaCreacion;
    @NotNull
    private Date fecha;
    private Date fechaModificacion;
    @NotNull
    private BigDecimal horasEjecutadas;
    @NotNull
    @NotEmpty
    private String observacion;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String usuaCreador;
    private String usuaModificador;
    private BigDecimal porcentajeAvance;

    public VtReporteTiempo() {
    }

    public VtReporteTiempo(Long retiId, String activo, Date fechaCreacion,
        Date fecha, Date fechaModificacion,
        BigDecimal horasEjecutadas, String observacion, String usuaCreador,
        String usuaModificador, VtAsignacion vtAsignacion, VtEstado vtEstado, BigDecimal porcentajeAvance) {
        this.retiId = retiId;
        this.vtAsignacion = vtAsignacion;
        this.vtEstado = vtEstado;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fecha = fecha;
        this.fechaModificacion = fechaModificacion;
        this.horasEjecutadas = horasEjecutadas;
        this.observacion = observacion;
        this.usuaCreador = usuaCreador;
        this.usuaModificador = usuaModificador;
        this.porcentajeAvance = porcentajeAvance;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "reti_id", unique = true)
    public Long getRetiId() {
        return this.retiId;
    }

    public void setRetiId(Long retiId) {
        this.retiId = retiId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asig_id")
    public VtAsignacion getVtAsignacion() {
        return this.vtAsignacion;
    }

    public void setVtAsignacion(VtAsignacion vtAsignacion) {
        this.vtAsignacion = vtAsignacion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "esta_id")
    public VtEstado getVtEstado() {
        return this.vtEstado;
    }

    public void setVtEstado(VtEstado vtEstado) {
        this.vtEstado = vtEstado;
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

    @Column(name = "fecha", nullable = false)
    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Column(name = "fecha_modificacion")
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Column(name = "horas_ejecutadas", nullable = false)
    public BigDecimal getHorasEjecutadas() {
        return this.horasEjecutadas;
    }

    public void setHorasEjecutadas(BigDecimal horasEjecutadas) {
        this.horasEjecutadas = horasEjecutadas;
    }

    @Column(name = "observacion", nullable = false)
    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
