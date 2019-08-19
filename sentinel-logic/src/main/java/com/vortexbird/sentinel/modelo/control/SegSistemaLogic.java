package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.dataaccess.dao.*;
import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegSistemaDTO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("SegSistemaLogic")
public class SegSistemaLogic implements ISegSistemaLogic {
    private static final Logger log = LoggerFactory.getLogger(SegSistemaLogic.class);

    /**
     * DAO injected by Spring that manages SegSistema entities
     *
     */
    @Autowired
    private ISegSistemaDAO segSistemaDAO;

    /**
    * DAO injected by Spring that manages SegGrupoOpcion entities
    *
    */
    @Autowired
    private ISegGrupoOpcionDAO segGrupoOpcionDAO;

    /**
    * DAO injected by Spring that manages SegRol entities
    *
    */
    @Autowired
    private ISegRolDAO segRolDAO;

    /**
    * DAO injected by Spring that manages SegSistemaCia entities
    *
    */
    @Autowired
    private ISegSistemaCiaDAO segSistemaCiaDAO;

    /**
    * Logic injected by Spring that manages SegUsuario entities
    *
    */
    @Autowired
    ISegUsuarioLogic logicSegUsuario1;
    
    @Autowired
    ISegRolLogic segRolLogic;
    
    @Autowired
    ISegUsuarioLogic segUsuarioLogic;
    
    @Autowired
    private ISegPermisoLogic segPermisoLogic;

