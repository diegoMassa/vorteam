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
public class SegSistemaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegSistemaDTO.class);
    private Long sisCodigo;
    private String sisDescripcion;
    private String sisEstadoRegistro;
    private String sisNombre;
    private Long usuCodigo_SegUsuario;

    public Long getSisCodigo() {
        return sisCodigo;
    }

    public void setSisCodigo(Long sisCodigo) {
        this.sisCodigo = sisCodigo;
    }

    public String getSisDescripcion() {
        return sisDescripcion;
    }

    public void setSisDescripcion(String sisDescripcion) {
        this.sisDescripcion = sisDescripcion;
    }

    public String getSisEstadoRegistro() {
        return sisEstadoRegistro;
    }

    public void setSisEstadoRegistro(String sisEstadoRegistro) {
        this.sisEstadoRegistro = sisEstadoRegistro;
    }

    public String getSisNombre() {
        return sisNombre;
    }

    public void setSisNombre(String sisNombre) {
        this.sisNombre = sisNombre;
    }

    public Long getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(Long usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }
}
