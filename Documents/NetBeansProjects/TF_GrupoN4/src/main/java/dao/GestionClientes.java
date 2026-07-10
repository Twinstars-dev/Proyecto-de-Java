package dao;
import model.Cliente;

public class GestionClientes {    
    // Atributos privados: un int y un arreglo de objetos de la clase Cliente
    private Cliente[] clientes;
    private int contador = 0;
    
    // Constructor que asigna 100 espacios en la memoria
    public GestionClientes() {
        this.clientes = new Cliente[100];
    }
    
    // Método que agrega un cliente
    public void agregarCliente(Cliente c) {
        if (contador < clientes.length) {
            clientes[contador] = c;
            contador++;
        } else {
            System.out.println("Se alcanzó el límite de clientes");
        }
    }
    
    // Método que busca un cliente por su ID y devuelve su nombre
    public String buscarCliente(int id) {
        for (int i = 0; i < contador; i++) {
            if (clientes[i].getId() == id) {
                return clientes[i].getNombre();
            }
        }
        return "Cliente no existente";
    }
    
    // Método que busca un cliente y devuelve el objeto 
    // Busca por nombre (o parte de él) y muestra en pantalla las opciones con sus IDs
    public void buscarClientePorNombre(String nombreBuscar) {
        boolean encontrado = false;
        for (int i = 0; i < contador; i++) {
            // "contains" permite buscar coincidencias parciales (ej: "juan" encuentra "Juan Pérez")
            // "toLowerCase" asegura que funcione aunque escribas con mayúsculas o minúsculas
            if (clientes[i].getNombre().toLowerCase().contains(nombreBuscar.toLowerCase())) {
                System.out.println("ID: " + clientes[i].getId() + " | Nombre: " + clientes[i].getNombre());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron clientes que coincidan con ese nombre.");
        }
    }
    public Cliente buscarClientePorId(int id) {
        for (int i = 0; i < contador; i++) {
            if (clientes[i].getId() == id) {
                return clientes[i]; // Devuelve todo el objeto con todos sus datos
            }
        }
        return null; // Si no lo encuentra, avisa con un null
    }
    // Método que lista todos los clientes
    public void listarClientes() {
        for (int i = 0; i < contador; i++) {
            if (clientes[i] != null) {
                System.out.println(clientes[i].toString());
            }
        }
    }
    
    // Método que actualiza los datos de un cliente
    public void actualizarCliente(int idBuscar, int nuevoTelefono, String nuevoEmail) {
        boolean encontrado = false;
        for (int i = 0; i < contador; i++) {
            if (clientes[i].getId() == idBuscar) {
                clientes[i].setTelefono(nuevoTelefono);
                clientes[i].setEmail(nuevoEmail);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Error: Cliente con ID " + idBuscar + " no existe.");
        }
    }
    
    // Método que elimina un cliente por su ID
    public void eliminarCliente(int idBuscar) {
        boolean encontrado = false;
        for (int i = 0; i < contador; i++) {
            if (clientes[i] != null && clientes[i].getId() == idBuscar) {
                encontrado = true;
                for (int j = i; j < contador - 1; j++) {
                    clientes[j] = clientes[j + 1];
                }
                clientes[contador - 1] = null;
                contador--;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Error: Cliente con ID " + idBuscar + " no encontrado.");
        }
    }
}