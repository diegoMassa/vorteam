package com.vortexbird.vorteam.controller;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.dto.VtTipoIdentificacionDTO;
import com.vortexbird.vorteam.mapper.VtTipoIdentificacionMapper;
import com.vortexbird.vorteam.service.VtTipoIdentificacionService;

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
@RequestMapping("/vtTipoIdentificacion")
public class VtTipoIdentificacionRestController {
    private static final Logger log = LoggerFactory.getLogger(VtTipoIdentificacionRestController.class);
    @Autowired
    private VtTipoIdentificacionService vtTipoIdentificacionService;
    @Autowired
    private VtTipoIdentificacionMapper vtTipoIdentificacionMapper;

    @PostMapping(value = "/saveVtTipoIdentificacion")
    public void saveVtTipoIdentificacion(
        @RequestBody
    VtTipoIdentificacionDTO vtTipoIdentificacionDTO) throws Exception {
        try {
            VtTipoIdentificacion vtTipoIdentificacion = vtTipoIdentificacionMapper.vtTipoIdentificacionDTOToVtTipoIdentificacion(vtTipoIdentificacionDTO);

            vtTipoIdentificacionService.saveVtTipoIdentificacion(vtTipoIdentificacion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteVtTipoIdentificacion/{tiidId}")
    public void deleteVtTipoIdentificacion(@PathVariable("tiidId")
    Long tiidId) throws Exception {
        try {
            VtTipoIdentificacion vtTipoIdentificacion = vtTipoIdentificacionService.getVtTipoIdentificacion(tiidId);

            vtTipoIdentificacionService.deleteVtTipoIdentificacion(vtTipoIdentificacion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateVtTipoIdentificacion/")
    public void updateVtTipoIdentificacion(
        @RequestBody
    VtTipoIdentificacionDTO vtTipoIdentificacionDTO) throws Exception {
        try {
            VtTipoIdentificacion vtTipoIdentificacion = vtTipoIdentificacionMapper.vtTipoIdentificacionDTOToVtTipoIdentificacion(vtTipoIdentificacionDTO);

            vtTipoIdentificacionService.updateVtTipoIdentificacion(vtTipoIdentificacion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataVtTipoIdentificacion")
    public List<VtTipoIdentificacionDTO> getDataVtTipoIdentificacion()
        throws Exception {
        try {
            return vtTipoIdentificacionService.getDataVtTipoIdentificacion();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getVtTipoIdentificacion/{tiidId}")
    public VtTipoIdentificacionDTO getVtTipoIdentificacion(
        @PathVariable("tiidId")
    Long tiidId) throws Exception {
        try {
            VtTipoIdentificacion vtTipoIdentificacion = vtTipoIdentificacionService.getVtTipoIdentificacion(tiidId);

            return vtTipoIdentificacionMapper.vtTipoIdentificacionToVtTipoIdentificacionDTO(vtTipoIdentificacion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
