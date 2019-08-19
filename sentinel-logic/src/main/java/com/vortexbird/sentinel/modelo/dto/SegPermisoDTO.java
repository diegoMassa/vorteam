package com.vortexbird.sentinel.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.sentinel.modelo.SegPermiso;

import java.io.Serializable;
import java.sql.*;
import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public class SegPermisoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegPermisoDTO.class);
    private Long perCodigo;
    private String perEstadoRegistro;
    private Long gruCodigo_SegGrupoOpcion;
    private Long opcCodigo_SegOpcion;
    private Long rolCodigo_SegRol;
    private Long sicCodigo_SegSistemaCia;
    private Long sucCodigo_SegSucursal;
    private Long usuCodigo_SegUsuarioCrea;
    private Long usuCodigo_SegUsuarioModifica;
    
    private String ciaCodigo_SegCompania;
    private String usuCodigo_SegUsuario;
    private boolean rowSelected = false;
    private SegPermiso segPermiso;
    
    private String perEstadoRegistroNombre;

    public Long getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(Long perCodigo) {
        this.perCodigo = perCodigo;
    }

    public String getPerEstadoRegistro() {
        return perEstadoRegistro;
    }

    public void setPerEstadoRegistro(String perEstadoRegistro) {
        this.perEstadoRegistro = perEstadoRegistro;
    }

    public Long getGruCodigo_SegGrupoOpcion() {
        return gruCodigo_SegGrupoOpcion;
    }

    public void setGruCodigo_SegGrupoOpcion(Long gruCodigo_SegGrupoOpcion) {
        this.gruCodigo_SegGrupoOpcion = gruCodigo_SegGrupoOpcion;
    }

    public Long getOpcCodigo_SegOpcion() {
        return opcCodigo_SegOpcion;
    }

    public void setOpcCodigo_SegOpcion(Long opcCodigo_SegOpcion) {
        this.opcCodigo_SegOpcion = opcCodigo_SegOpcion;
    }

    public Long getRolCodigo_SegRol() {
        return rolCodigo_SegRol;
    }

    public void setRolCodigo_SegRol(Long rolCodigo_SegRol) {
        this.rolCodigo_SegRol = rolCodigo_SegRol;
    }

    public Long getSicCodigo_SegSistemaCia() {
        return sicCodigo_SegSistemaCia;
    }

    public void setSicCodigo_SegSistemaCia(Long sicCodigo_SegSistemaCia) {
        this.sicCodigo_SegSistemaCia = sicCodigo_SegSistemaCia;
    }

    public Long getSucCodigo_SegSucursal() {
        return sucCodigo_SegSucursal;
    }

    public void setSucCodigo_SegSucursal(Long sucCodigo_SegSucursal) {
        this.sucCodigo_SegSucursal = sucCodigo_SegSucursal;
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

	public String getCiaCodigo_SegCompania() {
		return ciaCodigo_SegCompania;
	}

	public void setCiaCodigo_SegCompania(String ciaCodigo_SegCompania) {
		this.ciaCodigo_SegCompania = ciaCodigo_SegCompania;
	}

	public String getUsuCodigo_SegUsuario() {
		return usuCodigo_SegUsuario;
	}

	public void setUsuCodigo_SegUsuario(String usuCodigo_SegUsuario) {
		this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
	}

	public boolean isRowSelected() {
		return rowSelected;
	}

	public void setRowSelected(boolean rowSelected) {
		this.rowSelected = rowSelected;
	}

	public SegPermiso getSegPermiso() {
		return segPermiso;
	}

	public void setSegPermiso(SegPermiso segPermiso) {
		this.segPermiso = segPermiso;
	}

	public String getPerEstadoRegistroNombre() {
		return perEstadoRegistroNombre;
	}

	public void setPerEstadoRegistroNombre(String perEstadoRegistroNombre) {
		this.perEstadoRegistroNombre = perEstadoRegistroNombre;
	}
	
}
