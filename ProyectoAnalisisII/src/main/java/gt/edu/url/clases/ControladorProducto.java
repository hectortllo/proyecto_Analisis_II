/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.clases;

import gt.edu.url.controller.ProductoJpaController;
import gt.edu.url.controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Héctor Tello <hectortllo@gmail.com>
 */
public class ControladorProducto {

    private List<AbstractProduct> productos;
    private ProductoJpaController controller;

    public ControladorProducto(EntityManager em) {
        controller = new ProductoJpaController(em);
        productos = new ArrayList<AbstractProduct>();
    }

    public List getProductos() {
        return this.productos;
    }

    public void setProductos(List productos) {
        this.productos = productos;
    }

    public void addProducto(AbstractProduct producto) {
        this.productos.add(producto);
    }

    public void removerProducto(AbstractProduct producto) {
        this.productos.remove(producto);
    }

    public float getPrecio() {
        float precio = 0;
        for (AbstractProduct product : productos) {
            precio += product.getPrecio() * product.getCantidad();
        }
        return precio;
    }

    public void imprimir() {
        System.out.println("----------------------------------------------");
        for (AbstractProduct product : productos) {
            System.out.println(product.getNombre() + " " + product.getPrecio()
                    + product.getCantidad());
        }
        System.out.println("----------------------------------------------");
    }

    public void ActualizarProducto() {
        for (AbstractProduct product : productos) {
            try {
                gt.edu.url.entity.Producto producto = controller.findProducto(product.getId());
                producto.setCantidad(producto.getCantidad() - product.getCantidad());
                controller.edit(producto);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
