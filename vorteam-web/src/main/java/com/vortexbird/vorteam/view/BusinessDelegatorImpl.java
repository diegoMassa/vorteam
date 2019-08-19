package com.vortexbird.vorteam.view;

import com.vortexbird.sentinel.dto.UsuarioDTO;
import com.vortexbird.vorteam.domain.VtActividad;
import com.vortexbird.vorteam.domain.VtAsignacion;
import com.vortexbird.vorteam.domain.VtClasificacionFinanciera;
import com.vortexbird.vorteam.domain.VtCliente;
import com.vortexbird.vorteam.domain.VtEstado;
import com.vortexbird.vorteam.domain.VtLineaNegocio;
import com.vortexbird.vorteam.domain.VtPersonas;
import com.vortexbird.vorteam.domain.VtProyCosto;
import com.vortexbird.vorteam.domain.VtProyecto;
import com.vortexbird.vorteam.domain.VtReporteTiempo;
import com.vortexbird.vorteam.domain.VtTipoActividad;
import com.vortexbird.vorteam.domain.VtTipoIdentificacion;
import com.vortexbird.vorteam.domain.ZcodeEntityAuditEvent;
import com.vortexbird.vorteam.dto.GrupoDTO;
import com.vortexbird.vorteam.dto.VtActividadDTO;
import com.vortexbird.vorteam.dto.VtAsignacionDTO;
import com.vortexbird.vorteam.dto.VtClasificacionFinancieraDTO;
import com.vortexbird.vorteam.dto.VtClienteDTO;
import com.vortexbird.vorteam.dto.VtEstadoDTO;
import com.vortexbird.vorteam.dto.VtLineaNegocioDTO;
import com.vortexbird.vorteam.dto.VtPersonasDTO;
import com.vortexbird.vorteam.dto.VtProyCostoDTO;
import com.vortexbird.vorteam.dto.VtProyectoDTO;
import com.vortexbird.vorteam.dto.VtReporteTiempoDTO;
import com.vortexbird.vorteam.dto.VtTipoActividadDTO;
import com.vortexbird.vorteam.dto.VtTipoIdentificacionDTO;
import com.vortexbird.vorteam.dto.ZcodeEntityAuditEventDTO;
import com.vortexbird.vorteam.mapper.VtProyectoMapper;
import com.vortexbird.vorteam.service.VtActividadService;
import com.vortexbird.vorteam.service.VtAsignacionService;
import com.vortexbird.vorteam.service.VtClasificacionFinancieraService;
import com.vortexbird.vorteam.service.VtClienteService;
import com.vortexbird.vorteam.service.VtClienteServiceImpl;
import com.vortexbird.vorteam.service.VtEstadoService;
import com.vortexbird.vorteam.service.VtLineaNegocioService;
import com.vortexbird.vorteam.service.VtPersonasService;
import com.vortexbird.vorteam.service.VtProyCostoService;
import com.vortexbird.vorteam.service.VtProyectoService;
import com.vortexbird.vorteam.service.VtReporteTiempoService;
import com.vortexbird.vorteam.service.VtReportesService;
import com.vortexbird.vorteam.service.VtTipoActividadService;
import com.vortexbird.vorteam.service.VtTipoIdentificacionService;
import com.vortexbird.vorteam.service.ZcodeEntityAuditEventService;
import com.vortexbird.vorteam.utility.Constantes.REPORT_OUTPUT_TYPE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;

