package com.vortexbird.vorteam.controller;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.dto.VtActividadDTO;
import com.vortexbird.vorteam.mapper.VtActividadMapper;
import com.vortexbird.vorteam.service.VtActividadService;

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
@RequestMapping("/vtActividad")
public class VtActividadRestController {
    private static final Logger log = LoggerFactory.getLogger(VtActividadRestController.class);
    @Autowired
    private VtActividadService vtActividadService;
    @Autowired
    private VtActividadMapper vtActividadMapper;

    @PostMapping(value = "/saveVtActividad")
    public void saveVtActividad(@RequestBody
    VtActividadDTO vtActividadDTO) throws Exception {
        try {
            VtActividad vtActividad = vtActividadMapper.vtActividadDTOToVtActividad(vtActividadDTO);

            vtActividadService.saveVtActividad(vtActividad);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteVtActividad/{actiId}")
    public void deleteVtActividad(@PathVariable("actiId")
    Long actiId) throws Exception {
        try {
            VtActividad vtActividad = vtActividadService.getVtActividad(actiId);

            vtActividadService.deleteVtActividad(vtActividad);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateVtActividad/")
    public void updateVtActividad(@RequestBody
    VtActividadDTO vtActividadDTO) throws Exception {
        try {
            VtActividad vtActividad = vtActividadMapper.vtActividadDTOToVtActividad(vtActividadDTO);

            vtActividadService.updateVtActividad(vtActividad);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataVtActividad")
    public List<VtActividadDTO> getDataVtActividad() throws Exception {
        try {
            return vtActividadService.getDataVtActividad();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getVtActividad/{actiId}")
    public VtActividadDTO getVtActividad(@PathVariable("actiId")
    Long actiId) throws Exception {
        try {
            VtActividad vtActividad = vtActividadService.getVtActividad(actiId);

            return vtActividadMapper.vtActividadToVtActividadDTO(vtActividad);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
