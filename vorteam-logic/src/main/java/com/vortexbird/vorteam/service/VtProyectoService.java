package com.vortexbird.vorteam.service;

import java.util.List;

import com.vortexbird.vorteam.domain.VtProyecto;
import com.vortexbird.vorteam.dto.VtPersonasDTO;
import com.vortexbird.vorteam.dto.VtProyectoDTO;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtProyectoService {
    public List<VtProyecto> getVtProyecto() throws Exception;

    /**
         * Save an new VtProyecto entity
         */
    public void saveVtProyecto(VtProyecto entity) throws Exception;

    /**
         * Delete an existing VtProyecto entity
         *
         */
    public void deleteVtProyecto(VtProyecto entity) throws Exception;

    /**
        * Update an existing VtProyecto entity
        *
        */
    public void updateVtProyecto(VtProyecto entity) throws Exception;

    /**
         * Load an existing VtProyecto entity
         *
         */
    public VtProyecto getVtProyecto(Long proyId) throws Exception;

    public List<VtProyecto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtProyecto> findPageVtProyecto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtProyecto() throws Exception;

    public List<VtProyectoDTO> getDataVtProyecto() throws Exception;

    public void validateVtProyecto(VtProyecto vtProyecto)
        throws Exception;
    
	public List<VtProyectoDTO> consultaProyectosClientes(String activo) throws Exception;
	
	public void guardarProyectoConCostosPersonas(VtProyecto proyecto, List<VtPersonasDTO> personas) throws Exception;

	public void actualizarProyectoConCostosPersonas(VtProyecto proyecto, List<VtPersonasDTO> personas) throws Exception;
	
	public List<VtProyectoDTO> consultaProyectosClientesRecursos(String activo) throws Exception;
	
	public List<VtProyectoDTO> consultaProyectos(String activo) throws Exception;
	
	public List<VtProyectoDTO> consultaProyectosActivosPersona(Long persId, String activo) throws Exception;
	
	public List<VtProyectoDTO> consultaProyectosTodosPersona(Long persId, String activo) throws Exception;
}
