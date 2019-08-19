package com.vortexbird.vorteam.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public class VtTipoActividadDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtTipoActividadDTO.class);
    private String activo;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long tiacId;
    private String usuaCreador;
    private String usuaModificador;
    private Long clfiId_VtClasificacionFinanciera;

    private String clasificacionFinanciera, activoDescripcion;
    
    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Long getTiacId() {
        return tiacId;
    }

    public void setTiacId(Long tiacId) {
        this.tiacId = tiacId;
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
    
    public Long getClfiId_VtClasificacionFinanciera() {
        return clfiId_VtClasificacionFinanciera;
    }

    public void setClfiId_VtClasificacionFinanciera(
        Long clfiId_VtClasificacionFinanciera) {
        this.clfiId_VtClasificacionFinanciera = clfiId_VtClasificacionFinanciera;
    }

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad clasificacionFinanciera
	 *
	 */
	public String getClasificacionFinanciera() {
		return clasificacionFinanciera;
	}

	/**
	 *
	 * @param clasificacionFinanciera El/La clasificacionFinanciera a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setClasificacionFinanciera(String clasificacionFinanciera) {
		this.clasificacionFinanciera = clasificacionFinanciera;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad activoDescripcion
	 *
	 */
	public String getActivoDescripcion() {
		return activoDescripcion;
	}

	/**
	 *
	 * @param activoDescripcion El/La activoDescripcion a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setActivoDescripcion(String activoDescripcion) {
		this.activoDescripcion = activoDescripcion;
	}
}
