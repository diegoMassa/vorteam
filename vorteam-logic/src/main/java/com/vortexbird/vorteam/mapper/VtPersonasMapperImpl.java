package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.domain.VtPersonas;
import com.vortexbird.vorteam.dto.VtPersonasDTO;
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
public class VtPersonasMapperImpl implements VtPersonasMapper {
    private static final Logger log = LoggerFactory.getLogger(VtPersonasMapperImpl.class);

    @Transactional(readOnly = true)
    public VtPersonasDTO vtPersonasToVtPersonasDTO(VtPersonas vtPersonas)
        throws Exception {
        try {
            VtPersonasDTO vtPersonasDTO = new VtPersonasDTO();

            vtPersonasDTO.setPersId(vtPersonas.getPersId());
            vtPersonasDTO.setActivo((vtPersonas.getActivo() != null)
                ? vtPersonas.getActivo() : null);
            vtPersonasDTO.setApellidos((vtPersonas.getApellidos() != null)
                ? vtPersonas.getApellidos() : null);
            vtPersonasDTO.setEmail((vtPersonas.getEmail() != null)
                ? vtPersonas.getEmail() : null);
            vtPersonasDTO.setFechaCreacion(vtPersonas.getFechaCreacion());
            vtPersonasDTO.setFechaModificacion(vtPersonas.getFechaModificacion());
            vtPersonasDTO.setNombre((vtPersonas.getNombre() != null)
                ? vtPersonas.getNombre() : null);
            vtPersonasDTO.setSalario((vtPersonas.getSalario() != null)
                ? vtPersonas.getSalario() : null);
            vtPersonasDTO.setUsuaCreador((vtPersonas.getUsuaCreador() != null)
                ? vtPersonas.getUsuaCreador() : null);
            vtPersonasDTO.setUsuaModificador((vtPersonas.getUsuaModificador() != null)
                ? vtPersonas.getUsuaModificador() : null);
            vtPersonasDTO.setValorHora((vtPersonas.getValorHora() != null)
                ? vtPersonas.getValorHora() : null);

            return vtPersonasDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtPersonas vtPersonasDTOToVtPersonas(VtPersonasDTO vtPersonasDTO)
        throws Exception {
        try {
            VtPersonas vtPersonas = new VtPersonas();

            vtPersonas.setPersId(vtPersonasDTO.getPersId());
            vtPersonas.setActivo((vtPersonasDTO.getActivo() != null)
                ? vtPersonasDTO.getActivo() : null);
            vtPersonas.setApellidos((vtPersonasDTO.getApellidos() != null)
                ? vtPersonasDTO.getApellidos() : null);
            vtPersonas.setEmail((vtPersonasDTO.getEmail() != null)
                ? vtPersonasDTO.getEmail() : null);
            vtPersonas.setFechaCreacion(vtPersonasDTO.getFechaCreacion());
            vtPersonas.setFechaModificacion(vtPersonasDTO.getFechaModificacion());
            vtPersonas.setNombre((vtPersonasDTO.getNombre() != null)
                ? vtPersonasDTO.getNombre() : null);
            vtPersonas.setSalario((vtPersonasDTO.getSalario() != null)
                ? vtPersonasDTO.getSalario() : null);
            vtPersonas.setUsuaCreador((vtPersonasDTO.getUsuaCreador() != null)
                ? vtPersonasDTO.getUsuaCreador() : null);
            vtPersonas.setUsuaModificador((vtPersonasDTO.getUsuaModificador() != null)
                ? vtPersonasDTO.getUsuaModificador() : null);
            vtPersonas.setValorHora((vtPersonasDTO.getValorHora() != null)
                ? vtPersonasDTO.getValorHora() : null);

            return vtPersonas;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtPersonasDTO> listVtPersonasToListVtPersonasDTO(
        List<VtPersonas> listVtPersonas) throws Exception {
        try {
            List<VtPersonasDTO> vtPersonasDTOs = new ArrayList<VtPersonasDTO>();

            for (VtPersonas vtPersonas : listVtPersonas) {
                VtPersonasDTO vtPersonasDTO = vtPersonasToVtPersonasDTO(vtPersonas);

                vtPersonasDTOs.add(vtPersonasDTO);
            }

            return vtPersonasDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtPersonas> listVtPersonasDTOToListVtPersonas(
        List<VtPersonasDTO> listVtPersonasDTO) throws Exception {
        try {
            List<VtPersonas> listVtPersonas = new ArrayList<VtPersonas>();

            for (VtPersonasDTO vtPersonasDTO : listVtPersonasDTO) {
                VtPersonas vtPersonas = vtPersonasDTOToVtPersonas(vtPersonasDTO);

                listVtPersonas.add(vtPersonas);
            }

            return listVtPersonas;
        } catch (Exception e) {
            throw e;
        }
    }
}
