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
public class VtAsignacionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtAsignacionDTO.class);
    private String activo;
    private Long asigId;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String usuaCreador;
    private String usuaModificador;
    private Long actiId_VtActividad;
    private Long persId_VtPersonas;

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Long getAsigId() {
        return asigId;
    }

    public void setAsigId(Long asigId) {
        this.asigId = asigId;
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

    public Long getActiId_VtActividad() {
        return actiId_VtActividad;
    }

    public void setActiId_VtActividad(Long actiId_VtActividad) {
        this.actiId_VtActividad = actiId_VtActividad;
    }

    public Long getPersId_VtPersonas() {
        return persId_VtPersonas;
    }

    public void setPersId_VtPersonas(Long persId_VtPersonas) {
        this.persId_VtPersonas = persId_VtPersonas;
    }
}
