package com.vortexbird.sentinel.modelo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
public class SellSucursalDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String descripcion;
	private String estadoRegistro;
	private String descripcionEstadoRegistro;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Long operCreador;
	private Long operModifica;
	private Integer sucuId;
	private Integer ciudId_SellCiudad;
	private String descripcionCiudad;
	private Integer puveId_SellPuntoVenta;
	private String descripcionPuntoDeVenta;
	private Integer cantidadAsesores;

	// Para servicios rest
	private String mensaje;
	private String cod_error;
	private List<SellSucursalDTO> lstSucursal;
	private String sucu_codigo;
	private String sucu_descripcion;
	

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

	public Integer getSucuId() {
		return sucuId;
	}

	public void setSucuId(Integer sucuId) {
		this.sucuId = sucuId;
	}

	public Integer getCiudId_SellCiudad() {
		return ciudId_SellCiudad;
	}

	public void setCiudId_SellCiudad(Integer ciudId_SellCiudad) {
		this.ciudId_SellCiudad = ciudId_SellCiudad;
	}

	public Integer getPuveId_SellPuntoVenta() {
		return puveId_SellPuntoVenta;
	}

	public void setPuveId_SellPuntoVenta(Integer puveId_SellPuntoVenta) {
		this.puveId_SellPuntoVenta = puveId_SellPuntoVenta;
	}

	public String getDescripcionEstadoRegistro() {
		return descripcionEstadoRegistro;
	}

	public void setDescripcionEstadoRegistro(String descripcionEstadoRegistro) {
		this.descripcionEstadoRegistro = descripcionEstadoRegistro;
	}

	public String getDescripcionCiudad() {
		return descripcionCiudad;
	}

	public void setDescripcionCiudad(String descripcionCiudad) {
		this.descripcionCiudad = descripcionCiudad;
	}

	public String getDescripcionPuntoDeVenta() {
		return descripcionPuntoDeVenta;
	}

	public void setDescripcionPuntoDeVenta(String descripcionPuntoDeVenta) {
		this.descripcionPuntoDeVenta = descripcionPuntoDeVenta;
	}

	public Integer getCantidadAsesores() {
		return cantidadAsesores;
	}

	public void setCantidadAsesores(Integer cantidadAsesores) {
		this.cantidadAsesores = cantidadAsesores;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getCod_error() {
		return cod_error;
	}

	public void setCod_error(String cod_error) {
		this.cod_error = cod_error;
	}

	public List<SellSucursalDTO> getLstSucursal() {
		return lstSucursal;
	}

	public void setLstSucursal(List<SellSucursalDTO> lstSucursal) {
		this.lstSucursal = lstSucursal;
	}

	public String getSucu_codigo() {
		return sucu_codigo;
	}

	public void setSucu_codigo(String sucu_codigo) {
		this.sucu_codigo = sucu_codigo;
	}

	public String getSucu_descripcion() {
		return sucu_descripcion;
	}

	public void setSucu_descripcion(String sucu_descripcion) {
		this.sucu_descripcion = sucu_descripcion;
	}

	

	

}
