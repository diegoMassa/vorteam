package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.modelo.SegRolUsuario;
import com.vortexbird.sentinel.modelo.dto.SegRolUsuarioDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface ISegRolUsuarioLogic {
    public List<SegRolUsuario> getSegRolUsuario() throws Exception;

    /**
         * Save an new SegRolUsuario entity
         */
    public void saveSegRolUsuario(SegRolUsuario entity)
        throws Exception;

    /**
         * Delete an existing SegRolUsuario entity
         *
         */
    public void deleteSegRolUsuario(SegRolUsuario entity)
        throws Exception;

    /**
        * Update an existing SegRolUsuario entity
        *
        */
    public void updateSegRolUsuario(SegRolUsuario entity)
        throws Exception;

    /**
         * Load an existing SegRolUsuario entity
         *
         */
    public SegRolUsuario getSegRolUsuario(Long rluCodigo)
        throws Exception;

    public List<SegRolUsuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SegRolUsuario> findPageSegRolUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSegRolUsuario() throws Exception;

    public List<SegRolUsuarioDTO> getDataSegRolUsuario()
        throws Exception;
}
