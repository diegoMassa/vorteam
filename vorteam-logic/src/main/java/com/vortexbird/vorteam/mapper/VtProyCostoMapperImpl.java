package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.domain.VtProyCosto;
import com.vortexbird.vorteam.dto.VtProyCostoDTO;
import com.vortexbird.vorteam.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Component
@Scope("singleton")
public class VtProyCostoMapperImpl implements VtProyCostoMapper {
    private static final Logger log = LoggerFactory.getLogger(VtProyCostoMapperImpl.class);

    /**
    * Service injected by Spring that manages VtPersonas entities
    *
    */
    @Autowired
    VtPersonasService serviceVtPersonas1;

    /**
    * Service injected by Spring that manages VtProyecto entities
    *
    */
    @Autowired
    VtProyectoService serviceVtProyecto2;

    @Transactional(readOnly = true)
    public VtProyCostoDTO vtProyCostoToVtProyCostoDTO(VtProyCosto vtProyCosto)
        throws Exception {
        try {
            VtProyCostoDTO vtProyCostoDTO = new VtProyCostoDTO();

            vtProyCostoDTO.setPrcoId(vtProyCosto.getPrcoId());
            vtProyCostoDTO.setActivo((vtProyCosto.getActivo() != null)
                ? vtProyCosto.getActivo() : null);
            vtProyCostoDTO.setFechaCreacion(vtProyCosto.getFechaCreacion());
            vtProyCostoDTO.setFechaModificacion(vtProyCosto.getFechaModificacion());
            vtProyCostoDTO.setUsuaCreador((vtProyCosto.getUsuaCreador() != null)
                ? vtProyCosto.getUsuaCreador() : null);
            vtProyCostoDTO.setUsuaModificador((vtProyCosto.getUsuaModificador() != null)
                ? vtProyCosto.getUsuaModificador() : null);
            vtProyCostoDTO.setValorHora((vtProyCosto.getValorHora() != null)
                ? vtProyCosto.getValorHora() : null);
            vtProyCostoDTO.setPersId_VtPersonas((vtProyCosto.getVtPersonas()
                                                            .getPersId() != null)
                ? vtProyCosto.getVtPersonas().getPersId() : null);
            vtProyCostoDTO.setProyId_VtProyecto((vtProyCosto.getVtProyecto()
                                                            .getProyId() != null)
                ? vtProyCosto.getVtProyecto().getProyId() : null);

            return vtProyCostoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtProyCosto vtProyCostoDTOToVtProyCosto(
        VtProyCostoDTO vtProyCostoDTO) throws Exception {
        try {
            VtProyCosto vtProyCosto = new VtProyCosto();

            vtProyCosto.setPrcoId(vtProyCostoDTO.getPrcoId());
            vtProyCosto.setActivo((vtProyCostoDTO.getActivo() != null)
                ? vtProyCostoDTO.getActivo() : null);
            vtProyCosto.setFechaCreacion(vtProyCostoDTO.getFechaCreacion());
            vtProyCosto.setFechaModificacion(vtProyCostoDTO.getFechaModificacion());
            vtProyCosto.setUsuaCreador((vtProyCostoDTO.getUsuaCreador() != null)
                ? vtProyCostoDTO.getUsuaCreador() : null);
            vtProyCosto.setUsuaModificador((vtProyCostoDTO.getUsuaModificador() != null)
                ? vtProyCostoDTO.getUsuaModificador() : null);
            vtProyCosto.setValorHora((vtProyCostoDTO.getValorHora() != null)
                ? vtProyCostoDTO.getValorHora() : null);

            VtPersonas vtPersonas = new VtPersonas();

            if (vtProyCostoDTO.getPersId_VtPersonas() != null) {
                vtPersonas = serviceVtPersonas1.getVtPersonas(vtProyCostoDTO.getPersId_VtPersonas());
            }

            if (vtPersonas != null) {
                vtProyCosto.setVtPersonas(vtPersonas);
            }

            VtProyecto vtProyecto = new VtProyecto();

            if (vtProyCostoDTO.getProyId_VtProyecto() != null) {
                vtProyecto = serviceVtProyecto2.getVtProyecto(vtProyCostoDTO.getProyId_VtProyecto());
            }

            if (vtProyecto != null) {
                vtProyCosto.setVtProyecto(vtProyecto);
            }

            return vtProyCosto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtProyCostoDTO> listVtProyCostoToListVtProyCostoDTO(
        List<VtProyCosto> listVtProyCosto) throws Exception {
        try {
            List<VtProyCostoDTO> vtProyCostoDTOs = new ArrayList<VtProyCostoDTO>();

            for (VtProyCosto vtProyCosto : listVtProyCosto) {
                VtProyCostoDTO vtProyCostoDTO = vtProyCostoToVtProyCostoDTO(vtProyCosto);

                vtProyCostoDTOs.add(vtProyCostoDTO);
            }

            return vtProyCostoDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtProyCosto> listVtProyCostoDTOToListVtProyCosto(
        List<VtProyCostoDTO> listVtProyCostoDTO) throws Exception {
        try {
            List<VtProyCosto> listVtProyCosto = new ArrayList<VtProyCosto>();

            for (VtProyCostoDTO vtProyCostoDTO : listVtProyCostoDTO) {
                VtProyCosto vtProyCosto = vtProyCostoDTOToVtProyCosto(vtProyCostoDTO);

                listVtProyCosto.add(vtProyCosto);
            }

            return listVtProyCosto;
        } catch (Exception e) {
            throw e;
        }
    }
}
