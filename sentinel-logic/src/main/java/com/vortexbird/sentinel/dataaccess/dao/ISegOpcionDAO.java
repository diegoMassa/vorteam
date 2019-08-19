package com.vortexbird.sentinel.dataaccess.dao;

import java.util.List;

import com.vortexbird.sentinel.dataaccess.api.Dao;
import com.vortexbird.sentinel.modelo.SegOpcion;


/**
* Interface for   SegOpcionDAO.
*
*/
public interface ISegOpcionDAO extends Dao<SegOpcion, Long> {

	public List<SegOpcion> consultarOpcionesPorSistema(Long sisCodigo)
			throws Exception;
}
