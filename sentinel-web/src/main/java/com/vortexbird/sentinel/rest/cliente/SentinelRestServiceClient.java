package com.vortexbird.sentinel.rest.cliente;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.faces.bean.ManagedProperty;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proto.server.ws.AdminUsuarios;
import com.proto.server.ws.AdminUsuarios_Service;
import com.proto.server.ws.ConsultaUsuario;
import com.proto.server.ws.ConsultaUsuarioResponse;
import com.proto.server.ws.CrearUsuario;
import com.proto.server.ws.CrearUsuarioResponse;
import com.proto.server.ws.ModificarUsuario;
import com.proto.server.ws.ModificarUsuarioResponse;
import com.vortexbird.sentinel.modelo.dto.CrmUsuarioDTO;
import com.vortexbird.sentinel.modelo.dto.RollCrmDTO;
import com.vortexbird.sentinel.modelo.dto.RollesVcloudDTO;
import com.vortexbird.sentinel.modelo.dto.SellCiudadDTO;
import com.vortexbird.sentinel.modelo.dto.SellPersonaDTO;
import com.vortexbird.sentinel.modelo.dto.SellSucursalDTO;
import com.vortexbird.sentinel.modelo.dto.UsuarioCRMDTO;
import com.vortexbird.sentinel.utilities.Utilities;

import entidad.ws.server.proto.com.resultado.Resultado;

@Component("SentinelRestServiceClient")
@Scope("singleton")
public class SentinelRestServiceClient implements ISentinelRestServiceClient {

	private static Logger log = LoggerFactory.getLogger(SentinelRestServiceClient.class);

	@Autowired
	private Properties configPropsSent;

	// Consulto el Metodo de Rolles
	@Override
	public List<RollCrmDTO> consultarRollXUsuarioCRM(String cedula) throws Exception {
		RollCrmDTO rollesCrm = null;
		log.info("Consulto consultarRollXUsuarioCRM");
		String URL = configPropsSent.getProperty("rest.urlConsultarRollesUser");
		RestTemplate restTemplate = new RestTemplate();
		rollesCrm = restTemplate.postForObject(URL, cedula, RollCrmDTO.class, new HashMap<String, String>());

		log.info("Sale del metodo Rolles de CRM");
		return rollesCrm.getData();
	}

	// Consulto el Metodo de Rolles
	@Override
	public List<RollCrmDTO> consultarRollesCRM() throws Exception {
		RollCrmDTO rollesCrm = null;

		log.info("Consulto Rolles de CRM");

		String URL = configPropsSent.getProperty("rest.urlRollesCrm");
		RestTemplate restTemplate = new RestTemplate();
		rollesCrm = restTemplate.getForObject(URL, RollCrmDTO.class);

		log.info("Sale del metodo Rolles de CRM");
		return rollesCrm.getData();
	}

	// Meotdo que retorna los parametros para enviar a un servicio REST
	// Se debe pasar en el orden que debe quedar en la cadena
	public String armarCadenaParametrosRest(String url, LinkedHashMap<String, String> map) throws Exception {

		String cadenaParametros = "";
		String urlServicio = "";

		Integer contador = 0;

		try {

			if (url == null || url.trim().equals("")) {
				throw new Exception("Se debe tener una url para llamar al servicio");
			}

			// Si hay valor en el map ingresado se recorre
			if (map != null) {
				// Se recorre el map para sacar cada llave y valor
				for (Map.Entry<String, String> entry : map.entrySet()) {
					// Si es el primer parametro no se coloca & de lo contrario
					// se coloca & al inicio
					if (contador == 0) {
						cadenaParametros = cadenaParametros + "?" + entry.getKey() + "=" + entry.getValue() + "";
					} else {
						cadenaParametros = cadenaParametros + "&" + entry.getKey() + "=" + entry.getValue() + "";
					}
					contador++;
				}
			}

			// Se concatena la url con la cadena de parametros para tener la url
			// definitiva
			urlServicio = url + cadenaParametros;

			return urlServicio;

		} catch (Exception e) {
			throw e;
		}

	}

