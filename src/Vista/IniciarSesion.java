/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import com.placeholder.PlaceHolder;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Omar
 */
public class IniciarSesion extends javax.swing.JFrame {
PlaceHolder place;
    /**
     * Creates new form IniciarSesion
     */
    public IniciarSesion() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtRut = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnInicioSesion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Logotipo.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 140, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("RUT:");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 90, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contraseña:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        txtRut.setBackground(new java.awt.Color(0, 0, 0));
        txtRut.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtRut.setForeground(new java.awt.Color(255, 255, 255));
        txtRut.setCaretColor(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtRut, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 130, -1));

        txtPassword.setBackground(new java.awt.Color(0, 0, 0));
        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setCaretColor(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 130, -1));

        btnInicioSesion.setText("Iniciar Sesión");
        getContentPane().add(btnInicioSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, -1, -1));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/wallpaper.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 383));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnInicioSesion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JPasswordField txtPassword;
    public javax.swing.JTextField txtRut;
    // End of variables declaration//GEN-END:variables
}
