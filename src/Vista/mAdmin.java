/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author Omar
 */
public class mAdmin extends javax.swing.JFrame {

    /**
     * Creates new form mAdmin
     */
    public mAdmin() {
        initComponents();
             this.setExtendedState(this.MAXIMIZED_BOTH);
     this.setTitle("Estadia en un click");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnCerrarSesion = new javax.swing.JMenuItem();
        btnSalir = new javax.swing.JMenuItem();
        jmenu1 = new javax.swing.JMenu();
        btnVehiculos = new javax.swing.JMenuItem();
        Conductores = new javax.swing.JMenu();
        btnConductores = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnUsuarios = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btnDepartamentos = new javax.swing.JMenuItem();
        btnDisponibilidad = new javax.swing.JMenuItem();
        btnMantenimiento = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        btnInventario = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        btnTours = new javax.swing.JMenuItem();
        btnDescuentos = new javax.swing.JMenuItem();
        btnTransportes = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        btnInformes = new javax.swing.JMenuItem();
        btnEstadisticas = new javax.swing.JMenuItem();

        jMenuItem7.setText("jMenuItem7");

        jMenuItem8.setText("jMenuItem8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Sesión");

        btnCerrarSesion.setText("Cerrar Sesión");
        jMenu1.add(btnCerrarSesion);

        btnSalir.setText("Salir");
        jMenu1.add(btnSalir);

        jMenuBar1.add(jMenu1);

        jmenu1.setText("Vehiculos");

        btnVehiculos.setText("Ver vehiculos");
        jmenu1.add(btnVehiculos);

        jMenuBar1.add(jmenu1);

        Conductores.setText("Conductores");

        btnConductores.setText("Ver Conductores");
        Conductores.add(btnConductores);

        jMenuBar1.add(Conductores);

        jMenu2.setText("Clientes");

        btnUsuarios.setText("Ver Clientes");
        jMenu2.add(btnUsuarios);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Departmanentos");

        btnDepartamentos.setText("Ver Departamentos");
        jMenu3.add(btnDepartamentos);

        btnDisponibilidad.setText("Disponibilidad");
        jMenu3.add(btnDisponibilidad);

        btnMantenimiento.setText("Mantenimiento");
        jMenu3.add(btnMantenimiento);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Inventario");

        btnInventario.setText("Ver Inventario");
        jMenu5.add(btnInventario);

        jMenuBar1.add(jMenu5);

        jMenu4.setText("Extras");

        btnTours.setText("Ver Tours");
        jMenu4.add(btnTours);

        btnDescuentos.setText("Ver Descuentos");
        jMenu4.add(btnDescuentos);

        btnTransportes.setText("Ver Transportes");
        jMenu4.add(btnTransportes);

        jMenuBar1.add(jMenu4);

        jMenu6.setText("Estadisticas");

        btnInformes.setText("Generar Informes");
        jMenu6.add(btnInformes);

        btnEstadisticas.setText("Generar estadisticas");
        jMenu6.add(btnEstadisticas);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 411, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Conductores;
    public javax.swing.JMenuItem btnCerrarSesion;
    public javax.swing.JMenuItem btnConductores;
    public javax.swing.JMenuItem btnDepartamentos;
    public javax.swing.JMenuItem btnDescuentos;
    public javax.swing.JMenuItem btnDisponibilidad;
    public javax.swing.JMenuItem btnEstadisticas;
    public javax.swing.JMenuItem btnInformes;
    public javax.swing.JMenuItem btnInventario;
    public javax.swing.JMenuItem btnMantenimiento;
    public javax.swing.JMenuItem btnSalir;
    public javax.swing.JMenuItem btnTours;
    public javax.swing.JMenuItem btnTransportes;
    public javax.swing.JMenuItem btnUsuarios;
    public javax.swing.JMenuItem btnVehiculos;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    public javax.swing.JMenu jmenu1;
    // End of variables declaration//GEN-END:variables
}
