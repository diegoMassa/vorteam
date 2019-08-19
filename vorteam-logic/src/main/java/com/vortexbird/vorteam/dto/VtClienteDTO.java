package com.vortexbird.vorteam.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;
import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public class VtClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtClienteDTO.class);
    private String activo;
    private Long clieId;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String identificacion;
    private String nombreRazonSocial;
    private String usuaCreador;
    private String usuaModificador;
    private Long tiidId_VtTipoIdentificacion;

    private String tipoIdentificacion;
    private List<VtProyectoDTO> proyectos;
    
    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Long getClieId() {
        return clieId;
    }

    public void setClieId(Long clieId) {
        this.clieId = clieId;
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

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getUsuaCreador() {
        return usuaCreador;
    }

    public void setUsuaCreador(String usuaCreador) {
        this.usuaCreador = usuaCreador;
    }

    public String getUsuaModificador() {
        return usuaModificador;
    }

    public void setUsuaModificador(String usuaModificador) {
        this.usuaModificador = usuaModificador;
    }

    public Long getTiidId_VtTipoIdentificacion() {
        return tiidId_VtTipoIdentificacion;
    }

    public void setTiidId_VtTipoIdentificacion(Long tiidId_VtTipoIdentificacion) {
        this.tiidId_VtTipoIdentificacion = tiidId_VtTipoIdentificacion;
    }

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 * @return La entidad tipoIdentificacion
	 *
	 */
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	/**
	 *
	 * @param tipoIdentificacion El/La tipoIdentificacion a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 *
	 */
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 * @return La entidad proyectos
	 *
	 */
	public List<VtProyectoDTO> getProyectos() {
		return proyectos;
	}

	/**
	 *
	 * @param proyectos El/La proyectos a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 * @since 1.8
	 *
	 */
	public void setProyectos(List<VtProyectoDTO> proyectos) {
		this.proyectos = proyectos;
	}
}
