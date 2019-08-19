package com.vortexbird.vorteam.service;

import com.vortexbird.vorteam.domain.VtCliente;
import com.vortexbird.vorteam.dto.VtClienteDTO;

import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface VtClienteService {
    public List<VtCliente> getVtCliente() throws Exception;

    /**
         * Save an new VtCliente entity
         */
    public void saveVtCliente(VtCliente entity) throws Exception;

    /**
         * Delete an existing VtCliente entity
         *
         */
    public void deleteVtCliente(VtCliente entity) throws Exception;

    /**
        * Update an existing VtCliente entity
        *
        */
    public void updateVtCliente(VtCliente entity) throws Exception;

    /**
         * Load an existing VtCliente entity
         *
         */
    public VtCliente getVtCliente(Long clieId) throws Exception;

    public List<VtCliente> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtCliente> findPageVtCliente(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtCliente() throws Exception;

    public List<VtClienteDTO> getDataVtCliente() throws Exception;

    public void validateVtCliente(VtCliente vtCliente)
        throws Exception;
    
    public List<VtClienteDTO> clientesConProyectos() throws Exception;
}
