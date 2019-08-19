package com.vortexbird.sentinel.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.vortexbird.sentinel.modelo.dto.UsuarioDTO;

public class TestCreateUserWithRoles {

	public static void main(String[] args) {
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			String url = "http://192.168.1.148:9090/sentinel-web/controller/securityServices/createUserWithRoles";
			
			CreateUserRequest createUserRequest = new CreateUserRequest();
			
			String usuApellidos = "BEJARANO"; 
			String usuCodigoInterno = "HMCL";
			String usuConstrasena = "123456"; 
			String usuCorreo = "info@vortexbird.com"; 
			String usuLogin = "jbejarano"; 
			String usuNombres = "JOHAN";
			List<String> rolesAsignados = new ArrayList<String>(); 
			
			String compania = "0"; 
			String sistema = "1";
			
			createUserRequest.setUsuApellidos(usuApellidos);
			createUserRequest.setUsuCodigoInterno(usuCodigoInterno);
			createUserRequest.setUsuConstrasena(usuConstrasena);
			createUserRequest.setUsuCorreo(usuCorreo);
			
			createUserRequest.setUsuLogin(usuLogin);
			createUserRequest.setUsuNombres(usuNombres);
			
			createUserRequest.setRolesAsignados(rolesAsignados);
			createUserRequest.setCompania(compania);
			createUserRequest.setSistema(sistema);
			
			 
			UsuarioDTO usuarioDTO = restTemplate.postForObject(url, createUserRequest, UsuarioDTO.class, new HashMap<String, String>());
			
			if (usuarioDTO == null){
				throw new Exception("Respuesta del servicio null");
			}
			
			
			System.out.println("Estado del usuario: " + usuarioDTO.getEstado() + " "
					+ "Codigo del usuario " + usuarioDTO.getUsu_codigo() + " "
							+ "mensaje: " + usuarioDTO.getMensajeExcepcion());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
