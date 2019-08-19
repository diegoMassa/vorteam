package com.vortexbird.vorteam.domain;

import java.util.Date;

public abstract class AbstractAuditingEntity {
	
	  
	    private Date fechaCreacion;
	    private Date fechaModificacion;
	    private String usuarioCreador;
	    private String usuarioModificador;
	    
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
		public String getUsuarioCreador() {
			return usuarioCreador;
		}
		public void setUsuarioCreador(String usuarioCreador) {
			this.usuarioCreador = usuarioCreador;
		}
		public String getUsuarioModificador() {
			return usuarioModificador;
		}
		public void setUsuarioModificador(String usuarioModificador) {
			this.usuarioModificador = usuarioModificador;
		}
	    
	    
	    

}
