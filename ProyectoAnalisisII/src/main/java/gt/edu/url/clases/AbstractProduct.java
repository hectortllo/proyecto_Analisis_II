
package gt.edu.url.clases;

/**
 *
 * @author Héctor Tello <hectortllo@gmail.com>
 */
public abstract class AbstractProduct {
    private String nombre;
    private float precio;
    private String descripcion;

    public AbstractProduct(String nombre, float precio){
        this.nombre = nombre;
        this.precio = precio;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
        
}
