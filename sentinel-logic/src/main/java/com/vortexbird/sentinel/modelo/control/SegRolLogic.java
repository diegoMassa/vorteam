package com.vortexbird.sentinel.modelo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.sentinel.dataaccess.dao.ISegPermisoDAO;
import com.vortexbird.sentinel.dataaccess.dao.ISegRolDAO;
import com.vortexbird.sentinel.dataaccess.dao.ISegRolUsuarioDAO;
import com.vortexbird.sentinel.exceptions.ZMessManager;
import com.vortexbird.sentinel.modelo.SegCompania;
import com.vortexbird.sentinel.modelo.SegOpcion;
import com.vortexbird.sentinel.modelo.SegPermiso;
import com.vortexbird.sentinel.modelo.SegRol;
import com.vortexbird.sentinel.modelo.SegRolUsuario;
import com.vortexbird.sentinel.modelo.SegSistema;
import com.vortexbird.sentinel.modelo.SegSistemaCia;
import com.vortexbird.sentinel.modelo.SegUsuario;
import com.vortexbird.sentinel.modelo.dto.SegRolDTO;
import com.vortexbird.sentinel.utilities.Constantes;
import com.vortexbird.sentinel.utilities.Utilities;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("SegRolLogic")
public class SegRolLogic implements ISegRolLogic {
    private static final Logger log = LoggerFactory.getLogger(SegRolLogic.class);

    /**
     * DAO injected by Spring that manages SegRol entities
     *
     */
    @Autowired
    private ISegRolDAO segRolDAO;

    /**
    * DAO injected by Spring that manages SegPermiso entities
    *
    */
    @Autowired
    private ISegPermisoDAO segPermisoDAO;

    /**
    * DAO injected by Spring that manages SegRolUsuario entities
    *
    */
    @Autowired
    private ISegRolUsuarioDAO segRolUsuarioDAO;

    /**
    * Logic injected by Spring that manages SegSistema entities
    *
    */
    @Autowired
    private ISegSistemaLogic logicSegSistema1;
    
    @Autowired
    private ISegRolUsuarioLogic segRolUsuarioLogic;

    /**
    * Logic injected by Spring that manages SegUsuario entities
    *
    */
    @Autowired
    private ISegUsuarioLogic logicSegUsuario2;
    
    @Autowired
    private ISegUsuarioLogic segUsuarioLogic;
    
    @Autowired
    private ISegOpcionLogic segOpcionLogic;
    
    @Autowired
    private ISegCompaniaLogic segCompaniaLogic;
    
    @Autowired
    private ISegSistemaCiaLogic segSistemaCiaLogic;
    
    @Autowired
    private ISegPermisoLogic segPermisoLogic;

