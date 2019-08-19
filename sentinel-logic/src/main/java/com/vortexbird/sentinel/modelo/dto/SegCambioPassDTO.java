package com.vortexbird.sentinel.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public class SegCambioPassDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegCambioPassDTO.class);
    private Long capCodigo;
    private String capEstado;
    private Date capFechaFin;
    private Date capFechaIni;
    private String capToken;
    private Long usuCodigo_SegUsuario;

    public Long getCapCodigo() {
        return capCodigo;
    }

    public void setCapCodigo(Long capCodigo) {
        this.capCodigo = capCodigo;
    }

    public String getCapEstado() {
        return capEstado;
    }

    public void setCapEstado(String capEstado) {
        this.capEstado = capEstado;
    }

    public Date getCapFechaFin() {
        return capFechaFin;
    }

    public void setCapFechaFin(Date capFechaFin) {
        this.capFechaFin = capFechaFin;
    }

    public Date getCapFechaIni() {
        return capFechaIni;
    }

    public void setCapFechaIni(Date capFechaIni) {
        this.capFechaIni = capFechaIni;
    }

    public String getCapToken() {
        return capToken;
    }

    public void setCapToken(String capToken) {
        this.capToken = capToken;
    }

    public Long getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(Long usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }
}
