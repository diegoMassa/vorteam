package com.vortexbird.sentinel.controller;

import java.util.List;

import com.vortexbird.sentinel.modelo.dto.SegRolDTO;
/**
 * Clase parametro para el servicio /crearUsuarioConRolesYPermisos
 * @author Andrés Felipe Vargas López
 * @version Jun 19, 2018
 * @since 1.8
 * @param usuApellidos						Apellidos del usuario.
 * @param usuCodigoInterno					Dominio al que pertenece el usuario.
 * @param usuConstrasena					Clave asignada al usuario.
 * @param usuCorreo							Email del usuario.
 * @param usuLogin							Login asignado al usuario.
 * @param usuNombres						Nombre del usuario.
 * @param rolesAsignados					Lista de roles a ser asignados(<b>{@code List<SegRolDTO>}</b>)
 * @param compania							Codigo de la compania	
 * @param sistema							Codigo del sistema
 */
public class CrearUsuarioDTO {

	private String usuApellidos; 
	private String usuCodigoInterno;
	private String usuConstrasena; 
	private String usuCorreo; 
	private String usuLogin; 
	private String usuNombres;
	private List<SegRolDTO> rolesAsignados; 
	private String compania; 
	private String sistema;
	
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @return El/La usuApellidos
	 *
	 */
	public String getUsuApellidos() {
		return usuApellidos;
	}
	/**
	 *
	 * @param usuApellidos El/La usuApellidos a setear
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 *
	 */
	public void setUsuApellidos(String usuApellidos) {
		this.usuApellidos = usuApellidos;
	}
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @return El/La usuCodigoInterno
	 *
	 */
	public String getUsuCodigoInterno() {
		return usuCodigoInterno;
	}
	/**
	 *
	 * @param usuCodigoInterno El/La usuCodigoInterno a setear
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 *
	 */
	public void setUsuCodigoInterno(String usuCodigoInterno) {
		this.usuCodigoInterno = usuCodigoInterno;
	}
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @return El/La usuConstrasena
	 *
	 */
	public String getUsuConstrasena() {
		return usuConstrasena;
	}
	/**
	 *
	 * @param usuConstrasena El/La usuConstrasena a setear
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 *
	 */
	public void setUsuConstrasena(String usuConstrasena) {
		this.usuConstrasena = usuConstrasena;
	}
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @return El/La usuCorreo
	 *
	 */
	public String getUsuCorreo() {
		return usuCorreo;
	}
	/**
	 *
	 * @param usuCorreo El/La usuCorreo a setear
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 *
	 */
	public void setUsuCorreo(String usuCorreo) {
		this.usuCorreo = usuCorreo;
	}
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @return El/La usuLogin
	 *
	 */
	public String getUsuLogin() {
		return usuLogin;
	}
	/**
	 *
	 * @param usuLogin El/La usuLogin a setear
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 *
	 */
	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @return El/La usuNombres
	 *
	 */
	public String getUsuNombres() {
		return usuNombres;
	}
	/**
	 *
	 * @param usuNombres El/La usuNombres a setear
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 *
	 */
	public void setUsuNombres(String usuNombres) {
		this.usuNombres = usuNombres;
	}
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @return El/La rolesAsignados
	 *
	 */
	public List<SegRolDTO> getRolesAsignados() {
		return rolesAsignados;
	}
	/**
	 *
	 * @param rolesAsignados El/La rolesAsignados a setear
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 *
	 */
	public void setRolesAsignados(List<SegRolDTO> rolesAsignados) {
		this.rolesAsignados = rolesAsignados;
	}
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @return El/La compania
	 *
	 */
	public String getCompania() {
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
	public void setCompania(String compania) {
		this.compania = compania;
	}
	/**
	 *
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @return El/La sistema
	 *
	 */
	public String getSistema() {
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
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	
//	public static void main(String[] args) {
//	
//	List<SegRolDTO> roles = new ArrayList<>();
//	SegRolDTO rol = new SegRolDTO();
//	rol.setRolCodigo(15L);
//	roles.add(rol);
//	
//	CrearUsuarioDTO usuario = new CrearUsuarioDTO();
//	usuario.setUsuApellidos("Vargas Lopez");
//	usuario.setUsuCodigoInterno("1");
//	usuario.setUsuConstrasena("123456");
//	usuario.setUsuCorreo("avargas@vortexbird.com");
//	usuario.setUsuLogin("AV1144");
//	usuario.setUsuNombres("Andres Felipe");
//	usuario.setCompania("1");
//	usuario.setSistema("8");
//	usuario.setRolesAsignados(roles);
//	
//	
//	Gson gson = new Gson();
//	System.out.println(gson.toJson(usuario));
//	
//}
	
}
