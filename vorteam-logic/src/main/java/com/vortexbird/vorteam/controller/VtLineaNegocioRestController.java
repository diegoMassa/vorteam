package com.vortexbird.vorteam.controller;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.dto.VtLineaNegocioDTO;
import com.vortexbird.vorteam.mapper.VtLineaNegocioMapper;
import com.vortexbird.vorteam.service.VtLineaNegocioService;

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
@RequestMapping("/vtLineaNegocio")
public class VtLineaNegocioRestController {
    private static final Logger log = LoggerFactory.getLogger(VtLineaNegocioRestController.class);
    @Autowired
    private VtLineaNegocioService vtLineaNegocioService;
    @Autowired
    private VtLineaNegocioMapper vtLineaNegocioMapper;

    @PostMapping(value = "/saveVtLineaNegocio")
    public void saveVtLineaNegocio(
        @RequestBody
    VtLineaNegocioDTO vtLineaNegocioDTO) throws Exception {
        try {
            VtLineaNegocio vtLineaNegocio = vtLineaNegocioMapper.vtLineaNegocioDTOToVtLineaNegocio(vtLineaNegocioDTO);

            vtLineaNegocioService.saveVtLineaNegocio(vtLineaNegocio);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteVtLineaNegocio/{lineId}")
    public void deleteVtLineaNegocio(@PathVariable("lineId")
    Long lineId) throws Exception {
        try {
            VtLineaNegocio vtLineaNegocio = vtLineaNegocioService.getVtLineaNegocio(lineId);

            vtLineaNegocioService.deleteVtLineaNegocio(vtLineaNegocio);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateVtLineaNegocio/")
    public void updateVtLineaNegocio(
        @RequestBody
    VtLineaNegocioDTO vtLineaNegocioDTO) throws Exception {
        try {
            VtLineaNegocio vtLineaNegocio = vtLineaNegocioMapper.vtLineaNegocioDTOToVtLineaNegocio(vtLineaNegocioDTO);

            vtLineaNegocioService.updateVtLineaNegocio(vtLineaNegocio);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataVtLineaNegocio")
    public List<VtLineaNegocioDTO> getDataVtLineaNegocio()
        throws Exception {
        try {
            return vtLineaNegocioService.getDataVtLineaNegocio();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getVtLineaNegocio/{lineId}")
    public VtLineaNegocioDTO getVtLineaNegocio(
        @PathVariable("lineId")
    Long lineId) throws Exception {
        try {
            VtLineaNegocio vtLineaNegocio = vtLineaNegocioService.getVtLineaNegocio(lineId);

            return vtLineaNegocioMapper.vtLineaNegocioToVtLineaNegocioDTO(vtLineaNegocio);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
