package com.vortexbird.sentinel.dataaccess.dao;

import java.util.List;

import com.vortexbird.sentinel.dataaccess.api.Dao;
import com.vortexbird.sentinel.modelo.SegPermiso;


/**
* Interface for   SegPermisoDAO.
*
*/
public interface ISegPermisoDAO extends Dao<SegPermiso, Long> {

	public List<SegPermiso> consultarPermisosDeOpcionesAsignadosARolesYUsuarios(
			Long codigoRol, Long sicCodigo, Long codigoUsuario) throws Exception;

	public List<SegPermiso> consultarPermisosDeOpcionesAsignadosARolesYUsuarios(
			Long rolCodigo, Long sisCiaUnico, String codigoUsuario,
			Long codigoOpcion) throws Exception;

	public List<SegPermiso> consultarPermisosDeGruposAsignadosARolesYUsuarios(
			Long rolCodigo, Long sisCiaUnico, String codigoUsuario,
			Long codigoGrupo) throws Exception;
}
