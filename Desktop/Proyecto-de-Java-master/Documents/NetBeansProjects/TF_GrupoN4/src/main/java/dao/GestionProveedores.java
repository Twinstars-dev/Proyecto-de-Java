/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Proveedor;

/**
 * Clase que gestiona las operaciones relacionadas con los proveedores
 * @author Anderson
 */
//Clase que gestiona los proveedores 
public class GestionProveedores {
    
    // ===== ATRIBUTOS =====
    private Proveedor[] proveedores;  // Arreglo para almacenar proveedores
    private int contador;             // Controla cuántos proveedores hay registrados

    // ===== CONSTRUCTOR =====
    // Inicializa el arreglo con capacidad para 50 proveedores
    public GestionProveedores() {
        proveedores = new Proveedor[50];
        contador = 0;
    }

    // ===== MÉTODO PARA REGISTRAR UN PROVEEDOR =====
    /**
     * Agrega un nuevo proveedor al arreglo
     * @param p Objeto Proveedor a registrar
     */
    public void registrarProveedor(Proveedor p) {
        // Verifica si hay espacio disponible
        if (contador < proveedores.length) {
            proveedores[contador] = p;  // Guarda el proveedor en la posición actual
            contador++;                  // Incrementa el contador
            System.out.println("Proveedor registrado correctamente.");
        } else {
            System.out.println("No hay espacio para más proveedores.");
        }
    }

    // ===== MÉTODO PARA OBTENER UN PROVEEDOR POR SU ID =====
    /**
     * Busca un proveedor por su ID
     * @param codigo ID del proveedor a buscar
     * @return El objeto Proveedor si lo encuentra, o null si no existe
     */
    public Proveedor obtenerProveedor(int codigo) {
        // Recorre todos los proveedores registrados
        for (int i = 0; i < contador; i++) {
            // Verifica si el proveedor existe y si su ID coincide
            if (proveedores[i] != null && proveedores[i].getId() == codigo) {
                return proveedores[i];  // Retorna el proveedor encontrado
            }
        }
        return null;  // Retorna null si no se encontró
    }

    // ===== MÉTODO PARA BUSCAR Y MOSTRAR UN PROVEEDOR =====
    /**
     * Busca un proveedor por su ID y muestra sus datos en consola
     * @param codigo ID del proveedor a buscar
     */
    public void buscarProveedor(int codigo) {
        // Obtiene el proveedor usando el método auxiliar
        Proveedor proveedor = obtenerProveedor(codigo);

        // Si el proveedor existe, muestra sus datos
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

    // ===== MÉTODO PARA LISTAR TODOS LOS PROVEEDORES =====
    /**
     * Muestra en consola la lista completa de proveedores registrados
     * Usa StringBuilder para construir el texto de manera eficiente
     */
    public void listarProveedor() {
        // Verifica si hay proveedores registrados
        if (contador == 0) {
            System.out.println("No existen proveedores registrados.");
            return;
        }

        // Construye el listado usando StringBuilder
        StringBuilder sb = new StringBuilder();

        // Recorre todos los proveedores y agrega sus datos
        for (int i = 0; i < contador; i++) {
            sb.append("\n=============================\n");
            sb.append("Código: ").append(proveedores[i].getId()).append("\n");
            sb.append("Nombre: ").append(proveedores[i].getNombre()).append("\n");
            sb.append("Teléfono: ").append(proveedores[i].getTelefono()).append("\n");
            sb.append("Correo: ").append(proveedores[i].getEmail()).append("\n");
            sb.append("Tipo de medicamento: ").append(proveedores[i].getTipodemedicamento()).append("\n");
        }

        // Muestra todo el texto construido
        System.out.println(sb.toString());
    }

    // ===== MÉTODO PARA ACTUALIZAR UN PROVEEDOR =====
    /**
     * Actualiza los datos de un proveedor buscándolo por su nombre
     * @param nombreBuscar Nombre del proveedor a actualizar
     * @param nuevoTelefono Nuevo número de teléfono
     * @param nuevoEmail Nuevo correo electrónico
     */
    public void actualizarProveedor(String nombreBuscar, int nuevoTelefono, String nuevoEmail) {
        boolean encontrado = false;

        // Recorre todos los proveedores buscando coincidencia por nombre
        for (int i = 0; i < contador; i++) {
            if (proveedores[i] != null && proveedores[i].getNombre().equalsIgnoreCase(nombreBuscar)) {
                // Actualiza los datos del proveedor encontrado
                proveedores[i].setTelefono(nuevoTelefono);
                proveedores[i].setEmail(nuevoEmail);
                encontrado = true;
                System.out.println("Proveedor actualizado correctamente.");
                break;  // Sale del bucle porque ya encontró el proveedor
            }
        }

        // Si no se encontró, muestra mensaje de error
        if (!encontrado) {
            System.out.println("Proveedor no encontrado.");
        }
    }

    // ===== MÉTODO PARA ELIMINAR UN PROVEEDOR =====
    /**
     * Elimina un proveedor del arreglo buscándolo por su ID
     * @param codigoBuscar ID del proveedor a eliminar
     */
    public void eliminarProveedor(int codigoBuscar) {
        boolean encontrado = false;

        // Recorre todos los proveedores buscando el ID
        for (int i = 0; i < contador; i++) {
            if (proveedores[i] != null && proveedores[i].getId() == codigoBuscar) {
                encontrado = true;

                // Desplaza los elementos a la izquierda para rellenar el hueco
                // Ej: [A, B, C, D] → eliminar B → [A, C, D, null]
                for (int j = i; j < contador - 1; j++) {
                    proveedores[j] = proveedores[j + 1];
                }

                // El último espacio queda null y se reduce el contador
                proveedores[contador - 1] = null;
                contador--;

                System.out.println("Proveedor eliminado correctamente.");
                break;  // Sale del bucle
            }
        }

        // Si no se encontró, muestra mensaje de error
        if (!encontrado) {
            System.out.println("Proveedor no encontrado.");
        }
    }

    // ===== MÉTODO PARA OBTENER LA CANTIDAD DE PROVEEDORES =====
    /**
     * Retorna el número total de proveedores registrados
     * @return Cantidad de proveedores
     */
    public int cantidadDeProveedores() {
        return contador;
    }
}

