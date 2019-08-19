package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.modelo.SegCambioPass;
import com.vortexbird.sentinel.modelo.dto.SegCambioPassDTO;

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
public interface ISegCambioPassLogic {
    public List<SegCambioPass> getSegCambioPass() throws Exception;

    /**
         * Save an new SegCambioPass entity
         */
    public void saveSegCambioPass(SegCambioPass entity)
        throws Exception;

    /**
         * Delete an existing SegCambioPass entity
         *
         */
    public void deleteSegCambioPass(SegCambioPass entity)
        throws Exception;

    /**
        * Update an existing SegCambioPass entity
        *
        */
    public void updateSegCambioPass(SegCambioPass entity)
        throws Exception;

    /**
         * Load an existing SegCambioPass entity
         *
         */
    public SegCambioPass getSegCambioPass(Long capCodigo)
        throws Exception;

    public List<SegCambioPass> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegCambioPass> findPageSegCambioPass(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegCambioPass() throws Exception;

    public List<SegCambioPassDTO> getDataSegCambioPass()
        throws Exception;
}
