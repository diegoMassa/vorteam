package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.domain.VtLineaNegocio;
import com.vortexbird.vorteam.dto.VtLineaNegocioDTO;
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
public class VtLineaNegocioMapperImpl implements VtLineaNegocioMapper {
    private static final Logger log = LoggerFactory.getLogger(VtLineaNegocioMapperImpl.class);

    @Transactional(readOnly = true)
    public VtLineaNegocioDTO vtLineaNegocioToVtLineaNegocioDTO(
        VtLineaNegocio vtLineaNegocio) throws Exception {
        try {
            VtLineaNegocioDTO vtLineaNegocioDTO = new VtLineaNegocioDTO();

            vtLineaNegocioDTO.setLineId(vtLineaNegocio.getLineId());
            vtLineaNegocioDTO.setActivo((vtLineaNegocio.getActivo() != null)
                ? vtLineaNegocio.getActivo() : null);
            vtLineaNegocioDTO.setDescripcion((vtLineaNegocio.getDescripcion() != null)
                ? vtLineaNegocio.getDescripcion() : null);
            vtLineaNegocioDTO.setFechaCreacion(vtLineaNegocio.getFechaCreacion());
            vtLineaNegocioDTO.setFechaModificacion(vtLineaNegocio.getFechaModificacion());
            vtLineaNegocioDTO.setUsuaCreador((vtLineaNegocio.getUsuaCreador() != null)
                ? vtLineaNegocio.getUsuaCreador() : null);
            vtLineaNegocioDTO.setUsuaModificador((vtLineaNegocio.getUsuaModificador() != null)
                ? vtLineaNegocio.getUsuaModificador() : null);

            return vtLineaNegocioDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtLineaNegocio vtLineaNegocioDTOToVtLineaNegocio(
        VtLineaNegocioDTO vtLineaNegocioDTO) throws Exception {
        try {
            VtLineaNegocio vtLineaNegocio = new VtLineaNegocio();

            vtLineaNegocio.setLineId(vtLineaNegocioDTO.getLineId());
            vtLineaNegocio.setActivo((vtLineaNegocioDTO.getActivo() != null)
                ? vtLineaNegocioDTO.getActivo() : null);
            vtLineaNegocio.setDescripcion((vtLineaNegocioDTO.getDescripcion() != null)
                ? vtLineaNegocioDTO.getDescripcion() : null);
            vtLineaNegocio.setFechaCreacion(vtLineaNegocioDTO.getFechaCreacion());
            vtLineaNegocio.setFechaModificacion(vtLineaNegocioDTO.getFechaModificacion());
            vtLineaNegocio.setUsuaCreador((vtLineaNegocioDTO.getUsuaCreador() != null)
                ? vtLineaNegocioDTO.getUsuaCreador() : null);
            vtLineaNegocio.setUsuaModificador((vtLineaNegocioDTO.getUsuaModificador() != null)
                ? vtLineaNegocioDTO.getUsuaModificador() : null);

            return vtLineaNegocio;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtLineaNegocioDTO> listVtLineaNegocioToListVtLineaNegocioDTO(
        List<VtLineaNegocio> listVtLineaNegocio) throws Exception {
        try {
            List<VtLineaNegocioDTO> vtLineaNegocioDTOs = new ArrayList<VtLineaNegocioDTO>();

            for (VtLineaNegocio vtLineaNegocio : listVtLineaNegocio) {
                VtLineaNegocioDTO vtLineaNegocioDTO = vtLineaNegocioToVtLineaNegocioDTO(vtLineaNegocio);

                vtLineaNegocioDTOs.add(vtLineaNegocioDTO);
            }

            return vtLineaNegocioDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtLineaNegocio> listVtLineaNegocioDTOToListVtLineaNegocio(
        List<VtLineaNegocioDTO> listVtLineaNegocioDTO)
        throws Exception {
        try {
            List<VtLineaNegocio> listVtLineaNegocio = new ArrayList<VtLineaNegocio>();

            for (VtLineaNegocioDTO vtLineaNegocioDTO : listVtLineaNegocioDTO) {
                VtLineaNegocio vtLineaNegocio = vtLineaNegocioDTOToVtLineaNegocio(vtLineaNegocioDTO);

                listVtLineaNegocio.add(vtLineaNegocio);
            }

            return listVtLineaNegocio;
        } catch (Exception e) {
            throw e;
        }
    }
}
