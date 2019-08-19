package com.vortexbird.vorteam.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.vorteam.domain.VtActividad;
import com.vortexbird.vorteam.domain.VtPersonas;
import com.vortexbird.vorteam.domain.VtProyCosto;
import com.vortexbird.vorteam.domain.VtProyecto;
import com.vortexbird.vorteam.dto.VtPersonasDTO;
import com.vortexbird.vorteam.dto.VtProyectoDTO;
import com.vortexbird.vorteam.exception.ZMessManager;
import com.vortexbird.vorteam.mapper.VtProyectoMapper;
import com.vortexbird.vorteam.repository.IVtProyectoRepository;
import com.vortexbird.vorteam.repository.VtActividadRepository;
import com.vortexbird.vorteam.repository.VtProyCostoRepository;
import com.vortexbird.vorteam.repository.VtProyectoRepository;
import com.vortexbird.vorteam.utility.Constantes;
import com.vortexbird.vorteam.utility.Utilities;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service
public class VtProyectoServiceImpl implements VtProyectoService {
    private static final Logger log = LoggerFactory.getLogger(VtProyectoServiceImpl.class);

    /**
     * Repository injected by Spring that manages VtProyecto entities
     *
     */
    @Autowired
    private VtProyectoRepository vtProyectoRepository;
    @Autowired
    private VtProyectoMapper vtProyectoMapper;
    @Autowired
    private Validator validator;
    @Autowired
    private IVtProyectoRepository vtProyectoRepositoryImpl;
    @Autowired
    private VtPersonasService vtPersonasService;
    @Autowired
    private VtProyCostoService vtProyCostoService;

    /**
    * Repository injected by Spring that manages VtActividad entities
    *
    */
    @Autowired
    private VtActividadRepository vtActividadRepository;

    /**
    * Repository injected by Spring that manages VtProyCosto entities
    *
    */
    @Autowired
    private VtProyCostoRepository vtProyCostoRepository;

    /**
    * Service injected by Spring that manages VtCliente entities
    *
    */
    @Autowired
    VtClienteService serviceVtCliente1;
    
    /**
     * Service injected by Spring that manages VtLineaNegocio entities
     *
     */
     @Autowired
     VtLineaNegocioService serviceVtLineaNegocio1;

