package gt.edu.url.clases;

import gt.edu.url.controller.ClienteJpaController;
import gt.edu.url.controller.DetalleVentaJpaController;
import gt.edu.url.controller.ProductoJpaController;
import gt.edu.url.controller.UsuarioJpaController;
import gt.edu.url.controller.VentaJpaController;
import gt.edu.url.entity.DetalleVenta;
import java.util.Date;
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
    private UsuarioJpaController controllerUsuario;

    public Venta(EntityManager em) {
        controllerCliente = new ClienteJpaController(em);
        controllerVenta = new VentaJpaController(em);
        controllerDetalleVenta = new DetalleVentaJpaController(em);
        controllerProducto = new ProductoJpaController(em);
        controllerUsuario = new UsuarioJpaController(em);
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public boolean realizarVenta(String nombreUs, String nitCliente, float total) {
        try {
            gt.edu.url.entity.Venta venta = new gt.edu.url.entity.Venta();
            venta.setId(1);
            venta.setTotal(total);
            venta.setFecha(new Date());
            venta.setClienteId(controllerCliente.findId(nitCliente));
            venta.setUsuarioid(controllerUsuario.findId(nombreUs));
            controllerVenta.create(venta);
            detalleVenta();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void detalleVenta() {
        gt.edu.url.entity.Venta venta = controllerVenta.findVentaMaxId();
        int cont = 0;
        for (Producto producto : productos) {
            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setCantidad(producto.getCantidad());
            detalleVenta.setId(cont++);
            detalleVenta.setPrecio(producto.getPrecio());
            detalleVenta.setProductoid(controllerProducto.findProducto(producto.getId()));
            detalleVenta.setVentaid(venta);
            controllerDetalleVenta.create(detalleVenta);
        }
    }
}
