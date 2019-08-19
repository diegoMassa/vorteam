package com.vortexbird.vorteam.controller;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.dto.VtTipoActividadDTO;
import com.vortexbird.vorteam.mapper.VtTipoActividadMapper;
import com.vortexbird.vorteam.service.VtTipoActividadService;

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
@RequestMapping("/vtTipoActividad")
public class VtTipoActividadRestController {
    private static final Logger log = LoggerFactory.getLogger(VtTipoActividadRestController.class);
    @Autowired
    private VtTipoActividadService vtTipoActividadService;
    @Autowired
    private VtTipoActividadMapper vtTipoActividadMapper;

    @PostMapping(value = "/saveVtTipoActividad")
    public void saveVtTipoActividad(
        @RequestBody
    VtTipoActividadDTO vtTipoActividadDTO) throws Exception {
        try {
            VtTipoActividad vtTipoActividad = vtTipoActividadMapper.vtTipoActividadDTOToVtTipoActividad(vtTipoActividadDTO);

            vtTipoActividadService.saveVtTipoActividad(vtTipoActividad);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteVtTipoActividad/{tiacId}")
    public void deleteVtTipoActividad(@PathVariable("tiacId")
    Long tiacId) throws Exception {
        try {
            VtTipoActividad vtTipoActividad = vtTipoActividadService.getVtTipoActividad(tiacId);

            vtTipoActividadService.deleteVtTipoActividad(vtTipoActividad);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateVtTipoActividad/")
    public void updateVtTipoActividad(
        @RequestBody
    VtTipoActividadDTO vtTipoActividadDTO) throws Exception {
        try {
            VtTipoActividad vtTipoActividad = vtTipoActividadMapper.vtTipoActividadDTOToVtTipoActividad(vtTipoActividadDTO);

            vtTipoActividadService.updateVtTipoActividad(vtTipoActividad);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataVtTipoActividad")
    public List<VtTipoActividadDTO> getDataVtTipoActividad()
        throws Exception {
        try {
            return vtTipoActividadService.getDataVtTipoActividad();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getVtTipoActividad/{tiacId}")
    public VtTipoActividadDTO getVtTipoActividad(
        @PathVariable("tiacId")
    Long tiacId) throws Exception {
        try {
            VtTipoActividad vtTipoActividad = vtTipoActividadService.getVtTipoActividad(tiacId);

            return vtTipoActividadMapper.vtTipoActividadToVtTipoActividadDTO(vtTipoActividad);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
