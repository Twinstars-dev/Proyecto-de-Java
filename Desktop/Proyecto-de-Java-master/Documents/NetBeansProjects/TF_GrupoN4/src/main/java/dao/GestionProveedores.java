/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Proveedor;
import util.Validador;
import java.util.Scanner;

/**
 * Clase que gestiona las operaciones relacionadas con los proveedores
 * Implementa validaciones y manejo de excepciones usando Validador
 * @author Anderson
 */
public class GestionProveedores {
    
    // ===== CONSTANTES =====
    private static final int CAPACIDAD_MAXIMA = 50;
    
    // ===== ATRIBUTOS =====
    private Proveedor[] proveedores;
    private int contador;

    // ===== CONSTRUCTOR =====
    public GestionProveedores() {
        this.proveedores = new Proveedor[CAPACIDAD_MAXIMA];
        this.contador = 0;
    }

    // ===== MÉTODO PARA REGISTRAR UN PROVEEDOR =====
    /**
     * Agrega un nuevo proveedor al arreglo con validaciones
     * @param p Objeto Proveedor a registrar
     * @return true si se registró correctamente, false si hubo error
     */
    public boolean registrarProveedor(Proveedor p) {
    return registrarProveedor(p, false);
}

    public boolean registrarProveedor(Proveedor p, boolean silencioso) {
    // Validación: Espacio disponible
    if (!Validador.validarEspacioProveedores(contador)) {
        if (!silencioso) System.out.println("   Error: Capacidad maxima de proveedores alcanzada.");
        return false;
    }
    
    // Validación: Proveedor no nulo
    if (p == null) {
        if (!silencioso) System.out.println("   Error: No se puede registrar un proveedor nulo.");
        return false;
    }
    
    // ✅ VALIDACIÓN CON ITERACIÓN AUTOMÁTICA PARA ID REPETIDO
    int idOriginal = p.getId();
    int idFinal = idOriginal;
    boolean idValido = false;
    int intentos = 0;
    
    while (!idValido) {
        // Validar que el ID sea positivo
        if (!Validador.validarIdProveedor(idFinal)) {
            if (!silencioso) {
                System.out.println("   Error: El ID debe ser un numero positivo.");
            }
            return false;
        }
        
        // Verificar si el ID ya existe
        if (obtenerProveedor(idFinal, true) != null) {
            intentos++;
            if (!silencioso) {
                System.out.println("   Error: Ya existe un proveedor con el ID " + idFinal);
                if (intentos <= 3) {
                    System.out.println("   Sugerencia: Use ID " + (idFinal + 1) + " o " + (idFinal + 2));
                }
            }
            
            // GENERAR AUTOMÁTICAMENTE UN NUEVO ID
            idFinal = idOriginal + intentos;
            
            // Si ya son muchos intentos, preguntar al usuario
            if (intentos > 5) {
                if (!silencioso) {
                    System.out.println("   ¿Desea ingresar un ID manualmente? (S/N): ");
                    String respuesta = new Scanner(System.in).nextLine();
                    if (respuesta.equalsIgnoreCase("S")) {
                        idFinal = Validador.leerIdProveedor("   Ingrese nuevo ID: ");
                        intentos = 0; // Reiniciar contador
                    }
                } else {
                    return false; // En modo silencioso, falla
                }
            }
        } else {
            idValido = true;
        }
    }
    
    // Si el ID cambió, actualizar el objeto
    if (idFinal != idOriginal) {
        p.setId(idFinal);
        if (!silencioso) {
            System.out.println("   ID asignado automaticamente: " + idFinal);
        }
    }
    
    // Validar resto de campos
    if (!Validador.validarNombreProveedor(p.getNombre())) {
        if (!silencioso) System.out.println("   Error: El nombre del proveedor no es valido.");
        return false;
    }
    
    if (!Validador.validarTelefono(String.valueOf(p.getTelefono()))) {
        if (!silencioso) System.out.println("   Error: El telefono del proveedor no es valido.");
        return false;
    }
    
    if (!Validador.validarEmail(p.getEmail())) {
        if (!silencioso) System.out.println("   Error: El email del proveedor no es valido.");
        return false;
    }
    
    if (!Validador.validarTipoMedicamento(p.getTipodemedicamento())) {
        if (!silencioso) System.out.println("   Error: El tipo de medicamento no es valido.");
        return false;
    }
    
    // Registrar el proveedor
    proveedores[contador] = p;
    contador++;
    if (!silencioso) {
        System.out.println(" Proveedor registrado correctamente. Total: " + contador);
    }
    return true;
    }

