package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.modelo.SegSistema;
import com.vortexbird.sentinel.modelo.dto.SegSistemaDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface ISegSistemaLogic {
    public List<SegSistema> getSegSistema() throws Exception;

    /**
         * Save an new SegSistema entity
         */
    public void saveSegSistema(SegSistema entity) throws Exception;

    /**
         * Delete an existing SegSistema entity
         *
         */
    public void deleteSegSistema(SegSistema entity) throws Exception;

    /**
        * Update an existing SegSistema entity
        *
        */
    public void updateSegSistema(SegSistema entity) throws Exception;

    /**
         * Load an existing SegSistema entity
         *
         */
    public SegSistema getSegSistema(Long sisCodigo) throws Exception;

    public List<SegSistema> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegSistema> findPageSegSistema(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegSistema() throws Exception;

    public List<SegSistemaDTO> getDataSegSistema() throws Exception;

	public List<SegSistema> consultarSistemasDeUsuarioAdministrador(Long usuCodigo)
			throws Exception;

	public SegSistema consultarSistemDeRol(Long rolCodigo) throws Exception;

	public List<SegSistema> consultarSistemasALosQueTieneAccesoElUsuario(Long usuCodigo)
			throws Exception;
}
