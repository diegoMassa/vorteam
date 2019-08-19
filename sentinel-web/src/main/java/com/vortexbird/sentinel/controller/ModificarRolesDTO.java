package com.vortexbird.sentinel.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.vortexbird.sentinel.modelo.dto.SegRolDTO;

/**
 * Clase parametro de los servicios REST de tipo POST : 1. /adicionarRolesUsuario 2. /eliminarRolesUsuario
 * 
 * @param usuCodigo		Usuario a adicionar/eliminar la lista de roles.
 * @param listaRoles	Lista de roles a adicionar/eliminar.
 * @param sistema		Codigo del sistema.
 * @param compania		Codigo de la compania.
 * @param usuSession	Usuario modificador.
 * @author Andrés Felipe Vargas López
 * @version Jun 21, 2018
 * @since 1.8
 *
 */
public class ModificarRolesDTO {
	
	private Long usuCodigo;
	private List<SegRolDTO> listaRoles;
	private Long sistema;
	private Long compania;
	private Long usuSession;
	
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 21, 2018
	 * @since 1.8
	 * @return El/La usuCodigo
	 *
	 */
	public Long getUsuCodigo() {
		return usuCodigo;
	}
	/**
	 *
	 * @param usuCodigo El/La usuCodigo a setear
	 * @author Andrés Felipe Vargas López
	 * @version Jun 21, 2018
	 * @since 1.8
	 *
	 */
	public void setUsuCodigo(Long usuCodigo) {
		this.usuCodigo = usuCodigo;
	}
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 21, 2018
	 * @since 1.8
	 * @return El/La listaRoles
	 *
	 */
	public List<SegRolDTO> getListaRoles() {
		return listaRoles;
	}
	/**
	 *
	 * @param listaRoles El/La listaRoles a setear
	 * @author Andrés Felipe Vargas López
	 * @version Jun 21, 2018
	 * @since 1.8
	 *
	 */
	public void setListaRoles(List<SegRolDTO> listaRoles) {
		this.listaRoles = listaRoles;
	}
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 21, 2018
	 * @since 1.8
	 * @return El/La sistema
	 *
	 */
	public Long getSistema() {
		return sistema;
	}
	/**
	 *
	 * @param sistema El/La sistema a setear
	 * @author Andrés Felipe Vargas López
	 * @version Jun 21, 2018
	 * @since 1.8
	 *
	 */
	public void setSistema(Long sistema) {
		this.sistema = sistema;
	}
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 21, 2018
	 * @since 1.8
	 * @return El/La compania
	 *
	 */
	public Long getCompania() {
		return compania;
	}
	/**
	 *
	 * @param compania El/La compania a setear
	 * @author Andrés Felipe Vargas López
	 * @version Jun 21, 2018
	 * @since 1.8
	 *
	 */
	public void setCompania(Long compania) {
		this.compania = compania;
	}
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 21, 2018
	 * @since 1.8
	 * @return El/La usuSession
	 *
	 */
	public Long getUsuSession() {
		return usuSession;
	}
	/**
	 *
	 * @param usuSession El/La usuSession a setear
	 * @author Andrés Felipe Vargas López
	 * @version Jun 21, 2018
	 * @since 1.8
	 *
	 */
	public void setUsuSession(Long usuSession) {
		this.usuSession = usuSession;
	}
	
//	public static void main(String[] args) {
//
//		List<SegRolDTO> listaRoles = new ArrayList<>();
//		SegRolDTO rol = new SegRolDTO();
//		rol.setRolCodigo(15L);
//		listaRoles.add(rol);
//		
//		ModificarRolesDTO modificarRolesDTO = new ModificarRolesDTO();
//		
//		modificarRolesDTO.setUsuCodigo(38L);
//		modificarRolesDTO.setSistema(8L);
//		modificarRolesDTO.setCompania(1L);
//		modificarRolesDTO.setUsuSession(0L);
//		modificarRolesDTO.setListaRoles(listaRoles);
//
//		Gson gson = new Gson();
//		System.out.println(gson.toJson(modificarRolesDTO));
//
//	}
	
}
