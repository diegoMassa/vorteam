package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.modelo.SegSistemaCia;
import com.vortexbird.sentinel.modelo.dto.SegSistemaCiaDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface ISegSistemaCiaLogic {
    public List<SegSistemaCia> getSegSistemaCia() throws Exception;

    /**
         * Save an new SegSistemaCia entity
         */
    public void saveSegSistemaCia(SegSistemaCia entity)
        throws Exception;

    /**
         * Delete an existing SegSistemaCia entity
         *
         */
    public void deleteSegSistemaCia(SegSistemaCia entity)
        throws Exception;

    /**
        * Update an existing SegSistemaCia entity
        *
        */
    public void updateSegSistemaCia(SegSistemaCia entity)
        throws Exception;

    /**
         * Load an existing SegSistemaCia entity
         *
         */
    public SegSistemaCia getSegSistemaCia(Long sicCodigo)
        throws Exception;

    public List<SegSistemaCia> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegSistemaCia> findPageSegSistemaCia(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegSistemaCia() throws Exception;

    public List<SegSistemaCiaDTO> getDataSegSistemaCia()
        throws Exception;

	/**
	 * Obtengo el sistemaCia de un sistema y compañía
	 * @author Camilo José Delgado Herrera
	 * @version 2018-07-12
	 * @param sisCodigo
	 * @param ciaCodigo
	 * @return
	 */
	public SegSistemaCia getSegSistemaCiaBySistemaYCompania(Long sisCodigo, Long ciaCodigo);
}
