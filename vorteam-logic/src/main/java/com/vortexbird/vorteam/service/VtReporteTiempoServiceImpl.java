package com.vortexbird.vorteam.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.vortexbird.vorteam.domain.VtParametro;
import com.vortexbird.vorteam.domain.VtReporteTiempo;
import com.vortexbird.vorteam.dto.VtReporteTiempoDTO;
import com.vortexbird.vorteam.exception.ZMessManager;
import com.vortexbird.vorteam.mapper.VtReporteTiempoMapper;
import com.vortexbird.vorteam.repository.IVtInformesRepository;
import com.vortexbird.vorteam.repository.VtReporteTiempoRepository;
import com.vortexbird.vorteam.utility.Constantes;
import com.vortexbird.vorteam.utility.SendMailUtility;
import com.vortexbird.vorteam.utility.Utilities;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Scope("singleton")
@Service
public class VtReporteTiempoServiceImpl implements VtReporteTiempoService {
	private static final Logger log = LoggerFactory.getLogger(VtReporteTiempoServiceImpl.class);

	/**
	 * Repository injected by Spring that manages VtReporteTiempo entities
	 *
	 */
	@Autowired
	private VtReporteTiempoRepository vtReporteTiempoRepository;
	@Autowired
	private VtReporteTiempoMapper vtReporteTiempoMapper;
	@Autowired
	private Validator validator;

	/**
	 * Service injected by Spring that manages VtAsignacion entities
	 *
	 */
	@Autowired
	VtAsignacionService serviceVtAsignacion1;

	/**
	 * Service injected by Spring that manages VtEstado entities
	 *
	 */
	@Autowired
	VtEstadoService serviceVtEstado2;

	@Autowired
	IVtInformesRepository vtInformesRepository;
	
	@Autowired
	VtParametroService vtParametroService;

