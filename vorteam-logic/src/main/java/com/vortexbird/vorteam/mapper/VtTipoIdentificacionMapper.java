package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.VtTipoIdentificacion;
import com.vortexbird.vorteam.dto.VtTipoIdentificacionDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtTipoIdentificacionMapper {
    public VtTipoIdentificacionDTO vtTipoIdentificacionToVtTipoIdentificacionDTO(
        VtTipoIdentificacion vtTipoIdentificacion) throws Exception;

    public VtTipoIdentificacion vtTipoIdentificacionDTOToVtTipoIdentificacion(
        VtTipoIdentificacionDTO vtTipoIdentificacionDTO)
        throws Exception;

    public List<VtTipoIdentificacionDTO> listVtTipoIdentificacionToListVtTipoIdentificacionDTO(
        List<VtTipoIdentificacion> vtTipoIdentificacions)
        throws Exception;

    public List<VtTipoIdentificacion> listVtTipoIdentificacionDTOToListVtTipoIdentificacion(
        List<VtTipoIdentificacionDTO> vtTipoIdentificacionDTOs)
        throws Exception;
}
