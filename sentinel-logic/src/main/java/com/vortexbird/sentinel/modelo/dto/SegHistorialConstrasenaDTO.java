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
public class SegHistorialConstrasenaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegHistorialConstrasenaDTO.class);
    private Long hcoCodigo;
    private String hcoConstrasena;
    private String hcoDetalleCambio;
    private Date hcoFechaCambio;
    private Long usuCodigo_SegUsuario;

    public Long getHcoCodigo() {
        return hcoCodigo;
    }

    public void setHcoCodigo(Long hcoCodigo) {
        this.hcoCodigo = hcoCodigo;
    }

    public String getHcoConstrasena() {
        return hcoConstrasena;
    }

    public void setHcoConstrasena(String hcoConstrasena) {
        this.hcoConstrasena = hcoConstrasena;
    }

    public String getHcoDetalleCambio() {
        return hcoDetalleCambio;
    }

    public void setHcoDetalleCambio(String hcoDetalleCambio) {
        this.hcoDetalleCambio = hcoDetalleCambio;
    }

    public Date getHcoFechaCambio() {
        return hcoFechaCambio;
    }

    public void setHcoFechaCambio(Date hcoFechaCambio) {
        this.hcoFechaCambio = hcoFechaCambio;
    }

    public Long getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(Long usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }
}
