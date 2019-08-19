package com.vortexbird.vorteam.service;

import com.vortexbird.vorteam.domain.VtLineaNegocio;
import com.vortexbird.vorteam.dto.VtLineaNegocioDTO;

import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtLineaNegocioService {
    public List<VtLineaNegocio> getVtLineaNegocio() throws Exception;

    /**
         * Save an new VtLineaNegocio entity
         */
    public void saveVtLineaNegocio(VtLineaNegocio entity)
        throws Exception;

    /**
         * Delete an existing VtLineaNegocio entity
         *
         */
    public void deleteVtLineaNegocio(VtLineaNegocio entity)
        throws Exception;

    /**
        * Update an existing VtLineaNegocio entity
        *
        */
    public void updateVtLineaNegocio(VtLineaNegocio entity)
        throws Exception;

    /**
         * Load an existing VtLineaNegocio entity
         *
         */
    public VtLineaNegocio getVtLineaNegocio(Long lineId)
        throws Exception;

    public List<VtLineaNegocio> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtLineaNegocio> findPageVtLineaNegocio(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtLineaNegocio() throws Exception;

    public List<VtLineaNegocioDTO> getDataVtLineaNegocio()
        throws Exception;

    public void validateVtLineaNegocio(VtLineaNegocio vtLineaNegocio)
        throws Exception;
}