    // ===== MÉTODO PARA OBTENER UN PROVEEDOR POR SU ID =====
    /**
     * Busca un proveedor por su ID
     * @param codigo ID del proveedor a buscar
     * @return El objeto Proveedor si lo encuentra, o null si no existe
     */
    public Proveedor obtenerProveedor(int codigo, boolean silencioso) { //Utilizamos un segundo parametro para evitar los mensajes generados por el metodo cargarDatosPrueba
    try {
        // Validación: ID positivo
        if (!Validador.validarIdProveedor(codigo)) {
            return null;
        }
        
        // Validación: Existen proveedores - USANDO VERSIÓN SILENCIOSA
        if (!Validador.validarExistenElementos(contador, "proveedores", silencioso)) {
            return null;
        }
        
        // Búsqueda del proveedor
        for (int i = 0; i < contador; i++) {
            if (proveedores[i] != null && proveedores[i].getId() == codigo) {
                return proveedores[i];
            }
        }
        
        if (!silencioso) {
            System.out.println("Error: No se encontro proveedor con ID " + codigo);
        }
        return null;
        
    } catch (NullPointerException e) {
        if (!silencioso) {
            System.out.println("Error: El arreglo de proveedores contiene datos nulos.");
        }
        return null;
    } catch (Exception e) {
        if (!silencioso) {
            System.out.println("Error inesperado al buscar proveedor: " + e.getMessage());
        }
        return null;
    } finally {
        if (!silencioso) {
            System.out.println("Busqueda finalizada.");
        }
    }
}

    // ===== MÉTODO PARA BUSCAR Y MOSTRAR UN PROVEEDOR =====
    /**
     * Busca un proveedor por su ID y muestra sus datos en consola
     * @param codigo ID del proveedor a buscar
     * @return true si se encontró, false si no
     */
    public boolean buscarProveedor(int codigo) {
    try {
        Proveedor proveedor = obtenerProveedor(codigo, false);  

        if (proveedor != null) {
            System.out.println("\n══════════════════════════════════════════════");
            System.out.println("         PROVEEDOR ENCONTRADO");
            System.out.println("══════════════════════════════════════════════");
            System.out.println("| ID:                    | " + proveedor.getId());
            System.out.println("| Nombre:                | " + proveedor.getNombre());
            System.out.println("| Telefono:              | " + proveedor.getTelefono());
            System.out.println("| Correo:                | " + proveedor.getEmail());
            System.out.println("| Tipo de medicamento:   | " + proveedor.getTipodemedicamento());
            System.out.println("══════════════════════════════════════════════");
            return true;
        }
        return false;
        
    } catch (Exception e) {
        System.out.println("Error inesperado: " + e.getMessage());
        return false;
    }
}

