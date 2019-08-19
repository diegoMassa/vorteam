package com.vortexbird.vorteam.service;

import com.vortexbird.vorteam.domain.VtTipoActividad;
import com.vortexbird.vorteam.dto.VtTipoActividadDTO;

import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtTipoActividadService {
    public List<VtTipoActividad> getVtTipoActividad() throws Exception;

    /**
         * Save an new VtTipoActividad entity
         */
    public void saveVtTipoActividad(VtTipoActividad entity)
        throws Exception;

    /**
         * Delete an existing VtTipoActividad entity
         *
         */
    public void deleteVtTipoActividad(VtTipoActividad entity)
        throws Exception;

    /**
        * Update an existing VtTipoActividad entity
        *
        */
    public void updateVtTipoActividad(VtTipoActividad entity)
        throws Exception;

    /**
         * Load an existing VtTipoActividad entity
         *
         */
    public VtTipoActividad getVtTipoActividad(Long tiacId)
        throws Exception;

    public List<VtTipoActividad> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtTipoActividad> findPageVtTipoActividad(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberVtTipoActividad() throws Exception;

    public List<VtTipoActividadDTO> getDataVtTipoActividad()
        throws Exception;

    public void validateVtTipoActividad(VtTipoActividad vtTipoActividad)
        throws Exception;
    
    public List<VtTipoActividadDTO> tiposActividadesConClasificacionFinanciera() throws Exception;
    
    public List<VtTipoActividadDTO> tiposActividadesPorEstado(String activo) throws Exception;
}
