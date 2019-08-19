package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.modelo.SegOpcion;
import com.vortexbird.sentinel.modelo.SegPermiso;
import com.vortexbird.sentinel.modelo.dto.SegPermisoDTO;
import com.vortexbird.sentinel.modelo.dto.SellPersonaDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface ISegPermisoLogic {
    public List<SegPermiso> getSegPermiso() throws Exception;

    /**
         * Save an new SegPermiso entity
         */
    public void saveSegPermiso(SegPermiso entity) throws Exception;

    /**
         * Delete an existing SegPermiso entity
         *
         */
    public void deleteSegPermiso(SegPermiso entity) throws Exception;

    /**
        * Update an existing SegPermiso entity
        *
        */
    public void updateSegPermiso(SegPermiso entity) throws Exception;

    /**
         * Load an existing SegPermiso entity
         *
         */
    public SegPermiso getSegPermiso(Long perCodigo) throws Exception;

    public List<SegPermiso> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegPermiso> findPageSegPermiso(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegPermiso() throws Exception;

    public List<SegPermisoDTO> getDataSegPermiso() throws Exception;

	public List<SegPermiso> consultarPermisosDeUsuariosPorSistemaCia(Long siscCodigo)
			throws Exception;

	public void guardarPermiso(String perEstadoRegistro, Long sicCodigo_SegSistemaCia,
			Long gruCodigo_SegGrupoOpcion, Long opcCodigo_SegOpcion,
			Long rolCodigo_SegRol, Long sucCodigo_SegSucursal,
			Long usuCodigo_SegUsuario, Long usuCodigo_SegUsuario0)
			throws Exception;

	public List<SegOpcion> consultarPermisosDeOpcionesAsignadosARolesYUsuarios(
			Long codigoRol, Long codigoCompania, String codigoUsuario)
			throws Exception;

	 public List<SegPermiso> consultarPermisosDeOpcionesAsignadosARolesYUsuarios(Long rolCodigo,Long codigoCompania, Long codigoOpcion, String codigoUsuario) 
	    		throws Exception;

	public void guardarPermisosParaRolOUsuarioANivelDeGruposUOpciones(Long rolCodigo,
			List<SegOpcion> listOpciones, Long codigoCompania,
			Long codigoSistema, String codigoUsuario) throws Exception;

	public void saveSegPermisoAllUser(boolean enCrm, boolean enVcloud, boolean enSellout, SellPersonaDTO personaDTO);
}
