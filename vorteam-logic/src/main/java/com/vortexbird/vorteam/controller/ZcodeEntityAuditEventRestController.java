package com.vortexbird.vorteam.controller;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.dto.ZcodeEntityAuditEventDTO;
import com.vortexbird.vorteam.mapper.ZcodeEntityAuditEventMapper;
import com.vortexbird.vorteam.service.ZcodeEntityAuditEventService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/zcodeEntityAuditEvent")
public class ZcodeEntityAuditEventRestController {
    private static final Logger log = LoggerFactory.getLogger(ZcodeEntityAuditEventRestController.class);
    @Autowired
    private ZcodeEntityAuditEventService zcodeEntityAuditEventService;
    @Autowired
    private ZcodeEntityAuditEventMapper zcodeEntityAuditEventMapper;

    @PostMapping(value = "/saveZcodeEntityAuditEvent")
    public void saveZcodeEntityAuditEvent(
        @RequestBody
    ZcodeEntityAuditEventDTO zcodeEntityAuditEventDTO)
        throws Exception {
        try {
            ZcodeEntityAuditEvent zcodeEntityAuditEvent = zcodeEntityAuditEventMapper.zcodeEntityAuditEventDTOToZcodeEntityAuditEvent(zcodeEntityAuditEventDTO);

            zcodeEntityAuditEventService.saveZcodeEntityAuditEvent(zcodeEntityAuditEvent);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteZcodeEntityAuditEvent/{id}")
    public void deleteZcodeEntityAuditEvent(@PathVariable("id")
    Long id) throws Exception {
        try {
            ZcodeEntityAuditEvent zcodeEntityAuditEvent = zcodeEntityAuditEventService.getZcodeEntityAuditEvent(id);

            zcodeEntityAuditEventService.deleteZcodeEntityAuditEvent(zcodeEntityAuditEvent);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateZcodeEntityAuditEvent/")
    public void updateZcodeEntityAuditEvent(
        @RequestBody
    ZcodeEntityAuditEventDTO zcodeEntityAuditEventDTO)
        throws Exception {
        try {
            ZcodeEntityAuditEvent zcodeEntityAuditEvent = zcodeEntityAuditEventMapper.zcodeEntityAuditEventDTOToZcodeEntityAuditEvent(zcodeEntityAuditEventDTO);

            zcodeEntityAuditEventService.updateZcodeEntityAuditEvent(zcodeEntityAuditEvent);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataZcodeEntityAuditEvent")
    public List<ZcodeEntityAuditEventDTO> getDataZcodeEntityAuditEvent()
        throws Exception {
        try {
            return zcodeEntityAuditEventService.getDataZcodeEntityAuditEvent();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getZcodeEntityAuditEvent/{id}")
    public ZcodeEntityAuditEventDTO getZcodeEntityAuditEvent(
        @PathVariable("id")
    Long id) throws Exception {
        try {
            ZcodeEntityAuditEvent zcodeEntityAuditEvent = zcodeEntityAuditEventService.getZcodeEntityAuditEvent(id);

            return zcodeEntityAuditEventMapper.zcodeEntityAuditEventToZcodeEntityAuditEventDTO(zcodeEntityAuditEvent);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
