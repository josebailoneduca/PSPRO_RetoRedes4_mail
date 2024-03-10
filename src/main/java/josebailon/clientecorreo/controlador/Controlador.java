/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.clientecorreo.controlador;

import java.util.Properties;
import josebailon.clientecorreo.modelo.Configuracion;
import josebailon.clientecorreo.modelo.Modelo;
import josebailon.clientecorreo.vista.Ventana;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public class Controlador {
    private final Ventana vista;
    private final Modelo modelo;
    
    
    public Controlador(Ventana v, Modelo m) {
        vista=v;
        modelo=m;
        vista.setControlador(this);
        modelo.setControlador(this);
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(()-> {v.setVisible(true);});
    }

    public void iniciar() {
        if(!modelo.cargarConfiguracion())
            vista.pedirConfiguracion();
        modelo.guardarConfiguracion();
    }

    public Properties getConfig() {
        return modelo.getConfig();
    }

}//end Controlador