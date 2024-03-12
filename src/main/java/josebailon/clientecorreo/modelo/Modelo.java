/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package josebailon.clientecorreo.modelo;

import com.sun.mail.pop3.POP3Store;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import josebailon.clientecorreo.controlador.Controlador;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public class Modelo {

    private Controlador control;
    private Configuracion config;
    private Almacen almacen;
 

    public void setControlador(Controlador c) {
        control = c;
        config = new Configuracion();
        almacen = new Almacen(this);
    }

    public boolean cargarConfiguracion() {
        if (config.cargarConfiguracion()) {
            return configuracionCorrecta();
        } else {
            return false;
        }
    }

    private boolean configuracionCorrecta() {
        String usuario = config.getValor(Configuracion.K_USUARIO);
        return (usuario.length() > 3);
    }

    public boolean guardarConfiguracion(Properties propiedades) {
        return config.guardarConfiguracion(propiedades);
    }

    public Properties getConfig() {
        return config.getCopia();
    }

    public void salir() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Message> refrescarMensajes() {
        
        //descargar mensajes del servidor
        Message[] nuevosMensajes = null;
        try {
            Properties p = convertirPropiedadesAConfigConexion(this.config.getCopia());
            Session emailSession = Session.getInstance(p);
            // cogemos el contenedor para el protocolo 
            POP3Store emailStore = (POP3Store) emailSession.getStore();
            String host = p.getProperty(Configuracion.K_HOST_POP3);
            int puerto = Integer.parseInt(p.getProperty(Configuracion.K_PUERTO_POP3));
            String usuario = p.getProperty(Configuracion.K_USUARIO);
            String pass = p.getProperty(Configuracion.K_PASSWORD);
            emailStore.connect(host, puerto, usuario, pass);

            //pedimos la carpeta para el buzon de entrada
            Folder emailFolder = emailStore.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            //cogemos los mensajes
            nuevosMensajes = emailFolder.getMessages();
            for (int i = 0; i < nuevosMensajes.length; i++) {
                
                almacen.guardarMensaje(nuevosMensajes[i],emailSession);
            }
            //cerrando
            emailFolder.close(false);
            emailStore.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
        //devolver los mensajes que hay en el almacen
        return almacen.getMensajes();
    }

    public int probarSmtp(Properties config) {
        Properties p = convertirPropiedadesAConfigConexion(config);
        //probar conexion smtp
        try {
            Session sesion = crearSesion(p);
            Transport transport = sesion.getTransport("smtp");
            transport.connect(p.getProperty(Configuracion.K_USUARIO), p.getProperty(Configuracion.K_PASSWORD));
            transport.close();
        } catch (MessagingException e) {
            String es = e.getMessage();
            return 2000;//error de smtp
        }
        //OK
        return 1;
    }

    //4/0AeaYSHBZzyvlO1qs-pSJUFD-4EUwrsCDjlwVTEMTlFyspCJDqVMIPyqHCkYTNS6CQMiuHg
    //thxm gbhz cjxs fdiw
    public int probarPop3(Properties config) {

        
        Session s = getSesion();
        POP3Store store;
        try {
            store = (POP3Store) s.getStore();
            String host = config.getProperty(Configuracion.K_HOST_POP3);
            int puerto = Integer.parseInt(config.getProperty(Configuracion.K_PUERTO_POP3));
            String usuario = config.getProperty(Configuracion.K_USUARIO);
            String pass = config.getProperty(Configuracion.K_PASSWORD);
            store.connect(host, puerto, usuario, pass);
            store.close();

        } catch (NoSuchProviderException ex) {
            String es = ex.getMessage();
            return 2500;
        } catch (MessagingException ex) {
            String es = ex.getMessage();
            return 2500;
        }
        return 1;// OK
    }
    
    private Properties convertirPropiedadesAConfigConexion(Properties p) {

        Properties config = new Properties();
        config.setProperty("mail.smtp.host", p.getProperty(Configuracion.K_HOST_SMTP));
        config.setProperty("mail.smtp.port", p.getProperty(Configuracion.K_PUERTO_SMTP));
        config.setProperty("mail.smtp.auth", p.getProperty(Configuracion.K_AUTH));
        config.setProperty("mail.smtp.starttls.enable", p.getProperty(Configuracion.K_TLS_SMTP));
        config.setProperty("mail.smtp.ssl.protocols", p.getProperty(Configuracion.K_PROTOCOLO_SMTP));

        //pop3
        config.setProperty("mail.pop3.host", p.getProperty(Configuracion.K_HOST_POP3));
        config.setProperty("mail.pop3.rsetbeforequit", p.getProperty(Configuracion.K_MANTENER_EN_SERVIDOR));
        config.setProperty("mail.pop3s.rsetbeforequit", p.getProperty(Configuracion.K_MANTENER_EN_SERVIDOR));
        config.setProperty("mail.pop3.port", p.getProperty(Configuracion.K_PUERTO_POP3));
        config.setProperty("mail.pop3.user", p.getProperty(Configuracion.K_USUARIO));

        //seguridad pop3 
        config.setProperty("mail.pop3.starttls.enable", p.getProperty(Configuracion.K_TLS_POP3));
        config.setProperty("mail.pop3.ssl.protocols", p.getProperty(Configuracion.K_PROTOCOLO_POP3));
        config.setProperty("mail.pop3s.ssl.protocols", p.getProperty(Configuracion.K_PROTOCOLO_POP3));
        config.setProperty("mail.store.protocol", "pop3");
        if (Boolean.parseBoolean(p.getProperty(Configuracion.K_TLS_POP3))) {
            config.setProperty("mail.store.protocol", "pop3s");
        }

        config.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        config.setProperty("mail.pop3s.ssl.trust", "*");
        config.setProperty("mail.pop3s.auth", "true");
        //agregar las keys de config normal
        config.putAll(p);
        return config;
    }

    private Session crearSesion(Properties p) {

        return Session.getInstance(p, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(p.getProperty(Configuracion.K_USUARIO), p.getProperty(Configuracion.K_PASSWORD));
            }

        });
    }

    
    
    Session getSesion() {
        return Session.getInstance(convertirPropiedadesAConfigConexion(this.config.getCopia()));
    }

}//end Modelo
