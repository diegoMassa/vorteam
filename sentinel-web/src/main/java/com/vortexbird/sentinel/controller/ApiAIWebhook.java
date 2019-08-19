package com.vortexbird.sentinel.controller;

/***********************************************************************************************************************
 *
 * API.AI Java SDK - client-side libraries for API.AI
 * =================================================
 *
 * Copyright (C) 2017 by Speaktoit, Inc. (https://www.speaktoit.com)
 * https://www.api.ai
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************/

import java.util.HashMap;

import javax.servlet.annotation.WebServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import ai.api.model.Fulfillment;
import ai.api.web.AIWebhookServlet;

@WebServlet("/webhook")
public class ApiAIWebhook extends AIWebhookServlet {
	private static final long serialVersionUID = 1L;

	private final Logger log = LoggerFactory.getLogger(ApiAIWebhook.class);
	
	@Override
	protected void doWebhook(final AIWebhookRequest input, final Fulfillment output) {
		
		final String identificacion = input.getResult().getParameters().get("identificacion").getAsString();
		final String correo = input.getResult().getParameters().get("correo").getAsString();
		final String nombreUsuario = input.getResult().getParameters().get("usuario").getAsString();
		
		
		String respuesta = "Gracias "+nombreUsuario+". Procederemos a verificar la información. "
				+ "De ser correcta, le llegará una nueva contraseña generada, al correo electrónico "+correo+".";
		
		//output.setSpeech("You said: " + input.getResult().getFulfillment().getSpeech());
		output.setSpeech(respuesta);
		output.setDisplayText(respuesta);
		output.setSource("vortexbird");
		
		
		new Thread(){
			public void run() {
				
				String action = input.getResult().getAction();
				Integer statusCode = input.getStatus().getCode();
				
				if (statusCode == 200 && action.equals("cambiar.contrasena")){
					System.out.println("Se cambiará la contrasena...");
					
					invocarServicioCambioContrasena(identificacion, correo);
					
				}
				
			};
		}.start();
	}
	
	private void invocarServicioCambioContrasena(String identificacion, String correo){
		try {
			
			RestTemplate restTemplate = new RestTemplate();
			
			String url = "http://localhost:8080/sentinel-web/controller/securityServices/cambiarContrasena";
			
			ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
			changePasswordRequest.setEmail(correo);
			changePasswordRequest.setIdentificacion(identificacion);
			
			ChangePasswordResponse changePasswordResponse = 
					restTemplate.postForObject(url, changePasswordRequest, ChangePasswordResponse.class, new HashMap<String, String>());
			
			if (changePasswordResponse == null){
				throw new Exception ("No se pudo obtener respuesta al servicio de cambio de contrasena...changePasswordResponse=null");
			}
			
		} catch (Exception e) {
			log.error("Error invocando el servicio de cambio de contrasena: ", e);
		}
	}
}
