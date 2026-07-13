package dao;

import model.Cliente;

/**
 * Clase que gestiona las operaciones relacionadas con los clientes
 * Permite registrar, buscar, listar, actualizar y eliminar clientes
 */
public class GestionClientes {

    // ===== ATRIBUTOS =====
    private Cliente[] clientes;  // Arreglo para almacenar clientes
    private int contador = 0;    // Controla cuántos clientes hay registrados

    // ===== CONSTRUCTOR =====
    // Inicializa el arreglo con capacidad para 100 clientes
    public GestionClientes() {
        this.clientes = new Cliente[100];
    }

    // ===== MÉTODO PARA AGREGAR UN CLIENTE =====
    /**
     * Agrega un nuevo cliente al arreglo
     * @param c Objeto Cliente a registrar
     */
    public void agregarCliente(Cliente c) {
        // Verifica si hay espacio disponible
        if (contador < clientes.length) {
            clientes[contador] = c;  // Guarda el cliente en la posición actual
            contador++;              // Incrementa el contador
        } else {
            System.out.println("Se alcanzó el límite de clientes");
        }
    }

    // ===== MÉTODO PARA BUSCAR UN CLIENTE POR ID (DEVUELVE NOMBRE) =====
    /**
     * Busca un cliente por su ID y devuelve su nombre
     * @param id ID del cliente a buscar
     * @return El nombre del cliente o un mensaje de error
     */
    public String buscarCliente(int id) {
        // Recorre todos los clientes registrados
        for (int i = 0; i < contador; i++) {
            // Compara el ID del cliente con el buscado
            if (clientes[i].getId() == id) {
                return clientes[i].getNombre();  // Lo encontró, retorna el nombre
            }
        }
        return "Cliente no existente";  // No se encontró
    }

    // ===== MÉTODO PARA BUSCAR CLIENTE POR NOMBRE =====
    /**
     * Busca clientes por nombre (permite coincidencias parciales)
     * Ej: buscar "juan" encuentra "Juan Pérez" y "Juan Carlos"
     * @param nombreBuscar Nombre o parte del nombre a buscar
     */
    public void buscarClientePorNombre(String nombreBuscar) {
        boolean encontrado = false;
        
        // Recorre todos los clientes
        for (int i = 0; i < contador; i++) {
            // "contains" permite buscar coincidencias parciales 
            // "toLowerCase" hace que no distinga entre mayúsculas y minúsculas
            if (clientes[i].getNombre().toLowerCase().contains(nombreBuscar.toLowerCase())) {
                // Muestra el ID y nombre del cliente encontrado
                System.out.println("ID: " + clientes[i].getId() + " | Nombre: " + clientes[i].getNombre());
                encontrado = true;
            }
        }

        // Si no encontró ninguna coincidencia
        if (!encontrado) {
            System.out.println("No se encontraron clientes que coincidan con ese nombre.");
        }
    }

    // ===== MÉTODO PARA BUSCAR CLIENTE POR ID (DEVUELVE OBJETO COMPLETO) =====
    /**
     * Busca un cliente por su ID y devuelve el objeto completo
     * @param id ID del cliente a buscar
     * @return El objeto Cliente si lo encuentra, o null si no existe
     */
    public Cliente buscarClientePorId(int id) {
        // Recorre todos los clientes
        for (int i = 0; i < contador; i++) {
            if (clientes[i].getId() == id) {
                return clientes[i];  // Devuelve el objeto completo
            }
        }
        return null;  //  No se encontró, retorna null
    }

    // ===== MÉTODO PARA LISTAR TODOS LOS CLIENTES =====
    /**
     * Muestra en consola la lista completa de clientes registrados
     * Usa StringBuilder para construir el texto de manera eficiente
     */
    public void listarClientes() {
        // Verifica si hay clientes registrados
        if (contador == 0) {
            System.out.println("No existen clientes.");
            return;
        }
        
        // Construye el listado usando StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("\n================================== LISTA DE CLIENTES ==================================\n");
        
        // Recorre todos los clientes y agrega sus datos
        for (int i = 0; i < contador; i++) {
            sb.append("= Id: ").append(clientes[i].getId()).append("\n");
            sb.append("= Nombre: ").append(clientes[i].getNombre()).append("\n");
            sb.append("= Tiene receta médica? : ").append(clientes[i].isRecetamedica()).append("\n");
            sb.append("= Telefono: ").append(clientes[i].getTelefono()).append("\n");
            sb.append("= Email: ").append(clientes[i].getEmail()).append("\n");
            sb.append("=========================================================================================\n");
        }
        
        // Muestra todo el texto construido
        System.out.println(sb.toString());
    }

    // ===== MÉTODO PARA ACTUALIZAR UN CLIENTE =====
    /**
     * Actualiza el teléfono y email de un cliente buscándolo por su ID
     * @param idBuscar ID del cliente a actualizar
     * @param nuevoTelefono Nuevo número de teléfono
     * @param nuevoEmail Nuevo correo electrónico
     */
    public void actualizarCliente(int idBuscar, int nuevoTelefono, String nuevoEmail) {
        boolean encontrado = false;
        
        // Recorre todos los clientes buscando el ID
        for (int i = 0; i < contador; i++) {
            if (clientes[i].getId() == idBuscar) {
                // Actualiza los datos del cliente encontrado
                clientes[i].setTelefono(nuevoTelefono);
                clientes[i].setEmail(nuevoEmail);
                encontrado = true;
                System.out.println("Cliente actualizado correctamente.");
                break;  // Sale del bucle
            }
        }
        
        // Si no se encontró, muestra mensaje de error
        if (!encontrado) {
            System.out.println("Error: Cliente con ID " + idBuscar + " no existe.");
        }
    }

    // ===== MÉTODO PARA ELIMINAR UN CLIENTE =====
    /**
     * Elimina un cliente del arreglo buscándolo por su ID
     * @param idBuscar ID del cliente a eliminar
     */
    public void eliminarCliente(int idBuscar) {
        boolean encontrado = false;
        
        // Recorre todos los clientes buscando el ID
        for (int i = 0; i < contador; i++) {
            if (clientes[i] != null && clientes[i].getId() == idBuscar) {
                encontrado = true;
                
                // Desplaza los elementos a la izquierda para rellenar el hueco
                // Ej: [A, B, C, D] → eliminar B → [A, C, D, null]
                for (int j = i; j < contador - 1; j++) {
                    clientes[j] = clientes[j + 1];
                }
                
                // El último espacio queda null y se reduce el contador
                clientes[contador - 1] = null;
                contador--;
                System.out.println("Cliente eliminado correctamente.");
                break;  // Sale del bucle
            }
        }
        
        // Si no se encontró, muestra mensaje de error
        if (!encontrado) {
            System.out.println("Error: Cliente con ID " + idBuscar + " no encontrado.");
        }
    }
}