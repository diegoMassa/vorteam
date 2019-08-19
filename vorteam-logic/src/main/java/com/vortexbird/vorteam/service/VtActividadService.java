package com.vortexbird.vorteam.service;

import com.vortexbird.vorteam.domain.VtActividad;
import com.vortexbird.vorteam.domain.VtAsignacion;
import com.vortexbird.vorteam.domain.VtProyecto;
import com.vortexbird.vorteam.dto.VtActividadDTO;
import com.vortexbird.vorteam.dto.VtPersonasDTO;

import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtActividadService {
    public List<VtActividad> getVtActividad() throws Exception;

    /**
         * Save an new VtActividad entity
         */
    public void saveVtActividad(VtActividad entity) throws Exception;

    /**
         * Delete an existing VtActividad entity
         *
         */
    public void deleteVtActividad(VtActividad entity) throws Exception;

    /**
        * Update an existing VtActividad entity
        *
        */
    public void updateVtActividad(VtActividad entity) throws Exception;

    /**
         * Load an existing VtActividad entity
         *
         */
    public VtActividad getVtActividad(Long actiId) throws Exception;

    public List<VtActividad> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtActividad> findPageVtActividad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtActividad() throws Exception;

    public List<VtActividadDTO> getDataVtActividad() throws Exception;

    public void validateVtActividad(VtActividad vtActividad)
        throws Exception;
    
	public String guardarActividadConAsignacionesPersonas(VtActividad actividad, List<VtPersonasDTO> personas) throws Exception;
	
	public List<VtActividadDTO> consultaActividadesOrdenadaFechaDescendiente(String activo) throws Exception;
	
	public List<VtActividadDTO> consultaMisActividades(String usuario, String activo) throws Exception;
	
	public List<VtActividadDTO> consultaMisActividadesLazy(String usuario, String activo, Long proyId, Long estaId, Long tiacId, String sprint, String casoSoporte, String controlCambios, int first, int pageSize) throws Exception;
	
	public Long consultaTotalMisActividadesLazy(String usuario, String activo, Long proyId, Long estaId, Long tiacId, String sprint, String casoSoporte, String controlCambios) throws Exception;

}
