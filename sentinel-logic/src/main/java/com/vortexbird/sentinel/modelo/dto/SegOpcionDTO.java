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
public class SegOpcionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegOpcionDTO.class);
    private Long opcCodigo;
    private String opcDescripcion;
    private String opcEstadoRegistro;
    private String opcLlaveAcceso;
    private String opcNombre;
    private Long gruCodigo_SegGrupoOpcion;
    private Long usuCodigo_SegUsuario;
    private String gruNombre_SegGrupoOpcion;
    private Integer orden;
    
    public Long getOpcCodigo() {
        return opcCodigo;
    }

    public void setOpcCodigo(Long opcCodigo) {
        this.opcCodigo = opcCodigo;
    }

    public String getOpcDescripcion() {
        return opcDescripcion;
    }

    public void setOpcDescripcion(String opcDescripcion) {
        this.opcDescripcion = opcDescripcion;
    }

    public String getOpcEstadoRegistro() {
        return opcEstadoRegistro;
    }

    public void setOpcEstadoRegistro(String opcEstadoRegistro) {
        this.opcEstadoRegistro = opcEstadoRegistro;
    }

    public String getOpcLlaveAcceso() {
        return opcLlaveAcceso;
    }

    public void setOpcLlaveAcceso(String opcLlaveAcceso) {
        this.opcLlaveAcceso = opcLlaveAcceso;
    }

    public String getOpcNombre() {
        return opcNombre;
    }

    public void setOpcNombre(String opcNombre) {
        this.opcNombre = opcNombre;
    }

    public Long getGruCodigo_SegGrupoOpcion() {
        return gruCodigo_SegGrupoOpcion;
    }

    public void setGruCodigo_SegGrupoOpcion(Long gruCodigo_SegGrupoOpcion) {
        this.gruCodigo_SegGrupoOpcion = gruCodigo_SegGrupoOpcion;
    }

    public Long getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(Long usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }

	public String getGruNombre_SegGrupoOpcion() {
		return gruNombre_SegGrupoOpcion;
	}

	public void setGruNombre_SegGrupoOpcion(String gruNombre_SegGrupoOpcion) {
		this.gruNombre_SegGrupoOpcion = gruNombre_SegGrupoOpcion;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
}
