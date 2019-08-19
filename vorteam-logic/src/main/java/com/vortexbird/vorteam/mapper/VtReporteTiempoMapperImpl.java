package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.domain.VtReporteTiempo;
import com.vortexbird.vorteam.dto.VtReporteTiempoDTO;
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
public class VtReporteTiempoMapperImpl implements VtReporteTiempoMapper {
    private static final Logger log = LoggerFactory.getLogger(VtReporteTiempoMapperImpl.class);

    /**
    * Service injected by Spring that manages VtAsignacion entities
    *
    */
    @Autowired
    VtAsignacionService serviceVtAsignacion1;

    /**
    * Service injected by Spring that manages VtEstado entities
    *
    */
    @Autowired
    VtEstadoService serviceVtEstado2;

    @Transactional(readOnly = true)
    public VtReporteTiempoDTO vtReporteTiempoToVtReporteTiempoDTO(
        VtReporteTiempo vtReporteTiempo) throws Exception {
        try {
            VtReporteTiempoDTO vtReporteTiempoDTO = new VtReporteTiempoDTO();

            vtReporteTiempoDTO.setRetiId(vtReporteTiempo.getRetiId());
            vtReporteTiempoDTO.setActivo((vtReporteTiempo.getActivo() != null)
                ? vtReporteTiempo.getActivo() : null);
            vtReporteTiempoDTO.setFechaCreacion(vtReporteTiempo.getFechaCreacion());
            vtReporteTiempoDTO.setFecha(vtReporteTiempo.getFecha());
            vtReporteTiempoDTO.setFechaModificacion(vtReporteTiempo.getFechaModificacion());
            vtReporteTiempoDTO.setHorasEjecutadas((vtReporteTiempo.getHorasEjecutadas() != null)
                ? vtReporteTiempo.getHorasEjecutadas() : null);
            vtReporteTiempoDTO.setObservacion((vtReporteTiempo.getObservacion() != null)
                ? vtReporteTiempo.getObservacion() : null);
            vtReporteTiempoDTO.setUsuaCreador((vtReporteTiempo.getUsuaCreador() != null)
                ? vtReporteTiempo.getUsuaCreador() : null);
            vtReporteTiempoDTO.setUsuaModificador((vtReporteTiempo.getUsuaModificador() != null)
                ? vtReporteTiempo.getUsuaModificador() : null);
            vtReporteTiempoDTO.setAsigId_VtAsignacion((vtReporteTiempo.getVtAsignacion()
                                                                      .getAsigId() != null)
                ? vtReporteTiempo.getVtAsignacion().getAsigId() : null);
            vtReporteTiempoDTO.setEstaId_VtEstado((vtReporteTiempo.getVtEstado()
                                                                  .getEstaId() != null)
                ? vtReporteTiempo.getVtEstado().getEstaId() : null);

            return vtReporteTiempoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtReporteTiempo vtReporteTiempoDTOToVtReporteTiempo(
        VtReporteTiempoDTO vtReporteTiempoDTO) throws Exception {
        try {
            VtReporteTiempo vtReporteTiempo = new VtReporteTiempo();

            vtReporteTiempo.setRetiId(vtReporteTiempoDTO.getRetiId());
            vtReporteTiempo.setActivo((vtReporteTiempoDTO.getActivo() != null)
                ? vtReporteTiempoDTO.getActivo() : null);
            vtReporteTiempo.setFechaCreacion(vtReporteTiempoDTO.getFechaCreacion());
            vtReporteTiempo.setFecha(vtReporteTiempoDTO.getFecha());
            vtReporteTiempo.setFechaModificacion(vtReporteTiempoDTO.getFechaModificacion());
            vtReporteTiempo.setHorasEjecutadas((vtReporteTiempoDTO.getHorasEjecutadas() != null)
                ? vtReporteTiempoDTO.getHorasEjecutadas() : null);
            vtReporteTiempo.setObservacion((vtReporteTiempoDTO.getObservacion() != null)
                ? vtReporteTiempoDTO.getObservacion() : null);
            vtReporteTiempo.setUsuaCreador((vtReporteTiempoDTO.getUsuaCreador() != null)
                ? vtReporteTiempoDTO.getUsuaCreador() : null);
            vtReporteTiempo.setUsuaModificador((vtReporteTiempoDTO.getUsuaModificador() != null)
                ? vtReporteTiempoDTO.getUsuaModificador() : null);

            VtAsignacion vtAsignacion = new VtAsignacion();

            if (vtReporteTiempoDTO.getAsigId_VtAsignacion() != null) {
                vtAsignacion = serviceVtAsignacion1.getVtAsignacion(vtReporteTiempoDTO.getAsigId_VtAsignacion());
            }

            if (vtAsignacion != null) {
                vtReporteTiempo.setVtAsignacion(vtAsignacion);
            }

            VtEstado vtEstado = new VtEstado();

            if (vtReporteTiempoDTO.getEstaId_VtEstado() != null) {
                vtEstado = serviceVtEstado2.getVtEstado(vtReporteTiempoDTO.getEstaId_VtEstado());
            }

            if (vtEstado != null) {
                vtReporteTiempo.setVtEstado(vtEstado);
            }

            return vtReporteTiempo;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtReporteTiempoDTO> listVtReporteTiempoToListVtReporteTiempoDTO(
        List<VtReporteTiempo> listVtReporteTiempo) throws Exception {
        try {
            List<VtReporteTiempoDTO> vtReporteTiempoDTOs = new ArrayList<VtReporteTiempoDTO>();

            for (VtReporteTiempo vtReporteTiempo : listVtReporteTiempo) {
                VtReporteTiempoDTO vtReporteTiempoDTO = vtReporteTiempoToVtReporteTiempoDTO(vtReporteTiempo);

                vtReporteTiempoDTOs.add(vtReporteTiempoDTO);
            }

            return vtReporteTiempoDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtReporteTiempo> listVtReporteTiempoDTOToListVtReporteTiempo(
        List<VtReporteTiempoDTO> listVtReporteTiempoDTO)
        throws Exception {
        try {
            List<VtReporteTiempo> listVtReporteTiempo = new ArrayList<VtReporteTiempo>();

            for (VtReporteTiempoDTO vtReporteTiempoDTO : listVtReporteTiempoDTO) {
                VtReporteTiempo vtReporteTiempo = vtReporteTiempoDTOToVtReporteTiempo(vtReporteTiempoDTO);

                listVtReporteTiempo.add(vtReporteTiempo);
            }

            return listVtReporteTiempo;
        } catch (Exception e) {
            throw e;
        }
    }
}
