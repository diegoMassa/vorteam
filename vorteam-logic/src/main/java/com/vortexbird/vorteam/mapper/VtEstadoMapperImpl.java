package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.domain.VtEstado;
import com.vortexbird.vorteam.dto.VtEstadoDTO;
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
public class VtEstadoMapperImpl implements VtEstadoMapper {
    private static final Logger log = LoggerFactory.getLogger(VtEstadoMapperImpl.class);

    @Transactional(readOnly = true)
    public VtEstadoDTO vtEstadoToVtEstadoDTO(VtEstado vtEstado)
        throws Exception {
        try {
            VtEstadoDTO vtEstadoDTO = new VtEstadoDTO();

            vtEstadoDTO.setEstaId(vtEstado.getEstaId());
            vtEstadoDTO.setActivo((vtEstado.getActivo() != null)
                ? vtEstado.getActivo() : null);
            vtEstadoDTO.setDescripcion((vtEstado.getDescripcion() != null)
                ? vtEstado.getDescripcion() : null);
            vtEstadoDTO.setFechaCreacion(vtEstado.getFechaCreacion());
            vtEstadoDTO.setFechaModificacion(vtEstado.getFechaModificacion());
            vtEstadoDTO.setUsuaCreador((vtEstado.getUsuaCreador() != null)
                ? vtEstado.getUsuaCreador() : null);
            vtEstadoDTO.setUsuaModificador((vtEstado.getUsuaModificador() != null)
                ? vtEstado.getUsuaModificador() : null);

            return vtEstadoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtEstado vtEstadoDTOToVtEstado(VtEstadoDTO vtEstadoDTO)
        throws Exception {
        try {
            VtEstado vtEstado = new VtEstado();

            vtEstado.setEstaId(vtEstadoDTO.getEstaId());
            vtEstado.setActivo((vtEstadoDTO.getActivo() != null)
                ? vtEstadoDTO.getActivo() : null);
            vtEstado.setDescripcion((vtEstadoDTO.getDescripcion() != null)
                ? vtEstadoDTO.getDescripcion() : null);
            vtEstado.setFechaCreacion(vtEstadoDTO.getFechaCreacion());
            vtEstado.setFechaModificacion(vtEstadoDTO.getFechaModificacion());
            vtEstado.setUsuaCreador((vtEstadoDTO.getUsuaCreador() != null)
                ? vtEstadoDTO.getUsuaCreador() : null);
            vtEstado.setUsuaModificador((vtEstadoDTO.getUsuaModificador() != null)
                ? vtEstadoDTO.getUsuaModificador() : null);

            return vtEstado;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtEstadoDTO> listVtEstadoToListVtEstadoDTO(
        List<VtEstado> listVtEstado) throws Exception {
        try {
            List<VtEstadoDTO> vtEstadoDTOs = new ArrayList<VtEstadoDTO>();

            for (VtEstado vtEstado : listVtEstado) {
                VtEstadoDTO vtEstadoDTO = vtEstadoToVtEstadoDTO(vtEstado);

                vtEstadoDTOs.add(vtEstadoDTO);
            }

            return vtEstadoDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtEstado> listVtEstadoDTOToListVtEstado(
        List<VtEstadoDTO> listVtEstadoDTO) throws Exception {
        try {
            List<VtEstado> listVtEstado = new ArrayList<VtEstado>();

            for (VtEstadoDTO vtEstadoDTO : listVtEstadoDTO) {
                VtEstado vtEstado = vtEstadoDTOToVtEstado(vtEstadoDTO);

                listVtEstado.add(vtEstado);
            }

            return listVtEstado;
        } catch (Exception e) {
            throw e;
        }
    }
}