    @Transactional(readOnly = true)
    public List<SegRol> getSegRol() throws Exception {
        log.debug("finding all SegRol instances");

        List<SegRol> list = new ArrayList<SegRol>();

        try {
            list = segRolDAO.findAll();
        } catch (Exception e) {
            log.error("finding all SegRol failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SegRol");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSegRol(SegRol entity) throws Exception {
        log.debug("saving SegRol instance");

        try {
            if (entity.getSegSistema() == null) {
                throw new ZMessManager().new ForeignException("segSistema");
            }

            if (entity.getSegUsuario() == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            if (entity.getRolCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("rolCodigo");
            }

            if ((entity.getRolDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getRolDescripcion(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "rolDescripcion");
            }

            if ((entity.getRolDiasCaducidadPwd() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getRolDiasCaducidadPwd(), 3, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "rolDiasCaducidadPwd");
            }

            if (entity.getRolEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "rolEstadoRegistro");
            }

            if ((entity.getRolEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getRolEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "rolEstadoRegistro");
            }

            if (entity.getRolNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("rolNombre");
            }

            if ((entity.getRolNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getRolNombre(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "rolNombre");
            }

            if (entity.getSegSistema().getSisCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "sisCodigo_SegSistema");
            }

            if (entity.getSegUsuario().getUsuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            if (getSegRol(entity.getRolCodigo()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            segRolDAO.save(entity);

            log.debug("save SegRol successful");
        } catch (Exception e) {
            log.error("save SegRol failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSegRol(SegRol entity) throws Exception {
        log.debug("deleting SegRol instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("SegRol");
        }

        if (entity.getRolCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("rolCodigo");
        }

        List<SegPermiso> segPermisos = null;
        List<SegRolUsuario> segRolUsuarios = null;

        try {
            segPermisos = segPermisoDAO.findByProperty("segRol.rolCodigo",
                    entity.getRolCodigo());

            if (Utilities.validationsList(segPermisos) == true) {
                throw new ZMessManager().new DeletingException("segPermisos");
            }

            segRolUsuarios = segRolUsuarioDAO.findByProperty("segRol.rolCodigo",
                    entity.getRolCodigo());

            if (Utilities.validationsList(segRolUsuarios) == true) {
                throw new ZMessManager().new DeletingException("segRolUsuarios");
            }

            segRolDAO.delete(entity);

            log.debug("delete SegRol successful");
        } catch (Exception e) {
            log.error("delete SegRol failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSegRol(SegRol entity) throws Exception {
        log.debug("updating SegRol instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("SegRol");
            }

            if (entity.getSegSistema() == null) {
                throw new ZMessManager().new ForeignException("segSistema");
            }

            if (entity.getSegUsuario() == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            if (entity.getRolCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("rolCodigo");
            }

            if ((entity.getRolDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getRolDescripcion(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "rolDescripcion");
            }

            if ((entity.getRolDiasCaducidadPwd() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getRolDiasCaducidadPwd(), 3, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "rolDiasCaducidadPwd");
            }

            if (entity.getRolEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "rolEstadoRegistro");
            }

            if ((entity.getRolEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getRolEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "rolEstadoRegistro");
            }

            if (entity.getRolNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("rolNombre");
            }

            if ((entity.getRolNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getRolNombre(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "rolNombre");
            }

            if (entity.getSegSistema().getSisCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "sisCodigo_SegSistema");
            }

            if (entity.getSegUsuario().getUsuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            segRolDAO.update(entity);

            log.debug("update SegRol successful");
        } catch (Exception e) {
            log.error("update SegRol failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<SegRolDTO> getDataSegRol() throws Exception {
        try {
            List<SegRol> segRol = segRolDAO.findAll();

            List<SegRolDTO> segRolDTO = new ArrayList<SegRolDTO>();

            for (SegRol segRolTmp : segRol) {
                SegRolDTO segRolDTO2 = new SegRolDTO();

                segRolDTO2.setRolCodigo(segRolTmp.getRolCodigo());
                segRolDTO2.setRolDescripcion((segRolTmp.getRolDescripcion() != null)
                    ? segRolTmp.getRolDescripcion() : null);
                segRolDTO2.setRolDiasCaducidadPwd((segRolTmp.getRolDiasCaducidadPwd() != null)
                    ? segRolTmp.getRolDiasCaducidadPwd() : null);
                segRolDTO2.setRolEstadoRegistro((segRolTmp.getRolEstadoRegistro() != null)
                    ? segRolTmp.getRolEstadoRegistro() : null);
                segRolDTO2.setRolNombre((segRolTmp.getRolNombre() != null)
                    ? segRolTmp.getRolNombre() : null);
                segRolDTO2.setSisCodigo_SegSistema((segRolTmp.getSegSistema()
                                                              != null)
                    ? segRolTmp.getSegSistema().getSisNombre().toString() : null);
                segRolDTO2.setUsuCodigo_SegUsuario((segRolTmp.getSegUsuario()
                                                              != null)
                    ? segRolTmp.getSegUsuario().getUsuCodigo() : null);
                
                segRolDTO2.setEsAdmonDeSistema(segRolTmp.getEsAdmonDeAplicacion()!=null && segRolTmp.getEsAdmonDeAplicacion().equals("S") ? "Si" : "No");
                
                segRolDTO.add(segRolDTO2);
            }

            return segRolDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public SegRol getSegRol(Long rolCodigo) throws Exception {
        log.debug("getting SegRol instance");

        SegRol entity = null;

        try {
            entity = segRolDAO.findById(rolCodigo);
        } catch (Exception e) {
            log.error("get SegRol failed", e);
            throw new ZMessManager().new FindingException("SegRol");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<SegRol> findPageSegRol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SegRol> entity = null;

        try {
            entity = segRolDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegRol Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberSegRol() throws Exception {
        Long entity = null;

        try {
            entity = segRolDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegRol Count");
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
    public List<SegRol> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SegRol> list = new ArrayList<SegRol>();
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
            list = segRolDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void guardarRol(String rolDescripcion,
			Long rolDiasCaducidadPwd, String rolEstadoRegistro, String rolNombre,
			Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario, String esAdmonDeSistema)
					throws Exception {
		SegRol entity = null;

		try {

			if ((rolDescripcion != null) &&
					(Utilities.checkWordAndCheckWithlength(rolDescripcion, 200) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolDescripcion");
			}

//			if ((rolDiasCaducidadPwd != null) &&
//					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
//							rolDiasCaducidadPwd, 3, 0) == false)) {
//				throw new ZMessManager().new NotValidFormatException(
//						"rolDiasCaducidadPwd");
//			}
			
			if (rolEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"rolEstadoRegistro");
			}

			if ((rolEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(rolEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolEstadoRegistro");
			}

			if (rolNombre == null) {
				throw new ZMessManager().new EmptyFieldException("rolNombre");
			}

			if ((rolNombre != null) &&
					(Utilities.checkWordAndCheckWithlength(rolNombre, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolNombre");
			}

			if (sisCodigo_SegSistema == null) {
				throw new ZMessManager().new EmptyFieldException(
						"sisCodigo_SegSistema");
			}

			if ((sisCodigo_SegSistema != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							sisCodigo_SegSistema, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"sisCodigo_SegSistema");
			}

			if (usuCodigo_SegUsuario == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuCodigo_SegUsuario");
			}

			if ((usuCodigo_SegUsuario != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							usuCodigo_SegUsuario, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuCodigo_SegUsuario");
			}

			SegSistema segSistemaClass = logicSegSistema1.getSegSistema(sisCodigo_SegSistema);
			SegUsuario segUsuarioClass = logicSegUsuario2.getSegUsuario(usuCodigo_SegUsuario);

			if (segSistemaClass == null) {
				throw new ZMessManager().new ForeignException("segSistema");
			}

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = new SegRol();
			//entity.setRolCodigo(rolCodigo);
			entity.setRolDescripcion(rolDescripcion);
			entity.setRolDiasCaducidadPwd(rolDiasCaducidadPwd);
			entity.setRolEstadoRegistro(rolEstadoRegistro);
			entity.setRolNombre(rolNombre);
			entity.setSegSistema(segSistemaClass);
			entity.setSegUsuario(segUsuarioClass);
			entity.setEsAdmonDeAplicacion(esAdmonDeSistema);
			
			segRolDAO.save(entity);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void modificarRol(Long rolCodigo, String rolDescripcion,
			Long rolDiasCaducidadPwd, String rolEstadoRegistro, String rolNombre,
			Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario, String esAdmonDeSistema)
					throws Exception {
		SegRol entity = null;

		try {
			if (rolCodigo == null) {
				throw new ZMessManager().new EmptyFieldException("rolCodigo");
			}

			if ((rolCodigo != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							rolCodigo, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolCodigo");
			}

			if ((rolDescripcion != null) &&
					(Utilities.checkWordAndCheckWithlength(rolDescripcion, 200) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolDescripcion");
			}

//			if ((rolDiasCaducidadPwd != null) &&
//					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
//							rolDiasCaducidadPwd, 3, 0) == false)) {
//				throw new ZMessManager().new NotValidFormatException(
//						"rolDiasCaducidadPwd");
//			}

			if (rolEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"rolEstadoRegistro");
			}

			if ((rolEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(rolEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolEstadoRegistro");
			}

			if (rolNombre == null) {
				throw new ZMessManager().new EmptyFieldException("rolNombre");
			}

			if ((rolNombre != null) &&
					(Utilities.checkWordAndCheckWithlength(rolNombre, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"rolNombre");
			}

			if (sisCodigo_SegSistema == null) {
				throw new ZMessManager().new EmptyFieldException(
						"sisCodigo_SegSistema");
			}

			if ((sisCodigo_SegSistema != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							sisCodigo_SegSistema, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"sisCodigo_SegSistema");
			}

			if (usuCodigo_SegUsuario == null) {
				throw new ZMessManager().new EmptyFieldException(
						"usuCodigo_SegUsuario");
			}

			if ((usuCodigo_SegUsuario != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							usuCodigo_SegUsuario, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuCodigo_SegUsuario");
			}

			SegSistema segSistemaClass = logicSegSistema1.getSegSistema(sisCodigo_SegSistema);
			SegUsuario segUsuarioClass = logicSegUsuario2.getSegUsuario(usuCodigo_SegUsuario);

			if (segSistemaClass == null) {
				throw new ZMessManager().new ForeignException("segSistema");
			}

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = getSegRol(rolCodigo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setRolCodigo(rolCodigo);
			entity.setRolDescripcion(rolDescripcion);
			entity.setRolDiasCaducidadPwd(rolDiasCaducidadPwd);
			entity.setRolEstadoRegistro(rolEstadoRegistro);
			entity.setRolNombre(rolNombre);
			entity.setSegSistema(segSistemaClass);
			entity.setSegUsuario(segUsuarioClass);
			entity.setEsAdmonDeAplicacion(esAdmonDeSistema);
			
			segRolDAO.update(entity);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
    
    @Override
    @Transactional(readOnly = true)
    public List<SegRolDTO> consultarRolesPorSistemaDTO(Long sisCodigo) throws Exception{
    	try {

			List<SegRolDTO> segRolDTO = new ArrayList<SegRolDTO>();
			SegSistema sis = null;

			if (sisCodigo ==null) { 
				throw new Exception("El usuario debe ser administrador de algun sistema");
			}

			List<SegRol> objectsRol = segRolDAO.consultarRolesPorSistema(sisCodigo);

			for (SegRol segRol : objectsRol) {

				SegRolDTO segRolDTO2 = new SegRolDTO();

				segRolDTO2.setRolCodigo(segRol.getRolCodigo());
				segRolDTO2.setRolDescripcion(segRol.getRolDescripcion());
				segRolDTO2.setRolDiasCaducidadPwd(segRol.getRolDiasCaducidadPwd());
				segRolDTO2.setRolEstadoRegistro(segRol.getRolEstadoRegistro()!=null && segRol.getRolEstadoRegistro().equals("A")? "Activo" : "Inactivo");
				segRolDTO2.setRolNombre(segRol.getRolNombre());
				segRolDTO2.setEsAdmonDeSistema(segRol.getEsAdmonDeAplicacion()!=null && segRol.getEsAdmonDeAplicacion().equals("S") ? "Si" : "No");

				if (segRol.getSegSistema()!=null) {
					segRolDTO2.setSisCodigo_SegSistema(segRol.getSegSistema().getSisNombre());
				}
				
				segRolDTO.add(segRolDTO2);
			}
			
			return segRolDTO;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SegRol> consultarRolesPorSistema(Long sisCodigo) throws Exception{
    	try {

			List<SegRol> segRol = new ArrayList<SegRol>();
			SegSistema sis = null;

			if (sisCodigo ==null) { 
				throw new Exception("El usuario debe ser administrador de algun sistema");
			}

			List<SegRol> objectsRol = segRolDAO.consultarRolesPorSistema(sisCodigo);

			return segRol;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public List<SegRol> consultarRolesPorUsuario(Long usuCodigo) throws Exception{
    	try {

    		List<SegRol> rolesDelUsuario = new ArrayList<SegRol>();
    		
    		//Se consulta el usuario
    		SegUsuario segUsuario = segUsuarioLogic.getSegUsuario(usuCodigo);
    		if (segUsuario == null){
    			throw new Exception ("No existe el usuario " + usuCodigo);
    		}
    		
    		//Se consultan los roles del usuario
    		Object[] variablesConsultaRolesUsuario = {"segUsuarioBySegUsuarioUsuCodigo.usuCodigo", false, segUsuario.getUsuCodigo(), "=",
    				"rluEstadoRegistro", true, "A", "="};
    		
    		List<SegRolUsuario> rolesUsuario = segRolUsuarioLogic.findByCriteria(variablesConsultaRolesUsuario, null, null);
    		
			if (rolesUsuario != null){
				for (SegRolUsuario segRolUsuario : rolesUsuario) {
					rolesDelUsuario.add(segRolUsuario.getSegRol());
				}
			}
			
			return rolesDelUsuario;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
    }
    
    /**
     * 
     * @author Andrés Felipe Vargas López
     * @version Jun 18, 2018
     *
     * @see com.vortexbird.sentinel.modelo.control.ISegRolLogic#consultarRolPorSistemaCompania(java.lang.Long, java.lang.Long)
     *
     */
    @Override
    @Transactional(readOnly = true)
	public List<SegRolDTO> consultarRolPorSistemaCompania(String sistema, String compania) throws Exception {
		try {
			if(sistema == null || sistema.trim().equals("")) {
				throw new Exception("Por favor ingrese un sistema valido");
			}
			
			if(compania == null || compania.trim().equals("")) {
				throw new Exception("Por favor ingrese una compania valida");
			}
			//AFVL Se verifica que los parametros indicados sean numericos
			Long sisCodigo = Long.parseLong(sistema);
			Long ciaCodigo = Long.parseLong(compania);
			
			return segRolDAO.consultarRolPorSistemaCompania(sisCodigo, ciaCodigo);
			
		} catch (NumberFormatException e) {
			log.error("El sistema o la compania indicados no son numericos", e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @author Andrés Felipe Vargas López
	 * @version Jun 18, 2018
	 *
	 * @see com.vortexbird.sentinel.modelo.control.ISegRolLogic#consultarRolPorUsuarioSistemaCompania(java.lang.Long, java.lang.Long, java.lang.String)
	 *
	 */
    @Override
    @Transactional(readOnly = true)
	public List<SegRolDTO> consultarRolPorUsuarioSistemaCompania(String sistema, String compania, String login) throws Exception {
		try {
			//AFVL Valdio que se haya ingresado un valor valido para el sistema
			if(sistema == null || sistema.trim().equals("")) {
				throw new Exception("Por favor ingrese un sistema valido");
			}
			
			//AFVL Valdio que se haya ingresado un valor valido para la compania
			if(compania == null || compania.trim().equals("")) {
				throw new Exception("Por favor ingrese una compania valida");
			}
			
			//AFVL Valdio que se haya ingresado un valor valido para el login
			if(login == null || login.trim().equals("")) {
				throw new Exception("Por favor ingrese un login valido");
			}
			
			//AFVL Se verifica que los parametros indicados sean numericos
			Long sisCodigo = Long.parseLong(sistema);
			Long ciaCodigo = Long.parseLong(compania);
			
			return segRolDAO.consultarRolPorUsuarioSistemaCompania(sisCodigo, ciaCodigo, login);
			
		} catch (NumberFormatException e) {
			log.error("El sistema o la compania indicados no son numericos", e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.vortexbird.sentinel.modelo.control.ISegRolLogic#crearRol(com.vortexbird.sentinel.modelo.dto.SegRolDTO)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void crearRol(SegRolDTO rol) throws Exception{
		try {
			//Valido que llegue la info
			if(rol == null){
				throw new Exception("Rol no puede estár vacío");
			}
			if(rol.getRolDescripcion().trim().equals("")){
				throw new Exception("Nombre requerido");
			}
			if(rol.getOpciones().isEmpty()){
				throw new Exception("No se le han asignado permisos al rol");
			}
			
			//Valido que el sistema exista
			SegSistema sistema = logicSegSistema1.getSegSistema(Long.valueOf(rol.getSisCodigo_SegSistema()));
			if(sistema == null){
				throw new Exception("No existe el sistema con id " + rol.getSisCodigo_SegSistema());
			}
			
			//Valido que la compañía exista
			SegCompania compania = segCompaniaLogic.getSegCompania(rol.getCiaCodigo_SegCompania());
			if(compania == null){
				throw new Exception("No existe la compañia con id " + rol.getCiaCodigo_SegCompania());
			}
			
			//Valido que el sistema compañía exista
			SegSistemaCia sistemaCia = segSistemaCiaLogic.getSegSistemaCiaBySistemaYCompania(sistema.getSisCodigo(), compania.getCiaCodigo());
			if(sistemaCia == null || !sistema.getSisEstadoRegistro().equals(Constantes.ESTADO_ACTIVO)){
				throw new Exception("El sistema no se encuentra asociado a la compañía");
			}
			
			//Valido el usuario mod exista
			SegUsuario usuarioMod = segUsuarioLogic.getSegUsuario(rol.getUsuCodigo_SegUsuario());
			if(usuarioMod == null){
				throw new Exception("El usuario indicado no existe");
			}
			
			List<SegOpcion> opciones = new ArrayList<SegOpcion>();
			//Valido que las opciones a darles permiso existan
			for (Long opcCodigo : rol.getOpciones()) {
				SegOpcion opcion = segOpcionLogic.getSegOpcion(opcCodigo);
				if(opcion == null){
					throw new Exception("La opcion no existe");
				}
				
				opciones.add(opcion);
			}
			
			//Valido que el rol no exista
			String descripcionRol = rol.getRolDescripcion().trim().toUpperCase();
			String nombreRol = descripcionRol.replaceAll(" ", "_");
			
			SegRol entity = consultarRolPorNombreYSistema(nombreRol, sistema.getSisCodigo());
			if(entity != null){
				throw new Exception("Ya existe el rol en el sistema");
			}
			
			//Creo la entidad
			entity = new SegRol();
			entity.setRolNombre(nombreRol);
			entity.setRolDescripcion(descripcionRol);
			
			entity.setRolDiasCaducidadPwd(rol.getRolDiasCaducidadPwd());
			entity.setSegSistema(sistema);
			entity.setSegUsuario(usuarioMod);
			entity.setEsAdmonDeAplicacion(rol.getEsAdmonDeSistema());
			
			entity.setRolEstadoRegistro(Constantes.ESTADO_ACTIVO);
			segRolDAO.save(entity);
			
			//Le doy los permisos al rol
			for (SegOpcion segOpcion : opciones) {
				SegPermiso permiso = new SegPermiso();
				
				permiso.setPerEstadoRegistro(Constantes.ESTADO_ACTIVO);
				permiso.setSegRol(entity);
				permiso.setSegOpcion(segOpcion);
				permiso.setSegUsuarioByUsuCodigo(null);
				permiso.setSegGrupoOpcion(null);
				permiso.setSegSistemaCia(sistemaCia);
				permiso.setSegSucursal(null);
				permiso.setSegUsuarioByModUsuCodigo(usuarioMod);
				
				segPermisoDAO.save(permiso);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Transactional(readOnly = true)
	private SegRol consultarRolPorNombreYSistema(String nombre, Long sisCodigo) throws Exception{
		try {
			Object[] variables = {"rolNombre", true, nombre, "=",
					"segSistema.sisCodigo", false, sisCodigo, "="};
			
			List<SegRol> roles = findByCriteria(variables, null, null);
			
			if(roles == null || roles.isEmpty()){
				return null;
			}
			
			return roles.get(0);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		
		
	}

	/** 
	 * (non-Javadoc)
	 * @see com.vortexbird.sentinel.modelo.control.ISegRolLogic#modificarRol(com.vortexbird.sentinel.modelo.dto.SegRolDTO)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void modificarRol(SegRolDTO rol) throws Exception {
		try {
			//Valido que llegue la info
			if(rol == null){
				throw new Exception("Rol no puede estár vacío");
			}
			if(rol.getRolNombre().trim().equals("")){
				throw new Exception("Nombre requerido");
			}
			if(rol.getOpciones().isEmpty()){
				throw new Exception("No se le han asignado permisos al rol");
			}
			if(rol.getRolCodigo() == null){
				throw new Exception("No se ha asignado al id del rol");
			}
			
			//Valido que el sistema exista
			SegSistema sistema = logicSegSistema1.getSegSistema(Long.valueOf(rol.getSisCodigo_SegSistema()));
			if(sistema == null){
				throw new Exception("No existe el sistema con id " + rol.getSisCodigo_SegSistema());
			}
			
			//Valido que la compañía exista
			SegCompania compania = segCompaniaLogic.getSegCompania(rol.getCiaCodigo_SegCompania());
			if(compania == null){
				throw new Exception("No existe la compañia con id " + rol.getCiaCodigo_SegCompania());
			}
			
			//Valido que el sistema compañía exista
			SegSistemaCia sistemaCia = segSistemaCiaLogic.getSegSistemaCiaBySistemaYCompania(sistema.getSisCodigo(), compania.getCiaCodigo());
			if(sistemaCia == null || !sistema.getSisEstadoRegistro().equals(Constantes.ESTADO_ACTIVO)){
				throw new Exception("El sistema no se encuentra asociado a la compañía");
			}
			
			//Valido el usuario mod exista
			SegUsuario usuarioMod = segUsuarioLogic.getSegUsuario(rol.getUsuCodigo_SegUsuario());
			if(usuarioMod == null){
				throw new Exception("El usuario indicado no existe");
			}
			
			List<SegOpcion> opciones = new ArrayList<SegOpcion>();
			//Valido que las opciones a darles permiso existan
			SegOpcion opcion = null;
			for (Long opcCodigo : rol.getOpciones()) {
				opcion = segOpcionLogic.getSegOpcion(opcCodigo);
				if(opcion == null){
					throw new Exception("La opcion no existe");
				}
				
				opciones.add(opcion);
			}
			
			//valido que el rol exista
			SegRol entity = getSegRol(rol.getRolCodigo());
			if(entity == null){
				throw new Exception("El rol no existe");
			}
			
			//Valido que el nombre del rol no se repita
			String descripcionRol = rol.getRolDescripcion().trim().toUpperCase();
			String nombreRol = descripcionRol.replaceAll(" ", "_");
			
			SegRol entity1 = consultarRolPorNombreYSistema(nombreRol, sistema.getSisCodigo());
			if(entity1 != null && !entity1.getRolCodigo().equals(rol.getRolCodigo())){
				throw new Exception("Ya existe un rol con ese nombre");
			}
			
			entity.setRolNombre(nombreRol);
			entity.setRolDescripcion(descripcionRol);
			
			entity.setRolDiasCaducidadPwd(rol.getRolDiasCaducidadPwd());
			entity.setSegSistema(sistema);
			entity.setSegUsuario(usuarioMod);
			entity.setEsAdmonDeAplicacion(rol.getEsAdmonDeSistema());
			
			entity.setRolEstadoRegistro(Constantes.ESTADO_ACTIVO);
			segRolDAO.save(entity);
			
			//Le quito los permisos al rol
			
			Object[] variables = {"segRol.rolCodigo", false, rol.getRolCodigo(), "=",
					"segOpcion.opcCodigo", false, "NULL", "IS NOT",
					"perEstadoRegistro", true, Constantes.ESTADO_ACTIVO, "="};
			List<SegPermiso> permisos = segPermisoLogic.findByCriteria(variables, null, null);
			
			Map<Long, SegPermiso> opcionesEnBD = new HashMap<Long, SegPermiso>();
			
			for (SegPermiso segPermiso : permisos) {
				opcionesEnBD.put(segPermiso.getSegOpcion().getOpcCodigo(), segPermiso);
				segPermiso.setPerEstadoRegistro(Constantes.ESTADO_INACTIVO);
			}
			
			SegPermiso permiso = null;
			
			//Le doy los permisos al rol
			for (SegOpcion segOpcion : opciones) {
				
				if(opcionesEnBD.containsKey(segOpcion.getOpcCodigo())){
					permiso = opcionesEnBD.get(segOpcion.getOpcCodigo());
					permiso.setPerEstadoRegistro(Constantes.ESTADO_ACTIVO);
				}else{
					permiso = new SegPermiso();
					permiso.setPerEstadoRegistro(Constantes.ESTADO_ACTIVO);
					permiso.setSegRol(entity);
					permiso.setSegOpcion(segOpcion);
					permiso.setSegUsuarioByUsuCodigo(null);
					permiso.setSegGrupoOpcion(null);
					permiso.setSegSistemaCia(sistemaCia);
					permiso.setSegSucursal(null);
					permiso.setSegUsuarioByModUsuCodigo(usuarioMod);
				}
				segPermisoDAO.save(permiso);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
}
