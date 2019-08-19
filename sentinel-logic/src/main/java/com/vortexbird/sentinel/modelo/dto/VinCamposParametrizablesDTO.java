package com.vortexbird.sentinel.modelo.dto;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public class VinCamposParametrizablesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String estadoRegistro;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String llave;
    private String operCreador;
    private String operModifica;
    private String valor;

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
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

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getOperCreador() {
        return operCreador;
    }

    public void setOperCreador(String operCreador) {
        this.operCreador = operCreador;
    }

    public String getOperModifica() {
        return operModifica;
    }

    public void setOperModifica(String operModifica) {
        this.operModifica = operModifica;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
