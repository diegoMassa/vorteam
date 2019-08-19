package com.vortexbird.sentinel.modelo;
// Generated Oct 15, 2016 4:43:12 PM by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * SegCompania generated by hbm2java
 */
public class SegCompania  implements java.io.Serializable {


     private Long ciaCodigo;
     private SegUsuario segUsuario;
     private String ciaCodigoInterno;
     private String ciaNombre;
     private String ciaEstadoRegistro;
     private Set<SegSistemaCia> segSistemaCias = new HashSet<SegSistemaCia>(0);
     private Set<SegSucursal> segSucursals = new HashSet<SegSucursal>(0);

    public SegCompania() {
    }

	
    public SegCompania(Long ciaCodigo, String ciaCodigoInterno, String ciaNombre, String ciaEstadoRegistro) {
        this.ciaCodigo = ciaCodigo;
        this.ciaCodigoInterno = ciaCodigoInterno;
        this.ciaNombre = ciaNombre;
        this.ciaEstadoRegistro = ciaEstadoRegistro;
    }
    public SegCompania(Long ciaCodigo, SegUsuario segUsuario, String ciaCodigoInterno, String ciaNombre, String ciaEstadoRegistro, Set<SegSistemaCia> segSistemaCias, Set<SegSucursal> segSucursals) {
       this.ciaCodigo = ciaCodigo;
       this.segUsuario = segUsuario;
       this.ciaCodigoInterno = ciaCodigoInterno;
       this.ciaNombre = ciaNombre;
       this.ciaEstadoRegistro = ciaEstadoRegistro;
       this.segSistemaCias = segSistemaCias;
       this.segSucursals = segSucursals;
    }
   
    public Long getCiaCodigo() {
        return this.ciaCodigo;
    }
    
    public void setCiaCodigo(Long ciaCodigo) {
        this.ciaCodigo = ciaCodigo;
    }
    public SegUsuario getSegUsuario() {
        return this.segUsuario;
    }
    
    public void setSegUsuario(SegUsuario segUsuario) {
        this.segUsuario = segUsuario;
    }
    public String getCiaCodigoInterno() {
        return this.ciaCodigoInterno;
    }
    
    public void setCiaCodigoInterno(String ciaCodigoInterno) {
        this.ciaCodigoInterno = ciaCodigoInterno;
    }
    public String getCiaNombre() {
        return this.ciaNombre;
    }
    
    public void setCiaNombre(String ciaNombre) {
        this.ciaNombre = ciaNombre;
    }
    public String getCiaEstadoRegistro() {
        return this.ciaEstadoRegistro;
    }
    
    public void setCiaEstadoRegistro(String ciaEstadoRegistro) {
        this.ciaEstadoRegistro = ciaEstadoRegistro;
    }
    public Set<SegSistemaCia> getSegSistemaCias() {
        return this.segSistemaCias;
    }
    
    public void setSegSistemaCias(Set<SegSistemaCia> segSistemaCias) {
        this.segSistemaCias = segSistemaCias;
    }
    public Set<SegSucursal> getSegSucursals() {
        return this.segSucursals;
    }
    
    public void setSegSucursals(Set<SegSucursal> segSucursals) {
        this.segSucursals = segSucursals;
    }




}


