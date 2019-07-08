/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.clases;

import gt.edu.url.controller.UsuarioJpaController;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Oswaldo Alvarez <mynoswaldo@gmail.com>
 */
public class Persona {

    private Conexion conexion;
    private UsuarioJpaController usuario;

    public Persona(Conexion conexion) {
        this.conexion = conexion;
        usuario = new UsuarioJpaController(conexion.getEntityManager());
    }

    public boolean verificarLogin(String nombreUsuario, String contrasenia) {
        String contraseniaBD = usuario.findContrasenia(nombreUsuario);
        System.out.println(contraseniaBD + " " + encriptar(contrasenia));
        if (contraseniaBD != null) {

            return contraseniaBD.equals(encriptar(contrasenia));
        }
        return false;
    }

    private String encriptar(String password) {
        return DigestUtils.md5Hex(password);
    }
}
