/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Grupo Farmacia
 */
//Clase que representa un medicamento de la farmacia.
public class Medicamento {
    //Atributos privados del medicamento (Encapsulamiento)
    private int codigo;
    private String nombre;
    private String laboratorio;
    private int stock;                   
    private double precio;
    private LocalDate fechaDeVencimiento;
    private String categoria;
    private boolean requiereReceta;
    
    //Constructor vacio (Sobrecarga)
    public Medicamento() {
    }
    
    //Constructor con 2 atributos
    public Medicamento(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    
    //Constructor con todos los atributos
    public Medicamento(int codigo, String nombre, String laboratorio, int stock, double precio, LocalDate fechaDeVencimiento, String categoria, boolean requiereReceta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.laboratorio = laboratorio;
        this.stock = stock;
        this.precio = precio;
        this.fechaDeVencimiento = fechaDeVencimiento;
        this.categoria = categoria;
        this.requiereReceta = requiereReceta;
    }
    
    //Getters y Setters de los atributos
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public void setFechaDeVencimiento(LocalDate fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isRequiereReceta() {
        return requiereReceta;
    }

    public void setRequiereReceta(boolean requiereReceta) {
        this.requiereReceta = requiereReceta;
    }
    
    /**Método para convertir la fecha al formato day/month/year usando
     *la clase DateTimeFormatter, entonces retorna la fecha formateada
     */
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Código: " + codigo + " | Nombre: " + nombre + 
               " | Stock: " + stock + " | Precio: S/" + precio + 
               " | Vence: " + fechaDeVencimiento.format(fmt);
    }
}

