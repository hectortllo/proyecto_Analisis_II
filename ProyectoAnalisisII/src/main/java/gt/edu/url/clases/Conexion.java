/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.clases;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Oswaldo Alvarez <mynoswaldo@gmail.com>
 */
public class Conexion {

    private static Conexion conexion;
    private EntityManagerFactory emf;
    private EntityManager em;

    public Conexion() {
        emf = Persistence.createEntityManagerFactory("Aceitera");
        em = emf.createEntityManager();
    }

    public static Conexion getInstancia() {
        if (conexion == null) {
            conexion = new Conexion();
        }
        return conexion;
    }

    public String conectar() {
        if (em.isOpen()) {
            return "Conectado";
        } else {
            return "Error";
        }
    }

    public String desconectar() {
        this.em.close();
        return "Desconectado";
    }

    public EntityManager getEntity() {
        return this.em;
    }
}
