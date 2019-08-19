package com.vortexbird.sentinel.controller;

import java.io.Serializable;


public class SistemaDTO implements Serializable{

	private static final long serialVersionUID = 6141284793552604898L;
	private Long codigo;
    private String nombre;
    private String descripcion;
    private String url;
    
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
    
    
}
