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
public class Persona {
    private Conexion conexion;
    public Persona(Conexion conexion) {
        this.conexion = conexion;
    }

    public void verificarLogin() {
        System.out.println("Inicio Correctamente");
    }
}
