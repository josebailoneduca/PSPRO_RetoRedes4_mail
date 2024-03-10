package josebailon.clientecorreo;

import java.io.IOException;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;

public class ClienteSMTPConexion {
 
	public static void start () {
	
		SMTPClient client = new SMTPClient();
		
	try {
	    int respuesta;
	    
	    //NOS CONECTAMOS AL PUERTO 25 por defecto
	    client.connect("localhost");// local server as mercury
	    
	    //client.connect("smtp.gmail.com");
        
	    System.out.print(client.getReplyString());
	    respuesta = client.getReplyCode();
	    System.out.print("codigo de respuesta: "+respuesta);
		
	    if (!SMTPReply.isPositiveCompletion(respuesta)) {
	    	client.disconnect();
	    	System.err.println("CONEXION RECHAZADA.");
	    	System.exit(1);
	    }
			
	    // REALIZAR ACCIONES, por ejemplo enviar un correo
		
	    // NOS DESCONECTAMOS
	    
	    client.disconnect();
		
	} catch (IOException e) {
	   System.err.println("NO SE PUEDE CONECTAR AL SERVIDOR.");		   
	   e.printStackTrace();
	   System.exit(2);
	}		
		
  }//main
}// ..
