package com.vortexbird.vorteam.service;

import com.vortexbird.vorteam.domain.VtProyCosto;
import com.vortexbird.vorteam.dto.VtProyCostoDTO;

import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtProyCostoService {
    public List<VtProyCosto> getVtProyCosto() throws Exception;

    /**
         * Save an new VtProyCosto entity
         */
    public void saveVtProyCosto(VtProyCosto entity) throws Exception;

    /**
         * Delete an existing VtProyCosto entity
         *
         */
    public void deleteVtProyCosto(VtProyCosto entity) throws Exception;

    /**
        * Update an existing VtProyCosto entity
        *
        */
    public void updateVtProyCosto(VtProyCosto entity) throws Exception;

    /**
         * Load an existing VtProyCosto entity
         *
         */
    public VtProyCosto getVtProyCosto(Long prcoId) throws Exception;

    public List<VtProyCosto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtProyCosto> findPageVtProyCosto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtProyCosto() throws Exception;

    public List<VtProyCostoDTO> getDataVtProyCosto() throws Exception;

    public void validateVtProyCosto(VtProyCosto vtProyCosto)
        throws Exception;
}
