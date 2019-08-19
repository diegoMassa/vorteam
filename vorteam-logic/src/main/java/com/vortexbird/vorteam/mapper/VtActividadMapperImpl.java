package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.domain.VtActividad;
import com.vortexbird.vorteam.dto.VtActividadDTO;
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
public class VtActividadMapperImpl implements VtActividadMapper {
    private static final Logger log = LoggerFactory.getLogger(VtActividadMapperImpl.class);

    /**
    * Service injected by Spring that manages VtEstado entities
    *
    */
    @Autowired
    VtEstadoService serviceVtEstado1;

    /**
    * Service injected by Spring that manages VtProyecto entities
    *
    */
    @Autowired
    VtProyectoService serviceVtProyecto2;

    /**
    * Service injected by Spring that manages VtTipoActividad entities
    *
    */
    @Autowired
    VtTipoActividadService serviceVtTipoActividad3;

    @Transactional(readOnly = true)
    public VtActividadDTO vtActividadToVtActividadDTO(VtActividad vtActividad)
        throws Exception {
        try {
            VtActividadDTO vtActividadDTO = new VtActividadDTO();

            vtActividadDTO.setActiId(vtActividad.getActiId());
            vtActividadDTO.setActivo((vtActividad.getActivo() != null)
                ? vtActividad.getActivo() : null);
            vtActividadDTO.setCasoSoporte((vtActividad.getCasoSoporte() != null)
                ? vtActividad.getCasoSoporte() : null);
            vtActividadDTO.setDescripcion((vtActividad.getDescripcion() != null)
                ? vtActividad.getDescripcion() : null);
            vtActividadDTO.setFacturable((vtActividad.getFacturable() != null)
                ? vtActividad.getFacturable() : null);
            vtActividadDTO.setFechaCreacion(vtActividad.getFechaCreacion());
            vtActividadDTO.setFechaLimite(vtActividad.getFechaLimite());
            vtActividadDTO.setFechaModificacion(vtActividad.getFechaModificacion());
            vtActividadDTO.setHorasPresupuestadas((vtActividad.getHorasPresupuestadas() != null)
                ? vtActividad.getHorasPresupuestadas() : null);
            vtActividadDTO.setNombre((vtActividad.getNombre() != null)
                ? vtActividad.getNombre() : null);
            vtActividadDTO.setSprint((vtActividad.getSprint() != null)
                ? vtActividad.getSprint() : null);
            vtActividadDTO.setUsuaCreador((vtActividad.getUsuaCreador() != null)
                ? vtActividad.getUsuaCreador() : null);
            vtActividadDTO.setUsuaModificador((vtActividad.getUsuaModificador() != null)
                ? vtActividad.getUsuaModificador() : null);
            vtActividadDTO.setValorFacturable((vtActividad.getValorFacturable() != null)
                ? vtActividad.getValorFacturable() : null);
            vtActividadDTO.setEstaId_VtEstado((vtActividad.getVtEstado()
                                                          .getEstaId() != null)
                ? vtActividad.getVtEstado().getEstaId() : null);
            vtActividadDTO.setProyId_VtProyecto((vtActividad.getVtProyecto()
                                                            .getProyId() != null)
                ? vtActividad.getVtProyecto().getProyId() : null);
            vtActividadDTO.setTiacId_VtTipoActividad((vtActividad.getVtTipoActividad()
                                                                 .getTiacId() != null)
                ? vtActividad.getVtTipoActividad().getTiacId() : null);
            vtActividadDTO.setControlCambios((vtActividad.getControlCambios() != null)
            		? vtActividad.getControlCambios() : null);
            
            
            return vtActividadDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtActividad vtActividadDTOToVtActividad(
        VtActividadDTO vtActividadDTO) throws Exception {
        try {
            VtActividad vtActividad = new VtActividad();

            vtActividad.setActiId(vtActividadDTO.getActiId());
            vtActividad.setActivo((vtActividadDTO.getActivo() != null)
                ? vtActividadDTO.getActivo() : null);
            vtActividad.setCasoSoporte((vtActividadDTO.getCasoSoporte() != null)
                ? vtActividadDTO.getCasoSoporte() : null);
            vtActividad.setDescripcion((vtActividadDTO.getDescripcion() != null)
                ? vtActividadDTO.getDescripcion() : null);
            vtActividad.setFacturable((vtActividadDTO.getFacturable() != null)
                ? vtActividadDTO.getFacturable() : null);
            vtActividad.setFechaCreacion(vtActividadDTO.getFechaCreacion());
            vtActividad.setFechaLimite(vtActividadDTO.getFechaLimite());
            vtActividad.setFechaModificacion(vtActividadDTO.getFechaModificacion());
            vtActividad.setHorasPresupuestadas((vtActividadDTO.getHorasPresupuestadas() != null)
                ? vtActividadDTO.getHorasPresupuestadas() : null);
            vtActividad.setNombre((vtActividadDTO.getNombre() != null)
                ? vtActividadDTO.getNombre() : null);
            vtActividad.setSprint((vtActividadDTO.getSprint() != null)
                ? vtActividadDTO.getSprint() : null);
            vtActividad.setUsuaCreador((vtActividadDTO.getUsuaCreador() != null)
                ? vtActividadDTO.getUsuaCreador() : null);
            vtActividad.setUsuaModificador((vtActividadDTO.getUsuaModificador() != null)
                ? vtActividadDTO.getUsuaModificador() : null);
            vtActividad.setValorFacturable((vtActividadDTO.getValorFacturable() != null)
                ? vtActividadDTO.getValorFacturable() : null);
            vtActividad.setControlCambios((vtActividadDTO.getControlCambios() != null)
            		? vtActividadDTO.getControlCambios() : null);

            VtEstado vtEstado = new VtEstado();

            if (vtActividadDTO.getEstaId_VtEstado() != null) {
                vtEstado = serviceVtEstado1.getVtEstado(vtActividadDTO.getEstaId_VtEstado());
            }

            if (vtEstado != null) {
                vtActividad.setVtEstado(vtEstado);
            }

            VtProyecto vtProyecto = new VtProyecto();

            if (vtActividadDTO.getProyId_VtProyecto() != null) {
                vtProyecto = serviceVtProyecto2.getVtProyecto(vtActividadDTO.getProyId_VtProyecto());
            }

            if (vtProyecto != null) {
                vtActividad.setVtProyecto(vtProyecto);
            }

            VtTipoActividad vtTipoActividad = new VtTipoActividad();

            if (vtActividadDTO.getTiacId_VtTipoActividad() != null) {
                vtTipoActividad = serviceVtTipoActividad3.getVtTipoActividad(vtActividadDTO.getTiacId_VtTipoActividad());
            }

            if (vtTipoActividad != null) {
                vtActividad.setVtTipoActividad(vtTipoActividad);
            }

            return vtActividad;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtActividadDTO> listVtActividadToListVtActividadDTO(
        List<VtActividad> listVtActividad) throws Exception {
        try {
            List<VtActividadDTO> vtActividadDTOs = new ArrayList<VtActividadDTO>();

            for (VtActividad vtActividad : listVtActividad) {
                VtActividadDTO vtActividadDTO = vtActividadToVtActividadDTO(vtActividad);

                vtActividadDTOs.add(vtActividadDTO);
            }

            return vtActividadDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtActividad> listVtActividadDTOToListVtActividad(
        List<VtActividadDTO> listVtActividadDTO) throws Exception {
        try {
            List<VtActividad> listVtActividad = new ArrayList<VtActividad>();

            for (VtActividadDTO vtActividadDTO : listVtActividadDTO) {
                VtActividad vtActividad = vtActividadDTOToVtActividad(vtActividadDTO);

                listVtActividad.add(vtActividad);
            }

            return listVtActividad;
        } catch (Exception e) {
            throw e;
        }
    }
}
