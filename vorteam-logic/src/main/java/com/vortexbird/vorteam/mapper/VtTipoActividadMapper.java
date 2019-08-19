package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.VtTipoActividad;
import com.vortexbird.vorteam.dto.VtTipoActividadDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtTipoActividadMapper {
    public VtTipoActividadDTO vtTipoActividadToVtTipoActividadDTO(
        VtTipoActividad vtTipoActividad) throws Exception;

    public VtTipoActividad vtTipoActividadDTOToVtTipoActividad(
        VtTipoActividadDTO vtTipoActividadDTO) throws Exception;

    public List<VtTipoActividadDTO> listVtTipoActividadToListVtTipoActividadDTO(
        List<VtTipoActividad> vtTipoActividads) throws Exception;

    public List<VtTipoActividad> listVtTipoActividadDTOToListVtTipoActividad(
        List<VtTipoActividadDTO> vtTipoActividadDTOs) throws Exception;
}
