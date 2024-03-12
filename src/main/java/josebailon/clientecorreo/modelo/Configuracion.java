/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package josebailon.clientecorreo.modelo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

/**
 * Configuracion. Contiene varios parametros accesibles de manera estatica.
 * Puede cargar los parametros desde un archivo
 *
 * @author Jose Javier Bailon Ortiz
 */
public class Configuracion {

    public static final String K_HOST_SMTP = "hostSmtp";
    public static final String K_PUERTO_SMTP = "puertoSmtp";
    public static final String K_PROTOCOLO_SMTP = "protocoloSmtp";
    public static final String K_AUTH = "auth";
    public static final String K_TLS_SMTP=  "tlsSmtp";
    public static final String K_HOST_POP3 = "hostPop3";
    public static final String K_PUERTO_POP3 = "puertoPop3";
    public static final String K_PROTOCOLO_POP3 = "protocoloPop3";
    public static final String K_TLS_POP3=  "tlsPop3";
    public static final String K_MANTENER_EN_SERVIDOR = "mantenerEnServidorPop3";
    public static final String K_USUARIO = "usuario";
    public static final String K_PASSWORD = "password";

    /**
     * Ruta para el almacenamiento de acceso anonimo
     */
    private static final String RUTA_CONFIG = "res/configuracion.cfg";
    private static Properties propiedades;
 
    public Configuracion() {
        propiedades = new Properties();
        propiedades.setProperty(K_HOST_SMTP, "localhost");
        propiedades.setProperty(K_PUERTO_SMTP, "25");
        propiedades.setProperty(K_PROTOCOLO_SMTP, "TLSv1.2");
        propiedades.setProperty(K_AUTH, "true");
        propiedades.setProperty(K_TLS_SMTP, "true");
        propiedades.setProperty(K_HOST_POP3, "localhost");
        propiedades.setProperty(K_PUERTO_POP3, "110");
        propiedades.setProperty(K_PROTOCOLO_POP3, "TLSv1.2");
        propiedades.setProperty(K_TLS_POP3, "true");
        propiedades.setProperty(K_MANTENER_EN_SERVIDOR, "true");
        propiedades.setProperty(K_USUARIO, "");
        propiedades.setProperty(K_PASSWORD, "");
    }

 

    public  String getValor(String clave) {
        return propiedades.getProperty(clave,"");
    }

    public  void setValor(String clave, String valor) {
        propiedades.setProperty(clave, valor);
    }

    /**
     * Carga la configuracion desde una ruta
     *
     * @param ruta La ruta del archivo de configuracion
     */
    public  boolean cargarConfiguracion() {

        File archConf = new File(RUTA_CONFIG);
        if (!archConf.exists()) {
            return false;
        }
        try (FileReader fr = new FileReader(archConf);) {
            propiedades.load(fr);
            return true;
        } catch (IOException | NumberFormatException e) {
            System.out.println("No se ha podido cargar la configuracion: " + e.getMessage());
            return false;
        }
    }

    public  boolean guardarConfiguracion(Properties p) {

        propiedades.setProperty(K_HOST_SMTP, p.getProperty(K_HOST_SMTP));
        propiedades.setProperty(K_PUERTO_SMTP, p.getProperty(K_PUERTO_SMTP));
        propiedades.setProperty(K_HOST_POP3, p.getProperty(K_HOST_POP3));
        propiedades.setProperty(K_PUERTO_POP3, p.getProperty(K_PUERTO_POP3));
        propiedades.setProperty(K_TLS_POP3, p.getProperty(K_TLS_POP3));
        propiedades.setProperty(K_USUARIO, p.getProperty(K_USUARIO));
        propiedades.setProperty(K_PASSWORD, p.getProperty(K_PASSWORD));

       
        
        File archConf = new File(RUTA_CONFIG);

        try (FileWriter fw=new FileWriter(archConf)){
            propiedades.store(fw, "");
            return true;
        } catch (IOException ex) {
            System.out.println("No se ha podido guardar la configuracion: "+ex.getMessage());
            return false;        
        }
    }

    public Properties  getCopia() {
        Properties p = new Properties();
        p.putAll(propiedades);
        return p;
    }
}
