package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.modelo.SegUsuario;
import com.vortexbird.sentinel.modelo.dto.SegRolDTO;
import com.vortexbird.sentinel.modelo.dto.SegUsuarioDTO;
import com.vortexbird.sentinel.modelo.dto.SellPersonaDTO;
import com.vortexbird.sentinel.modelo.dto.UsuarioDTO;


import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface ISegUsuarioLogic {
    public List<SegUsuario> getSegUsuario() throws Exception;

    /**
         * Save an new SegUsuario entity
         */
    public void saveSegUsuario(SegUsuario entity) throws Exception;

    /**
         * Delete an existing SegUsuario entity
         *
         */
    public void deleteSegUsuario(SegUsuario entity) throws Exception;

    /**
        * Update an existing SegUsuario entity
        *
        */
    public void updateSegUsuario(SegUsuario entity) throws Exception;

    /**
         * Load an existing SegUsuario entity
         *
         */
    public SegUsuario getSegUsuario(Long usuCodigo) throws Exception;

    public List<SegUsuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegUsuario> findPageSegUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegUsuario() throws Exception;

    public List<SegUsuarioDTO> getDataSegUsuario() throws Exception;

	public SegUsuario guardarUsuarioConRoles(String usuApellidos, String usuCodigoInterno,
			String usuConstrasena, String usuCorreo, String usuEstadoRegistro,
			Long usuIntentosFallidos, Long ciaCodigo_SegCompania,
			String usuLogin, String usuNombres,
			Date usuUltmimaModificacionPass, Long usuSession,
			List<String> rolesAsignados, String compania, String sistema) throws Exception;

	public SegUsuario consultarUsuarioPorLogin(String login) throws Exception;

	public List<SegUsuario> consultarUsuariosPorSistemaCompania(Long sisCodigfo,
			Long ciaCodigo) throws Exception;

	public void modificarUsuarioConRoles(String usuApellidos,
			Long usuCodigo, String usuCodigoInterno, String usuConstrasena,
			String usuCorreo, String usuEstadoRegistro,
			Long usuIntentosFallidos, Long ciaCodigo_SegCompania,
			String usuLogin, String usuNombres,
			Date usuUltmimaModificacionPass, Long usuSession,
			List<String> rolesAsignados, String compania, String sistema)
			throws Exception;

	public List<SegUsuario> consultarUsuariosPorRol(Long rolCodigo) throws Exception;

	public UsuarioDTO consultarUsuarioPorLogin(String login, String dominio,
			String codigoSistema);

	public UsuarioDTO consultarPrimeraVez(String usu_login, String usu_password, String dominio)throws Exception;

	public boolean savePrimeraVez(UsuarioDTO usuario)throws Exception;

	public String generarNuevaContrasena(String usuLogin, String email) throws Exception;
	
	public void crearUsuario(SellPersonaDTO personaDTO)throws Exception;

	public void agregarRolles(SellPersonaDTO personaDTO)throws Exception;
	
	/**
	 * Metodo que crea un usuario con roles y permisos.
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @param usuApellidos						Apellidos del usuario.
	 * @param usuCodigoInterno					Dominio al que pertenece el usuario.
	 * @param usuConstrasena					Clave asignada al usuario.
	 * @param usuCorreo							Email del usuario.
	 * @param usuEstadoRegistro					Estado del registro.
	 * @param usuIntentosFallidos				0 al momento de crear el usuario.
	 * @param ciaCodigo_SegCompania				Codigo del compania a la que pertenece el usuario.
	 * @param usuLogin							Login asignado al usuario.
	 * @param usuNombres						Nombre del usuario.
	 * @param usuUltmimaModificacionPass		Ultima fecha de modificacion del usuario. new Date() al momento de crearse.
	 * @param usuSession						Usuario modificador.
	 * @param rolesAsignados					Lista de roles a ser asignados(<b>{@code List<SegRolDTO>}</b>)
	 * @param compania							Codigo de la compania	
	 * @param sistema							Codigo del sistema
	 * @throws Exception
	 */
	public void crearUsuarioConRolesYPermisos(String usuApellidos, String usuCodigoInterno, String usuConstrasena,
			String usuCorreo, String usuEstadoRegistro, Long usuIntentosFallidos, Long ciaCodigo_SegCompania,
			String usuLogin, String usuNombres, Date usuUltmimaModificacionPass, Long usuSession,
			List<SegRolDTO> rolesAsignados, String compania, String sistema) throws Exception;
	
	/**
	 * Metodo encargado de cambiar el rol del usuario especificado.
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @param usuCodigo							Codigo del usuario
	 * @param rolCodigoActual					Codigo del rol actual
	 * @param rolCodigoNuevo					Codigo del rol a asignar
	 * @param compania							Codigo de la compania	
	 * @param sistema							Codigo del sistema
	 * @throws Exception
	 */
	public void actualizarRolesYPermisosDeUsuario(Long usuCodigo, Long rolCodigoActual, Long rolCodigoNuevo, Long sistema, Long compania) throws Exception;
	
	/**
	 * Metodo encargado de adicionar roles a un usuario, sin necesidad de duplicar registros.
	 * @author Andrés Felipe Vargas López
	 * @version Jun 21, 2018
	 * @since 1.8
	 * @param usuCodigo		Usuario a asignar la lista de roles.
	 * @param listaRoles	Lista de roles a asignar.
	 * @param sistema		Codigo del sistema.
	 * @param compania		Codigo de la compania.
	 * @param usuSession	Usuario modificador.
	 * @throws Exception
	 *
	 */
	public void adicionarRolPermisoAUsuario(Long usuCodigo, List<SegRolDTO> listaRoles, Long sistema, Long compania, Long usuSession) throws Exception;
	
	/**
	 * Metodo encargado de eliminar roles a un usuario.
	 * @author Andrés Felipe Vargas López
	 * @version Jun 21, 2018
	 * @since 1.8
	 * @param usuCodigo		Usuario a eliminar la lista de roles.
	 * @param listaRoles	Lista de roles a eliminar.
	 * @param sistema		Codigo del sistema.
	 * @param compania		Codigo de la compania.
	 * @param usuSession	Usuario modificador.
	 * @throws Exception
	 *
	 */
	public void eliminarRolPermisoAUsuario(Long usuCodigo, List<SegRolDTO> listaRoles, Long sistema, Long compania, Long usuSession) throws Exception;

	/**
	 * Método encargado de consulta la existencia de un usuario.
	 * @author Camilo José Delgado Herrera
	 * @version 2018-06-22
	 * @param login
	 * @param dominio
	 * @return
	 */
	public UsuarioDTO consultarUsuarioPorLogin(String login, String dominio);

}
