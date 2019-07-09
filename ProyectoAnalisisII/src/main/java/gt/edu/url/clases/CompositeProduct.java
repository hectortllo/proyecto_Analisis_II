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
public class CompositeProduct extends AbstractProduct {
    
    private List<AbstractProduct> productos = new ArrayList<AbstractProduct>();
    
    public CompositeProduct(String nombre){
        super(0, "", 0, 0);
    }
    
    @Override
    public float getPrecio(){
        float precio = 0;
        for(AbstractProduct producto : productos){
            precio += producto.getPrecio();
        }
        return precio;
    }
    
    public void addProducto(AbstractProduct producto){
        this.productos.add(producto);
    }
    
    public boolean removerProducto(AbstractProduct producto){
        return this.productos.remove(producto);        
    }
    
}
