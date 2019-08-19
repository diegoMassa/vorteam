package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.VtClasificacionFinanciera;
import com.vortexbird.vorteam.dto.VtClasificacionFinancieraDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtClasificacionFinancieraMapper {
    public VtClasificacionFinancieraDTO vtClasificacionFinancieraToVtClasificacionFinancieraDTO(
        VtClasificacionFinanciera vtClasificacionFinanciera)
        throws Exception;

    public VtClasificacionFinanciera vtClasificacionFinancieraDTOToVtClasificacionFinanciera(
        VtClasificacionFinancieraDTO vtClasificacionFinancieraDTO)
        throws Exception;

    public List<VtClasificacionFinancieraDTO> listVtClasificacionFinancieraToListVtClasificacionFinancieraDTO(
        List<VtClasificacionFinanciera> vtClasificacionFinancieras)
        throws Exception;

    public List<VtClasificacionFinanciera> listVtClasificacionFinancieraDTOToListVtClasificacionFinanciera(
        List<VtClasificacionFinancieraDTO> vtClasificacionFinancieraDTOs)
        throws Exception;
}
