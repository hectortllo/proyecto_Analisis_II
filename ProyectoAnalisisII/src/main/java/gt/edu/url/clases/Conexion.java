/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.clases;

/**
 *
 * @author Oswaldo Alvarez <mynoswaldo@gmail.com>
 */
public class Conexion {

    private static Conexion conexion;
    private String nombreBD;
    private String url;
    private String contrasenia;
    private String puerto;

    public Conexion() {
    }

    public static Conexion getInstancia() {
        if (conexion == null) {
            conexion = new Conexion();
        }
        return conexion;
    }

    public void conectar() {
        System.out.println("Me conecte a la BD");
    }

    public void desconectar() {
        System.out.println("Desconectado de la BD");
    }
}
