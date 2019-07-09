package gt.edu.url.clases;

import gt.edu.url.controller.ClienteJpaController;
import gt.edu.url.controller.DetalleVentaJpaController;
import gt.edu.url.controller.ProductoJpaController;
import gt.edu.url.controller.VentaJpaController;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Héctor Tello <hectortllo@gmail.com>
 */
public class Venta {
    private ClienteJpaController controllerCliente;
    private VentaJpaController controllerVenta;
    private DetalleVentaJpaController controllerDetalleVenta;
    private ProductoJpaController controllerProducto;
    private List<Producto> productos;
    
    
    
    public Venta(EntityManager em){
        controllerCliente = new ClienteJpaController(em);
        controllerVenta = new VentaJpaController(em);
        controllerDetalleVenta = new DetalleVentaJpaController(em);
        controllerProducto = new ProductoJpaController(em);
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
}
