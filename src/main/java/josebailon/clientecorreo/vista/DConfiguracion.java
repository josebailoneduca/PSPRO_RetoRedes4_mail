/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
Lista de paquetes:
 */
package josebailon.clientecorreo.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import josebailon.clientecorreo.controlador.Controlador;
import josebailon.clientecorreo.modelo.Configuracion;

/**
 *
 * @author Jose Javier BO
 */
public class DConfiguracion extends javax.swing.JDialog implements ActionListener {

    private Properties config;
    private boolean guardado = false;
    private Controlador control;

    /**
     * Creates new form DConfiguracion
     */
    public DConfiguracion(java.awt.Frame parent, Properties config, Controlador control) {
        super(parent, true);
        initComponents();
        this.config = config;
        this.control = control;
        inicializar();
        eventos();
    }

    private void inicializar() {
        inputHostSmtp.setText(config.getProperty(Configuracion.K_HOST_SMTP, ""));
        inputPuertoSmtp.setText(config.getProperty(Configuracion.K_PUERTO_SMTP, ""));
        inputHostPop3.setText(config.getProperty(Configuracion.K_HOST_POP3, ""));
        inputPuertoPop3.setText(config.getProperty(Configuracion.K_PUERTO_POP3, ""));
        inputUser.setText(config.getProperty(Configuracion.K_USUARIO, ""));
        inputPass.setText(config.getProperty(Configuracion.K_PASSWORD, ""));
        if (Boolean.parseBoolean(config.getProperty(Configuracion.K_TLS_POP3)))
            inputPop3Tls.setSelectedIndex(1);
    }

    private void eventos() {
        btnGuardar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnProbar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();

        switch (ac) {
            case "guardar" ->
                guardar();
            case "cancelar" ->
                this.dispose();
            case "probar" ->
                probar();
            default -> {
            }
        }
    }

    private void guardar() {
        recogerConfiguracion();

        if (configOk()) {
            this.guardado = true;
            this.dispose();
        }
    }

    private void probar() {
        recogerConfiguracion();
        if (configOk()) {
            btnCancelar.setEnabled(false);
            btnGuardar.setEnabled(false);
            btnProbar.setEnabled(false);
            Comprobador c = new Comprobador(lbConexion, config);
            c.execute();
        }

    }

    private void resultadoPrueba(int resultado){
                btnCancelar.setEnabled(true);
            btnGuardar.setEnabled(true);
            btnProbar.setEnabled(true);
            
            switch (resultado) {
            case 1-> lbConexion.setText("Conexión válida");
            case 2000 ->lbConexion.setText("Error conectando sevidor SMTP");
            case 2500 ->lbConexion.setText("Error conectando sevidor POP3");
            default -> {}
        }
    }
    
    private void recogerConfiguracion() {
        config.setProperty(Configuracion.K_HOST_SMTP, inputHostSmtp.getText());
        config.setProperty(Configuracion.K_PUERTO_SMTP, inputPuertoSmtp.getText());
        config.setProperty(Configuracion.K_HOST_POP3, inputHostPop3.getText());
        config.setProperty(Configuracion.K_PUERTO_POP3, inputPuertoPop3.getText());
        config.setProperty(Configuracion.K_USUARIO, inputUser.getText());
        config.setProperty(Configuracion.K_PASSWORD, inputPass.getText());
        
        if (inputPop3Tls.getSelectedIndex()==0)
            config.setProperty(Configuracion.K_TLS_POP3, "false");
        else
            config.setProperty(Configuracion.K_TLS_POP3, "true");
        
    }

    private boolean configOk() {
        if (config.getProperty(Configuracion.K_HOST_SMTP, "").length() == 0) {
            msg("El host no puede estar vacío");
            return false;
        }
        if (config.getProperty(Configuracion.K_PUERTO_SMTP, "").length() == 0) {
            msg("El puerto no puede estar vacío");
            return false;
        }
        if (config.getProperty(Configuracion.K_USUARIO, "").length() == 0) {
            msg("El usuario no puede estar vacío");
            return false;
        }
        return true;
    }

