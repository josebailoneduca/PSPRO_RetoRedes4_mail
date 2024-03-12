package josebailon.clientecorreo;



import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import com.sun.mail.pop3.POP3Store;

public class JavaMailReceive_POP3{

 public static void start() {
	 
	 // String pop3Host = "pop3.gmail.com";
	  String pop3Host = "localhost";
	  String storeType = "pop3";
	  String user= "root2";
	  String password= "1234";
	  
  try {
	  
	  //propiedades y sesion
   Properties properties = new Properties();
   properties.put("mail.pop3.host", pop3Host);
   properties.put("mail.pop3.rsetbeforequit", true);
   Session emailSession = Session.getInstance(properties);

   	// cogemos el contenedor para el protocolo 
   POP3Store emailStore = (POP3Store) emailSession.getStore(storeType);
   emailStore.connect(user, password);

   	//pedimos la carpeta para el buzon de entrada
   Folder emailFolder = emailStore.getFolder("INBOX");
   emailFolder.open(Folder.READ_ONLY);

   	//cogemos los mensajes
   Message[] messages = emailFolder.getMessages();
   System.out.println("Cantidad de mensajes: "+messages.length);
   for (int i = 0; i < messages.length; i++) {
	Message message = messages[i];
	message.getReceivedDate();
	System.out.println("---------------------------------");
	System.out.println("Email Number " + (i + 1));
	System.out.println("Subject: " + message.getSubject());
	System.out.println("From: " + message.getFrom()[0]);
	System.out.println("To: " + message.getRecipients(Message.RecipientType.TO));
	System.out.println("Text: " + message.getContent().toString());
	
	
   }

   //cerrando
   emailFolder.close(false);
   emailStore.close();

  }  catch (NoSuchProviderException e) {e.printStackTrace();} 
     catch (MessagingException e) {e.printStackTrace();}
     catch (IOException e) {e.printStackTrace();}
 }

 
}