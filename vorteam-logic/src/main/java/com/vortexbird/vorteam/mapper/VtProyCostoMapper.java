package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.VtProyCosto;
import com.vortexbird.vorteam.dto.VtProyCostoDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtProyCostoMapper {
    public VtProyCostoDTO vtProyCostoToVtProyCostoDTO(VtProyCosto vtProyCosto)
        throws Exception;

    public VtProyCosto vtProyCostoDTOToVtProyCosto(
        VtProyCostoDTO vtProyCostoDTO) throws Exception;

    public List<VtProyCostoDTO> listVtProyCostoToListVtProyCostoDTO(
        List<VtProyCosto> vtProyCostos) throws Exception;

    public List<VtProyCosto> listVtProyCostoDTOToListVtProyCosto(
        List<VtProyCostoDTO> vtProyCostoDTOs) throws Exception;
}
