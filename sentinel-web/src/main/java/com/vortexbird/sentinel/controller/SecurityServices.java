package com.vortexbird.sentinel.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.proto.server.ws.AdminUsuarios;
import com.proto.server.ws.AdminUsuarios_Service;
import com.vortexbird.comprobantepago.modelo.control.jobs.IEliminarTokensExpiradosLogic;
import com.vortexbird.sentinel.modelo.SegSistema;
import com.vortexbird.sentinel.modelo.SegUsuario;
import com.vortexbird.sentinel.modelo.VinCamposParametrizables;
import com.vortexbird.sentinel.modelo.control.ISegOpcionLogic;
import com.vortexbird.sentinel.modelo.control.ISegRolLogic;
import com.vortexbird.sentinel.modelo.control.ISegUsuarioLogic;
import com.vortexbird.sentinel.modelo.dto.GrupoDTO;
import com.vortexbird.sentinel.modelo.dto.ResultadoCrearUsuarioDTO;
import com.vortexbird.sentinel.modelo.dto.SegOpcionDTO;
import com.vortexbird.sentinel.modelo.dto.SegRolDTO;
import com.vortexbird.sentinel.modelo.dto.UsuarioDTO;
import com.vortexbird.sentinel.presentation.businessDelegate.IBusinessDelegatorView;
import com.vortexbird.sentinel.utilities.Utilities;



@RestController
@RequestMapping("/securityServices")
public class SecurityServices {

	@Autowired
	private IBusinessDelegatorView businessDelegatorView;
	
	@Autowired
	private IEliminarTokensExpiradosLogic eliminarTokensExpiradosLogic; 
	
	@Autowired
	private ISegOpcionLogic segOpcionLogic;
	
	@Autowired
	private ISegUsuarioLogic segUsuarioLogic;
	
	@Autowired
	private ISegRolLogic segRolLogic;
	
	private Logger log = LoggerFactory.getLogger(SecurityServices.class);

	private AuthenticationResponse privateAuthenticate(String login, String password, String dominio){
		try {
			
			UsuarioDTO usuarioDTO = businessDelegatorView.autenticar(login, password, dominio);
			
			if (usuarioDTO == null || (
					!usuarioDTO.getEstado().equals("1") && !usuarioDTO.getEstado().equals("2")
					)){
				throw new Exception("No existe el usuario, o la contraseña es inválida");
			}
			
			Long usuCodigo = Long.parseLong(usuarioDTO.getUsu_codigo());
			
			//Se consultan los sistemas a los que tiene permiso de acceso el usuario
			List<SegSistema> sistemas = businessDelegatorView.consultarSistemasALosQueTieneAccesoElUsuario(usuCodigo);
			List<SistemaDTO> losSistemas = new ArrayList<SistemaDTO>();
			
			if (sistemas!=null && sistemas.size()>0){
			
				for (SegSistema segSistema : sistemas) {
					
					SistemaDTO sistemaDTO = new SistemaDTO();
					sistemaDTO.setCodigo(segSistema.getSisCodigo());
					sistemaDTO.setDescripcion(segSistema.getSisDescripcion());
					sistemaDTO.setNombre(segSistema.getSisNombre());
					
					sistemaDTO.setUrl(segSistema.getSisUrl()!=null?segSistema.getSisUrl():"?");
					sistemaDTO.setIcono(segSistema.getSisIcono()!=null?segSistema.getSisIcono():"icon-circle-empty");
					
					losSistemas.add(sistemaDTO);
				}
			}
			
			String token = UUID.randomUUID().toString();
			
			AuthenticationResponse authenticationResponse = new AuthenticationResponse();
			authenticationResponse.setUsuarioDTO(usuarioDTO);
			authenticationResponse.setSistemas(losSistemas);
			
			authenticationResponse.setToken(token);
			authenticationResponse.setFechaInicioSesion(new Date());
			
			Map<String, AuthenticationResponse> objetosEnSession = eliminarTokensExpiradosLogic.getObjetosensesion();
			
			//Se valida si el usuario ya estaba en la sesión con ese token
			AuthenticationResponse autenticacionPrevia = objetosEnSession.get(token);
			if (autenticacionPrevia != null){
				objetosEnSession.remove(token);
			}
			
			objetosEnSession.put(token, authenticationResponse);
			
			Log.info("Se inició sesión con el token: " + token);
			
			return authenticationResponse;
			
		} catch (Exception e) {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setEstado("3");
			usuarioDTO.setMensajeExcepcion(e.getMessage());
			
			AuthenticationResponse authenticationResponse = new AuthenticationResponse();
			authenticationResponse.setUsuarioDTO(usuarioDTO);
			
			return authenticationResponse;
		}
	}
	
