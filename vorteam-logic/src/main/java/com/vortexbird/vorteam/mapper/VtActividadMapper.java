package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.VtActividad;
import com.vortexbird.vorteam.dto.VtActividadDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtActividadMapper {
    public VtActividadDTO vtActividadToVtActividadDTO(VtActividad vtActividad)
        throws Exception;

    public VtActividad vtActividadDTOToVtActividad(
        VtActividadDTO vtActividadDTO) throws Exception;

    public List<VtActividadDTO> listVtActividadToListVtActividadDTO(
        List<VtActividad> vtActividads) throws Exception;

    public List<VtActividad> listVtActividadDTOToListVtActividad(
        List<VtActividadDTO> vtActividadDTOs) throws Exception;
}
