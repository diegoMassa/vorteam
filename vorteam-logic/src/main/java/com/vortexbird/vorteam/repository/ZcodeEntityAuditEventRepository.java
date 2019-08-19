package com.vortexbird.vorteam.repository;

import com.vortexbird.vorteam.domain.ZcodeEntityAuditEvent;

import java.math.BigDecimal;


/**
* Interface for   ZcodeEntityAuditEventRepository.
*
*/
public interface ZcodeEntityAuditEventRepository extends JpaGenericRepository<ZcodeEntityAuditEvent, Long> {
	public Integer countByEntityTypeAndEntityId(String entityType, String entityId) throws Exception;
}
