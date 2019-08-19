package com.vortexbird.vorteam.service;

import com.vortexbird.vorteam.domain.*;
import com.vortexbird.vorteam.dto.VtTipoActividadDTO;
import com.vortexbird.vorteam.exception.*;
import com.vortexbird.vorteam.mapper.VtTipoActividadMapper;
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
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Scope("singleton")
@Service
public class VtTipoActividadServiceImpl implements VtTipoActividadService {
	private static final Logger log = LoggerFactory.getLogger(VtTipoActividadServiceImpl.class);

	/**
	 * Repository injected by Spring that manages VtTipoActividad entities
	 *
	 */
	@Autowired
	private VtTipoActividadRepository vtTipoActividadRepository;
	@Autowired
	private VtTipoActividadMapper vtTipoActividadMapper;
	@Autowired
	private Validator validator;

	/**
	 * Service injected by Spring that manages VtClasificacionFinanciera entities
	 *
	 */
	@Autowired
	VtClasificacionFinancieraService serviceVtClasificacionFinanciera1;

	public void validateVtTipoActividad(VtTipoActividad vtTipoActividad) throws Exception {
		try {
			Set<ConstraintViolation<VtTipoActividad>> constraintViolations = validator.validate(vtTipoActividad);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<VtTipoActividad> constraintViolation : constraintViolations) {
					strMessage.append(constraintViolation.getPropertyPath().toString());
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
	public List<VtTipoActividad> getVtTipoActividad() throws Exception {
		log.debug("finding all VtTipoActividad instances");

		List<VtTipoActividad> list = new ArrayList<VtTipoActividad>();

		try {
			list = vtTipoActividadRepository.findAll();
		} catch (Exception e) {
			log.error("finding all VtTipoActividad failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "VtTipoActividad");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveVtTipoActividad(VtTipoActividad entity) throws Exception {
		log.debug("saving VtTipoActividad instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("VtTipoActividad");
			}

			validateVtTipoActividad(entity);

			vtTipoActividadRepository.save(entity);
			log.debug("save VtTipoActividad successful");
		} catch (Exception e) {
			log.error("save VtTipoActividad failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteVtTipoActividad(VtTipoActividad entity) throws Exception {
		log.debug("deleting VtTipoActividad instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("VtTipoActividad");
		}

		if (entity.getTiacId() == null) {
			throw new ZMessManager().new EmptyFieldException("tiacId");
		}

		try {
			vtTipoActividadRepository.deleteById(entity.getTiacId());
			log.debug("delete VtTipoActividad successful");
		} catch (Exception e) {
			log.error("delete VtTipoActividad failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateVtTipoActividad(VtTipoActividad entity) throws Exception {
		log.debug("updating VtTipoActividad instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("VtTipoActividad");
			}

			validateVtTipoActividad(entity);

			vtTipoActividadRepository.update(entity);

			log.debug("update VtTipoActividad successful");
		} catch (Exception e) {
			log.error("update VtTipoActividad failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<VtTipoActividadDTO> getDataVtTipoActividad() throws Exception {
		try {
			List<VtTipoActividad> vtTipoActividad = vtTipoActividadRepository.findAll();

			List<VtTipoActividadDTO> vtTipoActividadDTO = new ArrayList<VtTipoActividadDTO>();

			for (VtTipoActividad vtTipoActividadTmp : vtTipoActividad) {
				VtTipoActividadDTO vtTipoActividadDTO2 = vtTipoActividadMapper
						.vtTipoActividadToVtTipoActividadDTO(vtTipoActividadTmp);
				vtTipoActividadDTO.add(vtTipoActividadDTO2);
			}

			return vtTipoActividadDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public VtTipoActividad getVtTipoActividad(Long tiacId) throws Exception {
		log.debug("getting VtTipoActividad instance");

		VtTipoActividad entity = null;

		try {
			if (vtTipoActividadRepository.findById(tiacId).isPresent()) {
				entity = vtTipoActividadRepository.findById(tiacId).get();
			}
		} catch (Exception e) {
			log.error("get VtTipoActividad failed", e);
			throw new ZMessManager().new FindingException("VtTipoActividad");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<VtTipoActividad> findPageVtTipoActividad(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<VtTipoActividad> entity = null;

		try {
			entity = vtTipoActividadRepository.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("VtTipoActividad Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberVtTipoActividad() throws Exception {
		Long entity = null;

		try {
			entity = vtTipoActividadRepository.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("VtTipoActividad Count");
		} finally {
		}

		return entity;
	}

	/**
	 *
	 * @param varibles
	 *            este arreglo debera tener:
	 *
	 *            [0] = String variable = (String) varibles[i]; representa como se
	 *            llama la variable en el pojo
	 *
	 *            [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa
	 *            si el valor necesita o no ''(comillas simples)usado para campos de
	 *            tipo string
	 *
	 *            [2] = Object value = varibles[i + 2]; representa el valor que se
	 *            va a buscar en la BD
	 *
	 *            [3] = String comparator = (String) varibles[i + 3]; representa que
	 *            tipo de busqueda voy a hacer.., ejemplo: where nombre=william o
	 *            where nombre<>william, en este campo iria el tipo de comparador
	 *            que quiero si es = o <>
	 *
	 *            Se itera de 4 en 4..., entonces 4 registros del arreglo
	 *            representan 1 busqueda en un campo, si se ponen mas pues el
	 *            continuara buscando en lo que se le ingresen en los otros 4
	 *
	 *
	 * @param variablesBetween
	 *
	 *            la diferencia son estas dos posiciones
	 *
	 *            [0] = String variable = (String) varibles[j]; la variable ne la BD
	 *            que va a ser buscada en un rango
	 *
	 *            [1] = Object value = varibles[j + 1]; valor 1 para buscar en un
	 *            rango
	 *
	 *            [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un
	 *            rango ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
	 *
	 *            [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
	 *            ejemplo: a comparator1 1 and a < 5
	 *
	 *            [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
	 *            ejemplo: a comparador1>1 and a comparador2<5 (el original: a > 1
	 *            and a < 5) *
	 * @param variablesBetweenDates(en
	 *            este caso solo para mysql) [0] = String variable = (String)
	 *            varibles[k]; el nombre de la variable que hace referencia a una
	 *            fecha
	 *
	 *            [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben
	 *            ser dates)
	 *
	 *            [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben
	 *            ser dates)
	 *
	 *            esto hace un between entre las dos fechas.
	 *
	 * @return lista con los objetos que se necesiten
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<VtTipoActividad> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<VtTipoActividad> list = new ArrayList<VtTipoActividad>();
		String where = new String();
		String tempWhere = new String();

		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				if ((variables[i] != null) && (variables[i + 1] != null) && (variables[i + 2] != null)
						&& (variables[i + 3] != null)) {
					String variable = (String) variables[i];
					Boolean booVariable = (Boolean) variables[i + 1];
					Object value = variables[i + 2];
					String comparator = (String) variables[i + 3];

					if (booVariable.booleanValue()) {
						tempWhere = (tempWhere.length() == 0)
								? ("(model." + variable + " " + comparator + " \'" + value + "\' )")
								: (tempWhere + " AND (model." + variable + " " + comparator + " \'" + value + "\' )");
					} else {
						tempWhere = (tempWhere.length() == 0)
								? ("(model." + variable + " " + comparator + " " + value + " )")
								: (tempWhere + " AND (model." + variable + " " + comparator + " " + value + " )");
					}
				}

				i = i + 3;
			}
		}

		if (variablesBetween != null) {
			for (int j = 0; j < variablesBetween.length; j++) {
				if ((variablesBetween[j] != null) && (variablesBetween[j + 1] != null)
						&& (variablesBetween[j + 2] != null) && (variablesBetween[j + 3] != null)
						&& (variablesBetween[j + 4] != null)) {
					String variable = (String) variablesBetween[j];
					Object value = variablesBetween[j + 1];
					Object value2 = variablesBetween[j + 2];
					String comparator1 = (String) variablesBetween[j + 3];
					String comparator2 = (String) variablesBetween[j + 4];
					tempWhere = (tempWhere.length() == 0)
							? ("(" + value + " " + comparator1 + " " + variable + " and " + variable + " " + comparator2
									+ " " + value2 + " )")
							: (tempWhere + " AND (" + value + " " + comparator1 + " " + variable + " and " + variable
									+ " " + comparator2 + " " + value2 + " )");
				}

				j = j + 4;
			}
		}

		if (variablesBetweenDates != null) {
			for (int k = 0; k < variablesBetweenDates.length; k++) {
				if ((variablesBetweenDates[k] != null) && (variablesBetweenDates[k + 1] != null)
						&& (variablesBetweenDates[k + 2] != null)) {
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
							? ("(model." + variable + " between " + value + " and " + value2 + ")")
							: (tempWhere + " AND (model." + variable + " between " + value + " and " + value2 + ")");
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
			list = vtTipoActividadRepository.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtTipoActividadService#tiposActividadesConClasificacionFinanciera()
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtTipoActividadDTO> tiposActividadesConClasificacionFinanciera() throws Exception {
		List<VtTipoActividadDTO> tiposActividadDTO = null;
		try {
			List<VtTipoActividad> tiposActividad = getVtTipoActividad();
			tiposActividadDTO = vtTipoActividadMapper.listVtTipoActividadToListVtTipoActividadDTO(tiposActividad);
			for (VtTipoActividadDTO tipoActividadDTO : tiposActividadDTO) {
				VtClasificacionFinanciera clasificacionFinanciera = serviceVtClasificacionFinanciera1
						.getVtClasificacionFinanciera(tipoActividadDTO.getClfiId_VtClasificacionFinanciera());
				tipoActividadDTO.setClasificacionFinanciera(clasificacionFinanciera.getDescripcion());
				tipoActividadDTO.setActivoDescripcion(tipoActividadDTO.getActivo().trim().equalsIgnoreCase(Constantes.ESTADO_ACTIVO)?"ACTIVO":"INACTIVO");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return tiposActividadDTO;
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtTipoActividadService#tiposActividadesPorEstado(java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtTipoActividadDTO> tiposActividadesPorEstado(String activo) throws Exception {
		List<VtTipoActividadDTO> tiposActividadDTO = null;
		try {
			List<VtTipoActividad> tiposActividad = vtTipoActividadRepository.findByProperty("activo", activo.toUpperCase().trim());
			tiposActividadDTO = vtTipoActividadMapper.listVtTipoActividadToListVtTipoActividadDTO(tiposActividad);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return tiposActividadDTO;
	}
}
