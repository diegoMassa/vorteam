package com.vortexbird.vorteam.service;

import java.io.ByteArrayInputStream;
import java.util.Date;

import com.vortexbird.vorteam.utility.Constantes.REPORT_OUTPUT_TYPE;

/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtReportesService {
	public ByteArrayInputStream generarReporteTiemposEntreFechas(Date fechaInicial, Date fechaFinal, REPORT_OUTPUT_TYPE reportOutputTye)
			throws Exception;
	public ByteArrayInputStream generarReporteCostosPorProyectos(Date fechaInicial, Date fechaFinal, Long persId, Long proyId, Long tiacId,REPORT_OUTPUT_TYPE reportOutputTye)
			throws Exception;
	public ByteArrayInputStream generarReporteOcupacionPlanta(Date fechaInicial, Date fechaFinal, Long persId, Long proyId, Long tiacId,REPORT_OUTPUT_TYPE reportOutputTye)
			throws Exception;
	public ByteArrayInputStream generarReporteCostosPorPersona(Date fechaInicial, Date fechaFinal, Long persId, Long proyId, Long tiacId,REPORT_OUTPUT_TYPE reportOutputTye)
			throws Exception;
	public ByteArrayInputStream generarReporteCostoAcumuladoProyecto(Long proyId, REPORT_OUTPUT_TYPE reportOutputTye)
			throws Exception;

}
