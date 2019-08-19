package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.ZcodeEntityAuditEvent;
import com.vortexbird.vorteam.dto.ZcodeEntityAuditEventDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ZcodeEntityAuditEventMapper {
    public ZcodeEntityAuditEventDTO zcodeEntityAuditEventToZcodeEntityAuditEventDTO(
        ZcodeEntityAuditEvent zcodeEntityAuditEvent) throws Exception;

    public ZcodeEntityAuditEvent zcodeEntityAuditEventDTOToZcodeEntityAuditEvent(
        ZcodeEntityAuditEventDTO zcodeEntityAuditEventDTO)
        throws Exception;

    public List<ZcodeEntityAuditEventDTO> listZcodeEntityAuditEventToListZcodeEntityAuditEventDTO(
        List<ZcodeEntityAuditEvent> zcodeEntityAuditEvents)
        throws Exception;

    public List<ZcodeEntityAuditEvent> listZcodeEntityAuditEventDTOToListZcodeEntityAuditEvent(
        List<ZcodeEntityAuditEventDTO> zcodeEntityAuditEventDTOs)
        throws Exception;
}
