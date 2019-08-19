package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.modelo.SegOpcion;
import com.vortexbird.sentinel.modelo.dto.GrupoDTO;
import com.vortexbird.sentinel.modelo.dto.SegOpcionDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface ISegOpcionLogic {
    public List<SegOpcion> getSegOpcion() throws Exception;

    /**
         * Save an new SegOpcion entity
         */
    public void saveSegOpcion(SegOpcion entity) throws Exception;

    /**
         * Delete an existing SegOpcion entity
         *
         */
    public void deleteSegOpcion(SegOpcion entity) throws Exception;

    /**
        * Update an existing SegOpcion entity
        *
        */
    public void updateSegOpcion(SegOpcion entity) throws Exception;

    /**
         * Load an existing SegOpcion entity
         *
         */
    public SegOpcion getSegOpcion(Long opcCodigo) throws Exception;

    public List<SegOpcion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegOpcion> findPageSegOpcion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegOpcion() throws Exception;

    public List<SegOpcionDTO> getDataSegOpcion() throws Exception;

	public List<GrupoDTO> consultarOpcionesDeUsuarioPorSistemaSucursalYCompania(
			String login, String sistema, String sucursal, String cia);

	public void guardarOpcion(String nombre, String descripcion, String llaveAcceso, Long gruCodigo, Long usuCreador, String estadoRegistro, Integer orden) throws Exception;

	public List<SegOpcionDTO> consultarOpcionesPorSistema(Long sisCodigo)
			throws Exception;

	public void modificarOpcion(Long opcCodigo, String nombre, String descripcion,
			String llaveAcceso, Long gruCodigo, Long usuCreador,
			String estadoRegistro, Integer orden) throws Exception;

	public List<SegOpcion> consultarOpcionesDeGrupoOpcion(Long gruCodigo)
			throws Exception;

	public List<GrupoDTO> getOpcionesPorRolYSistema(String login, String dominio, String sistema) throws Exception;

	/**
	 * Método que consulta todas las opciones disponibles de un rol
	 * @author Camilo José Delgado Herrera
	 * @version 2018-07-17
	 * @param rol
	 * @return
	 * @throws Exception 
	 */
	public List<SegOpcionDTO> consultarOpcionesPorRol(Long rol) throws Exception;
}
