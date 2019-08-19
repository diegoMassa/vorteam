package com.vortexbird.sentinel.dataaccess.dao;

import java.util.List;

import com.vortexbird.sentinel.dataaccess.api.Dao;
import com.vortexbird.sentinel.modelo.SegGrupoOpcion;


/**
* Interface for   SegGrupoOpcionDAO.
*
*/
public interface ISegGrupoOpcionDAO extends Dao<SegGrupoOpcion, Long> {

	public List<SegGrupoOpcion> consultarGrupoOpcionesPorSistema(Long sisCodigo) throws Exception;
}
