package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.VtPersonas;
import com.vortexbird.vorteam.dto.VtPersonasDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtPersonasMapper {
    public VtPersonasDTO vtPersonasToVtPersonasDTO(VtPersonas vtPersonas)
        throws Exception;

    public VtPersonas vtPersonasDTOToVtPersonas(VtPersonasDTO vtPersonasDTO)
        throws Exception;

    public List<VtPersonasDTO> listVtPersonasToListVtPersonasDTO(
        List<VtPersonas> vtPersonass) throws Exception;

    public List<VtPersonas> listVtPersonasDTOToListVtPersonas(
        List<VtPersonasDTO> vtPersonasDTOs) throws Exception;
}
