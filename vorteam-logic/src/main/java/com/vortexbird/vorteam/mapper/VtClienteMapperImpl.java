package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.domain.VtCliente;
import com.vortexbird.vorteam.dto.VtClienteDTO;
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
public class VtClienteMapperImpl implements VtClienteMapper {
    private static final Logger log = LoggerFactory.getLogger(VtClienteMapperImpl.class);

    /**
    * Service injected by Spring that manages VtTipoIdentificacion entities
    *
    */
    @Autowired
    VtTipoIdentificacionService serviceVtTipoIdentificacion1;

    @Transactional(readOnly = true)
    public VtClienteDTO vtClienteToVtClienteDTO(VtCliente vtCliente)
        throws Exception {
        try {
            VtClienteDTO vtClienteDTO = new VtClienteDTO();

            vtClienteDTO.setClieId(vtCliente.getClieId());
            vtClienteDTO.setActivo((vtCliente.getActivo() != null)
                ? vtCliente.getActivo() : null);
            vtClienteDTO.setFechaCreacion(vtCliente.getFechaCreacion());
            vtClienteDTO.setFechaModificacion(vtCliente.getFechaModificacion());
            vtClienteDTO.setIdentificacion((vtCliente.getIdentificacion() != null)
                ? vtCliente.getIdentificacion() : null);
            vtClienteDTO.setNombreRazonSocial((vtCliente.getNombreRazonSocial() != null)
                ? vtCliente.getNombreRazonSocial() : null);
            vtClienteDTO.setUsuaCreador((vtCliente.getUsuaCreador() != null)
                ? vtCliente.getUsuaCreador() : null);
            vtClienteDTO.setUsuaModificador((vtCliente.getUsuaModificador() != null)
                ? vtCliente.getUsuaModificador() : null);
            vtClienteDTO.setTiidId_VtTipoIdentificacion((vtCliente.getVtTipoIdentificacion()
                                                                  .getTiidId() != null)
                ? vtCliente.getVtTipoIdentificacion().getTiidId() : null);

            return vtClienteDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtCliente vtClienteDTOToVtCliente(VtClienteDTO vtClienteDTO)
        throws Exception {
        try {
            VtCliente vtCliente = new VtCliente();

            vtCliente.setClieId(vtClienteDTO.getClieId());
            vtCliente.setActivo((vtClienteDTO.getActivo() != null)
                ? vtClienteDTO.getActivo() : null);
            vtCliente.setFechaCreacion(vtClienteDTO.getFechaCreacion());
            vtCliente.setFechaModificacion(vtClienteDTO.getFechaModificacion());
            vtCliente.setIdentificacion((vtClienteDTO.getIdentificacion() != null)
                ? vtClienteDTO.getIdentificacion() : null);
            vtCliente.setNombreRazonSocial((vtClienteDTO.getNombreRazonSocial() != null)
                ? vtClienteDTO.getNombreRazonSocial() : null);
            vtCliente.setUsuaCreador((vtClienteDTO.getUsuaCreador() != null)
                ? vtClienteDTO.getUsuaCreador() : null);
            vtCliente.setUsuaModificador((vtClienteDTO.getUsuaModificador() != null)
                ? vtClienteDTO.getUsuaModificador() : null);

            VtTipoIdentificacion vtTipoIdentificacion = new VtTipoIdentificacion();

            if (vtClienteDTO.getTiidId_VtTipoIdentificacion() != null) {
                vtTipoIdentificacion = serviceVtTipoIdentificacion1.getVtTipoIdentificacion(vtClienteDTO.getTiidId_VtTipoIdentificacion());
            }

            if (vtTipoIdentificacion != null) {
                vtCliente.setVtTipoIdentificacion(vtTipoIdentificacion);
            }

            return vtCliente;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtClienteDTO> listVtClienteToListVtClienteDTO(
        List<VtCliente> listVtCliente) throws Exception {
        try {
            List<VtClienteDTO> vtClienteDTOs = new ArrayList<VtClienteDTO>();

            for (VtCliente vtCliente : listVtCliente) {
                VtClienteDTO vtClienteDTO = vtClienteToVtClienteDTO(vtCliente);

                vtClienteDTOs.add(vtClienteDTO);
            }

            return vtClienteDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtCliente> listVtClienteDTOToListVtCliente(
        List<VtClienteDTO> listVtClienteDTO) throws Exception {
        try {
            List<VtCliente> listVtCliente = new ArrayList<VtCliente>();

            for (VtClienteDTO vtClienteDTO : listVtClienteDTO) {
                VtCliente vtCliente = vtClienteDTOToVtCliente(vtClienteDTO);

                listVtCliente.add(vtCliente);
            }

            return listVtCliente;
        } catch (Exception e) {
            throw e;
        }
    }
}
