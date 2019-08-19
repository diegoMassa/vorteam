package com.vortexbird.vorteam.repository;

import java.util.List;

import com.vortexbird.vorteam.dto.VtPersonasDTO;

/**
 * 
 *
 * @author Daniel Pareja Londoño
 * @version ago. 02, 2018
 * @since 1.8
 *
 */
public interface IVtPersonasRepository {
	public List<VtPersonasDTO> consultaPersonasProyectos(String activo) throws Exception;
	public List<VtPersonasDTO> consultaPersonasProyecto(String activo, Long proyId) throws Exception;
	public List<VtPersonasDTO> consultaPersonas(String activo) throws Exception;
	public List<VtPersonasDTO> consultaPersonasActividad(Long actiId, String activo) throws Exception;
}
