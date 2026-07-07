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
public class Venta {
    private int id;
    private LocalDate fechavendida;
    private String nombredelmedicamento;
    private int cantidadeproductos;
    private double preciodeventa;

    public Venta() {
    }

    public Venta(int id, LocalDate fechavendida) {
        this.id = id;
        this.fechavendida = fechavendida;
    }

    public Venta(int id, LocalDate fechavendida, String nombredelmedicamento, int cantidadeproductos, double preciodeventa) {
        this.id = id;
        this.fechavendida = fechavendida;
        this.nombredelmedicamento = nombredelmedicamento;
        this.cantidadeproductos = cantidadeproductos;
        this.preciodeventa = preciodeventa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechavendida() {
        return fechavendida;
    }

    public void setFechavendida(LocalDate fechavendida) {
        this.fechavendida = fechavendida;
    }

    public String getNombredelmedicamento() {
        return nombredelmedicamento;
    }

    public void setNombredelmedicamento(String nombredelmedicamento) {
        this.nombredelmedicamento = nombredelmedicamento;
    }

    public int getCantidadeproductos() {
        return cantidadeproductos;
    }

    public void setCantidadeproductos(int cantidadeproductos) {
        this.cantidadeproductos = cantidadeproductos;
    }

    public double getPreciodeventa() {
        return preciodeventa;
    }

    public void setPreciodeventa(double preciodeventa) {
        this.preciodeventa = preciodeventa;
    }

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", fechavendida=" + fechavendida + ", nombredelmedicamento=" + nombredelmedicamento + ", cantidadeproductos=" + cantidadeproductos + ", preciodeventa=" + preciodeventa + '}';
    }
public double Costotal(){
    return cantidadeproductos*preciodeventa;
}
   
}
