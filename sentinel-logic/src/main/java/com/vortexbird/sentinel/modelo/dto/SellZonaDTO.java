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
public class SellZonaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String descripcion;
    private String estadoRegistro;
    private String descripcionEstadoRegistro;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long operCreador;
    private Long operModifica;
    private Integer zonaId;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

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

    public Long getOperCreador() {
        return operCreador;
    }

    public void setOperCreador(Long operCreador) {
        this.operCreador = operCreador;
    }

    public Long getOperModifica() {
        return operModifica;
    }

    public void setOperModifica(Long operModifica) {
        this.operModifica = operModifica;
    }

    public Integer getZonaId() {
        return zonaId;
    }

    public void setZonaId(Integer zonaId) {
        this.zonaId = zonaId;
    }

	public String getDescripcionEstadoRegistro() {
		return descripcionEstadoRegistro;
	}

	public void setDescripcionEstadoRegistro(String descripcionEstadoRegistro) {
		this.descripcionEstadoRegistro = descripcionEstadoRegistro;
	}
}
