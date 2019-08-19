package com.vortexbird.vorteam.service;

import com.vortexbird.vorteam.domain.VtTipoIdentificacion;
import com.vortexbird.vorteam.dto.VtTipoIdentificacionDTO;

import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtTipoIdentificacionService {
    public List<VtTipoIdentificacion> getVtTipoIdentificacion()
        throws Exception;

    /**
         * Save an new VtTipoIdentificacion entity
         */
    public void saveVtTipoIdentificacion(VtTipoIdentificacion entity)
        throws Exception;

    /**
         * Delete an existing VtTipoIdentificacion entity
         *
         */
    public void deleteVtTipoIdentificacion(VtTipoIdentificacion entity)
        throws Exception;

    /**
        * Update an existing VtTipoIdentificacion entity
        *
        */
    public void updateVtTipoIdentificacion(VtTipoIdentificacion entity)
        throws Exception;

    /**
         * Load an existing VtTipoIdentificacion entity
         *
         */
    public VtTipoIdentificacion getVtTipoIdentificacion(Long tiidId)
        throws Exception;

    public List<VtTipoIdentificacion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtTipoIdentificacion> findPageVtTipoIdentificacion(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberVtTipoIdentificacion() throws Exception;

    public List<VtTipoIdentificacionDTO> getDataVtTipoIdentificacion()
        throws Exception;

    public void validateVtTipoIdentificacion(
        VtTipoIdentificacion vtTipoIdentificacion) throws Exception;
}
