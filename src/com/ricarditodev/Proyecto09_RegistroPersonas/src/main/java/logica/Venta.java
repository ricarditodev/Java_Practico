package logica;

/**
 *
 * @author KA EduSoft
 */
public class Venta {
    private String codigo;
    private Cliente cliente;
    private Producto producto;
    private int cantidad;

    public Venta(String codigo, Cliente cliente, Producto producto, int cantidad) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.producto= producto;
        this.cantidad = cantidad;
    }

    public double getMonto(){
        return this.producto.getPrecio()*this.cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