    // ===== MÉTODO PARA LISTAR TODOS LOS PROVEEDORES =====
    /**
     * Muestra en consola la lista completa de proveedores registrados
     * @return true si se listaron, false si no hay proveedores
     */
    public boolean listarProveedores() {
    try {
        if (!Validador.validarExistenElementos(contador, "proveedores", false)) {
            return false;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n═══════════════════════════════════════════════════════════════\n");
        sb.append("               LISTA DE PROVEEDORES (").append(contador).append(")\n");
        sb.append("═══════════════════════════════════════════════════════════════\n");
        sb.append("|  ID  |     NOMBRE     |  TELEFONO  |        EMAIL        |\n");
        sb.append("═══════════════════════════════════════════════════════════════\n");

        for (int i = 0; i < contador; i++) {
            if (proveedores[i] != null) {
                sb.append(String.format("| %-4d | %-14s | %-10d | %-19s |\n",
                    proveedores[i].getId(),
                    proveedores[i].getNombre().length() > 14 ? 
                        proveedores[i].getNombre().substring(0, 11) + "..." : 
                        proveedores[i].getNombre(),
                    proveedores[i].getTelefono(),
                    proveedores[i].getEmail().length() > 19 ? 
                        proveedores[i].getEmail().substring(0, 16) + "..." : 
                        proveedores[i].getEmail()
                ));
            }
        }
        sb.append("═══════════════════════════════════════════════════════════════\n");
        
        System.out.println(sb.toString());
        return true;
        
    } catch (NullPointerException e) {
        System.out.println(" Error: Hay datos nulos en el arreglo de proveedores.");
        return false;
    } catch (Exception e) {
        System.out.println(" Error inesperado al listar proveedores: " + e.getMessage());
        return false;
    } finally {
        System.out.println(" Operacion de listado finalizada.");
    }
}

    // ===== MÉTODO PARA ACTUALIZAR UN PROVEEDOR =====
    /**
     * Actualiza los datos de un proveedor buscándolo por su ID
     * @param idBuscar ID del proveedor a actualizar
     * @param nuevoNombre Nuevo nombre del proveedor
     * @param nuevoTelefono Nuevo número de teléfono
     * @param nuevoEmail Nuevo correo electrónico
     * @param nuevoTipo Nuevo tipo de medicamento
     * @return true si se actualizó, false si no se encontró
     */
    public boolean actualizarProveedor(int idBuscar, String nuevoNombre, int nuevoTelefono, 
                                        String nuevoEmail, String nuevoTipo) {
        try {
            // Validación: ID positivo
            if (!Validador.validarIdProveedor(idBuscar)) {
                return false;
            }
            
            // Validación: Existen proveedores
            if (!Validador.validarExistenElementos(contador, "proveedores")) {
                return false;
            }
            
            // Validar nuevos datos
            if (!Validador.validarNombreProveedor(nuevoNombre)) return false;
            if (!Validador.validarTelefono(String.valueOf(nuevoTelefono))) return false;
            if (!Validador.validarEmail(nuevoEmail)) return false;
            if (!Validador.validarTipoMedicamento(nuevoTipo)) return false;

            // Buscar y actualizar
            for (int i = 0; i < contador; i++) {
                if (proveedores[i] != null && proveedores[i].getId() == idBuscar) {
                    proveedores[i].setNombre(nuevoNombre);
                    proveedores[i].setTelefono(nuevoTelefono);
                    proveedores[i].setEmail(nuevoEmail);
                    proveedores[i].setTipodemedicamento(nuevoTipo);
                    System.out.println("Proveedor actualizado correctamente.");
                    return true;
                }
            }

            System.out.println("Error: No se encontro proveedor con ID " + idBuscar);
            return false;
            
        } catch (NullPointerException e) {
            System.out.println("Error: Datos del proveedor nulos.");
            return false;
        } catch (Exception e) {
            System.out.println("Error inesperado al actualizar proveedor: " + e.getMessage());
            return false;
        } finally {
            System.out.println(" Operacion de actualización finalizada.");
        }
    }

    // ===== MÉTODO PARA ELIMINAR UN PROVEEDOR =====
    /**
     * Elimina un proveedor del arreglo buscándolo por su ID
     * @param idBuscar ID del proveedor a eliminar
     * @return true si se eliminó, false si no se encontró
     */
    public boolean eliminarProveedor(int idBuscar) {
        try {
            // Validación: ID positivo
            if (!Validador.validarIdProveedor(idBuscar)) {
                return false;
            }
            
            // Validación: Existen proveedores
            if (!Validador.validarExistenElementos(contador, "proveedores")) {
                return false;
            }

            for (int i = 0; i < contador; i++) {
                if (proveedores[i] != null && proveedores[i].getId() == idBuscar) {
                    // Desplazar elementos a la izquierda
                    for (int j = i; j < contador - 1; j++) {
                        proveedores[j] = proveedores[j + 1];
                    }
                    proveedores[contador - 1] = null;
                    contador--;
                    System.out.println(" Proveedor eliminado correctamente.");
                    return true;
                }
            }

            System.out.println(" Error: No se encontro proveedor con ID " + idBuscar);
            return false;
            
        } catch (NullPointerException e) {
            System.out.println(" Error: Datos del proveedor nulos al eliminar.");
            return false;
        } catch (Exception e) {
            System.out.println(" Error inesperado al eliminar proveedor: " + e.getMessage());
            return false;
        } finally {
            System.out.println("Operacion de eliminación finalizada.");
        }
    }

    // ===== MÉTODO PARA OBTENER LA CANTIDAD DE PROVEEDORES =====
    public int cantidadDeProveedores() {
        return contador;
    }
    
    public boolean hayProveedores() {
        return contador > 0;
    }
}