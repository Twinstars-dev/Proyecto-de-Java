/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Grupo Farmacia
 */
// Clase que representa un cliente de la farmacia
public class Cliente {
    // Atributos privados del cliente(Encapsulamienro)    
    private int id;
    private String nombre;
    private boolean recetamedica;
    private int telefono;
    private String email;
    
    //Constructor vacio
    public Cliente() {
    }
    
    //Constructor con 2 atributos
    public Cliente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    //Constructor con todos los atributos
    public Cliente(int id, String nombre, boolean recetamedica, int telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.recetamedica = recetamedica;
        this.telefono = telefono;
        this.email = email;
    }
    
    //Getters y Setters de los atributos
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

    public boolean isRecetamedica() {
        return recetamedica;
    }

    public void setRecetamedica(boolean recetamedica) {
        this.recetamedica = recetamedica;
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
    
    //Método que facilita la lectura de un objeto de la clase Cliente
    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", recetamedica=" + recetamedica + ", telefono=" + telefono + ", email=" + email + '}';
    }
}

