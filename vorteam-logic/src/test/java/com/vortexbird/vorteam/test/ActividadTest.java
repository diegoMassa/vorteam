package com.vortexbird.vorteam.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.vorteam.domain.VtActividad;
import com.vortexbird.vorteam.domain.VtAsignacion;
import com.vortexbird.vorteam.domain.VtClasificacionFinanciera;
import com.vortexbird.vorteam.domain.VtCliente;
import com.vortexbird.vorteam.domain.VtEstado;
import com.vortexbird.vorteam.domain.VtLineaNegocio;
import com.vortexbird.vorteam.domain.VtPersonas;
import com.vortexbird.vorteam.domain.VtProyecto;
import com.vortexbird.vorteam.domain.VtReporteTiempo;
import com.vortexbird.vorteam.domain.VtTipoActividad;
import com.vortexbird.vorteam.domain.VtTipoIdentificacion;
import com.vortexbird.vorteam.dto.VtActividadDTO;
import com.vortexbird.vorteam.service.VtActividadService;
import com.vortexbird.vorteam.service.VtAsignacionService;
import com.vortexbird.vorteam.service.VtClasificacionFinancieraService;
import com.vortexbird.vorteam.service.VtClienteService;
import com.vortexbird.vorteam.service.VtEstadoService;
import com.vortexbird.vorteam.service.VtLineaNegocioService;
import com.vortexbird.vorteam.service.VtPersonasService;
import com.vortexbird.vorteam.service.VtProyectoService;
import com.vortexbird.vorteam.service.VtReporteTiempoService;
import com.vortexbird.vorteam.service.VtTipoActividadService;
import com.vortexbird.vorteam.service.VtTipoIdentificacionService;
import com.vortexbird.vorteam.utility.Constantes;

/**
 * @author Frank Edward Daza González
 * @version 2019-03-13
 */
@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "classpath:test-applicationContext.xml" })
class ActividadTest {

	@Autowired
	private VtActividadService vtActividadService;
	@Autowired
	private VtTipoActividadService vtTipoActividadService;
	@Autowired
	private VtTipoIdentificacionService vtTipoIdentificacionService;
	@Autowired
	private VtClienteService vtClienteService;
	@Autowired
	private VtLineaNegocioService vtLineaNegocioService;
	@Autowired
	private VtProyectoService vtProyectoService;
	@Autowired
	private VtEstadoService vtEstadoService;
	@Autowired
	private VtClasificacionFinancieraService vtClasificacionFinancieraService;
	@Autowired
	private VtPersonasService vtPersonasService;
	@Autowired
	private VtAsignacionService vtAsignacionService;
	@Autowired
	private VtReporteTiempoService vtReporteTiempoService;
	
	
	/**
	 * Prueba de unidad valida la acción de listar las actividades de un usuario.
	 * 
	 * @author Frank Edward Daza González
	 * @version 2019-03-14
	 */
	@Test
	@DisplayName("Listar actividades de un usuario")
	void listarActividadesUsuarioTest() {
		String usuario = "fdaza@vortexbird.com";
		try {
			List<VtActividadDTO> vtActividadDTOs = this.vtActividadService.consultaMisActividades(usuario, Constantes.ESTADO_ACTIVO);
			
			if (vtActividadDTOs != null && vtActividadDTOs.isEmpty()) {
				fail("La lista de actividades del usuario: " + usuario + ", se encuentra vacía.");
			}
		} catch (Exception e) {
			fail("Error al consultar la lista de actividades del usuario: " + usuario + ". Mensaje: " + e.getMessage());
		}
	}
	