    private void msg(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public Properties getResultado() {
        if (!guardado) {
            return null;
        } else {
            return config;
        }
    }



    private class Comprobador extends SwingWorker<Integer, String> {
        // Esta etiqueta se recibe en el constructor o a través de un
        // metodo setEtiqueta().

        private JLabel etiqueta;
        private Properties config;

        public Comprobador(JLabel etiqueta, Properties config) {
            this.config=config;
            this.etiqueta=etiqueta;
        }

        @Override
        protected Integer doInBackground() throws Exception {
            
            //smtp
            publish("Comprobando SMTP...");
            int res =  control.probarSmtp(config);
             if (res==2000){
                 publish("Error conectando con servidor SMTP");
                 return 2000;
             }else{
                 publish("Comprobando POP3...");
                 res = control.probarPop3(config);
                 if (res==2500){
                    publish("Error conectando con el servidor POP3...");
                 }
             }
             return res;
        }

        @Override
        protected void done() {
            try {
                int resultado = get();
                resultadoPrueba(resultado);
            } catch (InterruptedException ex) {
                Logger.getLogger(DConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(DConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        protected void process(List<String> chunks) {
            etiqueta.setText(chunks.get(chunks.size()-1));
        }
 
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
        inputPuertoPop3 = new javax.swing.JTextField();
        lbpUERTOPop3 = new javax.swing.JLabel();
        inputHostPop3 = new javax.swing.JTextField();
        lbUser = new javax.swing.JLabel();
        inputUser = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        inputPass = new javax.swing.JTextField();
        btnProbar = new javax.swing.JButton();
        lbConexion = new javax.swing.JLabel();
        lbHostPop3 = new javax.swing.JLabel();
        inputPuertoSmtp = new javax.swing.JTextField();
        inputPop3Tls = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configurar cuenta");
        setResizable(false);

        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 9, 150, 9, 0};
        jPanel1Layout.rowHeights = new int[] {0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0};
        panelGeneral.setLayout(jPanel1Layout);

        lbHostsmtp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHostsmtp.setText("HOST SMTP:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panelGeneral.add(lbHostsmtp, gridBagConstraints);

        inputHostSmtp.setText("localhost");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panelGeneral.add(inputHostSmtp, gridBagConstraints);

        lbPuertoSmtp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbPuertoSmtp.setText("PUERTO SMTP:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panelGeneral.add(lbPuertoSmtp, gridBagConstraints);

        inputPuertoPop3.setText("110");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panelGeneral.add(inputPuertoPop3, gridBagConstraints);

        lbpUERTOPop3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbpUERTOPop3.setText("PUERTO POP3:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panelGeneral.add(lbpUERTOPop3, gridBagConstraints);

        inputHostPop3.setText("localhost");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panelGeneral.add(inputHostPop3, gridBagConstraints);

        lbUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbUser.setText("USUARIO:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panelGeneral.add(lbUser, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panelGeneral.add(inputUser, gridBagConstraints);

        lbPass.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbPass.setText("CONTRASEÑA:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panelGeneral.add(lbPass, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panelGeneral.add(inputPass, gridBagConstraints);

        btnProbar.setText("Comprobar conexión");
        btnProbar.setActionCommand("probar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panelGeneral.add(btnProbar, gridBagConstraints);

        lbConexion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panelGeneral.add(lbConexion, gridBagConstraints);

        lbHostPop3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHostPop3.setText("HOST POP3:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panelGeneral.add(lbHostPop3, gridBagConstraints);

        inputPuertoSmtp.setText("25");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelGeneral.add(inputPuertoSmtp, gridBagConstraints);

        inputPop3Tls.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SIN SEGURIDAD", "TLS/SSL" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panelGeneral.add(inputPop3Tls, gridBagConstraints);

        btnGuardar.setText("Guardar");
        btnGuardar.setActionCommand("guardar");

        btnCancelar.setText("Cancelar");
        btnCancelar.setActionCommand("cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(100, 100, 100))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
    private javax.swing.JButton btnProbar;
    private javax.swing.JTextField inputHostPop3;
    private javax.swing.JTextField inputHostSmtp;
    private javax.swing.JTextField inputPass;
    private javax.swing.JComboBox<String> inputPop3Tls;
    private javax.swing.JTextField inputPuertoPop3;
    private javax.swing.JTextField inputPuertoSmtp;
    private javax.swing.JTextField inputUser;
    private javax.swing.JLabel lbConexion;
    private javax.swing.JLabel lbHostPop3;
    private javax.swing.JLabel lbHostsmtp;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbPuertoSmtp;
    private javax.swing.JLabel lbUser;
    private javax.swing.JLabel lbpUERTOPop3;
    private javax.swing.JPanel panelGeneral;
    // End of variables declaration//GEN-END:variables

}
