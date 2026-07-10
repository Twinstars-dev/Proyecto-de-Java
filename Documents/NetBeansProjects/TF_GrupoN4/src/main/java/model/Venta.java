/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;
/**
 *
 * @author Anderson
 */
// Clase que representa una venta de la famarcia
public class Venta {
// Atributos de    
    private int id;
    private LocalDate fechaVendida;
    private String nombreDelMedicamento;
    private int cantidadDeProductos;
    private double precioDeVenta;

    public Venta() {
    }

    public Venta(int id, LocalDate fechaVendida) {
        this.id = id;
        this.fechaVendida = fechaVendida;
    }

    public Venta(int id, LocalDate fechaVendida, String nombreDelMedicamento, int cantidadDeProductos, double precioDeVenta) {
        this.id = id;
        this.fechaVendida = fechaVendida;
        this.nombreDelMedicamento = nombreDelMedicamento;
        this.cantidadDeProductos = cantidadDeProductos;
        this.precioDeVenta = precioDeVenta;
    }

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

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", fechaVendida=" + fechaVendida + ", nombreDelMedicamento=" + nombreDelMedicamento + ", cantidadDeProductos=" + cantidadDeProductos + ", precioDeVenta=" + precioDeVenta + '}';
    }
    
}   