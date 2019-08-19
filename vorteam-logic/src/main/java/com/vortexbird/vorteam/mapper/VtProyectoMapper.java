package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.VtProyecto;
import com.vortexbird.vorteam.dto.VtProyectoDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtProyectoMapper {
    public VtProyectoDTO vtProyectoToVtProyectoDTO(VtProyecto vtProyecto)
        throws Exception;

    public VtProyecto vtProyectoDTOToVtProyecto(VtProyectoDTO vtProyectoDTO)
        throws Exception;

    public List<VtProyectoDTO> listVtProyectoToListVtProyectoDTO(
        List<VtProyecto> vtProyectos) throws Exception;

    public List<VtProyecto> listVtProyectoDTOToListVtProyecto(
        List<VtProyectoDTO> vtProyectoDTOs) throws Exception;
}
