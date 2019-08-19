package com.vortexbird.vorteam.controller;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.dto.VtEstadoDTO;
import com.vortexbird.vorteam.mapper.VtEstadoMapper;
import com.vortexbird.vorteam.service.VtEstadoService;

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
@RequestMapping("/vtEstado")
public class VtEstadoRestController {
    private static final Logger log = LoggerFactory.getLogger(VtEstadoRestController.class);
    @Autowired
    private VtEstadoService vtEstadoService;
    @Autowired
    private VtEstadoMapper vtEstadoMapper;

    @PostMapping(value = "/saveVtEstado")
    public void saveVtEstado(@RequestBody
    VtEstadoDTO vtEstadoDTO) throws Exception {
        try {
            VtEstado vtEstado = vtEstadoMapper.vtEstadoDTOToVtEstado(vtEstadoDTO);

            vtEstadoService.saveVtEstado(vtEstado);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteVtEstado/{estaId}")
    public void deleteVtEstado(@PathVariable("estaId")
    Long estaId) throws Exception {
        try {
            VtEstado vtEstado = vtEstadoService.getVtEstado(estaId);

            vtEstadoService.deleteVtEstado(vtEstado);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateVtEstado/")
    public void updateVtEstado(@RequestBody
    VtEstadoDTO vtEstadoDTO) throws Exception {
        try {
            VtEstado vtEstado = vtEstadoMapper.vtEstadoDTOToVtEstado(vtEstadoDTO);

            vtEstadoService.updateVtEstado(vtEstado);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataVtEstado")
    public List<VtEstadoDTO> getDataVtEstado() throws Exception {
        try {
            return vtEstadoService.getDataVtEstado();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getVtEstado/{estaId}")
    public VtEstadoDTO getVtEstado(@PathVariable("estaId")
    Long estaId) throws Exception {
        try {
            VtEstado vtEstado = vtEstadoService.getVtEstado(estaId);

            return vtEstadoMapper.vtEstadoToVtEstadoDTO(vtEstado);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
