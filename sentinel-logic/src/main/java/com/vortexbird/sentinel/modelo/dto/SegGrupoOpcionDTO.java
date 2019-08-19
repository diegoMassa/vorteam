package com.vortexbird.sentinel.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.sentinel.modelo.SegGrupoOpcion;

import java.io.Serializable;
import java.sql.*;
import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public class SegGrupoOpcionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String gruDescripcion;
    private String gruEstadoRegistro;
    private String gruLlaveAcceso;
    private String gruNombre;
    private String gruCodigo_SegGrupoOpcion;
    private String segNombre_SegGrupoPadre;
    private String sisCodigo_SegSistema;
    private String sisNombre_SegSistema;
    private String usuCodigo_SegUsuario;
    private String gruCodigo;
    private boolean rowSelected = false;
    private SegGrupoOpcion segGrupoOpcion;
    private Integer orden;

    public SegGrupoOpcion getSegGrupoOpcion() {
        return segGrupoOpcion;
    }


    public void setSegGrupoOpcion(SegGrupoOpcion segGrupoOpcion) {
        this.segGrupoOpcion = segGrupoOpcion;
    }

    public String getGruDescripcion() {
        return gruDescripcion;
    }

    public void setGruDescripcion(String gruDescripcion) {
        this.gruDescripcion = gruDescripcion;
    }

    public String getGruEstadoRegistro() {
        return gruEstadoRegistro;
    }

    public void setGruEstadoRegistro(String gruEstadoRegistro) {
        this.gruEstadoRegistro = gruEstadoRegistro;
    }

    public String getGruLlaveAcceso() {
        return gruLlaveAcceso;
    }

    public void setGruLlaveAcceso(String gruLlaveAcceso) {
        this.gruLlaveAcceso = gruLlaveAcceso;
    }

    public String getGruNombre() {
        return gruNombre;
    }

    public void setGruNombre(String gruNombre) {
        this.gruNombre = gruNombre;
    }

    public String getGruCodigo_SegGrupoOpcion() {
        return gruCodigo_SegGrupoOpcion;
    }

    public void setGruCodigo_SegGrupoOpcion(String gruCodigo_SegGrupoOpcion) {
        this.gruCodigo_SegGrupoOpcion = gruCodigo_SegGrupoOpcion;
    }

    public String getSisCodigo_SegSistema() {
        return sisCodigo_SegSistema;
    }

    public void setSisCodigo_SegSistema(String sisCodigo_SegSistema) {
        this.sisCodigo_SegSistema = sisCodigo_SegSistema;
    }

    public String getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(String usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }

    public String getGruCodigo() {
        return gruCodigo;
    }

    public void setGruCodigo(String gruCodigo) {
        this.gruCodigo = gruCodigo;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }

	public String getSisNombre_SegSistema() {
		return sisNombre_SegSistema;
	}

	public void setSisNombre_SegSistema(String sisNombreSegSistema) {
		sisNombre_SegSistema = sisNombreSegSistema;
	}

	public String getSegNombre_SegGrupoPadre() {
		return segNombre_SegGrupoPadre;
	}

	public void setSegNombre_SegGrupoPadre(String segNombreSegGrupoPadre) {
		segNombre_SegGrupoPadre = segNombreSegGrupoPadre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Integer getOrden() {
		return orden;
	}


	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
}
