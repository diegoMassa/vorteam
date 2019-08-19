package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.VtEstado;
import com.vortexbird.vorteam.dto.VtEstadoDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtEstadoMapper {
    public VtEstadoDTO vtEstadoToVtEstadoDTO(VtEstado vtEstado)
        throws Exception;

    public VtEstado vtEstadoDTOToVtEstado(VtEstadoDTO vtEstadoDTO)
        throws Exception;

    public List<VtEstadoDTO> listVtEstadoToListVtEstadoDTO(
        List<VtEstado> vtEstados) throws Exception;

    public List<VtEstado> listVtEstadoDTOToListVtEstado(
        List<VtEstadoDTO> vtEstadoDTOs) throws Exception;
}
