package com.vortexbird.sentinel.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;
import java.util.List;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public class SegRolDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegRolDTO.class);
    private Long rolCodigo;
    private String rolDescripcion;
    private Long rolDiasCaducidadPwd;
    private String rolEstadoRegistro;
    private String rolNombre;
    private String sisCodigo_SegSistema;
    private Long usuCodigo_SegUsuario;
    private String esAdmonDeSistema;
    
    private Long ciaCodigo_SegCompania;
    private List<Long> opciones;

    public Long getRolCodigo() {
        return rolCodigo;
    }

    public void setRolCodigo(Long rolCodigo) {
        this.rolCodigo = rolCodigo;
    }

    public String getRolDescripcion() {
        return rolDescripcion;
    }

    public void setRolDescripcion(String rolDescripcion) {
        this.rolDescripcion = rolDescripcion;
    }

    public Long getRolDiasCaducidadPwd() {
        return rolDiasCaducidadPwd;
    }

    public void setRolDiasCaducidadPwd(Long rolDiasCaducidadPwd) {
        this.rolDiasCaducidadPwd = rolDiasCaducidadPwd;
    }

    public String getRolEstadoRegistro() {
        return rolEstadoRegistro;
    }

    public void setRolEstadoRegistro(String rolEstadoRegistro) {
        this.rolEstadoRegistro = rolEstadoRegistro;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String getSisCodigo_SegSistema() {
        return sisCodigo_SegSistema;
    }

    public void setSisCodigo_SegSistema(String sisCodigo_SegSistema) {
        this.sisCodigo_SegSistema = sisCodigo_SegSistema;
    }

    public Long getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(Long usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }

	public String getEsAdmonDeSistema() {
		return esAdmonDeSistema;
	}

	public void setEsAdmonDeSistema(String esAdmonDeSistema) {
		this.esAdmonDeSistema = esAdmonDeSistema;
	}

	/**
	 * @author Camilo José Delgado Herrera
	 * @version 2018-07-12
	 * @return the opciones
	 */
	public List<Long> getOpciones() {
		return opciones;
	}

	/**
	 * @author Camilo José Delgado Herrera
	 * @version 2018-07-12
	 * @param opciones the permisos to set
	 */
	public void setOpciones(List<Long> opciones) {
		this.opciones = opciones;
	}

	/**
	 * @author Camilo José Delgado Herrera
	 * @version 2018-07-12
	 * @return the ciaCodigo_SegCompania
	 */
	public Long getCiaCodigo_SegCompania() {
		return ciaCodigo_SegCompania;
	}

	/**
	 * @author Camilo José Delgado Herrera
	 * @version 2018-07-12
	 * @param ciaCodigo_SegCompania the ciaCodigo_SegCompania to set
	 */
	public void setCiaCodigo_SegCompania(Long ciaCodigo_SegCompania) {
		this.ciaCodigo_SegCompania = ciaCodigo_SegCompania;
	}
}