	public void validateVtReporteTiempo(VtReporteTiempo vtReporteTiempo) throws Exception {
		try {
			Set<ConstraintViolation<VtReporteTiempo>> constraintViolations = validator.validate(vtReporteTiempo);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<VtReporteTiempo> constraintViolation : constraintViolations) {
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
	public List<VtReporteTiempo> getVtReporteTiempo() throws Exception {
		log.debug("finding all VtReporteTiempo instances");

		List<VtReporteTiempo> list = new ArrayList<VtReporteTiempo>();

		try {
			list = vtReporteTiempoRepository.findAll();
		} catch (Exception e) {
			log.error("finding all VtReporteTiempo failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "VtReporteTiempo");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveVtReporteTiempo(VtReporteTiempo entity) throws Exception {
		log.debug("saving VtReporteTiempo instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("VtReporteTiempo");
			}

			validateVtReporteTiempo(entity);

			vtReporteTiempoRepository.save(entity);
			log.debug("save VtReporteTiempo successful");
		} catch (Exception e) {
			log.error("save VtReporteTiempo failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteVtReporteTiempo(VtReporteTiempo entity) throws Exception {
		log.debug("deleting VtReporteTiempo instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("VtReporteTiempo");
		}

		if (entity.getRetiId() == null) {
			throw new ZMessManager().new EmptyFieldException("retiId");
		}

		try {
			vtReporteTiempoRepository.deleteById(entity.getRetiId());
			log.debug("delete VtReporteTiempo successful");
		} catch (Exception e) {
			log.error("delete VtReporteTiempo failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateVtReporteTiempo(VtReporteTiempo entity) throws Exception {
		log.debug("updating VtReporteTiempo instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("VtReporteTiempo");
			}

			validateVtReporteTiempo(entity);

			vtReporteTiempoRepository.update(entity);

			log.debug("update VtReporteTiempo successful");
		} catch (Exception e) {
			log.error("update VtReporteTiempo failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<VtReporteTiempoDTO> getDataVtReporteTiempo() throws Exception {
		try {
			List<VtReporteTiempo> vtReporteTiempo = vtReporteTiempoRepository.findAll();

			List<VtReporteTiempoDTO> vtReporteTiempoDTO = new ArrayList<VtReporteTiempoDTO>();

			for (VtReporteTiempo vtReporteTiempoTmp : vtReporteTiempo) {
				VtReporteTiempoDTO vtReporteTiempoDTO2 = vtReporteTiempoMapper
						.vtReporteTiempoToVtReporteTiempoDTO(vtReporteTiempoTmp);
				vtReporteTiempoDTO.add(vtReporteTiempoDTO2);
			}

			return vtReporteTiempoDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public VtReporteTiempo getVtReporteTiempo(Long retiId) throws Exception {
		log.debug("getting VtReporteTiempo instance");

		VtReporteTiempo entity = null;

		try {
			if (vtReporteTiempoRepository.findById(retiId).isPresent()) {
				entity = vtReporteTiempoRepository.findById(retiId).get();
			}
		} catch (Exception e) {
			log.error("get VtReporteTiempo failed", e);
			throw new ZMessManager().new FindingException("VtReporteTiempo");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<VtReporteTiempo> findPageVtReporteTiempo(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<VtReporteTiempo> entity = null;

		try {
			entity = vtReporteTiempoRepository.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("VtReporteTiempo Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberVtReporteTiempo() throws Exception {
		Long entity = null;

		try {
			entity = vtReporteTiempoRepository.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("VtReporteTiempo Count");
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
	public List<VtReporteTiempo> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<VtReporteTiempo> list = new ArrayList<VtReporteTiempo>();
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
			list = vtReporteTiempoRepository.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtReporteTiempoService#reporteEntreFechas(java.util.Date,
	 *      java.util.Date)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VtReporteTiempoDTO> reporteEntreFechas(Date fechaInicial, Date fechaFinal) throws Exception {
		List<VtReporteTiempoDTO> informe = null;
		try {
			if (fechaInicial == null) {
				throw new Exception("Seleccione la Fecha Inicial");
			}
			if (fechaFinal == null) {
				throw new Exception("Seleccione la Fecha Final");
			}
			if (fechaInicial.after(fechaFinal)) {
				throw new Exception("Fecha Inicial está después de la Fecha Final");
			}
			informe = vtInformesRepository.reporteEntreFechas(fechaInicial, fechaFinal);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return informe;
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtReporteTiempoService#horasReportadasHoy(java.util.Date,
	 *      java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public BigDecimal horasReportadasHoy(Date fecha, String email) throws Exception {
		BigDecimal horasReportadas = null;
		try {
			if (fecha == null) {
				fecha = new Date();
			}
			if (email == null) {
				throw new Exception("No ha llegado el usuario para consultar las horas reportadas");
			}
			horasReportadas = vtInformesRepository.horasReportadasHoy(fecha, email.trim().toLowerCase());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return horasReportadas;
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtReporteTiempoService#enviarCorreoNotificacionNoHaReportadoHoy(java.lang.String,
	 *      java.lang.String)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public void enviarCorreoNotificacionNoHaReportadoHoy(String destinatario, String mensaje) throws Exception {
		try {
			// Se envía un correo de notificación al usuario
			String[] to = { destinatario.trim().toLowerCase() };
			String subject = "[Vorteam] - Registro de Actividades";
			
			
			VtParametro rutaVorteam = vtParametroService.obtenerParametroPorCodigo(Constantes.URL_VORTEAM, Constantes.ESTADO_ACTIVO);
			if(rutaVorteam == null || rutaVorteam.getValor() == null || rutaVorteam.getValor().trim().equals("")) {
				throw new Exception("No se ha encontrado el parámetro "+Constantes.URL_VORTEAM);
			}
			String parametroRuta = rutaVorteam.getValor().trim();
			
			String mensajeRuta = "<br>" + 
					"Puedes reportar tiempo haciendo <a href=\""+parametroRuta+"\">clic aquí</a>";
			
			String mailText = "<div style=\"font-size: 12pt;\">" + mensaje + mensajeRuta + "</div>";

			File[] attachments = null;
			
			VtParametro usuario = vtParametroService.obtenerParametroPorCodigo(Constantes.US_NOTIFICACION, Constantes.ESTADO_ACTIVO);
			if(usuario == null || usuario.getValor() == null || usuario.getValor().trim().equals("")) {
				throw new Exception("No se ha encontrado el parámetro "+Constantes.US_NOTIFICACION);
			}
			
			VtParametro psw = vtParametroService.obtenerParametroPorCodigo(Constantes.PS_NOTIFICACION, Constantes.ESTADO_ACTIVO);
			if(psw == null || psw.getValor() == null || psw.getValor().trim().equals("")) {
				throw new Exception("No se ha encontrado el parámetro "+Constantes.PS_NOTIFICACION);
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

	/**
	 * @author Daniel Pareja Londoño
	 * @version ene. 25, 2019
	 *
	 * @see com.vortexbird.vorteam.service.VtReporteTiempoService#enviarCorreoNotificacionOcupacionPlantaDiaAnterior(java.lang.String, java.lang.String, java.io.File)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public void enviarCorreoNotificacionOcupacionPlantaDiaAnterior(String[] destinatarios, String mensaje, File adjunto) throws Exception {
		try {
			// Se envía un correo de notificación al usuario
			String subject = "[Vorteam] - Ocupación Planta";
			
			
			VtParametro rutaVorteam = vtParametroService.obtenerParametroPorCodigo(Constantes.URL_VORTEAM, Constantes.ESTADO_ACTIVO);
			if(rutaVorteam == null || rutaVorteam.getValor() == null || rutaVorteam.getValor().trim().equals("")) {
				throw new Exception("No se ha encontrado el parámetro "+Constantes.URL_VORTEAM);
			}
			String parametroRuta = rutaVorteam.getValor().trim();
			
			String mensajeRuta = "<br>" + 
					"Puedes validar el reporte haciendo <a href=\""+parametroRuta+"\">clic aquí</a>";
			
			String mailText = "<div style=\"font-size: 12pt;\">" + mensaje + mensajeRuta + "</div>";

			File[] attachments;
			if(adjunto != null) {
				attachments = new File[1];
				attachments[0] = adjunto;
			}else {
				attachments = null;
			}
			
			VtParametro usuario = vtParametroService.obtenerParametroPorCodigo(Constantes.US_NOTIFICACION, Constantes.ESTADO_ACTIVO);
			if(usuario == null || usuario.getValor() == null || usuario.getValor().trim().equals("")) {
				throw new Exception("No se ha encontrado el parámetro "+Constantes.US_NOTIFICACION);
			}
			
			VtParametro psw = vtParametroService.obtenerParametroPorCodigo(Constantes.PS_NOTIFICACION, Constantes.ESTADO_ACTIVO);
			if(psw == null || psw.getValor() == null || psw.getValor().trim().equals("")) {
				throw new Exception("No se ha encontrado el parámetro "+Constantes.PS_NOTIFICACION);
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

			SendMailUtility.sendMail(destinatarios, subject, mailText, attachments, null, properties, username, password);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	
	}
}
