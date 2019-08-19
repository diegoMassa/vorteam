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
public class SegUsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegUsuarioDTO.class);
    private String usuApellidos;
    private Long usuCodigo;
    private String usuCodigoInterno;
    private Long usuCompaniaCiaCodigo;
    private String usuConstrasena;
    private String usuCorreo;
    private String usuEstadoRegistro;
    private Long usuIntentosFallidos;
    private String usuLogin;
    private String usuNombres;
    private Date usuUltmimaModificacionPass;
    private Long usuCodigo_SegUsuario;
    
    private String usuEstadoRegistroNombre;

    public String getUsuApellidos() {
        return usuApellidos;
    }

    public void setUsuApellidos(String usuApellidos) {
        this.usuApellidos = usuApellidos;
    }

    public Long getUsuCodigo() {
        return usuCodigo;
    }

    public void setUsuCodigo(Long usuCodigo) {
        this.usuCodigo = usuCodigo;
    }

    public String getUsuCodigoInterno() {
        return usuCodigoInterno;
    }

    public void setUsuCodigoInterno(String usuCodigoInterno) {
        this.usuCodigoInterno = usuCodigoInterno;
    }

    public Long getUsuCompaniaCiaCodigo() {
        return usuCompaniaCiaCodigo;
    }

    public void setUsuCompaniaCiaCodigo(Long usuCompaniaCiaCodigo) {
        this.usuCompaniaCiaCodigo = usuCompaniaCiaCodigo;
    }

    public String getUsuConstrasena() {
        return usuConstrasena;
    }

    public void setUsuConstrasena(String usuConstrasena) {
        this.usuConstrasena = usuConstrasena;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public String getUsuEstadoRegistro() {
        return usuEstadoRegistro;
    }

    public void setUsuEstadoRegistro(String usuEstadoRegistro) {
        this.usuEstadoRegistro = usuEstadoRegistro;
    }

    public Long getUsuIntentosFallidos() {
        return usuIntentosFallidos;
    }

    public void setUsuIntentosFallidos(Long usuIntentosFallidos) {
        this.usuIntentosFallidos = usuIntentosFallidos;
    }

    public String getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(String usuLogin) {
        this.usuLogin = usuLogin;
    }

    public String getUsuNombres() {
        return usuNombres;
    }

    public void setUsuNombres(String usuNombres) {
        this.usuNombres = usuNombres;
    }

    public Date getUsuUltmimaModificacionPass() {
        return usuUltmimaModificacionPass;
    }

    public void setUsuUltmimaModificacionPass(Date usuUltmimaModificacionPass) {
        this.usuUltmimaModificacionPass = usuUltmimaModificacionPass;
    }

    public Long getUsuCodigo_SegUsuario() {
        return usuCodigo_SegUsuario;
    }

    public void setUsuCodigo_SegUsuario(Long usuCodigo_SegUsuario) {
        this.usuCodigo_SegUsuario = usuCodigo_SegUsuario;
    }

	public String getUsuEstadoRegistroNombre() {
		return usuEstadoRegistroNombre;
	}

	public void setUsuEstadoRegistroNombre(String usuEstadoRegistroNombre) {
		this.usuEstadoRegistroNombre = usuEstadoRegistroNombre;
	}
    
}
