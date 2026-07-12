
package model;
import java.time.LocalDate;
/**
 *
 * @author Grupo Farmacia
 */
// Clase que representa una venta de la famarcia
public class Venta {
    // Atributos privados
    private int id;
    private LocalDate fechaVendida;
    private String nombreDelMedicamento;
    private int cantidadDeProductos;
    private double precioDeVenta;
    private Cliente cliente;
    //Constructor Vacío
    public Venta() {
    }
    
    //Constructor con 2 parametros
    public Venta(int id, LocalDate fechaVendida) {
        this.id = id;
        this.fechaVendida = fechaVendida;
    }
    
    //Constructor con todos los parametros 
    public Venta(int id, LocalDate fechaVendida, String nombreDelMedicamento, int cantidadDeProductos, double precioDeVenta, Cliente cliente) {
        this.id = id;
        this.fechaVendida = fechaVendida;
        this.nombreDelMedicamento = nombreDelMedicamento;
        this.cantidadDeProductos = cantidadDeProductos;
        this.precioDeVenta = precioDeVenta;
        this.cliente=cliente;
    }
    
    //Creación de los setters y getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaVendida() {
        return fechaVendida;
    }

    public void setFechaVendida(LocalDate fechaVendida) {
        this.fechaVendida = fechaVendida;
    }

    public String getNombreDelMedicamento() {
        return nombreDelMedicamento;
    }

    public void setNombreDelMedicamento(String nombreDelMedicamento) {
        this.nombreDelMedicamento = nombreDelMedicamento;
    }

    public int getCantidadDeProductos() {
        return cantidadDeProductos;
    }

    public void setCantidadDeProductos(int cantidadDeProductos) {
        this.cantidadDeProductos = cantidadDeProductos;
    }

    public double getPrecioDeVenta() {
        return precioDeVenta;
    }

    public void setPrecioDeVenta(double precioDeVenta) {
        this.precioDeVenta = precioDeVenta;
    }
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    //Método que facilita la impresión en consola del objeto de tipo Venta
    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", fechaVendida=" + fechaVendida + ", nombreDelMedicamento=" + nombreDelMedicamento + ", cantidadDeProductos=" + cantidadDeProductos + ", precioDeVenta=" + precioDeVenta + ", Cliente="+ cliente+ '}';
    }
    
}   