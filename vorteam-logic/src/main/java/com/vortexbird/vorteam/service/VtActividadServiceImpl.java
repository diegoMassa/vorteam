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
import com.vortexbird.vorteam.domain.VtAsignacion;
import com.vortexbird.vorteam.domain.VtPersonas;
import com.vortexbird.vorteam.domain.VtProyCosto;
import com.vortexbird.vorteam.domain.VtProyecto;
import com.vortexbird.vorteam.dto.VtActividadDTO;
import com.vortexbird.vorteam.dto.VtPersonasDTO;
import com.vortexbird.vorteam.exception.ZMessManager;
import com.vortexbird.vorteam.mapper.VtActividadMapper;
import com.vortexbird.vorteam.repository.IVtActividadRepository;
import com.vortexbird.vorteam.repository.VtActividadRepository;
import com.vortexbird.vorteam.repository.VtAsignacionRepository;
import com.vortexbird.vorteam.utility.Constantes;
import com.vortexbird.vorteam.utility.Utilities;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Scope("singleton")
@Service
public class VtActividadServiceImpl implements VtActividadService {
	private static final Logger log = LoggerFactory.getLogger(VtActividadServiceImpl.class);

	/**
	 * Repository injected by Spring that manages VtActividad entities
	 *
	 */
	@Autowired
	private VtActividadRepository vtActividadRepository;
	@Autowired
	private VtActividadMapper vtActividadMapper;
	@Autowired
	private Validator validator;

	/**
	 * Repository injected by Spring that manages VtAsignacion entities
	 *
	 */
	@Autowired
	private VtAsignacionRepository vtAsignacionRepository;

	/**
	 * Service injected by Spring that manages VtEstado entities
	 *
	 */
	@Autowired
	VtEstadoService serviceVtEstado1;

	/**
	 * Service injected by Spring that manages VtProyecto entities
	 *
	 */
	@Autowired
	VtProyectoService serviceVtProyecto2;

	/**
	 * Service injected by Spring that manages VtTipoActividad entities
	 *
	 */
	@Autowired
	VtTipoActividadService serviceVtTipoActividad3;

	/**
	 * Service injected by Spring that manages VtPersonas entities
	 *
	 */
	@Autowired
	private VtPersonasService vtPersonasService;

	/**
	 * Service injected by Spring that manages VtAsignacion entities
	 *
	 */
	@Autowired
	private VtAsignacionService vtAsignacionService;
	
	/**
	 * Service injected by Spring that manages VtProyCostoService entities
	 *
	 */
	@Autowired
	private VtProyCostoService vtProyCostoService;
	
	@Autowired
	private IVtActividadRepository vtActividadRepositoryImpl;

