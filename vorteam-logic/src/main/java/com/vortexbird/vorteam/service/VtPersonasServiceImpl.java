package com.vortexbird.vorteam.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
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
import org.springframework.web.client.RestTemplate;

import com.vortexbird.sentinel.dto.ResultadoCrearUsuarioDTO;
import com.vortexbird.sentinel.dto.UsuarioDTO;
import com.vortexbird.vorteam.domain.VtAsignacion;
import com.vortexbird.vorteam.domain.VtParametro;
import com.vortexbird.vorteam.domain.VtPersonas;
import com.vortexbird.vorteam.domain.VtProyCosto;
import com.vortexbird.vorteam.domain.VtProyecto;
import com.vortexbird.vorteam.dto.GrupoDTO;
import com.vortexbird.vorteam.dto.VtPersonasDTO;
import com.vortexbird.vorteam.dto.VtProyectoDTO;
import com.vortexbird.vorteam.exception.ZMessManager;
import com.vortexbird.vorteam.mapper.VtPersonasMapper;
import com.vortexbird.vorteam.repository.IVtPersonasRepository;
import com.vortexbird.vorteam.repository.VtAsignacionRepository;
import com.vortexbird.vorteam.repository.VtPersonasRepository;
import com.vortexbird.vorteam.repository.VtProyCostoRepository;
import com.vortexbird.vorteam.utility.Constantes;
import com.vortexbird.vorteam.utility.PasswordGenerator;
import com.vortexbird.vorteam.utility.SendMailUtility;
import com.vortexbird.vorteam.utility.Utilities;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Scope("singleton")
@Service
public class VtPersonasServiceImpl implements VtPersonasService {
	private static final Logger log = LoggerFactory.getLogger(VtPersonasServiceImpl.class);
	/**
	 * 
	 * Repository injected by Spring that manages VtPersonas entities
	 *
	 */
	@Autowired
	private VtPersonasRepository vtPersonasRepository;
	@Autowired
	private VtPersonasMapper vtPersonasMapper;
	@Autowired
	private Validator validator;
	@Autowired
	private IVtPersonasRepository vtPersonasRepositoryImpl;

	@Autowired
	private VtParametroService vtParametroService;

	/**
	 * Repository injected by Spring that manages VtAsignacion entities
	 *
	 */
	@Autowired
	private VtAsignacionRepository vtAsignacionRepository;

	/**
	 * Repository injected by Spring that manages VtProyCosto entities
	 *
	 */
	@Autowired
	private VtProyCostoRepository vtProyCostoRepository;

	@Autowired
	private VtProyectoService vtProyectoServiceImpl;

	@Autowired
	private VtProyCostoService vtProyCostoServiceImpl;

