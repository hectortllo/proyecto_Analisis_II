/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.controller;

import gt.edu.url.controller.exceptions.IllegalOrphanException;
import gt.edu.url.controller.exceptions.NonexistentEntityException;
import gt.edu.url.entity.Usuario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import gt.edu.url.entity.Venta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Oswaldo Alvarez <mynoswaldo@gmail.com>
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManager em) {
        this.em = em;
    }
    private EntityManager em = null;

    public EntityManager getEntityManager() {
        return em;
    }

    public void create(Usuario usuario) {
        if (usuario.getVentaCollection() == null) {
            usuario.setVentaCollection(new ArrayList<Venta>());
        }
        try {
            em.getTransaction().begin();
            Collection<Venta> attachedVentaCollection = new ArrayList<Venta>();
            for (Venta ventaCollectionVentaToAttach : usuario.getVentaCollection()) {
                ventaCollectionVentaToAttach = em.getReference(ventaCollectionVentaToAttach.getClass(), ventaCollectionVentaToAttach.getId());
                attachedVentaCollection.add(ventaCollectionVentaToAttach);
            }
            usuario.setVentaCollection(attachedVentaCollection);
            em.persist(usuario);
            for (Venta ventaCollectionVenta : usuario.getVentaCollection()) {
                Usuario oldUsuarioidOfVentaCollectionVenta = ventaCollectionVenta.getUsuarioid();
                ventaCollectionVenta.setUsuarioid(usuario);
                ventaCollectionVenta = em.merge(ventaCollectionVenta);
                if (oldUsuarioidOfVentaCollectionVenta != null) {
                    oldUsuarioidOfVentaCollectionVenta.getVentaCollection().remove(ventaCollectionVenta);
                    oldUsuarioidOfVentaCollectionVenta = em.merge(oldUsuarioidOfVentaCollectionVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            Collection<Venta> ventaCollectionOld = persistentUsuario.getVentaCollection();
            Collection<Venta> ventaCollectionNew = usuario.getVentaCollection();
            List<String> illegalOrphanMessages = null;
            for (Venta ventaCollectionOldVenta : ventaCollectionOld) {
                if (!ventaCollectionNew.contains(ventaCollectionOldVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Venta " + ventaCollectionOldVenta + " since its usuarioid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Venta> attachedVentaCollectionNew = new ArrayList<Venta>();
            for (Venta ventaCollectionNewVentaToAttach : ventaCollectionNew) {
                ventaCollectionNewVentaToAttach = em.getReference(ventaCollectionNewVentaToAttach.getClass(), ventaCollectionNewVentaToAttach.getId());
                attachedVentaCollectionNew.add(ventaCollectionNewVentaToAttach);
            }
            ventaCollectionNew = attachedVentaCollectionNew;
            usuario.setVentaCollection(ventaCollectionNew);
            usuario = em.merge(usuario);
            for (Venta ventaCollectionNewVenta : ventaCollectionNew) {
                if (!ventaCollectionOld.contains(ventaCollectionNewVenta)) {
                    Usuario oldUsuarioidOfVentaCollectionNewVenta = ventaCollectionNewVenta.getUsuarioid();
                    ventaCollectionNewVenta.setUsuarioid(usuario);
                    ventaCollectionNewVenta = em.merge(ventaCollectionNewVenta);
                    if (oldUsuarioidOfVentaCollectionNewVenta != null && !oldUsuarioidOfVentaCollectionNewVenta.equals(usuario)) {
                        oldUsuarioidOfVentaCollectionNewVenta.getVentaCollection().remove(ventaCollectionNewVenta);
                        oldUsuarioidOfVentaCollectionNewVenta = em.merge(oldUsuarioidOfVentaCollectionNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        try {
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Venta> ventaCollectionOrphanCheck = usuario.getVentaCollection();
            for (Venta ventaCollectionOrphanCheckVenta : ventaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Venta " + ventaCollectionOrphanCheckVenta + " in its ventaCollection field has a non-nullable usuarioid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public Usuario findUsuario(Integer id) {
        try {
            return em.find(Usuario.class, id);
        } finally {
        }
    }

    public int getUsuarioCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
        }
    }

    public String findContrasenia(String nombreUsuario) {
        try {
            Usuario usuario = (Usuario) em.createNamedQuery("Usuario.findByNombreUsuario").setParameter("nombreUsuario", nombreUsuario).getSingleResult();
            return usuario.getContrasenia();
        } catch (Exception e) {
            return null;
        }
    }
}
