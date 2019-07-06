/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.vista;

import javax.swing.JButton;
import javax.swing.JPanel;
import rojerusan.RSPanelsSlider;

/**
 *
 * @author Héctor Tello <hectortllo@gmail.com>
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        pnlMenus = new javax.swing.JPanel();
        btnInventario = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        rsPnlPrincipal = new rojerusan.RSPanelsSlider();
        pnlAceitera = new javax.swing.JPanel();
        lblOccidente = new javax.swing.JLabel();
        lblAceitera = new javax.swing.JLabel();
        pnlInventario = new javax.swing.JPanel();
        lblInventario = new javax.swing.JLabel();
        pnlVentas = new javax.swing.JPanel();
        lblVentas = new javax.swing.JLabel();
        pnlClientes = new javax.swing.JPanel();
        lblClientes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMenus.setBackground(new java.awt.Color(204, 255, 204));
        pnlMenus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnInventario.setIcon(new javax.swing.ImageIcon("/home/hectortllo/NetBeansProjects/GitProjects/proyecto_Analisis_II/ProyectoAnalisisII/src/Imagenes/inventario.png")); // NOI18N
        btnInventario.setText("\n");
        btnInventario.setToolTipText("<html>\n<head>\n\t<style>\n\t\t #contenido{ \n\t\tbackground: #FFA533;  /*Se le da un color de fondo*/\n\t\tcolor: white;\t\t  /*Color a la letra*/\n\t\t}\n\t</style>\n</head>\n<body>\n\t<div id=contenido>\n\t\t<h2>Inventario</h2>\n\t\t<!-- <img src=\"Path img\"> -->\n\t</div>\n</body>\n</html>");
        btnInventario.setBorder(null);
        btnInventario.setBorderPainted(false);
        btnInventario.setContentAreaFilled(false);
        btnInventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInventario.setFocusPainted(false);
        btnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventarioActionPerformed(evt);
            }
        });
        pnlMenus.add(btnInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        btnVentas.setIcon(new javax.swing.ImageIcon("/home/hectortllo/NetBeansProjects/GitProjects/proyecto_Analisis_II/ProyectoAnalisisII/src/Imagenes/ventas.png")); // NOI18N
        btnVentas.setToolTipText("<html>\n<head>\n\t<style>\n\t\t #contenido{ \n\t\tbackground: #FFA533;  /*Se le da un color de fondo*/\n\t\tcolor: white;\t\t  /*Color a la letra*/\n\t\t}\n\t</style>\n</head>\n<body>\n\t<div id=contenido>\n\t\t<h2>Ventas</h2>\n\t\t<!-- <img src=\"Path img\"> -->\n\t</div>\n</body>\n</html>");
        btnVentas.setBorder(null);
        btnVentas.setBorderPainted(false);
        btnVentas.setContentAreaFilled(false);
        btnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas.setName("pnlVentas"); // NOI18N
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });
        pnlMenus.add(btnVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        btnClientes.setIcon(new javax.swing.ImageIcon("/home/hectortllo/NetBeansProjects/GitProjects/proyecto_Analisis_II/ProyectoAnalisisII/src/Imagenes/clientes.png")); // NOI18N
        btnClientes.setToolTipText("<html>\n<head>\n\t<style>\n\t\t #contenido{ \n\t\tbackground: #FFA533;  /*Se le da un color de fondo*/\n\t\tcolor: white;\t\t  /*Color a la letra*/\n\t\t}\n\t</style>\n</head>\n<body>\n\t<div id=contenido>\n\t\t<h2>Clientes</h2>\n\t\t<!-- <img src=\"Path img\"> -->\n\t</div>\n</body>\n</html>");
        btnClientes.setBorder(null);
        btnClientes.setBorderPainted(false);
        btnClientes.setContentAreaFilled(false);
        btnClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClientes.setName("pnlClientes"); // NOI18N
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        pnlMenus.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, -1, -1));

        jPanel1.add(pnlMenus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 650));

        pnlAceitera.setBackground(new java.awt.Color(255, 255, 204));
        pnlAceitera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblOccidente.setBackground(new java.awt.Color(0, 0, 0));
        lblOccidente.setFont(new java.awt.Font("Dialog", 1, 80)); // NOI18N
        lblOccidente.setForeground(new java.awt.Color(102, 102, 102));
        lblOccidente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOccidente.setText("DE OCCIDENTE");
        pnlAceitera.add(lblOccidente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 800, 90));

        lblAceitera.setBackground(new java.awt.Color(102, 102, 102));
        lblAceitera.setFont(new java.awt.Font("Dialog", 1, 80)); // NOI18N
        lblAceitera.setForeground(new java.awt.Color(102, 102, 102));
        lblAceitera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAceitera.setText("ACEITERA");
        pnlAceitera.add(lblAceitera, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 800, 90));

        rsPnlPrincipal.add(pnlAceitera, "card2");

        pnlInventario.setBackground(new java.awt.Color(255, 255, 204));
        pnlInventario.setName("pnlInventario"); // NOI18N
        pnlInventario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInventario.setBackground(new java.awt.Color(102, 102, 102));
        lblInventario.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        lblInventario.setForeground(new java.awt.Color(0, 153, 153));
        lblInventario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInventario.setText("INVENTARIO");
        lblInventario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlInventario.add(lblInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 880, 50));

        rsPnlPrincipal.add(pnlInventario, "card3");

        pnlVentas.setBackground(new java.awt.Color(255, 255, 204));
        pnlVentas.setForeground(new java.awt.Color(255, 255, 204));
        pnlVentas.setName("pnlVentas"); // NOI18N
        pnlVentas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblVentas.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        lblVentas.setForeground(new java.awt.Color(0, 153, 153));
        lblVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVentas.setText("VENTAS");
        lblVentas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlVentas.add(lblVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 880, 60));

        rsPnlPrincipal.add(pnlVentas, "card4");

        pnlClientes.setBackground(new java.awt.Color(255, 255, 204));
        pnlClientes.setName("pnlClientes"); // NOI18N
        pnlClientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblClientes.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        lblClientes.setForeground(new java.awt.Color(0, 153, 153));
        lblClientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClientes.setText("CLIENTES");
        pnlClientes.add(lblClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 870, 60));

        rsPnlPrincipal.add(pnlClientes, "card5");

        jPanel1.add(rsPnlPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 880, 650));

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed
        moverPanel(btnInventario, pnlInventario);
    }//GEN-LAST:event_btnInventarioActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        moverPanel(btnVentas, pnlVentas);
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        moverPanel(btnClientes, pnlClientes);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void moverPanel(JButton boton, JPanel panel){
        if(!boton.isSelected()){
            btnInventario.setSelected(false);
            btnClientes.setSelected(false);
            btnVentas.setSelected(false);
            boton.setSelected(true);
            rsPnlPrincipal.setPanelSlider(10, panel, RSPanelsSlider.DIRECT.RIGHT);
        }
    }
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
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnVentas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAceitera;
    private javax.swing.JLabel lblClientes;
    private javax.swing.JLabel lblInventario;
    private javax.swing.JLabel lblOccidente;
    private javax.swing.JLabel lblVentas;
    private javax.swing.JPanel pnlAceitera;
    private javax.swing.JPanel pnlClientes;
    private javax.swing.JPanel pnlInventario;
    private javax.swing.JPanel pnlMenus;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPanel pnlVentas;
    private rojerusan.RSPanelsSlider rsPnlPrincipal;
    // End of variables declaration//GEN-END:variables
}
