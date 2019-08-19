package com.vortexbird.comprobantepago.modelo.control.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.vortexbird.sentinel.controller.AuthenticationResponse;

@Service("EliminarTokensExpiradosLogic")
@PropertySource("classpath:system.properties")
@Scope("singleton")
public class EliminarTokensExpiradosLogic implements IEliminarTokensExpiradosLogic {

	private final Logger log = LoggerFactory.getLogger(EliminarTokensExpiradosLogic.class);

	@Value("${minutosDuracionToken}")
	private int minutosDuracionToken;

	public static final Map<String , AuthenticationResponse> objetosEnSesion = new HashMap<String, AuthenticationResponse>();

	/* (non-Javadoc)
	 * @see com.vortexbird.comprobantepago.modelo.control.jobs.IEliminarTokensExpiradosLogic#eliminarTokensExpirados()
	 */
	@Override
	public void eliminarTokensExpirados(){
		try {


			List<String> tokensAEliminar = new ArrayList<String>();

			Set<String> keys = objetosEnSesion.keySet();
			
			for (String key : keys) {
				
				Object objetoEnSesion = objetosEnSesion.get(key);

				if (objetoEnSesion!=null && objetoEnSesion instanceof AuthenticationResponse){

					AuthenticationResponse authenticationResponse = (AuthenticationResponse)objetoEnSesion;

					Date ahora = new Date();
					Date horaAuthenticacion = authenticationResponse.getFechaInicioSesion();

					Long diferencia = ahora.getTime() - horaAuthenticacion.getTime();
					diferencia = diferencia / 1000 /60;

					if (diferencia > minutosDuracionToken){
						tokensAEliminar.add(key);
					}


				}

			}

			//Se eliminan los tokens expirados
			for (String token : tokensAEliminar) {
				log.info("Eliminando de la sesión al token " + token);
				objetosEnSesion.remove(token);
			}


		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public int getMinutosDuracionToken() {
		return minutosDuracionToken;
	}

	public void setMinutosDuracionToken(int minutosDuracionToken) {
		this.minutosDuracionToken = minutosDuracionToken;
	}

	public Map<String, AuthenticationResponse> getObjetosensesion() {
		return objetosEnSesion;
	}
	

}
