package com.vortexbird.vorteam.mapper;

import com.vortexbird.vorteam.domain.VtReporteTiempo;
import com.vortexbird.vorteam.dto.VtReporteTiempoDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtReporteTiempoMapper {
    public VtReporteTiempoDTO vtReporteTiempoToVtReporteTiempoDTO(
        VtReporteTiempo vtReporteTiempo) throws Exception;

    public VtReporteTiempo vtReporteTiempoDTOToVtReporteTiempo(
        VtReporteTiempoDTO vtReporteTiempoDTO) throws Exception;

    public List<VtReporteTiempoDTO> listVtReporteTiempoToListVtReporteTiempoDTO(
        List<VtReporteTiempo> vtReporteTiempos) throws Exception;

    public List<VtReporteTiempo> listVtReporteTiempoDTOToListVtReporteTiempo(
        List<VtReporteTiempoDTO> vtReporteTiempoDTOs) throws Exception;
}
