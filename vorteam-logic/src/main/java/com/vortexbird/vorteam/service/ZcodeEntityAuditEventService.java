package com.vortexbird.vorteam.service;

import com.vortexbird.vorteam.domain.ZcodeEntityAuditEvent;
import com.vortexbird.vorteam.dto.ZcodeEntityAuditEventDTO;

import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ZcodeEntityAuditEventService {
    public List<ZcodeEntityAuditEvent> getZcodeEntityAuditEvent()
        throws Exception;

    /**
         * Save an new ZcodeEntityAuditEvent entity
         */
    public void saveZcodeEntityAuditEvent(ZcodeEntityAuditEvent entity)
        throws Exception;

    /**
         * Delete an existing ZcodeEntityAuditEvent entity
         *
         */
    public void deleteZcodeEntityAuditEvent(ZcodeEntityAuditEvent entity)
        throws Exception;

    /**
        * Update an existing ZcodeEntityAuditEvent entity
        *
        */
    public void updateZcodeEntityAuditEvent(ZcodeEntityAuditEvent entity)
        throws Exception;

    /**
         * Load an existing ZcodeEntityAuditEvent entity
         *
         */
    public ZcodeEntityAuditEvent getZcodeEntityAuditEvent(Long id)
        throws Exception;

    public List<ZcodeEntityAuditEvent> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<ZcodeEntityAuditEvent> findPageZcodeEntityAuditEvent(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberZcodeEntityAuditEvent()
        throws Exception;

    public List<ZcodeEntityAuditEventDTO> getDataZcodeEntityAuditEvent()
        throws Exception;

    public void validateZcodeEntityAuditEvent(
        ZcodeEntityAuditEvent zcodeEntityAuditEvent) throws Exception;
}