import java.sql.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Use a Business Delegate to reduce coupling between presentation-tier clients
 * and business services. The Business Delegate hides the underlying
 * implementation details of the business service, such as lookup and access
 * details of the EJB architecture.
 *
 * The Business Delegate acts as a client-side business abstraction; it provides
 * an abstraction for, and thus hides, the implementation of the business
 * services. Using a Business Delegate reduces the coupling between
 * presentation-tier clients and the system's business services. Depending on
 * the implementation strategy, the Business Delegate may shield clients from
 * possible volatility in the implementation of the business service API.
 * Potentially, this reduces the number of changes that must be made to the
 * presentation-tier client code when the business service API or its underlying
 * implementation changes.
 *
 * However, interface methods in the Business Delegate may still require
 * modification if the underlying business service API changes. Admittedly,
 * though, it is more likely that changes will be made to the business service
 * rather than to the Business Delegate.
 *
 * Often, developers are skeptical when a design goal such as abstracting the
 * business layer causes additional upfront work in return for future gains.
 * However, using this pattern or its strategies results in only a small amount
 * of additional upfront work and provides considerable benefits. The main
 * benefit is hiding the details of the underlying service. For example, the
 * client can become transparent to naming and lookup services. The Business
 * Delegate also handles the exceptions from the business services, such as
 * java.rmi.Remote exceptions, Java Messages Service (JMS) exceptions and so on.
 * The Business Delegate may intercept such service level exceptions and
 * generate application level exceptions instead. Application level exceptions
 * are easier to handle by the clients, and may be user friendly. The Business
 * Delegate may also transparently perform any retry or recovery operations
 * necessary in the event of a service failure without exposing the client to
 * the problem until it is determined that the problem is not resolvable. These
 * gains present a compelling reason to use the pattern.
 *
 * Another benefit is that the delegate may cache results and references to
 * remote business services. Caching can significantly improve performance,
 * because it limits unnecessary and potentially costly round trips over the
 * network.
 *
 * A Business Delegate uses a component called the Lookup Service. The Lookup
 * Service is responsible for hiding the underlying implementation details of
 * the business service lookup code. The Lookup Service may be written as part
 * of the Delegate, but we recommend that it be implemented as a separate
 * component, as outlined in the Service Locator pattern (See "Service Locator"
 * on page 368.)
 *
 * When the Business Delegate is used with a Session Facade, typically there is
 * a one-to-one relationship between the two. This one-to-one relationship
 * exists because logic that might have been encapsulated in a Business Delegate
 * relating to its interaction with multiple business services (creating a
 * one-to-many relationship) will often be factored back into a Session Facade.
 *
 * Finally, it should be noted that this pattern could be used to reduce
 * coupling between other tiers, not simply the presentation and the business
 * tiers.
 *
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Scope("singleton")
@Component("businessDelegator")
public class BusinessDelegatorImpl implements BusinessDelegator {
	private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorImpl.class);
	@Autowired
	private VtActividadService vtActividadService;
	@Autowired
	private VtAsignacionService vtAsignacionService;
	@Autowired
	private VtClienteService vtClienteService;
	@Autowired
	private VtEstadoService vtEstadoService;
	@Autowired
	private VtLineaNegocioService vtLineaNegocioService;
	@Autowired
	private VtPersonasService vtPersonasService;
	@Autowired
	private VtProyCostoService vtProyCostoService;
	@Autowired
	private VtProyectoService vtProyectoService;
	@Autowired
	private VtReporteTiempoService vtReporteTiempoService;
	@Autowired
	private VtTipoActividadService vtTipoActividadService;
	@Autowired
	private VtTipoIdentificacionService vtTipoIdentificacionService;
	@Autowired
	private ZcodeEntityAuditEventService zcodeEntityAuditEventService;
	@Autowired
	private VtProyectoMapper vtProyectoMapper;
	@Autowired
	private VtReportesService vtReportesService;
	@Autowired
	private VtClasificacionFinancieraService vtClasificacionFinancieraService;

	public List<VtActividad> getVtActividad() throws Exception {
		return vtActividadService.getVtActividad();
	}

	public void saveVtActividad(VtActividad entity) throws Exception {
		vtActividadService.saveVtActividad(entity);
	}

	public void deleteVtActividad(VtActividad entity) throws Exception {
		vtActividadService.deleteVtActividad(entity);
	}

	public void updateVtActividad(VtActividad entity) throws Exception {
		vtActividadService.updateVtActividad(entity);
	}

	public VtActividad getVtActividad(Long actiId) throws Exception {
		VtActividad vtActividad = null;

		try {
			vtActividad = vtActividadService.getVtActividad(actiId);
		} catch (Exception e) {
			throw e;
		}

		return vtActividad;
	}

	public List<VtActividad> findByCriteriaInVtActividad(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return vtActividadService.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<VtActividad> findPageVtActividad(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return vtActividadService.findPageVtActividad(sortColumnName, sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberVtActividad() throws Exception {
		return vtActividadService.findTotalNumberVtActividad();
	}

	public List<VtActividadDTO> getDataVtActividad() throws Exception {
		return vtActividadService.getDataVtActividad();
	}

	public void validateVtActividad(VtActividad vtActividad) throws Exception {
		vtActividadService.validateVtActividad(vtActividad);
	}

	public List<VtAsignacion> getVtAsignacion() throws Exception {
		return vtAsignacionService.getVtAsignacion();
	}

	public void saveVtAsignacion(VtAsignacion entity) throws Exception {
		vtAsignacionService.saveVtAsignacion(entity);
	}

	public void deleteVtAsignacion(VtAsignacion entity) throws Exception {
		vtAsignacionService.deleteVtAsignacion(entity);
	}

	public void updateVtAsignacion(VtAsignacion entity) throws Exception {
		vtAsignacionService.updateVtAsignacion(entity);
	}

	public VtAsignacion getVtAsignacion(Long asigId) throws Exception {
		VtAsignacion vtAsignacion = null;

		try {
			vtAsignacion = vtAsignacionService.getVtAsignacion(asigId);
		} catch (Exception e) {
			throw e;
		}

		return vtAsignacion;
	}

	public List<VtAsignacion> findByCriteriaInVtAsignacion(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return vtAsignacionService.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<VtAsignacion> findPageVtAsignacion(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return vtAsignacionService.findPageVtAsignacion(sortColumnName, sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberVtAsignacion() throws Exception {
		return vtAsignacionService.findTotalNumberVtAsignacion();
	}

	public List<VtAsignacionDTO> getDataVtAsignacion() throws Exception {
		return vtAsignacionService.getDataVtAsignacion();
	}

	public void validateVtAsignacion(VtAsignacion vtAsignacion) throws Exception {
		vtAsignacionService.validateVtAsignacion(vtAsignacion);
	}

	public List<VtCliente> getVtCliente() throws Exception {
		return vtClienteService.getVtCliente();
	}

	public void saveVtCliente(VtCliente entity) throws Exception {
		vtClienteService.saveVtCliente(entity);
	}

	public void deleteVtCliente(VtCliente entity) throws Exception {
		vtClienteService.deleteVtCliente(entity);
	}

	public void updateVtCliente(VtCliente entity) throws Exception {
		vtClienteService.updateVtCliente(entity);
	}

	public VtCliente getVtCliente(Long clieId) throws Exception {
		VtCliente vtCliente = null;

		try {
			vtCliente = vtClienteService.getVtCliente(clieId);
		} catch (Exception e) {
			throw e;
		}

		return vtCliente;
	}

	public List<VtCliente> findByCriteriaInVtCliente(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return vtClienteService.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<VtCliente> findPageVtCliente(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return vtClienteService.findPageVtCliente(sortColumnName, sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberVtCliente() throws Exception {
		return vtClienteService.findTotalNumberVtCliente();
	}

	public List<VtClienteDTO> getDataVtCliente() throws Exception {
		return vtClienteService.getDataVtCliente();
	}

	public void validateVtCliente(VtCliente vtCliente) throws Exception {
		vtClienteService.validateVtCliente(vtCliente);
	}

	public List<VtEstado> getVtEstado() throws Exception {
		return vtEstadoService.getVtEstado();
	}

	public void saveVtEstado(VtEstado entity) throws Exception {
		vtEstadoService.saveVtEstado(entity);
	}

	public void deleteVtEstado(VtEstado entity) throws Exception {
		vtEstadoService.deleteVtEstado(entity);
	}

	public void updateVtEstado(VtEstado entity) throws Exception {
		vtEstadoService.updateVtEstado(entity);
	}

	public VtEstado getVtEstado(Long estaId) throws Exception {
		VtEstado vtEstado = null;

		try {
			vtEstado = vtEstadoService.getVtEstado(estaId);
		} catch (Exception e) {
			throw e;
		}

		return vtEstado;
	}

	public List<VtEstado> findByCriteriaInVtEstado(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return vtEstadoService.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<VtEstado> findPageVtEstado(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return vtEstadoService.findPageVtEstado(sortColumnName, sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberVtEstado() throws Exception {
		return vtEstadoService.findTotalNumberVtEstado();
	}

	public List<VtEstadoDTO> getDataVtEstado() throws Exception {
		return vtEstadoService.getDataVtEstado();
	}

	public void validateVtEstado(VtEstado vtEstado) throws Exception {
		vtEstadoService.validateVtEstado(vtEstado);
	}

	public List<VtPersonas> getVtPersonas() throws Exception {
		return vtPersonasService.getVtPersonas();
	}

	public void saveVtPersonas(VtPersonas entity) throws Exception {
		vtPersonasService.saveVtPersonas(entity);
	}

	public void deleteVtPersonas(VtPersonas entity) throws Exception {
		vtPersonasService.deleteVtPersonas(entity);
	}

	public void updateVtPersonas(VtPersonas entity) throws Exception {
		vtPersonasService.updateVtPersonas(entity);
	}

	public VtPersonas getVtPersonas(Long persId) throws Exception {
		VtPersonas vtPersonas = null;

		try {
			vtPersonas = vtPersonasService.getVtPersonas(persId);
		} catch (Exception e) {
			throw e;
		}

		return vtPersonas;
	}

	public List<VtPersonas> findByCriteriaInVtPersonas(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return vtPersonasService.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<VtPersonas> findPageVtPersonas(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return vtPersonasService.findPageVtPersonas(sortColumnName, sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberVtPersonas() throws Exception {
		return vtPersonasService.findTotalNumberVtPersonas();
	}

	public List<VtPersonasDTO> getDataVtPersonas() throws Exception {
		return vtPersonasService.getDataVtPersonas();
	}

	public void validateVtPersonas(VtPersonas vtPersonas) throws Exception {
		vtPersonasService.validateVtPersonas(vtPersonas);
	}

	public List<VtProyCosto> getVtProyCosto() throws Exception {
		return vtProyCostoService.getVtProyCosto();
	}

	public void saveVtProyCosto(VtProyCosto entity) throws Exception {
		vtProyCostoService.saveVtProyCosto(entity);
	}

	public void deleteVtProyCosto(VtProyCosto entity) throws Exception {
		vtProyCostoService.deleteVtProyCosto(entity);
	}

	public void updateVtProyCosto(VtProyCosto entity) throws Exception {
		vtProyCostoService.updateVtProyCosto(entity);
	}

	public VtProyCosto getVtProyCosto(Long prcoId) throws Exception {
		VtProyCosto vtProyCosto = null;

		try {
			vtProyCosto = vtProyCostoService.getVtProyCosto(prcoId);
		} catch (Exception e) {
			throw e;
		}

		return vtProyCosto;
	}

	public List<VtProyCosto> findByCriteriaInVtProyCosto(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return vtProyCostoService.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<VtProyCosto> findPageVtProyCosto(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return vtProyCostoService.findPageVtProyCosto(sortColumnName, sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberVtProyCosto() throws Exception {
		return vtProyCostoService.findTotalNumberVtProyCosto();
	}

	public List<VtProyCostoDTO> getDataVtProyCosto() throws Exception {
		return vtProyCostoService.getDataVtProyCosto();
	}

	public void validateVtProyCosto(VtProyCosto vtProyCosto) throws Exception {
		vtProyCostoService.validateVtProyCosto(vtProyCosto);
	}

	public List<VtProyecto> getVtProyecto() throws Exception {
		return vtProyectoService.getVtProyecto();
	}

	public void saveVtProyecto(VtProyecto entity) throws Exception {
		vtProyectoService.saveVtProyecto(entity);
	}

	public void deleteVtProyecto(VtProyecto entity) throws Exception {
		vtProyectoService.deleteVtProyecto(entity);
	}

	public void updateVtProyecto(VtProyecto entity) throws Exception {
		vtProyectoService.updateVtProyecto(entity);
	}

	public VtProyecto getVtProyecto(Long proyId) throws Exception {
		VtProyecto vtProyecto = null;

		try {
			vtProyecto = vtProyectoService.getVtProyecto(proyId);
		} catch (Exception e) {
			throw e;
		}

		return vtProyecto;
	}

	public List<VtProyecto> findByCriteriaInVtProyecto(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return vtProyectoService.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<VtProyecto> findPageVtProyecto(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return vtProyectoService.findPageVtProyecto(sortColumnName, sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberVtProyecto() throws Exception {
		return vtProyectoService.findTotalNumberVtProyecto();
	}

	public List<VtProyectoDTO> getDataVtProyecto() throws Exception {
		return vtProyectoService.getDataVtProyecto();
	}

	public void validateVtProyecto(VtProyecto vtProyecto) throws Exception {
		vtProyectoService.validateVtProyecto(vtProyecto);
	}

	public List<VtReporteTiempo> getVtReporteTiempo() throws Exception {
		return vtReporteTiempoService.getVtReporteTiempo();
	}

	public void saveVtReporteTiempo(VtReporteTiempo entity) throws Exception {
		vtReporteTiempoService.saveVtReporteTiempo(entity);
	}

	public void deleteVtReporteTiempo(VtReporteTiempo entity) throws Exception {
		vtReporteTiempoService.deleteVtReporteTiempo(entity);
	}

	public void updateVtReporteTiempo(VtReporteTiempo entity) throws Exception {
		vtReporteTiempoService.updateVtReporteTiempo(entity);
	}

	public VtReporteTiempo getVtReporteTiempo(Long retiId) throws Exception {
		VtReporteTiempo vtReporteTiempo = null;

		try {
			vtReporteTiempo = vtReporteTiempoService.getVtReporteTiempo(retiId);
		} catch (Exception e) {
			throw e;
		}

		return vtReporteTiempo;
	}

	public List<VtReporteTiempo> findByCriteriaInVtReporteTiempo(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return vtReporteTiempoService.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<VtReporteTiempo> findPageVtReporteTiempo(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return vtReporteTiempoService.findPageVtReporteTiempo(sortColumnName, sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberVtReporteTiempo() throws Exception {
		return vtReporteTiempoService.findTotalNumberVtReporteTiempo();
	}

	public List<VtReporteTiempoDTO> getDataVtReporteTiempo() throws Exception {
		return vtReporteTiempoService.getDataVtReporteTiempo();
	}

	public void validateVtReporteTiempo(VtReporteTiempo vtReporteTiempo) throws Exception {
		vtReporteTiempoService.validateVtReporteTiempo(vtReporteTiempo);
	}

	public List<VtTipoActividad> getVtTipoActividad() throws Exception {
		return vtTipoActividadService.getVtTipoActividad();
	}

	public void saveVtTipoActividad(VtTipoActividad entity) throws Exception {
		vtTipoActividadService.saveVtTipoActividad(entity);
	}

	public void deleteVtTipoActividad(VtTipoActividad entity) throws Exception {
		vtTipoActividadService.deleteVtTipoActividad(entity);
	}

	public void updateVtTipoActividad(VtTipoActividad entity) throws Exception {
		vtTipoActividadService.updateVtTipoActividad(entity);
	}

	public VtTipoActividad getVtTipoActividad(Long tiacId) throws Exception {
		VtTipoActividad vtTipoActividad = null;

		try {
			vtTipoActividad = vtTipoActividadService.getVtTipoActividad(tiacId);
		} catch (Exception e) {
			throw e;
		}

		return vtTipoActividad;
	}

	public List<VtTipoActividad> findByCriteriaInVtTipoActividad(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return vtTipoActividadService.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<VtTipoActividad> findPageVtTipoActividad(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return vtTipoActividadService.findPageVtTipoActividad(sortColumnName, sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberVtTipoActividad() throws Exception {
		return vtTipoActividadService.findTotalNumberVtTipoActividad();
	}

	public List<VtTipoActividadDTO> getDataVtTipoActividad() throws Exception {
		return vtTipoActividadService.getDataVtTipoActividad();
	}

	public void validateVtTipoActividad(VtTipoActividad vtTipoActividad) throws Exception {
		vtTipoActividadService.validateVtTipoActividad(vtTipoActividad);
	}

	public List<VtTipoIdentificacion> getVtTipoIdentificacion() throws Exception {
		return vtTipoIdentificacionService.getVtTipoIdentificacion();
	}

	public void saveVtTipoIdentificacion(VtTipoIdentificacion entity) throws Exception {
		vtTipoIdentificacionService.saveVtTipoIdentificacion(entity);
	}

	public void deleteVtTipoIdentificacion(VtTipoIdentificacion entity) throws Exception {
		vtTipoIdentificacionService.deleteVtTipoIdentificacion(entity);
	}

	public void updateVtTipoIdentificacion(VtTipoIdentificacion entity) throws Exception {
		vtTipoIdentificacionService.updateVtTipoIdentificacion(entity);
	}

	public VtTipoIdentificacion getVtTipoIdentificacion(Long tiidId) throws Exception {
		VtTipoIdentificacion vtTipoIdentificacion = null;

		try {
			vtTipoIdentificacion = vtTipoIdentificacionService.getVtTipoIdentificacion(tiidId);
		} catch (Exception e) {
			throw e;
		}

		return vtTipoIdentificacion;
	}

	public List<VtTipoIdentificacion> findByCriteriaInVtTipoIdentificacion(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
		return vtTipoIdentificacionService.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<VtTipoIdentificacion> findPageVtTipoIdentificacion(String sortColumnName, boolean sortAscending,
			int startRow, int maxResults) throws Exception {
		return vtTipoIdentificacionService.findPageVtTipoIdentificacion(sortColumnName, sortAscending, startRow,
				maxResults);
	}

	public Long findTotalNumberVtTipoIdentificacion() throws Exception {
		return vtTipoIdentificacionService.findTotalNumberVtTipoIdentificacion();
	}

	public List<VtTipoIdentificacionDTO> getDataVtTipoIdentificacion() throws Exception {
		return vtTipoIdentificacionService.getDataVtTipoIdentificacion();
	}

	public void validateVtTipoIdentificacion(VtTipoIdentificacion vtTipoIdentificacion) throws Exception {
		vtTipoIdentificacionService.validateVtTipoIdentificacion(vtTipoIdentificacion);
	}

	public List<ZcodeEntityAuditEvent> getZcodeEntityAuditEvent() throws Exception {
		return zcodeEntityAuditEventService.getZcodeEntityAuditEvent();
	}

	public void saveZcodeEntityAuditEvent(ZcodeEntityAuditEvent entity) throws Exception {
		zcodeEntityAuditEventService.saveZcodeEntityAuditEvent(entity);
	}

	public void deleteZcodeEntityAuditEvent(ZcodeEntityAuditEvent entity) throws Exception {
		zcodeEntityAuditEventService.deleteZcodeEntityAuditEvent(entity);
	}

	public void updateZcodeEntityAuditEvent(ZcodeEntityAuditEvent entity) throws Exception {
		zcodeEntityAuditEventService.updateZcodeEntityAuditEvent(entity);
	}

	public ZcodeEntityAuditEvent getZcodeEntityAuditEvent(Long id) throws Exception {
		ZcodeEntityAuditEvent zcodeEntityAuditEvent = null;

		try {
			zcodeEntityAuditEvent = zcodeEntityAuditEventService.getZcodeEntityAuditEvent(id);
		} catch (Exception e) {
			throw e;
		}

		return zcodeEntityAuditEvent;
	}

	public List<ZcodeEntityAuditEvent> findByCriteriaInZcodeEntityAuditEvent(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
		return zcodeEntityAuditEventService.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<ZcodeEntityAuditEvent> findPageZcodeEntityAuditEvent(String sortColumnName, boolean sortAscending,
			int startRow, int maxResults) throws Exception {
		return zcodeEntityAuditEventService.findPageZcodeEntityAuditEvent(sortColumnName, sortAscending, startRow,
				maxResults);
	}

	public Long findTotalNumberZcodeEntityAuditEvent() throws Exception {
		return zcodeEntityAuditEventService.findTotalNumberZcodeEntityAuditEvent();
	}

	public List<ZcodeEntityAuditEventDTO> getDataZcodeEntityAuditEvent() throws Exception {
		return zcodeEntityAuditEventService.getDataZcodeEntityAuditEvent();
	}

	public void validateZcodeEntityAuditEvent(ZcodeEntityAuditEvent zcodeEntityAuditEvent) throws Exception {
		zcodeEntityAuditEventService.validateZcodeEntityAuditEvent(zcodeEntityAuditEvent);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 01, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#clientesConProyectos()
	 *
	 */
	@Override
	public List<VtClienteDTO> clientesConProyectos() throws Exception {
		return vtClienteService.clientesConProyectos();
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#consultaProyectosClientes()
	 *
	 */
	@Override
	public List<VtProyectoDTO> consultaProyectosClientes(String activo) throws Exception {
		return vtProyectoService.consultaProyectosClientes(activo);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#consultaProyectosClientesRecursos(java.lang.String)
	 *
	 */
	@Override
	public List<VtProyectoDTO> consultaProyectosClientesRecursos(String activo) throws Exception {
		return vtProyectoService.consultaProyectosClientesRecursos(activo);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#guardarProyectoConCostosPersonas(com.vortexbird.vorteam.domain.VtProyecto,
	 *      java.util.List)
	 *
	 */
	@Override
	public void guardarProyectoConCostosPersonas(VtProyecto proyecto, List<VtPersonasDTO> personas) throws Exception {
		vtProyectoService.guardarProyectoConCostosPersonas(proyecto, personas);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#actualizarProyectoConCostosPersonas(com.vortexbird.vorteam.domain.VtProyecto,
	 *      java.util.List)
	 *
	 */
	@Override
	public void actualizarProyectoConCostosPersonas(VtProyecto proyecto, List<VtPersonasDTO> personas)
			throws Exception {
		vtProyectoService.actualizarProyectoConCostosPersonas(proyecto, personas);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#consultaPersonasProyectos(java.lang.String)
	 *
	 */
	@Override
	public List<VtPersonasDTO> consultaPersonasProyectos(String activo) throws Exception {
		return vtPersonasService.consultaPersonasProyectos(activo);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#consultaPersonasProyecto(java.lang.String,
	 *      java.lang.Long)
	 *
	 */
	@Override
	public List<VtPersonasDTO> consultaPersonasProyecto(String activo, Long proyId) throws Exception {
		return vtPersonasService.consultaPersonasProyecto(activo, proyId);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#getDataVtLineaNegocio()
	 *
	 */
	@Override
	public List<VtLineaNegocioDTO> getDataVtLineaNegocio() throws Exception {
		return vtLineaNegocioService.getDataVtLineaNegocio();
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#getVtLineaNegocio(java.lang.Long)
	 *
	 */
	@Override
	public VtLineaNegocio getVtLineaNegocio(Long lineId) throws Exception {
		return vtLineaNegocioService.getVtLineaNegocio(lineId);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#consultaPersonas(java.lang.String)
	 *
	 */
	@Override
	public List<VtPersonasDTO> consultaPersonas(String activo) throws Exception {
		return vtPersonasService.consultaPersonas(activo);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#guardarActividadConAsignacionesPersonas(com.vortexbird.vorteam.domain.VtActividad,
	 *      java.util.List)
	 *
	 */
	@Override
	public String guardarActividadConAsignacionesPersonas(VtActividad actividad, List<VtPersonasDTO> personas)
			throws Exception {
		return vtActividadService.guardarActividadConAsignacionesPersonas(actividad, personas);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#consultaActividadesOrdenadaFechaDescendiente(java.lang.String)
	 *
	 */
	@Override
	public List<VtActividadDTO> consultaActividadesOrdenadaFechaDescendiente(String activo) throws Exception {
		return vtActividadService.consultaActividadesOrdenadaFechaDescendiente(activo);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#listVtProyectoToListVtProyectoDTO(java.util.List)
	 *
	 */
	@Override
	public List<VtProyectoDTO> listVtProyectoToListVtProyectoDTO(List<VtProyecto> vtProyectos) throws Exception {
		return vtProyectoMapper.listVtProyectoToListVtProyectoDTO(vtProyectos);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#guardarPersonasConCostosProyecto(com.vortexbird.vorteam.dto.VtPersonasDTO,
	 *      java.util.List)
	 *
	 */
	@Override
	public void guardarPersonasConCostosProyecto(VtPersonasDTO persona, List<VtProyectoDTO> proyectos)
			throws Exception {
		vtPersonasService.guardarPersonasConCostosProyecto(persona, proyectos);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#actualizarPersonasConCostosProyecto(com.vortexbird.vorteam.dto.VtPersonasDTO,
	 *      java.util.List)
	 *
	 */
	@Override
	public void actualizarPersonasConCostosProyecto(VtPersonasDTO persona, List<VtProyectoDTO> proyectos)
			throws Exception {
		vtPersonasService.actualizarPersonasConCostosProyecto(persona, proyectos);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#consultaProyectos(java.lang.String)
	 *
	 */
	@Override
	public List<VtProyectoDTO> consultaProyectos(String activo) throws Exception {
		return vtProyectoService.consultaProyectos(activo);
	}

	@Override
	public UsuarioDTO autenticar(String userId, String password) throws Exception {
		return vtPersonasService.autenticar(userId, password);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#consultaPersonasActividad(java.lang.Long,
	 *      java.lang.String)
	 *
	 */
	@Override
	public List<VtPersonasDTO> consultaPersonasActividad(Long actiId, String activo) throws Exception {
		return vtPersonasService.consultaPersonasActividad(actiId, activo);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#consultaMisActividades(java.lang.String,
	 *      java.lang.String)
	 *
	 */
	@Override
	public List<VtActividadDTO> consultaMisActividades(String usuario, String activo) throws Exception {
		return vtActividadService.consultaMisActividades(usuario, activo);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#consultarOpcionesDeUsuarioPorSistemaSucursalYCompania(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 *
	 */
	@Override
	public List<GrupoDTO> consultarOpcionesDeUsuarioPorSistemaSucursalYCompania(String login, String sistema,
			String sucursal, String compania) throws Exception {
		return vtPersonasService.consultarOpcionesDeUsuarioPorSistemaSucursalYCompania(login, sistema, sucursal,
				compania);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 15, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#consultaProyectosActivosPersona(java.lang.Long,
	 *      java.lang.String)
	 *
	 */
	@Override
	public List<VtProyectoDTO> consultaProyectosActivosPersona(Long persId, String activo) throws Exception {
		return vtProyectoService.consultaProyectosActivosPersona(persId, activo);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 15, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#consultarPersonaPorCorreo(java.lang.String)
	 *
	 */
	@Override
	public VtPersonas consultarPersonaPorCorreo(String correo) throws Exception {
		return vtPersonasService.consultarPersonaPorCorreo(correo);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#reporteEntreFechas(java.util.Date,
	 *      java.util.Date)
	 *
	 */
	@Override
	public List<VtReporteTiempoDTO> reporteEntreFechas(Date fechaInicial, Date fechaFinal) throws Exception {
		return vtReporteTiempoService.reporteEntreFechas(fechaInicial, fechaFinal);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#generarReporteTiemposEntreFechas(java.util.Date,
	 *      java.util.Date,
	 *      com.vortexbird.vorteam.utility.Constantes.REPORT_OUTPUT_TYPE)
	 *
	 */
	@Override
	public ByteArrayInputStream generarReporteTiemposEntreFechas(Date fechaInicial, Date fechaFinal,
			REPORT_OUTPUT_TYPE reportOutputTye) throws Exception {
		return vtReportesService.generarReporteTiemposEntreFechas(fechaInicial, fechaFinal, reportOutputTye);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#horasReportadasHoy(java.util.Date,
	 *      java.lang.String)
	 *
	 */
	@Override
	public BigDecimal horasReportadasHoy(Date fecha, String email) throws Exception {
		return vtReporteTiempoService.horasReportadasHoy(fecha, email);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 20, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#generarReporteCostosPorProyectos(java.util.Date,
	 *      java.util.Date, java.lang.Long, java.lang.Long, java.lang.Long,
	 *      com.vortexbird.vorteam.utility.Constantes.REPORT_OUTPUT_TYPE)
	 *
	 */
	@Override
	public ByteArrayInputStream generarReporteCostosPorProyectos(Date fechaInicial, Date fechaFinal, Long persId,
			Long proyId, Long tiacId, REPORT_OUTPUT_TYPE reportOutputTye) throws Exception {
		return vtReportesService.generarReporteCostosPorProyectos(fechaInicial, fechaFinal, persId, proyId, tiacId,
				reportOutputTye);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 21, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#generarReporteOcupacionPlanta(java.util.Date,
	 *      java.util.Date, java.lang.Long, java.lang.Long, java.lang.Long,
	 *      com.vortexbird.vorteam.utility.Constantes.REPORT_OUTPUT_TYPE)
	 *
	 */
	@Override
	public ByteArrayInputStream generarReporteOcupacionPlanta(Date fechaInicial, Date fechaFinal, Long persId,
			Long proyId, Long tiacId, REPORT_OUTPUT_TYPE reportOutputTye) throws Exception {
		return vtReportesService.generarReporteOcupacionPlanta(fechaInicial, fechaFinal, persId, proyId, tiacId,
				reportOutputTye);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 21, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#generarReporteCostosPorPersona(java.util.Date,
	 *      java.util.Date, java.lang.Long, java.lang.Long, java.lang.Long,
	 *      com.vortexbird.vorteam.utility.Constantes.REPORT_OUTPUT_TYPE)
	 *
	 */
	@Override
	public ByteArrayInputStream generarReporteCostosPorPersona(Date fechaInicial, Date fechaFinal, Long persId,
			Long proyId, Long tiacId, REPORT_OUTPUT_TYPE reportOutputTye) throws Exception {
		return vtReportesService.generarReporteCostosPorPersona(fechaInicial, fechaFinal, persId, proyId, tiacId,
				reportOutputTye);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 21, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#cambiarClave(com.vortexbird.vorteam.dto.VtPersonasDTO, com.vortexbird.sentinel.dto.UsuarioDTO)
	 *
	 */
	@Override
	public void cambiarClave(VtPersonasDTO personaDTO, UsuarioDTO usuarioDTO) throws Exception {
		vtPersonasService.cambiarClave(personaDTO, usuarioDTO);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 21, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#generarReporteCostoAcumuladoProyecto(java.lang.Long, com.vortexbird.vorteam.utility.Constantes.REPORT_OUTPUT_TYPE)
	 *
	 */
	@Override
	public ByteArrayInputStream generarReporteCostoAcumuladoProyecto(Long proyId, REPORT_OUTPUT_TYPE reportOutputTye)
			throws Exception {
		return vtReportesService.generarReporteCostoAcumuladoProyecto(proyId, reportOutputTye);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#tiposActividadesConClasificacionFinanciera()
	 *
	 */
	@Override
	public List<VtTipoActividadDTO> tiposActividadesConClasificacionFinanciera() throws Exception {
		return vtTipoActividadService.tiposActividadesConClasificacionFinanciera();
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#getDataVtClasificacionFinanciera()
	 *
	 */
	@Override
	public List<VtClasificacionFinancieraDTO> getDataVtClasificacionFinanciera() throws Exception {
		return vtClasificacionFinancieraService.getDataVtClasificacionFinanciera();
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#getVtClasificacionFinanciera(java.lang.Long)
	 *
	 */
	@Override
	public VtClasificacionFinanciera getVtClasificacionFinanciera(Long clfiId) throws Exception {
		return vtClasificacionFinancieraService.getVtClasificacionFinanciera(clfiId);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#tiposActividadesPorEstado(java.lang.String)
	 *
	 */
	@Override
	public List<VtTipoActividadDTO> tiposActividadesPorEstado(String activo) throws Exception {
		return vtTipoActividadService.tiposActividadesPorEstado(activo);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version oct. 01, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#restaurarClave(java.lang.String)
	 *
	 */
	@Override
	public void restaurarClave(String login) throws Exception {
		vtPersonasService.restaurarClave(login);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#consultaMisActividadesLazy(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, int, int)
	 *
	 */
	@Override
	public List<VtActividadDTO> consultaMisActividadesLazy(String usuario, String activo, Long proyId, Long estaId,
			Long tiacId, String sprint, String casoSoporte, String controlCambios, int first, int pageSize)
			throws Exception {
		return vtActividadService.consultaMisActividadesLazy(usuario, activo, proyId, estaId, tiacId, sprint, casoSoporte, controlCambios, first, pageSize);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#consultaTotalMisActividadesLazy(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 *
	 */
	@Override
	public Long consultaTotalMisActividadesLazy(String usuario, String activo, Long proyId, Long estaId, Long tiacId,
			String sprint, String casoSoporte, String controlCambios) throws Exception {
		return vtActividadService.consultaTotalMisActividadesLazy(usuario, activo, proyId, estaId, tiacId, sprint, casoSoporte, controlCambios);
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version oct. 05, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.view.BusinessDelegator#consultaProyectosTodosPersona(java.lang.Long, java.lang.String)
	 *
	 */
	@Override
	public List<VtProyectoDTO> consultaProyectosTodosPersona(Long persId, String activo) throws Exception {
		return vtProyectoService.consultaProyectosTodosPersona(persId, activo);
	}
}
