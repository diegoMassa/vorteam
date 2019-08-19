package com.vortexbird.sentinel.modelo.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vortexbird.sentinel.modelo.dto.SegRolDTO;
import com.vortexbird.sentinel.modelo.dto.SegUsuarioDTO;
import com.vortexbird.sentinel.modelo.dto.UsuarioDTO;
import com.vortexbird.sentinel.utilities.Constantes;
import com.vortexbird.sentinel.utilities.EmailValidator;
import com.vortexbird.sentinel.utilities.Fechas;
import com.vortexbird.sentinel.utilities.IVerificacionEmailUsuarioLogic;
import com.vortexbird.sentinel.utilities.PasswordGenerator;
import com.vortexbird.sentinel.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.vortexbird.sentinel.dataaccess.dao.ISegAuditoriaDAO;
import com.vortexbird.sentinel.dataaccess.dao.ISegCambioPassDAO;
import com.vortexbird.sentinel.dataaccess.dao.ISegCompaniaDAO;
import com.vortexbird.sentinel.dataaccess.dao.ISegGrupoOpcionDAO;
import com.vortexbird.sentinel.dataaccess.dao.ISegHistorialConstrasenaDAO;
import com.vortexbird.sentinel.dataaccess.dao.ISegOpcionDAO;
import com.vortexbird.sentinel.dataaccess.dao.ISegParametroDAO;
import com.vortexbird.sentinel.dataaccess.dao.ISegPermisoDAO;
import com.vortexbird.sentinel.dataaccess.dao.ISegRolDAO;
import com.vortexbird.sentinel.dataaccess.dao.ISegRolUsuarioDAO;
import com.vortexbird.sentinel.dataaccess.dao.ISegSistemaCiaDAO;
import com.vortexbird.sentinel.dataaccess.dao.ISegSistemaDAO;
import com.vortexbird.sentinel.dataaccess.dao.ISegSucursalDAO;
import com.vortexbird.sentinel.dataaccess.dao.ISegUsuarioDAO;
import com.vortexbird.sentinel.exceptions.ZMessManager;
import com.vortexbird.sentinel.modelo.SegAuditoria;
import com.vortexbird.sentinel.modelo.SegCambioPass;
import com.vortexbird.sentinel.modelo.SegCompania;
import com.vortexbird.sentinel.modelo.SegGrupoOpcion;
import com.vortexbird.sentinel.modelo.SegHistorialConstrasena;
import com.vortexbird.sentinel.modelo.SegOpcion;
import com.vortexbird.sentinel.modelo.SegParametro;
import com.vortexbird.sentinel.modelo.SegPermiso;
import com.vortexbird.sentinel.modelo.SegRol;
import com.vortexbird.sentinel.modelo.SegRolUsuario;
import com.vortexbird.sentinel.modelo.SegSistema;
import com.vortexbird.sentinel.modelo.SegSistemaCia;
import com.vortexbird.sentinel.modelo.SegSucursal;
import com.vortexbird.sentinel.modelo.SegUsuario;
import com.vortexbird.sentinel.modelo.VinCamposParametrizables;
import com.vortexbird.sentinel.modelo.dto.SellPersonaDTO;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("SegUsuarioLogic")
public class SegUsuarioLogic implements ISegUsuarioLogic {
	private static final Logger log = LoggerFactory.getLogger(SegUsuarioLogic.class);

	/**
	 * DAO injected by Spring that manages SegAuditoria entities
	 *
	 */
	@Autowired
	private ISegAuditoriaDAO segAuditoriaDAO;

	/**
	 * DAO injected by Spring that manages SegCambioPass entities
	 *
	 */
	@Autowired
	private ISegCambioPassDAO segCambioPassDAO;

	/**
	 * DAO injected by Spring that manages SegCompania entities
	 *
	 */
	@Autowired
	private ISegCompaniaDAO segCompaniaDAO;

	/**
	 * DAO injected by Spring that manages SegGrupoOpcion entities
	 *
	 */
	@Autowired
	private ISegGrupoOpcionDAO segGrupoOpcionDAO;

	/**
	 * DAO injected by Spring that manages SegHistorialConstrasena entities
	 *
	 */
	@Autowired
	private ISegHistorialConstrasenaDAO segHistorialConstrasenaDAO;

	/**
	 * DAO injected by Spring that manages SegOpcion entities
	 *
	 */
	@Autowired
	private ISegOpcionDAO segOpcionDAO;

	/**
	 * DAO injected by Spring that manages SegParametro entities
	 *
	 */
	@Autowired
	private ISegParametroDAO segParametroDAO;

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
	 * DAO injected by Spring that manages SegSistema entities
	 *
	 */
	@Autowired
	private ISegSistemaDAO segSistemaDAO;

	/**
	 * DAO injected by Spring that manages SegSucursal entities
	 *
	 */
	@Autowired
	private ISegSucursalDAO segSucursalDAO;

	/**
	 * DAO injected by Spring that manages SegUsuario entities
	 *
	 */
	@Autowired
	private ISegUsuarioDAO segUsuarioDAO;

	/**
	 * Logic injected by Spring that manages SegUsuario entities
	 *
	 */
	@Autowired
	private ISegUsuarioLogic logicSegUsuario1;

	@Autowired
	private ISegCompaniaLogic segCompaniaLogic;

	@Autowired
	private ISegRolUsuarioLogic segRolUsuarioLogic;

	@Autowired
	private ISegPermisoLogic segPermisoLogic;

	@Autowired
	private ISegRolLogic segRolLogic;

	@Autowired
	private ISegSistemaCiaLogic segSistemaCiaLogic;

	@Autowired
	private ISegUsuarioLogic segUsuarioLogic;
	
	@Autowired
	   private IVerificacionEmailUsuarioLogic verificacionEmailUsuarioLogic;
	
	@Autowired
	private IVinCamposParametrizablesLogic vinCamposParametrizablesLogic;

