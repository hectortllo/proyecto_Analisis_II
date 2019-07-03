/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.clases;

import gt.edu.url.entity.TipoProducto;
import gt.edu.url.formularios.Login;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Oswaldo Alvarez <mynoswaldo@gmail.com>
 */
public class Main {

    public static void main(String[] args) {
//        Conexion conexion = Conexion.getInstancia();
//        conexion.conectar();
//        Persona persona = new Persona(conexion);
//        Login login = new Login();
//        login.setVisible(true);
//        login.setPersona(persona);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Aceitera");
        EntityManager em = emf.createEntityManager();
        TypedQuery<TipoProducto> query = em.createNamedQuery("TipoProducto.findAll", TipoProducto.class);
        List<TipoProducto> listaTProducto = query.getResultList();
        for (TipoProducto tp : listaTProducto) {
            System.out.println(tp);
        }
    }
}
