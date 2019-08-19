package com.vortexbird.vorteam.repository;

import java.util.Date;
import java.util.List;

import com.vortexbird.vorteam.dto.VtFestivosDTO;

/**
* Interface for   VtReporteTiempoRepository.
*
*/
public interface IVtFestivosRepository {
	public List<VtFestivosDTO> buscarEnFestivosPorFecha(Date fecha) throws Exception;
}
