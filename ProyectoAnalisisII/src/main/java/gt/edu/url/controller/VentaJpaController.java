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
import gt.edu.url.entity.Usuario;
import gt.edu.url.entity.Cliente;
import gt.edu.url.entity.DetalleVenta;
import gt.edu.url.entity.Venta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Oswaldo Alvarez <mynoswaldo@gmail.com>
 */
public class VentaJpaController implements Serializable {

    private EntityManager em = null;
    public VentaJpaController(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEntityManager() {
        return this.em;
    }

    public void create(Venta venta) {
        if (venta.getDetalleVentaCollection() == null) {
            venta.setDetalleVentaCollection(new ArrayList<DetalleVenta>());
        }
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioid = venta.getUsuarioid();
            if (usuarioid != null) {
                usuarioid = em.getReference(usuarioid.getClass(), usuarioid.getId());
                venta.setUsuarioid(usuarioid);
            }
            Cliente clienteId = venta.getClienteId();
            if (clienteId != null) {
                clienteId = em.getReference(clienteId.getClass(), clienteId.getId());
                venta.setClienteId(clienteId);
            }
            Collection<DetalleVenta> attachedDetalleVentaCollection = new ArrayList<DetalleVenta>();
            for (DetalleVenta detalleVentaCollectionDetalleVentaToAttach : venta.getDetalleVentaCollection()) {
                detalleVentaCollectionDetalleVentaToAttach = em.getReference(detalleVentaCollectionDetalleVentaToAttach.getClass(), detalleVentaCollectionDetalleVentaToAttach.getId());
                attachedDetalleVentaCollection.add(detalleVentaCollectionDetalleVentaToAttach);
            }
            venta.setDetalleVentaCollection(attachedDetalleVentaCollection);
            em.persist(venta);
            if (usuarioid != null) {
                usuarioid.getVentaCollection().add(venta);
                usuarioid = em.merge(usuarioid);
            }
            if (clienteId != null) {
                clienteId.getVentaCollection().add(venta);
                clienteId = em.merge(clienteId);
            }
            for (DetalleVenta detalleVentaCollectionDetalleVenta : venta.getDetalleVentaCollection()) {
                Venta oldVentaidOfDetalleVentaCollectionDetalleVenta = detalleVentaCollectionDetalleVenta.getVentaid();
                detalleVentaCollectionDetalleVenta.setVentaid(venta);
                detalleVentaCollectionDetalleVenta = em.merge(detalleVentaCollectionDetalleVenta);
                if (oldVentaidOfDetalleVentaCollectionDetalleVenta != null) {
                    oldVentaidOfDetalleVentaCollectionDetalleVenta.getDetalleVentaCollection().remove(detalleVentaCollectionDetalleVenta);
                    oldVentaidOfDetalleVentaCollectionDetalleVenta = em.merge(oldVentaidOfDetalleVentaCollectionDetalleVenta);
                }
            }
            em.getTransaction().commit();
        } finally {}
    }