	public void validateVtPersonas(VtPersonas vtPersonas) throws Exception {
		try {
			Set<ConstraintViolation<VtPersonas>> constraintViolations = validator.validate(vtPersonas);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<VtPersonas> constraintViolation : constraintViolations) {
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
	public List<VtPersonas> getVtPersonas() throws Exception {
		log.debug("finding all VtPersonas instances");

		List<VtPersonas> list = new ArrayList<VtPersonas>();

		try {
			list = vtPersonasRepository.findAll();
		} catch (Exception e) {
			log.error("finding all VtPersonas failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "VtPersonas");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveVtPersonas(VtPersonas entity) throws Exception {
		log.debug("saving VtPersonas instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("VtPersonas");
			}

			validateVtPersonas(entity);

			vtPersonasRepository.save(entity);
			log.debug("save VtPersonas successful");
		} catch (Exception e) {
			log.error("save VtPersonas failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteVtPersonas(VtPersonas entity) throws Exception {
		log.debug("deleting VtPersonas instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("VtPersonas");
		}

		if (entity.getPersId() == null) {
			throw new ZMessManager().new EmptyFieldException("persId");
		}

		List<VtAsignacion> vtAsignacions = null;
		List<VtProyCosto> vtProyCostos = null;

		try {
			vtAsignacions = vtAsignacionRepository.findByProperty("vtPersonas.persId", entity.getPersId());

			if (Utilities.validationsList(vtAsignacions) == true) {
				throw new ZMessManager().new DeletingException("vtAsignacions");
			}

			vtProyCostos = vtProyCostoRepository.findByProperty("vtPersonas.persId", entity.getPersId());

			if (Utilities.validationsList(vtProyCostos) == true) {
				throw new ZMessManager().new DeletingException("vtProyCostos");
			}

			vtPersonasRepository.deleteById(entity.getPersId());
			log.debug("delete VtPersonas successful");
		} catch (Exception e) {
			log.error("delete VtPersonas failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateVtPersonas(VtPersonas entity) throws Exception {
		log.debug("updating VtPersonas instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("VtPersonas");
			}

			validateVtPersonas(entity);

			vtPersonasRepository.update(entity);

			log.debug("update VtPersonas successful");
		} catch (Exception e) {
			log.error("update VtPersonas failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<VtPersonasDTO> getDataVtPersonas() throws Exception {
		try {
			List<VtPersonas> vtPersonas = vtPersonasRepository.findAll();

			List<VtPersonasDTO> vtPersonasDTO = new ArrayList<VtPersonasDTO>();

			for (VtPersonas vtPersonasTmp : vtPersonas) {
				VtPersonasDTO vtPersonasDTO2 = vtPersonasMapper.vtPersonasToVtPersonasDTO(vtPersonasTmp);
				vtPersonasDTO.add(vtPersonasDTO2);
			}

			return vtPersonasDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public VtPersonas getVtPersonas(Long persId) throws Exception {
		log.debug("getting VtPersonas instance");

		VtPersonas entity = null;

		try {
			if (vtPersonasRepository.findById(persId).isPresent()) {
				entity = vtPersonasRepository.findById(persId).get();
			}
		} catch (Exception e) {
			log.error("get VtPersonas failed", e);
			throw new ZMessManager().new FindingException("VtPersonas");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<VtPersonas> findPageVtPersonas(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<VtPersonas> entity = null;

		try {
			entity = vtPersonasRepository.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("VtPersonas Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberVtPersonas() throws Exception {
		Long entity = null;

		try {
			entity = vtPersonasRepository.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("VtPersonas Count");
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
	public List<VtPersonas> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<VtPersonas> list = new ArrayList<VtPersonas>();
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
			list = vtPersonasRepository.findByCriteria(where);
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
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtPersonasService#consultaPersonasProyectos(java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtPersonasDTO> consultaPersonasProyectos(String activo) throws Exception {
		try {
			if (activo == null || activo.trim().equals("")) {
				throw new Exception("No ha llegado el parámetro de Estado");
			}
			return vtPersonasRepositoryImpl.consultaPersonasProyectos(activo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtPersonasService#consultaPersonasProyecto(java.lang.String,
	 *      java.lang.Long)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtPersonasDTO> consultaPersonasProyecto(String activo, Long proyId) throws Exception {
		try {
			if (activo == null || activo.trim().equals("")) {
				throw new Exception("No ha llegado el parámetro de Estado");
			}
			if (proyId == null || proyId.equals(Constantes.SOM_OPCION_SELECCIONAR)) {
				throw new Exception("No ha llegado el parámetro Proyecto");
			}
			return vtPersonasRepositoryImpl.consultaPersonasProyecto(activo, proyId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtPersonasService#consultaPersonas(java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtPersonasDTO> consultaPersonas(String activo) throws Exception {
		try {
			if (activo == null || activo.trim().equals("")) {
				throw new Exception("No ha llegado el parámetro de Estado");
			}
			return vtPersonasRepositoryImpl.consultaPersonas(activo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtPersonasService#guardarPersonasConCostosProyecto(com.vortexbird.vorteam.dto.VtPersonasDTO,
	 *      java.util.List)
	 *
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void guardarPersonasConCostosProyecto(VtPersonasDTO persona, List<VtProyectoDTO> proyectos)
			throws Exception {
		try {
			VtPersonas vtPersonas = vtPersonasMapper.vtPersonasDTOToVtPersonas(persona);
			saveVtPersonas(vtPersonas);

			if (proyectos != null && !proyectos.isEmpty()) {
				for (VtProyectoDTO vtProyectoDTO : proyectos) {
					VtProyecto vtProyecto = vtProyectoServiceImpl.getVtProyecto(vtProyectoDTO.getProyId());
					VtProyCosto proyectoCosto = new VtProyCosto(null, Constantes.ESTADO_ACTIVO, new Date(), null,
							persona.getUsuaCreador(), null, vtProyectoDTO.getValorHoraRecurso(), vtPersonas,
							vtProyecto);
					vtProyCostoServiceImpl.saveVtProyCosto(proyectoCosto);
				}
			}

			String rol = "";

			if (persona.isEsAdministrador()) {
				rol = Constantes.USU_ROL_CODIGO_ADMIN;
			} else {
				rol = Constantes.USU_ROL_CODIGO_DESA;
			}

			RestTemplate restTemplate = new RestTemplate();

			Object[] variableBusquedaParametro = { "nombreCorto", true, Constantes.URL_SENTINEL, "=" };
			List<VtParametro> parametros = vtParametroService.findByCriteria(variableBusquedaParametro, null, null);

			VtParametro parametro = parametros.get(0);

			String url = parametro.getValor().trim() + "/crearUsuario/" + persona.getApellidos() + "/"
					+ Constantes.USU_CODIGO_INTERNO + "/" + persona.getPassword() + "/" + persona.getEmail() + "/"
					+ Constantes.ESTADO_ACTIVO + "/" + Constantes.USU_INTENTOS + "/" + Constantes.USU_COMPANIA + "/"
					+ persona.getEmail() + "/" + persona.getNombre() + "/" + Constantes.USU_SESION + "/" + rol + "/"
					+ Constantes.USU_COMPANIA + "/" + Constantes.USU_COMPANIA;

			ResultadoCrearUsuarioDTO usrSentinel = restTemplate.getForObject(url, ResultadoCrearUsuarioDTO.class);

			if (usrSentinel == null || usrSentinel.getExito() == null) {
				throw new Exception("Lo sentimos ocurrio un error al crear el usuario.");
			}

			if (!usrSentinel.getExito()) {
				throw new Exception(
						"No fue posible crear el usuario el mensaje de error es= " + usrSentinel.getMensaje());
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtPersonasService#actualizarPersonasConCostosProyecto(com.vortexbird.vorteam.dto.VtPersonasDTO,
	 *      java.util.List)
	 *
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void actualizarPersonasConCostosProyecto(VtPersonasDTO persona, List<VtProyectoDTO> proyectos)
			throws Exception {
		try {
			VtPersonas vtPersonas = vtPersonasMapper.vtPersonasDTOToVtPersonas(persona);
			updateVtPersonas(vtPersonas);

			// Buscar ProyCosto por personas y borrarlos
			Object[] variablesBusquedaProyCosto = { "vtPersonas.persId", false, vtPersonas.getPersId(), "=", "activo",
					true, Constantes.ESTADO_ACTIVO, "=" };

			List<VtProyCosto> proyectosCostos = vtProyCostoServiceImpl.findByCriteria(variablesBusquedaProyCosto, null,
					null);
			for (VtProyCosto vtProyCosto : proyectosCostos) {
				vtProyCostoServiceImpl.deleteVtProyCosto(vtProyCosto);
			}

			// Si la persona está asignada a proyectos, adicionarlos a proyectos costos
			if (!proyectos.isEmpty()) {
				for (VtProyectoDTO vtProyectoDTO : proyectos) {
					VtProyecto vtProyecto = vtProyectoServiceImpl.getVtProyecto(vtProyectoDTO.getProyId());
					VtProyCosto proyectoCosto = new VtProyCosto(null, Constantes.ESTADO_ACTIVO, new Date(), null,
							persona.getUsuaCreador(), null, vtProyectoDTO.getValorHoraRecurso(), vtPersonas,
							vtProyecto);
					vtProyCostoServiceImpl.saveVtProyCosto(proyectoCosto);
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public UsuarioDTO autenticar(String userId, String password) throws Exception {
		try {

			Object[] variableBusquedaParametro = { "nombreCorto", true, Constantes.URL_SENTINEL, "=" };
			List<VtParametro> parametros = vtParametroService.findByCriteria(variableBusquedaParametro, null, null);

			VtParametro parametro = parametros.get(0);

			RestTemplate restTemplate = new RestTemplate();

			// http://localhost:8080/sentinel-web/controller/securityServices/autenticar/admin/123456/1

			String url = parametro.getValor().trim() + "/autenticar/" + userId + "/" + password + "/"
					+ Constantes.USU_CODIGO_INTERNO;

			UsuarioDTO usrSentinel = restTemplate.getForObject(url, UsuarioDTO.class);

			return usrSentinel;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtPersonasService#consultaPersonasActividad(java.lang.Long,
	 *      java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtPersonasDTO> consultaPersonasActividad(Long actiId, String activo) throws Exception {
		try {
			if (activo == null || activo.trim().equals("")) {
				throw new Exception("No ha llegado el parámetro de Estado");
			}
			if (actiId == null || actiId.equals(0L)) {
				throw new Exception("No ha llegado el parámetro actividad para buscar");
			}
			return vtPersonasRepositoryImpl.consultaPersonasActividad(actiId, activo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtPersonasService#consultarOpcionesDeUsuarioPorSistemaSucursalYCompania(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<GrupoDTO> consultarOpcionesDeUsuarioPorSistemaSucursalYCompania(String login, String sistema,
			String sucursal, String compania) throws Exception {

		if (sucursal == null || sucursal.equals("")) {
			sucursal = "1";
		}

		Object[] variableBusquedaParametro = { "nombreCorto", true, Constantes.URL_SENTINEL, "=" };
		List<VtParametro> parametros = vtParametroService.findByCriteria(variableBusquedaParametro, null, null);

		VtParametro parametro = parametros.get(0);

		// Instancio el servicio
		RestTemplate templeteSave = new RestTemplate();

		//
		// getOpciones/{login}/{sistema}/{sucursal}/{cia}
		String ruta = parametro.getValor().trim() + "/getOpciones/" + login + "/" + sistema + "/" + sucursal + "/"
				+ compania;

		GrupoDTO[] grupos = templeteSave.getForObject(ruta, GrupoDTO[].class);
		return new ArrayList<>(Arrays.asList(grupos));
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 15, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtPersonasService#consultarPersonaPorCorreo(java.lang.String)
	 *
	 */
	@Override
	public VtPersonas consultarPersonaPorCorreo(String correo) throws Exception {
		VtPersonas persona = null;
		try {
			Object[] variableBusquedaPersonaCorreo = { "email", true, correo.trim().toLowerCase(), "=", "activo", true,
					Constantes.ESTADO_ACTIVO, "=" };
			List<VtPersonas> personas = findByCriteria(variableBusquedaPersonaCorreo, null, null);

			persona = (personas.isEmpty() ? null : personas.get(0));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return persona;
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 21, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtPersonasService#cambiarClave(com.vortexbird.vorteam.domain.VtPersonas)
	 *
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void cambiarClave(VtPersonasDTO personaDTO, UsuarioDTO usuarioDTO) throws Exception {
		try {
			RestTemplate restTemplate = new RestTemplate();

			Object[] variableBusquedaParametro = { "nombreCorto", true, Constantes.URL_SENTINEL, "=" };
			List<VtParametro> parametros = vtParametroService.findByCriteria(variableBusquedaParametro, null, null);

			VtParametro parametro = parametros.get(0);

			String url = parametro.getValor().trim() + "/actualizarUsuario/" + personaDTO.getApellidos() + "/"
					+ usuarioDTO.getUsu_codigo() + "/" + Constantes.USU_CODIGO_INTERNO + "/" + personaDTO.getPassword()
					+ "/" + personaDTO.getEmail() + "/" + Constantes.ESTADO_ACTIVO + "/" + Constantes.USU_INTENTOS + "/"
					+ Constantes.USU_COMPANIA + "/" + personaDTO.getEmail().trim() + "/" + personaDTO.getNombre()
					+ "/0";

			ResultadoCrearUsuarioDTO usrSentinel = restTemplate.getForObject(url, ResultadoCrearUsuarioDTO.class);

			if (usrSentinel == null || usrSentinel.getExito() == null) {
				throw new Exception("Lo sentimos ocurrio un error al actualizar el usuario.");
			}

			if (!usrSentinel.getExito()) {
				throw new Exception(
						"No fue posible actualizar el usuario el mensaje de error es= " + usrSentinel.getMensaje());
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version oct. 01, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtPersonasService#restaurarClave(java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void restaurarClave(String login) throws Exception {
		try {
			VtPersonas persona = null;
			boolean esCorreo = login.contains(Constantes.ARROBA);
			if (esCorreo) {
				persona = consultarPersonaPorCorreo(login);
			} else {
				persona = consultarPersonaPorCorreo(login.trim() + "@vortexbird.com");
			}
			if (persona == null) {
				throw new Exception("No se ha encontrado el usuario " + login);
			}

			RestTemplate restTemplate = new RestTemplate();

			Object[] variableBusquedaParametro = { "nombreCorto", true, Constantes.URL_SENTINEL, "=" };
			List<VtParametro> parametros = vtParametroService.findByCriteria(variableBusquedaParametro, null, null);

			VtParametro parametro = parametros.get(0);

			String url = parametro.getValor().trim() + "/consultarUsuarioPorLogin/"
					+ persona.getEmail().trim().toUpperCase() + "/" + Constantes.USU_CODIGO_INTERNO;

			UsuarioDTO usuarioDTO = restTemplate.getForObject(url, UsuarioDTO.class);
			if (usuarioDTO == null) {
				throw new Exception("No se ha encontrado el usuario " + login + " en el Sistema de Seguridad");
			}
			String nuevaClave = PasswordGenerator.getPinNumber();

			VtPersonasDTO personaDTO = new VtPersonasDTO();
			personaDTO.setApellidos(persona.getApellidos().trim());
			personaDTO.setPassword(nuevaClave);
			personaDTO.setEmail(persona.getEmail().trim());
			personaDTO.setNombre(persona.getNombre().trim());

			cambiarClave(personaDTO, usuarioDTO);

			// Se envía un correo de notificación al usuario
			String[] to = { persona.getEmail().trim().toLowerCase() };
			String subject = "[Vorteam] - Restaurar Contraseña";

			String mensaje = "Hola " + persona.getNombre().trim().toUpperCase()
					+ " tu nueva contraseña para acceder a Vorteam es: " + nuevaClave + " recuerda cambiar la clave.";
			String mailText = "<div style=\"font-size: 12pt;\">" + mensaje + "</div>";

			File[] attachments = null;

			VtParametro usuario = vtParametroService.obtenerParametroPorCodigo(Constantes.US_NOTIFICACION,
					Constantes.ESTADO_ACTIVO);
			if (usuario == null || usuario.getValor() == null || usuario.getValor().trim().equals("")) {
				throw new Exception("No se ha encontrado el parámetro " + Constantes.US_NOTIFICACION);
			}

			VtParametro psw = vtParametroService.obtenerParametroPorCodigo(Constantes.PS_NOTIFICACION,
					Constantes.ESTADO_ACTIVO);
			if (psw == null || psw.getValor() == null || psw.getValor().trim().equals("")) {
				throw new Exception("No se ha encontrado el parámetro " + Constantes.PS_NOTIFICACION);
			}

			String username = usuario.getValor().trim().toLowerCase();
			String password = psw.getValor().trim();

			Properties properties = new Properties();
			properties.put("type", "javax.mail.Session");
			properties.put("mail.transport.protocol", "smtp");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.user", username);
			properties.put("password", password);
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			SendMailUtility.sendMail(to, subject, mailText, attachments, null, properties, username, password);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
}
