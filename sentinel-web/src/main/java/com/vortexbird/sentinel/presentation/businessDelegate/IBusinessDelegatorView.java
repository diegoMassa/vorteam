package com.vortexbird.sentinel.presentation.businessDelegate;

import java.util.Date;
import java.util.List;

import com.vortexbird.sentinel.modelo.SegAuditoria;
import com.vortexbird.sentinel.modelo.SegCambioPass;
import com.vortexbird.sentinel.modelo.SegCompania;
import com.vortexbird.sentinel.modelo.SegGrupoOpcion;
import com.vortexbird.sentinel.modelo.SegHistorialConstrasena;
import com.vortexbird.sentinel.modelo.SegOpcion;
import com.vortexbird.sentinel.modelo.SegParametro;
import com.vortexbird.sentinel.modelo.SegPermiso;
import com.vortexbird.sentinel.modelo.SegRol;
import com.vortexbird.sentinel.modelo.SegRolUsuario;
import com.vortexbird.sentinel.modelo.SegSistema;
import com.vortexbird.sentinel.modelo.SegSistemaCia;
import com.vortexbird.sentinel.modelo.SegSucursal;
import com.vortexbird.sentinel.modelo.SegUsuario;
import com.vortexbird.sentinel.modelo.VinCamposParametrizables;
import com.vortexbird.sentinel.modelo.dto.GrupoDTO;
import com.vortexbird.sentinel.modelo.dto.SegAuditoriaDTO;
import com.vortexbird.sentinel.modelo.dto.SegCambioPassDTO;
import com.vortexbird.sentinel.modelo.dto.SegCompaniaDTO;
import com.vortexbird.sentinel.modelo.dto.SegGrupoOpcionDTO;
import com.vortexbird.sentinel.modelo.dto.SegHistorialConstrasenaDTO;
import com.vortexbird.sentinel.modelo.dto.SegOpcionDTO;
import com.vortexbird.sentinel.modelo.dto.SegParametroDTO;
import com.vortexbird.sentinel.modelo.dto.SegPermisoDTO;
import com.vortexbird.sentinel.modelo.dto.SegRolDTO;
import com.vortexbird.sentinel.modelo.dto.SegRolUsuarioDTO;
import com.vortexbird.sentinel.modelo.dto.SegSistemaCiaDTO;
import com.vortexbird.sentinel.modelo.dto.SegSistemaDTO;
import com.vortexbird.sentinel.modelo.dto.SegSucursalDTO;
import com.vortexbird.sentinel.modelo.dto.SegUsuarioDTO;
import com.vortexbird.sentinel.modelo.dto.SellPersonaDTO;
import com.vortexbird.sentinel.modelo.dto.UsuarioDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IBusinessDelegatorView {
    public List<SegAuditoria> getSegAuditoria() throws Exception;

    public void saveSegAuditoria(SegAuditoria entity) throws Exception;

    public void deleteSegAuditoria(SegAuditoria entity)
        throws Exception;

    public void updateSegAuditoria(SegAuditoria entity)
        throws Exception;

    public SegAuditoria getSegAuditoria(Long autCodigo)
        throws Exception;

    public List<SegAuditoria> findByCriteriaInSegAuditoria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegAuditoria> findPageSegAuditoria(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegAuditoria() throws Exception;

    public List<SegAuditoriaDTO> getDataSegAuditoria()
        throws Exception;

    public List<SegCambioPass> getSegCambioPass() throws Exception;

    public void saveSegCambioPass(SegCambioPass entity)
        throws Exception;

    public void deleteSegCambioPass(SegCambioPass entity)
        throws Exception;

    public void updateSegCambioPass(SegCambioPass entity)
        throws Exception;

    public SegCambioPass getSegCambioPass(Long capCodigo)
        throws Exception;

    public List<SegCambioPass> findByCriteriaInSegCambioPass(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<SegCambioPass> findPageSegCambioPass(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegCambioPass() throws Exception;

    public List<SegCambioPassDTO> getDataSegCambioPass()
        throws Exception;

    public List<SegCompania> getSegCompania() throws Exception;

    public void saveSegCompania(SegCompania entity) throws Exception;

    public void deleteSegCompania(SegCompania entity) throws Exception;

    public void updateSegCompania(SegCompania entity) throws Exception;

    public SegCompania getSegCompania(Long ciaCodigo) throws Exception;

    public List<SegCompania> findByCriteriaInSegCompania(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegCompania> findPageSegCompania(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegCompania() throws Exception;

    public List<SegCompaniaDTO> getDataSegCompania() throws Exception;

    public List<SegGrupoOpcion> getSegGrupoOpcion() throws Exception;

    public void saveSegGrupoOpcion(SegGrupoOpcion entity)
        throws Exception;

    public void deleteSegGrupoOpcion(SegGrupoOpcion entity)
        throws Exception;

    public void updateSegGrupoOpcion(SegGrupoOpcion entity)
        throws Exception;

    public SegGrupoOpcion getSegGrupoOpcion(Long gruCodigo)
        throws Exception;

    public List<SegGrupoOpcion> findByCriteriaInSegGrupoOpcion(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<SegGrupoOpcion> findPageSegGrupoOpcion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegGrupoOpcion() throws Exception;

    public List<SegGrupoOpcionDTO> getDataSegGrupoOpcion()
        throws Exception;

    public List<SegHistorialConstrasena> getSegHistorialConstrasena()
        throws Exception;

    public void saveSegHistorialConstrasena(SegHistorialConstrasena entity)
        throws Exception;

    public void deleteSegHistorialConstrasena(SegHistorialConstrasena entity)
        throws Exception;

    public void updateSegHistorialConstrasena(SegHistorialConstrasena entity)
        throws Exception;

    public SegHistorialConstrasena getSegHistorialConstrasena(Long hcoCodigo)
        throws Exception;

    public List<SegHistorialConstrasena> findByCriteriaInSegHistorialConstrasena(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<SegHistorialConstrasena> findPageSegHistorialConstrasena(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberSegHistorialConstrasena()
        throws Exception;

    public List<SegHistorialConstrasenaDTO> getDataSegHistorialConstrasena()
        throws Exception;

    public List<SegOpcion> getSegOpcion() throws Exception;

    public void saveSegOpcion(SegOpcion entity) throws Exception;

    public void deleteSegOpcion(SegOpcion entity) throws Exception;

    public void updateSegOpcion(SegOpcion entity) throws Exception;

    public SegOpcion getSegOpcion(Long opcCodigo) throws Exception;

    public List<SegOpcion> findByCriteriaInSegOpcion(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegOpcion> findPageSegOpcion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegOpcion() throws Exception;

    public List<SegOpcionDTO> getDataSegOpcion() throws Exception;

    public List<SegParametro> getSegParametro() throws Exception;

    public void saveSegParametro(SegParametro entity) throws Exception;

    public void deleteSegParametro(SegParametro entity)
        throws Exception;

    public void updateSegParametro(SegParametro entity)
        throws Exception;

    public SegParametro getSegParametro(Long parCodigo)
        throws Exception;

    public List<SegParametro> findByCriteriaInSegParametro(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegParametro> findPageSegParametro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegParametro() throws Exception;

    public List<SegParametroDTO> getDataSegParametro()
        throws Exception;

    public List<SegPermiso> getSegPermiso() throws Exception;

    public void saveSegPermiso(SegPermiso entity) throws Exception;

    public void deleteSegPermiso(SegPermiso entity) throws Exception;

    public void updateSegPermiso(SegPermiso entity) throws Exception;

    public SegPermiso getSegPermiso(Long perCodigo) throws Exception;

    public List<SegPermiso> findByCriteriaInSegPermiso(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegPermiso> findPageSegPermiso(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegPermiso() throws Exception;

    public List<SegPermisoDTO> getDataSegPermiso() throws Exception;

    public List<SegRol> getSegRol() throws Exception;

    public void saveSegRol(SegRol entity) throws Exception;

    public void deleteSegRol(SegRol entity) throws Exception;

    public void updateSegRol(SegRol entity) throws Exception;

    public SegRol getSegRol(Long rolCodigo) throws Exception;

    public List<SegRol> findByCriteriaInSegRol(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegRol> findPageSegRol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegRol() throws Exception;

    public List<SegRolDTO> getDataSegRol() throws Exception;

    public List<SegRolUsuario> getSegRolUsuario() throws Exception;

    public void saveSegRolUsuario(SegRolUsuario entity)
        throws Exception;

    public void deleteSegRolUsuario(SegRolUsuario entity)
        throws Exception;

    public void updateSegRolUsuario(SegRolUsuario entity)
        throws Exception;

    public SegRolUsuario getSegRolUsuario(Long rluCodigo)
        throws Exception;

    public List<SegRolUsuario> findByCriteriaInSegRolUsuario(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<SegRolUsuario> findPageSegRolUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegRolUsuario() throws Exception;

    public List<SegRolUsuarioDTO> getDataSegRolUsuario()
        throws Exception;

    public List<SegSistema> getSegSistema() throws Exception;

    public void saveSegSistema(SegSistema entity) throws Exception;

    public void deleteSegSistema(SegSistema entity) throws Exception;

    public void updateSegSistema(SegSistema entity) throws Exception;

    public SegSistema getSegSistema(Long sisCodigo) throws Exception;

    public List<SegSistema> findByCriteriaInSegSistema(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegSistema> findPageSegSistema(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegSistema() throws Exception;

    public List<SegSistemaDTO> getDataSegSistema() throws Exception;

    public List<SegSistemaCia> getSegSistemaCia() throws Exception;

    public void saveSegSistemaCia(SegSistemaCia entity)
        throws Exception;

    public void deleteSegSistemaCia(SegSistemaCia entity)
        throws Exception;

    public void updateSegSistemaCia(SegSistemaCia entity)
        throws Exception;

    public SegSistemaCia getSegSistemaCia(Long sicCodigo)
        throws Exception;

    public List<SegSistemaCia> findByCriteriaInSegSistemaCia(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<SegSistemaCia> findPageSegSistemaCia(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegSistemaCia() throws Exception;

    public List<SegSistemaCiaDTO> getDataSegSistemaCia()
        throws Exception;

    public List<SegSucursal> getSegSucursal() throws Exception;

    public void saveSegSucursal(SegSucursal entity) throws Exception;

    public void deleteSegSucursal(SegSucursal entity) throws Exception;

    public void updateSegSucursal(SegSucursal entity) throws Exception;

    public SegSucursal getSegSucursal(Long sucCodigo) throws Exception;

    public List<SegSucursal> findByCriteriaInSegSucursal(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegSucursal> findPageSegSucursal(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegSucursal() throws Exception;

    public List<SegSucursalDTO> getDataSegSucursal() throws Exception;

    public List<SegUsuario> getSegUsuario() throws Exception;

    public void saveSegUsuario(SegUsuario entity) throws Exception;

    public void deleteSegUsuario(SegUsuario entity) throws Exception;

    public void updateSegUsuario(SegUsuario entity) throws Exception;

    public SegUsuario getSegUsuario(Long usuCodigo) throws Exception;

    public List<SegUsuario> findByCriteriaInSegUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegUsuario> findPageSegUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegUsuario() throws Exception;

    public List<SegUsuarioDTO> getDataSegUsuario() throws Exception;

	public UsuarioDTO autenticar(String login, String pass, String dominio)
			throws Exception;

	public List<SegSistema> consultarSistemasDeUsuarioAdministrador(Long usuCodigo)
			throws Exception;

	public List<SegCompania> consultarCompaniasDeDeusuarioAdministrador(
			Long ucuCodigo, long sisCodigo) throws Exception;

	public List<GrupoDTO> consultarOpcionesDeUsuarioPorSistemaSucursalYCompania(
			String login, String sistema, String sucursal, String cia);

	public void guardarOpcion(String nombre, String descripcion, String llaveAcceso, Long gruCodigo, Long usuCreador, String estadoRegistro, Integer orden) throws Exception;

	public List<SegOpcionDTO> consultarOpcionesPorSistema(Long sisCodigo)
			throws Exception;

	public List<SegGrupoOpcion> consultarGrupoOpcionesPorSistema(Long sisCodigo)
			throws Exception;

	public void modificarOpcion(Long opcCodigo, String nombre, String descripcion,
			String llaveAcceso, Long gruCodigo, Long usuCreador,
			String estadoRegistro, Integer orden) throws Exception;

	public void guardarGrupoOpcion(Long gruCodigo, String gruDescripcion,
			String gruEstadoRegistro, String gruLlaveAcceso, String gruNombre,
			Long gruCodigo_SegGrupoOpcion, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario) throws Exception;

	public void modificarGrupoOpcion(Long gruCodigo, String gruDescripcion,
			String gruEstadoRegistro, String gruLlaveAcceso, String gruNombre,
			Long gruCodigo_SegGrupoOpcion, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario) throws Exception;

	public void guardarRol(String rolDescripcion, Long rolDiasCaducidadPwd,
			String rolEstadoRegistro, String rolNombre,
			Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario, String esAdmonDeSistema)
			throws Exception;

	public void modificarRol(Long rolCodigo, String rolDescripcion,
			Long rolDiasCaducidadPwd, String rolEstadoRegistro,
			String rolNombre, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario, String esAdmonDeSistema) throws Exception;

	public List<SegRolDTO> consultarRolesPorSistemaDTO(Long sisCodigo)
			throws Exception;
	
	public List<SegRol> consultarRolesPorSistema(Long sisCodigo)
			throws Exception;

	public List<SegRol> consultarRolesPorUsuario(Long usuCodigo) throws Exception;

	public SegUsuario guardarUsuarioConRoles(String usuApellidos, String usuCodigoInterno,
			String usuConstrasena, String usuCorreo, String usuEstadoRegistro,
			Long usuIntentosFallidos, Long ciaCodigo_SegCompania,
			String usuLogin, String usuNombres,
			Date usuUltmimaModificacionPass, Long usuSession,
			List<String> rolesAsignados, String compania, String sistema)
			throws Exception;

	public SegUsuario consultarUsuarioPorLogin(String login) throws Exception;

	public List<SegUsuario> consultarUsuariosPorSistemaCompania(Long sisCodigfo,
			Long ciaCodigo) throws Exception;

	public void modificarUsuarioConRoles(String usuApellidos, Long usuCodigo,
			String usuCodigoInterno, String usuConstrasena, String usuCorreo,
			String usuEstadoRegistro, Long usuIntentosFallidos, Long ciaCodigo_SegCompania,
			String usuLogin, String usuNombres,
			Date usuUltmimaModificacionPass, Long usuSession,
			List<String> rolesAsignados, String compania, String sistema) throws Exception;

	public void guardarPermiso(String perEstadoRegistro, Long sicCodigo_SegSistemaCia,
			Long gruCodigo_SegGrupoOpcion, Long opcCodigo_SegOpcion,
			Long rolCodigo_SegRol, Long sucCodigo_SegSucursal,
			Long usuCodigo_SegUsuario, Long usuCodigo_SegUsuario0)
			throws Exception;

	public List<SegUsuario> consultarUsuariosPorRol(Long rolCodigo) throws Exception;

	public List<SegCompania> consultarCompaniasPorRol(Long rolCodigo) throws Exception;

	public List<SegOpcion> consultarPermisosDeOpcionesAsignadosARolesYUsuarios(
			Long codigoRol, Long codigoCompania, String codigoUsuario)
			throws Exception;

	public List<SegGrupoOpcion> consultarGrupoOpcionesPorRol(Long rolCodigo)
			throws Exception;

	public List<SegOpcion> consultarOpcionesDeGrupoOpcion(Long gruCodigo)
			throws Exception;

	public List<SegPermiso> consultarPermisosDeOpcionesAsignadosARolesYUsuarios(Long rolCodigo,Long codigoCompania, Long codigoOpcion, String codigoUsuario) 
    		throws Exception;

	public void guardarPermisosParaRolOUsuarioANivelDeGruposUOpciones(Long rolCodigo,
			List<SegOpcion> listOpciones, Long codigoCompania,
			Long codigoSistema, String codigoUsuario) throws Exception;

	public SegSistema consultarSistemDeRol(Long rolCodigo) throws Exception;

	public List<SegSistema> consultarSistemasALosQueTieneAccesoElUsuario(Long usuCodigo)
			throws Exception;

	public VinCamposParametrizables getVinCamposParametrizables(String llave)
			throws Exception;

	public UsuarioDTO consultarPrimeraVez(String usu_login, String usu_password, String string) throws Exception;

	public boolean savePrimeraVez(UsuarioDTO usuario)throws Exception;

	public String generarNuevaContrasena(String usuLogin, String email) throws Exception;

	public void agregarRolles(SellPersonaDTO personaDTO)throws Exception;

	public void crearUsuario(SellPersonaDTO personaDTO)throws Exception;

	public List<VinCamposParametrizables> findByCriteriaInVinCamposParametrizables(Object[] variables, Object[] object,
			Object[] object2)throws Exception;

	public long getSucuXPuntoVenta(String[] sucursalesSeleccionadas)throws Exception;

	public void saveSegPermisoAllUser(boolean enCrm, boolean enVcloud, boolean enSellout, SellPersonaDTO personaDTO)throws Exception;
	
	/**
	 * Metodo encargado de consultar los roles de un sistema el cual hace parte
	 * de una compania.
	 * @author Andrés Felipe Vargas López
	 * @version Jun 18, 2018
	 * @since 1.8
	 * @param sistema		Codigo del sistema.
	 * @param compania		Codigo de la compania.
	 * @throws Exception
	 * @return <b>{@code List<SegRolDTO>}</b> Lista de roles pertenecientes al sistema y compania indicados.
	 *
	 */
	public List<SegRolDTO> consultarRolPorSistemaCompania(String sistema, String compania) throws Exception;
	
	/**
	 * Metodo encargado de consultar los roles para un usuario de un sistema el cual hace parte
	 * de una compania.
	 * @author Andrés Felipe Vargas López
	 * @version Jun 18, 2018
	 * @since 1.8
	 * @param sistema		Codigo del sistema.
	 * @param compania		Codigo de la compania.
	 * @param login			Login del Usuario
	 * @throws Exception
	 * @return <b>{@code List<SegRolDTO>}</b> Lista de roles del usuario pertenecientes al sistema y compania indicados.
	 *
	 */
	public List<SegRolDTO> consultarRolPorUsuarioSistemaCompania(String sistema, String compania, String login) throws Exception;
	
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
}
