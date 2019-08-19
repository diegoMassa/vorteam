package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.modelo.SegParametro;
import com.vortexbird.sentinel.modelo.dto.SegParametroDTO;

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
public interface ISegParametroLogic {
    public List<SegParametro> getSegParametro() throws Exception;

    /**
         * Save an new SegParametro entity
         */
    public void saveSegParametro(SegParametro entity) throws Exception;

    /**
         * Delete an existing SegParametro entity
         *
         */
    public void deleteSegParametro(SegParametro entity)
        throws Exception;

    /**
        * Update an existing SegParametro entity
        *
        */
    public void updateSegParametro(SegParametro entity)
        throws Exception;

    /**
         * Load an existing SegParametro entity
         *
         */
    public SegParametro getSegParametro(Long parCodigo)
        throws Exception;

    public List<SegParametro> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegParametro> findPageSegParametro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegParametro() throws Exception;

    public List<SegParametroDTO> getDataSegParametro()
        throws Exception;
}
