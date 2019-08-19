package com.vortexbird.vorteam.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.vortexbird.vorteam.dto.VtReporteTiempoDTO;

/**
 * 
 *
 * @author Daniel Pareja Londoño
 * @version ago. 02, 2018
 * @since 1.8
 *
 */
public interface IVtInformesRepository {
	public List<VtReporteTiempoDTO> reporteEntreFechas(Date fechaInicial, Date fechaFinal) throws Exception;
	public BigDecimal horasReportadasHoy(Date fecha, String email) throws Exception;
}
