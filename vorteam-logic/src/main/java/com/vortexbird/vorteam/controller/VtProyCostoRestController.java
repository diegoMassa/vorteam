package com.vortexbird.vorteam.controller;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.dto.VtProyCostoDTO;
import com.vortexbird.vorteam.mapper.VtProyCostoMapper;
import com.vortexbird.vorteam.service.VtProyCostoService;

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
@RequestMapping("/vtProyCosto")
public class VtProyCostoRestController {
    private static final Logger log = LoggerFactory.getLogger(VtProyCostoRestController.class);
    @Autowired
    private VtProyCostoService vtProyCostoService;
    @Autowired
    private VtProyCostoMapper vtProyCostoMapper;

    @PostMapping(value = "/saveVtProyCosto")
    public void saveVtProyCosto(@RequestBody
    VtProyCostoDTO vtProyCostoDTO) throws Exception {
        try {
            VtProyCosto vtProyCosto = vtProyCostoMapper.vtProyCostoDTOToVtProyCosto(vtProyCostoDTO);

            vtProyCostoService.saveVtProyCosto(vtProyCosto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteVtProyCosto/{prcoId}")
    public void deleteVtProyCosto(@PathVariable("prcoId")
    Long prcoId) throws Exception {
        try {
            VtProyCosto vtProyCosto = vtProyCostoService.getVtProyCosto(prcoId);

            vtProyCostoService.deleteVtProyCosto(vtProyCosto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateVtProyCosto/")
    public void updateVtProyCosto(@RequestBody
    VtProyCostoDTO vtProyCostoDTO) throws Exception {
        try {
            VtProyCosto vtProyCosto = vtProyCostoMapper.vtProyCostoDTOToVtProyCosto(vtProyCostoDTO);

            vtProyCostoService.updateVtProyCosto(vtProyCosto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataVtProyCosto")
    public List<VtProyCostoDTO> getDataVtProyCosto() throws Exception {
        try {
            return vtProyCostoService.getDataVtProyCosto();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getVtProyCosto/{prcoId}")
    public VtProyCostoDTO getVtProyCosto(@PathVariable("prcoId")
    Long prcoId) throws Exception {
        try {
            VtProyCosto vtProyCosto = vtProyCostoService.getVtProyCosto(prcoId);

            return vtProyCostoMapper.vtProyCostoToVtProyCostoDTO(vtProyCosto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
