package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.modelo.SegRol;
import com.vortexbird.sentinel.modelo.dto.OpcionDTO;
import com.vortexbird.sentinel.modelo.dto.SegRolDTO;
import com.vortexbird.sentinel.modelo.dto.UsuarioDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface ISegRolLogic {
    public List<SegRol> getSegRol() throws Exception;

    /**
         * Save an new SegRol entity
         */
    public void saveSegRol(SegRol entity) throws Exception;

    /**
         * Delete an existing SegRol entity
         *
         */
    public void deleteSegRol(SegRol entity) throws Exception;

    /**
        * Update an existing SegRol entity
        *
        */
    public void updateSegRol(SegRol entity) throws Exception;

    /**
         * Load an existing SegRol entity
         *
         */
    public SegRol getSegRol(Long rolCodigo) throws Exception;

    public List<SegRol> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegRol> findPageSegRol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegRol() throws Exception;

    public List<SegRolDTO> getDataSegRol() throws Exception;

	public void guardarRol(String rolDescripcion, Long rolDiasCaducidadPwd,
			String rolEstadoRegistro, String rolNombre,
			Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario, String esAdmonDeSistema)
			throws Exception;

	public void modificarRol(Long rolCodigo, String rolDescripcion,
			Long rolDiasCaducidadPwd, String rolEstadoRegistro,
			String rolNombre, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario, String esAdmonDeSistema) throws Exception;

	public List<SegRol> consultarRolesPorSistema(Long sisCodigo)
			throws Exception;
	
	public List<SegRolDTO> consultarRolesPorSistemaDTO(Long sisCodigo)
			throws Exception;

	public List<SegRol> consultarRolesPorUsuario(Long usuCodigo) throws Exception;
	
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
	 * Método encargado de la creación de roles en un sistema dado
	 * @author Camilo José Delgado Herrera
	 * @version 2018-07-12
	 * @param rol
	 * @return
	 * @throws Exception
	 */
	public void crearRol(SegRolDTO rol) throws Exception;

	/**
	 * Método encargado de la creación de roles en un sistema dado
	 * @author Camilo José Delgado Herrera
	 * @version 2018-07-17
	 * @param rol
	 * @throws Exception
	 */
	public void modificarRol(SegRolDTO rol) throws Exception;
}
