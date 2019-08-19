package com.vortexbird.sentinel.modelo.dto;

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
public class SellCiudadDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer ciudId;
    private String codigo;
    private String descripcion;
    private String estadoRegistro;
    private String descripcionEstadoRegistro;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long operCreador;
    private Long operModifica;
    private Integer deptId_SellDepartamento;
    private String descripcionDepartamento;
    private Integer idSubZona;
    private String descripcionSubZona;
    private Integer idZona;
    private String descripcionZona;
    
    private String cod_error;
    private String mensaje;
    private List<SellCiudadDTO> lstCiudad;

    public Integer getCiudId() {
        return ciudId;
    }

    public void setCiudId(Integer ciudId) {
        this.ciudId = ciudId;
    }

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

    public Integer getDeptId_SellDepartamento() {
        return deptId_SellDepartamento;
    }

    public void setDeptId_SellDepartamento(Integer deptId_SellDepartamento) {
        this.deptId_SellDepartamento = deptId_SellDepartamento;
    }

	public String getDescripcionEstadoRegistro() {
		return descripcionEstadoRegistro;
	}

	public void setDescripcionEstadoRegistro(String descripcionEstadoRegistro) {
		this.descripcionEstadoRegistro = descripcionEstadoRegistro;
	}

	public String getDescripcionDepartamento() {
		return descripcionDepartamento;
	}

	public void setDescripcionDepartamento(String descripcionDepartamento) {
		this.descripcionDepartamento = descripcionDepartamento;
	}

	public Integer getIdSubZona() {
		return idSubZona;
	}

	public void setIdSubZona(Integer idSubZona) {
		this.idSubZona = idSubZona;
	}

	public String getDescripcionSubZona() {
		return descripcionSubZona;
	}

	public void setDescripcionSubZona(String descripcionSubZona) {
		this.descripcionSubZona = descripcionSubZona;
	}

	public Integer getIdZona() {
		return idZona;
	}

	public void setIdZona(Integer idZona) {
		this.idZona = idZona;
	}

	public String getDescripcionZona() {
		return descripcionZona;
	}

	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

	public String getCod_error() {
		return cod_error;
	}

	public void setCod_error(String cod_error) {
		this.cod_error = cod_error;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<SellCiudadDTO> getLstCiudad() {
		return lstCiudad;
	}

	public void setLstCiudad(List<SellCiudadDTO> lstCiudad) {
		this.lstCiudad = lstCiudad;
	}
	
	

}
