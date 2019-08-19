package com.vortexbird.sentinel.modelo.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "row-perfiles")
@XmlAccessorType(XmlAccessType.FIELD)
public class RollVcloudDTO implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String id;
	    private String perfil;
	    private String activo;
	    private String borrado;
	
	    
	    public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPerfil() {
			return perfil;
		}
		public void setPerfil(String perfil) {
			this.perfil = perfil;
		}
		public String getActivo() {
			return activo;
		}
		public void setActivo(String activo) {
			this.activo = activo;
		}
		public String getBorrado() {
			return borrado;
		}
		public void setBorrado(String borrado) {
			this.borrado = borrado;
		}
	}