	// Meotdo que retorna los parametros para enviar a un servicio REST
	// Se debe pasar en el orden que debe quedar en la cadena
	public String concatenarArreglo(String[] arreglo, String separador) throws Exception {

		String cadenaArreglo = "";
		Integer contador = 0;

		try {

			if (arreglo == null) {
				throw new Exception("Se debe tener valores para el arreglo");
			}

			if (separador == null) {
				throw new Exception("Se debe tener un separador");
			}

			// Se recorre elarreglo a separar
			for (String texto : arreglo) {
				// Si es el primer parametro no se coloca separador de lo
				// contrario se coloca
				if (contador == 0) {
					cadenaArreglo = cadenaArreglo + texto;
				} else {
					cadenaArreglo = cadenaArreglo + separador + texto;
				}
				contador++;
			}

			return cadenaArreglo;

		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void crearUsuarioCRM(UsuarioCRMDTO usuarios, String somRolesCRM) throws Exception {

		// Mapear a un objeto
		ObjectMapper mapper = new ObjectMapper();
		// Recibe la respuesta del servicio
		String respuesta = "";
		RestTemplate restTemplate = new RestTemplate();

		// URL base para llamar al servicio
		String url = "";
		// Url definitica para llamar al servicio
		String urlServicio = "";

		try {

			log.info("crearUsuarioCRM userName: " + usuarios.getCedula() + " Inicio crearUsuarioCRM usuarios: ["
					+ usuarios.toString() + "] roles:" + somRolesCRM.toString());

			// Se obtiene la URL del archivo de propiedades
			url = configPropsSent.getProperty("rest.urlCreateUserCrm");

			// Se colocan las llaves y valores de los parametros
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("userName", usuarios.getCedula());
			map.put("Password", usuarios.getPassword());
			map.put("Nombres", usuarios.getNombres());
			map.put("Apellidos", usuarios.getApellidos());
			map.put("Correo", usuarios.getCorreo());
			map.put("SellOut", usuarios.getSellout());
			map.put("Rol", somRolesCRM);
			map.put("Sucursales", usuarios.getSucursales());

			// Se obtiene la URL del servicio con los parametros
			urlServicio = armarCadenaParametrosRest(url, map);
			log.info("crearUsuarioCRM userName: " + usuarios.getCedula() + " URL resultante: [" + urlServicio + "]");

			// Se llama al servicio y se obtiene un resultado
			respuesta = restTemplate.getForObject(urlServicio, String.class);
			// Se captura y muestra la excepción en caso de no encontrarse
			// respuesta
			if (respuesta == null || respuesta.trim().isEmpty()) {
				throw new Exception("No se obtuvo respuesta del servicio crearUsuarioCRM");
			}

			// Se tranforma el String resultante del servicio a un objeto
			CrmUsuarioDTO crmUsuarioDTO = mapper.readValue(respuesta, CrmUsuarioDTO.class);
			log.info("crearUsuarioCRM userName: " + usuarios.getCedula() + " Resultado: [" + crmUsuarioDTO.toString()
					+ "]");

		} catch (org.springframework.web.client.HttpClientErrorException e) {
			CrmUsuarioDTO crmUsuarioDTO = mapper.readValue(e.getResponseBodyAsString(), CrmUsuarioDTO.class);
			log.error("crearUsuarioCRM userName: " + usuarios.getCedula() + " " + crmUsuarioDTO.getData(), e);
			throw e;
		} catch (RestClientException e) {
			log.error("crearUsuarioCRM userName: " + usuarios.getCedula() + " " + e.getMessage(), e);
			throw e;
		} catch (JsonParseException e) {
			log.error("crearUsuarioCRM userName: " + usuarios.getCedula() + " " + e.getMessage(), e);
			throw e;
		} catch (JsonMappingException e) {
			log.error("crearUsuarioCRM userName: " + usuarios.getCedula() + " " + e.getMessage(), e);
			throw e;
		} catch (IOException e) {
			log.error("crearUsuarioCRM userName: " + usuarios.getCedula() + " " + e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error("crearUsuarioCRM userName: " + usuarios.getCedula() + " " + e.getMessage(), e);
			throw e;
		}

	}

	@Override
	public void modificarUsuarioCRM(UsuarioCRMDTO usuarios, String somRolesCRM) throws Exception {

		// Mapear a un objeto
		ObjectMapper mapper = new ObjectMapper();
		// Recibe la respuesta del servicio
		String respuesta = "";
		RestTemplate restTemplate = new RestTemplate();

		// URL base para llamar al servicio
		String url = "";
		// Url definitica para llamar al servicio
		String urlServicio = "";

		try {

			log.info("modificarUsuarioCRM userName: " + usuarios.getCedula() + " Inicio modificarUsuarioCRM usuarios: ["
					+ usuarios.toString() + "] roles:" + somRolesCRM.toString());

			// Se obtiene la URL del archivo de propiedades
			url = configPropsSent.getProperty("rest.urlUpdateUserCrm");

			// Se colocan las llaves y valores de los parametros
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("userName", usuarios.getCedula());
			map.put("Password", usuarios.getPassword());
			map.put("Nombres", usuarios.getNombres());
			map.put("Apellidos", usuarios.getApellidos());
			map.put("Correo", usuarios.getCorreo());
			map.put("SellOut", usuarios.getSellout());
			map.put("Rol", somRolesCRM);
			map.put("Sucursales", usuarios.getSucursales());

			// Se obtiene la URL del servicio con los parametros
			urlServicio = armarCadenaParametrosRest(url, map);
			log.info(
					"modificarUsuarioCRM userName: " + usuarios.getCedula() + " URL resultante: [" + urlServicio + "]");

			// Se llama al servicio y se obtiene un resultado
			respuesta = restTemplate.getForObject(urlServicio, String.class);
			// Se captura y muestra la excepción en caso de no encontrarse
			// respuesta
			if (respuesta == null || respuesta.trim().isEmpty()) {
				throw new Exception("No se obtuvo respuesta del servicio crearUsuarioCRM");
			}

			// Se tranforma el String resultante del servicio a un objeto
			CrmUsuarioDTO crmUsuarioDTO = mapper.readValue(respuesta, CrmUsuarioDTO.class);
			log.info("modificarUsuarioCRM userName: " + usuarios.getCedula() + " Resultado: ["
					+ crmUsuarioDTO.toString() + "]");

		} catch (org.springframework.web.client.HttpClientErrorException e) {
			CrmUsuarioDTO crmUsuarioDTO = mapper.readValue(e.getResponseBodyAsString(), CrmUsuarioDTO.class);
			log.error("modificarUsuarioCRM userName:" + usuarios.getCedula() + " " + crmUsuarioDTO.getData(), e);
			throw e;
		} catch (RestClientException e) {
			log.error("modificarUsuarioCRM userName:" + usuarios.getCedula() + " " + e.getMessage(), e);
			throw e;
		} catch (JsonParseException e) {
			log.error("modificarUsuarioCRM userName:" + usuarios.getCedula() + " " + e.getMessage(), e);
			throw e;
		} catch (JsonMappingException e) {
			log.error("modificarUsuarioCRM userName:" + usuarios.getCedula() + " " + e.getMessage(), e);
			throw e;
		} catch (IOException e) {
			log.error("modificarUsuarioCRM userName:" + usuarios.getCedula() + " " + e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error("modificarUsuarioCRM userName:" + usuarios.getCedula() + " " + e.getMessage(), e);
			throw e;
		}

	}

	@Override
	public List<RollCrmDTO> consultarRolesCRM(String cedula) throws Exception {

		// Mapear a un objeto
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		RollCrmDTO rollesCrm = null;

		// URL base para llamar al servicio
		String url = "";
		// Url definitica para llamar al servicio
		String urlServicio = "";

		List<RollCrmDTO> lstRoles = new ArrayList<RollCrmDTO>();

		try {

			if (cedula == null || cedula.trim().equals("")) {
				throw new Exception("Se debe ingresar una cédula para la consulta");
			}

			log.info("consultarRolesCRM userName: " + cedula + " Inicio consultarRolesCRM usuarios: [" + cedula + "]");

			// Se obtiene la URL del archivo de propiedades
			url = configPropsSent.getProperty("rest.urlConsultarRollesUser");

			// Se colocan las llaves y valores de los parametros
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("cedula", cedula);

			// Se obtiene la URL del servicio con los parametros
			urlServicio = armarCadenaParametrosRest(url, map);
			log.info("consultarRolesCRM userName: " + cedula + " URL resultante: [" + urlServicio + "]");

			rollesCrm = restTemplate.postForObject(urlServicio, cedula, RollCrmDTO.class,
					new HashMap<String, String>());

			if (rollesCrm != null && rollesCrm.getData() != null) {
				lstRoles = rollesCrm.getData();
			}

			log.info("consultarRolesCRM userName: " + cedula + " Resultado: [" + rollesCrm.toString() + "]");

			return lstRoles;

		} catch (org.springframework.web.client.HttpClientErrorException e) {
			CrmUsuarioDTO crmUsuarioDTO = mapper.readValue(e.getResponseBodyAsString(), CrmUsuarioDTO.class);
			log.error("consultarRolesCRM userName:" + cedula + " " + crmUsuarioDTO.getData(), e);
			throw e;
		} catch (RestClientException e) {
			log.error("consultarRolesCRM userName:" + cedula + " " + e.getMessage(), e);
			throw e;
		} catch (JsonParseException e) {
			log.error("consultarRolesCRM userName:" + cedula + " " + e.getMessage(), e);
			throw e;
		} catch (JsonMappingException e) {
			log.error("consultarRolesCRM userName:" + cedula + " " + e.getMessage(), e);
			throw e;
		} catch (IOException e) {
			log.error("consultarRolesCRM userName:" + cedula + " " + e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error("consultarRolesCRM userName:" + cedula + " " + e.getMessage(), e);
			throw e;
		}

	}

	@Override
	public SellSucursalDTO ConsultarTodasLasSucursales() throws Exception {
		SellSucursalDTO respuesta = null;

		String URL = configPropsSent.getProperty("rest.urlAllSucursales");

		RestTemplate restTemplate = new RestTemplate();
		respuesta = restTemplate.getForObject(URL, SellSucursalDTO.class);

		return respuesta;

	}

	@Override
	public SellPersonaDTO consultarPersonaByCedula(String cedula) throws Exception {
		SellPersonaDTO respusta = null;

		String URL = configPropsSent.getProperty("rest.consultarPersonaByCedula");
		RestTemplate restTemplate = new RestTemplate();
		respusta = restTemplate.postForObject(URL, cedula, SellPersonaDTO.class, new HashMap<String, String>());

		return respusta;
	}

	@Override
	public SellCiudadDTO consultarCiudades() throws Exception {
		SellCiudadDTO respusta = null;

		String URL = configPropsSent.getProperty("rest.consultarCiudades");
		RestTemplate restTemplate = new RestTemplate();
		respusta = restTemplate.getForObject(URL, SellCiudadDTO.class);

		return respusta;
	}

	@Override
	public void guardarAsesor(SellPersonaDTO personaDTO) throws Exception {

		// 1---> se creo correctamente
		// Cualquier otra respuesta es error
		String respustaSellout = "";
		String URL = configPropsSent.getProperty("rest.crearOActualizarPersonaSellout");
		RestTemplate restTemplate = new RestTemplate();
		respustaSellout = restTemplate.postForObject(URL, personaDTO, String.class, new HashMap<String, String>());
		log.info("Respuesta de creacion Sellout para la cedula: " + personaDTO.getNumeroDocumento() + "Es :"
				+ respustaSellout);

	}

	public void crearUsuarioVCloud(UsuarioCRMDTO crmUser, String somRolesCRM, QName SERVICE_NAME,
			String[] sucursalesSeleccionadas, String url, long puntoDeVenta, String user, String pass)
			throws Exception {

		// consulta el servicio WEB
		AdminUsuarios_Service ss = new AdminUsuarios_Service(new URL(url + "?wsdl"), SERVICE_NAME);
		AdminUsuarios port = ss.getAdminUsuariosPort();
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);

		CrearUsuario usuario = new CrearUsuario();
		usuario.setUsuario(crmUser.getCedula());
		usuario.setContrasena(crmUser.getPassword());
		usuario.setApellidos(crmUser.getApellidos());
		if (puntoDeVenta == 0) {
			usuario.setCodigoSiic(sucursalesSeleccionadas[0]);
			usuario.setCodigoConcesionario("");
		} else {
			usuario.setCodigoSiic("");
			usuario.setCodigoConcesionario(puntoDeVenta + "");
		}

		usuario.setEMail(crmUser.getCorreo());
		usuario.setNombres(crmUser.getNombres());
		usuario.setNroCelular(crmUser.getCelular());
		usuario.setNroDocumento(crmUser.getCedula());
		usuario.setPerfilId(Integer.parseInt(somRolesCRM));

		CrearUsuarioResponse r = port.crearUsuario(usuario, user, pass);

		log.info(r.getXmlResultado().getCodigo() + " crearUsuarioVCloud");
		log.info(r.getXmlResultado().getMensaje() + " crearUsuarioVCloud");
		if (!r.getXmlResultado().getCodigo().equals("1")) {
			throw new Exception("No se pudo crear en Vcloud debido a "+ r.getXmlResultado().getCodigo());
		}
	}

	public String consultarUsuarioVCloud(UsuarioCRMDTO crmUser, String somRolesCRM, QName SERVICE_NAME,
			String[] sucursalesSeleccionadas, String url, long puntoDeVenta, String user, String pass)
			throws Exception {

		// consulta el servicio WEB
		AdminUsuarios_Service ss = new AdminUsuarios_Service(new URL(url + "?wsdl"), SERVICE_NAME);
		AdminUsuarios port = ss.getAdminUsuariosPort();
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);

		ConsultaUsuario usuario = new ConsultaUsuario();
		usuario.setUsuario(crmUser.getCedula());
		ConsultaUsuarioResponse r = port.consultaUsuario(usuario, user, pass);

		log.info(r.getXmlResultado().getCodigo() + " consultarUsuarioVCloud");
		log.info(r.getXmlResultado().getMensaje() + " consultarUsuarioVCloud");
		return r.getXmlResultado().getCodigo();
	}

	public void actualizarUsuarioVCloud(UsuarioCRMDTO crmUser, String somRolesCRM, QName SERVICE_NAME,
			String[] sucursalesSeleccionadas, String url, long puntoDeVenta, String user, String pass)
			throws Exception {

		// consulta el servicio WEB
		AdminUsuarios_Service ss = new AdminUsuarios_Service(new URL(url + "?wsdl"), SERVICE_NAME);
		AdminUsuarios port = ss.getAdminUsuariosPort();
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);

		ModificarUsuario usuario = new ModificarUsuario();
		usuario.setUsuario(crmUser.getCedula());
		usuario.setApellidos(crmUser.getApellidos());
		usuario.setContrasena(crmUser.getPassword());
		if (puntoDeVenta == 0) {
			usuario.setCodigoSiic(sucursalesSeleccionadas[0]);
			usuario.setCodigoConcesionario("");
		} else {
			usuario.setCodigoSiic("");
			usuario.setCodigoConcesionario(puntoDeVenta + "");
		}

		usuario.setEMail(crmUser.getCorreo());
		usuario.setNombres(crmUser.getNombres());
		usuario.setNroCelular(crmUser.getCelular());
		usuario.setNroDocumento(crmUser.getCedula());
		usuario.setPerfilId(Integer.parseInt(somRolesCRM));

		ModificarUsuarioResponse r = port.modificarUsuario(usuario, user, pass);

		log.info(r.getXmlResultado().getCodigo() + " actualizarUsuarioVCloud");
		log.info(r.getXmlResultado().getMensaje() + " actualizarUsuarioVCloud");

		if (!r.getXmlResultado().getCodigo().equals("1")) {
			throw new Exception("No se pudo crear en Vcloud debido a "+ r.getXmlResultado().getCodigo());
		}
	}

	public long getSucuXPuntoVenta(String[] sucursalesSeleccionadas) throws Exception {

		// 1---> se creo correctamente
		// Cualquier otra respuesta es error
		long respustaSellout = 0;
		String URL = configPropsSent.getProperty("rest.getSucuXPuntoVenta");
		RestTemplate restTemplate = new RestTemplate();
		respustaSellout = restTemplate.postForObject(URL, sucursalesSeleccionadas, Long.class);

		return respustaSellout;

	}
}
