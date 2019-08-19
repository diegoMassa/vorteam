package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.VtLineaNegocio;
import com.vortexbird.vorteam.dto.VtLineaNegocioDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtLineaNegocioMapper {
    public VtLineaNegocioDTO vtLineaNegocioToVtLineaNegocioDTO(
        VtLineaNegocio vtLineaNegocio) throws Exception;

    public VtLineaNegocio vtLineaNegocioDTOToVtLineaNegocio(
        VtLineaNegocioDTO vtLineaNegocioDTO) throws Exception;

    public List<VtLineaNegocioDTO> listVtLineaNegocioToListVtLineaNegocioDTO(
        List<VtLineaNegocio> vtLineaNegocios) throws Exception;

    public List<VtLineaNegocio> listVtLineaNegocioDTOToListVtLineaNegocio(
        List<VtLineaNegocioDTO> vtLineaNegocioDTOs) throws Exception;
}
