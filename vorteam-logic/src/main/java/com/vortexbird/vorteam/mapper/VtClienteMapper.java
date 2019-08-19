package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.VtCliente;
import com.vortexbird.vorteam.dto.VtClienteDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtClienteMapper {
    public VtClienteDTO vtClienteToVtClienteDTO(VtCliente vtCliente)
        throws Exception;

    public VtCliente vtClienteDTOToVtCliente(VtClienteDTO vtClienteDTO)
        throws Exception;

    public List<VtClienteDTO> listVtClienteToListVtClienteDTO(
        List<VtCliente> vtClientes) throws Exception;

    public List<VtCliente> listVtClienteDTOToListVtCliente(
        List<VtClienteDTO> vtClienteDTOs) throws Exception;
}