    public void validateVtProyecto(VtProyecto vtProyecto)
        throws Exception {
        try {
            Set<ConstraintViolation<VtProyecto>> constraintViolations = validator.validate(vtProyecto);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<VtProyecto> constraintViolation : constraintViolations) {
                    strMessage.append(constraintViolation.getPropertyPath()
                                                         .toString());
                    strMessage.append(" - ");
                    strMessage.append(constraintViolation.getMessage());
                    strMessage.append(". \n");
                }

                throw new Exception(strMessage.toString());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VtProyecto> getVtProyecto() throws Exception {
        log.debug("finding all VtProyecto instances");

        List<VtProyecto> list = new ArrayList<VtProyecto>();

        try {
            list = vtProyectoRepository.findAll();
        } catch (Exception e) {
            log.error("finding all VtProyecto failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "VtProyecto");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveVtProyecto(VtProyecto entity) throws Exception {
        log.debug("saving VtProyecto instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("VtProyecto");
            }

            validateVtProyecto(entity);

            vtProyectoRepository.save(entity);
            log.debug("save VtProyecto successful");
        } catch (Exception e) {
            log.error("save VtProyecto failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteVtProyecto(VtProyecto entity) throws Exception {
        log.debug("deleting VtProyecto instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("VtProyecto");
        }

        if (entity.getProyId() == null) {
            throw new ZMessManager().new EmptyFieldException("proyId");
        }

        List<VtActividad> vtActividads = null;
        List<VtProyCosto> vtProyCostos = null;

        try {
            vtActividads = vtActividadRepository.findByProperty("vtProyecto.proyId",
                    entity.getProyId());

            if (Utilities.validationsList(vtActividads) == true) {
                throw new ZMessManager().new DeletingException("vtActividads");
            }

            vtProyCostos = vtProyCostoRepository.findByProperty("vtProyecto.proyId",
                    entity.getProyId());

            if (Utilities.validationsList(vtProyCostos) == true) {
                throw new ZMessManager().new DeletingException("vtProyCostos");
            }

            vtProyectoRepository.deleteById(entity.getProyId());
            log.debug("delete VtProyecto successful");
        } catch (Exception e) {
            log.error("delete VtProyecto failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateVtProyecto(VtProyecto entity) throws Exception {
        log.debug("updating VtProyecto instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("VtProyecto");
            }

            validateVtProyecto(entity);

            vtProyectoRepository.update(entity);

            log.debug("update VtProyecto successful");
        } catch (Exception e) {
            log.error("update VtProyecto failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<VtProyectoDTO> getDataVtProyecto() throws Exception {
        try {
            List<VtProyecto> vtProyecto = vtProyectoRepository.findAll();

            List<VtProyectoDTO> vtProyectoDTO = new ArrayList<VtProyectoDTO>();

            for (VtProyecto vtProyectoTmp : vtProyecto) {
                VtProyectoDTO vtProyectoDTO2 = vtProyectoMapper.vtProyectoToVtProyectoDTO(vtProyectoTmp);
                vtProyectoDTO.add(vtProyectoDTO2);
            }

            return vtProyectoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtProyecto getVtProyecto(Long proyId) throws Exception {
        log.debug("getting VtProyecto instance");

        VtProyecto entity = null;

        try {
            if (vtProyectoRepository.findById(proyId).isPresent()) {
                entity = vtProyectoRepository.findById(proyId).get();
            }
        } catch (Exception e) {
            log.error("get VtProyecto failed", e);
            throw new ZMessManager().new FindingException("VtProyecto");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<VtProyecto> findPageVtProyecto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<VtProyecto> entity = null;

        try {
            entity = vtProyectoRepository.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("VtProyecto Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberVtProyecto() throws Exception {
        Long entity = null;

        try {
            entity = vtProyectoRepository.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("VtProyecto Count");
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
    public List<VtProyecto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<VtProyecto> list = new ArrayList<VtProyecto>();
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
            list = vtProyectoRepository.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtProyectoService#consultaProyectosClientes()
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtProyectoDTO> consultaProyectosClientes(String activo) throws Exception {
		try {
			if(activo == null || activo.trim().equals("")) {
				throw new Exception("No ha llegado el parámetro de Estado");
			}
			return vtProyectoRepositoryImpl.consultaProyectosClientes(activo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtProyectoService#guardarProyectoConCostosPersonas(com.vortexbird.vorteam.domain.VtProyecto, java.util.List)
	 *
	 */
	@Override
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	public void guardarProyectoConCostosPersonas(VtProyecto proyecto, List<VtPersonasDTO> personas) throws Exception {
		try {
			//Guardar Proyecto
			saveVtProyecto(proyecto);
			
			if(!personas.isEmpty()) {
				for (VtPersonasDTO vtPersonasDTO: personas) {
					VtPersonas vtPersonas = vtPersonasService.getVtPersonas(vtPersonasDTO.getPersId());
					VtProyCosto proyectoCosto = new VtProyCosto(null, 
							Constantes.ESTADO_ACTIVO, 
							new Date(), 
							null, 
							proyecto.getUsuaCreador(), 
							null, 
							vtPersonasDTO.getValorHora(), 
							vtPersonas, 
							proyecto);
					vtProyCostoService.saveVtProyCosto(proyectoCosto);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtProyectoService#actualizarProyectoConCostosPersonas(com.vortexbird.vorteam.domain.VtProyecto, java.util.List)
	 *
	 */
	@Override
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	public void actualizarProyectoConCostosPersonas(VtProyecto proyecto, List<VtPersonasDTO> personas) throws Exception {
		try {
			//Guardar Proyecto
			updateVtProyecto(proyecto);
			
			//Buscar proyCosto por proyecto y borrarlos
			Object[] variablesBusquedaProyCosto = {"vtProyecto.proyId", false, proyecto.getProyId(), "=",
					"activo", true, Constantes.ESTADO_ACTIVO, "="};
	
			List<VtProyCosto> proyectosCostos = vtProyCostoService.findByCriteria(variablesBusquedaProyCosto, null, null);
			for (VtProyCosto vtProyCosto : proyectosCostos) {
				vtProyCostoService.deleteVtProyCosto(vtProyCosto);
			}
			
			//Si hay personas en los proyectos, adicionarlos a Proyectos Costos
			if(!personas.isEmpty()) {
				for (VtPersonasDTO vtPersonasDTO : personas) {
					VtPersonas vtPersonas = vtPersonasService.getVtPersonas(vtPersonasDTO.getPersId());
					VtProyCosto proyectoCosto = new VtProyCosto(null, 
							Constantes.ESTADO_ACTIVO, 
							new Date(), 
							null, 
							proyecto.getUsuaModificador(), 
							null, 
							vtPersonasDTO.getValorHora(), 
							vtPersonas, 
							proyecto);
					vtProyCostoService.saveVtProyCosto(proyectoCosto);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtProyectoService#consultaProyectosClientesRecursos(java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtProyectoDTO> consultaProyectosClientesRecursos(String activo) throws Exception {
		try {
			if(activo == null || activo.trim().equals("")) {
				throw new Exception("No ha llegado el parámetro de Estado");
			}
			return vtProyectoRepositoryImpl.consultaProyectosClientesRecursos(activo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtProyectoService#consultaProyectos(java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtProyectoDTO> consultaProyectos(String activo) throws Exception {
		try {
			if(activo == null || activo.trim().equals("")) {
				throw new Exception("No ha llegado el parámetro de Estado");
			}
			return vtProyectoRepositoryImpl.consultaProyectos(activo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 15, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtProyectoService#consultaProyectosActivosPersona(java.lang.Long, java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtProyectoDTO> consultaProyectosActivosPersona(Long persId, String activo) throws Exception {
		try {
			if(persId == null || persId.equals(0L)) {
				throw new Exception("No ha llegado la persona");
			}
			if(activo == null || activo.trim().equals("")) {
				throw new Exception("No ha llegado el parámetro de Estado");
			}
			return vtProyectoRepositoryImpl.consultaProyectosActivosPersona(persId, activo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version oct. 05, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtProyectoService#consultaProyectosTodosPersona(java.lang.Long, java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtProyectoDTO> consultaProyectosTodosPersona(Long persId, String activo) throws Exception {
		try {
			if(persId == null || persId.equals(0L)) {
				throw new Exception("No ha llegado la persona");
			}
			if(activo == null || activo.trim().equals("")) {
				throw new Exception("No ha llegado el parámetro de Estado");
			}
			return vtProyectoRepositoryImpl.consultaProyectosTodosPersona(persId, activo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	
}
