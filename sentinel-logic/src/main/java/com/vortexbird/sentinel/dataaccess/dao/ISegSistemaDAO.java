package com.vortexbird.sentinel.dataaccess.dao;

import java.util.List;

import com.vortexbird.sentinel.dataaccess.api.Dao;
import com.vortexbird.sentinel.modelo.SegSistema;


/**
* Interface for   SegSistemaDAO.
*
*/
public interface ISegSistemaDAO extends Dao<SegSistema, Long> {

	public List<SegSistema> consultarSistemasDeUsuarioAdministrador(Long usuCodigo);
	
}
