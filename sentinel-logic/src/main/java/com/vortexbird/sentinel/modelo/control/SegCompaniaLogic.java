package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.dataaccess.dao.*;
import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegCompaniaDTO;
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
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("SegCompaniaLogic")
public class SegCompaniaLogic implements ISegCompaniaLogic {
    private static final Logger log = LoggerFactory.getLogger(SegCompaniaLogic.class);

    /**
     * DAO injected by Spring that manages SegCompania entities
     *
     */
    @Autowired
    private ISegCompaniaDAO segCompaniaDAO;

    /**
    * DAO injected by Spring that manages SegSistemaCia entities
    *
    */
    @Autowired
    private ISegSistemaCiaDAO segSistemaCiaDAO;

    /**
    * DAO injected by Spring that manages SegSucursal entities
    *
    */
    @Autowired
    private ISegSucursalDAO segSucursalDAO;

    /**
    * Logic injected by Spring that manages SegUsuario entities
    *
    */
    @Autowired
    ISegUsuarioLogic logicSegUsuario1;
    
    @Autowired
    ISegRolLogic segRolLogic;

    @Transactional(readOnly = true)
    public List<SegCompania> getSegCompania() throws Exception {
        log.debug("finding all SegCompania instances");

        List<SegCompania> list = new ArrayList<SegCompania>();

        try {
            list = segCompaniaDAO.findAll();
        } catch (Exception e) {
            log.error("finding all SegCompania failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SegCompania");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSegCompania(SegCompania entity) throws Exception {
        log.debug("saving SegCompania instance");

        try {
            if (entity.getSegUsuario() == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            if (entity.getCiaCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("ciaCodigo");
            }

            if (entity.getCiaCodigoInterno() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "ciaCodigoInterno");
            }

            if ((entity.getCiaCodigoInterno() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCiaCodigoInterno(), 15) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "ciaCodigoInterno");
            }

            if (entity.getCiaEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "ciaEstadoRegistro");
            }

            if ((entity.getCiaEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCiaEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "ciaEstadoRegistro");
            }

            if (entity.getCiaNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("ciaNombre");
            }

            if ((entity.getCiaNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCiaNombre(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "ciaNombre");
            }

            if (entity.getSegUsuario().getUsuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            if (getSegCompania(entity.getCiaCodigo()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            segCompaniaDAO.save(entity);

            log.debug("save SegCompania successful");
        } catch (Exception e) {
            log.error("save SegCompania failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSegCompania(SegCompania entity) throws Exception {
        log.debug("deleting SegCompania instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("SegCompania");
        }

        if (entity.getCiaCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("ciaCodigo");
        }

        List<SegSistemaCia> segSistemaCias = null;
        List<SegSucursal> segSucursals = null;

        try {
            segSistemaCias = segSistemaCiaDAO.findByProperty("segCompania.ciaCodigo",
                    entity.getCiaCodigo());

            if (Utilities.validationsList(segSistemaCias) == true) {
                throw new ZMessManager().new DeletingException("segSistemaCias");
            }

            segSucursals = segSucursalDAO.findByProperty("segCompania.ciaCodigo",
                    entity.getCiaCodigo());

            if (Utilities.validationsList(segSucursals) == true) {
                throw new ZMessManager().new DeletingException("segSucursals");
            }

            segCompaniaDAO.delete(entity);

            log.debug("delete SegCompania successful");
        } catch (Exception e) {
            log.error("delete SegCompania failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSegCompania(SegCompania entity) throws Exception {
        log.debug("updating SegCompania instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("SegCompania");
            }

            if (entity.getSegUsuario() == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            if (entity.getCiaCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("ciaCodigo");
            }

            if (entity.getCiaCodigoInterno() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "ciaCodigoInterno");
            }

            if ((entity.getCiaCodigoInterno() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCiaCodigoInterno(), 15) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "ciaCodigoInterno");
            }

            if (entity.getCiaEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "ciaEstadoRegistro");
            }

            if ((entity.getCiaEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCiaEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "ciaEstadoRegistro");
            }

            if (entity.getCiaNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("ciaNombre");
            }

            if ((entity.getCiaNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCiaNombre(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "ciaNombre");
            }

            if (entity.getSegUsuario().getUsuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            segCompaniaDAO.update(entity);

            log.debug("update SegCompania successful");
        } catch (Exception e) {
            log.error("update SegCompania failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<SegCompaniaDTO> getDataSegCompania() throws Exception {
        try {
            List<SegCompania> segCompania = segCompaniaDAO.findAll();

            List<SegCompaniaDTO> segCompaniaDTO = new ArrayList<SegCompaniaDTO>();

            for (SegCompania segCompaniaTmp : segCompania) {
                SegCompaniaDTO segCompaniaDTO2 = new SegCompaniaDTO();

                segCompaniaDTO2.setCiaCodigo(segCompaniaTmp.getCiaCodigo());
                segCompaniaDTO2.setCiaCodigoInterno((segCompaniaTmp.getCiaCodigoInterno() != null)
                    ? segCompaniaTmp.getCiaCodigoInterno() : null);
                segCompaniaDTO2.setCiaEstadoRegistro((segCompaniaTmp.getCiaEstadoRegistro() != null)
                    ? segCompaniaTmp.getCiaEstadoRegistro() : null);
                segCompaniaDTO2.setCiaNombre((segCompaniaTmp.getCiaNombre() != null)
                    ? segCompaniaTmp.getCiaNombre() : null);
                segCompaniaDTO2.setUsuCodigo_SegUsuario((segCompaniaTmp.getSegUsuario()
                                                                       .getUsuCodigo() != null)
                    ? segCompaniaTmp.getSegUsuario().getUsuCodigo() : null);
                segCompaniaDTO.add(segCompaniaDTO2);
            }

            return segCompaniaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public SegCompania getSegCompania(Long ciaCodigo) throws Exception {
        log.debug("getting SegCompania instance");

        SegCompania entity = null;

        try {
            entity = segCompaniaDAO.findById(ciaCodigo);
        } catch (Exception e) {
            log.error("get SegCompania failed", e);
            throw new ZMessManager().new FindingException("SegCompania");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<SegCompania> findPageSegCompania(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SegCompania> entity = null;

        try {
            entity = segCompaniaDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegCompania Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberSegCompania() throws Exception {
        Long entity = null;

        try {
            entity = segCompaniaDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegCompania Count");
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
    public List<SegCompania> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SegCompania> list = new ArrayList<SegCompania>();
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
            list = segCompaniaDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    
    
    @Override
    @Transactional(readOnly = true)
	public List<SegCompania> consultarCompaniasDeDeusuarioAdministrador(Long ucuCodigo, long sisCodigo) throws Exception{
    	try {
			return segCompaniaDAO.consultarCompaniasDeDeusuarioAdministrador(ucuCodigo, sisCodigo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
    }
    
    @Override
    @Transactional(readOnly = true)
	public List<SegCompania> consultarCompaniasPorRol(Long rolCodigo) throws Exception{
    	try {
			
    		List<SegCompania> companias = new ArrayList<SegCompania>();
    		
    		SegRol rol = segRolLogic.getSegRol(rolCodigo);
    		
    		if (rol == null || !rol.getRolEstadoRegistro().equals("A")){
    			throw new Exception("No existe el rol " + rolCodigo);
    		}
    		
    		//Se consulta el sistema del rol
    		Set<SegSistemaCia> sistemasCias = rol.getSegSistema().getSegSistemaCias();
			
    		if (sistemasCias != null){
    			Iterator<SegSistemaCia> iteraror = sistemasCias.iterator();
    			while(iteraror.hasNext()){
    				SegSistemaCia segSistemaCia = iteraror.next();
    				
    				if(segSistemaCia.getSicEstadoRegistro().equals("A")){
    					companias.add(segSistemaCia.getSegCompania());
    				}
    				
    			}
    		}
    		
    		return companias;
    		
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
    }
}
