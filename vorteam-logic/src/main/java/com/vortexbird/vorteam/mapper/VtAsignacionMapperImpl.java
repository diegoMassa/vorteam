package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.domain.VtAsignacion;
import com.vortexbird.vorteam.dto.VtAsignacionDTO;
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
public class VtAsignacionMapperImpl implements VtAsignacionMapper {
    private static final Logger log = LoggerFactory.getLogger(VtAsignacionMapperImpl.class);

    /**
    * Service injected by Spring that manages VtActividad entities
    *
    */
    @Autowired
    VtActividadService serviceVtActividad1;

    /**
    * Service injected by Spring that manages VtPersonas entities
    *
    */
    @Autowired
    VtPersonasService serviceVtPersonas2;

    @Transactional(readOnly = true)
    public VtAsignacionDTO vtAsignacionToVtAsignacionDTO(
        VtAsignacion vtAsignacion) throws Exception {
        try {
            VtAsignacionDTO vtAsignacionDTO = new VtAsignacionDTO();

            vtAsignacionDTO.setAsigId(vtAsignacion.getAsigId());
            vtAsignacionDTO.setActivo((vtAsignacion.getActivo() != null)
                ? vtAsignacion.getActivo() : null);
            vtAsignacionDTO.setFechaCreacion(vtAsignacion.getFechaCreacion());
            vtAsignacionDTO.setFechaModificacion(vtAsignacion.getFechaModificacion());
            vtAsignacionDTO.setUsuaCreador((vtAsignacion.getUsuaCreador() != null)
                ? vtAsignacion.getUsuaCreador() : null);
            vtAsignacionDTO.setUsuaModificador((vtAsignacion.getUsuaModificador() != null)
                ? vtAsignacion.getUsuaModificador() : null);
            vtAsignacionDTO.setActiId_VtActividad((vtAsignacion.getVtActividad()
                                                               .getActiId() != null)
                ? vtAsignacion.getVtActividad().getActiId() : null);
            vtAsignacionDTO.setPersId_VtPersonas((vtAsignacion.getVtPersonas()
                                                              .getPersId() != null)
                ? vtAsignacion.getVtPersonas().getPersId() : null);

            return vtAsignacionDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtAsignacion vtAsignacionDTOToVtAsignacion(
        VtAsignacionDTO vtAsignacionDTO) throws Exception {
        try {
            VtAsignacion vtAsignacion = new VtAsignacion();

            vtAsignacion.setAsigId(vtAsignacionDTO.getAsigId());
            vtAsignacion.setActivo((vtAsignacionDTO.getActivo() != null)
                ? vtAsignacionDTO.getActivo() : null);
            vtAsignacion.setFechaCreacion(vtAsignacionDTO.getFechaCreacion());
            vtAsignacion.setFechaModificacion(vtAsignacionDTO.getFechaModificacion());
            vtAsignacion.setUsuaCreador((vtAsignacionDTO.getUsuaCreador() != null)
                ? vtAsignacionDTO.getUsuaCreador() : null);
            vtAsignacion.setUsuaModificador((vtAsignacionDTO.getUsuaModificador() != null)
                ? vtAsignacionDTO.getUsuaModificador() : null);

            VtActividad vtActividad = new VtActividad();

            if (vtAsignacionDTO.getActiId_VtActividad() != null) {
                vtActividad = serviceVtActividad1.getVtActividad(vtAsignacionDTO.getActiId_VtActividad());
            }

            if (vtActividad != null) {
                vtAsignacion.setVtActividad(vtActividad);
            }

            VtPersonas vtPersonas = new VtPersonas();

            if (vtAsignacionDTO.getPersId_VtPersonas() != null) {
                vtPersonas = serviceVtPersonas2.getVtPersonas(vtAsignacionDTO.getPersId_VtPersonas());
            }

            if (vtPersonas != null) {
                vtAsignacion.setVtPersonas(vtPersonas);
            }

            return vtAsignacion;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtAsignacionDTO> listVtAsignacionToListVtAsignacionDTO(
        List<VtAsignacion> listVtAsignacion) throws Exception {
        try {
            List<VtAsignacionDTO> vtAsignacionDTOs = new ArrayList<VtAsignacionDTO>();

            for (VtAsignacion vtAsignacion : listVtAsignacion) {
                VtAsignacionDTO vtAsignacionDTO = vtAsignacionToVtAsignacionDTO(vtAsignacion);

                vtAsignacionDTOs.add(vtAsignacionDTO);
            }

            return vtAsignacionDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtAsignacion> listVtAsignacionDTOToListVtAsignacion(
        List<VtAsignacionDTO> listVtAsignacionDTO) throws Exception {
        try {
            List<VtAsignacion> listVtAsignacion = new ArrayList<VtAsignacion>();

            for (VtAsignacionDTO vtAsignacionDTO : listVtAsignacionDTO) {
                VtAsignacion vtAsignacion = vtAsignacionDTOToVtAsignacion(vtAsignacionDTO);

                listVtAsignacion.add(vtAsignacion);
            }

            return listVtAsignacion;
        } catch (Exception e) {
            throw e;
        }
    }
}
