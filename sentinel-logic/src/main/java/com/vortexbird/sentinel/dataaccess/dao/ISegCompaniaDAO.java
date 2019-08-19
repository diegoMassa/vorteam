package com.vortexbird.sentinel.dataaccess.dao;

import java.util.List;

import com.vortexbird.sentinel.dataaccess.api.Dao;
import com.vortexbird.sentinel.modelo.SegCompania;


/**
* Interface for   SegCompaniaDAO.
*
*/
public interface ISegCompaniaDAO extends Dao<SegCompania, Long> {

	public List<SegCompania> consultarCompaniasDeDeusuarioAdministrador(
			Long ucuCodigo, long sisCodigo) throws Exception;
}
