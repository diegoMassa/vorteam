package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.modelo.SegGrupoOpcion;
import com.vortexbird.sentinel.modelo.dto.SegGrupoOpcionDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface ISegGrupoOpcionLogic {
    public List<SegGrupoOpcion> getSegGrupoOpcion() throws Exception;

    /**
         * Save an new SegGrupoOpcion entity
         */
    public void saveSegGrupoOpcion(SegGrupoOpcion entity)
        throws Exception;

    /**
         * Delete an existing SegGrupoOpcion entity
         *
         */
    public void deleteSegGrupoOpcion(SegGrupoOpcion entity)
        throws Exception;

    /**
        * Update an existing SegGrupoOpcion entity
        *
        */
    public void updateSegGrupoOpcion(SegGrupoOpcion entity)
        throws Exception;

    /**
         * Load an existing SegGrupoOpcion entity
         *
         */
    public SegGrupoOpcion getSegGrupoOpcion(Long gruCodigo)
        throws Exception;

    public List<SegGrupoOpcion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegGrupoOpcion> findPageSegGrupoOpcion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegGrupoOpcion() throws Exception;

    public List<SegGrupoOpcionDTO> getDataSegGrupoOpcion()
        throws Exception;

	public List<SegGrupoOpcion> consultarGrupoOpcionesPorSistema(Long sisCodigo)
			throws Exception;

	public void guardarGrupoOpcion(Long gruCodigo, String gruDescripcion,
			String gruEstadoRegistro, String gruLlaveAcceso, String gruNombre,
			Long gruCodigo_SegGrupoOpcion, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario) throws Exception;

	public void modificarGrupoOpcion(Long gruCodigo, String gruDescripcion,
			String gruEstadoRegistro, String gruLlaveAcceso, String gruNombre,
			Long gruCodigo_SegGrupoOpcion, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario) throws Exception;

	public List<SegGrupoOpcion> consultarGrupoOpcionesPorRol(Long rolCodigo)
			throws Exception;
}
