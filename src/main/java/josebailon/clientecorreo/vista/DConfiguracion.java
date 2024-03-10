/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
Lista de paquetes:
 */
package josebailon.clientecorreo.vista;

import java.util.Properties;
import josebailon.clientecorreo.modelo.Configuracion;

/**
 *
 * @author Jose Javier BO
 */
public class DConfiguracion extends javax.swing.JDialog {

    private Properties config;
    /**
     * Creates new form DConfiguracion
     */
    public DConfiguracion(java.awt.Frame parent, Properties config) {
        super(parent, true);
        initComponents();
        this.config = config;
        inicializar();
    }
    private void inicializar() {
        inputHostSmtp.setText(config.getProperty(Configuracion.K_HOST_SMTP,""));
        inputPuertoSmtp.setText(config.getProperty(Configuracion.K_PUERTO_SMTP,""));
        inputHostPop3.setText(config.getProperty(Configuracion.K_HOST_POP3,""));
        inputUser.setText(config.getProperty(Configuracion.K_USUARIO,""));
        inputPass.setText(config.getProperty(Configuracion.K_PASSWORD,""));
        inputHostPop3.setEnabled(!inputHostPop3.getText().equals(inputHostSmtp.getText()));
        inputDiferente.setSelected(inputHostPop3.isEnabled());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelGeneral = new javax.swing.JPanel();
        lbHostsmtp = new javax.swing.JLabel();
        inputHostSmtp = new javax.swing.JTextField();
        lbPuertoSmtp = new javax.swing.JLabel();
        inputPuertoSmtp = new javax.swing.JTextField();
        lbHostPop3 = new javax.swing.JLabel();
        inputHostPop3 = new javax.swing.JTextField();
        inputDiferente = new javax.swing.JCheckBox();
        lbUser = new javax.swing.JLabel();
        inputUser = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        inputPass = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 9, 150, 9, 0};
        jPanel1Layout.rowHeights = new int[] {0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0};
        panelGeneral.setLayout(jPanel1Layout);

        lbHostsmtp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHostsmtp.setText("HOST SMTP:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panelGeneral.add(lbHostsmtp, gridBagConstraints);

        inputHostSmtp.setText("localhost");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelGeneral.add(inputHostSmtp, gridBagConstraints);

        lbPuertoSmtp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbPuertoSmtp.setText("PUERTO SMTP:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panelGeneral.add(lbPuertoSmtp, gridBagConstraints);

        inputPuertoSmtp.setText("25");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelGeneral.add(inputPuertoSmtp, gridBagConstraints);

        lbHostPop3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHostPop3.setText("HOST POP3:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panelGeneral.add(lbHostPop3, gridBagConstraints);

        inputHostPop3.setText("localhost");
        inputHostPop3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelGeneral.add(inputHostPop3, gridBagConstraints);

        inputDiferente.setText("diferente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        panelGeneral.add(inputDiferente, gridBagConstraints);

        lbUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbUser.setText("USUARIO:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panelGeneral.add(lbUser, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelGeneral.add(inputUser, gridBagConstraints);

        lbPass.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbPass.setText("CONTRASEÑA:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panelGeneral.add(lbPass, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelGeneral.add(inputPass, gridBagConstraints);

        btnGuardar.setText("Guardar");
        btnGuardar.setActionCommand("guardar");

        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(100, 100, 100))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JCheckBox inputDiferente;
    private javax.swing.JTextField inputHostPop3;
    private javax.swing.JTextField inputHostSmtp;
    private javax.swing.JTextField inputPass;
    private javax.swing.JTextField inputPuertoSmtp;
    private javax.swing.JTextField inputUser;
    private javax.swing.JLabel lbHostPop3;
    private javax.swing.JLabel lbHostsmtp;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbPuertoSmtp;
    private javax.swing.JLabel lbUser;
    private javax.swing.JPanel panelGeneral;
    // End of variables declaration//GEN-END:variables


}
