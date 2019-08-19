package com.vortexbird.vorteam.controller;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.dto.VtClienteDTO;
import com.vortexbird.vorteam.mapper.VtClienteMapper;
import com.vortexbird.vorteam.service.VtClienteService;

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
@RequestMapping("/vtCliente")
public class VtClienteRestController {
    private static final Logger log = LoggerFactory.getLogger(VtClienteRestController.class);
    @Autowired
    private VtClienteService vtClienteService;
    @Autowired
    private VtClienteMapper vtClienteMapper;

    @PostMapping(value = "/saveVtCliente")
    public void saveVtCliente(@RequestBody
    VtClienteDTO vtClienteDTO) throws Exception {
        try {
            VtCliente vtCliente = vtClienteMapper.vtClienteDTOToVtCliente(vtClienteDTO);

            vtClienteService.saveVtCliente(vtCliente);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteVtCliente/{clieId}")
    public void deleteVtCliente(@PathVariable("clieId")
    Long clieId) throws Exception {
        try {
            VtCliente vtCliente = vtClienteService.getVtCliente(clieId);

            vtClienteService.deleteVtCliente(vtCliente);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateVtCliente/")
    public void updateVtCliente(@RequestBody
    VtClienteDTO vtClienteDTO) throws Exception {
        try {
            VtCliente vtCliente = vtClienteMapper.vtClienteDTOToVtCliente(vtClienteDTO);

            vtClienteService.updateVtCliente(vtCliente);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataVtCliente")
    public List<VtClienteDTO> getDataVtCliente() throws Exception {
        try {
            return vtClienteService.getDataVtCliente();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getVtCliente/{clieId}")
    public VtClienteDTO getVtCliente(@PathVariable("clieId")
    Long clieId) throws Exception {
        try {
            VtCliente vtCliente = vtClienteService.getVtCliente(clieId);

            return vtClienteMapper.vtClienteToVtClienteDTO(vtCliente);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
