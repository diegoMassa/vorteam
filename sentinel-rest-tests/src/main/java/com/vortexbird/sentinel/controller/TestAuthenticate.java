package com.vortexbird.sentinel.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.vortexbird.sentinel.modelo.dto.UsuarioDTO;

public class TestAuthenticate {

	public static void main(String[] args) {
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			String url = "http://192.168.1.25:8080/repuestos-web/RepuestosAuth.do";
			
			AuthenticationRequest authenticationRequest = new AuthenticationRequest();
			authenticationRequest.setLogin("31978235");
			authenticationRequest.setPassword("010101");
			authenticationRequest.setDominio("1");
			
			AuthenticationResponse authenticationResponse = 
					restTemplate.postForObject(url, authenticationRequest, AuthenticationResponse.class, new HashMap<String, String>());
			
			if (authenticationResponse == null){
				
				throw new Exception("Respuesta del servicio null");
			}
			
			UsuarioDTO usuarioDTO = authenticationResponse.getUsuarioDTO();
			List<SistemaDTO> sistemas = authenticationResponse.getSistemas();
			
			
			System.out.println("Token: " + authenticationResponse.getToken() + "\n" 
					+ "Estado del usuario: " + usuarioDTO.getEstado() + "\n"
					+ "Codigo del usuario: " + usuarioDTO.getUsu_codigo() + "\n"
					+ "Última modificación Pwd: " + usuarioDTO.getUsu_ultmima_modificacion_pass() + "\n"
					+ "e-Mail: " + usuarioDTO.getUsu_correo() + "\n"
					+ "e-Mail Certificado: " + usuarioDTO.getEmailFueCertificado() + "\n"
					+ "mensaje: " + usuarioDTO.getMensajeExcepcion());
			
			System.out.println("Sistemas: ");
			if (sistemas!=null){
				for (SistemaDTO sistemaDTO : sistemas) {
					System.out.println(sistemaDTO.getNombre());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
