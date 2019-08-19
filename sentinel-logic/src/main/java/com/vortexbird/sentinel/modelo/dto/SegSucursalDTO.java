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
public class SegSucursalDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegSucursalDTO.class);
    private Long sucCodigo;
    private String sucCodigoInterno;
    private String sucEstadoRegistro;
    private String sucNombre;
    private Long ciaCodigo_SegCompania;
    private Long usuCodigo_SegUsuario;

    public Long getSucCodigo() {
        return sucCodigo;
    }

    public void setSucCodigo(Long sucCodigo) {
        this.sucCodigo = sucCodigo;
    }

    public String getSucCodigoInterno() {
        return sucCodigoInterno;
    }

    public void setSucCodigoInterno(String sucCodigoInterno) {
        this.sucCodigoInterno = sucCodigoInterno;
    }

    public String getSucEstadoRegistro() {
        return sucEstadoRegistro;
    }

    public void setSucEstadoRegistro(String sucEstadoRegistro) {
        this.sucEstadoRegistro = sucEstadoRegistro;
    }

    public String getSucNombre() {
        return sucNombre;
    }

    public void setSucNombre(String sucNombre) {
        this.sucNombre = sucNombre;
    }

    public Long getCiaCodigo_SegCompania() {
        return ciaCodigo_SegCompania;
    }

    public void setCiaCodigo_SegCompania(Long ciaCodigo_SegCompania) {
        this.ciaCodigo_SegCompania = ciaCodigo_SegCompania;
    }

    public Long getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(Long usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }
}
