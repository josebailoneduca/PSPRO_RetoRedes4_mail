/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.clientecorreo.modelo;

import java.util.Properties;
import josebailon.clientecorreo.controlador.Controlador;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public class Modelo {
    Controlador control;
    Configuracion config;
    
    
    public void setControlador(Controlador c) {
        control=c;
        config=new Configuracion();
    }

    public boolean cargarConfiguracion() {
       if (config.cargarConfiguracion())
           return configuracionCorrecta();
       else
         return false;
    }

    private boolean configuracionCorrecta() {
        String usuario=config.getValor(Configuracion.K_USUARIO);
        
        
        return (usuario.length()>3);
    }

    public boolean guardarConfiguracion() {
        return config.guardarConfiguracion();
    }

    public Properties getConfig() {
        return config.getCopia();
    }

 

}//end Modelo
