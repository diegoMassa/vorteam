package com.vortexbird.vorteam.service;

import java.util.List;

import com.vortexbird.sentinel.dto.UsuarioDTO;
import com.vortexbird.vorteam.domain.VtPersonas;
import com.vortexbird.vorteam.dto.GrupoDTO;
import com.vortexbird.vorteam.dto.VtPersonasDTO;
import com.vortexbird.vorteam.dto.VtProyectoDTO;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtPersonasService {
    public List<VtPersonas> getVtPersonas() throws Exception;

    /**
         * Save an new VtPersonas entity
         */
    public void saveVtPersonas(VtPersonas entity) throws Exception;

    /**
         * Delete an existing VtPersonas entity
         *
         */
    public void deleteVtPersonas(VtPersonas entity) throws Exception;

    /**
        * Update an existing VtPersonas entity
        *
        */
    public void updateVtPersonas(VtPersonas entity) throws Exception;

    /**
         * Load an existing VtPersonas entity
         *
         */
    public VtPersonas getVtPersonas(Long persId) throws Exception;

    public List<VtPersonas> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtPersonas> findPageVtPersonas(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtPersonas() throws Exception;

    public List<VtPersonasDTO> getDataVtPersonas() throws Exception;

    public void validateVtPersonas(VtPersonas vtPersonas)
        throws Exception;
    
    public List<VtPersonasDTO> consultaPersonasProyectos(String activo) throws Exception;
    
    public List<VtPersonasDTO> consultaPersonasProyecto(String activo, Long proyId) throws Exception;
    
    public List<VtPersonasDTO> consultaPersonas(String activo) throws Exception;
    
    public void guardarPersonasConCostosProyecto(VtPersonasDTO persona, List<VtProyectoDTO> proyectos) throws Exception;
    
    public void actualizarPersonasConCostosProyecto(VtPersonasDTO persona, List<VtProyectoDTO> proyectos) throws Exception;

	public UsuarioDTO autenticar(String userId, String password) throws Exception;
	
	public List<VtPersonasDTO> consultaPersonasActividad(Long actiId, String activo) throws Exception;
	
	public List<GrupoDTO> consultarOpcionesDeUsuarioPorSistemaSucursalYCompania(String login, String sistema,
			String sucursal, String compania)throws Exception;
	
	public VtPersonas consultarPersonaPorCorreo(String correo) throws Exception;
	
	public void cambiarClave(VtPersonasDTO personaDTO, UsuarioDTO usuarioDTO) throws Exception;
	
	public void restaurarClave(String login) throws Exception;
}