	@RequestMapping(value="/cambiarContrasena", method = RequestMethod.POST)
	public ChangePasswordResponse cambiarContrasena(@RequestBody ChangePasswordRequest changePasswordRequest) {
			
		ChangePasswordResponse changePasswordResponse = new ChangePasswordResponse();
		
		try {
			
			if (changePasswordRequest == null){
				throw new Exception("No se recibió ningún parámetro");
			}
			
			String email = changePasswordRequest.getEmail();
			String identificacion = changePasswordRequest.getIdentificacion();
			
			if (email == null || email.trim().equals("")){
				throw new Exception("No se recibió ningún email");
			}
			
			if (identificacion == null || identificacion.trim().equals("")){
				throw new Exception("No se recibió ninguna identificación");
			}
			
			changePasswordResponse.setEmail(email);
			changePasswordResponse.setIdentificacion(identificacion);
			
			//se solicita cambiar el password
			String nuevaContrasena = businessDelegatorView.generarNuevaContrasena(identificacion, email);
			String contrasenaMD5 = Utilities.convertirMD5(nuevaContrasena);
			
			
			//********************* Se notifica a CRM ********************* 
			
			//Se consulta el parametro que indica la ubicación del servicio de CRM para el cambio de PWD
			log.info("Se notificará el cambio de PWD a CRM...");
			
			log.info("Consultando el param SERVICIO_CRM_CAMBIO_PWD...");
			
			VinCamposParametrizables paramUrlCambioPwdCRM = businessDelegatorView.getVinCamposParametrizables("SERVICIO_CRM_CAMBIO_PWD");
			
			if (paramUrlCambioPwdCRM == null || paramUrlCambioPwdCRM.getValor() == null || paramUrlCambioPwdCRM.getValor().trim().equals("")){
				log.error("No existe el parámetro SERVICIO_CRM_CAMBIO_PWD, el cual indica la ubicación del servicio para cambio de PWD en CRM!");
			}
			else{
				
				try {
					String URL = paramUrlCambioPwdCRM.getValor();
					
					if (!URL.endsWith("?")){
						URL += "?";
					}
					
					RestTemplate restTemplate = new RestTemplate();
					
					String paramCedula = "cedula="+identificacion;
					String paramPwdEncriptado = "password="+contrasenaMD5;
					String rutaDefinitiva = URL+paramCedula+"&"+paramPwdEncriptado;
					
					log.info("Se notificará el cambio de PWD en CRM a la siguiente URL: " + rutaDefinitiva);
					
					String response = restTemplate.getForObject(rutaDefinitiva,  String.class);
					
					log.info("La respuesta de la invocación al servicio de cambio de PWD en CRM es: " + response);
				} catch (Exception e) {
					log.error("Error invocando el servicio de cambio de PWD en CRM: " + e.getMessage(), e);
				}
				
			}
			
			
			//********************* Se notifica a CRM ********************* 
			
			//Se consulta el parametro que indica la ubicación del servicio de CRM para el cambio de PWD
			log.info("Se notificará el cambio de PWD a Vcloud...");
			
			log.info("Consultando el param 22...");
			
			VinCamposParametrizables paramUrlCambioContrasenaVcloud = businessDelegatorView.getVinCamposParametrizables("22");
			
			if (paramUrlCambioContrasenaVcloud == null || paramUrlCambioContrasenaVcloud.getValor() == null || paramUrlCambioContrasenaVcloud.getValor().trim().equals("")){
				log.error("No existe el parámetro 22, el cual indica la ubicación del servicio para cambio de PWD en Vcloud!");
			}
			else{
				try {
					
					log.info("Consultando el param 23...");
					
					VinCamposParametrizables usuarioPwdHeaderWSVCloud = businessDelegatorView.getVinCamposParametrizables("23");
					
					if (usuarioPwdHeaderWSVCloud == null || usuarioPwdHeaderWSVCloud.getValor() == null || usuarioPwdHeaderWSVCloud.getValor().trim().equals("")){
						throw new Exception("No existe el parámetro 23, el cual indica el usuario y pwd para autenticación con el servicio web de Vcloud!");
					}
					
					String datosAutenticacionServicioVCloud[] = usuarioPwdHeaderWSVCloud.getValor().split("-");
					if (datosAutenticacionServicioVCloud==null || datosAutenticacionServicioVCloud.length != 2){
						throw new Exception("No se puede parsear los datos de usuario y pwd para autenticación con el WS de Vcloud. "
								+ "Debe ser una cadena separada por guión (-). Deben ser dos datos, usuario-pwd");
					}
					
					String headerUsername = datosAutenticacionServicioVCloud[0];
					String headerPassword = datosAutenticacionServicioVCloud[1];
					
					URL wsdlURL = AdminUsuarios_Service.WSDL_LOCATION;
					final QName SERVICE_NAME = new QName("http://ws.server.proto.com/", "AdminUsuarios");
					
					// se consulta establece la URL del servicio
					String servicioVcloud = paramUrlCambioContrasenaVcloud.getValor();
					
			        try {
			        	//wsdlURL = new URL("http://10.75.30.16:8080/servWsRs/AdminUsuarios?wsdl");
			        	wsdlURL = new URL(servicioVcloud+"?wsdl");
			        } catch (MalformedURLException e) {
			            log.info("Can not initialize the default wsdl from " + (servicioVcloud+"?wsdl"));
			        }
					//
					
			        log.info("Invocando el cambio de pwd en Vcloud en el endpoint " + (servicioVcloud+"?wsdl") + " con los siguientes parametros: usuario="+identificacion + ", pwd="+nuevaContrasena);
			        
					AdminUsuarios_Service ss = new AdminUsuarios_Service(wsdlURL, SERVICE_NAME);
			        
					AdminUsuarios port = ss.getAdminUsuariosPort();
					BindingProvider bp = (BindingProvider) port;
					bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, servicioVcloud);
			        
			        com.proto.server.ws.CambiarContrasena _cambiarContrasena_parameters = new com.proto.server.ws.CambiarContrasena();
			        _cambiarContrasena_parameters.setUsuario(identificacion);
			        _cambiarContrasena_parameters.setContrasena(nuevaContrasena);
			        
			        com.proto.server.ws.CambiarContrasenaResponse _cambiarContrasena__return = port.cambiarContrasena(_cambiarContrasena_parameters, headerUsername, headerPassword);
			        
			        log.info("Se ha invocado el servicio de cambio de pwd en vcloud. El resultado es: ");
			        if (_cambiarContrasena__return!=null){
			        	log.info(_cambiarContrasena__return.toString());
			        	
			        	if (_cambiarContrasena__return.getXmlResultado()!=null){
			        		log.info("Código: " +_cambiarContrasena__return.getXmlResultado().getCodigo() + ". Mensaje = " + _cambiarContrasena__return.getXmlResultado().getMensaje());
			        	}
			        }
			        
					
				} catch (Exception e) {
					log.error("Error invocando el servicio de cambio de PWD en VCloud: " + e.getMessage(), e);
				}
			}
			
			changePasswordResponse.setError("");
			
			
		} catch (Exception e) {
			changePasswordResponse.setError("Error cambiando la contraseña: "+ e.getMessage());
		}
		
