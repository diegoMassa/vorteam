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
public class SegCompaniaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegCompaniaDTO.class);
    private Long ciaCodigo;
    private String ciaCodigoInterno;
    private String ciaEstadoRegistro;
    private String ciaNombre;
    private Long usuCodigo_SegUsuario;

    public Long getCiaCodigo() {
        return ciaCodigo;
    }

    public void setCiaCodigo(Long ciaCodigo) {
        this.ciaCodigo = ciaCodigo;
    }

    public String getCiaCodigoInterno() {
        return ciaCodigoInterno;
    }

    public void setCiaCodigoInterno(String ciaCodigoInterno) {
        this.ciaCodigoInterno = ciaCodigoInterno;
    }

    public String getCiaEstadoRegistro() {
        return ciaEstadoRegistro;
    }

    public void setCiaEstadoRegistro(String ciaEstadoRegistro) {
        this.ciaEstadoRegistro = ciaEstadoRegistro;
    }

    public String getCiaNombre() {
        return ciaNombre;
    }

    public void setCiaNombre(String ciaNombre) {
        this.ciaNombre = ciaNombre;
    }

    public Long getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(Long usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }
}
