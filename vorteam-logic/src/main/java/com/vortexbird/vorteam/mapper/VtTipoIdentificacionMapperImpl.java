package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.domain.VtTipoIdentificacion;
import com.vortexbird.vorteam.dto.VtTipoIdentificacionDTO;
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
public class VtTipoIdentificacionMapperImpl
    implements VtTipoIdentificacionMapper {
    private static final Logger log = LoggerFactory.getLogger(VtTipoIdentificacionMapperImpl.class);

    @Transactional(readOnly = true)
    public VtTipoIdentificacionDTO vtTipoIdentificacionToVtTipoIdentificacionDTO(
        VtTipoIdentificacion vtTipoIdentificacion) throws Exception {
        try {
            VtTipoIdentificacionDTO vtTipoIdentificacionDTO = new VtTipoIdentificacionDTO();

            vtTipoIdentificacionDTO.setTiidId(vtTipoIdentificacion.getTiidId());
            vtTipoIdentificacionDTO.setActivo((vtTipoIdentificacion.getActivo() != null)
                ? vtTipoIdentificacion.getActivo() : null);
            vtTipoIdentificacionDTO.setDescripcion((vtTipoIdentificacion.getDescripcion() != null)
                ? vtTipoIdentificacion.getDescripcion() : null);
            vtTipoIdentificacionDTO.setFechaCreacion(vtTipoIdentificacion.getFechaCreacion());
            vtTipoIdentificacionDTO.setFechaModificacion(vtTipoIdentificacion.getFechaModificacion());
            vtTipoIdentificacionDTO.setUsuaCreador((vtTipoIdentificacion.getUsuaCreador() != null)
                ? vtTipoIdentificacion.getUsuaCreador() : null);
            vtTipoIdentificacionDTO.setUsuaModificador((vtTipoIdentificacion.getUsuaModificador() != null)
                ? vtTipoIdentificacion.getUsuaModificador() : null);

            return vtTipoIdentificacionDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtTipoIdentificacion vtTipoIdentificacionDTOToVtTipoIdentificacion(
        VtTipoIdentificacionDTO vtTipoIdentificacionDTO)
        throws Exception {
        try {
            VtTipoIdentificacion vtTipoIdentificacion = new VtTipoIdentificacion();

            vtTipoIdentificacion.setTiidId(vtTipoIdentificacionDTO.getTiidId());
            vtTipoIdentificacion.setActivo((vtTipoIdentificacionDTO.getActivo() != null)
                ? vtTipoIdentificacionDTO.getActivo() : null);
            vtTipoIdentificacion.setDescripcion((vtTipoIdentificacionDTO.getDescripcion() != null)
                ? vtTipoIdentificacionDTO.getDescripcion() : null);
            vtTipoIdentificacion.setFechaCreacion(vtTipoIdentificacionDTO.getFechaCreacion());
            vtTipoIdentificacion.setFechaModificacion(vtTipoIdentificacionDTO.getFechaModificacion());
            vtTipoIdentificacion.setUsuaCreador((vtTipoIdentificacionDTO.getUsuaCreador() != null)
                ? vtTipoIdentificacionDTO.getUsuaCreador() : null);
            vtTipoIdentificacion.setUsuaModificador((vtTipoIdentificacionDTO.getUsuaModificador() != null)
                ? vtTipoIdentificacionDTO.getUsuaModificador() : null);

            return vtTipoIdentificacion;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtTipoIdentificacionDTO> listVtTipoIdentificacionToListVtTipoIdentificacionDTO(
        List<VtTipoIdentificacion> listVtTipoIdentificacion)
        throws Exception {
        try {
            List<VtTipoIdentificacionDTO> vtTipoIdentificacionDTOs = new ArrayList<VtTipoIdentificacionDTO>();

            for (VtTipoIdentificacion vtTipoIdentificacion : listVtTipoIdentificacion) {
                VtTipoIdentificacionDTO vtTipoIdentificacionDTO = vtTipoIdentificacionToVtTipoIdentificacionDTO(vtTipoIdentificacion);

                vtTipoIdentificacionDTOs.add(vtTipoIdentificacionDTO);
            }

            return vtTipoIdentificacionDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtTipoIdentificacion> listVtTipoIdentificacionDTOToListVtTipoIdentificacion(
        List<VtTipoIdentificacionDTO> listVtTipoIdentificacionDTO)
        throws Exception {
        try {
            List<VtTipoIdentificacion> listVtTipoIdentificacion = new ArrayList<VtTipoIdentificacion>();

            for (VtTipoIdentificacionDTO vtTipoIdentificacionDTO : listVtTipoIdentificacionDTO) {
                VtTipoIdentificacion vtTipoIdentificacion = vtTipoIdentificacionDTOToVtTipoIdentificacion(vtTipoIdentificacionDTO);

                listVtTipoIdentificacion.add(vtTipoIdentificacion);
            }

            return listVtTipoIdentificacion;
        } catch (Exception e) {
            throw e;
        }
    }
}
