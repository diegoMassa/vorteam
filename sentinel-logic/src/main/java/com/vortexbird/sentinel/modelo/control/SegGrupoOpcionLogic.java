package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.dataaccess.dao.*;
import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegGrupoOpcionDTO;
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
@Service("SegGrupoOpcionLogic")
public class SegGrupoOpcionLogic implements ISegGrupoOpcionLogic {
    private static final Logger log = LoggerFactory.getLogger(SegGrupoOpcionLogic.class);

    /**
    * DAO injected by Spring that manages SegGrupoOpcion entities
    *
    */
    @Autowired
    private ISegGrupoOpcionDAO segGrupoOpcionDAO;

    /**
    * DAO injected by Spring that manages SegOpcion entities
    *
    */
    @Autowired
    private ISegOpcionDAO segOpcionDAO;

    /**
    * DAO injected by Spring that manages SegPermiso entities
    *
    */
    @Autowired
    private ISegPermisoDAO segPermisoDAO;

    /**
    * Logic injected by Spring that manages SegGrupoOpcion entities
    *
    */
    @Autowired
    ISegGrupoOpcionLogic logicSegGrupoOpcion1;

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
    
    @Autowired
    ISegPermisoLogic segPermisoLogic;
    
    @Autowired
    ISegRolLogic segRolLogic;

