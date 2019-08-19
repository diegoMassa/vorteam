package com.vortexbird.sentinel.controller;

import com.google.gson.Gson;

/**
 * Clase parametro para el servicio /crearUsuarioConRolesYPermisos
 * @author Andrés Felipe Vargas López
 * @version Jun 19, 2018
 * @since 1.8
 * @param usuCodigo							Codigo del usuario a actualizar.
 * @param rolCodigoActual					Codigo del rol actual.
 * @param rolCodigoNuevo					Codigo del rol a asignar.
 * @param compania							Codigo de la compania	
 * @param sistema							Codigo del sistema
 */
public class ActualizarUsuarioDTO {
	
	private Long usuCodigo; 
	private Long rolCodigoActual; 
	private Long rolCodigoNuevo;
	private Long sistema; 
	private Long compania;
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
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
	 * @version Jun 19, 2018
	 * @since 1.8
	 *
	 */
	public void setUsuCodigo(Long usuCodigo) {
		this.usuCodigo = usuCodigo;
	}
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @return El/La rolCodigoActual
	 *
	 */
	public Long getRolCodigoActual() {
		return rolCodigoActual;
	}
	/**
	 *
	 * @param rolCodigoActual El/La rolCodigoActual a setear
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 *
	 */
	public void setRolCodigoActual(Long rolCodigoActual) {
		this.rolCodigoActual = rolCodigoActual;
	}
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @return El/La rolCodigoNuevo
	 *
	 */
	public Long getRolCodigoNuevo() {
		return rolCodigoNuevo;
	}
	/**
	 *
	 * @param rolCodigoNuevo El/La rolCodigoNuevo a setear
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 *
	 */
	public void setRolCodigoNuevo(Long rolCodigoNuevo) {
		this.rolCodigoNuevo = rolCodigoNuevo;
	}
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
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
	 * @version Jun 19, 2018
	 * @since 1.8
	 *
	 */
	public void setSistema(Long sistema) {
		this.sistema = sistema;
	}
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
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
	 * @version Jun 19, 2018
	 * @since 1.8
	 *
	 */
	public void setCompania(Long compania) {
		this.compania = compania;
	}
	
//	public static void main(String[] args) {
//
//
//		ActualizarUsuarioDTO actualizarUsuarioDTO = new ActualizarUsuarioDTO();
//		
//		actualizarUsuarioDTO.setUsuCodigo(38L);
//		actualizarUsuarioDTO.setRolCodigoActual(15L);
//		actualizarUsuarioDTO.setRolCodigoNuevo(30L);
//		actualizarUsuarioDTO.setSistema(8L);
//		actualizarUsuarioDTO.setCompania(1L);
//
//		Gson gson = new Gson();
//		System.out.println(gson.toJson(actualizarUsuarioDTO));
//
//	}
	

}
