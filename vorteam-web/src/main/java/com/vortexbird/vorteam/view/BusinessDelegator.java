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
import com.vortexbird.vorteam.utility.Constantes.REPORT_OUTPUT_TYPE;

import java.io.ByteArrayInputStream;
import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface BusinessDelegator {
    public List<VtActividad> getVtActividad() throws Exception;

    public void saveVtActividad(VtActividad entity) throws Exception;

    public void deleteVtActividad(VtActividad entity) throws Exception;

    public void updateVtActividad(VtActividad entity) throws Exception;

    public VtActividad getVtActividad(Long actiId) throws Exception;

    public List<VtActividad> findByCriteriaInVtActividad(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtActividad> findPageVtActividad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtActividad() throws Exception;

    public List<VtActividadDTO> getDataVtActividad() throws Exception;

    public void validateVtActividad(VtActividad vtActividad)
        throws Exception;

    public List<VtAsignacion> getVtAsignacion() throws Exception;

    public void saveVtAsignacion(VtAsignacion entity) throws Exception;

    public void deleteVtAsignacion(VtAsignacion entity)
        throws Exception;

    public void updateVtAsignacion(VtAsignacion entity)
        throws Exception;

    public VtAsignacion getVtAsignacion(Long asigId) throws Exception;

    public List<VtAsignacion> findByCriteriaInVtAsignacion(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtAsignacion> findPageVtAsignacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtAsignacion() throws Exception;

    public List<VtAsignacionDTO> getDataVtAsignacion()
        throws Exception;

    public void validateVtAsignacion(VtAsignacion vtAsignacion)
        throws Exception;

    public List<VtCliente> getVtCliente() throws Exception;

    public void saveVtCliente(VtCliente entity) throws Exception;

    public void deleteVtCliente(VtCliente entity) throws Exception;

    public void updateVtCliente(VtCliente entity) throws Exception;

    public VtCliente getVtCliente(Long clieId) throws Exception;

    public List<VtCliente> findByCriteriaInVtCliente(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtCliente> findPageVtCliente(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtCliente() throws Exception;

    public List<VtClienteDTO> getDataVtCliente() throws Exception;

    public void validateVtCliente(VtCliente vtCliente)
        throws Exception;

    public List<VtEstado> getVtEstado() throws Exception;

    public void saveVtEstado(VtEstado entity) throws Exception;

    public void deleteVtEstado(VtEstado entity) throws Exception;

    public void updateVtEstado(VtEstado entity) throws Exception;

    public VtEstado getVtEstado(Long estaId) throws Exception;

    public List<VtEstado> findByCriteriaInVtEstado(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtEstado> findPageVtEstado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtEstado() throws Exception;

    public List<VtEstadoDTO> getDataVtEstado() throws Exception;

    public void validateVtEstado(VtEstado vtEstado) throws Exception;

    public List<VtPersonas> getVtPersonas() throws Exception;

    public void saveVtPersonas(VtPersonas entity) throws Exception;

    public void deleteVtPersonas(VtPersonas entity) throws Exception;

    public void updateVtPersonas(VtPersonas entity) throws Exception;

    public VtPersonas getVtPersonas(Long persId) throws Exception;

    public List<VtPersonas> findByCriteriaInVtPersonas(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtPersonas> findPageVtPersonas(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtPersonas() throws Exception;

    public List<VtPersonasDTO> getDataVtPersonas() throws Exception;

    public void validateVtPersonas(VtPersonas vtPersonas)
        throws Exception;

    public List<VtProyCosto> getVtProyCosto() throws Exception;

    public void saveVtProyCosto(VtProyCosto entity) throws Exception;

    public void deleteVtProyCosto(VtProyCosto entity) throws Exception;

    public void updateVtProyCosto(VtProyCosto entity) throws Exception;

    public VtProyCosto getVtProyCosto(Long prcoId) throws Exception;

    public List<VtProyCosto> findByCriteriaInVtProyCosto(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtProyCosto> findPageVtProyCosto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtProyCosto() throws Exception;

    public List<VtProyCostoDTO> getDataVtProyCosto() throws Exception;

    public void validateVtProyCosto(VtProyCosto vtProyCosto)
        throws Exception;

    public List<VtProyecto> getVtProyecto() throws Exception;

    public void saveVtProyecto(VtProyecto entity) throws Exception;

    public void deleteVtProyecto(VtProyecto entity) throws Exception;

    public void updateVtProyecto(VtProyecto entity) throws Exception;

    public VtProyecto getVtProyecto(Long proyId) throws Exception;

    public List<VtProyecto> findByCriteriaInVtProyecto(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtProyecto> findPageVtProyecto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtProyecto() throws Exception;

    public List<VtProyectoDTO> getDataVtProyecto() throws Exception;

    public void validateVtProyecto(VtProyecto vtProyecto)
        throws Exception;

    public List<VtReporteTiempo> getVtReporteTiempo() throws Exception;

    public void saveVtReporteTiempo(VtReporteTiempo entity)
        throws Exception;

    public void deleteVtReporteTiempo(VtReporteTiempo entity)
        throws Exception;

    public void updateVtReporteTiempo(VtReporteTiempo entity)
        throws Exception;

    public VtReporteTiempo getVtReporteTiempo(Long retiId)
        throws Exception;

    public List<VtReporteTiempo> findByCriteriaInVtReporteTiempo(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<VtReporteTiempo> findPageVtReporteTiempo(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberVtReporteTiempo() throws Exception;

    public List<VtReporteTiempoDTO> getDataVtReporteTiempo()
        throws Exception;

    public void validateVtReporteTiempo(VtReporteTiempo vtReporteTiempo)
        throws Exception;

    public List<VtTipoActividad> getVtTipoActividad() throws Exception;

    public void saveVtTipoActividad(VtTipoActividad entity)
        throws Exception;

    public void deleteVtTipoActividad(VtTipoActividad entity)
        throws Exception;

    public void updateVtTipoActividad(VtTipoActividad entity)
        throws Exception;

    public VtTipoActividad getVtTipoActividad(Long tiacId)
        throws Exception;

    public List<VtTipoActividad> findByCriteriaInVtTipoActividad(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<VtTipoActividad> findPageVtTipoActividad(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberVtTipoActividad() throws Exception;

    public List<VtTipoActividadDTO> getDataVtTipoActividad()
        throws Exception;

    public void validateVtTipoActividad(VtTipoActividad vtTipoActividad)
        throws Exception;

    public List<VtTipoIdentificacion> getVtTipoIdentificacion()
        throws Exception;

    public void saveVtTipoIdentificacion(VtTipoIdentificacion entity)
        throws Exception;

    public void deleteVtTipoIdentificacion(VtTipoIdentificacion entity)
        throws Exception;

    public void updateVtTipoIdentificacion(VtTipoIdentificacion entity)
        throws Exception;

    public VtTipoIdentificacion getVtTipoIdentificacion(Long tiidId)
        throws Exception;

    public List<VtTipoIdentificacion> findByCriteriaInVtTipoIdentificacion(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<VtTipoIdentificacion> findPageVtTipoIdentificacion(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberVtTipoIdentificacion() throws Exception;

    public List<VtTipoIdentificacionDTO> getDataVtTipoIdentificacion()
        throws Exception;

    public void validateVtTipoIdentificacion(
        VtTipoIdentificacion vtTipoIdentificacion) throws Exception;

    public List<ZcodeEntityAuditEvent> getZcodeEntityAuditEvent()
        throws Exception;

    public void saveZcodeEntityAuditEvent(ZcodeEntityAuditEvent entity)
        throws Exception;

    public void deleteZcodeEntityAuditEvent(ZcodeEntityAuditEvent entity)
        throws Exception;

    public void updateZcodeEntityAuditEvent(ZcodeEntityAuditEvent entity)
        throws Exception;

    public ZcodeEntityAuditEvent getZcodeEntityAuditEvent(Long id)
        throws Exception;

    public List<ZcodeEntityAuditEvent> findByCriteriaInZcodeEntityAuditEvent(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<ZcodeEntityAuditEvent> findPageZcodeEntityAuditEvent(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberZcodeEntityAuditEvent()
        throws Exception;

    public List<ZcodeEntityAuditEventDTO> getDataZcodeEntityAuditEvent()
        throws Exception;

    public void validateZcodeEntityAuditEvent(
        ZcodeEntityAuditEvent zcodeEntityAuditEvent) throws Exception;
    
    public List<VtClienteDTO> clientesConProyectos() throws Exception;
    
    public List<VtProyectoDTO> consultaProyectosClientes(String activo) throws Exception;
    
    public List<VtProyectoDTO> consultaProyectosClientesRecursos(String activo) throws Exception;
    
    public void guardarProyectoConCostosPersonas(VtProyecto proyecto, List<VtPersonasDTO> personas) throws Exception;

	public void actualizarProyectoConCostosPersonas(VtProyecto proyecto, List<VtPersonasDTO> personas) throws Exception;
	
	public List<VtPersonasDTO> consultaPersonasProyectos(String activo) throws Exception;
	
	public List<VtPersonasDTO> consultaPersonasProyecto(String activo, Long proyId) throws Exception;
	
	public List<VtLineaNegocioDTO> getDataVtLineaNegocio() throws Exception;
	
	public VtLineaNegocio getVtLineaNegocio(Long lineId) throws Exception;
	
	public List<VtPersonasDTO> consultaPersonas(String activo) throws Exception;
	
	public String guardarActividadConAsignacionesPersonas(VtActividad actividad, List<VtPersonasDTO> personas) throws Exception;
	
	public List<VtActividadDTO> consultaActividadesOrdenadaFechaDescendiente(String activo) throws Exception;
	
	public List<VtProyectoDTO> listVtProyectoToListVtProyectoDTO(List<VtProyecto> vtProyectos) throws Exception;
	
	public void guardarPersonasConCostosProyecto(VtPersonasDTO persona, List<VtProyectoDTO> proyectos) throws Exception;
    
    public void actualizarPersonasConCostosProyecto(VtPersonasDTO persona, List<VtProyectoDTO> proyectos) throws Exception;

    public List<VtProyectoDTO> consultaProyectos(String activo) throws Exception;

	public UsuarioDTO autenticar(String userId, String password) throws Exception;
	
	public List<VtPersonasDTO> consultaPersonasActividad(Long actiId, String activo) throws Exception;
	
	public List<VtActividadDTO> consultaMisActividades(String usuario, String activo) throws Exception;
	
	public List<GrupoDTO> consultarOpcionesDeUsuarioPorSistemaSucursalYCompania(String login, String sistema,
			String sucursal, String compania)throws Exception;
	
	public List<VtProyectoDTO> consultaProyectosActivosPersona(Long persId, String activo) throws Exception;
	
	public VtPersonas consultarPersonaPorCorreo(String correo) throws Exception;
	
	public List<VtReporteTiempoDTO> reporteEntreFechas(Date fechaInicial, Date fechaFinal) throws Exception;
	
	public ByteArrayInputStream generarReporteTiemposEntreFechas(Date fechaInicial, Date fechaFinal, REPORT_OUTPUT_TYPE reportOutputTye)
			throws Exception;
	
	public BigDecimal horasReportadasHoy(Date fecha, String email) throws Exception;
	
	public ByteArrayInputStream generarReporteCostosPorProyectos(Date fechaInicial, Date fechaFinal, Long persId, Long proyId, Long tiacId,REPORT_OUTPUT_TYPE reportOutputTye)
			throws Exception;
	public ByteArrayInputStream generarReporteOcupacionPlanta(Date fechaInicial, Date fechaFinal, Long persId, Long proyId, Long tiacId,REPORT_OUTPUT_TYPE reportOutputTye)
			throws Exception;
	public ByteArrayInputStream generarReporteCostosPorPersona(Date fechaInicial, Date fechaFinal, Long persId, Long proyId, Long tiacId,REPORT_OUTPUT_TYPE reportOutputTye)
			throws Exception;
	public void cambiarClave(VtPersonasDTO personaDTO, UsuarioDTO usuarioDTO) throws Exception;
	public ByteArrayInputStream generarReporteCostoAcumuladoProyecto(Long proyId, REPORT_OUTPUT_TYPE reportOutputTye)
			throws Exception;
	
	public List<VtTipoActividadDTO> tiposActividadesConClasificacionFinanciera() throws Exception;
    
	public List<VtClasificacionFinancieraDTO> getDataVtClasificacionFinanciera()
            throws Exception;

	public VtClasificacionFinanciera getVtClasificacionFinanciera(Long clfiId)
	        throws Exception;
	
	public List<VtTipoActividadDTO> tiposActividadesPorEstado(String activo) throws Exception;
	
	public void restaurarClave(String login) throws Exception;
	
	public List<VtActividadDTO> consultaMisActividadesLazy(String usuario, String activo, Long proyId, Long estaId, Long tiacId, String sprint, String casoSoporte, String controlCambios, int first, int pageSize) throws Exception;

	public Long consultaTotalMisActividadesLazy(String usuario, String activo, Long proyId, Long estaId, Long tiacId, String sprint, String casoSoporte, String controlCambios) throws Exception;
	
	public List<VtProyectoDTO> consultaProyectosTodosPersona(Long persId, String activo) throws Exception;
}
