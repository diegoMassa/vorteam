package com.vortexbird.vorteam.service;

import com.vortexbird.vorteam.domain.VtAsignacion;
import com.vortexbird.vorteam.dto.VtAsignacionDTO;

import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtAsignacionService {
    public List<VtAsignacion> getVtAsignacion() throws Exception;

    /**
         * Save an new VtAsignacion entity
         */
    public void saveVtAsignacion(VtAsignacion entity) throws Exception;

    /**
         * Delete an existing VtAsignacion entity
         *
         */
    public void deleteVtAsignacion(VtAsignacion entity)
        throws Exception;

    /**
        * Update an existing VtAsignacion entity
        *
        */
    public void updateVtAsignacion(VtAsignacion entity)
        throws Exception;

    /**
         * Load an existing VtAsignacion entity
         *
         */
    public VtAsignacion getVtAsignacion(Long asigId) throws Exception;

    public List<VtAsignacion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtAsignacion> findPageVtAsignacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtAsignacion() throws Exception;

    public List<VtAsignacionDTO> getDataVtAsignacion()
        throws Exception;

    public void validateVtAsignacion(VtAsignacion vtAsignacion)
        throws Exception;
}
