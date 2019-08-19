package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.VtAsignacion;
import com.vortexbird.vorteam.dto.VtAsignacionDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtAsignacionMapper {
    public VtAsignacionDTO vtAsignacionToVtAsignacionDTO(
        VtAsignacion vtAsignacion) throws Exception;

    public VtAsignacion vtAsignacionDTOToVtAsignacion(
        VtAsignacionDTO vtAsignacionDTO) throws Exception;

    public List<VtAsignacionDTO> listVtAsignacionToListVtAsignacionDTO(
        List<VtAsignacion> vtAsignacions) throws Exception;

    public List<VtAsignacion> listVtAsignacionDTOToListVtAsignacion(
        List<VtAsignacionDTO> vtAsignacionDTOs) throws Exception;
}
