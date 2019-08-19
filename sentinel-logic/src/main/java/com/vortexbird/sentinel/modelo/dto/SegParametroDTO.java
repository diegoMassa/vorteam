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
public class SegParametroDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegParametroDTO.class);
    private Long parCodigo;
    private String parEstadoRegistro;
    private String parNombre;
    private String parValorAlfanumerico;
    private Date parValorFecha;
    private Double parValorNumerico;
    private Long usuCodigo_SegUsuario;

    public Long getParCodigo() {
        return parCodigo;
    }

    public void setParCodigo(Long parCodigo) {
        this.parCodigo = parCodigo;
    }

    public String getParEstadoRegistro() {
        return parEstadoRegistro;
    }

    public void setParEstadoRegistro(String parEstadoRegistro) {
        this.parEstadoRegistro = parEstadoRegistro;
    }

    public String getParNombre() {
        return parNombre;
    }

    public void setParNombre(String parNombre) {
        this.parNombre = parNombre;
    }

    public String getParValorAlfanumerico() {
        return parValorAlfanumerico;
    }

    public void setParValorAlfanumerico(String parValorAlfanumerico) {
        this.parValorAlfanumerico = parValorAlfanumerico;
    }

    public Date getParValorFecha() {
        return parValorFecha;
    }

    public void setParValorFecha(Date parValorFecha) {
        this.parValorFecha = parValorFecha;
    }

    public Double getParValorNumerico() {
        return parValorNumerico;
    }

    public void setParValorNumerico(Double parValorNumerico) {
        this.parValorNumerico = parValorNumerico;
    }

    public Long getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(Long usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }
}
