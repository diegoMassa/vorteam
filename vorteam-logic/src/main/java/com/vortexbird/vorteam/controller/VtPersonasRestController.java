package com.vortexbird.vorteam.controller;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.dto.VtPersonasDTO;
import com.vortexbird.vorteam.mapper.VtPersonasMapper;
import com.vortexbird.vorteam.service.VtPersonasService;

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
@RequestMapping("/vtPersonas")
public class VtPersonasRestController {
    private static final Logger log = LoggerFactory.getLogger(VtPersonasRestController.class);
    @Autowired
    private VtPersonasService vtPersonasService;
    @Autowired
    private VtPersonasMapper vtPersonasMapper;

    @PostMapping(value = "/saveVtPersonas")
    public void saveVtPersonas(@RequestBody
    VtPersonasDTO vtPersonasDTO) throws Exception {
        try {
            VtPersonas vtPersonas = vtPersonasMapper.vtPersonasDTOToVtPersonas(vtPersonasDTO);

            vtPersonasService.saveVtPersonas(vtPersonas);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteVtPersonas/{persId}")
    public void deleteVtPersonas(@PathVariable("persId")
    Long persId) throws Exception {
        try {
            VtPersonas vtPersonas = vtPersonasService.getVtPersonas(persId);

            vtPersonasService.deleteVtPersonas(vtPersonas);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateVtPersonas/")
    public void updateVtPersonas(@RequestBody
    VtPersonasDTO vtPersonasDTO) throws Exception {
        try {
            VtPersonas vtPersonas = vtPersonasMapper.vtPersonasDTOToVtPersonas(vtPersonasDTO);

            vtPersonasService.updateVtPersonas(vtPersonas);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataVtPersonas")
    public List<VtPersonasDTO> getDataVtPersonas() throws Exception {
        try {
            return vtPersonasService.getDataVtPersonas();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getVtPersonas/{persId}")
    public VtPersonasDTO getVtPersonas(@PathVariable("persId")
    Long persId) throws Exception {
        try {
            VtPersonas vtPersonas = vtPersonasService.getVtPersonas(persId);

            return vtPersonasMapper.vtPersonasToVtPersonasDTO(vtPersonas);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
