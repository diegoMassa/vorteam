package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.domain.ZcodeEntityAuditEvent;
import com.vortexbird.vorteam.dto.ZcodeEntityAuditEventDTO;
import com.vortexbird.vorteam.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Component
@Scope("singleton")
public class ZcodeEntityAuditEventMapperImpl
    implements ZcodeEntityAuditEventMapper {
    private static final Logger log = LoggerFactory.getLogger(ZcodeEntityAuditEventMapperImpl.class);

    @Transactional(readOnly = true)
    public ZcodeEntityAuditEventDTO zcodeEntityAuditEventToZcodeEntityAuditEventDTO(
        ZcodeEntityAuditEvent zcodeEntityAuditEvent) throws Exception {
        try {
            ZcodeEntityAuditEventDTO zcodeEntityAuditEventDTO = new ZcodeEntityAuditEventDTO();

            zcodeEntityAuditEventDTO.setId(zcodeEntityAuditEvent.getId());
            zcodeEntityAuditEventDTO.setAction((zcodeEntityAuditEvent.getAction() != null)
                ? zcodeEntityAuditEvent.getAction() : null);
            zcodeEntityAuditEventDTO.setCommitVersion((zcodeEntityAuditEvent.getCommitVersion() != null)
                ? zcodeEntityAuditEvent.getCommitVersion() : null);
            zcodeEntityAuditEventDTO.setEntityId((zcodeEntityAuditEvent.getEntityId() != null)
                ? zcodeEntityAuditEvent.getEntityId() : null);
            zcodeEntityAuditEventDTO.setEntityType((zcodeEntityAuditEvent.getEntityType() != null)
                ? zcodeEntityAuditEvent.getEntityType() : null);
            zcodeEntityAuditEventDTO.setEntityValue((zcodeEntityAuditEvent.getEntityValue() != null)
                ? zcodeEntityAuditEvent.getEntityValue() : null);
            zcodeEntityAuditEventDTO.setModifiedBy((zcodeEntityAuditEvent.getModifiedBy() != null)
                ? zcodeEntityAuditEvent.getModifiedBy() : null);
            zcodeEntityAuditEventDTO.setModifiedDate(zcodeEntityAuditEvent.getModifiedDate());

            return zcodeEntityAuditEventDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public ZcodeEntityAuditEvent zcodeEntityAuditEventDTOToZcodeEntityAuditEvent(
        ZcodeEntityAuditEventDTO zcodeEntityAuditEventDTO)
        throws Exception {
        try {
            ZcodeEntityAuditEvent zcodeEntityAuditEvent = new ZcodeEntityAuditEvent();

            zcodeEntityAuditEvent.setId(zcodeEntityAuditEventDTO.getId());
            zcodeEntityAuditEvent.setAction((zcodeEntityAuditEventDTO.getAction() != null)
                ? zcodeEntityAuditEventDTO.getAction() : null);
            zcodeEntityAuditEvent.setCommitVersion((zcodeEntityAuditEventDTO.getCommitVersion() != null)
                ? zcodeEntityAuditEventDTO.getCommitVersion() : null);
            zcodeEntityAuditEvent.setEntityId((zcodeEntityAuditEventDTO.getEntityId() != null)
                ? zcodeEntityAuditEventDTO.getEntityId() : null);
            zcodeEntityAuditEvent.setEntityType((zcodeEntityAuditEventDTO.getEntityType() != null)
                ? zcodeEntityAuditEventDTO.getEntityType() : null);
            zcodeEntityAuditEvent.setEntityValue((zcodeEntityAuditEventDTO.getEntityValue() != null)
                ? zcodeEntityAuditEventDTO.getEntityValue() : null);
            zcodeEntityAuditEvent.setModifiedBy((zcodeEntityAuditEventDTO.getModifiedBy() != null)
                ? zcodeEntityAuditEventDTO.getModifiedBy() : null);
            zcodeEntityAuditEvent.setModifiedDate(zcodeEntityAuditEventDTO.getModifiedDate());

            return zcodeEntityAuditEvent;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<ZcodeEntityAuditEventDTO> listZcodeEntityAuditEventToListZcodeEntityAuditEventDTO(
        List<ZcodeEntityAuditEvent> listZcodeEntityAuditEvent)
        throws Exception {
        try {
            List<ZcodeEntityAuditEventDTO> zcodeEntityAuditEventDTOs = new ArrayList<ZcodeEntityAuditEventDTO>();

            for (ZcodeEntityAuditEvent zcodeEntityAuditEvent : listZcodeEntityAuditEvent) {
                ZcodeEntityAuditEventDTO zcodeEntityAuditEventDTO = zcodeEntityAuditEventToZcodeEntityAuditEventDTO(zcodeEntityAuditEvent);

                zcodeEntityAuditEventDTOs.add(zcodeEntityAuditEventDTO);
            }

            return zcodeEntityAuditEventDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<ZcodeEntityAuditEvent> listZcodeEntityAuditEventDTOToListZcodeEntityAuditEvent(
        List<ZcodeEntityAuditEventDTO> listZcodeEntityAuditEventDTO)
        throws Exception {
        try {
            List<ZcodeEntityAuditEvent> listZcodeEntityAuditEvent = new ArrayList<ZcodeEntityAuditEvent>();

            for (ZcodeEntityAuditEventDTO zcodeEntityAuditEventDTO : listZcodeEntityAuditEventDTO) {
                ZcodeEntityAuditEvent zcodeEntityAuditEvent = zcodeEntityAuditEventDTOToZcodeEntityAuditEvent(zcodeEntityAuditEventDTO);

                listZcodeEntityAuditEvent.add(zcodeEntityAuditEvent);
            }

            return listZcodeEntityAuditEvent;
        } catch (Exception e) {
            throw e;
        }
    }
}
