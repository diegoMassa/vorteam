package com.vortexbird.vorteam.repository;

import java.util.List;

import com.vortexbird.vorteam.dto.VtActividadDTO;

/**
 * 
 *
 * @author Daniel Pareja Londoño
 * @version ago. 02, 2018
 * @since 1.8
 *
 */
public interface IVtActividadRepository {
	public List<VtActividadDTO> consultaActividadesOrdenadaFechaDescendiente(String activo) throws Exception;
	public List<VtActividadDTO> consultaMisActividades(Long persId, String activo) throws Exception;
	public List<VtActividadDTO> consultaMisActividadesLazy(Long persId, String activo, Long proyId, Long estaId, Long tiacId, String sprint, String casoSoporte, String controlCambios, int first, int pageSize) throws Exception;
	public Long consultaTotalMisActividadesLazy(Long persId, String activo, Long proyId, Long estaId, Long tiacId, String sprint, String casoSoporte, String controlCambios) throws Exception;
}
