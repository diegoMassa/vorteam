package com.vortexbird.vorteam.controller;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.dto.VtProyectoDTO;
import com.vortexbird.vorteam.mapper.VtProyectoMapper;
import com.vortexbird.vorteam.service.VtProyectoService;

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
@RequestMapping("/vtProyecto")
public class VtProyectoRestController {
    private static final Logger log = LoggerFactory.getLogger(VtProyectoRestController.class);
    @Autowired
    private VtProyectoService vtProyectoService;
    @Autowired
    private VtProyectoMapper vtProyectoMapper;

    @PostMapping(value = "/saveVtProyecto")
    public void saveVtProyecto(@RequestBody
    VtProyectoDTO vtProyectoDTO) throws Exception {
        try {
            VtProyecto vtProyecto = vtProyectoMapper.vtProyectoDTOToVtProyecto(vtProyectoDTO);

            vtProyectoService.saveVtProyecto(vtProyecto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteVtProyecto/{proyId}")
    public void deleteVtProyecto(@PathVariable("proyId")
    Long proyId) throws Exception {
        try {
            VtProyecto vtProyecto = vtProyectoService.getVtProyecto(proyId);

            vtProyectoService.deleteVtProyecto(vtProyecto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateVtProyecto/")
    public void updateVtProyecto(@RequestBody
    VtProyectoDTO vtProyectoDTO) throws Exception {
        try {
            VtProyecto vtProyecto = vtProyectoMapper.vtProyectoDTOToVtProyecto(vtProyectoDTO);

            vtProyectoService.updateVtProyecto(vtProyecto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataVtProyecto")
    public List<VtProyectoDTO> getDataVtProyecto() throws Exception {
        try {
            return vtProyectoService.getDataVtProyecto();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getVtProyecto/{proyId}")
    public VtProyectoDTO getVtProyecto(@PathVariable("proyId")
    Long proyId) throws Exception {
        try {
            VtProyecto vtProyecto = vtProyectoService.getVtProyecto(proyId);

            return vtProyectoMapper.vtProyectoToVtProyectoDTO(vtProyecto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
