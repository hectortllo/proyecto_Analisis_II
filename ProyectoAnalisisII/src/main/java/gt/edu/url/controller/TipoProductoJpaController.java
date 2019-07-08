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
import gt.edu.url.entity.Producto;
import gt.edu.url.entity.TipoProducto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Oswaldo Alvarez <mynoswaldo@gmail.com>
 */
public class TipoProductoJpaController implements Serializable {

    private EntityManager em = null;
    public TipoProductoJpaController(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEntityManager() {
        return this.em;
    }

    public void create(TipoProducto tipoProducto) {
        if (tipoProducto.getProductos() == null) {
            tipoProducto.setProductos(new ArrayList<Producto>());
        }
        try {
            em.getTransaction().begin();
            Collection<Producto> attachedProductoCollection = new ArrayList<Producto>();
            for (Producto productoCollectionProductoToAttach : tipoProducto.getProductos()) {
                productoCollectionProductoToAttach = em.getReference(productoCollectionProductoToAttach.getClass(), productoCollectionProductoToAttach.getId());
                attachedProductoCollection.add(productoCollectionProductoToAttach);
            }
            tipoProducto.setProductos(attachedProductoCollection);
            em.persist(tipoProducto);
            for (Producto productoCollectionProducto : tipoProducto.getProductos()) {
                TipoProducto oldTipoProductoidOfProductoCollectionProducto = productoCollectionProducto.getTipoProductoid();
                productoCollectionProducto.setTipoProductoid(tipoProducto);
                productoCollectionProducto = em.merge(productoCollectionProducto);
                if (oldTipoProductoidOfProductoCollectionProducto != null) {
                    oldTipoProductoidOfProductoCollectionProducto.getProductos().remove(productoCollectionProducto);
                    oldTipoProductoidOfProductoCollectionProducto = em.merge(oldTipoProductoidOfProductoCollectionProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
        }
    }

    public void edit(TipoProducto tipoProducto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            TipoProducto persistentTipoProducto = em.find(TipoProducto.class, tipoProducto.getId());
            Collection<Producto> productoCollectionOld = persistentTipoProducto.getProductos();
            Collection<Producto> productoCollectionNew = tipoProducto.getProductos();
            List<String> illegalOrphanMessages = null;
            for (Producto productoCollectionOldProducto : productoCollectionOld) {
                if (!productoCollectionNew.contains(productoCollectionOldProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Producto " + productoCollectionOldProducto + " since its tipoProductoid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Producto> attachedProductoCollectionNew = new ArrayList<Producto>();
            for (Producto productoCollectionNewProductoToAttach : productoCollectionNew) {
                productoCollectionNewProductoToAttach = em.getReference(productoCollectionNewProductoToAttach.getClass(), productoCollectionNewProductoToAttach.getId());
                attachedProductoCollectionNew.add(productoCollectionNewProductoToAttach);
            }
            productoCollectionNew = attachedProductoCollectionNew;
            tipoProducto.setProductos(productoCollectionNew);
            tipoProducto = em.merge(tipoProducto);
            for (Producto productoCollectionNewProducto : productoCollectionNew) {
                if (!productoCollectionOld.contains(productoCollectionNewProducto)) {
                    TipoProducto oldTipoProductoidOfProductoCollectionNewProducto = productoCollectionNewProducto.getTipoProductoid();
                    productoCollectionNewProducto.setTipoProductoid(tipoProducto);
                    productoCollectionNewProducto = em.merge(productoCollectionNewProducto);
                    if (oldTipoProductoidOfProductoCollectionNewProducto != null && !oldTipoProductoidOfProductoCollectionNewProducto.equals(tipoProducto)) {
                        oldTipoProductoidOfProductoCollectionNewProducto.getProductos().remove(productoCollectionNewProducto);
                        oldTipoProductoidOfProductoCollectionNewProducto = em.merge(oldTipoProductoidOfProductoCollectionNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoProducto.getId();
                if (findTipoProducto(id) == null) {
                    throw new NonexistentEntityException("The tipoProducto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        try {
            em.getTransaction().begin();
            TipoProducto tipoProducto;
            try {
                tipoProducto = em.getReference(TipoProducto.class, id);
                tipoProducto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoProducto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Producto> productoCollectionOrphanCheck = tipoProducto.getProductos();
            for (Producto productoCollectionOrphanCheckProducto : productoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoProducto (" + tipoProducto + ") cannot be destroyed since the Producto " + productoCollectionOrphanCheckProducto + " in its productoCollection field has a non-nullable tipoProductoid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoProducto);
            em.getTransaction().commit();
        } finally {
        }
    }

    public List<TipoProducto> findTipoProductoEntities() {
        return findTipoProductoEntities(true, -1, -1);
    }

    public List<TipoProducto> findTipoProductoEntities(int maxResults, int firstResult) {
        return findTipoProductoEntities(false, maxResults, firstResult);
    }

    private List<TipoProducto> findTipoProductoEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoProducto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public TipoProducto findTipoProducto(Integer id) {
        try {
            return em.find(TipoProducto.class, id);
        } finally {
        }
    }

    public int getTipoProductoCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoProducto> rt = cq.from(TipoProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
        }
    }

    public List<Producto> mostrar(int id) {
        try {
            TipoProducto tipoProducto = em.find(TipoProducto.class, id);
            List<Producto> productos = (List<Producto>) tipoProducto.getProductos();
            return productos;
        } finally {
        }
    }
}
