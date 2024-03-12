/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package josebailon.clientecorreo.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public class Almacen {

    private static String ruta = "res/almacen/";
    private ArrayList<Message> mensajes;
    Modelo modelo;

    public Almacen(Modelo modelo) {
        mensajes = new ArrayList<Message>();
        this.modelo = modelo;
        leerMensajesDeDisco();
    }

    public ArrayList<Message> getMensajes() {
        leerMensajesDeDisco();
        return mensajes;
    }

    public boolean guardarMensaje(Message msg, Session sesion) {

        if (yaExiste(msg, sesion)) {
            return false;
        }
        //construir ruta
        String nuevaRuta = getNuevaRuta(msg);
        if (nuevaRuta == null) {
            return false;
        }

        //guardar
        File f = new File(nuevaRuta);
        System.out.println(f.getAbsolutePath());
        try (FileOutputStream fos = new FileOutputStream(f)) {
            msg.writeTo(fos);
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException | MessagingException ex) {
            return false;
        }
    }

    private String getNuevaRuta(Message msg) {
        File directorio = new File(ruta);
        if (!directorio.isDirectory() || !directorio.exists()) {
            return null;
        } else {
            String id = "";
            try {
                id = ((MimeMessage) msg).getMessageID();
            } catch (MessagingException ex) {
            }

            int indice = directorio.listFiles().length;
            while (true) {
                String nuevaRuta = ruta + indice + "_" + id + ".eml";
                nuevaRuta = nuevaRuta.replaceAll("[^a-zA-Z0-9.-/\\\\]", "_");
                if (new File(nuevaRuta).exists()) {
                    indice++;
                } else {
                    return nuevaRuta;
                }
            }
        }
    }

    public void leerMensajesDeDisco() {
        this.mensajes = new ArrayList<Message>();

        File alm = new File(ruta);
        File[] archivos = alm.listFiles();

        for (int i = 0; i < archivos.length; i++) {
            File f = archivos[i];
            if (!f.isDirectory()) {
                try (FileInputStream fis = new FileInputStream(f)) {

                    Session session = modelo.getSesion();
                    MimeMessage message = new MimeMessage(session, fis);
                    this.mensajes.add(message);
                } catch (MessagingException | IOException ex) {
                }
            }
        }
    }

    private boolean yaExiste(Message msg, Session s) {
        PipedInputStream in = new PipedInputStream();
        boolean existe  = false;
        try {
        PipedOutputStream out = new PipedOutputStream(in);
          
            new Thread (() -> {
                try {
                    msg.writeTo(out);
                    out.close();
                } catch (IOException |MessagingException ex) {try {out.close();} catch (IOException ex1) {}
}
            }).start();
            
            MimeMessage mensajeRemoto = new MimeMessage(s, in);
            String idRemota = mensajeRemoto.getMessageID();
            
            //si hay identificador remoto comprobamos si existe en local
            if (idRemota != null) {
                for (int i = 0; i < mensajes.size() && !existe; i++) {
                    String idLocal = ((MimeMessage) mensajes.get(i)).getMessageID();
                    if (idLocal != null && idLocal.equals(idRemota)) {
                        existe= true;
                    }
                }
            }
        } catch (MessagingException |IOException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
}//end Almacen
