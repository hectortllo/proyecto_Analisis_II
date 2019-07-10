package gt.edu.url.clases;

/**
 *
 * @author Héctor Tello <hectortllo@gmail.com>
 */
public abstract class AbstractProduct {

    private gt.edu.url.entity.Producto producto;

    public int getId() {
        return producto.getId();
    }

    public int getCantidad() {
        return producto.getCantidad();
    }

    public AbstractProduct(int id, String nombre, float precio, int cantidad) {
        producto = new gt.edu.url.entity.Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setId(id);
        producto.setCantidad(cantidad);
    }

    public String getNombre() {
        return producto.getNombre();
    }

    public float getPrecio() {
        return producto.getPrecio();
    }

    public String getDescripcion() {
        return producto.getDescripcion();
    }
}
