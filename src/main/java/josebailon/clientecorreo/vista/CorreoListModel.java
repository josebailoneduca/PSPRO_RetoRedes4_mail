/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.clientecorreo.vista;

import java.util.ArrayList;
import java.util.List;
import javax.mail.Message;
import javax.swing.AbstractListModel;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public class CorreoListModel extends AbstractListModel<Message>{

    List<Message> mensajes;

    public CorreoListModel(List<Message> mensajes) {
        this.mensajes = mensajes;
    }
    
    
    @Override
    public int getSize() {
        if (mensajes==null)
            return 0;
        return mensajes.size();
    }

    @Override
    public Message getElementAt(int index) {
        return mensajes.get(mensajes.size()-1-index);
    }

}//end CorreoListModel
