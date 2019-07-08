/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.controller;

import gt.edu.url.controller.exceptions.NonexistentEntityException;
import gt.edu.url.entity.DetalleVenta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import gt.edu.url.entity.Producto;
import gt.edu.url.entity.Venta;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Oswaldo Alvarez <mynoswaldo@gmail.com>
 */
public class DetalleVentaJpaController implements Serializable {

    private EntityManager em = null;
    public DetalleVentaJpaController(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEntityManager() {
        return this.em;
    }

    public void create(DetalleVenta detalleVenta) {
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto productoid = detalleVenta.getProductoid();
            if (productoid != null) {
                productoid = em.getReference(productoid.getClass(), productoid.getId());
                detalleVenta.setProductoid(productoid);
            }
            Venta ventaid = detalleVenta.getVentaid();
            if (ventaid != null) {
                ventaid = em.getReference(ventaid.getClass(), ventaid.getId());
                detalleVenta.setVentaid(ventaid);
            }
            em.persist(detalleVenta);
            if (productoid != null) {
                productoid.getDetalleVentaCollection().add(detalleVenta);
                productoid = em.merge(productoid);
            }
            if (ventaid != null) {
                ventaid.getDetalleVentaCollection().add(detalleVenta);
                ventaid = em.merge(ventaid);
            }
            em.getTransaction().commit();
        } finally {}
    }

    public void edit(DetalleVenta detalleVenta) throws NonexistentEntityException, Exception {
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleVenta persistentDetalleVenta = em.find(DetalleVenta.class, detalleVenta.getId());
            Producto productoidOld = persistentDetalleVenta.getProductoid();
            Producto productoidNew = detalleVenta.getProductoid();
            Venta ventaidOld = persistentDetalleVenta.getVentaid();
            Venta ventaidNew = detalleVenta.getVentaid();
            if (productoidNew != null) {
                productoidNew = em.getReference(productoidNew.getClass(), productoidNew.getId());
                detalleVenta.setProductoid(productoidNew);
            }
            if (ventaidNew != null) {
                ventaidNew = em.getReference(ventaidNew.getClass(), ventaidNew.getId());
                detalleVenta.setVentaid(ventaidNew);
            }
            detalleVenta = em.merge(detalleVenta);
            if (productoidOld != null && !productoidOld.equals(productoidNew)) {
                productoidOld.getDetalleVentaCollection().remove(detalleVenta);
                productoidOld = em.merge(productoidOld);
            }
            if (productoidNew != null && !productoidNew.equals(productoidOld)) {
                productoidNew.getDetalleVentaCollection().add(detalleVenta);
                productoidNew = em.merge(productoidNew);
            }
            if (ventaidOld != null && !ventaidOld.equals(ventaidNew)) {
                ventaidOld.getDetalleVentaCollection().remove(detalleVenta);
                ventaidOld = em.merge(ventaidOld);
            }
            if (ventaidNew != null && !ventaidNew.equals(ventaidOld)) {
                ventaidNew.getDetalleVentaCollection().add(detalleVenta);
                ventaidNew = em.merge(ventaidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleVenta.getId();
                if (findDetalleVenta(id) == null) {
                    throw new NonexistentEntityException("The detalleVenta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {}
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleVenta detalleVenta;
            try {
                detalleVenta = em.getReference(DetalleVenta.class, id);
                detalleVenta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleVenta with id " + id + " no longer exists.", enfe);
            }
            Producto productoid = detalleVenta.getProductoid();
            if (productoid != null) {
                productoid.getDetalleVentaCollection().remove(detalleVenta);
                productoid = em.merge(productoid);
            }
            Venta ventaid = detalleVenta.getVentaid();
            if (ventaid != null) {
                ventaid.getDetalleVentaCollection().remove(detalleVenta);
                ventaid = em.merge(ventaid);
            }
            em.remove(detalleVenta);
            em.getTransaction().commit();
        } finally {}
    }

    public List<DetalleVenta> findDetalleVentaEntities() {
        return findDetalleVentaEntities(true, -1, -1);
    }

    public List<DetalleVenta> findDetalleVentaEntities(int maxResults, int firstResult) {
        return findDetalleVentaEntities(false, maxResults, firstResult);
    }

    private List<DetalleVenta> findDetalleVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleVenta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DetalleVenta findDetalleVenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleVenta.class, id);
        } finally {}
    }

    public int getDetalleVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleVenta> rt = cq.from(DetalleVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {}
    }
    
}
