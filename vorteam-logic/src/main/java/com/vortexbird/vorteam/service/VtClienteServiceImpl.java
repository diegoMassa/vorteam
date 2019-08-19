package com.vortexbird.vorteam.service;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.dto.VtClienteDTO;
import com.vortexbird.vorteam.dto.VtProyectoDTO;
import com.vortexbird.vorteam.exception.*;
import com.vortexbird.vorteam.mapper.VtClienteMapper;
import com.vortexbird.vorteam.repository.*;
import com.vortexbird.vorteam.utility.Constantes;
import com.vortexbird.vorteam.utility.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.*;

import java.util.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service
public class VtClienteServiceImpl implements VtClienteService {
    private static final Logger log = LoggerFactory.getLogger(VtClienteServiceImpl.class);

    /**
     * Repository injected by Spring that manages VtCliente entities
     *
     */
    @Autowired
    private VtClienteRepository vtClienteRepository;
    @Autowired
    private VtClienteMapper vtClienteMapper;
    @Autowired
    private Validator validator;

    /**
    * Repository injected by Spring that manages VtProyecto entities
    *
    */
    @Autowired
    private VtProyectoRepository vtProyectoRepository;
    
    @Autowired
    private VtProyectoService vtProyectoService;

    /**
    * Service injected by Spring that manages VtTipoIdentificacion entities
    *
    */
    @Autowired
    VtTipoIdentificacionService serviceVtTipoIdentificacion1;

    public void validateVtCliente(VtCliente vtCliente)
        throws Exception {
        try {
            Set<ConstraintViolation<VtCliente>> constraintViolations = validator.validate(vtCliente);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<VtCliente> constraintViolation : constraintViolations) {
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
    public List<VtCliente> getVtCliente() throws Exception {
        log.debug("finding all VtCliente instances");

        List<VtCliente> list = new ArrayList<VtCliente>();

        try {
            list = vtClienteRepository.findAll();
        } catch (Exception e) {
            log.error("finding all VtCliente failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "VtCliente");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveVtCliente(VtCliente entity) throws Exception {
        log.debug("saving VtCliente instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("VtCliente");
            }

            validateVtCliente(entity);

            vtClienteRepository.save(entity);
            log.debug("save VtCliente successful");
        } catch (Exception e) {
            log.error("save VtCliente failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteVtCliente(VtCliente entity) throws Exception {
        log.debug("deleting VtCliente instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("VtCliente");
        }

        if (entity.getClieId() == null) {
            throw new ZMessManager().new EmptyFieldException("clieId");
        }

        List<VtProyecto> vtProyectos = null;

        try {
            vtProyectos = vtProyectoRepository.findByProperty("vtCliente.clieId",
                    entity.getClieId());

            if (Utilities.validationsList(vtProyectos) == true) {
                throw new ZMessManager().new DeletingException("vtProyectos");
            }

            vtClienteRepository.deleteById(entity.getClieId());
            log.debug("delete VtCliente successful");
        } catch (Exception e) {
            log.error("delete VtCliente failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateVtCliente(VtCliente entity) throws Exception {
        log.debug("updating VtCliente instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("VtCliente");
            }

            validateVtCliente(entity);

            vtClienteRepository.update(entity);

            log.debug("update VtCliente successful");
        } catch (Exception e) {
            log.error("update VtCliente failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<VtClienteDTO> getDataVtCliente() throws Exception {
        try {
            List<VtCliente> vtCliente = vtClienteRepository.findAll();

            List<VtClienteDTO> vtClienteDTO = new ArrayList<VtClienteDTO>();

            for (VtCliente vtClienteTmp : vtCliente) {
                VtClienteDTO vtClienteDTO2 = vtClienteMapper.vtClienteToVtClienteDTO(vtClienteTmp);
                vtClienteDTO.add(vtClienteDTO2);
            }

            return vtClienteDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtCliente getVtCliente(Long clieId) throws Exception {
        log.debug("getting VtCliente instance");

        VtCliente entity = null;

        try {
            if (vtClienteRepository.findById(clieId).isPresent()) {
                entity = vtClienteRepository.findById(clieId).get();
            }
        } catch (Exception e) {
            log.error("get VtCliente failed", e);
            throw new ZMessManager().new FindingException("VtCliente");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<VtCliente> findPageVtCliente(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<VtCliente> entity = null;

        try {
            entity = vtClienteRepository.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("VtCliente Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberVtCliente() throws Exception {
        Long entity = null;

        try {
            entity = vtClienteRepository.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("VtCliente Count");
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
    public List<VtCliente> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<VtCliente> list = new ArrayList<VtCliente>();
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
            list = vtClienteRepository.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtClienteService#clientesConProyectos()
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtClienteDTO> clientesConProyectos() throws Exception {
		List<VtClienteDTO> clientes;
		try {
			clientes = getDataVtCliente();
			
			//Recorrer clientes y llenar sus proyectos
			for (VtClienteDTO vtClienteDTO : clientes) {
				
				VtTipoIdentificacion tipoIdentificacion = serviceVtTipoIdentificacion1.getVtTipoIdentificacion(vtClienteDTO.getTiidId_VtTipoIdentificacion());
				vtClienteDTO.setTipoIdentificacion(tipoIdentificacion.getDescripcion());
				
				Object[] variablesBusquedaProyectos = {"vtCliente.clieId", false, vtClienteDTO.getClieId(), "=",
								"activo", true, Constantes.ESTADO_ACTIVO, "="};
				
				List<VtProyecto> proyectos = vtProyectoService.findByCriteria(variablesBusquedaProyectos, null, null);  
				if(proyectos != null && !proyectos.isEmpty()) {
					List<VtProyectoDTO> proyectosDTO = new ArrayList<>();
					for (VtProyecto vtProyecto : proyectos) {
						VtProyectoDTO proyectoDTO = new VtProyectoDTO();
						proyectoDTO.setActivo(vtProyecto.getActivo());
						proyectoDTO.setClieId_VtCliente(vtClienteDTO.getClieId());
						proyectoDTO.setCostoTotal(vtProyecto.getCostoTotal());
						proyectoDTO.setFechaCreacion(vtProyecto.getFechaCreacion());
						proyectoDTO.setFechaModificacion(vtProyecto.getFechaModificacion());
						proyectoDTO.setNombreProyecto(vtProyecto.getNombreProyecto());
						proyectoDTO.setProyId(vtProyecto.getProyId());
						proyectoDTO.setUsuaCreador(vtProyecto.getUsuaCreador());
						proyectoDTO.setUsuaModificador(vtProyecto.getUsuaModificador());
						
						proyectosDTO.add(proyectoDTO);
					}
					
					vtClienteDTO.setProyectos(proyectosDTO);
				}
			}
			
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return clientes;
	}
}