    @Transactional(readOnly = true)
    public List<SegSistema> getSegSistema() throws Exception {
        log.debug("finding all SegSistema instances");

        List<SegSistema> list = new ArrayList<SegSistema>();

        try {
            list = segSistemaDAO.findAll();
        } catch (Exception e) {
            log.error("finding all SegSistema failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SegSistema");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSegSistema(SegSistema entity) throws Exception {
        log.debug("saving SegSistema instance");

        try {
            if (entity.getSegUsuario() == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            if (entity.getSisCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("sisCodigo");
            }

            if ((entity.getSisDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSisDescripcion(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "sisDescripcion");
            }

            if (entity.getSisEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "sisEstadoRegistro");
            }

            if ((entity.getSisEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSisEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "sisEstadoRegistro");
            }

            if (entity.getSisNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("sisNombre");
            }

            if ((entity.getSisNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSisNombre(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "sisNombre");
            }

            if (entity.getSegUsuario().getUsuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            if (getSegSistema(entity.getSisCodigo()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            segSistemaDAO.save(entity);

            log.debug("save SegSistema successful");
        } catch (Exception e) {
            log.error("save SegSistema failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSegSistema(SegSistema entity) throws Exception {
        log.debug("deleting SegSistema instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("SegSistema");
        }

        if (entity.getSisCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("sisCodigo");
        }

        List<SegGrupoOpcion> segGrupoOpcions = null;
        List<SegRol> segRols = null;
        List<SegSistemaCia> segSistemaCias = null;

        try {
            segGrupoOpcions = segGrupoOpcionDAO.findByProperty("segSistema.sisCodigo",
                    entity.getSisCodigo());

            if (Utilities.validationsList(segGrupoOpcions) == true) {
                throw new ZMessManager().new DeletingException(
                    "segGrupoOpcions");
            }

            segRols = segRolDAO.findByProperty("segSistema.sisCodigo",
                    entity.getSisCodigo());

            if (Utilities.validationsList(segRols) == true) {
                throw new ZMessManager().new DeletingException("segRols");
            }

            segSistemaCias = segSistemaCiaDAO.findByProperty("segSistema.sisCodigo",
                    entity.getSisCodigo());

            if (Utilities.validationsList(segSistemaCias) == true) {
                throw new ZMessManager().new DeletingException("segSistemaCias");
            }

            segSistemaDAO.delete(entity);

            log.debug("delete SegSistema successful");
        } catch (Exception e) {
            log.error("delete SegSistema failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSegSistema(SegSistema entity) throws Exception {
        log.debug("updating SegSistema instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("SegSistema");
            }

            if (entity.getSegUsuario() == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            if (entity.getSisCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("sisCodigo");
            }

            if ((entity.getSisDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSisDescripcion(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "sisDescripcion");
            }

            if (entity.getSisEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "sisEstadoRegistro");
            }

            if ((entity.getSisEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSisEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "sisEstadoRegistro");
            }

            if (entity.getSisNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("sisNombre");
            }

            if ((entity.getSisNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSisNombre(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "sisNombre");
            }

            if (entity.getSegUsuario().getUsuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            segSistemaDAO.update(entity);

            log.debug("update SegSistema successful");
        } catch (Exception e) {
            log.error("update SegSistema failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<SegSistemaDTO> getDataSegSistema() throws Exception {
        try {
            List<SegSistema> segSistema = segSistemaDAO.findAll();

            List<SegSistemaDTO> segSistemaDTO = new ArrayList<SegSistemaDTO>();

            for (SegSistema segSistemaTmp : segSistema) {
                SegSistemaDTO segSistemaDTO2 = new SegSistemaDTO();

                segSistemaDTO2.setSisCodigo(segSistemaTmp.getSisCodigo());
                segSistemaDTO2.setSisDescripcion((segSistemaTmp.getSisDescripcion() != null)
                    ? segSistemaTmp.getSisDescripcion() : null);
                segSistemaDTO2.setSisEstadoRegistro((segSistemaTmp.getSisEstadoRegistro() != null)
                    ? segSistemaTmp.getSisEstadoRegistro() : null);
                segSistemaDTO2.setSisNombre((segSistemaTmp.getSisNombre() != null)
                    ? segSistemaTmp.getSisNombre() : null);
                segSistemaDTO2.setUsuCodigo_SegUsuario((segSistemaTmp.getSegUsuario()
                                                                     .getUsuCodigo() != null)
                    ? segSistemaTmp.getSegUsuario().getUsuCodigo() : null);
                segSistemaDTO.add(segSistemaDTO2);
            }

            return segSistemaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public SegSistema getSegSistema(Long sisCodigo) throws Exception {
        log.debug("getting SegSistema instance");

        SegSistema entity = null;

        try {
            entity = segSistemaDAO.findById(sisCodigo);
        } catch (Exception e) {
            log.error("get SegSistema failed", e);
            throw new ZMessManager().new FindingException("SegSistema");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<SegSistema> findPageSegSistema(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SegSistema> entity = null;

        try {
            entity = segSistemaDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegSistema Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberSegSistema() throws Exception {
        Long entity = null;

        try {
            entity = segSistemaDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegSistema Count");
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
    public List<SegSistema> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SegSistema> list = new ArrayList<SegSistema>();
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
            list = segSistemaDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SegSistema> consultarSistemasDeUsuarioAdministrador(Long usuCodigo) throws Exception{
    	try {
			
    		return segSistemaDAO.consultarSistemasDeUsuarioAdministrador(usuCodigo);
    		
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
    }
    
    @Override
    @Transactional(readOnly = true)
    public SegSistema consultarSistemDeRol(Long rolCodigo) throws Exception{
    	try {
			SegRol segRol = segRolLogic.getSegRol(rolCodigo);
			
			if (segRol == null){
				throw new Exception("no existe el rol " + rolCodigo);
			}
			
			return segRol.getSegSistema();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
    }

    @Override
    @Transactional(readOnly = true)
    public List<SegSistema> consultarSistemasALosQueTieneAccesoElUsuario(Long usuCodigo) throws Exception{
    	try {
			
    		Map<Long, SegSistema> sistemas = new HashMap<Long, SegSistema>();
    		
    		//Se consulta el usuario
    		SegUsuario segUsuario = segUsuarioLogic.getSegUsuario(usuCodigo);
    		
    		if (segUsuario == null || !segUsuario.getUsuEstadoRegistro().equals("A")){
    			throw new Exception("No exite el usuario " + usuCodigo);
    		}
    		
    		//Se consultan los permisos del usuario a nivel de sistema para cualquiera de las cias
    		Set<SegPermiso> permisos = segUsuario.getSegPermisosForUsuCodigo();
    		
    		if (permisos != null){
    			for (SegPermiso segPermiso : permisos) {
					
    				if (segPermiso.getSegSistemaCia() != null && segPermiso.getSegGrupoOpcion() == null &&
    						segPermiso.getSegOpcion() == null && segPermiso.getPerEstadoRegistro().equals("A")){
    					
    					sistemas.put(segPermiso.getSegSistemaCia().getSegSistema().getSisCodigo(), 
    							segPermiso.getSegSistemaCia().getSegSistema());
    					
    				}
    				
				}
    		}
    		
    		//Se consultan los sistemas a los que tienen permiso todos los usuarios
    		Object[] variablesConsulta = {"segRol", false, "", "is null",
    				"segOpcion", false, "", "is null",
    				"segUsuarioByUsuCodigo", false, "", "is null",
    				"segGrupoOpcion", false, "", "is null",
    				"perEstadoRegistro", true, "A", "="};
    		List<SegPermiso> permisosDeTotos = segPermisoLogic.findByCriteria(variablesConsulta, null, null);
    		if (permisosDeTotos != null){
    			for (SegPermiso segPermiso : permisosDeTotos) {
					
					sistemas.put(segPermiso.getSegSistemaCia().getSegSistema().getSisCodigo(), 
							segPermiso.getSegSistemaCia().getSegSistema());
    				
				}
    		}
    		
    		List<SegSistema> losPermisos = new ArrayList<SegSistema>(sistemas.values());
    		return losPermisos;
    		
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
    }
}
