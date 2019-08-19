package com.vortexbird.vorteam.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public class VtReporteTiempoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtReporteTiempoDTO.class);
    private String activo;
    private Date fechaCreacion;
    private Date fecha;
    private Date fechaModificacion;
    private BigDecimal horasEjecutadas;
    private String observacion;
    private Long retiId;
    private String usuaCreador;
    private String usuaModificador;
    private Long asigId_VtAsignacion;
    private Long estaId_VtEstado;
    private BigDecimal porcentajeAvance;
    
    private String semana, nombreCompleto;
    private BigDecimal horas;
    
    

    /**
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 16, 2018
	 * @since 1.8
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	public VtReporteTiempoDTO() {
		super();
	}
	
	

	/**
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 16, 2018
	 * @since 1.8
	 * @param semana
	 * @param nombreCompleto
	 * @param horas
	 * @return <b>{@code }</b> Start here...
	 * reporteTiemposEntreFechas
	 */
	public VtReporteTiempoDTO(String semana, String nombreCompleto, BigDecimal horas) {
		super();
		this.semana = semana;
		this.nombreCompleto = nombreCompleto;
		this.horas = horas;
	}



	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 * @param horas
	 * @return <b>{@code }</b> Start here...
	 * reporteTiemposEntreFechas
	 */
	public VtReporteTiempoDTO(BigDecimal horas) {
		super();
		this.horas = horas;
	}



	public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public BigDecimal getHorasEjecutadas() {
        return horasEjecutadas;
    }

    public void setHorasEjecutadas(BigDecimal horasEjecutadas) {
        this.horasEjecutadas = horasEjecutadas;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Long getRetiId() {
        return retiId;
    }

    public void setRetiId(Long retiId) {
        this.retiId = retiId;
    }

    public String getUsuaCreador() {
        return usuaCreador;
    }

    public void setUsuaCreador(String usuaCreador) {
        this.usuaCreador = usuaCreador;
    }

    public String getUsuaModificador() {
        return usuaModificador;
    }

    public void setUsuaModificador(String usuaModificador) {
        this.usuaModificador = usuaModificador;
    }

    public Long getAsigId_VtAsignacion() {
        return asigId_VtAsignacion;
    }

    public void setAsigId_VtAsignacion(Long asigId_VtAsignacion) {
        this.asigId_VtAsignacion = asigId_VtAsignacion;
    }

    public Long getEstaId_VtEstado() {
        return estaId_VtEstado;
    }

    public void setEstaId_VtEstado(Long estaId_VtEstado) {
        this.estaId_VtEstado = estaId_VtEstado;
    }

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 16, 2018
	 * @since 1.8
	 * @return La entidad semana
	 *
	 */
	public String getSemana() {
		return semana;
	}

	/**
	 *
	 * @param semana El/La semana a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 16, 2018
	 * @since 1.8
	 *
	 */
	public void setSemana(String semana) {
		this.semana = semana;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 16, 2018
	 * @since 1.8
	 * @return La entidad nombreCompleto
	 *
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 *
	 * @param nombreCompleto El/La nombreCompleto a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 16, 2018
	 * @since 1.8
	 *
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 16, 2018
	 * @since 1.8
	 * @return La entidad horas
	 *
	 */
	public BigDecimal getHoras() {
		return horas;
	}

	/**
	 *
	 * @param horas El/La horas a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 16, 2018
	 * @since 1.8
	 *
	 */
	public void setHoras(BigDecimal horas) {
		this.horas = horas;
	}



	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad porcentajeAvance
	 *
	 */
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
