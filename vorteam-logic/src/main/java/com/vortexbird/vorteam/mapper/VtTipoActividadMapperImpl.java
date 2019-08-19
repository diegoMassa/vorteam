package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.domain.VtTipoActividad;
import com.vortexbird.vorteam.dto.VtTipoActividadDTO;
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
public class VtTipoActividadMapperImpl implements VtTipoActividadMapper {
    private static final Logger log = LoggerFactory.getLogger(VtTipoActividadMapperImpl.class);

    /**
    * Service injected by Spring that manages VtClasificacionFinanciera entities
    *
    */
    @Autowired
    VtClasificacionFinancieraService serviceVtClasificacionFinanciera1;

    @Transactional(readOnly = true)
    public VtTipoActividadDTO vtTipoActividadToVtTipoActividadDTO(
        VtTipoActividad vtTipoActividad) throws Exception {
        try {
            VtTipoActividadDTO vtTipoActividadDTO = new VtTipoActividadDTO();

            vtTipoActividadDTO.setTiacId(vtTipoActividad.getTiacId());
            vtTipoActividadDTO.setActivo((vtTipoActividad.getActivo() != null)
                ? vtTipoActividad.getActivo() : null);
            vtTipoActividadDTO.setDescripcion((vtTipoActividad.getDescripcion() != null)
                ? vtTipoActividad.getDescripcion() : null);
            vtTipoActividadDTO.setFechaCreacion(vtTipoActividad.getFechaCreacion());
            vtTipoActividadDTO.setFechaModificacion(vtTipoActividad.getFechaModificacion());
            vtTipoActividadDTO.setUsuaCreador((vtTipoActividad.getUsuaCreador() != null)
                ? vtTipoActividad.getUsuaCreador() : null);
            vtTipoActividadDTO.setUsuaModificador((vtTipoActividad.getUsuaModificador() != null)
                ? vtTipoActividad.getUsuaModificador() : null);
            vtTipoActividadDTO.setClfiId_VtClasificacionFinanciera((vtTipoActividad.getVtClasificacionFinanciera()
                                                                                   .getClfiId() != null)
                ? vtTipoActividad.getVtClasificacionFinanciera().getClfiId()
                : null);

            return vtTipoActividadDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtTipoActividad vtTipoActividadDTOToVtTipoActividad(
        VtTipoActividadDTO vtTipoActividadDTO) throws Exception {
        try {
            VtTipoActividad vtTipoActividad = new VtTipoActividad();

            vtTipoActividad.setTiacId(vtTipoActividadDTO.getTiacId());
            vtTipoActividad.setActivo((vtTipoActividadDTO.getActivo() != null)
                ? vtTipoActividadDTO.getActivo() : null);
            vtTipoActividad.setDescripcion((vtTipoActividadDTO.getDescripcion() != null)
                ? vtTipoActividadDTO.getDescripcion() : null);
            vtTipoActividad.setFechaCreacion(vtTipoActividadDTO.getFechaCreacion());
            vtTipoActividad.setFechaModificacion(vtTipoActividadDTO.getFechaModificacion());
            vtTipoActividad.setUsuaCreador((vtTipoActividadDTO.getUsuaCreador() != null)
                ? vtTipoActividadDTO.getUsuaCreador() : null);
            vtTipoActividad.setUsuaModificador((vtTipoActividadDTO.getUsuaModificador() != null)
                ? vtTipoActividadDTO.getUsuaModificador() : null);

            VtClasificacionFinanciera vtClasificacionFinanciera = new VtClasificacionFinanciera();

            if (vtTipoActividadDTO.getClfiId_VtClasificacionFinanciera() != null) {
                vtClasificacionFinanciera = serviceVtClasificacionFinanciera1.getVtClasificacionFinanciera(vtTipoActividadDTO.getClfiId_VtClasificacionFinanciera());
            }

            if (vtClasificacionFinanciera != null) {
                vtTipoActividad.setVtClasificacionFinanciera(vtClasificacionFinanciera);
            }

            return vtTipoActividad;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtTipoActividadDTO> listVtTipoActividadToListVtTipoActividadDTO(
        List<VtTipoActividad> listVtTipoActividad) throws Exception {
        try {
            List<VtTipoActividadDTO> vtTipoActividadDTOs = new ArrayList<VtTipoActividadDTO>();

            for (VtTipoActividad vtTipoActividad : listVtTipoActividad) {
                VtTipoActividadDTO vtTipoActividadDTO = vtTipoActividadToVtTipoActividadDTO(vtTipoActividad);

                vtTipoActividadDTOs.add(vtTipoActividadDTO);
            }

            return vtTipoActividadDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtTipoActividad> listVtTipoActividadDTOToListVtTipoActividad(
        List<VtTipoActividadDTO> listVtTipoActividadDTO)
        throws Exception {
        try {
            List<VtTipoActividad> listVtTipoActividad = new ArrayList<VtTipoActividad>();

            for (VtTipoActividadDTO vtTipoActividadDTO : listVtTipoActividadDTO) {
                VtTipoActividad vtTipoActividad = vtTipoActividadDTOToVtTipoActividad(vtTipoActividadDTO);

                listVtTipoActividad.add(vtTipoActividad);
            }

            return listVtTipoActividad;
        } catch (Exception e) {
            throw e;
        }
    }
}
