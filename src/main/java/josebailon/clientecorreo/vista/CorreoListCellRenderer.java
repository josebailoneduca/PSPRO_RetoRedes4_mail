/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
Lista de paquetes:
 */
package josebailon.clientecorreo.vista;

import java.awt.Color;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public class CorreoListCellRenderer extends javax.swing.JPanel implements ListCellRenderer  {
  private static Color FONDO_SELECCIONADO = new Color(0.7f,0.90f,0.95f);
    /**
     * Creates new form CorreoListCellRenderer
     */
    public CorreoListCellRenderer() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbRemitente = new javax.swing.JLabel();
        lbAsunto = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(100, 53));

        jLabel1.setText("De:");

        lbRemitente.setBackground(new java.awt.Color(255, 255, 255));
        lbRemitente.setText(" ");

        lbAsunto.setBackground(new java.awt.Color(255, 255, 255));
        lbAsunto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbAsunto.setText(" ");

        jSeparator1.setMinimumSize(null);
        jSeparator1.setPreferredSize(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbRemitente, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
                    .addComponent(lbAsunto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbRemitente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAsunto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (isSelected)
            this.setBackground(FONDO_SELECCIONADO);
            else
            this.setBackground(Color.WHITE);
        Message msg = (Message)value;
        try {
            lbRemitente.setText(msg.getFrom()[0].toString());
            lbAsunto.setText(msg.getSubject());
        } catch (MessagingException ex) {
           // Logger.getLogger(CorreoListCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
           return this;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbAsunto;
    private javax.swing.JLabel lbRemitente;
    // End of variables declaration//GEN-END:variables
}