    @Transactional(readOnly = true)
    public List<SegGrupoOpcion> getSegGrupoOpcion() throws Exception {
        log.debug("finding all SegGrupoOpcion instances");

        List<SegGrupoOpcion> list = new ArrayList<SegGrupoOpcion>();

        try {
            list = segGrupoOpcionDAO.findAll();
        } catch (Exception e) {
            log.error("finding all SegGrupoOpcion failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SegGrupoOpcion");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSegGrupoOpcion(SegGrupoOpcion entity)
        throws Exception {
        log.debug("saving SegGrupoOpcion instance");

        try {
            if (entity.getSegGrupoOpcion() == null) {
                throw new ZMessManager().new ForeignException("segGrupoOpcion");
            }

            if (entity.getSegSistema() == null) {
                throw new ZMessManager().new ForeignException("segSistema");
            }

            if (entity.getSegUsuario() == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            if (entity.getGruCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("gruCodigo");
            }

            if ((entity.getGruDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getGruDescripcion(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "gruDescripcion");
            }

            if (entity.getGruEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "gruEstadoRegistro");
            }

            if ((entity.getGruEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getGruEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "gruEstadoRegistro");
            }

            if ((entity.getGruIcono() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getGruIcono(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException("gruIcono");
            }

            if ((entity.getGruLlaveAcceso() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getGruLlaveAcceso(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "gruLlaveAcceso");
            }

            if (entity.getGruNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("gruNombre");
            }

            if ((entity.getGruNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getGruNombre(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "gruNombre");
            }

            if (entity.getSegGrupoOpcion().getGruCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "gruCodigo_SegGrupoOpcion");
            }

            if (entity.getSegSistema().getSisCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "sisCodigo_SegSistema");
            }

            if (entity.getSegUsuario().getUsuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            if (getSegGrupoOpcion(entity.getGruCodigo()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            segGrupoOpcionDAO.save(entity);

            log.debug("save SegGrupoOpcion successful");
        } catch (Exception e) {
            log.error("save SegGrupoOpcion failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSegGrupoOpcion(SegGrupoOpcion entity)
        throws Exception {
        log.debug("deleting SegGrupoOpcion instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("SegGrupoOpcion");
        }

        if (entity.getGruCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("gruCodigo");
        }

        List<SegGrupoOpcion> segGrupoOpcions = null;
        List<SegOpcion> segOpcions = null;
        List<SegPermiso> segPermisos = null;

        try {
            segGrupoOpcions = segGrupoOpcionDAO.findByProperty("segGrupoOpcion.gruCodigo",
                    entity.getGruCodigo());

            if (Utilities.validationsList(segGrupoOpcions) == true) {
                throw new ZMessManager().new DeletingException(
                    "segGrupoOpcions");
            }

            segOpcions = segOpcionDAO.findByProperty("segGrupoOpcion.gruCodigo",
                    entity.getGruCodigo());

            if (Utilities.validationsList(segOpcions) == true) {
                throw new ZMessManager().new DeletingException("segOpcions");
            }

            segPermisos = segPermisoDAO.findByProperty("segGrupoOpcion.gruCodigo",
                    entity.getGruCodigo());

            if (Utilities.validationsList(segPermisos) == true) {
                throw new ZMessManager().new DeletingException("segPermisos");
            }

            segGrupoOpcionDAO.delete(entity);

            log.debug("delete SegGrupoOpcion successful");
        } catch (Exception e) {
            log.error("delete SegGrupoOpcion failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSegGrupoOpcion(SegGrupoOpcion entity)
        throws Exception {
        log.debug("updating SegGrupoOpcion instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "SegGrupoOpcion");
            }

            if (entity.getSegGrupoOpcion() == null) {
                throw new ZMessManager().new ForeignException("segGrupoOpcion");
            }

            if (entity.getSegSistema() == null) {
                throw new ZMessManager().new ForeignException("segSistema");
            }

            if (entity.getSegUsuario() == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            if (entity.getGruCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("gruCodigo");
            }

            if ((entity.getGruDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getGruDescripcion(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "gruDescripcion");
            }

            if (entity.getGruEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "gruEstadoRegistro");
            }

            if ((entity.getGruEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getGruEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "gruEstadoRegistro");
            }

            if ((entity.getGruIcono() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getGruIcono(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException("gruIcono");
            }

            if ((entity.getGruLlaveAcceso() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getGruLlaveAcceso(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "gruLlaveAcceso");
            }

            if (entity.getGruNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("gruNombre");
            }

            if ((entity.getGruNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getGruNombre(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "gruNombre");
            }

            if (entity.getSegGrupoOpcion().getGruCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "gruCodigo_SegGrupoOpcion");
            }

            if (entity.getSegSistema().getSisCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "sisCodigo_SegSistema");
            }

            if (entity.getSegUsuario().getUsuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            segGrupoOpcionDAO.update(entity);

            log.debug("update SegGrupoOpcion successful");
        } catch (Exception e) {
            log.error("update SegGrupoOpcion failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<SegGrupoOpcionDTO> getDataSegGrupoOpcion()
        throws Exception {
        try {
			List<SegGrupoOpcion> segGrupoOpcion = segGrupoOpcionDAO.findAll();

			List<SegGrupoOpcionDTO> segGrupoOpcionDTO = new ArrayList<SegGrupoOpcionDTO>();

			for (SegGrupoOpcion segGrupoOpcionTmp : segGrupoOpcion) {

				SegGrupoOpcionDTO segGrupoOpcionDTO2 = new SegGrupoOpcionDTO();
				segGrupoOpcionDTO2.setGruCodigo(segGrupoOpcionTmp.getGruCodigo().toString());
				segGrupoOpcionDTO2.setGruDescripcion((segGrupoOpcionTmp.getGruDescripcion() != null)? segGrupoOpcionTmp.getGruDescripcion() : null);
				segGrupoOpcionDTO2.setGruEstadoRegistro((segGrupoOpcionTmp.getGruEstadoRegistro().equals("0"))? "Inactivo" : "Activo");
				segGrupoOpcionDTO2.setGruLlaveAcceso((segGrupoOpcionTmp.getGruLlaveAcceso() != null)? segGrupoOpcionTmp.getGruLlaveAcceso() : null);
				segGrupoOpcionDTO2.setGruNombre((segGrupoOpcionTmp.getGruNombre() != null)? segGrupoOpcionTmp.getGruNombre() : null);
				segGrupoOpcionDTO2.setGruCodigo_SegGrupoOpcion((segGrupoOpcionTmp.getSegGrupoOpcion() != null)? segGrupoOpcionTmp.getSegGrupoOpcion().getGruCodigo().toString() : null);
				segGrupoOpcionDTO2.setSegNombre_SegGrupoPadre((segGrupoOpcionTmp.getSegGrupoOpcion() != null)? segGrupoOpcionTmp.getSegGrupoOpcion().getGruNombre() : null);
				segGrupoOpcionDTO2.setSisCodigo_SegSistema((segGrupoOpcionTmp.getSegSistema().getSisCodigo() != null)? segGrupoOpcionTmp.getSegSistema().getSisCodigo().toString() : null);
				segGrupoOpcionDTO2.setSisNombre_SegSistema((segGrupoOpcionTmp.getSegSistema()!=null)?segGrupoOpcionTmp.getSegSistema().getSisNombre():null);
				segGrupoOpcionDTO2.setUsuCodigo_SegUsuario((segGrupoOpcionTmp.getSegUsuario() != null)? segGrupoOpcionTmp.getSegUsuario().getUsuNombres() : null);
				segGrupoOpcionDTO2.setOrden((segGrupoOpcionTmp.getOrden() != null)? segGrupoOpcionTmp.getOrden() : null);

				segGrupoOpcionDTO.add(segGrupoOpcionDTO2);
			}

			return segGrupoOpcionDTO;
		} catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public SegGrupoOpcion getSegGrupoOpcion(Long gruCodigo)
        throws Exception {
        log.debug("getting SegGrupoOpcion instance");

        SegGrupoOpcion entity = null;

        try {
            entity = segGrupoOpcionDAO.findById(gruCodigo);
        } catch (Exception e) {
            log.error("get SegGrupoOpcion failed", e);
            throw new ZMessManager().new FindingException("SegGrupoOpcion");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<SegGrupoOpcion> findPageSegGrupoOpcion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SegGrupoOpcion> entity = null;

        try {
            entity = segGrupoOpcionDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "SegGrupoOpcion Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberSegGrupoOpcion() throws Exception {
        Long entity = null;

        try {
            entity = segGrupoOpcionDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "SegGrupoOpcion Count");
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
    public List<SegGrupoOpcion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SegGrupoOpcion> list = new ArrayList<SegGrupoOpcion>();
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
            list = segGrupoOpcionDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public List<SegGrupoOpcion> consultarGrupoOpcionesPorSistema(Long sisCodigo) throws Exception{
    	try {
			
    		return segGrupoOpcionDAO.consultarGrupoOpcionesPorSistema(sisCodigo);
    		
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void guardarGrupoOpcion(Long gruCodigo, String gruDescripcion,
			String gruEstadoRegistro, String gruLlaveAcceso, String gruNombre,
			Long gruCodigo_SegGrupoOpcion, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario) throws Exception {
		SegGrupoOpcion entity = null;

		try {

			if ((gruDescripcion != null) &&
					(Utilities.checkWordAndCheckWithlength(gruDescripcion, 200) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruDescripcion");
			}

			if (gruEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"gruEstadoRegistro");
			}

			if ((gruEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(gruEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruEstadoRegistro");
			}

			if ((gruLlaveAcceso != null) &&
					(Utilities.checkWordAndCheckWithlength(gruLlaveAcceso, 200) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruLlaveAcceso");
			}

			if (gruNombre == null) {
				throw new ZMessManager().new EmptyFieldException("gruNombre");
			}

			if ((gruNombre != null) &&
					(Utilities.checkWordAndCheckWithlength(gruNombre, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruNombre");
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

			SegGrupoOpcion segGrupoOpcionClass= null;

			if (gruCodigo_SegGrupoOpcion!=null) {
				segGrupoOpcionClass = logicSegGrupoOpcion1.getSegGrupoOpcion(gruCodigo_SegGrupoOpcion);
			}

			SegSistema segSistemaClass = logicSegSistema2.getSegSistema(sisCodigo_SegSistema);
			SegUsuario segUsuarioClass = logicSegUsuario3.getSegUsuario(usuCodigo_SegUsuario);

			//			if (segGrupoOpcionClass == null) {
			//				throw new ZMessManager().new ForeignException("segGrupoOpcion");
			//			}

			if (segSistemaClass == null) {
				throw new ZMessManager().new ForeignException("segSistema");
			}

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = new SegGrupoOpcion();
			//entity.setGruCodigo(gruCodigo);
			entity.setGruDescripcion(gruDescripcion);
			entity.setGruEstadoRegistro(gruEstadoRegistro);
			entity.setGruLlaveAcceso(gruLlaveAcceso);
			entity.setGruNombre(gruNombre);
			entity.setSegGrupoOpcion(segGrupoOpcionClass);
			entity.setSegSistema(segSistemaClass);
			entity.setSegUsuario(segUsuarioClass);
			
			segGrupoOpcionDAO.save(entity);
			
			//Si el que crea el grupo opcion, es un usuario super administrador,
			//Y el grupo se crea para el sistema 0 = Sistema de seguridad
			//se le otorgan los permisos de acceso a este, automáticamente
			if (usuCodigo_SegUsuario == 0L && sisCodigo_SegSistema == 0L){
				
				//Se consulta el rol 0 = Super administrador
				SegRol segRol = segRolLogic.getSegRol(0L);
				if (segRol == null){
					throw new Exception("Valide que exista el Rol con código 0 = Administrador Sistema de seguridad");
				}
				
				SegPermiso segPermiso = new SegPermiso();
				
				//segPermiso.setPerCodigo(perCodigo);
				segPermiso.setPerEstadoRegistro("A");
				segPermiso.setSegGrupoOpcion(entity);
				//segPermiso.setSegOpcion(segOpcion);
				segPermiso.setSegRol(segRol);
				//segPermiso.setSegSistemaCia(segSistemaCia);
				//segPermiso.setSegSucursal(segSucursal);
				//segPermiso.setSegUsuarioByModUsuCodigo(segUsuarioByModUsuCodigo);
				segPermiso.setSegUsuarioByUsuCodigo(segUsuarioClass);
				
				segPermisoLogic.saveSegPermiso(segPermiso);
				
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void modificarGrupoOpcion(Long gruCodigo, String gruDescripcion,
			String gruEstadoRegistro, String gruLlaveAcceso, String gruNombre,
			Long gruCodigo_SegGrupoOpcion, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario) throws Exception {
    	
		SegGrupoOpcion entity = null;

		try {
			if (gruCodigo == null) {
				throw new ZMessManager().new EmptyFieldException("gruCodigo");
			}

			if ((gruCodigo != null) &&
					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
							gruCodigo, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruCodigo");
			}

			if ((gruDescripcion != null) &&
					(Utilities.checkWordAndCheckWithlength(gruDescripcion, 200) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruDescripcion");
			}

			if (gruEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"gruEstadoRegistro");
			}

			if ((gruEstadoRegistro != null) &&
					(Utilities.checkWordAndCheckWithlength(gruEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruEstadoRegistro");
			}

			if ((gruLlaveAcceso != null) &&
					(Utilities.checkWordAndCheckWithlength(gruLlaveAcceso, 200) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruLlaveAcceso");
			}

			if (gruNombre == null) {
				throw new ZMessManager().new EmptyFieldException("gruNombre");
			}

			if ((gruNombre != null) &&
					(Utilities.checkWordAndCheckWithlength(gruNombre, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"gruNombre");
			}

			//			if (gruCodigo_SegGrupoOpcion == null) {
			//				throw new ZMessManager().new EmptyFieldException(
			//				"gruCodigo_SegGrupoOpcion");
			//			}

			//			if ((gruCodigo_SegGrupoOpcion != null) &&
			//					(Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
			//							gruCodigo_SegGrupoOpcion, 5, 0) == false)) {
			//				throw new ZMessManager().new NotValidFormatException(
			//				"gruCodigo_SegGrupoOpcion");
			//			}

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

			SegGrupoOpcion segGrupoOpcionClass= null;

			if (gruCodigo_SegGrupoOpcion!=null) {
				segGrupoOpcionClass = logicSegGrupoOpcion1.getSegGrupoOpcion(gruCodigo_SegGrupoOpcion);
			}

			SegSistema segSistemaClass = logicSegSistema2.getSegSistema(sisCodigo_SegSistema);
			SegUsuario segUsuarioClass = logicSegUsuario3.getSegUsuario(usuCodigo_SegUsuario);

			//			if (segGrupoOpcionClass == null) {
			//				throw new ZMessManager().new ForeignException("segGrupoOpcion");
			//			}

			if (segSistemaClass == null) {
				throw new ZMessManager().new ForeignException("segSistema");
			}

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			entity = getSegGrupoOpcion(gruCodigo);

			if (entity == null) {
				throw new ZMessManager(ZMessManager.ENTITY_NOENTITYTOUPDATE);
			}

			entity.setGruCodigo(gruCodigo);
			entity.setGruDescripcion(gruDescripcion);
			entity.setGruEstadoRegistro(gruEstadoRegistro);
			entity.setGruLlaveAcceso(gruLlaveAcceso);
			entity.setGruNombre(gruNombre);
			entity.setSegGrupoOpcion(segGrupoOpcionClass);
			entity.setSegSistema(segSistemaClass);
			entity.setSegUsuario(segUsuarioClass);
			
			segGrupoOpcionDAO.update(entity);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
    
    @Override
    @Transactional(readOnly = true)
    public List<SegGrupoOpcion> consultarGrupoOpcionesPorRol(Long rolCodigo) throws Exception{
    	try {
			
    		//Se consulta el rol
    		SegRol segRol = segRolLogic.getSegRol(rolCodigo);
    		
    		if (segRol == null || !segRol.getRolEstadoRegistro().equals("A")){
    			throw new Exception("No existe el rol " + rolCodigo);
    		}
    		
    		Set<SegGrupoOpcion> setOpciones = segRol.getSegSistema().getSegGrupoOpcions();
    		List<SegGrupoOpcion> opciones = new ArrayList<SegGrupoOpcion>(setOpciones);
    		
    		return opciones; 
    		
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
    }
    
    
}
