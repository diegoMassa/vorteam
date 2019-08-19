package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.dataaccess.dao.*;
import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegSistemaCiaDTO;
import com.vortexbird.sentinel.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("SegSistemaCiaLogic")
public class SegSistemaCiaLogic implements ISegSistemaCiaLogic {
    private static final Logger log = LoggerFactory.getLogger(SegSistemaCiaLogic.class);

    /**
     * DAO injected by Spring that manages SegSistemaCia entities
     *
     */
    @Autowired
    private ISegSistemaCiaDAO segSistemaCiaDAO;

    /**
    * DAO injected by Spring that manages SegPermiso entities
    *
    */
    @Autowired
    private ISegPermisoDAO segPermisoDAO;

    /**
    * Logic injected by Spring that manages SegCompania entities
    *
    */
    @Autowired
    ISegCompaniaLogic logicSegCompania1;

    /**
    * Logic injected by Spring that manages SegSistema entities
    *
    */
    @Autowired
    ISegSistemaLogic logicSegSistema2;

    /**
    * Logic injected by Spring that manages SegUsuario entities
    *
    */
    @Autowired
    ISegUsuarioLogic logicSegUsuario3;

    @Transactional(readOnly = true)
    public List<SegSistemaCia> getSegSistemaCia() throws Exception {
        log.debug("finding all SegSistemaCia instances");

        List<SegSistemaCia> list = new ArrayList<SegSistemaCia>();

        try {
            list = segSistemaCiaDAO.findAll();
        } catch (Exception e) {
            log.error("finding all SegSistemaCia failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SegSistemaCia");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSegSistemaCia(SegSistemaCia entity)
        throws Exception {
        log.debug("saving SegSistemaCia instance");

        try {
            if (entity.getSegCompania() == null) {
                throw new ZMessManager().new ForeignException("segCompania");
            }

            if (entity.getSegSistema() == null) {
                throw new ZMessManager().new ForeignException("segSistema");
            }

            if (entity.getSegUsuario() == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            if (entity.getSicCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("sicCodigo");
            }

            if ((entity.getSicEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSicEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "sicEstadoRegistro");
            }

            if (entity.getSegCompania().getCiaCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "ciaCodigo_SegCompania");
            }

            if (entity.getSegSistema().getSisCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "sisCodigo_SegSistema");
            }

            if (entity.getSegUsuario().getUsuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            if (getSegSistemaCia(entity.getSicCodigo()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            segSistemaCiaDAO.save(entity);

            log.debug("save SegSistemaCia successful");
        } catch (Exception e) {
            log.error("save SegSistemaCia failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSegSistemaCia(SegSistemaCia entity)
        throws Exception {
        log.debug("deleting SegSistemaCia instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("SegSistemaCia");
        }

        if (entity.getSicCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("sicCodigo");
        }

        List<SegPermiso> segPermisos = null;

        try {
            segPermisos = segPermisoDAO.findByProperty("segSistemaCia.sicCodigo",
                    entity.getSicCodigo());

            if (Utilities.validationsList(segPermisos) == true) {
                throw new ZMessManager().new DeletingException("segPermisos");
            }

            segSistemaCiaDAO.delete(entity);

            log.debug("delete SegSistemaCia successful");
        } catch (Exception e) {
            log.error("delete SegSistemaCia failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSegSistemaCia(SegSistemaCia entity)
        throws Exception {
        log.debug("updating SegSistemaCia instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "SegSistemaCia");
            }

            if (entity.getSegCompania() == null) {
                throw new ZMessManager().new ForeignException("segCompania");
            }

            if (entity.getSegSistema() == null) {
                throw new ZMessManager().new ForeignException("segSistema");
            }

            if (entity.getSegUsuario() == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            if (entity.getSicCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("sicCodigo");
            }

            if ((entity.getSicEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSicEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "sicEstadoRegistro");
            }

            if (entity.getSegCompania().getCiaCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "ciaCodigo_SegCompania");
            }

            if (entity.getSegSistema().getSisCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "sisCodigo_SegSistema");
            }

            if (entity.getSegUsuario().getUsuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            segSistemaCiaDAO.update(entity);

            log.debug("update SegSistemaCia successful");
        } catch (Exception e) {
            log.error("update SegSistemaCia failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<SegSistemaCiaDTO> getDataSegSistemaCia()
        throws Exception {
        try {
            List<SegSistemaCia> segSistemaCia = segSistemaCiaDAO.findAll();

            List<SegSistemaCiaDTO> segSistemaCiaDTO = new ArrayList<SegSistemaCiaDTO>();

            for (SegSistemaCia segSistemaCiaTmp : segSistemaCia) {
                SegSistemaCiaDTO segSistemaCiaDTO2 = new SegSistemaCiaDTO();

                segSistemaCiaDTO2.setSicCodigo(segSistemaCiaTmp.getSicCodigo());
                segSistemaCiaDTO2.setSicEstadoRegistro((segSistemaCiaTmp.getSicEstadoRegistro() != null)
                    ? segSistemaCiaTmp.getSicEstadoRegistro() : null);
                segSistemaCiaDTO2.setCiaCodigo_SegCompania((segSistemaCiaTmp.getSegCompania()
                                                                            .getCiaCodigo() != null)
                    ? segSistemaCiaTmp.getSegCompania().getCiaCodigo() : null);
                segSistemaCiaDTO2.setSisCodigo_SegSistema((segSistemaCiaTmp.getSegSistema()
                                                                           .getSisCodigo() != null)
                    ? segSistemaCiaTmp.getSegSistema().getSisCodigo() : null);
                segSistemaCiaDTO2.setUsuCodigo_SegUsuario((segSistemaCiaTmp.getSegUsuario()
                                                                           .getUsuCodigo() != null)
                    ? segSistemaCiaTmp.getSegUsuario().getUsuCodigo() : null);
                segSistemaCiaDTO.add(segSistemaCiaDTO2);
            }

            return segSistemaCiaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public SegSistemaCia getSegSistemaCia(Long sicCodigo)
        throws Exception {
        log.debug("getting SegSistemaCia instance");

        SegSistemaCia entity = null;

        try {
            entity = segSistemaCiaDAO.findById(sicCodigo);
        } catch (Exception e) {
            log.error("get SegSistemaCia failed", e);
            throw new ZMessManager().new FindingException("SegSistemaCia");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<SegSistemaCia> findPageSegSistemaCia(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SegSistemaCia> entity = null;

        try {
            entity = segSistemaCiaDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegSistemaCia Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberSegSistemaCia() throws Exception {
        Long entity = null;

        try {
            entity = segSistemaCiaDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegSistemaCia Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<SegSistemaCia> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SegSistemaCia> list = new ArrayList<SegSistemaCia>();
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
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
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
            list = segSistemaCiaDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }

	/** 
	 * (non-Javadoc)
	 * @see com.vortexbird.sentinel.modelo.control.ISegSistemaCiaLogic#getSegSistemaCiaBySistemaYCompania(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public SegSistemaCia getSegSistemaCiaBySistemaYCompania(Long sisCodigo, Long ciaCodigo) {
		try {
			Object[] variables = {"segSistema.sisCodigo", false, sisCodigo, "=",
					"segCompania.ciaCodigo", false, ciaCodigo, "="};
			List<SegSistemaCia> sistemaCias = findByCriteria(variables, null, null);
			
			if(sistemaCias == null || sistemaCias.isEmpty()){
				return null;
			}
			
			return sistemaCias.get(0);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}
