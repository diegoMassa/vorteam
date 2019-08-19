package com.vortexbird.sentinel.rest.cliente;

import java.util.List;

import javax.xml.namespace.QName;

import com.vortexbird.sentinel.modelo.dto.RollCrmDTO;
import com.vortexbird.sentinel.modelo.dto.SellCiudadDTO;
import com.vortexbird.sentinel.modelo.dto.SellPersonaDTO;
import com.vortexbird.sentinel.modelo.dto.SellSucursalDTO;
import com.vortexbird.sentinel.modelo.dto.UsuarioCRMDTO;;

public interface ISentinelRestServiceClient {

	public abstract List<RollCrmDTO> consultarRollesCRM() throws Exception;

	public abstract List<RollCrmDTO> consultarRollXUsuarioCRM(String cedula) throws Exception;

	public abstract SellSucursalDTO ConsultarTodasLasSucursales() throws Exception;

	public abstract SellPersonaDTO consultarPersonaByCedula(String string) throws Exception;

	public abstract SellCiudadDTO consultarCiudades() throws Exception;

	public abstract void guardarAsesor(SellPersonaDTO personaDTO) throws Exception;

	public abstract void crearUsuarioVCloud(UsuarioCRMDTO crmUser, String somRolesCRM, QName serviceName,
			String[] sucursalesSeleccionadas, String url, long puntoDeventa, String userVcloud, String passVcloud)
			throws Exception;

	public abstract void actualizarUsuarioVCloud(UsuarioCRMDTO crmUser, String somRolesCRM, QName serviceName,
			String[] sucursalesSeleccionadas, String url, long puntoDeventa, String userVcloud, String passVcloud)
			throws Exception;

	public abstract String consultarUsuarioVCloud(UsuarioCRMDTO crmUser, String somRolesCRM, QName SERVICE_NAME,
			String[] sucursalesSeleccionadas, String url, long puntoDeVenta, String user, String pass) throws Exception;

	public abstract long getSucuXPuntoVenta(String[] sucursalesSeleccionadas) throws Exception;

	public abstract void modificarUsuarioCRM(UsuarioCRMDTO usuarios, String somRolesCRM) throws Exception;

	public abstract void crearUsuarioCRM(UsuarioCRMDTO crmUser, String somRolesCRM) throws Exception;

	public abstract List<RollCrmDTO> consultarRolesCRM(String cedula) throws Exception;

}