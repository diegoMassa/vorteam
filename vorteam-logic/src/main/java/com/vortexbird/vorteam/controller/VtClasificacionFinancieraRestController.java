package com.vortexbird.vorteam.controller;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.dto.VtClasificacionFinancieraDTO;
import com.vortexbird.vorteam.mapper.VtClasificacionFinancieraMapper;
import com.vortexbird.vorteam.service.VtClasificacionFinancieraService;

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
@RequestMapping("/vtClasificacionFinanciera")
public class VtClasificacionFinancieraRestController {
    private static final Logger log = LoggerFactory.getLogger(VtClasificacionFinancieraRestController.class);
    @Autowired
    private VtClasificacionFinancieraService vtClasificacionFinancieraService;
    @Autowired
    private VtClasificacionFinancieraMapper vtClasificacionFinancieraMapper;

    @PostMapping(value = "/saveVtClasificacionFinanciera")
    public void saveVtClasificacionFinanciera(
        @RequestBody
    VtClasificacionFinancieraDTO vtClasificacionFinancieraDTO)
        throws Exception {
        try {
            VtClasificacionFinanciera vtClasificacionFinanciera = vtClasificacionFinancieraMapper.vtClasificacionFinancieraDTOToVtClasificacionFinanciera(vtClasificacionFinancieraDTO);

            vtClasificacionFinancieraService.saveVtClasificacionFinanciera(vtClasificacionFinanciera);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteVtClasificacionFinanciera/{clfiId}")
    public void deleteVtClasificacionFinanciera(
        @PathVariable("clfiId")
    Long clfiId) throws Exception {
        try {
            VtClasificacionFinanciera vtClasificacionFinanciera = vtClasificacionFinancieraService.getVtClasificacionFinanciera(clfiId);

            vtClasificacionFinancieraService.deleteVtClasificacionFinanciera(vtClasificacionFinanciera);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateVtClasificacionFinanciera/")
    public void updateVtClasificacionFinanciera(
        @RequestBody
    VtClasificacionFinancieraDTO vtClasificacionFinancieraDTO)
        throws Exception {
        try {
            VtClasificacionFinanciera vtClasificacionFinanciera = vtClasificacionFinancieraMapper.vtClasificacionFinancieraDTOToVtClasificacionFinanciera(vtClasificacionFinancieraDTO);

            vtClasificacionFinancieraService.updateVtClasificacionFinanciera(vtClasificacionFinanciera);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataVtClasificacionFinanciera")
    public List<VtClasificacionFinancieraDTO> getDataVtClasificacionFinanciera()
        throws Exception {
        try {
            return vtClasificacionFinancieraService.getDataVtClasificacionFinanciera();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getVtClasificacionFinanciera/{clfiId}")
    public VtClasificacionFinancieraDTO getVtClasificacionFinanciera(
        @PathVariable("clfiId")
    Long clfiId) throws Exception {
        try {
            VtClasificacionFinanciera vtClasificacionFinanciera = vtClasificacionFinancieraService.getVtClasificacionFinanciera(clfiId);

            return vtClasificacionFinancieraMapper.vtClasificacionFinancieraToVtClasificacionFinancieraDTO(vtClasificacionFinanciera);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
