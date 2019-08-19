package com.vortexbird.sentinel.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.sentinel.modelo.SegUsuario;
import com.vortexbird.sentinel.modelo.VinCamposParametrizables;
import com.vortexbird.sentinel.modelo.control.IVinCamposParametrizablesLogic;
import com.vortexbird.sentinel.modelo.dto.UsuarioDTO;

@Scope("singleton")
@Service("VerificacionEmailUsuarioLogic")
public class VerificacionEmailUsuarioLogic implements IVerificacionEmailUsuarioLogic{

	@Autowired
	private IVinCamposParametrizablesLogic camposParametrizablesLogic;
	
	private static final Logger log = LoggerFactory.getLogger(VerificacionEmailUsuarioLogic.class);
	
	/* (non-Javadoc)
	 * @see co.com.hero.sso.modelo.control.IVerificacionEmailUsuarioLogic#enviarCorreoVerificacionEmail(com.vortexbird.sentinel.modelo.dto.UsuarioDTO)
	 */
	@Override
	@Transactional(readOnly=true)
	public void notificarCambioContrasena(SegUsuario usuario, String nuevaConstrasena) {

		List<String> emails = null;
		//String[] arregloCorreos = null;
		String nombreUsuario = "";

		VinCamposParametrizables parametroHostServerMail = null;
		VinCamposParametrizables parametroAuth = null;
		VinCamposParametrizables parametroUserServer = null;
		VinCamposParametrizables parametroPassServer = null;
		VinCamposParametrizables parametroEsGmail = null;


		String hostServerMail = null;
		String auth = null;
		String userServer = null;
		String passServer = null;
		String esGmail = null;

		try {


			emails = new ArrayList<String>();
			parametroHostServerMail = camposParametrizablesLogic.getVinCamposParametrizables(Constantes.ID_HOST_SERVER_CORREO);
			parametroAuth =  camposParametrizablesLogic.getVinCamposParametrizables(Constantes.ID_AUTORIZACION_CORREO);
			parametroUserServer = camposParametrizablesLogic.getVinCamposParametrizables(Constantes.ID_USUARIO_SERVIDOR_CORREO);
			parametroPassServer = camposParametrizablesLogic.getVinCamposParametrizables(Constantes.ID_PASSWORD_SERVIDOR_CORREO);
			parametroEsGmail = camposParametrizablesLogic.getVinCamposParametrizables(Constantes.ID_ES_GMAIL_CORREO);

			if(parametroHostServerMail == null || parametroHostServerMail.getValor() == null || parametroHostServerMail.getValor().trim().equals("")){
				throw new Exception("Error en el parametro "+ Constantes.ID_HOST_SERVER_CORREO);
			}

			if(parametroAuth == null || parametroAuth.getValor() == null || parametroAuth.getValor().trim().equals("")){
				throw new Exception("Error en el parametro "+ Constantes.ID_AUTORIZACION_CORREO);				
			}

			if(parametroUserServer == null || parametroUserServer.getValor() == null || parametroUserServer.getValor().trim().equals("")){
				throw new Exception("Error en el parametro "+ Constantes.ID_USUARIO_SERVIDOR_CORREO);				 
			}

			if(parametroPassServer == null || parametroPassServer.getValor() == null || parametroPassServer.getValor().trim().equals("")){
				throw new Exception("Error en el parametro "+ Constantes.ID_PASSWORD_SERVIDOR_CORREO);				 
			}

			if(parametroEsGmail == null || parametroEsGmail.getValor() == null || parametroEsGmail.getValor().trim().equals("")){
				throw new Exception("Error en el parametro "+ Constantes.ID_ES_GMAIL_CORREO);				 
			}

			hostServerMail = parametroHostServerMail.getValor().trim();
			auth = parametroAuth.getValor().trim();
			userServer = parametroUserServer.getValor().trim();
			passServer = parametroPassServer.getValor().trim();
			esGmail = parametroEsGmail.getValor().trim();


			String[] arregloCorreos = {usuario.getUsuCorreo()};

			//Se recorre el arreglo de correos y se llena la lista de correos de destino
			for(int i=0 ; i<arregloCorreos.length;i++){
				emails.add(arregloCorreos[i].trim());
			}


			nombreUsuario =  ( 
					((usuario.getUsuApellidos() == null || usuario.getUsuApellidos().trim().equals("")) ? "" :  usuario.getUsuApellidos() + " ") +
					((usuario.getUsuNombres() == null || usuario.getUsuNombres().trim().equals("")) ? "" :  usuario.getUsuNombres() + " "));

			String subject = "Hero DataPRO - Cambio de constraseña";
			

			String mensaje = "<p class=\"p1\">Hola "+nombreUsuario+",</p> " +
				"<h2 class=\"p1\"><strong>Su solicitud de cambio de contrasena, ha sido procesada exitosamente en Hero DataPRO!</strong></h2> " +
				"<p class=\"p1\">Con nuestra plataforma integrada, pordr&aacute;s acceder a todas las funcionalidades desde un solo punto de acceso de manera muy sencilla!</p> " +
				"<h2 class=\"p1\" style=\"text-align: center;\"><strong><span style=\"color: #808080;\">Usuario: "+ usuario.getUsuCorreo() +"</span></strong></h2> " +
				"<h2 class=\"p1\" style=\"text-align: center;\"><strong><span style=\"color: #808080;\">Nueva contraseña: "+ nuevaConstrasena +"</span></strong></h2> " +
//				"<h1 style=\"text-align: center;\"> " +
//				"<a href=\"http://www.google.com\" style=\"display: inline-block; border: none; color: #ffffff; border-radius: 3px 3px 3px 3px; " +
//				"-webkit-border-radius: 3px 3px 3px 3px; -moz-border-radius: 3px 3px 3px 3px; font-family: Verdana; width: auto; " + 
//				"height: auto; font-size: 23px; padding: 14px 53px; text-shadow: 0 1px 0 #FFFFFF; background-color: #de0707;\"> Activa tu Cuenta Ahora!&nbsp;</a></h1> " +
				"<p>&nbsp;</p> " +
				"<hr /> " +
				"<p>&nbsp;</p> " +
				"<p class=\"p1\"><span class=\"s1\">Disclaimer:</span></p> " +
				"<p class=\"p2\">&nbsp;</p> " +
				"<p class=\"p1\"><span class=\"s1\">The information in this e-mail, and any attachments therein, is privileged and confidential and for use by the addressee only. "
				+ "If you are not the intended recipient, please return the e-mail to the sender and delete it from your computer. If you have received this communication in error, "
				+ "please be informed that any review, dissemination, distribution, or copying of this message is strictly prohibited.The sender confirms that HMCL Colombia SAS shall "
				+ "not be responsible if this email message is used for any indecent, unsolicited or illegal purposes, which are in violation of any existing laws and the same shall "
				+ "solely be the responsibility of the sender and that HMCL Colombia SAS shall at all times be indemnified of any civil and/ or criminal liabilities or consequences "
				+ "thereof.Although we attempt to sweep e-mail and attachments for viruses, we do not guarantee that either are virus-free and accept no liability for any damage sustained as a result of viruses.</span></p>";


			File attachment = null;
			EnviarCorreo.sendMail(hostServerMail, auth, userServer, passServer, esGmail, emails.toArray(new String[0]), subject, mensaje, attachment, null);


		} catch (Exception e) {
			log.error("Error al enviar correo para verificación del e-mail del usuario.", e);
		}

	}

}
