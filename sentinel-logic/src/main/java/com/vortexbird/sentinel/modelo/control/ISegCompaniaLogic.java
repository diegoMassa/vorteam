package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.modelo.SegCompania;
import com.vortexbird.sentinel.modelo.dto.SegCompaniaDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface ISegCompaniaLogic {
    public List<SegCompania> getSegCompania() throws Exception;

    /**
         * Save an new SegCompania entity
         */
    public void saveSegCompania(SegCompania entity) throws Exception;

    /**
         * Delete an existing SegCompania entity
         *
         */
    public void deleteSegCompania(SegCompania entity) throws Exception;

    /**
        * Update an existing SegCompania entity
        *
        */
    public void updateSegCompania(SegCompania entity) throws Exception;

    /**
         * Load an existing SegCompania entity
         *
         */
    public SegCompania getSegCompania(Long ciaCodigo) throws Exception;

    public List<SegCompania> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegCompania> findPageSegCompania(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegCompania() throws Exception;

    public List<SegCompaniaDTO> getDataSegCompania() throws Exception;

	public List<SegCompania> consultarCompaniasDeDeusuarioAdministrador(
			Long ucuCodigo, long sisCodigo) throws Exception;

	public List<SegCompania> consultarCompaniasPorRol(Long rolCodigo) throws Exception;
}