    public void edit(Venta venta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getId());
            Usuario usuarioidOld = persistentVenta.getUsuarioid();
            Usuario usuarioidNew = venta.getUsuarioid();
            Cliente clienteIdOld = persistentVenta.getClienteId();
            Cliente clienteIdNew = venta.getClienteId();
            Collection<DetalleVenta> detalleVentaCollectionOld = persistentVenta.getDetalleVentaCollection();
            Collection<DetalleVenta> detalleVentaCollectionNew = venta.getDetalleVentaCollection();
            List<String> illegalOrphanMessages = null;
            for (DetalleVenta detalleVentaCollectionOldDetalleVenta : detalleVentaCollectionOld) {
                if (!detalleVentaCollectionNew.contains(detalleVentaCollectionOldDetalleVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleVenta " + detalleVentaCollectionOldDetalleVenta + " since its ventaid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioidNew != null) {
                usuarioidNew = em.getReference(usuarioidNew.getClass(), usuarioidNew.getId());
                venta.setUsuarioid(usuarioidNew);
            }
            if (clienteIdNew != null) {
                clienteIdNew = em.getReference(clienteIdNew.getClass(), clienteIdNew.getId());
                venta.setClienteId(clienteIdNew);
            }
            Collection<DetalleVenta> attachedDetalleVentaCollectionNew = new ArrayList<DetalleVenta>();
            for (DetalleVenta detalleVentaCollectionNewDetalleVentaToAttach : detalleVentaCollectionNew) {
                detalleVentaCollectionNewDetalleVentaToAttach = em.getReference(detalleVentaCollectionNewDetalleVentaToAttach.getClass(), detalleVentaCollectionNewDetalleVentaToAttach.getId());
                attachedDetalleVentaCollectionNew.add(detalleVentaCollectionNewDetalleVentaToAttach);
            }
            detalleVentaCollectionNew = attachedDetalleVentaCollectionNew;
            venta.setDetalleVentaCollection(detalleVentaCollectionNew);
            venta = em.merge(venta);
            if (usuarioidOld != null && !usuarioidOld.equals(usuarioidNew)) {
                usuarioidOld.getVentaCollection().remove(venta);
                usuarioidOld = em.merge(usuarioidOld);
            }
            if (usuarioidNew != null && !usuarioidNew.equals(usuarioidOld)) {
                usuarioidNew.getVentaCollection().add(venta);
                usuarioidNew = em.merge(usuarioidNew);
            }
            if (clienteIdOld != null && !clienteIdOld.equals(clienteIdNew)) {
                clienteIdOld.getVentaCollection().remove(venta);
                clienteIdOld = em.merge(clienteIdOld);
            }
            if (clienteIdNew != null && !clienteIdNew.equals(clienteIdOld)) {
                clienteIdNew.getVentaCollection().add(venta);
                clienteIdNew = em.merge(clienteIdNew);
            }
            for (DetalleVenta detalleVentaCollectionNewDetalleVenta : detalleVentaCollectionNew) {
                if (!detalleVentaCollectionOld.contains(detalleVentaCollectionNewDetalleVenta)) {
                    Venta oldVentaidOfDetalleVentaCollectionNewDetalleVenta = detalleVentaCollectionNewDetalleVenta.getVentaid();
                    detalleVentaCollectionNewDetalleVenta.setVentaid(venta);
                    detalleVentaCollectionNewDetalleVenta = em.merge(detalleVentaCollectionNewDetalleVenta);
                    if (oldVentaidOfDetalleVentaCollectionNewDetalleVenta != null && !oldVentaidOfDetalleVentaCollectionNewDetalleVenta.equals(venta)) {
                        oldVentaidOfDetalleVentaCollectionNewDetalleVenta.getDetalleVentaCollection().remove(detalleVentaCollectionNewDetalleVenta);
                        oldVentaidOfDetalleVentaCollectionNewDetalleVenta = em.merge(oldVentaidOfDetalleVentaCollectionNewDetalleVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = venta.getId();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {}
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DetalleVenta> detalleVentaCollectionOrphanCheck = venta.getDetalleVentaCollection();
            for (DetalleVenta detalleVentaCollectionOrphanCheckDetalleVenta : detalleVentaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Venta (" + venta + ") cannot be destroyed since the DetalleVenta " + detalleVentaCollectionOrphanCheckDetalleVenta + " in its detalleVentaCollection field has a non-nullable ventaid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuarioid = venta.getUsuarioid();
            if (usuarioid != null) {
                usuarioid.getVentaCollection().remove(venta);
                usuarioid = em.merge(usuarioid);
            }
            Cliente clienteId = venta.getClienteId();
            if (clienteId != null) {
                clienteId.getVentaCollection().remove(venta);
                clienteId = em.merge(clienteId);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {}
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {}
    }

    public Venta findVenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {}
    }
    
}
