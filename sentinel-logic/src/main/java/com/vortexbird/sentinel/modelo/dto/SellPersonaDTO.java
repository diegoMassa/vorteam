package com.vortexbird.sentinel.modelo.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;





/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public class SellPersonaDTO implements Serializable {
    
	private static final long serialVersionUID = 1L;
    
	private Long operCreador;
    private Long operModifica;
    private Integer persId;
    private Integer ciudId_SellCiudad;
    private Integer tidoId_SellTipoDocumento;
    private Integer idSucursal;
    private Integer asesorId;
    private Integer idSucuAnterior;    
    private String celular;
    private String direccion;
    private String email;
    private String estadoRegistro;
    private String estadoRegistroDescripcion;
    private String fechaNacimientoString;
    private String genero;
    private String numeroDocumento;
    private String primerApellido;
    private String primerNombre;
    private String razonSocial;
    private String segundoApellido;
    private String segundoNombre;
    private String tieneWhatsapp;    
    private String nombreMostrar;
    private String fechaIngresoString;    
    private String descripcionSucursal;
    private String nombreTipoDocumento;
    private String codigoSucursal;
    private String codigoCiudad;
    private String nombreCiudad;
    private String nombreDepartamento;
    private String nombreSubzona;
    private String nombreZona;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Date fechaNacimiento;        
    private Date fechaIngreso;
        
    private SimpleDateFormat simple = new SimpleDateFormat("yyyy/MM/dd");
    
    //nuevos campos
    private String descripcionCiudad;
    private String descripcionDepartamento;
    private String descripcionSubZona;
    private String descripcionZona;
    
    
    //Administradores
    private String tipoAdministrador;
    private List<SellZonaDTO> zonasDeAdministrador;
    private List<SellSucursalDTO> sucursalesDeAdministrador;
    
    private boolean esAdministradorDeSucursal;
    private String urlDespliegue;
    private String nombreCompleto;
    
    private List<SellSucursalDTO> sucursalesDeAsesor;
    private List<Integer> sucursalesDeAsesorIds;
    private List<SellSucursalDTO> listSellSucursalDTO;
    private List<SellSucursalDTO> sucursalDTOWS;
    
    private String[] sucursalesSeleccionadas;
    
    private String[] somRolesSellou;
	private String[] somRolesVCloud;
	private String[] somRolesCRM;
    
    
    public SellPersonaDTO() {
    	super();
    }
        
    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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

    public Integer getPersId() {
        return persId;
    }

    public void setPersId(Integer persId) {
        this.persId = persId;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getTieneWhatsapp() {
        return tieneWhatsapp;
    }

    public void setTieneWhatsapp(String tieneWhatsapp) {
        this.tieneWhatsapp = tieneWhatsapp;
    }

    public Integer getCiudId_SellCiudad() {
        return ciudId_SellCiudad;
    }

    public void setCiudId_SellCiudad(Integer ciudId_SellCiudad) {
        this.ciudId_SellCiudad = ciudId_SellCiudad;
    }

    public Integer getTidoId_SellTipoDocumento() {
        return tidoId_SellTipoDocumento;
    }

    public void setTidoId_SellTipoDocumento(Integer tidoId_SellTipoDocumento) {
        this.tidoId_SellTipoDocumento = tidoId_SellTipoDocumento;
    }

	public String getNombreMostrar() {
		return nombreMostrar;
	}

	public void setNombreMostrar(String nombreMostrar) {
		this.nombreMostrar = nombreMostrar;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getFechaNacimientoString() {
		if (fechaNacimiento != null) {
			fechaNacimientoString = simple.format(fechaNacimiento);
		}
		return fechaNacimientoString;
	}

	public void setFechaNacimientoString(String fechaNacimientoString) {
		this.fechaNacimientoString = fechaNacimientoString;
	}

	public String getFechaIngresoString() {
		if (fechaIngreso != null) {
			fechaIngresoString = simple.format(fechaIngreso);
		}
		return fechaIngresoString;
	}

	public void setFechaIngresoString(String fechaIngresoString) {
		this.fechaIngresoString = fechaIngresoString;
	}

	public SimpleDateFormat getSimple() {
		return simple;
	}

	public void setSimple(SimpleDateFormat simple) {
		this.simple = simple;
	}

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getDescripcionSucursal() {
		return descripcionSucursal;
	}

	public void setDescripcionSucursal(String descripcionSucursal) {
		this.descripcionSucursal = descripcionSucursal;
	}

	public Integer getAsesorId() {
		return asesorId;
	}

	public void setAsesorId(Integer asesorId) {
		this.asesorId = asesorId;
	}

	public String getEstadoRegistroDescripcion() {
//		if (estadoRegistro.equals(Constantes.ESTADO_ACTIVO)) {
//			estadoRegistroDescripcion = "Activo";
//		}else {
//			estadoRegistroDescripcion = "Inactivo";
//		}
		return estadoRegistroDescripcion;
	}

	public void setEstadoRegistroDescripcion(String estadoRegistroDescripcion) {
		this.estadoRegistroDescripcion = estadoRegistroDescripcion;
	}

	public String getDescripcionCiudad() {
		return descripcionCiudad;
	}

	public void setDescripcionCiudad(String descripcionCiudad) {
		this.descripcionCiudad = descripcionCiudad;
	}

	public String getDescripcionDepartamento() {
		return descripcionDepartamento;
	}

	public void setDescripcionDepartamento(String descripcionDepartamento) {
		this.descripcionDepartamento = descripcionDepartamento;
	}

	public String getDescripcionSubZona() {
		return descripcionSubZona;
	}

	public void setDescripcionSubZona(String descripcionSubZona) {
		this.descripcionSubZona = descripcionSubZona;
	}

	public String getDescripcionZona() {
		return descripcionZona;
	}

	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

	public List<SellZonaDTO> getZonasDeAdministrador() {
		return zonasDeAdministrador;
	}

	public void setZonasDeAdministrador(List<SellZonaDTO> zonasDeAdministrador) {
		this.zonasDeAdministrador = zonasDeAdministrador;
	}

	public List<SellSucursalDTO> getSucursalesDeAdministrador() {
		return sucursalesDeAdministrador;
	}

	public void setSucursalesDeAdministrador(
			List<SellSucursalDTO> sucursalesDeAdministrador) {
		this.sucursalesDeAdministrador = sucursalesDeAdministrador;
	}

	public String getTipoAdministrador() {
		return tipoAdministrador;
	}

	public void setTipoAdministrador(String tipoAdministrador) {
		this.tipoAdministrador = tipoAdministrador;
	}

	public boolean isEsAdministradorDeSucursal() {
		return esAdministradorDeSucursal;
	}

	public void setEsAdministradorDeSucursal(boolean esAdministradorDeSucursal) {
		this.esAdministradorDeSucursal = esAdministradorDeSucursal;
	}

	public String getUrlDespliegue() {
		return urlDespliegue;
	}

	public void setUrlDespliegue(String urlDespliegue) {
		this.urlDespliegue = urlDespliegue;
	}

	public Integer getIdSucuAnterior() {
		return idSucuAnterior;
	}

	public void setIdSucuAnterior(Integer idSucuAnterior) {
		this.idSucuAnterior = idSucuAnterior;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}



	public List<SellSucursalDTO> getListSellSucursalDTO() {
		return listSellSucursalDTO;
	}

	public void setListSellSucursalDTO(List<SellSucursalDTO> listSellSucursalDTO) {
		this.listSellSucursalDTO = listSellSucursalDTO;
	}

	public List<Integer> getSucursalesDeAsesorIds() {
		return sucursalesDeAsesorIds;
	}

	public void setSucursalesDeAsesorIds(List<Integer> sucursalesDeAsesorIds) {
		this.sucursalesDeAsesorIds = sucursalesDeAsesorIds;
	}

	public String getNombreTipoDocumento() {
		return nombreTipoDocumento;
	}

	public void setNombreTipoDocumento(String nombreTipoDocumento) {
		this.nombreTipoDocumento = nombreTipoDocumento;
	}

	public String getCodigoSucursal() {
		return codigoSucursal;
	}

	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	public String getCodigoCiudad() {
		return codigoCiudad;
	}

	public void setCodigoCiudad(String codigoCiudad) {
		this.codigoCiudad = codigoCiudad;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public String getNombreDepartamento() {
		return nombreDepartamento;
	}

	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}

	public String getNombreSubzona() {
		return nombreSubzona;
	}

	public void setNombreSubzona(String nombreSubzona) {
		this.nombreSubzona = nombreSubzona;
	}

	public String getNombreZona() {
		return nombreZona;
	}

	public void setNombreZona(String nombreZona) {
		this.nombreZona = nombreZona;
	}

	public List<SellSucursalDTO> getSucursalesDeAsesor() {
		return sucursalesDeAsesor;
	}

	public void setSucursalesDeAsesor(List<SellSucursalDTO> sucursalesDeAsesor) {
		this.sucursalesDeAsesor = sucursalesDeAsesor;
	}

	public List<SellSucursalDTO> getSucursalDTOWS() {
		return sucursalDTOWS;
	}

	public void setSucursalDTOWS(List<SellSucursalDTO> sucursalDTOWS) {
		this.sucursalDTOWS = sucursalDTOWS;
	}

	public String[] getSomRolesSellou() {
		return somRolesSellou;
	}

	public void setSomRolesSellou(String[] somRolesSellou) {
		this.somRolesSellou = somRolesSellou;
	}

	public String[] getSomRolesVCloud() {
		return somRolesVCloud;
	}

	public void setSomRolesVCloud(String[] somRolesVCloud) {
		this.somRolesVCloud = somRolesVCloud;
	}

	public String[] getSomRolesCRM() {
		return somRolesCRM;
	}

	public void setSomRolesCRM(String[] somRolesCRM) {
		this.somRolesCRM = somRolesCRM;
	}

	public String[] getSucursalesSeleccionadas() {
		return sucursalesSeleccionadas;
	}

	public void setSucursalesSeleccionadas(String[] sucursalesSeleccionadas) {
		this.sucursalesSeleccionadas = sucursalesSeleccionadas;
	}

	
	
	
}
