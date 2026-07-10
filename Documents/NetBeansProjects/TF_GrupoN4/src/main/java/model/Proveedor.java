/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Anderson
 */
public class Proveedor {
private int id;
private String nombre;
private int telefono;
private String email;
private String tipodemedicamento;

    public Proveedor() {
    }

    public Proveedor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Proveedor(int id, String nombre, int telefono, String email,String tipodemedicamento) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.tipodemedicamento = tipodemedicamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
        public String getTipodemedicamento() {
        return tipodemedicamento;
    }

    public void setTipodemedicamento(String tipodemedicamento) {
        this.tipodemedicamento = tipodemedicamento;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + ", tipodemedicamento=" + tipodemedicamento + '}';
    }



}
