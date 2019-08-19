package com.vortexbird.vorteam.controller;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.dto.VtAsignacionDTO;
import com.vortexbird.vorteam.mapper.VtAsignacionMapper;
import com.vortexbird.vorteam.service.VtAsignacionService;

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
@RequestMapping("/vtAsignacion")
public class VtAsignacionRestController {
    private static final Logger log = LoggerFactory.getLogger(VtAsignacionRestController.class);
    @Autowired
    private VtAsignacionService vtAsignacionService;
    @Autowired
    private VtAsignacionMapper vtAsignacionMapper;

    @PostMapping(value = "/saveVtAsignacion")
    public void saveVtAsignacion(@RequestBody
    VtAsignacionDTO vtAsignacionDTO) throws Exception {
        try {
            VtAsignacion vtAsignacion = vtAsignacionMapper.vtAsignacionDTOToVtAsignacion(vtAsignacionDTO);

            vtAsignacionService.saveVtAsignacion(vtAsignacion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteVtAsignacion/{asigId}")
    public void deleteVtAsignacion(@PathVariable("asigId")
    Long asigId) throws Exception {
        try {
            VtAsignacion vtAsignacion = vtAsignacionService.getVtAsignacion(asigId);

            vtAsignacionService.deleteVtAsignacion(vtAsignacion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateVtAsignacion/")
    public void updateVtAsignacion(@RequestBody
    VtAsignacionDTO vtAsignacionDTO) throws Exception {
        try {
            VtAsignacion vtAsignacion = vtAsignacionMapper.vtAsignacionDTOToVtAsignacion(vtAsignacionDTO);

            vtAsignacionService.updateVtAsignacion(vtAsignacion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataVtAsignacion")
    public List<VtAsignacionDTO> getDataVtAsignacion()
        throws Exception {
        try {
            return vtAsignacionService.getDataVtAsignacion();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getVtAsignacion/{asigId}")
    public VtAsignacionDTO getVtAsignacion(@PathVariable("asigId")
    Long asigId) throws Exception {
        try {
            VtAsignacion vtAsignacion = vtAsignacionService.getVtAsignacion(asigId);

            return vtAsignacionMapper.vtAsignacionToVtAsignacionDTO(vtAsignacion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