	/**
	 * Prueba de unidad que valida la creación de una actividad a un usuario.
	 * 
	 * @author Frank Edward Daza González
	 * @version 2019-03-14
	 */
	@Test
	@DisplayName("Crear actividad a un usuario")
	void crearActividadUsuarioTest() {
		Date fechaActual = new Date();
		String usuarioCreador = "ADMIN";
		String emailUsuario = "fdaza@vortexbird.com";
		
		VtClasificacionFinanciera vtClasificacionFinanciera = new VtClasificacionFinanciera();
		vtClasificacionFinanciera.setActivo(Constantes.ESTADO_ACTIVO);
		vtClasificacionFinanciera.setDescripcion("Clasificacion financiera de prueba.");
		vtClasificacionFinanciera.setFechaCreacion(fechaActual);
		vtClasificacionFinanciera.setUsuaCreador(usuarioCreador);

		try {
			this.vtClasificacionFinancieraService.saveVtClasificacionFinanciera(vtClasificacionFinanciera);
		} catch (Exception e) {
			fail("Error al guardar la clasificación financiera. Mensaje: " + e.getMessage());
		}
		
		VtTipoActividad vtTipoActividad = new VtTipoActividad();
		vtTipoActividad.setActivo(Constantes.ESTADO_ACTIVO);
		vtTipoActividad.setDescripcion("Mi actividad de prueba");
		vtTipoActividad.setFechaCreacion(fechaActual);
		vtTipoActividad.setUsuaCreador(usuarioCreador);
		vtTipoActividad.setVtClasificacionFinanciera(vtClasificacionFinanciera);
		
		try {
			this.vtTipoActividadService.saveVtTipoActividad(vtTipoActividad);
		} catch (Exception e1) {
			fail("Error al guardar el tipo de actividad. Mensaje: " + e1.getMessage());
		}
		
		VtTipoIdentificacion vtTipoIdentificacion = new VtTipoIdentificacion();
		vtTipoIdentificacion.setActivo(Constantes.ESTADO_ACTIVO);
		vtTipoIdentificacion.setDescripcion("Tipo de identificación de prueba");
		vtTipoIdentificacion.setFechaCreacion(fechaActual);
		vtTipoIdentificacion.setUsuaCreador(usuarioCreador);
		
		try {
			this.vtTipoIdentificacionService.saveVtTipoIdentificacion(vtTipoIdentificacion);
		} catch (Exception e1) {
			fail("Error al guardar el tipo de identificación. Mensaje: " + e1.getMessage());
		}
		
		VtCliente vtCliente = new VtCliente();
		vtCliente.setActivo(Constantes.ESTADO_ACTIVO);
		vtCliente.setFechaCreacion(fechaActual);
		vtCliente.setIdentificacion("123456789");
		vtCliente.setNombreRazonSocial("Cliente de prueba");
		vtCliente.setUsuaCreador(usuarioCreador);
		vtCliente.setVtTipoIdentificacion(vtTipoIdentificacion);
		
		try {
			this.vtClienteService.saveVtCliente(vtCliente);
		} catch (Exception e1) {
			fail("Error al guardar el cliente. Mensaje: " + e1.getMessage());
		}
		
		VtLineaNegocio vtLineaNegocio = new VtLineaNegocio();
		vtLineaNegocio.setActivo(Constantes.ESTADO_ACTIVO);
		vtLineaNegocio.setDescripcion("Linea de negocio de prueba");
		vtLineaNegocio.setFechaCreacion(fechaActual);
		vtLineaNegocio.setUsuaCreador(usuarioCreador);
		
		try {
			this.vtLineaNegocioService.saveVtLineaNegocio(vtLineaNegocio);
		} catch (Exception e1) {
			fail("Error al guardar la línea de negocio. Mensaje: " + e1.getMessage());
		}
		
		VtProyecto vtProyecto = new VtProyecto();
		vtProyecto.setActivo(Constantes.ESTADO_ACTIVO);
		vtProyecto.setCostoTotal(0L);
		vtProyecto.setFechaCreacion(fechaActual);
		vtProyecto.setNombreProyecto("Proyecto de prueba");
		vtProyecto.setUsuaCreador(usuarioCreador);
		vtProyecto.setVtCliente(vtCliente);
		vtProyecto.setVtLineaNegocio(vtLineaNegocio);
		
		try {
			this.vtProyectoService.saveVtProyecto(vtProyecto);
		} catch (Exception e1) {
			fail("Error al guardar el proyecto. Mensaje: " + e1.getMessage());
		}
		
		VtEstado vtEstado = new VtEstado();
		vtEstado.setActivo(Constantes.ESTADO_ACTIVO);
		vtEstado.setDescripcion("Estado de prueba");
		vtEstado.setFechaCreacion(fechaActual);
		vtEstado.setUsuaCreador(usuarioCreador);
		
		try {
			this.vtEstadoService.saveVtEstado(vtEstado);
		} catch (Exception e1) {
			fail("Error al guardar el estado. Mensaje: " + e1.getMessage());
		}
		
		VtActividad vtActividad = new VtActividad();
		vtActividad.setVtTipoActividad(vtTipoActividad);
		vtActividad.setVtProyecto(vtProyecto);
		vtActividad.setVtEstado(vtEstado);
		vtActividad.setNombre("Nombre de prueba");
		vtActividad.setDescripcion("Descripción de prueba");
		vtActividad.setSprint("Sprint de prueba");
		vtActividad.setCasoSoporte("Caso soporte de prueba");
		vtActividad.setControlCambios("Control de cambios de prueba");
		vtActividad.setFacturable(Constantes.NO);
		vtActividad.setActivo(Constantes.ESTADO_ACTIVO);
		vtActividad.setUsuaCreador(usuarioCreador);
		vtActividad.setFechaCreacion(fechaActual);
		vtActividad.setPorcentajeAvance(new BigDecimal(0));

		try {
			this.vtActividadService.saveVtActividad(vtActividad);
			assertTrue("La actividad ha sido creada exitosamente", true);
		} catch (Exception e) {
			fail("Error al guardar la actividad. Mensaje: " + e.getMessage());
		}
		
		Object[] variablesBusquedaUsuario = {"email", true, emailUsuario, "="};
		
		List<VtPersonas> listVtPersonas = new ArrayList<VtPersonas>();
		
		try {
			listVtPersonas = this.vtPersonasService.findByCriteria(variablesBusquedaUsuario, null, null);
		} catch (Exception e) {
			fail("Error al traer la información de la persona con email: " + emailUsuario + ". Mensaje: " + e.getMessage());
		}
		
		if (listVtPersonas == null || listVtPersonas.isEmpty()) {
			fail("No hay personas registradas con el email: " + emailUsuario);
		}
		
		VtPersonas vtPersonas = listVtPersonas.get(0);
		
		VtAsignacion vtAsignacion = new VtAsignacion();
		vtAsignacion.setActivo(Constantes.ESTADO_ACTIVO);
		vtAsignacion.setFechaCreacion(fechaActual);
		vtAsignacion.setUsuaCreador(usuarioCreador);
		vtAsignacion.setVtActividad(vtActividad);
		vtAsignacion.setVtPersonas(vtPersonas);
		
		try {
			this.vtAsignacionService.saveVtAsignacion(vtAsignacion);
		} catch (Exception e) {
			fail("Error al guardar la asignación. Mensaje: " + e.getMessage());
		}

		VtReporteTiempo vtReporteTiempo = new VtReporteTiempo();
		vtReporteTiempo.setActivo(Constantes.ESTADO_ACTIVO);
		vtReporteTiempo.setFecha(fechaActual);
		vtReporteTiempo.setFechaCreacion(fechaActual);
		vtReporteTiempo.setHorasEjecutadas(new BigDecimal(10));
		vtReporteTiempo.setObservacion("Observación de prueba.");
		vtReporteTiempo.setPorcentajeAvance(new BigDecimal(10));
		vtReporteTiempo.setUsuaCreador(usuarioCreador);
		vtReporteTiempo.setVtAsignacion(vtAsignacion);
		vtReporteTiempo.setVtEstado(vtEstado);
		
		try {
			this.vtReporteTiempoService.saveVtReporteTiempo(vtReporteTiempo);
		} catch (Exception e) {
			fail("Error al guardar el reporte de tiempo. Mensaje: " + e.getMessage());
		}
	}

}
