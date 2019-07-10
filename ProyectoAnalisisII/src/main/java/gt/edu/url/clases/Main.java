/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.clases;

import gt.edu.url.controller.TipoProductoJpaController;
import gt.edu.url.controller.VentaJpaController;
import gt.edu.url.vista.FrmPrincipal;
import gt.edu.url.vista.Login;

/**
 *
 * @author Oswaldo Alvarez <mynoswaldo@gmail.com>
 */
public class Main {
    
    public static void main(String[] args) {
        Conexion conexion = Conexion.getInstancia();
        try {
//            VentaJpaController venta = new VentaJpaController(conexion.getEntityManager());
//            System.out.println(venta.findVentaMaxId().getId());
            FrmPrincipal frmPrincipal = new FrmPrincipal(conexion);
            //Login login = new Login(conexion);
            //login.setVisible(true);
            //login.setFrmPrincipal(frmPrincipal);
            frmPrincipal.setVisible(true);
//            TipoProductoJpaController controller = new TipoProductoJpaController(conexion.getEntityManager());
//            System.out.println(controller.findTipoProducto(1));
//            for (gt.edu.url.entity.Producto producto : controller.mostrar(1)) {
//                System.out.println(producto.getNombre());
//            }
        } catch (Exception e) {
            System.out.println(conexion.desconectar());
        }
        
    }
}
