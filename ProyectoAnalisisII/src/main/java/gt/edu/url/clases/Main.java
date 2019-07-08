/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.clases;

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
            FrmPrincipal frmPrincipal = new FrmPrincipal();
            Login login = new Login();
            login.setVisible(true);
            login.setConexion(conexion);
            login.setFrmPrincipal(frmPrincipal);
        } catch (Exception e) {
            System.out.println(conexion.desconectar());
        }

    }
}
