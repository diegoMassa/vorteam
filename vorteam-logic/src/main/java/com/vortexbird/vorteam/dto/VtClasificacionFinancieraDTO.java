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
public class VtClasificacionFinancieraDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtClasificacionFinancieraDTO.class);
    private String activo;
    private Long clfiId;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String usuaCreador;
    private String usuaModificador;

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Long getClfiId() {
        return clfiId;
    }

    public void setClfiId(Long clfiId) {
        this.clfiId = clfiId;
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
}
