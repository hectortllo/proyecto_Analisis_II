/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.clases;

/**
 *
 * @author Héctor Tello <hectortllo@gmail.com>
 */
public class Producto extends AbstractProduct {
    private String marca;
    
    public Producto(String nombre, float precio, String marca){
        super(nombre, precio);
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }   
}
