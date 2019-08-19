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
public class SegRolUsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegRolUsuarioDTO.class);
    private Long rluCodigo;
    private String rluEstadoRegistro;
    private Long rolCodigo_SegRol;
    private Long usuCodigo_SegUsuarioCrea;
    private Long usuCodigo_SegUsuarioModifica;

    public Long getRluCodigo() {
        return rluCodigo;
    }

    public void setRluCodigo(Long rluCodigo) {
        this.rluCodigo = rluCodigo;
    }

    public String getRluEstadoRegistro() {
        return rluEstadoRegistro;
    }

    public void setRluEstadoRegistro(String rluEstadoRegistro) {
        this.rluEstadoRegistro = rluEstadoRegistro;
    }

    public Long getRolCodigo_SegRol() {
        return rolCodigo_SegRol;
    }

    public void setRolCodigo_SegRol(Long rolCodigo_SegRol) {
        this.rolCodigo_SegRol = rolCodigo_SegRol;
    }

	public Long getUsuCodigo_SegUsuarioCrea() {
		return usuCodigo_SegUsuarioCrea;
	}

	public void setUsuCodigo_SegUsuarioCrea(Long usuCodigo_SegUsuarioCrea) {
		this.usuCodigo_SegUsuarioCrea = usuCodigo_SegUsuarioCrea;
	}

	public Long getUsuCodigo_SegUsuarioModifica() {
		return usuCodigo_SegUsuarioModifica;
	}

	public void setUsuCodigo_SegUsuarioModifica(Long usuCodigo_SegUsuarioModifica) {
		this.usuCodigo_SegUsuarioModifica = usuCodigo_SegUsuarioModifica;
	}
    
}
