package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.modelo.SegHistorialConstrasena;
import com.vortexbird.sentinel.modelo.dto.SegHistorialConstrasenaDTO;

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
public interface ISegHistorialConstrasenaLogic {
    public List<SegHistorialConstrasena> getSegHistorialConstrasena()
        throws Exception;

    /**
         * Save an new SegHistorialConstrasena entity
         */
    public void saveSegHistorialConstrasena(SegHistorialConstrasena entity)
        throws Exception;

    /**
         * Delete an existing SegHistorialConstrasena entity
         *
         */
    public void deleteSegHistorialConstrasena(SegHistorialConstrasena entity)
        throws Exception;

    /**
        * Update an existing SegHistorialConstrasena entity
        *
        */
    public void updateSegHistorialConstrasena(SegHistorialConstrasena entity)
        throws Exception;

    /**
         * Load an existing SegHistorialConstrasena entity
         *
         */
    public SegHistorialConstrasena getSegHistorialConstrasena(Long hcoCodigo)
        throws Exception;

    public List<SegHistorialConstrasena> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegHistorialConstrasena> findPageSegHistorialConstrasena(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberSegHistorialConstrasena()
        throws Exception;

    public List<SegHistorialConstrasenaDTO> getDataSegHistorialConstrasena()
        throws Exception;
}
