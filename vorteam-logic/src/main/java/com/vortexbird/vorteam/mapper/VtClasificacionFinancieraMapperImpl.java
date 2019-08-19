package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.domain.VtClasificacionFinanciera;
import com.vortexbird.vorteam.dto.VtClasificacionFinancieraDTO;
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
public class VtClasificacionFinancieraMapperImpl
    implements VtClasificacionFinancieraMapper {
    private static final Logger log = LoggerFactory.getLogger(VtClasificacionFinancieraMapperImpl.class);

    @Transactional(readOnly = true)
    public VtClasificacionFinancieraDTO vtClasificacionFinancieraToVtClasificacionFinancieraDTO(
        VtClasificacionFinanciera vtClasificacionFinanciera)
        throws Exception {
        try {
            VtClasificacionFinancieraDTO vtClasificacionFinancieraDTO = new VtClasificacionFinancieraDTO();

            vtClasificacionFinancieraDTO.setClfiId(vtClasificacionFinanciera.getClfiId());
            vtClasificacionFinancieraDTO.setActivo((vtClasificacionFinanciera.getActivo() != null)
                ? vtClasificacionFinanciera.getActivo() : null);
            vtClasificacionFinancieraDTO.setDescripcion((vtClasificacionFinanciera.getDescripcion() != null)
                ? vtClasificacionFinanciera.getDescripcion() : null);
            vtClasificacionFinancieraDTO.setFechaCreacion(vtClasificacionFinanciera.getFechaCreacion());
            vtClasificacionFinancieraDTO.setFechaModificacion(vtClasificacionFinanciera.getFechaModificacion());
            vtClasificacionFinancieraDTO.setUsuaCreador((vtClasificacionFinanciera.getUsuaCreador() != null)
                ? vtClasificacionFinanciera.getUsuaCreador() : null);
            vtClasificacionFinancieraDTO.setUsuaModificador((vtClasificacionFinanciera.getUsuaModificador() != null)
                ? vtClasificacionFinanciera.getUsuaModificador() : null);

            return vtClasificacionFinancieraDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtClasificacionFinanciera vtClasificacionFinancieraDTOToVtClasificacionFinanciera(
        VtClasificacionFinancieraDTO vtClasificacionFinancieraDTO)
        throws Exception {
        try {
            VtClasificacionFinanciera vtClasificacionFinanciera = new VtClasificacionFinanciera();

            vtClasificacionFinanciera.setClfiId(vtClasificacionFinancieraDTO.getClfiId());
            vtClasificacionFinanciera.setActivo((vtClasificacionFinancieraDTO.getActivo() != null)
                ? vtClasificacionFinancieraDTO.getActivo() : null);
            vtClasificacionFinanciera.setDescripcion((vtClasificacionFinancieraDTO.getDescripcion() != null)
                ? vtClasificacionFinancieraDTO.getDescripcion() : null);
            vtClasificacionFinanciera.setFechaCreacion(vtClasificacionFinancieraDTO.getFechaCreacion());
            vtClasificacionFinanciera.setFechaModificacion(vtClasificacionFinancieraDTO.getFechaModificacion());
            vtClasificacionFinanciera.setUsuaCreador((vtClasificacionFinancieraDTO.getUsuaCreador() != null)
                ? vtClasificacionFinancieraDTO.getUsuaCreador() : null);
            vtClasificacionFinanciera.setUsuaModificador((vtClasificacionFinancieraDTO.getUsuaModificador() != null)
                ? vtClasificacionFinancieraDTO.getUsuaModificador() : null);

            return vtClasificacionFinanciera;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtClasificacionFinancieraDTO> listVtClasificacionFinancieraToListVtClasificacionFinancieraDTO(
        List<VtClasificacionFinanciera> listVtClasificacionFinanciera)
        throws Exception {
        try {
            List<VtClasificacionFinancieraDTO> vtClasificacionFinancieraDTOs = new ArrayList<VtClasificacionFinancieraDTO>();

            for (VtClasificacionFinanciera vtClasificacionFinanciera : listVtClasificacionFinanciera) {
                VtClasificacionFinancieraDTO vtClasificacionFinancieraDTO = vtClasificacionFinancieraToVtClasificacionFinancieraDTO(vtClasificacionFinanciera);

                vtClasificacionFinancieraDTOs.add(vtClasificacionFinancieraDTO);
            }

            return vtClasificacionFinancieraDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtClasificacionFinanciera> listVtClasificacionFinancieraDTOToListVtClasificacionFinanciera(
        List<VtClasificacionFinancieraDTO> listVtClasificacionFinancieraDTO)
        throws Exception {
        try {
            List<VtClasificacionFinanciera> listVtClasificacionFinanciera = new ArrayList<VtClasificacionFinanciera>();

            for (VtClasificacionFinancieraDTO vtClasificacionFinancieraDTO : listVtClasificacionFinancieraDTO) {
                VtClasificacionFinanciera vtClasificacionFinanciera = vtClasificacionFinancieraDTOToVtClasificacionFinanciera(vtClasificacionFinancieraDTO);

                listVtClasificacionFinanciera.add(vtClasificacionFinanciera);
            }

            return listVtClasificacionFinanciera;
        } catch (Exception e) {
            throw e;
        }
    }
}
