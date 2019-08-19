package com.vortexbird.sentinel.modelo.control;


import java.math.BigDecimal;
import java.util.*;

import com.vortexbird.sentinel.modelo.VinCamposParametrizables;
import com.vortexbird.sentinel.modelo.dto.VinCamposParametrizablesDTO;



/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IVinCamposParametrizablesLogic {
    public List<VinCamposParametrizables> getVinCamposParametrizables()
        throws Exception;

    /**
         * Save an new VinCamposParametrizables entity
         */
    public void saveVinCamposParametrizables(VinCamposParametrizables entity)
        throws Exception;

    /**
         * Delete an existing VinCamposParametrizables entity
         *
         */
    public void deleteVinCamposParametrizables(VinCamposParametrizables entity)
        throws Exception;

    /**
        * Update an existing VinCamposParametrizables entity
        *
        */
    public void updateVinCamposParametrizables(VinCamposParametrizables entity)
        throws Exception;

    /**
         * Load an existing VinCamposParametrizables entity
         *
         */
    public VinCamposParametrizables getVinCamposParametrizables(String llave)
        throws Exception;

    public List<VinCamposParametrizables> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VinCamposParametrizables> findPageVinCamposParametrizables(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberVinCamposParametrizables()
        throws Exception;

    public List<VinCamposParametrizablesDTO> getDataVinCamposParametrizables()
        throws Exception;
}