	public void validateVtActividad(VtActividad vtActividad) throws Exception {
		try {
			Set<ConstraintViolation<VtActividad>> constraintViolations = validator.validate(vtActividad);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<VtActividad> constraintViolation : constraintViolations) {
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
	public List<VtActividad> getVtActividad() throws Exception {
		log.debug("finding all VtActividad instances");

		List<VtActividad> list = new ArrayList<VtActividad>();

		try {
			list = vtActividadRepository.findAll();
		} catch (Exception e) {
			log.error("finding all VtActividad failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "VtActividad");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveVtActividad(VtActividad entity) throws Exception {
		log.debug("saving VtActividad instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("VtActividad");
			}

			validateVtActividad(entity);

			vtActividadRepository.save(entity);
			log.debug("save VtActividad successful");
		} catch (Exception e) {
			log.error("save VtActividad failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteVtActividad(VtActividad entity) throws Exception {
		log.debug("deleting VtActividad instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("VtActividad");
		}

		if (entity.getActiId() == null) {
			throw new ZMessManager().new EmptyFieldException("actiId");
		}

		List<VtAsignacion> vtAsignacions = null;

		try {
			vtAsignacions = vtAsignacionRepository.findByProperty("vtActividad.actiId", entity.getActiId());

			if (Utilities.validationsList(vtAsignacions) == true) {
				throw new ZMessManager().new DeletingException("vtAsignacions");
			}

			vtActividadRepository.deleteById(entity.getActiId());
			log.debug("delete VtActividad successful");
		} catch (Exception e) {
			log.error("delete VtActividad failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateVtActividad(VtActividad entity) throws Exception {
		log.debug("updating VtActividad instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("VtActividad");
			}

			validateVtActividad(entity);

			vtActividadRepository.update(entity);

			log.debug("update VtActividad successful");
		} catch (Exception e) {
			log.error("update VtActividad failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<VtActividadDTO> getDataVtActividad() throws Exception {
		try {
			List<VtActividad> vtActividad = vtActividadRepository.findAll();

			List<VtActividadDTO> vtActividadDTO = new ArrayList<VtActividadDTO>();

			for (VtActividad vtActividadTmp : vtActividad) {
				VtActividadDTO vtActividadDTO2 = vtActividadMapper.vtActividadToVtActividadDTO(vtActividadTmp);
				vtActividadDTO.add(vtActividadDTO2);
			}

			return vtActividadDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public VtActividad getVtActividad(Long actiId) throws Exception {
		log.debug("getting VtActividad instance");

		VtActividad entity = null;

		try {
			if (vtActividadRepository.findById(actiId).isPresent()) {
				entity = vtActividadRepository.findById(actiId).get();
			}
		} catch (Exception e) {
			log.error("get VtActividad failed", e);
			throw new ZMessManager().new FindingException("VtActividad");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<VtActividad> findPageVtActividad(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<VtActividad> entity = null;

		try {
			entity = vtActividadRepository.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("VtActividad Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberVtActividad() throws Exception {
		Long entity = null;

		try {
			entity = vtActividadRepository.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("VtActividad Count");
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
	public List<VtActividad> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<VtActividad> list = new ArrayList<VtActividad>();
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
			list = vtActividadRepository.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtActividadService#guardarActividadConAsignacionesPersonas(com.vortexbird.vorteam.domain.VtActividad,
	 *      java.util.List)
	 *
	 */
	@Override
	public String guardarActividadConAsignacionesPersonas(VtActividad actividad, List<VtPersonasDTO> personas)
			throws Exception {
		String mensajeRetorno = "Guardado Exitosamente";
		try {
			// GuardarActividad
			saveVtActividad(actividad);

			VtProyecto proyecto = serviceVtProyecto2.getVtProyecto(actividad.getVtProyecto().getProyId());

			if (!personas.isEmpty()) {
				for (VtPersonasDTO vtPersonasDTO : personas) {
					VtPersonas vtPersonas = vtPersonasService.getVtPersonas(vtPersonasDTO.getPersId());
					VtAsignacion asignacion = new VtAsignacion(null, Constantes.ESTADO_ACTIVO, new Date(), null,
							actividad.getUsuaCreador(), null, actividad, vtPersonas, null);
					vtAsignacionService.saveVtAsignacion(asignacion);

					// Validar que la persona esté asignada al proyecto, si no está asignada
					// entonces asignarla
					Object[] variablesBusquedaProyectoCosto = { 
							"activo", true, Constantes.ESTADO_ACTIVO, "=",
							"vtProyecto.proyId",false,proyecto.getProyId(),"=",
							"vtPersonas.persId",false,vtPersonas.getPersId(),"="
							};
					
					List<VtProyCosto> proyectoCostos = vtProyCostoService.findByCriteria(variablesBusquedaProyectoCosto, null, null);
					if(proyectoCostos.isEmpty()) {
						VtProyCosto proyectoCosto = new VtProyCosto(null, 
								Constantes.ESTADO_ACTIVO, 
								new Date(), 
								null, 
								actividad.getUsuaCreador(), 
								null, 
								vtPersonas.getValorHora(), 
								vtPersonas, 
								proyecto);
						vtProyCostoService.saveVtProyCosto(proyectoCosto);
						mensajeRetorno+=". "
								+ "Asignar Valor hora para: "+vtPersonas.getNombre()+" "+vtPersonas.getApellidos()+" en el proyecto "+proyecto.getNombreProyecto();
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return mensajeRetorno;
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtActividadService#consultaActividadesOrdenadaFechaDescendiente(java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtActividadDTO> consultaActividadesOrdenadaFechaDescendiente(String activo) throws Exception {
		try {
			if(activo == null || activo.trim().equals("")) {
				throw new Exception("No ha llegado el parámetro de Estado");
			}
			return vtActividadRepositoryImpl.consultaActividadesOrdenadaFechaDescendiente(activo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtActividadService#consultaMisActividades(java.lang.String, java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtActividadDTO> consultaMisActividades(String usuario, String activo) throws Exception {
		try {
			if(usuario == null || usuario.trim().equals("")) {
				throw new Exception("No ha llegado el usuario para consultar mis actividades");
			}
			if(activo == null || activo.trim().equals("")) {
				throw new Exception("No ha llegado el parámetro de Estado");
			}
			
			Object[] variablesBusquedaUsuario = {"email",true,usuario.trim().toLowerCase(),"="};
			List<VtPersonas> lstPersonas = vtPersonasService.findByCriteria(variablesBusquedaUsuario, null, null);
			if(lstPersonas.isEmpty()) {
				throw new Exception("No existe la persona con usuario "+usuario.trim().toLowerCase());
			}
			
			VtPersonas persona = lstPersonas.get(0);
			return vtActividadRepositoryImpl.consultaMisActividades(persona.getPersId(), activo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtActividadService#consultaMisActividadesLazy(java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, int, int)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtActividadDTO> consultaMisActividadesLazy(String usuario, String activo, Long proyId, Long estaId,
			Long tiacId, String sprint, String casoSoporte, String controlCambios, int first, int pageSize)
			throws Exception {
		try {
			if(usuario == null || usuario.trim().equals("")) {
				throw new Exception("No ha llegado el usuario para consultar mis actividades");
			}
			if(activo == null || activo.trim().equals("")) {
				throw new Exception("No ha llegado el parámetro de Estado");
			}
			
			Object[] variablesBusquedaUsuario = {"email",true,usuario.trim().toLowerCase(),"="};
			List<VtPersonas> lstPersonas = vtPersonasService.findByCriteria(variablesBusquedaUsuario, null, null);
			if(lstPersonas.isEmpty()) {
				throw new Exception("No existe la persona con usuario "+usuario.trim().toLowerCase());
			}
			
			VtPersonas persona = lstPersonas.get(0);
			return vtActividadRepositoryImpl.consultaMisActividadesLazy(persona.getPersId(), activo, proyId, estaId, tiacId, sprint, casoSoporte, controlCambios, first, pageSize);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtActividadService#consultaTotalMisActividadesLazy(java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public Long consultaTotalMisActividadesLazy(String usuario, String activo, Long proyId, Long estaId, Long tiacId,
			String sprint, String casoSoporte, String controlCambios) throws Exception {
		try {
			if(usuario == null || usuario.trim().equals("")) {
				throw new Exception("No ha llegado el usuario para consultar mis actividades");
			}
			if(activo == null || activo.trim().equals("")) {
				throw new Exception("No ha llegado el parámetro de Estado");
			}
			
			Object[] variablesBusquedaUsuario = {"email",true,usuario.trim().toLowerCase(),"="};
			List<VtPersonas> lstPersonas = vtPersonasService.findByCriteria(variablesBusquedaUsuario, null, null);
			if(lstPersonas.isEmpty()) {
				throw new Exception("No existe la persona con usuario "+usuario.trim().toLowerCase());
			}
			
			VtPersonas persona = lstPersonas.get(0);
			return vtActividadRepositoryImpl.consultaTotalMisActividadesLazy(persona.getPersId(), activo, proyId, estaId, tiacId, sprint, casoSoporte, controlCambios);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
}
