package com.vortexbird.vorteam.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.vorteam.domain.VtParametro;
import com.vortexbird.vorteam.repository.VtParametroRepository;
import com.vortexbird.vorteam.utility.Constantes;
import com.vortexbird.vorteam.utility.Utilities;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service
public class VtParametroServiceImpl implements VtParametroService {
    private static final Logger log = LoggerFactory.getLogger(VtParametroServiceImpl.class);

    @Autowired
    private VtParametroRepository vtParametroRepository;

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 14, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtParametroService#findByCriteria(java.lang.Object[], java.lang.Object[], java.lang.Object[])
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtParametro> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
        List<VtParametro> list = new ArrayList<VtParametro>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between " + value +
                        " and " + value2 + ")")
                        : (tempWhere + " AND (model." + variable + " between " +
                        value + " and " + value2 + ")");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = vtParametroRepository.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtParametroService#obtenerParametroPorCodigo(java.lang.String, java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public VtParametro obtenerParametroPorCodigo(String codigo, String activo) throws Exception {
		VtParametro parametro = null;
		try {
			if(codigo == null || codigo.trim().equals("")) {
				throw new Exception("No ha llegado el código del parámetro a buscar");
			}
			if(activo == null || activo.trim().equals("")) {
				activo = Constantes.ESTADO_ACTIVO;
			}
			
			Object[] variables = {
					"nombreCorto",true,codigo.trim().toUpperCase(),"=",
					 "activo",true,Constantes.ESTADO_ACTIVO,"="
			};
			List<VtParametro> parametros = findByCriteria(variables, null, null);
			parametro = parametros.isEmpty()?null:parametros.get(0);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return parametro;
	}
    
	
}