	@Transactional(readOnly = true)
	public List<SegUsuario> getSegUsuario() throws Exception {
		log.debug("finding all SegUsuario instances");

		List<SegUsuario> list = new ArrayList<SegUsuario>();

		try {
			list = segUsuarioDAO.findAll();
		} catch (Exception e) {
			log.error("finding all SegUsuario failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "SegUsuario");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveSegUsuario(SegUsuario entity) throws Exception {
		log.debug("saving SegUsuario instance");

		try {
			if (entity.getSegUsuario() == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			if (entity.getUsuApellidos() == null) {
				throw new ZMessManager().new EmptyFieldException("usuApellidos");
			}

			if ((entity.getUsuApellidos() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getUsuApellidos(), 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuApellidos");
			}

			if (entity.getUsuCodigoInterno() == null) {
				throw new ZMessManager().new EmptyFieldException("usuCodigoInterno");
			}

			if ((entity.getUsuCodigoInterno() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getUsuCodigoInterno(), 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuCodigoInterno");
			}

			if ((entity.getUsuCompaniaCiaCodigo() != null) && (Utilities
					.checkNumberAndCheckWithPrecisionAndScale("" + entity.getUsuCompaniaCiaCodigo(), 12, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuCompaniaCiaCodigo");
			}

			if (entity.getUsuConstrasena() == null) {
				throw new ZMessManager().new EmptyFieldException("usuConstrasena");
			}

			if ((entity.getUsuConstrasena() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getUsuConstrasena(), 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuConstrasena");
			}

			if ((entity.getUsuCorreo() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getUsuCorreo(), 200) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuCorreo");
			}

			if (entity.getUsuEstadoRegistro() == null) {
				throw new ZMessManager().new EmptyFieldException("usuEstadoRegistro");
			}

			if ((entity.getUsuEstadoRegistro() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getUsuEstadoRegistro(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuEstadoRegistro");
			}

			if ((entity.getUsuIntentosFallidos() != null) && (Utilities
					.checkNumberAndCheckWithPrecisionAndScale("" + entity.getUsuIntentosFallidos(), 18, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuIntentosFallidos");
			}

			if (entity.getUsuLogin() == null) {
				throw new ZMessManager().new EmptyFieldException("usuLogin");
			}

			if ((entity.getUsuLogin() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getUsuLogin(), 30) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuLogin");
			}

			if (entity.getUsuNombres() == null) {
				throw new ZMessManager().new EmptyFieldException("usuNombres");
			}

			if ((entity.getUsuNombres() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getUsuNombres(), 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuNombres");
			}

			if (entity.getSegUsuario().getUsuCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException("usuCodigo_SegUsuario");
			}

			segUsuarioDAO.save(entity);

			log.debug("save SegUsuario successful");
		} catch (Exception e) {
			log.error("save SegUsuario failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteSegUsuario(SegUsuario entity) throws Exception {
		log.debug("deleting SegUsuario instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("SegUsuario");
		}

		if (entity.getUsuCodigo() == null) {
			throw new ZMessManager().new EmptyFieldException("usuCodigo");
		}

		List<SegAuditoria> segAuditorias = null;
		List<SegCambioPass> segCambioPasses = null;
		List<SegCompania> segCompanias = null;
		List<SegGrupoOpcion> segGrupoOpcions = null;
		List<SegHistorialConstrasena> segHistorialConstrasenas = null;
		List<SegOpcion> segOpcions = null;
		List<SegParametro> segParametros = null;
		List<SegPermiso> segPermisosForModUsuCodigo = null;
		List<SegPermiso> segPermisosForUsuCodigo = null;
		List<SegRolUsuario> segRolUsuariosForModUsuCodigo = null;
		List<SegRolUsuario> segRolUsuariosForSegUsuarioUsuCodigo = null;
		List<SegRol> segRols = null;
		List<SegSistemaCia> segSistemaCias = null;
		List<SegSistema> segSistemas = null;
		List<SegSucursal> segSucursals = null;
		List<SegUsuario> segUsuarios = null;

		try {
			segAuditorias = segAuditoriaDAO.findByProperty("segUsuario.usuCodigo", entity.getUsuCodigo());

			if (Utilities.validationsList(segAuditorias) == true) {
				throw new ZMessManager().new DeletingException("segAuditorias");
			}

			segCambioPasses = segCambioPassDAO.findByProperty("segUsuario.usuCodigo", entity.getUsuCodigo());

			if (Utilities.validationsList(segCambioPasses) == true) {
				throw new ZMessManager().new DeletingException("segCambioPasses");
			}

			segCompanias = segCompaniaDAO.findByProperty("segUsuario.usuCodigo", entity.getUsuCodigo());

			if (Utilities.validationsList(segCompanias) == true) {
				throw new ZMessManager().new DeletingException("segCompanias");
			}

			segGrupoOpcions = segGrupoOpcionDAO.findByProperty("segUsuario.usuCodigo", entity.getUsuCodigo());

			if (Utilities.validationsList(segGrupoOpcions) == true) {
				throw new ZMessManager().new DeletingException("segGrupoOpcions");
			}

			segHistorialConstrasenas = segHistorialConstrasenaDAO.findByProperty("segUsuario.usuCodigo",
					entity.getUsuCodigo());

			if (Utilities.validationsList(segHistorialConstrasenas) == true) {
				throw new ZMessManager().new DeletingException("segHistorialConstrasenas");
			}

			segOpcions = segOpcionDAO.findByProperty("segUsuario.usuCodigo", entity.getUsuCodigo());

			if (Utilities.validationsList(segOpcions) == true) {
				throw new ZMessManager().new DeletingException("segOpcions");
			}

			segParametros = segParametroDAO.findByProperty("segUsuario.usuCodigo", entity.getUsuCodigo());

			if (Utilities.validationsList(segParametros) == true) {
				throw new ZMessManager().new DeletingException("segParametros");
			}

			segPermisosForModUsuCodigo = segPermisoDAO.findByProperty("segUsuario.usuCodigo", entity.getUsuCodigo());

			if (Utilities.validationsList(segPermisosForModUsuCodigo) == true) {
				throw new ZMessManager().new DeletingException("segPermisosForModUsuCodigo");
			}

			segPermisosForUsuCodigo = segPermisoDAO.findByProperty("segUsuario.usuCodigo", entity.getUsuCodigo());

			if (Utilities.validationsList(segPermisosForUsuCodigo) == true) {
				throw new ZMessManager().new DeletingException("segPermisosForUsuCodigo");
			}

			segRolUsuariosForModUsuCodigo = segRolUsuarioDAO.findByProperty("segUsuario.usuCodigo",
					entity.getUsuCodigo());

			if (Utilities.validationsList(segRolUsuariosForModUsuCodigo) == true) {
				throw new ZMessManager().new DeletingException("segRolUsuariosForModUsuCodigo");
			}

			segRolUsuariosForSegUsuarioUsuCodigo = segRolUsuarioDAO.findByProperty("segUsuario.usuCodigo",
					entity.getUsuCodigo());

			if (Utilities.validationsList(segRolUsuariosForSegUsuarioUsuCodigo) == true) {
				throw new ZMessManager().new DeletingException("segRolUsuariosForSegUsuarioUsuCodigo");
			}

			segRols = segRolDAO.findByProperty("segUsuario.usuCodigo", entity.getUsuCodigo());

			if (Utilities.validationsList(segRols) == true) {
				throw new ZMessManager().new DeletingException("segRols");
			}

			segSistemaCias = segSistemaCiaDAO.findByProperty("segUsuario.usuCodigo", entity.getUsuCodigo());

			if (Utilities.validationsList(segSistemaCias) == true) {
				throw new ZMessManager().new DeletingException("segSistemaCias");
			}

			segSistemas = segSistemaDAO.findByProperty("segUsuario.usuCodigo", entity.getUsuCodigo());

			if (Utilities.validationsList(segSistemas) == true) {
				throw new ZMessManager().new DeletingException("segSistemas");
			}

			segSucursals = segSucursalDAO.findByProperty("segUsuario.usuCodigo", entity.getUsuCodigo());

			if (Utilities.validationsList(segSucursals) == true) {
				throw new ZMessManager().new DeletingException("segSucursals");
			}

			segUsuarios = segUsuarioDAO.findByProperty("segUsuario.usuCodigo", entity.getUsuCodigo());

			if (Utilities.validationsList(segUsuarios) == true) {
				throw new ZMessManager().new DeletingException("segUsuarios");
			}

			segUsuarioDAO.delete(entity);

			log.debug("delete SegUsuario successful");
		} catch (Exception e) {
			log.error("delete SegUsuario failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateSegUsuario(SegUsuario entity) throws Exception {
		log.debug("updating SegUsuario instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("SegUsuario");
			}

			if (entity.getUsuApellidos() == null) {
				throw new ZMessManager().new EmptyFieldException("usuApellidos");
			}

			if ((entity.getUsuApellidos() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getUsuApellidos(), 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuApellidos");
			}

			if (entity.getUsuCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException("usuCodigo");
			}

			if (entity.getUsuCodigoInterno() == null) {
				throw new ZMessManager().new EmptyFieldException("usuCodigoInterno");
			}

			if ((entity.getUsuCodigoInterno() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getUsuCodigoInterno(), 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuCodigoInterno");
			}

			if ((entity.getUsuCompaniaCiaCodigo() != null) && (Utilities
					.checkNumberAndCheckWithPrecisionAndScale("" + entity.getUsuCompaniaCiaCodigo(), 12, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuCompaniaCiaCodigo");
			}

			if (entity.getUsuConstrasena() == null) {
				throw new ZMessManager().new EmptyFieldException("usuConstrasena");
			}

			if ((entity.getUsuConstrasena() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getUsuConstrasena(), 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuConstrasena");
			}

			if ((entity.getUsuCorreo() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getUsuCorreo(), 200) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuCorreo");
			}

			if (entity.getUsuEstadoRegistro() == null) {
				throw new ZMessManager().new EmptyFieldException("usuEstadoRegistro");
			}

			if ((entity.getUsuEstadoRegistro() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getUsuEstadoRegistro(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuEstadoRegistro");
			}

			if ((entity.getUsuIntentosFallidos() != null) && (Utilities
					.checkNumberAndCheckWithPrecisionAndScale("" + entity.getUsuIntentosFallidos(), 18, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuIntentosFallidos");
			}

			if (entity.getUsuLogin() == null) {
				throw new ZMessManager().new EmptyFieldException("usuLogin");
			}

			if ((entity.getUsuLogin() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getUsuLogin(), 30) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuLogin");
			}

			if (entity.getUsuNombres() == null) {
				throw new ZMessManager().new EmptyFieldException("usuNombres");
			}

			if ((entity.getUsuNombres() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getUsuNombres(), 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuNombres");
			}

			// if (entity.getSegUsuario().getUsuCodigo() == null) {
			// throw new ZMessManager().new EmptyFieldException(
			// "usuCodigo_SegUsuario");
			// }

			segUsuarioDAO.update(entity);

			log.debug("update SegUsuario successful");
		} catch (Exception e) {
			log.error("update SegUsuario failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<SegUsuarioDTO> getDataSegUsuario() throws Exception {
		try {
			List<SegUsuario> segUsuario = segUsuarioDAO.findAll();

			List<SegUsuarioDTO> segUsuarioDTO = new ArrayList<SegUsuarioDTO>();

			for (SegUsuario segUsuarioTmp : segUsuario) {
				SegUsuarioDTO segUsuarioDTO2 = new SegUsuarioDTO();

				segUsuarioDTO2.setUsuCodigo(segUsuarioTmp.getUsuCodigo());
				segUsuarioDTO2.setUsuApellidos(
						(segUsuarioTmp.getUsuApellidos() != null) ? segUsuarioTmp.getUsuApellidos() : null);
				segUsuarioDTO2.setUsuCodigoInterno(
						(segUsuarioTmp.getUsuCodigoInterno() != null) ? segUsuarioTmp.getUsuCodigoInterno() : null);
				segUsuarioDTO2.setUsuCompaniaCiaCodigo((segUsuarioTmp.getUsuCompaniaCiaCodigo() != null)
						? segUsuarioTmp.getUsuCompaniaCiaCodigo() : null);
				segUsuarioDTO2.setUsuConstrasena(
						(segUsuarioTmp.getUsuConstrasena() != null) ? segUsuarioTmp.getUsuConstrasena() : null);
				segUsuarioDTO2
						.setUsuCorreo((segUsuarioTmp.getUsuCorreo() != null) ? segUsuarioTmp.getUsuCorreo() : null);
				segUsuarioDTO2.setUsuEstadoRegistro(
						(segUsuarioTmp.getUsuEstadoRegistro() != null) ? segUsuarioTmp.getUsuEstadoRegistro() : null);
				segUsuarioDTO2.setUsuIntentosFallidos((segUsuarioTmp.getUsuIntentosFallidos() != null)
						? segUsuarioTmp.getUsuIntentosFallidos() : null);
				segUsuarioDTO2.setUsuLogin((segUsuarioTmp.getUsuLogin() != null) ? segUsuarioTmp.getUsuLogin() : null);
				segUsuarioDTO2
						.setUsuNombres((segUsuarioTmp.getUsuNombres() != null) ? segUsuarioTmp.getUsuNombres() : null);
				segUsuarioDTO2.setUsuUltmimaModificacionPass(segUsuarioTmp.getUsuUltmimaModificacionPass());
				segUsuarioDTO2.setUsuCodigo_SegUsuario(
						(segUsuarioTmp.getSegUsuario() != null) ? segUsuarioTmp.getSegUsuario().getUsuCodigo() : null);

				segUsuarioDTO2.setUsuEstadoRegistroNombre(
						segUsuarioTmp.getUsuEstadoRegistro().equals("A") ? "Activo" : "Inactivo");

				segUsuarioDTO.add(segUsuarioDTO2);
			}

			return segUsuarioDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public SegUsuario getSegUsuario(Long usuCodigo) throws Exception {
		log.debug("getting SegUsuario instance");

		SegUsuario entity = null;

		try {
			entity = segUsuarioDAO.findById(usuCodigo);
		} catch (Exception e) {
			log.error("get SegUsuario failed", e);
			throw new ZMessManager().new FindingException("SegUsuario");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<SegUsuario> findPageSegUsuario(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<SegUsuario> entity = null;

		try {
			entity = segUsuarioDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegUsuario Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberSegUsuario() throws Exception {
		Long entity = null;

		try {
			entity = segUsuarioDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("SegUsuario Count");
		} finally {
		}

		return entity;
	}

	/**
	 *
	 * @param varibles
	 *            este arreglo debera tener:
	 *
	 *            [0] = String variable = (String) varibles[i]; representa como
	 *            se llama la variable en el pojo
	 *
	 *            [1] = Boolean booVariable = (Boolean) varibles[i + 1];
	 *            representa si el valor necesita o no ''(comillas simples)usado
	 *            para campos de tipo string
	 *
	 *            [2] = Object value = varibles[i + 2]; representa el valor que
	 *            se va a buscar en la BD
	 *
	 *            [3] = String comparator = (String) varibles[i + 3]; representa
	 *            que tipo de busqueda voy a hacer.., ejemplo: where
	 *            nombre=william o where nombre<>william, en este campo iria el
	 *            tipo de comparador que quiero si es = o <>
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
	 *            [0] = String variable = (String) varibles[j]; la variable ne
	 *            la BD que va a ser buscada en un rango
	 *
	 *            [1] = Object value = varibles[j + 1]; valor 1 para buscar en
	 *            un rango
	 *
	 *            [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en
	 *            un rango ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria
	 *            value2
	 *
	 *            [3] = String comparator1 = (String) varibles[j + 3];
	 *            comparador 1 ejemplo: a comparator1 1 and a < 5
	 *
	 *            [4] = String comparator2 = (String) varibles[j + 4];
	 *            comparador 2 ejemplo: a comparador1>1 and a comparador2<5 (el
	 *            original: a > 1 and a < 5) *
	 * @param variablesBetweenDates(en
	 *            este caso solo para mysql) [0] = String variable = (String)
	 *            varibles[k]; el nombre de la variable que hace referencia a
	 *            una fecha
	 *
	 *            [1] = Object object1 = varibles[k + 2]; fecha 1 a
	 *            comparar(deben ser dates)
	 *
	 *            [2] = Object object2 = varibles[k + 3]; fecha 2 a
	 *            comparar(deben ser dates)
	 *
	 *            esto hace un between entre las dos fechas.
	 *
	 * @return lista con los objetos que se necesiten
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<SegUsuario> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<SegUsuario> list = new ArrayList<SegUsuario>();
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
							? ("(model." + variable + " between \'" + value + "\' and \'" + value2 + "\')")
							: (tempWhere + " AND (model." + variable + " between \'" + value + "\' and \'" + value2
									+ "\')");
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
			list = segUsuarioDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SegUsuario guardarUsuarioConRoles(String usuApellidos, String usuCodigoInterno, String usuConstrasena,
			String usuCorreo, String usuEstadoRegistro, Long usuIntentosFallidos, Long ciaCodigo_SegCompania,
			String usuLogin, String usuNombres, Date usuUltmimaModificacionPass, Long usuSession,
			List<String> rolesAsignados, String compania, String sistema) throws Exception {

		SegUsuario entity = null;

		try {
			if (usuApellidos == null) {
				throw new ZMessManager().new EmptyFieldException("usuApellidos");
			}

			if ((usuApellidos != null) && (Utilities.checkWordAndCheckWithlength(usuApellidos, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuApellidos");
			}

			if (usuCodigoInterno == null) {
				throw new ZMessManager().new EmptyFieldException("usuCodigoInterno");
			}

			if ((usuCodigoInterno != null) && (Utilities.checkWordAndCheckWithlength(usuCodigoInterno, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuCodigoInterno");
			}

			if (usuConstrasena == null) {
				throw new ZMessManager().new EmptyFieldException("usuConstrasena");
			}

			if ((usuConstrasena != null) && (Utilities.checkWordAndCheckWithlength(usuConstrasena, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuConstrasena");
			}

			if (usuEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException("usuEstadoRegistro");
			}

			if ((usuEstadoRegistro != null) && (Utilities.checkWordAndCheckWithlength(usuEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuEstadoRegistro");
			}

			if (usuLogin == null) {
				throw new ZMessManager().new EmptyFieldException("usuLogin");
			}

			if ((usuLogin != null) && (Utilities.checkWordAndCheckWithlength(usuLogin, 30) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuLogin");
			}

			if (usuNombres == null) {
				throw new ZMessManager().new EmptyFieldException("usuNombres");
			}

			if ((usuNombres != null) && (Utilities.checkWordAndCheckWithlength(usuNombres, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuNombres");
			}

			if (usuSession == null) {
				throw new ZMessManager().new EmptyFieldException("usuCodigo_SegUsuario");
			}

			if ((usuSession != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + usuSession, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuCodigo_SegUsuario");
			}

			if (sistema == null) {
				throw new ZMessManager().new EmptyFieldException("sistema");
			}

			if (compania == null) {
				throw new ZMessManager().new EmptyFieldException("compania");
			}

			// if (ciaCodigo_SegCompania == null) {
			// throw new ZMessManager().new EmptyFieldException(
			// "ciaCodigo_SegCompania");
			// }
			//
			// if ((ciaCodigo_SegCompania != null) &&
			// (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
			// ciaCodigo_SegCompania, 5, 0) == false)) {
			// throw new ZMessManager().new NotValidFormatException(
			// "ciaCodigo_SegCompania");
			// }

			// SegCompania segCompaniaClass =
			// segCompaniaLogic.getSegCompania(ciaCodigo_SegCompania);

			// if (segCompaniaClass == null) {
			// throw new ZMessManager().new ForeignException("segCompania");
			// }

			SegUsuario segUsuarioClass = logicSegUsuario1.getSegUsuario(usuSession);

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}
			// TODO: Validado que no se crean dos usuarios con el mismo
			// correo electronico

			SegUsuario segUsuario = consultarUsuarioPorLogin(usuLogin);

			if (segUsuario != null) {
				throw new ZMessManager("Ya existe un usuario con el login " + usuLogin);
			}

			// Se consulta la compania
			SegCompania segCompania = segCompaniaLogic.getSegCompania(Long.parseLong(compania));
			if (segCompania == null) {
				throw new Exception("No existe la companía " + compania);
			}

			entity = new SegUsuario();
			entity.setUsuApellidos(usuApellidos);
			entity.setUsuCodigoInterno(usuCodigoInterno);
			entity.setUsuConstrasena(usuConstrasena);
			entity.setUsuCorreo(usuCorreo);
			entity.setUsuEstadoRegistro(usuEstadoRegistro);
			entity.setUsuLogin(usuLogin);
			entity.setUsuNombres(usuNombres);
			entity.setUsuIntentosFallidos(0L);
			entity.setUsuCompaniaCiaCodigo(segCompania.getCiaCodigo());
			entity.setUsuUltmimaModificacionPass(new Date());
			entity.setSegUsuario(segUsuarioClass);

			segUsuarioDAO.save(entity);

			// Se asignan los roles al usuario
			for (int i = 0; i < rolesAsignados.size(); i++) {
				SegRolUsuario segRolUsuario = new SegRolUsuario();

				// Se consulta el rol
				Long rolCodigo = Long.parseLong(rolesAsignados.get(i));
				SegRol segRol = segRolLogic.getSegRol(rolCodigo);

				segRolUsuario.setRluEstadoRegistro("A");
				segRolUsuario.setSegRol(segRol);
				segRolUsuario.setSegUsuarioBySegUsuarioUsuCodigo(entity);
				segRolUsuario.setSegUsuarioByModUsuCodigo(segUsuarioClass);

				// segRolUsuarioLogic.saveSegRolUsuario("1",
				// Long.parseLong(rolesAsignados.get(i)), entity.getUsuCodigo(),
				// entity.getUsuCodigo());
				segRolUsuarioLogic.saveSegRolUsuario(segRolUsuario);
			}

			// //Se asigna el permiso al usuario a nivel de SISTEMA-COMPANIA
			// if (usuSession==0) {
			// SegPermiso segPermiso = new SegPermiso();
			// segPermiso.setPerEstadoRegistro("A");
			// segPermiso.setSegUsuarioByUsuCodigo(entity);
			// segPermiso.setSegUsuarioByModUsuCodigo(segUsuarioClass);
			//
			// segPermisoLogic.saveSegPermiso(segPermiso);
			// }else {
			//

			List<SegSistemaCia> sistemasCompanias = segSistemaCiaLogic.findByCriteria(new Object[] {
					"segCompania.ciaCodigo", false, compania, "=", "segSistema.sisCodigo", false, sistema, "=" }, null,
					null);

			if (sistemasCompanias == null || sistemasCompanias.size() == 0) {
				throw new Exception("No se encontro la relación sistema companía " + sistema + " - " + compania);
			}

			SegSistemaCia segSistemaCia = sistemasCompanias.get(0);

			SegPermiso segPermiso = new SegPermiso();
			segPermiso.setPerEstadoRegistro("A");
			segPermiso.setSegUsuarioByUsuCodigo(entity);
			segPermiso.setSegUsuarioByModUsuCodigo(segUsuarioClass);
			segPermiso.setSegSistemaCia(segSistemaCia);

			segPermisoLogic.saveSegPermiso(segPermiso);
			// }

			return entity;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public SegUsuario consultarUsuarioPorLogin(String login) throws Exception {
		try {

			Object[] variablesConsulta = { "usuLogin", true, login, "=", "usuEstadoRegistro", true, "A", "=" };

			List<SegUsuario> usuarios = findByCriteria(variablesConsulta, null, null);
			if (usuarios != null && usuarios.size() > 0) {
				return usuarios.get(0);
			} else {
				return null;
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioDTO consultarUsuarioPorLogin(String login, String dominio, String codigoSistema) {

		UsuarioDTO usuDto = new UsuarioDTO();
		try {

			SegUsuario u = null;
			List<SegRolUsuario> usuarios = null;
			try {
				// usuarios =
				// BusinessDelegatorView.findByCriteriaInSegUsuario(new
				// Object[]{"usuLogin",true,login.toUpperCase().trim(),"="},null,
				// null);

				usuarios = segRolUsuarioLogic.findByCriteria(new Object[] { "segUsuarioBySegUsuarioUsuCodigo.usuLogin",
						true, login.toUpperCase().trim(), "=", "segUsuarioBySegUsuarioUsuCodigo.usuCodigoInterno", true,
						dominio.trim(), "=", "segRol.segSistema.sisCodigo", false, codigoSistema, "=" }, null, null);

			} catch (Exception e) {
				log.error("Error consultando el consultarUsuarioPorLogin. Login = " + login + ". Dominio = " + dominio
						+ ". codigoSistema = " + codigoSistema, e);
			}

			boolean existeUsuarioEnBD = usuarios != null && usuarios.size() > 0;

			if (existeUsuarioEnBD) {
				u = usuarios.get(0).getSegUsuarioBySegUsuarioUsuCodigo();
				SegRol rol = usuarios.get(0).getSegRol();

				usuDto.setUsu_activo(u.getUsuEstadoRegistro());
				usuDto.setUsu_apellidos(u.getUsuApellidos());
				usuDto.setUsu_codigo(u.getUsuCodigo().longValue() + "");
				usuDto.setUsu_codigo_interno(u.getUsuCodigoInterno());
				usuDto.setUsu_login(u.getUsuLogin());
				usuDto.setUsu_nombres(u.getUsuNombres());
				usuDto.setUsu_correo(u.getUsuCorreo());
				usuDto.setUsu_intentos_fallidos(
						u.getUsuIntentosFallidos() != null ? u.getUsuIntentosFallidos().toString() : "0");
				usuDto.setUsu_ultmima_modificacion_pass(
						Fechas.dateToStr(u.getUsuUltmimaModificacionPass(), "yyyy-MM-dd"));
				usuDto.setNombre_rol(rol.getRolNombre());
				usuDto.setContrasenaMD5(u.getUsuConstrasena());
			} else {
				usuDto = null;
			}

			return usuDto;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return usuDto;
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<SegUsuario> consultarUsuariosPorSistemaCompania(Long sisCodigfo, Long ciaCodigo) throws Exception {

		try {

			if (sisCodigfo == null) {
				throw new Exception("El usuario debe ser administrador de algun sistema");
			}

			if (ciaCodigo == null) {
				throw new Exception("El usuario debe ser administrador de alguna compaía");
			}

			// Se consulta el par sistema-cia
			List<SegSistemaCia> sistemasCompanias = segSistemaCiaLogic.findByCriteria(new Object[] {
					"segCompania.ciaCodigo", false, ciaCodigo, "=", "segSistema.sisCodigo", false, sisCodigfo, "=" },
					null, null);

			if (sistemasCompanias == null || sistemasCompanias.size() == 0) {
				throw new Exception("No existe el Sistema Companía " + sisCodigfo + ciaCodigo);
			}

			Long siscCodigo = sistemasCompanias.get(0).getSicCodigo();

			// Se consultan los usuario que tienen permisos para ese sistema cia
			List<SegPermiso> permisos = segPermisoLogic.consultarPermisosDeUsuariosPorSistemaCia(siscCodigo);

			// Se saca la lista de usuarios que se encontraron con permisos
			List<SegUsuario> usuarios = new ArrayList<SegUsuario>();
			for (SegPermiso segPermiso : permisos) {
				usuarios.add(segPermiso.getSegUsuarioByUsuCodigo());
			}

			return usuarios;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void modificarUsuarioConRoles(String usuApellidos, Long usuCodigo, String usuCodigoInterno,
			String usuConstrasena, String usuCorreo, String usuEstadoRegistro, Long usuIntentosFallidos,
			Long ciaCodigo_SegCompania, String usuLogin, String usuNombres, Date usuUltmimaModificacionPass,
			Long usuSession, List<String> rolesAsignados, String compania, String sistema) throws Exception {

		SegUsuario entity = null;

		try {
			if (usuApellidos == null) {
				throw new ZMessManager().new EmptyFieldException("usuApellidos");
			}

			if ((usuApellidos != null) && (Utilities.checkWordAndCheckWithlength(usuApellidos, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuApellidos");
			}

			if (usuCodigo == null) {
				throw new ZMessManager().new EmptyFieldException("usuCodigo");
			}

			if ((usuCodigo != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + usuCodigo, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuCodigo");
			}

			if (usuCodigoInterno == null) {
				throw new ZMessManager().new EmptyFieldException("usuCodigoInterno");
			}

			if ((usuCodigoInterno != null) && (Utilities.checkWordAndCheckWithlength(usuCodigoInterno, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuCodigoInterno");
			}

			if (usuEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException("usuEstadoRegistro");
			}

			if ((usuEstadoRegistro != null) && (Utilities.checkWordAndCheckWithlength(usuEstadoRegistro, 1) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuEstadoRegistro");
			}

			if (usuLogin == null) {
				throw new ZMessManager().new EmptyFieldException("usuLogin");
			}

			if ((usuLogin != null) && (Utilities.checkWordAndCheckWithlength(usuLogin, 30) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuLogin");
			}

			if (usuNombres == null) {
				throw new ZMessManager().new EmptyFieldException("usuNombres");
			}

			if ((usuNombres != null) && (Utilities.checkWordAndCheckWithlength(usuNombres, 50) == false)) {
				throw new ZMessManager().new NotValidFormatException("usuNombres");
			}

			if (usuSession == null) {
				throw new ZMessManager().new EmptyFieldException("usuCodigo_SegUsuario");
			}

			if (ciaCodigo_SegCompania == null) {
				throw new ZMessManager().new EmptyFieldException("ciaCodigo_SegCompania");
			}

			if ((ciaCodigo_SegCompania != null) && (Utilities
					.checkNumberAndCheckWithPrecisionAndScale("" + ciaCodigo_SegCompania, 5, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("ciaCodigo_SegCompania");
			}

			SegCompania segCompaniaClass = segCompaniaLogic.getSegCompania(ciaCodigo_SegCompania);

			if (segCompaniaClass == null) {
				throw new ZMessManager().new ForeignException("segCompania");
			}

			SegUsuario segUsuarioClass = logicSegUsuario1.getSegUsuario(usuSession);

			if (segUsuarioClass == null) {
				throw new ZMessManager().new ForeignException("segUsuario");
			}

			// Se consulta el suuario por codigo
			entity = getSegUsuario(usuCodigo);

			if (entity == null) {
				throw new Exception("No existe el usuario " + usuCodigo);
			}

			// Se consultan los roles actuales del usuario
			Object[] variablesConsultarolesDeUsuario = { "segUsuarioBySegUsuarioUsuCodigo.usuCodigo", false, usuCodigo,
					"=", "rluEstadoRegistro", true, "A", "=" };
			List<SegRolUsuario> rolesDeUsuario = segRolUsuarioLogic.findByCriteria(variablesConsultarolesDeUsuario,
					null, null);

			// Se arma un mapa con los codigos de los roles asignados
			Map<Long, SegRolUsuario> rolesActualesDeUsuario = new HashMap<Long, SegRolUsuario>();

			if (rolesDeUsuario != null && rolesDeUsuario.size() > 0) {
				for (SegRolUsuario segRolUsuario : rolesDeUsuario) {
					rolesActualesDeUsuario.put(segRolUsuario.getRluCodigo(), segRolUsuario);

					// Se borra la asignación del rol al usuario
					segRolUsuarioLogic.deleteSegRolUsuario(segRolUsuario);
				}
			}

			// Se actualiza la información del usuario
			entity.setUsuApellidos(usuApellidos);
			entity.setUsuCodigo(usuCodigo);
			entity.setUsuCodigoInterno(usuCodigoInterno);
			if (usuConstrasena != null && !usuConstrasena.equals(""))
				entity.setUsuConstrasena(usuConstrasena);
			entity.setUsuCorreo(usuCorreo);
			entity.setUsuEstadoRegistro(usuEstadoRegistro);
			entity.setUsuLogin(usuLogin);
			entity.setUsuNombres(usuNombres);
			entity.setUsuIntentosFallidos(usuIntentosFallidos != null ? usuIntentosFallidos : 0L);
			entity.setUsuCompaniaCiaCodigo(segCompaniaClass.getCiaCodigo());
			entity.setUsuUltmimaModificacionPass(
					usuUltmimaModificacionPass != null ? usuUltmimaModificacionPass : new Date());
			entity.setSegUsuario(segUsuarioClass);

			segUsuarioDAO.update(entity);

			// Se borran todos los roles asignados al usuario

			// Se asignan los roles al usuario
			for (String strRolCodigo : rolesAsignados) {

				Long rolCodigo = Long.parseLong(strRolCodigo);

				// Se consulta el rol
				SegRol segRol = segRolLogic.getSegRol(rolCodigo);
				if (segRol == null || !segRol.getRolEstadoRegistro().equals("A")) {
					throw new Exception("No existe el rol " + rolCodigo);
				}

				SegRolUsuario segRolUsuario = new SegRolUsuario();

				segRolUsuario.setRluCodigo(null);
				segRolUsuario.setRluEstadoRegistro("A");
				segRolUsuario.setSegRol(segRol);
				segRolUsuario.setSegUsuarioByModUsuCodigo(segUsuarioClass);
				segRolUsuario.setSegUsuarioBySegUsuarioUsuCodigo(entity);

				segRolUsuarioLogic.saveSegRolUsuario(segRolUsuario);

			}

			// Se asigna el permiso al usuario a nivel de SISTEMA-COMPANIA
			if (usuSession == 0) {
				// String perEstadoRegistro,
				// Long sicCodigo_SegSistemaCia, Long gruCodigo_SegGrupoOpcion,
				// Long opcCodigo_SegOpcion, Long rolCodigo_SegRol,
				// Long sucCodigo_SegSucursal, Long usuCodigo_SegUsuario,
				// Long usuCodigo_SegUsuario0

				SegPermiso segPermiso = new SegPermiso();
				segPermiso.setPerEstadoRegistro("A");
				segPermiso.setSegUsuarioByUsuCodigo(entity);
				segPermiso.setSegUsuarioByModUsuCodigo(segUsuarioClass);

				segPermisoLogic.saveSegPermiso(segPermiso);
			} else {

				List<SegSistemaCia> sistemasCompanias = segSistemaCiaLogic.findByCriteria(new Object[] {
						"segCompania.ciaCodigo", false, compania, "=", "segSistema.sisCodigo", false, sistema, "=" },
						null, null);

				if (sistemasCompanias == null || sistemasCompanias.size() != 0) {
					throw new Exception("No se encontro la relación sistema companía " + sistema + " - " + compania);
				}

				SegSistemaCia segSistemaCia = sistemasCompanias.get(0);

				SegPermiso segPermiso = new SegPermiso();
				segPermiso.setPerEstadoRegistro("A");
				segPermiso.setSegUsuarioByUsuCodigo(entity);
				segPermiso.setSegUsuarioByModUsuCodigo(segUsuarioClass);
				segPermiso.setSegSistemaCia(segSistemaCia);

				segPermisoLogic.saveSegPermiso(segPermiso);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<SegUsuario> consultarUsuariosPorRol(Long rolCodigo) throws Exception {
		try {
			List<SegUsuario> usuarios = new ArrayList<SegUsuario>();

			Object[] variables = { "segRol.rolCodigo", false, rolCodigo, "=", "rluEstadoRegistro", true, "A", "=" };

			List<SegRolUsuario> usuariosConEseRol = segRolUsuarioLogic.findByCriteria(variables, null, null);
			if (usuariosConEseRol != null) {
				for (SegRolUsuario segRolUsuario : usuariosConEseRol) {

					usuarios.add(segRolUsuario.getSegUsuarioBySegUsuarioUsuCodigo());

				}
			}

			return usuarios;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioDTO consultarPrimeraVez(String usu_login, String usu_password, String dominio) throws Exception {

		UsuarioDTO usuDto = new UsuarioDTO();
		List<SegUsuario> usuario = new ArrayList<SegUsuario>();
		try {

			SegUsuario u = null;

			boolean existeUsuarioEnBD = false;

			// Se consulta el usuario en la BD de seguridad
			usuario = findByCriteria(new Object[] { "usuLogin", true, usu_login.toUpperCase().trim(), "=",
					"usuConstrasena", true, Utilities.convertirMD5(usu_password).trim(), "=", "usuCodigoInterno", true,
					dominio.toUpperCase().trim(), "=", "usuEstadoRegistro", true, "A", "=" }, null, null);

			existeUsuarioEnBD = (usuario != null && usuario.size() > 0) ? true : false;

			// Se valida su contrase�a en la BD
			if (existeUsuarioEnBD) {
				u = usuario.get(0);
				usuDto.setUsu_activo(u.getUsuEstadoRegistro());
				usuDto.setUsu_apellidos(u.getUsuApellidos());
				usuDto.setUsu_codigo(u.getUsuCodigo().longValue() + "");
				usuDto.setUsu_codigo_interno(u.getUsuCodigoInterno());
				usuDto.setUsu_login(u.getUsuLogin());
				usuDto.setUsu_nombres(u.getUsuNombres());
				usuDto.setUsuValidarPrimeraVez(u.getUsuPrimerLoginSso());
				usuDto.setUsu_intentos_fallidos(
						u.getUsuIntentosFallidos() != null ? u.getUsuIntentosFallidos().toString() : "0");
				usuDto.setUsu_ultmima_modificacion_pass(u.getUsuUltmimaModificacionPass() != null
						? Fechas.dateToStr(u.getUsuUltmimaModificacionPass(), "yyyy-MM-dd") : null);
				usuDto.setContrasenaMD5(u.getUsuConstrasena());

			}

		} catch (Exception e) {
			log.error("Error autenticando el usuario", e);
			throw e;
		}

		return usuDto;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean savePrimeraVez(UsuarioDTO u) throws Exception {

		SegUsuario entity = null;
		try {
			// Se consultan los roles actuales del usuario
			Object[] usuario = { "usuLogin", true, u.getUsu_login(), "=", "usuEstadoRegistro", true, "A", "=" };
			List<SegUsuario> usu = segUsuarioLogic.findByCriteria(usuario, null, null);

			if (usu.isEmpty() == false) {
				entity = usu.get(0);
				entity.setUsuPrimerLoginSso("I");
				segUsuarioDAO.save(entity);

			}
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void crearUsuario(SellPersonaDTO personaDTO) throws Exception {
		try {
			
			SegUsuario usuario =null;
			List<SegUsuario> usuarios = findByCriteria(
					new Object[] { "usuLogin", true, personaDTO.getNumeroDocumento().trim(), "=", }, null, null);

			if (usuarios == null || usuarios.size()==0) {

				usuario = new SegUsuario();
				usuario.setUsuCodigo(null);
				usuario.setSegUsuario(getSegUsuario(0L));
				usuario.setUsuNombres(personaDTO.getPrimerNombre() + personaDTO.getSegundoNombre());
				usuario.setUsuApellidos(personaDTO.getPrimerApellido() + personaDTO.getSegundoApellido());
				usuario.setUsuLogin(personaDTO.getNumeroDocumento());
				String Contraseña = Utilities.crearContraseña(personaDTO.getPrimerNombre(), personaDTO.getPrimerApellido(),
						personaDTO.getNumeroDocumento());
				usuario.setUsuConstrasena(Utilities.convertirMD5(Contraseña));
				usuario.setUsuEstadoRegistro("A");
				usuario.setUsuCodigoInterno("1");
				usuario.setUsuUltmimaModificacionPass(new Date());
				usuario.setUsuCorreo(personaDTO.getEmail());
				usuario.setUsuIntentosFallidos(0L);
				usuario.setUsuCompaniaCiaCodigo(1L);
				usuario.setUsuPrimerLoginSso("I");
				saveSegUsuario(usuario);
			}else {
				usuario = usuarios.get(0);
				usuario.setSegUsuario(getSegUsuario(0L));
				usuario.setUsuNombres(personaDTO.getPrimerNombre() + personaDTO.getSegundoNombre());
				usuario.setUsuApellidos(personaDTO.getPrimerApellido() + personaDTO.getSegundoApellido());
				usuario.setUsuLogin(personaDTO.getNumeroDocumento());
				String Contraseña = Utilities.crearContraseña(personaDTO.getPrimerNombre(), personaDTO.getPrimerApellido(),
						personaDTO.getNumeroDocumento());
				usuario.setUsuConstrasena(Utilities.convertirMD5(Contraseña));
				usuario.setUsuEstadoRegistro("A");
				usuario.setUsuCodigoInterno("1");
				usuario.setUsuUltmimaModificacionPass(new Date());
				usuario.setUsuCorreo(personaDTO.getEmail());
				usuario.setUsuIntentosFallidos(0L);
				usuario.setUsuCompaniaCiaCodigo(1L);
				usuario.setUsuPrimerLoginSso("I");
				updateSegUsuario(usuario);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void agregarRolles(SellPersonaDTO personaDTO) throws Exception {
		try {
			SegRol rol = null;
			SegRolUsuario sru = new SegRolUsuario();
			sru.setRluCodigo(null);
			List<SegUsuario> usuarios = findByCriteria(
					new Object[] { "usuLogin", true, personaDTO.getNumeroDocumento().trim(), "=", }, null, null);

			sru.setSegUsuarioBySegUsuarioUsuCodigo(usuarios.get(0));
			// 1---> administrador
			// 2---> adminitrador sellout vin "pantalla para motorred"
			// 3---> asesor

			if (Arrays.toString(personaDTO.getSomRolesSellou()).contains("1")) {
				rol = segRolLogic.getSegRol(2L);
			} else if (Arrays.toString(personaDTO.getSomRolesSellou()).contains("2")) {
				rol = segRolLogic.getSegRol(12L);
			} else if (Arrays.toString(personaDTO.getSomRolesSellou()).contains("3")) {
				rol = segRolLogic.getSegRol(9L);
			}
			sru.setSegRol(rol);
			sru.setRluEstadoRegistro("A");
			sru.setSegUsuarioByModUsuCodigo(getSegUsuario(0L));
			segRolUsuarioDAO.save(sru);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	 @Override
	    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	    public String generarNuevaContrasena(String usuLogin, String email) throws Exception{
	    	try {
				
	    		//Se consulta el usuario por identificacion 
	    		log.info("Se consulta el usuario " + usuLogin);
				Object[] variables = {"usuLogin", true, usuLogin, "="};
				List<SegUsuario> usuariosEncontrados = findByCriteria(variables, null, null);
				
				if (usuariosEncontrados == null || usuariosEncontrados.isEmpty()){
					throw new Exception("No se encontró un usuario con la identificación " + usuLogin);
				}
				
				final SegUsuario segUsuario = usuariosEncontrados.get(0);
				
				if (!segUsuario.getUsuEstadoRegistro().equals("A")){
					segUsuario.setUsuEstadoRegistro("A");
				}
				
				//Se genera la constrasena
				final String nuevaContrasena = PasswordGenerator.getPassword(6);
				final String contrasenaMD5 = Utilities.convertirMD5(nuevaContrasena);
				
				segUsuario.setUsuConstrasena(contrasenaMD5);
				
				//Se actualiza el usuario
				log.info("Se actualiza el usaurio " + segUsuario.getUsuLogin());
				updateSegUsuario(segUsuario);
				
//				//********************* Se notifica a CRM ********************* 
//				
//				//Se consulta el parametro que indica la ubicación del servicio de CRM para el cambio de PWD
//				log.info("Se notificará el cambio de PWD a CRM...");
//				
//				log.info("Consultando el param SERVICIO_CRM_CAMBIO_PWD...");
//				VinCamposParametrizables paramUrlCambioPwdCRM = vinCamposParametrizablesLogic.getVinCamposParametrizables("SERVICIO_CRM_CAMBIO_PWD");
//				
//				if (paramUrlCambioPwdCRM == null || paramUrlCambioPwdCRM.getValor() == null || paramUrlCambioPwdCRM.getValor().trim().equals("")){
//					log.error("No existe el parámetro SERVICIO_CRM_CAMBIO_PWD, el cual indica la ubicación del servicio para cambio de PWD en CRM!");
//				}
//				else{
//					
//					try {
//						String URL = paramUrlCambioPwdCRM.getValor();
//						
//						if (!URL.endsWith("?")){
//							URL += "?";
//						}
//						
//						RestTemplate restTemplate = new RestTemplate();
//						
//						String paramCedula = "cedula="+usuLogin;
//						String paramPwdEncriptado = "password="+contrasenaMD5;
//						String rutaDefinitiva = URL+paramCedula+"&"+paramPwdEncriptado;
//						
//						log.info("Se notificará el cambio de PWD en CRM a la siguiente URL: " + rutaDefinitiva);
//						
//						String response = restTemplate.getForObject(rutaDefinitiva,  String.class);
//						
//						log.info("La respuesta de la invocación al servicio de cambio de PWD en CRM es: " + response);
//					} catch (Exception e) {
//						log.error("Error invocando el servicio de cambio de PWD en CRM: " + e.getMessage(), e);
//					}
//					
//				}
				
//				//********************* Se notifica a CRM ********************* 
//				try {
//					
//					URL wsdlURL = AdminUsuarios_Service.WSDL_LOCATION;
//					
//				} catch (Exception e) {
//					log.error("Error invocando el servicio de cambio de PWD en VCloud: " + e.getMessage(), e);
//				}
				
				
				//se notifica al usuario por email
				new Thread(){
					public void run() {
						
						try {
							log.info("Notificando al usuario su contrasena al correo " + segUsuario.getUsuCorreo());
							verificacionEmailUsuarioLogic.notificarCambioContrasena(segUsuario, nuevaContrasena);
						} catch (Exception e) {
							log.error("Error Notificando al usuario su contrasena al correo: " + e.getMessage(), e);
						}
						
					};
				}.start();
					
				
				return nuevaContrasena;
				
			} catch (Exception e) {
				log.error("Error generando la nueva contrasena para el usaurio " + usuLogin + ": " + e.getMessage(), e);
				return null;
			}
	    }
	 
	 	
	/**
	 * 
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 *
	 * @see com.vortexbird.sentinel.modelo.control.ISegUsuarioLogic#crearUsuarioConRoles(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.Long, java.lang.Long, java.lang.String, java.lang.String,
	 *      java.util.Date, java.lang.Long, java.util.List, java.lang.String,
	 *      java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void crearUsuarioConRolesYPermisos(String usuApellidos, String usuCodigoInterno, String usuConstrasena,
			String usuCorreo, String usuEstadoRegistro, Long usuIntentosFallidos, Long ciaCodigo_SegCompania,
			String usuLogin, String usuNombres, Date usuUltmimaModificacionPass, Long usuSession,
			List<SegRolDTO> rolesAsignados, String compania, String sistema) throws Exception {
		
		//AFVL					1. Se declaran variables y validadores necesarios
		SegUsuario entity = null;
		EmailValidator emailValidator = new EmailValidator();
		org.apache.commons.validator.routines.EmailValidator validator = org.apache.commons.validator.routines.EmailValidator.getInstance();

		try {
			//AFVL				2. Se validan los campos 
			if (usuApellidos == null) {
				throw new Exception("Debe ingresar un valor para el campo : usuApellidos");
			}

			if ((usuApellidos != null) && (Utilities.checkWordAndCheckWithlength(usuApellidos, 50) == false)) {
				throw new Exception("La longitud del campo usuApellidos, supera los 50 caracteres permitidos");
			}

			if (usuCodigoInterno == null) {
				throw new Exception("Debe ingresar un valor para el campo : usuCodigoInterno");
			}

			if ((usuCodigoInterno != null) && (Utilities.checkWordAndCheckWithlength(usuCodigoInterno, 50) == false)) {
				throw new Exception("La longitud del campo usuCodigoInterno, supera los 50 caracteres permitidos");
			}

			if (usuConstrasena == null) {
				throw new Exception("Debe ingresar un valor para el campo : usuConstrasena");
			}

			if ((usuConstrasena != null) && (Utilities.checkWordAndCheckWithlength(usuConstrasena, 50) == false)) {
				throw new Exception("La longitud del campo usuConstrasena, supera los 50 caracteres permitidos");
			}

			if (usuEstadoRegistro == null) {
				throw new Exception("Debe ingresar un valor para el campo : usuEstadoRegistro");
			}

			if ((usuEstadoRegistro != null) && (Utilities.checkWordAndCheckWithlength(usuEstadoRegistro, 1) == false)) {
				throw new Exception("La longitud del campo usuEstadoRegistro, supera 1 caracter permitido");
			}

			if (usuLogin == null) {
				throw new Exception("Debe ingresar un valor para el campo : usuLogin");
			}

			if ((usuLogin != null) && (Utilities.checkWordAndCheckWithlength(usuLogin, 30) == false)) {
				throw new Exception("La longitud del campo usuLogin, supera los 30 caracteres permitidos");
			}

			if (usuNombres == null) {
				throw new Exception("Debe ingresar un valor para el campo : usuNombres");
			}

			if ((usuNombres != null) && (Utilities.checkWordAndCheckWithlength(usuNombres, 50) == false)) {
				throw new Exception("La longitud del campo usuNombres, supera los 50 caracteres permitidos");
			}

			if (usuCorreo == null) {
				throw new Exception("Debe ingresar un valor para el campo : usuCorreo");
			}

			if ((usuCorreo != null) && (Utilities.checkWordAndCheckWithlength(usuCorreo, 200) == false)) {
				throw new Exception("La longitud del campo usuCorreo, supera los 200 caracteres permitidos");
			}

			if (emailValidator.validateEmail(usuCorreo) == false) {
				throw new Exception("Formato no valido para el campo: usuCorreo");
			}

			if (validator.isValid(usuCorreo) == false) {
				throw new Exception("Formato no valido para el campo: usuCorreo");
			}

			if (usuSession == null) {
				throw new Exception("Debe ingresar un valor para el campo : usuSession");
			}

			if ((usuSession != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale("" + usuSession, 5, 0) == false)) {
				throw new Exception("La longitud del campo usuSession, supera los 5 caracteres permitidos");
			}

			if (sistema == null) {
				throw new Exception("Debe ingresar un valor para el campo : sistema");
			}

			if (compania == null) {
				throw new Exception("Debe ingresar un valor para el campo : compania");
			}
			
			//AFVL					3. Se consulta el usuario modificador
			SegUsuario modUsuCodigo = logicSegUsuario1.getSegUsuario(usuSession);

			if (modUsuCodigo == null) {
				throw new Exception("El usuario modificador especificado no existe");
			}
			
			//AFVL					4. Se valida que el usuario no exista
			SegUsuario segUsuario = consultarUsuarioPorLogin(usuLogin);

			if (segUsuario != null) {
				throw new Exception("Ya existe un usuario con el login " + usuLogin);
			}

			//AFVL					5. Se consulta la compania
			SegCompania segCompania = segCompaniaLogic.getSegCompania(Long.parseLong(compania));

			if (segCompania == null) {
				throw new Exception("No existe la companía " + compania);
			}
				
			//AFVL					6. Se crea el usuario
			entity = new SegUsuario();
			entity.setUsuApellidos(usuApellidos);
			entity.setUsuCodigoInterno(usuCodigoInterno);
			entity.setUsuConstrasena(usuConstrasena);
			entity.setUsuCorreo(usuCorreo);
			entity.setUsuEstadoRegistro(usuEstadoRegistro);
			entity.setUsuLogin(usuLogin);
			entity.setUsuNombres(usuNombres);
			entity.setUsuIntentosFallidos(0L);
			entity.setUsuCompaniaCiaCodigo(segCompania.getCiaCodigo());
			entity.setUsuUltmimaModificacionPass(new Date());
			entity.setSegUsuario(modUsuCodigo);
			entity.setUsuPrimerLoginSso("I");

			segUsuarioDAO.save(entity);
			
			//AFVL					7. Se busca la relacion sistema/compania
			List<SegSistemaCia> sistemasCompanias = segSistemaCiaLogic.findByCriteria(new Object[] {
					"segCompania.ciaCodigo", false, compania, "=", "segSistema.sisCodigo", false, sistema, "=" }, null,
					null);

			if (sistemasCompanias == null || sistemasCompanias.size() == 0) {
				throw new Exception("No se encontro la relación sistema companía " + sistema + " - " + compania);
			}

			SegSistemaCia segSistemaCia = sistemasCompanias.get(0);

			//AFVL					8. Se asignan roles y permisos
			for (SegRolDTO rol : rolesAsignados) {

				SegRolUsuario segRolUsuario = new SegRolUsuario();

				// Se consulta el rol
				Long rolCodigo = rol.getRolCodigo();
				SegRol segRol = segRolLogic.getSegRol(rolCodigo);

				segRolUsuario.setRluEstadoRegistro("A");
				segRolUsuario.setSegRol(segRol);
				segRolUsuario.setSegUsuarioBySegUsuarioUsuCodigo(entity);
				segRolUsuario.setSegUsuarioByModUsuCodigo(modUsuCodigo);

				segRolUsuarioLogic.saveSegRolUsuario(segRolUsuario);

				SegPermiso segPermiso = new SegPermiso();
				segPermiso.setPerEstadoRegistro("A");
				segPermiso.setSegRol(segRol);
				segPermiso.setSegUsuarioByUsuCodigo(entity);
				segPermiso.setSegSistemaCia(segSistemaCia);
				segPermiso.setSegUsuarioByModUsuCodigo(modUsuCodigo);

				segPermisoLogic.saveSegPermiso(segPermiso);

			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	/**
	 * 
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 *
	 * @see com.vortexbird.sentinel.modelo.control.ISegUsuarioLogic#actualizarRolesYPermisosDeUsuario(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 *
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void actualizarRolesYPermisosDeUsuario(Long usuCodigo, Long rolCodigoActual, Long rolCodigoNuevo, Long sistema, Long compania) throws Exception {
		
		try {
			
			//AFVL					1. Se valida que los parametros tengan valor valido
			if (usuCodigo == null) {
				throw new Exception("Debe ingresar un valor para el campo : usuCodigo");
			}
			
			if (rolCodigoActual == null) {
				throw new Exception("Debe ingresar un valor para el campo : rolCodigoActual");
			}
			
			if (rolCodigoNuevo == null) {
				throw new Exception("Debe ingresar un valor para el campo : rolCodigoNuevo");
			}
			
			if (sistema == null) {
				throw new Exception("Debe ingresar un valor para el campo : sistema");
			}
			
			if (compania == null) {
				throw new Exception("Debe ingresar un valor para el campo : compania");
			}
			
			//AFVL					2. Se valida que el usuario exista en base de datos
			SegUsuario usuario = logicSegUsuario1.getSegUsuario(usuCodigo);
			
			if(usuario == null) {
				throw new Exception("El usuario con usu_codigo = "+usuCodigo+" no existe en la base datos.");
			}
			
			//AFVL					3. Se valida que los roles esten creados en base de datos
			SegRol rolActual = segRolLogic.getSegRol(rolCodigoActual);
			
			if(rolActual == null) {
				throw new Exception("El rol con rol_codigo = "+rolCodigoActual+" no esta definido.");
			}
			
			SegRol rolNuevo = segRolLogic.getSegRol(rolCodigoNuevo);
			
			if(rolNuevo == null) {
				throw new Exception("El rol con rol_codigo = "+rolCodigoNuevo+" no esta definido.");
			}
			
			//AFVL					4. Se valida que la relacion sistema x compania exista
			List<SegSistemaCia> sistemasCompanias = segSistemaCiaLogic.findByCriteria(new Object[] {"segCompania.ciaCodigo", false, compania, "=", 
																									"segSistema.sisCodigo", false, sistema, "=" }, null,null);

			if (sistemasCompanias == null || sistemasCompanias.size() == 0) {
				throw new Exception("No se encontro la relación sistema - companía = " + sistema + " - " + compania);
			}
			
			SegSistemaCia segSistemaCia = sistemasCompanias.get(0);
			
			//AFVL					5. Se valida que la realcion rol - usuario este definida y no exista mas de un registro
			List<SegRolUsuario> rolesUsuario = segRolUsuarioLogic.findByCriteria(new Object[] {"segUsuarioBySegUsuarioUsuCodigo.usuCodigo", false, usuCodigo, "=",
																							   "segRol.rolCodigo", false, rolCodigoActual, "=",
																							   "rluEstadoRegistro", true, Constantes.ESTADO_ACTIVO, "="}, null, null);
			
			if (rolesUsuario == null || rolesUsuario.size() == 0) {
				throw new Exception("No se encontro la relación rol - usuario = " + rolCodigoActual + " - " + usuCodigo );
			}
			
			if(rolesUsuario.size() >= 2) {
				throw new Exception("Existe un problema con la asignacion del rol al usuario. La combinacion <<  rol_codigo = "+rolCodigoActual+" y usu_codigo = "+usuCodigo+"  >> existe en base de datos mas de una vez.");
			}
			
			//AFVL					6. Se valida que la relacion rol - usuario - sistema x compania este definida y no exista mas de un registro
			List<SegPermiso> permisosUsuario = segPermisoLogic.findByCriteria(new Object[] {"segUsuarioByUsuCodigo.usuCodigo", false, usuCodigo, "=",
																						    "segRol.rolCodigo", false, rolCodigoActual, "=",
																						    "segSistemaCia.sicCodigo", false, segSistemaCia.getSicCodigo(), "=",
																						    "perEstadoRegistro", true, Constantes.ESTADO_ACTIVO, "="}, null, null);
			
			
			if (permisosUsuario == null || permisosUsuario.size() == 0) {
				throw new Exception("No se encontro el permiso rol - usuario - sistema_compania = " + rolCodigoActual + " - " + usuCodigo + " - " + segSistemaCia.getSicCodigo());
			}
			
			if (permisosUsuario.size() >= 2) {
				throw new Exception("Existe un problema con la asignacion del permiso al usuario. La combinacion <<  rol - usuario - sistema_compania = " + rolCodigoActual  + " - " + usuCodigo + " - " + segSistemaCia.getSicCodigo() + " >> existe en base de datos mas de una vez.");
			}
			
			//AFVL					7. Se obtienen los objetos de cada consulta
			SegRolUsuario rolUsuario = rolesUsuario.get(0);
			SegPermiso permiso = permisosUsuario.get(0);
			
			rolUsuario.setSegRol(rolNuevo);
			permiso.setSegRol(rolNuevo);
			
			//AFVL					8. Se guardan los ajustes en la base de datos
			segRolUsuarioLogic.updateSegRolUsuario(rolUsuario);
			segPermisoDAO.update(permiso);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	/**
	 * 
	 * @author Andrés Felipe Vargas López
	 * @version Jun 21, 2018
	 *
	 * @see com.vortexbird.sentinel.modelo.control.ISegUsuarioLogic#adicionarRolPermisoAUsuario(java.lang.Long, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long)
	 *
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void adicionarRolPermisoAUsuario(Long usuCodigo, List<SegRolDTO> listaRoles, Long sistema, Long compania, Long usuSession) throws Exception {
		try {
			//AFVL					1. Se valida que los parametros tengan valor valido
			if (usuCodigo == null) {
				throw new Exception("Debe ingresar un valor para el campo : usuCodigo");
			}
			
			if (listaRoles == null || listaRoles.size() == 0) {
				throw new Exception("Debe ingresar un valor para el campo : listaRoles");
			}
			
			if (sistema == null) {
				throw new Exception("Debe ingresar un valor para el campo : sistema");
			}
			
			if (compania == null) {
				throw new Exception("Debe ingresar un valor para el campo : compania");
			}
			
			if (usuSession == null) {
				throw new Exception("Debe ingresar un valor para el campo : usuSession");
			}
			
			//AFVL					2. Se valida que el usuario exista en base de datos
			SegUsuario usuario = logicSegUsuario1.getSegUsuario(usuCodigo);
			
			if(usuario == null) {
				throw new Exception("El usuario con usu_codigo = "+usuCodigo+" no existe en la base datos.");
			}
			
			//AFVL					3. Se consulta el usuario modificador
			SegUsuario modUsuCodigo = logicSegUsuario1.getSegUsuario(usuSession);

			if (modUsuCodigo == null) {
				throw new Exception("El usuario modificador especificado no existe");
			}
			
			//AFVL					4. Se valida que la relacion sistema x compania exista
			List<SegSistemaCia> sistemasCompanias = segSistemaCiaLogic.findByCriteria(new Object[] {"segCompania.ciaCodigo", false, compania, "=", 
																									"segSistema.sisCodigo", false, sistema, "=" }, null,null);

			if (sistemasCompanias == null || sistemasCompanias.size() == 0) {
				throw new Exception("No se encontro la relación sistema - companía = " + sistema + " - " + compania);
			}
			
			SegSistemaCia segSistemaCia = sistemasCompanias.get(0);
			
			for (SegRolDTO rolAsignar : listaRoles) {
				
				//AFVL				5. Se valida que los roles esten creados en base de datos
				SegRol rolActual = segRolLogic.getSegRol(rolAsignar.getRolCodigo());
				if(rolActual == null) {
					throw new Exception("El rol con rol_codigo = "+rolAsignar.getRolCodigo()+" no esta definido.");
				}
				
				Long rolCodigoActual = rolActual.getRolCodigo();
				
				//AFVL				6. Se valida que la realcion rol - usuario NO este definida mas de una vez
				List<SegRolUsuario> rolesUsuario = segRolUsuarioLogic.findByCriteria(new Object[] {"segUsuarioBySegUsuarioUsuCodigo.usuCodigo", false, usuCodigo, "=",
																								   "segRol.rolCodigo", false, rolCodigoActual, "=",
																								   "rluEstadoRegistro", true, Constantes.ESTADO_ACTIVO, "="}, null, null);
			
				if(rolesUsuario.size() > 1) {
					throw new Exception("Existe un problema con la asignacion del rol al usuario. La combinacion <<  rol_codigo = "+rolCodigoActual+" y usu_codigo = "+usuCodigo+"  >> existe en base de datos mas de una vez.");
				}
				
				//AFVL				7. Se valida que la relacion rol - usuario - sistema x compania NO este definida mas de una vez
				List<SegPermiso> permisosUsuario = segPermisoLogic.findByCriteria(new Object[] {"segUsuarioByUsuCodigo.usuCodigo", false, usuCodigo, "=",
																							    "segRol.rolCodigo", false, rolCodigoActual, "=",
																							    "segSistemaCia.sicCodigo", false, segSistemaCia.getSicCodigo(), "=",
																							    "perEstadoRegistro", true, Constantes.ESTADO_ACTIVO, "="}, null, null);
				
				
				if (permisosUsuario.size() > 1) {
					throw new Exception("Existe un problema con la asignacion del permiso al usuario. La combinacion <<  rol - usuario - sistema_compania = " + rolCodigoActual  + " - " + usuCodigo + " - " + segSistemaCia.getSicCodigo() + " >> existe en base de datos mas de una vez.");
				}
				
				//AFVL				8. Se crea el rol usuario si no existe en base de datos
				if (rolesUsuario == null || rolesUsuario.size() == 0) {
					SegRolUsuario segRolUsuario = new SegRolUsuario();
					segRolUsuario.setRluEstadoRegistro("A");
					segRolUsuario.setSegRol(rolActual);
					segRolUsuario.setSegUsuarioBySegUsuarioUsuCodigo(usuario);
					segRolUsuario.setSegUsuarioByModUsuCodigo(modUsuCodigo);
					segRolUsuarioLogic.saveSegRolUsuario(segRolUsuario);
				}
				
				//AFVL				9. Se crea el permiso si no existe
				if (permisosUsuario == null || permisosUsuario.size() == 0) {
					SegPermiso segPermiso = new SegPermiso();
					segPermiso.setPerEstadoRegistro("A");
					segPermiso.setSegRol(rolActual);
					segPermiso.setSegUsuarioByUsuCodigo(usuario);
					segPermiso.setSegSistemaCia(segSistemaCia);
					segPermiso.setSegUsuarioByModUsuCodigo(modUsuCodigo);
					segPermisoLogic.saveSegPermiso(segPermiso);
				}
				
			}
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	/**
	 * 
	 * @author Andrés Felipe Vargas López
	 * @version Jun 21, 2018
	 *
	 * @see com.vortexbird.sentinel.modelo.control.ISegUsuarioLogic#eliminarRolPermisoAUsuario(java.lang.Long, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long)
	 *
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarRolPermisoAUsuario(Long usuCodigo, List<SegRolDTO> listaRoles, Long sistema, Long compania, Long usuSession) throws Exception {
		try {
			//AFVL					1. Se valida que los parametros tengan valor valido
			if (usuCodigo == null) {
				throw new Exception("Debe ingresar un valor para el campo : usuCodigo");
			}
			
			if (listaRoles == null || listaRoles.size() == 0) {
				throw new Exception("Debe ingresar un valor para el campo : listaRoles");
			}
			
			if (sistema == null) {
				throw new Exception("Debe ingresar un valor para el campo : sistema");
			}
			
			if (compania == null) {
				throw new Exception("Debe ingresar un valor para el campo : compania");
			}
			
			if (usuSession == null) {
				throw new Exception("Debe ingresar un valor para el campo : usuSession");
			}
			
			//AFVL					2. Se valida que el usuario exista en base de datos
			SegUsuario usuario = logicSegUsuario1.getSegUsuario(usuCodigo);
			
			if(usuario == null) {
				throw new Exception("El usuario con usu_codigo = "+usuCodigo+" no existe en la base datos.");
			}
			
			//AFVL					3. Se consulta el usuario modificador
			SegUsuario modUsuCodigo = logicSegUsuario1.getSegUsuario(usuSession);

			if (modUsuCodigo == null) {
				throw new Exception("El usuario modificador especificado no existe");
			}
			
			//AFVL					4. Se valida que la relacion sistema x compania exista
			List<SegSistemaCia> sistemasCompanias = segSistemaCiaLogic.findByCriteria(new Object[] {"segCompania.ciaCodigo", false, compania, "=", 
																									"segSistema.sisCodigo", false, sistema, "=" }, null,null);

			if (sistemasCompanias == null || sistemasCompanias.size() == 0) {
				throw new Exception("No se encontro la relación sistema - companía = " + sistema + " - " + compania);
			}
			
			SegSistemaCia segSistemaCia = sistemasCompanias.get(0);
			
			for (SegRolDTO rolAsignar : listaRoles) {
				
				//AFVL				5. Se valida que los roles esten creados en base de datos
				SegRol rolActual = segRolLogic.getSegRol(rolAsignar.getRolCodigo());
				if(rolActual == null) {
					throw new Exception("El rol con rol_codigo = "+rolAsignar.getRolCodigo()+" no esta definido.");
				}
				
				Long rolCodigoActual = rolActual.getRolCodigo();
				
				//AFVL				6. Se valida que la realcion rol - usuario NO este definida mas de una vez
				List<SegRolUsuario> rolesUsuario = segRolUsuarioLogic.findByCriteria(new Object[] {"segUsuarioBySegUsuarioUsuCodigo.usuCodigo", false, usuCodigo, "=",
																								   "segRol.rolCodigo", false, rolCodigoActual, "=",
																								   "rluEstadoRegistro", true, Constantes.ESTADO_ACTIVO, "="}, null, null);
			
				if(rolesUsuario.size() > 1) {
					throw new Exception("Existe un problema con la asignacion del rol al usuario. La combinacion <<  rol_codigo = "+rolCodigoActual+" y usu_codigo = "+usuCodigo+"  >> existe en base de datos mas de una vez y no es posible identificar cual de los registros se debe eliminar.");
				}
				
				//AFVL				7. Se valida que la relacion rol - usuario - sistema x compania NO este definida mas de una vez
				List<SegPermiso> permisosUsuario = segPermisoLogic.findByCriteria(new Object[] {"segUsuarioByUsuCodigo.usuCodigo", false, usuCodigo, "=",
																							    "segRol.rolCodigo", false, rolCodigoActual, "=",
																							    "segSistemaCia.sicCodigo", false, segSistemaCia.getSicCodigo(), "=",
																							    "perEstadoRegistro", true, Constantes.ESTADO_ACTIVO, "="}, null, null);
				
				
				if (permisosUsuario.size() > 1) {
					throw new Exception("Existe un problema con la asignacion del permiso al usuario. La combinacion <<  rol - usuario - sistema_compania = " + rolCodigoActual  + " - " + usuCodigo + " - " + segSistemaCia.getSicCodigo() + " >> existe en base de datos mas de una vez y no es posible identificar cual de los registros se debe eliminar.");
				}
				
				//AFVL				8. Se elimina el rol usuario si existe
				if (rolesUsuario != null && !rolesUsuario.isEmpty()) {
					SegRolUsuario segRolUsuario = rolesUsuario.get(0);
					segRolUsuarioLogic.deleteSegRolUsuario(segRolUsuario);
				}
				
				//AFVL				9. Se elimina el permiso si existe
				if (permisosUsuario != null && !permisosUsuario.isEmpty()) {
					SegPermiso segPermiso = permisosUsuario.get(0);
					segPermisoLogic.deleteSegPermiso(segPermiso);
				}
				
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.vortexbird.sentinel.modelo.control.ISegUsuarioLogic#consultarUsuarioPorLogin(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public UsuarioDTO consultarUsuarioPorLogin(String login, String dominio) {
		try {
			Object[] variables = {"usuLogin", true, login.trim().toUpperCase(), "=",
					"usuCodigoInterno", true, dominio.trim().toUpperCase(), "="};
			
			List<SegUsuario> usuarios = findByCriteria(variables, null, null);
			
			if(usuarios == null || usuarios.isEmpty()){
				return null;
			}
			
			SegUsuario u = usuarios.get(0);
			
			UsuarioDTO usuDto = new UsuarioDTO();
			usuDto.setUsu_activo(u.getUsuEstadoRegistro());
			usuDto.setUsu_apellidos(u.getUsuApellidos());
			usuDto.setUsu_codigo(u.getUsuCodigo().longValue() + "");
			usuDto.setUsu_codigo_interno(u.getUsuCodigoInterno());
			usuDto.setUsu_login(u.getUsuLogin());
			usuDto.setUsu_nombres(u.getUsuNombres());
			usuDto.setUsu_correo(u.getUsuCorreo());
			usuDto.setUsu_intentos_fallidos(
					u.getUsuIntentosFallidos() != null ? u.getUsuIntentosFallidos().toString() : "0");
			usuDto.setUsu_ultmima_modificacion_pass(
					Fechas.dateToStr(u.getUsuUltmimaModificacionPass(), "yyyy-MM-dd"));
			usuDto.setNombre_rol("");
			usuDto.setContrasenaMD5(u.getUsuConstrasena());

			return usuDto;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
}
