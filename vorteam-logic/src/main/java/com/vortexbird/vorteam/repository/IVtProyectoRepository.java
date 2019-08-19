package com.vortexbird.vorteam.repository;

import java.util.List;

import com.vortexbird.vorteam.dto.VtProyectoDTO;

/**
 * 
 *
 * @author Daniel Pareja Londoño
 * @version ago. 02, 2018
 * @since 1.8
 *
 */
public interface IVtProyectoRepository {
	public List<VtProyectoDTO> consultaProyectosClientes(String activo) throws Exception;
	public List<VtProyectoDTO> consultaProyectosClientesRecursos(String activo) throws Exception;
	public List<VtProyectoDTO> consultaProyectos(String activo) throws Exception;
	public List<VtProyectoDTO> consultaProyectosActivosPersona(Long persId, String activo) throws Exception;
	public List<VtProyectoDTO> consultaProyectosTodosPersona(Long persId, String activo) throws Exception;
}
