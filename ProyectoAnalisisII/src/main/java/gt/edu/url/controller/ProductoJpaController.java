/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.controller;

import gt.edu.url.controller.exceptions.IllegalOrphanException;
import gt.edu.url.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import gt.edu.url.entity.TipoProducto;
import gt.edu.url.entity.DetalleVenta;
import gt.edu.url.entity.Producto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Oswaldo Alvarez <mynoswaldo@gmail.com>
 */
public class ProductoJpaController implements Serializable {

    private EntityManager em = null;
    public ProductoJpaController(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEntityManager() {
        return this.em;
    }

    public void create(Producto producto) {
        if (producto.getDetalleVentaCollection() == null) {
            producto.setDetalleVentaCollection(new ArrayList<DetalleVenta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoProducto tipoProductoid = producto.getTipoProductoid();
            if (tipoProductoid != null) {
                tipoProductoid = em.getReference(tipoProductoid.getClass(), tipoProductoid.getId());
                producto.setTipoProductoid(tipoProductoid);
            }
            Collection<DetalleVenta> attachedDetalleVentaCollection = new ArrayList<DetalleVenta>();
            for (DetalleVenta detalleVentaCollectionDetalleVentaToAttach : producto.getDetalleVentaCollection()) {
                detalleVentaCollectionDetalleVentaToAttach = em.getReference(detalleVentaCollectionDetalleVentaToAttach.getClass(), detalleVentaCollectionDetalleVentaToAttach.getId());
                attachedDetalleVentaCollection.add(detalleVentaCollectionDetalleVentaToAttach);
            }
            producto.setDetalleVentaCollection(attachedDetalleVentaCollection);
            em.persist(producto);
            if (tipoProductoid != null) {
                tipoProductoid.getProductos().add(producto);
                tipoProductoid = em.merge(tipoProductoid);
            }
            for (DetalleVenta detalleVentaCollectionDetalleVenta : producto.getDetalleVentaCollection()) {
                Producto oldProductoidOfDetalleVentaCollectionDetalleVenta = detalleVentaCollectionDetalleVenta.getProductoid();
                detalleVentaCollectionDetalleVenta.setProductoid(producto);
                detalleVentaCollectionDetalleVenta = em.merge(detalleVentaCollectionDetalleVenta);
                if (oldProductoidOfDetalleVentaCollectionDetalleVenta != null) {
                    oldProductoidOfDetalleVentaCollectionDetalleVenta.getDetalleVentaCollection().remove(detalleVentaCollectionDetalleVenta);
                    oldProductoidOfDetalleVentaCollectionDetalleVenta = em.merge(oldProductoidOfDetalleVentaCollectionDetalleVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {}
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getId());
            TipoProducto tipoProductoidOld = persistentProducto.getTipoProductoid();
            TipoProducto tipoProductoidNew = producto.getTipoProductoid();
            Collection<DetalleVenta> detalleVentaCollectionOld = persistentProducto.getDetalleVentaCollection();
            Collection<DetalleVenta> detalleVentaCollectionNew = producto.getDetalleVentaCollection();
            List<String> illegalOrphanMessages = null;
            for (DetalleVenta detalleVentaCollectionOldDetalleVenta : detalleVentaCollectionOld) {
                if (!detalleVentaCollectionNew.contains(detalleVentaCollectionOldDetalleVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleVenta " + detalleVentaCollectionOldDetalleVenta + " since its productoid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tipoProductoidNew != null) {
                tipoProductoidNew = em.getReference(tipoProductoidNew.getClass(), tipoProductoidNew.getId());
                producto.setTipoProductoid(tipoProductoidNew);
            }
            Collection<DetalleVenta> attachedDetalleVentaCollectionNew = new ArrayList<DetalleVenta>();
            for (DetalleVenta detalleVentaCollectionNewDetalleVentaToAttach : detalleVentaCollectionNew) {
                detalleVentaCollectionNewDetalleVentaToAttach = em.getReference(detalleVentaCollectionNewDetalleVentaToAttach.getClass(), detalleVentaCollectionNewDetalleVentaToAttach.getId());
                attachedDetalleVentaCollectionNew.add(detalleVentaCollectionNewDetalleVentaToAttach);
            }
            detalleVentaCollectionNew = attachedDetalleVentaCollectionNew;
            producto.setDetalleVentaCollection(detalleVentaCollectionNew);
            producto = em.merge(producto);
            if (tipoProductoidOld != null && !tipoProductoidOld.equals(tipoProductoidNew)) {
                tipoProductoidOld.getProductos().remove(producto);
                tipoProductoidOld = em.merge(tipoProductoidOld);
            }
            if (tipoProductoidNew != null && !tipoProductoidNew.equals(tipoProductoidOld)) {
                tipoProductoidNew.getProductos().add(producto);
                tipoProductoidNew = em.merge(tipoProductoidNew);
            }
            for (DetalleVenta detalleVentaCollectionNewDetalleVenta : detalleVentaCollectionNew) {
                if (!detalleVentaCollectionOld.contains(detalleVentaCollectionNewDetalleVenta)) {
                    Producto oldProductoidOfDetalleVentaCollectionNewDetalleVenta = detalleVentaCollectionNewDetalleVenta.getProductoid();
                    detalleVentaCollectionNewDetalleVenta.setProductoid(producto);
                    detalleVentaCollectionNewDetalleVenta = em.merge(detalleVentaCollectionNewDetalleVenta);
                    if (oldProductoidOfDetalleVentaCollectionNewDetalleVenta != null && !oldProductoidOfDetalleVentaCollectionNewDetalleVenta.equals(producto)) {
                        oldProductoidOfDetalleVentaCollectionNewDetalleVenta.getDetalleVentaCollection().remove(detalleVentaCollectionNewDetalleVenta);
                        oldProductoidOfDetalleVentaCollectionNewDetalleVenta = em.merge(oldProductoidOfDetalleVentaCollectionNewDetalleVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getId();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {}
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DetalleVenta> detalleVentaCollectionOrphanCheck = producto.getDetalleVentaCollection();
            for (DetalleVenta detalleVentaCollectionOrphanCheckDetalleVenta : detalleVentaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the DetalleVenta " + detalleVentaCollectionOrphanCheckDetalleVenta + " in its detalleVentaCollection field has a non-nullable productoid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoProducto tipoProductoid = producto.getTipoProductoid();
            if (tipoProductoid != null) {
                tipoProductoid.getProductos().remove(producto);
                tipoProductoid = em.merge(tipoProductoid);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {}
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {}
    }

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {}
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {}
    }
    
}
