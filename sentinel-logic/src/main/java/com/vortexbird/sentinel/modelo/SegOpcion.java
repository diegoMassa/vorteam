package com.vortexbird.sentinel.modelo;
// Generated Oct 15, 2016 4:43:12 PM by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * SegOpcion generated by hbm2java
 */
public class SegOpcion  implements java.io.Serializable {


     private Long opcCodigo;
     private SegUsuario segUsuario;
     private SegGrupoOpcion segGrupoOpcion;
     private String opcNombre;
     private String opcDescripcion;
     private String opcLlaveAcceso;
     private String opcEstadoRegistro;
     private Integer orden;
     
     private Set<SegPermiso> segPermisos = new HashSet<SegPermiso>(0);

    public SegOpcion() {
    }

	
    public SegOpcion(Long opcCodigo, SegGrupoOpcion segGrupoOpcion, String opcNombre, String opcEstadoRegistro) {
        this.opcCodigo = opcCodigo;
        this.segGrupoOpcion = segGrupoOpcion;
        this.opcNombre = opcNombre;
        this.opcEstadoRegistro = opcEstadoRegistro;
    }
    
    public SegOpcion(Long opcCodigo, SegUsuario segUsuario, SegGrupoOpcion segGrupoOpcion, String opcNombre, String opcDescripcion, String opcLlaveAcceso, String opcEstadoRegistro, Integer orden, Set<SegPermiso> segPermisos) {
       this.opcCodigo = opcCodigo;
       this.segUsuario = segUsuario;
       this.segGrupoOpcion = segGrupoOpcion;
       this.opcNombre = opcNombre;
       this.opcDescripcion = opcDescripcion;
       this.opcLlaveAcceso = opcLlaveAcceso;
       this.opcEstadoRegistro = opcEstadoRegistro;
       this.orden = orden;
       this.segPermisos = segPermisos;
    }
   
    public Long getOpcCodigo() {
        return this.opcCodigo;
    }
    
    public void setOpcCodigo(Long opcCodigo) {
        this.opcCodigo = opcCodigo;
    }
    public SegUsuario getSegUsuario() {
        return this.segUsuario;
    }
    
    public void setSegUsuario(SegUsuario segUsuario) {
        this.segUsuario = segUsuario;
    }
    public SegGrupoOpcion getSegGrupoOpcion() {
        return this.segGrupoOpcion;
    }
    
    public void setSegGrupoOpcion(SegGrupoOpcion segGrupoOpcion) {
        this.segGrupoOpcion = segGrupoOpcion;
    }
    public String getOpcNombre() {
        return this.opcNombre;
    }
    
    public void setOpcNombre(String opcNombre) {
        this.opcNombre = opcNombre;
    }
    public String getOpcDescripcion() {
        return this.opcDescripcion;
    }
    
    public void setOpcDescripcion(String opcDescripcion) {
        this.opcDescripcion = opcDescripcion;
    }
    public String getOpcLlaveAcceso() {
        return this.opcLlaveAcceso;
    }
    
    public void setOpcLlaveAcceso(String opcLlaveAcceso) {
        this.opcLlaveAcceso = opcLlaveAcceso;
    }
    public String getOpcEstadoRegistro() {
        return this.opcEstadoRegistro;
    }
    
    public void setOpcEstadoRegistro(String opcEstadoRegistro) {
        this.opcEstadoRegistro = opcEstadoRegistro;
    }
    public Set<SegPermiso> getSegPermisos() {
        return this.segPermisos;
    }
    
    public void setSegPermisos(Set<SegPermiso> segPermisos) {
        this.segPermisos = segPermisos;
    }
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
    
}


