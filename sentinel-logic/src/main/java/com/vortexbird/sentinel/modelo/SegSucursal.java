package com.vortexbird.sentinel.modelo;
// Generated Oct 15, 2016 4:43:12 PM by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * SegSucursal generated by hbm2java
 */
public class SegSucursal  implements java.io.Serializable {


     private Long sucCodigo;
     private SegCompania segCompania;
     private SegUsuario segUsuario;
     private String sucCodigoInterno;
     private String sucNombre;
     private String sucEstadoRegistro;
     private Set<SegPermiso> segPermisos = new HashSet<SegPermiso>(0);

    public SegSucursal() {
    }

	
    public SegSucursal(Long sucCodigo, SegCompania segCompania, String sucCodigoInterno, String sucNombre, String sucEstadoRegistro) {
        this.sucCodigo = sucCodigo;
        this.segCompania = segCompania;
        this.sucCodigoInterno = sucCodigoInterno;
        this.sucNombre = sucNombre;
        this.sucEstadoRegistro = sucEstadoRegistro;
    }
    public SegSucursal(Long sucCodigo, SegCompania segCompania, SegUsuario segUsuario, String sucCodigoInterno, String sucNombre, String sucEstadoRegistro, Set<SegPermiso> segPermisos) {
       this.sucCodigo = sucCodigo;
       this.segCompania = segCompania;
       this.segUsuario = segUsuario;
       this.sucCodigoInterno = sucCodigoInterno;
       this.sucNombre = sucNombre;
       this.sucEstadoRegistro = sucEstadoRegistro;
       this.segPermisos = segPermisos;
    }
   
    public Long getSucCodigo() {
        return this.sucCodigo;
    }
    
    public void setSucCodigo(Long sucCodigo) {
        this.sucCodigo = sucCodigo;
    }
    public SegCompania getSegCompania() {
        return this.segCompania;
    }
    
    public void setSegCompania(SegCompania segCompania) {
        this.segCompania = segCompania;
    }
    public SegUsuario getSegUsuario() {
        return this.segUsuario;
    }
    
    public void setSegUsuario(SegUsuario segUsuario) {
        this.segUsuario = segUsuario;
    }
    public String getSucCodigoInterno() {
        return this.sucCodigoInterno;
    }
    
    public void setSucCodigoInterno(String sucCodigoInterno) {
        this.sucCodigoInterno = sucCodigoInterno;
    }
    public String getSucNombre() {
        return this.sucNombre;
    }
    
    public void setSucNombre(String sucNombre) {
        this.sucNombre = sucNombre;
    }
    public String getSucEstadoRegistro() {
        return this.sucEstadoRegistro;
    }
    
    public void setSucEstadoRegistro(String sucEstadoRegistro) {
        this.sucEstadoRegistro = sucEstadoRegistro;
    }
    public Set<SegPermiso> getSegPermisos() {
        return this.segPermisos;
    }
    
    public void setSegPermisos(Set<SegPermiso> segPermisos) {
        this.segPermisos = segPermisos;
    }




}


