package com.vortexbird.sentinel.dataaccess.dao;

import java.util.List;

import com.vortexbird.sentinel.dataaccess.api.Dao;
import com.vortexbird.sentinel.modelo.SegRol;
import com.vortexbird.sentinel.modelo.dto.SegRolDTO;


/**
* Interface for   SegRolDAO.
*
*/
public interface ISegRolDAO extends Dao<SegRol, Long> {

	public List<SegRol> consultarRolesPorSistema(Long sisCodigo) throws Exception;
	
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
	public List<SegRolDTO> consultarRolPorSistemaCompania(Long sistema, Long compania) throws Exception;
	
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
	public List<SegRolDTO> consultarRolPorUsuarioSistemaCompania(Long sistema, Long compania, String login) throws Exception;
	
}
