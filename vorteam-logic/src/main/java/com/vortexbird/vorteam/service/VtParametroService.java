package com.vortexbird.vorteam.service;

import java.util.List;

import com.vortexbird.vorteam.domain.VtParametro;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtParametroService {

    public List<VtParametro> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;
    
    public VtParametro obtenerParametroPorCodigo(String codigo, String activo) throws Exception;
}
