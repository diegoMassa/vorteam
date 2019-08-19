package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.modelo.SegSucursal;
import com.vortexbird.sentinel.modelo.dto.SegSucursalDTO;

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
public interface ISegSucursalLogic {
    public List<SegSucursal> getSegSucursal() throws Exception;

    /**
         * Save an new SegSucursal entity
         */
    public void saveSegSucursal(SegSucursal entity) throws Exception;

    /**
         * Delete an existing SegSucursal entity
         *
         */
    public void deleteSegSucursal(SegSucursal entity) throws Exception;

    /**
        * Update an existing SegSucursal entity
        *
        */
    public void updateSegSucursal(SegSucursal entity) throws Exception;

    /**
         * Load an existing SegSucursal entity
         *
         */
    public SegSucursal getSegSucursal(Long sucCodigo) throws Exception;

    public List<SegSucursal> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegSucursal> findPageSegSucursal(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegSucursal() throws Exception;

    public List<SegSucursalDTO> getDataSegSucursal() throws Exception;
}
