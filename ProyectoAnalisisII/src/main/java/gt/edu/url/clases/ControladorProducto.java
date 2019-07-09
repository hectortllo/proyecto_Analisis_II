/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Héctor Tello <hectortllo@gmail.com>
 */
public class ControladorProducto {
    
    private List<AbstractProduct> productos;
    
    public ControladorProducto(){
        productos = new ArrayList<AbstractProduct>();
    }
    
    public List getProductos(){
        return this.productos;
    }
    
    public void setProductos(List productos){
        this.productos = productos;
    }
    
    public void addProducto(AbstractProduct producto){
        this.productos.add(producto);
    }
    
    public void removerProducto(AbstractProduct producto){
        this.productos.remove(producto);
    }
    
    public float getPrecio(){
        float precio = 0;
        for(AbstractProduct product : productos){
            precio += product.getPrecio();
        }
        return precio;
    }
    
    public void imprimir(){
        System.out.println("----------------------------------------------");
        for(AbstractProduct product : productos){
            System.out.println(product.getNombre() + " " + product.getPrecio()
            + product.getCantidad());
        }
        System.out.println("----------------------------------------------");
    }
    
}
