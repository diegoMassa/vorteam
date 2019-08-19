package com.vortexbird.vorteam.utility;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMailUtility {

	private static Logger logger = LoggerFactory.getLogger("Cifrador");
	
	public static void sendMailFake(String emailFrom, String emailTo, String subject, String messageContent) throws Exception{
		
	}
	
	
	public static void sendMail(
			String recipients[], String subject, String message, File[] attachments, String recipientesOcultos[],
			Properties props, final String username, final String password) throws Exception{
		
		try {
			logger.info("############ INICIANDO PROCESO PARA ENVIAR ELECTRONICO ################");

			//Session session = Session.getDefaultInstance(props);
			Session session = Session.getInstance(props,
					  new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					  });

			MimeMessage message2 = new MimeMessage(session);
			message2.setFrom(new InternetAddress(username));
			
			InternetAddress[] addressTo = new InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++) {
				addressTo[i] = new InternetAddress(recipients[i]);
			}
			message2.setRecipients(Message.RecipientType.TO, addressTo);
			
			//Si hay recipientes ocultos
			if (recipientesOcultos != null && recipientesOcultos.length>0) {
				InternetAddress[] addressBcc = new InternetAddress[recipientesOcultos.length];
				for (int i = 0; i < recipientesOcultos.length; i++) {
					addressBcc[i] = new InternetAddress(recipientesOcultos[i]);
				}
				message2.setRecipients(Message.RecipientType.BCC, addressBcc);
			}
			
			message2.setSubject(subject);
			message2.setText(message);
			message2.setHeader("X-Mailer", "HMCL");
			
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message, "text/html;  charset=ISO-8859-1");

            Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			
			// Part two is attachment
			if (attachments!=null && attachments.length>0) {
				for(File attachment : attachments){
					if (attachment.isFile() && attachment.exists()){
						messageBodyPart = new MimeBodyPart();
						String filename = attachment.getName();
						DataSource source = new FileDataSource(attachment);
						messageBodyPart.setDataHandler(new DataHandler(source));
						messageBodyPart.setFileName(filename);
						multipart.addBodyPart(messageBodyPart);
					}
				}
			}
			// Put parts in message
			message2.setContent(multipart);
			message2.saveChanges();
			
			// Send the message
			Transport transport = session.getTransport("smtp");
			transport.connect(username, password);
			transport.sendMessage(message2, message2.getAllRecipients());
			transport.close();
			
		} catch (Exception e) {
			logger.error("Error enviando el correo electronico, el error fue " + e.getMessage(), e);
			e.printStackTrace();
		}
		logger.info("############ PROCESO PARA ENVIAR CORREO ELECTRONICO FINALIZADO CON EXITO ################");
	}
	
}
