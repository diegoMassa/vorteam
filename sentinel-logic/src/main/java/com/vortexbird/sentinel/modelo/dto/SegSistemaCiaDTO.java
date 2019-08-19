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
public class SegSistemaCiaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegSistemaCiaDTO.class);
    private Long sicCodigo;
    private String sicEstadoRegistro;
    private Long ciaCodigo_SegCompania;
    private Long sisCodigo_SegSistema;
    private Long usuCodigo_SegUsuario;

    public Long getSicCodigo() {
        return sicCodigo;
    }

    public void setSicCodigo(Long sicCodigo) {
        this.sicCodigo = sicCodigo;
    }

    public String getSicEstadoRegistro() {
        return sicEstadoRegistro;
    }

    public void setSicEstadoRegistro(String sicEstadoRegistro) {
        this.sicEstadoRegistro = sicEstadoRegistro;
    }

    public Long getCiaCodigo_SegCompania() {
        return ciaCodigo_SegCompania;
    }

    public void setCiaCodigo_SegCompania(Long ciaCodigo_SegCompania) {
        this.ciaCodigo_SegCompania = ciaCodigo_SegCompania;
    }

    public Long getSisCodigo_SegSistema() {
        return sisCodigo_SegSistema;
    }

    public void setSisCodigo_SegSistema(Long sisCodigo_SegSistema) {
        this.sisCodigo_SegSistema = sisCodigo_SegSistema;
    }

    public Long getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(Long usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }
}
