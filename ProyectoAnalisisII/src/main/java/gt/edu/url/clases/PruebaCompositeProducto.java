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
public class PruebaCompositeProducto {
    public static void main(String[] args) {
        Producto aceiteC = new Producto("Aceite", 15, "Castrol");
        Producto aceiteM = new Producto("AceiteM", 15, "Mobil");
        
        Producto llantaA = new Producto("Llanta", 150, "A");
        Producto llantaB = new Producto("LlantaB", 200, "B");
        
        Producto aroA = new Producto("Aro", 50, "A");
        Producto aroB = new Producto("Aro", 60, "B");
        
        CompositeProduct comboA = new CompositeProduct("Combo A");
        comboA.addProducto(aceiteC);
        comboA.addProducto(llantaA);
        comboA.addProducto(aroA);
        
        CompositeProduct comboB = new CompositeProduct("Combo B");
        comboB.addProducto(aceiteM);
        comboB.addProducto(llantaB);
        comboB.addProducto(aroB);
        //comboB.imprimir();
        
        ControladorProducto controlador = new ControladorProducto();
        controlador.addProducto(comboA);
        controlador.imprimir();
        
        ControladorProducto controladorB = new ControladorProducto();
        controladorB.addProducto(comboB);
        controladorB.imprimir();
        
        ControladorProducto controladorC = new ControladorProducto();
        controladorC.addProducto(aceiteC);
        controladorC.addProducto(aroB);
        controladorC.addProducto(llantaA);
        controladorC.imprimir();
    }
    
}
