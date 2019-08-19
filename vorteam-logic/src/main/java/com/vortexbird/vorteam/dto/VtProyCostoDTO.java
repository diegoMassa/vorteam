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
public class VtProyCostoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtProyCostoDTO.class);
    private String activo;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long prcoId;
    private String usuaCreador;
    private String usuaModificador;
    private Long valorHora;
    private Long persId_VtPersonas;
    private Long proyId_VtProyecto;

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

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Long getPrcoId() {
        return prcoId;
    }

    public void setPrcoId(Long prcoId) {
        this.prcoId = prcoId;
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

    public Long getValorHora() {
        return valorHora;
    }

    public void setValorHora(Long valorHora) {
        this.valorHora = valorHora;
    }

    public Long getPersId_VtPersonas() {
        return persId_VtPersonas;
    }

    public void setPersId_VtPersonas(Long persId_VtPersonas) {
        this.persId_VtPersonas = persId_VtPersonas;
    }

    public Long getProyId_VtProyecto() {
        return proyId_VtProyecto;
    }

    public void setProyId_VtProyecto(Long proyId_VtProyecto) {
        this.proyId_VtProyecto = proyId_VtProyecto;
    }
}
