package com.vortexbird.vorteam.service;

import com.vortexbird.vorteam.domain.VtReporteTiempo;
import com.vortexbird.vorteam.dto.VtReporteTiempoDTO;

import java.io.File;
import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtReporteTiempoService {
    public List<VtReporteTiempo> getVtReporteTiempo() throws Exception;

    /**
         * Save an new VtReporteTiempo entity
         */
    public void saveVtReporteTiempo(VtReporteTiempo entity)
        throws Exception;

    /**
         * Delete an existing VtReporteTiempo entity
         *
         */
    public void deleteVtReporteTiempo(VtReporteTiempo entity)
        throws Exception;

    /**
        * Update an existing VtReporteTiempo entity
        *
        */
    public void updateVtReporteTiempo(VtReporteTiempo entity)
        throws Exception;

    /**
         * Load an existing VtReporteTiempo entity
         *
         */
    public VtReporteTiempo getVtReporteTiempo(Long retiId)
        throws Exception;

    public List<VtReporteTiempo> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtReporteTiempo> findPageVtReporteTiempo(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberVtReporteTiempo() throws Exception;

    public List<VtReporteTiempoDTO> getDataVtReporteTiempo()
        throws Exception;

    public void validateVtReporteTiempo(VtReporteTiempo vtReporteTiempo)
        throws Exception;
    
    public List<VtReporteTiempoDTO> reporteEntreFechas(Date fechaInicial, Date fechaFinal) throws Exception;
    
	public BigDecimal horasReportadasHoy(Date fecha, String email) throws Exception;
	
	public void enviarCorreoNotificacionNoHaReportadoHoy(String destinatario, String mensaje) throws Exception;
	
	public void enviarCorreoNotificacionOcupacionPlantaDiaAnterior(String[] destinatarios, String mensaje, File adjunto) throws Exception;
}
