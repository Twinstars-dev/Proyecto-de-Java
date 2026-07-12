/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Proveedor;

/**
 *
 * @author Anderson
 */
//Clase que gestiona los proveedores 
public class GestionProveedores {
    private Proveedor[] proveedores;
    private int contador;

    public GestionProveedores() {
        proveedores = new Proveedor[50];
        contador = 0;
    }

    public void registrarProveedor(Proveedor p) {
        if (contador < proveedores.length) {
            proveedores[contador] = p;
            contador++;
            System.out.println("Proveedor registrado correctamente.");
        } else {
            System.out.println("No hay espacio para más proveedores.");
        }
    }

    public Proveedor obtenerProveedor(int codigo) {
        for (int i = 0; i < contador; i++) {
            if (proveedores[i] != null && proveedores[i].getId() == codigo) {
                return proveedores[i];
            }
        }
        return null;
    }

    public void buscarProveedor(int codigo) {
        Proveedor proveedor = obtenerProveedor(codigo);

        if (proveedor != null) {
            System.out.println("========== PROVEEDOR ==========");
            System.out.println("Código: " + proveedor.getId());
            System.out.println("Nombre: " + proveedor.getNombre());
            System.out.println("Teléfono: " + proveedor.getTelefono());
            System.out.println("Correo: " + proveedor.getEmail());
            System.out.println("Tipo de medicamento: " + proveedor.getTipodemedicamento());
        } else {
            System.out.println("Proveedor no encontrado.");
        }
    }

    public void listarProveedor() {
        if (contador == 0) {
            System.out.println("No existen proveedores registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < contador; i++) {
            sb.append("\n=============================\n");
            sb.append("Código: ").append(proveedores[i].getId()).append("\n");
            sb.append("Nombre: ").append(proveedores[i].getNombre()).append("\n");
            sb.append("Teléfono: ").append(proveedores[i].getTelefono()).append("\n");
            sb.append("Correo: ").append(proveedores[i].getEmail()).append("\n");
            sb.append("Tipo de medicamento: ").append(proveedores[i].getTipodemedicamento()).append("\n");
        }

        System.out.println(sb.toString());
    }

    public void actualizarProveedor(String nombreBuscar, int nuevoTelefono, String nuevoEmail) {
        boolean encontrado = false;

        for (int i = 0; i < contador; i++) {
            if (proveedores[i] != null && proveedores[i].getNombre().equalsIgnoreCase(nombreBuscar)) {
                proveedores[i].setTelefono(nuevoTelefono);
                proveedores[i].setEmail(nuevoEmail);
                encontrado = true;
                System.out.println("Proveedor actualizado correctamente.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Proveedor no encontrado.");
        }
    }

    public void eliminarProveedor(int codigoBuscar) {
        boolean encontrado = false;

        for (int i = 0; i < contador; i++) {
            if (proveedores[i] != null && proveedores[i].getId() == codigoBuscar) {
                encontrado = true;

                // Desplazar los elementos para rellenar el vacío
                for (int j = i; j < contador - 1; j++) {
                    proveedores[j] = proveedores[j + 1];
                }

                proveedores[contador - 1] = null;
                contador--;

                System.out.println("Proveedor eliminado correctamente.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Proveedor no encontrado.");
        }
    }

    public int cantidadDeProveedores() {
        return contador;
    }
}

