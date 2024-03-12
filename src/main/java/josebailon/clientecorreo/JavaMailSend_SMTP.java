package josebailon.clientecorreo;

import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  


/**
 *  vista general
 *  https://support.google.com/a/topic/9202?hl=es&ref_topic=9197&sjid=17654871089098710219-EU
 *  
 *  configuracion cuenta
 * //https://support.google.com/mail/answer/7104828?hl=es
 * 
 *  password en dos pasos
 *  https://support.google.com/accounts/answer/185833?p=InvalidSecondFactor
 * 
 */

public class JavaMailSend_SMTP { 
	
	
public static void start() {  

	String host = "localhost";
	final String user="root2@localhost"; 
	final String password="12346"; 

	String to = "root12@localhost";
	
	//propiedades
	Properties props = new Properties();
	props.put("mail.smtp.host", host);
	//	If true, attempt to authenticate the user using the AUTH command. Defaults to false.
	props.put("mail.smtp.auth", true);
	props.put("mail.smtp.starttls.enable", true);
	props.put("mail.smtp.port", 25);

	props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");//version de TSL ojo!!
	
	
	//abrimos sesion
	Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {  
	
                  protected PasswordAuthentication getPasswordAuthentication() {  
                  return new PasswordAuthentication(user,password);  
                  }  

                  });  
        
	try {  
		 MimeMessage message = new MimeMessage(session);  
		 message.setFrom(new InternetAddress(user));  
		 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		 message.setSubject("Saludos despues  con TSL desde jose 1 a jose 2");  
		 message.setText("usando JavaMail API");  
		
		 Transport.send(message);  
		
		 System.out.println("message sent successfully...");  
	
	 } catch (MessagingException e) {
		 e.printStackTrace();
	   }  
   }  
} 