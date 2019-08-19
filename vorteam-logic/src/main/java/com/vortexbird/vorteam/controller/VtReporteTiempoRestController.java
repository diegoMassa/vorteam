package com.vortexbird.vorteam.controller;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.dto.VtReporteTiempoDTO;
import com.vortexbird.vorteam.mapper.VtReporteTiempoMapper;
import com.vortexbird.vorteam.service.VtReporteTiempoService;

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
@RequestMapping("/vtReporteTiempo")
public class VtReporteTiempoRestController {
    private static final Logger log = LoggerFactory.getLogger(VtReporteTiempoRestController.class);
    @Autowired
    private VtReporteTiempoService vtReporteTiempoService;
    @Autowired
    private VtReporteTiempoMapper vtReporteTiempoMapper;

    @PostMapping(value = "/saveVtReporteTiempo")
    public void saveVtReporteTiempo(
        @RequestBody
    VtReporteTiempoDTO vtReporteTiempoDTO) throws Exception {
        try {
            VtReporteTiempo vtReporteTiempo = vtReporteTiempoMapper.vtReporteTiempoDTOToVtReporteTiempo(vtReporteTiempoDTO);

            vtReporteTiempoService.saveVtReporteTiempo(vtReporteTiempo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteVtReporteTiempo/{retiId}")
    public void deleteVtReporteTiempo(@PathVariable("retiId")
    Long retiId) throws Exception {
        try {
            VtReporteTiempo vtReporteTiempo = vtReporteTiempoService.getVtReporteTiempo(retiId);

            vtReporteTiempoService.deleteVtReporteTiempo(vtReporteTiempo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateVtReporteTiempo/")
    public void updateVtReporteTiempo(
        @RequestBody
    VtReporteTiempoDTO vtReporteTiempoDTO) throws Exception {
        try {
            VtReporteTiempo vtReporteTiempo = vtReporteTiempoMapper.vtReporteTiempoDTOToVtReporteTiempo(vtReporteTiempoDTO);

            vtReporteTiempoService.updateVtReporteTiempo(vtReporteTiempo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataVtReporteTiempo")
    public List<VtReporteTiempoDTO> getDataVtReporteTiempo()
        throws Exception {
        try {
            return vtReporteTiempoService.getDataVtReporteTiempo();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getVtReporteTiempo/{retiId}")
    public VtReporteTiempoDTO getVtReporteTiempo(
        @PathVariable("retiId")
    Long retiId) throws Exception {
        try {
            VtReporteTiempo vtReporteTiempo = vtReporteTiempoService.getVtReporteTiempo(retiId);

            return vtReporteTiempoMapper.vtReporteTiempoToVtReporteTiempoDTO(vtReporteTiempo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
