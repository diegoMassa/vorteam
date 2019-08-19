package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.dataaccess.dao.*;
import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegParametroDTO;
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
@Service("SegParametroLogic")
public class SegParametroLogic implements ISegParametroLogic {
    private static final Logger log = LoggerFactory.getLogger(SegParametroLogic.class);

    /**
     * DAO injected by Spring that manages SegParametro entities
     *
     */
    @Autowired
    private ISegParametroDAO segParametroDAO;

    /**
    * Logic injected by Spring that manages SegUsuario entities
    *
    */
    @Autowired
    ISegUsuarioLogic logicSegUsuario1;

    @Transactional(readOnly = true)
    public List<SegParametro> getSegParametro() throws Exception {
        log.debug("finding all SegParametro instances");

        List<SegParametro> list = new ArrayList<SegParametro>();

        try {
            list = segParametroDAO.findAll();
        } catch (Exception e) {
            log.error("finding all SegParametro failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SegParametro");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSegParametro(SegParametro entity) throws Exception {
        log.debug("saving SegParametro instance");

        try {
            if (entity.getSegUsuario() == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            if (entity.getParCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("parCodigo");
            }

            if (entity.getParEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "parEstadoRegistro");
            }

            if ((entity.getParEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getParEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parEstadoRegistro");
            }

            if (entity.getParNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("parNombre");
            }

            if ((entity.getParNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getParNombre(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parNombre");
            }

            if ((entity.getParValorAlfanumerico() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getParValorAlfanumerico(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parValorAlfanumerico");
            }

            if ((entity.getParValorNumerico() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getParValorNumerico(), 6, 4) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parValorNumerico");
            }

            if (entity.getSegUsuario().getUsuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            if (getSegParametro(entity.getParCodigo()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            segParametroDAO.save(entity);

            log.debug("save SegParametro successful");
        } catch (Exception e) {
            log.error("save SegParametro failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSegParametro(SegParametro entity)
        throws Exception {
        log.debug("deleting SegParametro instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("SegParametro");
        }

        if (entity.getParCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("parCodigo");
        }

        try {
            segParametroDAO.delete(entity);

            log.debug("delete SegParametro successful");
        } catch (Exception e) {
            log.error("delete SegParametro failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSegParametro(SegParametro entity)
        throws Exception {
        log.debug("updating SegParametro instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("SegParametro");
            }

            if (entity.getSegUsuario() == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            if (entity.getParCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("parCodigo");
            }

            if (entity.getParEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "parEstadoRegistro");
            }

            if ((entity.getParEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getParEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parEstadoRegistro");
            }

            if (entity.getParNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("parNombre");
            }

            if ((entity.getParNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getParNombre(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parNombre");
            }

            if ((entity.getParValorAlfanumerico() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getParValorAlfanumerico(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parValorAlfanumerico");
            }

            if ((entity.getParValorNumerico() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getParValorNumerico(), 6, 4) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "parValorNumerico");
            }

            if (entity.getSegUsuario().getUsuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            segParametroDAO.update(entity);

            log.debug("update SegParametro successful");
        } catch (Exception e) {
            log.error("update SegParametro failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<SegParametroDTO> getDataSegParametro()
        throws Exception {
        try {
            List<SegParametro> segParametro = segParametroDAO.findAll();

            List<SegParametroDTO> segParametroDTO = new ArrayList<SegParametroDTO>();

            for (SegParametro segParametroTmp : segParametro) {
                SegParametroDTO segParametroDTO2 = new SegParametroDTO();

                segParametroDTO2.setParCodigo(segParametroTmp.getParCodigo());
                segParametroDTO2.setParEstadoRegistro((segParametroTmp.getParEstadoRegistro() != null)
                    ? segParametroTmp.getParEstadoRegistro() : null);
                segParametroDTO2.setParNombre((segParametroTmp.getParNombre() != null)
                    ? segParametroTmp.getParNombre() : null);
                segParametroDTO2.setParValorAlfanumerico((segParametroTmp.getParValorAlfanumerico() != null)
                    ? segParametroTmp.getParValorAlfanumerico() : null);
                segParametroDTO2.setParValorFecha(segParametroTmp.getParValorFecha());
                segParametroDTO2.setParValorNumerico((segParametroTmp.getParValorNumerico() != null)
                    ? segParametroTmp.getParValorNumerico() : null);
                segParametroDTO2.setUsuCodigo_SegUsuario((segParametroTmp.getSegUsuario()
                                                                         .getUsuCodigo() != null)
                    ? segParametroTmp.getSegUsuario().getUsuCodigo() : null);
                segParametroDTO.add(segParametroDTO2);
            }

            return segParametroDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public SegParametro getSegParametro(Long parCodigo)
        throws Exception {
        log.debug("getting SegParametro instance");

        SegParametro entity = null;

        try {
            entity = segParametroDAO.findById(parCodigo);
        } catch (Exception e) {
            log.error("get SegParametro failed", e);
            throw new ZMessManager().new FindingException("SegParametro");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<SegParametro> findPageSegParametro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SegParametro> entity = null;

        try {
            entity = segParametroDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegParametro Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberSegParametro() throws Exception {
        Long entity = null;

        try {
            entity = segParametroDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegParametro Count");
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
    public List<SegParametro> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SegParametro> list = new ArrayList<SegParametro>();
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
            list = segParametroDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
