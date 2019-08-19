package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.modelo.SegAuditoria;
import com.vortexbird.sentinel.modelo.dto.SegAuditoriaDTO;

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
public interface ISegAuditoriaLogic {
    public List<SegAuditoria> getSegAuditoria() throws Exception;

    /**
         * Save an new SegAuditoria entity
         */
    public void saveSegAuditoria(SegAuditoria entity) throws Exception;

    /**
         * Delete an existing SegAuditoria entity
         *
         */
    public void deleteSegAuditoria(SegAuditoria entity)
        throws Exception;

    /**
        * Update an existing SegAuditoria entity
        *
        */
    public void updateSegAuditoria(SegAuditoria entity)
        throws Exception;

    /**
         * Load an existing SegAuditoria entity
         *
         */
    public SegAuditoria getSegAuditoria(Long autCodigo)
        throws Exception;

    public List<SegAuditoria> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegAuditoria> findPageSegAuditoria(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegAuditoria() throws Exception;

    public List<SegAuditoriaDTO> getDataSegAuditoria()
        throws Exception;
}
