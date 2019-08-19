package com.vortexbird.vorteam.service;

import com.vortexbird.vorteam.domain.VtClasificacionFinanciera;
import com.vortexbird.vorteam.dto.VtClasificacionFinancieraDTO;

import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtClasificacionFinancieraService {
    public List<VtClasificacionFinanciera> getVtClasificacionFinanciera()
        throws Exception;

    /**
         * Save an new VtClasificacionFinanciera entity
         */
    public void saveVtClasificacionFinanciera(VtClasificacionFinanciera entity)
        throws Exception;

    /**
         * Delete an existing VtClasificacionFinanciera entity
         *
         */
    public void deleteVtClasificacionFinanciera(
        VtClasificacionFinanciera entity) throws Exception;

    /**
        * Update an existing VtClasificacionFinanciera entity
        *
        */
    public void updateVtClasificacionFinanciera(
        VtClasificacionFinanciera entity) throws Exception;

    /**
         * Load an existing VtClasificacionFinanciera entity
         *
         */
    public VtClasificacionFinanciera getVtClasificacionFinanciera(Long clfiId)
        throws Exception;

    public List<VtClasificacionFinanciera> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtClasificacionFinanciera> findPageVtClasificacionFinanciera(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberVtClasificacionFinanciera()
        throws Exception;

    public List<VtClasificacionFinancieraDTO> getDataVtClasificacionFinanciera()
        throws Exception;

    public void validateVtClasificacionFinanciera(
        VtClasificacionFinanciera vtClasificacionFinanciera)
        throws Exception;
}