		return changePasswordResponse;
		
	}
	
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
			
		String login = authenticationRequest.getLogin();
		String password = authenticationRequest.getPassword();
		String dominio = authenticationRequest.getDominio();
			
		return privateAuthenticate(login, password, dominio);
	}
	
	
	
	@RequestMapping(value="/autenticar/{login}/{pass}/{dominio}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody UsuarioDTO autenticar(@PathVariable("login")String login, 
			@PathVariable("pass")String pass,
			@PathVariable("dominio")String dominio) {	
		
		AuthenticationResponse authenticationResponse = privateAuthenticate(login, pass, dominio);
		
		if (authenticationResponse!=null){
			return authenticationResponse.getUsuarioDTO();
		}else{
			return null;
		}
		
	}
	
	@RequestMapping(value="/validateToken/{token}", method = RequestMethod.GET)
	private @ResponseBody AuthenticationResponse validateToken(@PathVariable String token){
		try {
			
			Map<String, AuthenticationResponse> objetosEnSession = eliminarTokensExpiradosLogic.getObjetosensesion();
			
			//Se valida si el usuario ya estaba en la sesión con ese token
			AuthenticationResponse autenticacionPrevia = objetosEnSession.get(token);
			if (autenticacionPrevia != null){
				return autenticacionPrevia;
			}
			
		} catch (Exception e) {
			Log.error(e.getMessage(), e);
		}
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setEstado("3");
		usuarioDTO.setMensajeExcepcion("No existe el token de autenticación");
		
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		authenticationResponse.setUsuarioDTO(usuarioDTO);
		
		return authenticationResponse;
	}
	
	@RequestMapping(value="/createUserWithRoles", method = RequestMethod.POST)
	public UsuarioDTO createUser(@RequestBody CreateUserRequest createUserRequest) {
		try {
			
			String usuApellidos = createUserRequest.getUsuApellidos(); 
			String usuCodigoInterno = createUserRequest.getUsuCodigoInterno();
			String usuConstrasena = createUserRequest.getUsuConstrasena(); 
			String usuCorreo  = createUserRequest.getUsuCorreo(); 
			String usuEstadoRegistro  = "A";
			Long usuIntentosFallidos  = 0L; 
			Long ciaCodigo_SegCompania  = null; 
			String usuLogin  = createUserRequest.getUsuLogin(); 
			String usuNombres  = createUserRequest.getUsuNombres();
			Date usuUltmimaModificacionPass = new Date(); 
			Long usuSession  = 0L;
			List<String> rolesAsignados = createUserRequest.getRolesAsignados(); 
			String compania = createUserRequest.getCompania(); 
			String sistema = createUserRequest.getSistema();
			
			
			if (usuApellidos == null){
				throw new Exception("Debe ingresar los apelleidos del usuario");
			}
			
			if (usuCodigoInterno == null){
				throw new Exception("Debe ingresar el codigo interno del usuario (Dominio)");
			}
			
			if (usuConstrasena == null){
				throw new Exception("Debe ingresar la contraseña del usuario");
			}
			
			if (usuCorreo == null){
				throw new Exception("Debe ingresar el correo del usuario");
			}
			
			if (usuLogin == null){
				throw new Exception("Debe ingresar el login del usuario");
			}
			
			if (usuNombres == null){
				throw new Exception("Debe ingresar el nombre del usuario");
			}
			
			if (compania == null){
				throw new Exception("Debe ingresar la compañía para la cual se desea crear el ususario");
			}
			
			if (sistema == null){
				throw new Exception("Debe ingresar el sistema para el cual se desea crear el ususario");
			}
			
			usuLogin = usuLogin.toUpperCase();
			String contrasenaMD5 = Utilities.convertirMD5(usuConstrasena);
			
			businessDelegatorView.guardarUsuarioConRoles(usuApellidos, usuCodigoInterno, contrasenaMD5, 
					usuCorreo, usuEstadoRegistro, usuIntentosFallidos, ciaCodigo_SegCompania, usuLogin, 
					usuNombres, usuUltmimaModificacionPass, usuSession, rolesAsignados, compania, sistema);
			
			UsuarioDTO usuarioDTO = businessDelegatorView.autenticar(usuLogin, usuConstrasena, usuCodigoInterno);
			
			if (usuarioDTO == null || (
					!usuarioDTO.getEstado().equals("1") && !usuarioDTO.getEstado().equals("2")
					)){
				throw new Exception("No existe el usuario, o la contraseña es inválida");
			} 
			
			return usuarioDTO;
			
		} catch (Exception e) {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setEstado("3");
			usuarioDTO.setMensajeExcepcion(e.getMessage());
			
			return usuarioDTO;
		}
	}
	
	@RequestMapping(value="/crearUsuario/{usuApellidos}/{usuCodigoInterno}/{usuConstrasena}/{usuCorreo}/{usuEstadoRegistro}/{usuIntentosFallidos}/{usuCompania}/{usuLogin}/{usuNombres}/{usuSession}/{rolesAsignados}/{compania}/{sistema}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody ResultadoCrearUsuarioDTO crearUsuario(
			@PathVariable("usuApellidos")String usuApellidos, 
			@PathVariable("usuCodigoInterno")String usuCodigoInterno,
			@PathVariable("usuConstrasena")String usuConstrasena, 
			@PathVariable("usuCorreo")String usuCorreo, 
			@PathVariable("usuEstadoRegistro")String usuEstadoRegistro,
			@PathVariable("usuIntentosFallidos")Long usuIntentosFallidos, 
			@PathVariable("usuCompania")Long usuCompania, 
			@PathVariable("usuLogin")String usuLogin, 
			@PathVariable("usuNombres")String usuNombres,
			@PathVariable("usuSession")Long usuSession,
			@PathVariable("rolesAsignados")String rolesAsignados, 
			@PathVariable("compania")String compania, 
			@PathVariable("sistema")String sistema){
		
		ResultadoCrearUsuarioDTO resultadoCrearUsuarioDTO=new ResultadoCrearUsuarioDTO();
		
		try {
			
			if (usuEstadoRegistro.equals("1") || usuEstadoRegistro.equals("a")){
				usuEstadoRegistro = "A";
			}
			
			if (usuEstadoRegistro.equals("0") || usuEstadoRegistro.equals("i")){
				usuEstadoRegistro = "I";
			}
			
			List<String> listaRoles=new ArrayList<String>(1);
			listaRoles.add(rolesAsignados);
			
			Long ciaCodigo_SegCompania  = null; 
			Date usuUltmimaModificacionPass = new Date(); 
			
			if (usuApellidos == null){
				throw new Exception("Debe ingresar los apelleidos del usuario");
			}
			
			if (usuCodigoInterno == null){
				throw new Exception("Debe ingresar el codigo interno del usuario (Dominio)");
			}
			
			if (usuConstrasena == null){
				throw new Exception("Debe ingresar la contraseña del usuario");
			}
			
			if (usuCorreo == null){
				throw new Exception("Debe ingresar el correo del usuario");
			}
			
			if (usuLogin == null){
				throw new Exception("Debe ingresar el login del usuario");
			}
			
			if (usuNombres == null){
				throw new Exception("Debe ingresar el nombre del usuario");
			}
			
			if (compania == null){
				throw new Exception("Debe ingresar la compañía para la cual se desea crear el ususario");
			}
			
			if (sistema == null){
				throw new Exception("Debe ingresar el sistema para el cual se desea crear el ususario");
			}
			
			usuLogin = usuLogin.toUpperCase();
			String contrasenaMD5 = Utilities.convertirMD5(usuConstrasena);
			
			businessDelegatorView.guardarUsuarioConRoles(usuApellidos, usuCodigoInterno, contrasenaMD5, 
					usuCorreo, usuEstadoRegistro, usuIntentosFallidos, ciaCodigo_SegCompania, usuLogin, 
					usuNombres, usuUltmimaModificacionPass, usuSession, listaRoles, compania, sistema);
			
//			UsuarioDTO usuarioDTO = businessDelegatorView.autenticar(usuLogin, usuConstrasena, usuCodigoInterno);
//			
//			if (usuarioDTO == null || (
//					!usuarioDTO.getEstado().equals("1") && !usuarioDTO.getEstado().equals("2")
//					)){
//				throw new Exception("No existe el usuario, o la contraseña es inválida");
//			} 
			
			resultadoCrearUsuarioDTO.setExito(true);
			resultadoCrearUsuarioDTO.setMensaje("Usuario creado con exito");
			
		} catch (Exception e) {
			resultadoCrearUsuarioDTO.setExito(false);
			resultadoCrearUsuarioDTO.setMensaje(e.getMessage());
		}
		return resultadoCrearUsuarioDTO;
	}
	
	@RequestMapping(value="/actualizarUsuario/{usuApellidos}/{usuCodigo}/{usuCodigoInterno}/{usuConstrasena}/{usuCorreo}/{usuEstadoRegistro}/{usuIntentosFallidos}/{usuCompania}/{usuLogin}/{usuNombres}/{usuCodigoSegUsuario}",method=RequestMethod.GET,produces="application/json" + "; charset=utf-8")
	public @ResponseBody ResultadoCrearUsuarioDTO updateUsuario(
			@PathVariable("usuApellidos")String usuApellidos,
			@PathVariable("usuCodigo")Long usuCodigo,
			@PathVariable("usuCodigoInterno")String usuCodigoInterno,
			@PathVariable("usuConstrasena")String usuConstrasena, 
			@PathVariable("usuCorreo")String usuCorreo, 
			@PathVariable("usuEstadoRegistro")String usuEstadoRegistro,
			@PathVariable("usuIntentosFallidos")Long usuIntentosFallidos, 
			@PathVariable("usuCompania")Long usuCompania, 
			@PathVariable("usuLogin")String usuLogin, 
			@PathVariable("usuNombres")String usuNombres,
			@PathVariable("usuCodigoSegUsuario")Long usuCodigoSegUsuario){
		
		
		ResultadoCrearUsuarioDTO resultadoCrearUsuarioDTO=new ResultadoCrearUsuarioDTO();
		
		try {
			
			if (usuEstadoRegistro.equals("1") || usuEstadoRegistro.equals("a")){
				usuEstadoRegistro = "A";
			}
			
			if (usuEstadoRegistro.equals("0") || usuEstadoRegistro.equals("i")){
				usuEstadoRegistro = "I";
			}
			
			SegUsuario segUsuario = businessDelegatorView.getSegUsuario(usuCodigo);
			
			if (segUsuario == null) {
				resultadoCrearUsuarioDTO.setExito(false);
				resultadoCrearUsuarioDTO.setMensaje("No se encontro el usuario en la base de datos");
				
				return resultadoCrearUsuarioDTO;
			}
			if(segUsuario.getUsuConstrasena().trim().equals(Utilities.convertirMD5(usuConstrasena).trim()) == false) {
				segUsuario.setUsuUltmimaModificacionPass(new Date());
				segUsuario.setUsuConstrasena(Utilities.convertirMD5(usuConstrasena));
			}
			segUsuario.setUsuApellidos(usuApellidos);
			segUsuario.setUsuCodigoInterno(usuCodigoInterno);
			segUsuario.setUsuCorreo(usuCorreo);
			segUsuario.setUsuEstadoRegistro(usuEstadoRegistro);
			segUsuario.setUsuIntentosFallidos(usuIntentosFallidos);
			segUsuario.setUsuCompaniaCiaCodigo(usuCompania);
			segUsuario.setUsuLogin(usuLogin.toUpperCase());
			segUsuario.setUsuNombres(usuNombres);
			
			segUsuario.setUsuPrimerLoginSso("I");

			businessDelegatorView.updateSegUsuario(segUsuario);
			
			resultadoCrearUsuarioDTO.setExito(true);
			resultadoCrearUsuarioDTO.setMensaje("Usuario actualizado con exito");
			
			return resultadoCrearUsuarioDTO;
			
		} catch (Exception e) {
			resultadoCrearUsuarioDTO.setExito(false);
			resultadoCrearUsuarioDTO.setMensaje(e.getMessage());
			
			return resultadoCrearUsuarioDTO;
		}
		
	}
	
	@RequestMapping(value="/getOpciones/{login}/{sistema}/{sucursal}/{cia}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody GrupoDTO[] getOpciones(@PathVariable("login") String login,
													@PathVariable("sistema") String sistema,
													@PathVariable("sucursal")String sucursal, 
													@PathVariable("cia")String cia) {
		
		
		List<GrupoDTO> listaGrupoDTOs = segOpcionLogic.consultarOpcionesDeUsuarioPorSistemaSucursalYCompania(login, sistema, sucursal, cia);
		
		if(listaGrupoDTOs!=null && listaGrupoDTOs.size()>0){
			
			GrupoDTO[] arrayGrupoDTO=new GrupoDTO[listaGrupoDTOs.size()];
			
			for(int i=0;i<listaGrupoDTOs.size();i++){
				arrayGrupoDTO[0]=listaGrupoDTOs.get(i);
			}
			
			return arrayGrupoDTO;
		}

				
		return null;
		
	}
	
	@RequestMapping(value="/getOpcionesPorRol/{login}/{dominio}/{sistema}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody  GrupoDTO[] getOpcionesPorRolYSistema(@PathVariable("login")String login, @PathVariable("dominio")String dominio, @PathVariable("sistema")String sistema) {		
		try {

			List<GrupoDTO> listaGrupoDTOs = segOpcionLogic.getOpcionesPorRolYSistema(login, dominio, sistema);

			if(listaGrupoDTOs!=null && listaGrupoDTOs.size()>0){
				GrupoDTO[] arrayGrupoDTO = new GrupoDTO[listaGrupoDTOs.size()];
				for(int i=0;i<listaGrupoDTOs.size();i++){
					arrayGrupoDTO[i]=listaGrupoDTOs.get(i);
				}
				return arrayGrupoDTO;
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/consultarUsuarioPorLogin/{login}/{dominio}/{codigoSistema}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody UsuarioDTO consultarUsuarioPorLogin(@PathVariable("login")String login,
															 @PathVariable("dominio")String dominio,
															 @PathVariable("codigoSistema")String codigoSistema) {
		return segUsuarioLogic.consultarUsuarioPorLogin(login,dominio,codigoSistema);
	}
	
//	@RequestMapping(value="/createUserWithRoles", method = RequestMethod.POST)
//	public Boolean changeUserPassword(@RequestBody ChangeUserPasswordRequest changeUserPasswordRequest) {
//		try {
//			
//			
//			
//		} catch (Exception e) {
//			return false;
//		}
//	}
	
//	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
//	public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
//				
	
	@RequestMapping(value="/validarPrimeraVez", method = RequestMethod.POST)
	public @ResponseBody UsuarioDTO validarPrimeraVez(@RequestBody UsuarioDTO usuario) {
		
		UsuarioDTO usuarioDTO = null;
		try {
			usuarioDTO = businessDelegatorView.consultarPrimeraVez(usuario.getUsu_login(), usuario.getUsu_password(),"1");	
			
			if (usuarioDTO.getUsu_login()==null) {
				return null;
			}else{
				return usuarioDTO;	
			}
		} catch (Exception e) {
			return null;
		}
		 
		
		 
	}
	
	@RequestMapping(value="/savePrimeraVez", method = RequestMethod.POST)
	public @ResponseBody boolean savePrimeraVez(@RequestBody UsuarioDTO usuario) {
		
		boolean respuesta;
		try {
			respuesta = businessDelegatorView.savePrimeraVez(usuario);	
			
			if (respuesta==false) {
				return false;
			}else{
				return true;	
			}
		} catch (Exception e) {
			return false;
		}
		 
		
		 
	}
	
	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}
	
	/**
	 * Servicio REST de tipo GET encargado de consultar los roles de un sistema el cual hace parte
	 * de una compania.
	 * @author Andrés Felipe Vargas López
	 * @version Jun 18, 2018
	 * @since 1.8
	 * @param sistema		Codigo del sistema.
	 * @param compania		Codigo de la compania.
	 * @throws Exception
	 * @return <b>{@code List<SegRolDTO>}</b> Lista de roles pertenecientes al sistema y compania indicados.
	 *
	 */
	@RequestMapping(value = "/consultarRolPorSistemaCompania/{sistema}/{compania}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SegRolDTO> consultarRolPorSistemaCompania(	@PathVariable("sistema") String sistema,
																			@PathVariable("compania") String compania   ) {
		List<SegRolDTO> roles = null;
		
		try { 
			roles = new ArrayList<>();
			roles = this.businessDelegatorView.consultarRolPorSistemaCompania(sistema, compania);
			
		} catch (Exception e) {
			
			log.error(e.getMessage(), e);
			roles = new ArrayList<>();
		}
		
		return roles;
	}
	
	/**
	 * Servicio REST de tipo GET encargado de consultar los roles para un usuario de un sistema el cual hace parte
	 * de una compania.
	 * @author Andrés Felipe Vargas López
	 * @version Jun 18, 2018
	 * @since 1.8
	 * @param sistema		Codigo del sistema.
	 * @param compania		Codigo de la compania.
	 * @param login			Login del Usuario
	 * @throws Exception
	 * @return <b>{@code List<SegRolDTO>}</b> Lista de roles del usuario pertenecientes al sistema y compania indicados.
	 *
	 */
	@RequestMapping(value = "/consultarRolPorUsuarioSistemaCompania/{login}/{sistema}/{compania}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SegRolDTO> consultarRolPorUsuarioSistemaCompania(	 @PathVariable("sistema") String sistema,
																				 @PathVariable("login") String login,
																			     @PathVariable("compania") String compania   ) {
		List<SegRolDTO> roles = null;
		
		try { 
			roles = new ArrayList<>();
			roles = this.businessDelegatorView.consultarRolPorUsuarioSistemaCompania(sistema, compania, login);
			
		} catch (Exception e) {
			
			log.error(e.getMessage(), e);
			roles = new ArrayList<>();
		}
		
		return roles;
	}
	
	/**
	 * Servicio REST de tipo POST que crea un usuario con roles y permisos.
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @param createUserRequest Clase obligatoria para crear el usuario. Aqui se especifican todos los datos y roles a asignar.
	 * @see com.vortexbird.sentinel.controller.CrearUsuarioDTO
	 * @throws Exception
	 * @return {@code ResultadoCrearUsuarioDTO} Respuesta en formato DTO que contiene un boolean de exito y un mensaje.
	 */
	@RequestMapping(value="/crearUsuarioConRolesYPermisos", method = RequestMethod.POST)
	public ResultadoCrearUsuarioDTO crearUsuarioConRolesYPermisos(@RequestBody CrearUsuarioDTO createUserRequest) {
		
		ResultadoCrearUsuarioDTO resultadoCrearUsuarioDTO = new ResultadoCrearUsuarioDTO();
		
		try {
			
			String usuApellidos = createUserRequest.getUsuApellidos(); 
			String usuCodigoInterno = createUserRequest.getUsuCodigoInterno();
			String usuConstrasena = createUserRequest.getUsuConstrasena(); 
			String usuCorreo  = createUserRequest.getUsuCorreo(); 
			String usuEstadoRegistro  = "A";
			Long usuIntentosFallidos  = 0L; 
			Long ciaCodigo_SegCompania  = null; 
			String usuLogin  = createUserRequest.getUsuLogin(); 
			String usuNombres  = createUserRequest.getUsuNombres();
			Date usuUltmimaModificacionPass = new Date(); 
			Long usuSession  = 0L;
			List<SegRolDTO> rolesAsignados = createUserRequest.getRolesAsignados(); 
			String compania = createUserRequest.getCompania(); 
			String sistema = createUserRequest.getSistema();
			
			
			if (usuApellidos == null){
				throw new Exception("Debe ingresar los apellidos del usuario");
			}
			
			if (usuCodigoInterno == null){
				throw new Exception("Debe ingresar el codigo interno del usuario (Dominio)");
			}
			
			if (usuConstrasena == null){
				throw new Exception("Debe ingresar la contraseña del usuario");
			}
			
			if (usuCorreo == null){
				throw new Exception("Debe ingresar el correo del usuario");
			}
			
			if (usuLogin == null){
				throw new Exception("Debe ingresar el login del usuario");
			}
			
			if (usuNombres == null){
				throw new Exception("Debe ingresar el nombre del usuario");
			}
			
			if (compania == null){
				throw new Exception("Debe ingresar la compañía para la cual se desea crear el ususario");
			}
			
			if (sistema == null){
				throw new Exception("Debe ingresar el sistema para el cual se desea crear el ususario");
			}
			
			usuLogin = usuLogin.toUpperCase();
			String contrasenaMD5 = Utilities.convertirMD5(usuConstrasena);
			
			businessDelegatorView.crearUsuarioConRolesYPermisos(usuApellidos, usuCodigoInterno, contrasenaMD5, 
					usuCorreo, usuEstadoRegistro, usuIntentosFallidos, ciaCodigo_SegCompania, usuLogin, 
					usuNombres, usuUltmimaModificacionPass, usuSession, rolesAsignados, compania, sistema);
			
			UsuarioDTO usuarioDTO = businessDelegatorView.autenticar(usuLogin, usuConstrasena, usuCodigoInterno);
			
			if (usuarioDTO == null || (!usuarioDTO.getEstado().equals("1") && !usuarioDTO.getEstado().equals("2"))){
				throw new Exception("Ocurrio un problema al intentar autenticar el usuario, no coincide el login, la clave o el codigo interno(dominio)");
			} 
			
			resultadoCrearUsuarioDTO.setExito(true);
			resultadoCrearUsuarioDTO.setMensaje("Usuario creado con exito");
			
		} catch (Exception e) {
			
			resultadoCrearUsuarioDTO.setExito(false);
			resultadoCrearUsuarioDTO.setMensaje(e.getMessage());
		}
		
		return resultadoCrearUsuarioDTO;
	}
	
	/**
	 * Servicio REST de tipo POST que actualiza el rol de un usuario.
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @param createUserRequest Clase obligatoria para actualizar el usuario. Aqui se especifican todos los datos y roles a actualizar.
	 * @see com.vortexbird.sentinel.controller.ActualizarUsuarioDTO
	 * @throws Exception
	 * @return {@code ResultadoCrearUsuarioDTO} Respuesta en formato DTO que contiene un boolean de exito y un mensaje.
	 */
	@RequestMapping(value="/actualizarRolesYPermisosDeUsuario", method = RequestMethod.POST)
	public ResultadoCrearUsuarioDTO crearUsuarioConRolesYPermisos(@RequestBody ActualizarUsuarioDTO updateUserRequest) {
		
		ResultadoCrearUsuarioDTO resultadoCrearUsuarioDTO = new ResultadoCrearUsuarioDTO();
		
		try {
			
			Long usuCodigo = updateUserRequest.getUsuCodigo();
			Long rolCodigoActual = updateUserRequest.getRolCodigoActual(); 
			Long rolCodigoNuevo = updateUserRequest.getRolCodigoNuevo();
			Long sistema = updateUserRequest.getSistema(); 
			Long compania = updateUserRequest.getCompania(); 
			
			businessDelegatorView.actualizarRolesYPermisosDeUsuario(usuCodigo, rolCodigoActual, rolCodigoNuevo, sistema, compania);
			
			resultadoCrearUsuarioDTO.setExito(true);
			resultadoCrearUsuarioDTO.setMensaje("Usuario actualizado con exito");
			
		} catch (Exception e) {
			
			resultadoCrearUsuarioDTO.setExito(false);
			resultadoCrearUsuarioDTO.setMensaje(e.getMessage());
		}
		
		return resultadoCrearUsuarioDTO;
	}
	
	/**
	 * Servicio REST de tipo POST que adicionar roles a un usuario.
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @param modifyUserRequest Clase obligatoria para actualizar el usuario. Aqui se especifican todos los datos y roles a adicionar.
	 * @see com.vortexbird.sentinel.controller.ModificarRolesDTO
	 * @throws Exception
	 * @return {@code ResultadoCrearUsuarioDTO} Respuesta en formato DTO que contiene un boolean de exito y un mensaje.
	 */
	@RequestMapping(value="/adicionarRolesUsuario", method = RequestMethod.POST)
	public ResultadoCrearUsuarioDTO adicionarRolesUsuario(@RequestBody ModificarRolesDTO modifyUserRequest) {
		
		ResultadoCrearUsuarioDTO resultadoCrearUsuarioDTO = new ResultadoCrearUsuarioDTO();
		
		try {
			
			Long usuCodigo = modifyUserRequest.getUsuCodigo();
			List<SegRolDTO> listaRoles = modifyUserRequest.getListaRoles();
			Long sistema = modifyUserRequest.getSistema();
			Long compania = modifyUserRequest.getCompania();
			Long usuSession = modifyUserRequest.getUsuSession();
			
			businessDelegatorView.adicionarRolPermisoAUsuario(usuCodigo, listaRoles, sistema, compania, usuSession);
			
			resultadoCrearUsuarioDTO.setExito(true);
			resultadoCrearUsuarioDTO.setMensaje("Usuario actualizado con exito");
			
		} catch (Exception e) {
			resultadoCrearUsuarioDTO.setExito(false);
			resultadoCrearUsuarioDTO.setMensaje(e.getMessage());
		}
		
		return resultadoCrearUsuarioDTO;
	}
	
	
	/**
	 * Servicio REST de tipo POST que eliminar roles a un usuario.
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 * @since 1.8
	 * @param modifyUserRequest Clase obligatoria para actualizar el usuario. Aqui se especifican todos los datos y roles a eliminar.
	 * @see com.vortexbird.sentinel.controller.ModificarRolesDTO
	 * @throws Exception
	 * @return {@code ResultadoCrearUsuarioDTO} Respuesta en formato DTO que contiene un boolean de exito y un mensaje.
	 */
	@RequestMapping(value="/eliminarRolesUsuario", method = RequestMethod.POST)
	public ResultadoCrearUsuarioDTO eliminarRolesUsuario(@RequestBody ModificarRolesDTO modifyUserRequest) {
		
		ResultadoCrearUsuarioDTO resultadoCrearUsuarioDTO = new ResultadoCrearUsuarioDTO();
		
		try {
			
			Long usuCodigo = modifyUserRequest.getUsuCodigo();
			List<SegRolDTO> listaRoles = modifyUserRequest.getListaRoles();
			Long sistema = modifyUserRequest.getSistema();
			Long compania = modifyUserRequest.getCompania();
			Long usuSession = modifyUserRequest.getUsuSession();
			
			businessDelegatorView.eliminarRolPermisoAUsuario(usuCodigo, listaRoles, sistema, compania, usuSession);
			
			resultadoCrearUsuarioDTO.setExito(true);
			resultadoCrearUsuarioDTO.setMensaje("Usuario actualizado con exito");
			
		} catch (Exception e) {
			resultadoCrearUsuarioDTO.setExito(false);
			resultadoCrearUsuarioDTO.setMensaje(e.getMessage());
		}
		
		return resultadoCrearUsuarioDTO;
	}
	
	/**
	 * Servicio REST de tipo GET que consulta si un usuario existe.
	 * @author Camilo José Delgado Herrera
	 * @version 2018-06-22
	 * @param login
	 * @param dominio
	 * @return
	 */
	@RequestMapping(value="/consultarUsuarioPorLogin/{login}/{dominio}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody UsuarioDTO consultarUsuarioPorLogin(@PathVariable("login")String login,
															 @PathVariable("dominio")String dominio) {
		return segUsuarioLogic.consultarUsuarioPorLogin(login,dominio);
	}
	
	/**
	 * Servicio REST de tipo POST que crea un rol con las opciones asignadas
	 * @author Camilo José Delgado Herrera
	 * @version 2018-07-17
	 * @param rol
	 * @return
	 */
	@RequestMapping(value="/crearRol",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Boolean> crearRol(@RequestBody SegRolDTO rol) {
		try {
			segRolLogic.crearRol(rol);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Servicio REST de tipo GET que consulta todas las opciones de un sistema
	 * @author Camilo José Delgado Herrera
	 * @version 2018-07-17
	 * @param sistema
	 * @return
	 */
	@RequestMapping(value="/getOpcionesBySistema/{sistema}",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<SegOpcionDTO>> getOpcionesBySistema(
			@PathVariable("sistema")Long sistema
			) {
		try {
			List<SegOpcionDTO> opciones = segOpcionLogic.consultarOpcionesPorSistema(sistema);
			return new ResponseEntity<List<SegOpcionDTO>>(opciones, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Servicio REST de tipo GET que consulta todas las opciones de un rol
	 * @author Camilo José Delgado Herrera
	 * @version 2018-07-17
	 * @param rol
	 * @return
	 */
	@RequestMapping(value="/getOpcionesByRol/{rol}",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<SegOpcionDTO>> getOpcionesByRol(
			@PathVariable("rol")Long rol
			) {
		try {
			List<SegOpcionDTO> opciones = segOpcionLogic.consultarOpcionesPorRol(rol);
			return new ResponseEntity<List<SegOpcionDTO>>(opciones, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Servicio REST de tipo POST que crea un rol con las opciones asignadas
	 * @author Camilo José Delgado Herrera
	 * @version 2018-07-17
	 * @param rol
	 * @return
	 */
	@RequestMapping(value="/modificarRol",method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Boolean> modificarRol(@RequestBody SegRolDTO rol) {
		try {
			segRolLogic.modificarRol(rol);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
