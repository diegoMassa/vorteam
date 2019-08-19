package com.vortexbird.vorteam.service;

import com.vortexbird.vorteam.domain.VtEstado;
import com.vortexbird.vorteam.dto.VtEstadoDTO;

import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtEstadoService {
    public List<VtEstado> getVtEstado() throws Exception;

    /**
         * Save an new VtEstado entity
         */
    public void saveVtEstado(VtEstado entity) throws Exception;

    /**
         * Delete an existing VtEstado entity
         *
         */
    public void deleteVtEstado(VtEstado entity) throws Exception;

    /**
        * Update an existing VtEstado entity
        *
        */
    public void updateVtEstado(VtEstado entity) throws Exception;

    /**
         * Load an existing VtEstado entity
         *
         */
    public VtEstado getVtEstado(Long estaId) throws Exception;

    public List<VtEstado> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtEstado> findPageVtEstado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtEstado() throws Exception;

    public List<VtEstadoDTO> getDataVtEstado() throws Exception;

    public void validateVtEstado(VtEstado vtEstado) throws Exception;
}
