/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.clientecorreo;

import java.io.File;

import javax.swing.UnsupportedLookAndFeelException;

import josebailon.clientecorreo.controlador.Controlador;
import josebailon.clientecorreo.modelo.Modelo;
import josebailon.clientecorreo.vista.Ventana;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public class Main {
    public static void main(String[] args) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException |InstantiationException|IllegalAccessException|UnsupportedLookAndFeelException ex) {
        	System.out.println("Error cargando lookAndFeel "+ex.getLocalizedMessage());
        	System.exit(0);
        }

        
        
        Ventana v = new Ventana();
        v.setLocationRelativeTo(null);
        Modelo m = new Modelo();
        Controlador c = new Controlador (v,m);
        c.iniciar();
//        new ClienteSMTPConexion().start();
    	//new JavaMailSend_SMTP().start();
//    	new JavaMailReceive_POP3().start();
//        System.out.println(new File("./").getAbsolutePath());
        
    }
}//end Main
