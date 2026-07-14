package dao;

import model.Medicamento;
import java.time.LocalDate;

/**
 * Clase que gestiona el inventario de medicamentos
 * @author Anderson
 */
//Clase que gestiona el inventario de medicamentos
public class GestionInventario {
    
    // ===== ATRIBUTOS =====
    private Medicamento[] medicamentos;  // Arreglo para almacenar medicamentos
    private int contador;                // Controla cuántos medicamentos hay registrados

    // ===== CONSTRUCTOR =====
    // Inicializa el arreglo con capacidad para 100 medicamentos
    public GestionInventario() {
        medicamentos = new Medicamento[100];
        contador = 0;
    }

    // ===== MÉTODO PARA AGREGAR UN MEDICAMENTO =====
    /**
     * Agrega un nuevo medicamento al inventario
     * @param m Objeto Medicamento a registrar
     */
    public void agregarMedicamento(Medicamento m) {
    agregarMedicamento(m, false);
}

    public void agregarMedicamento(Medicamento m, boolean silencioso) {
    // Validación: Espacio disponible
    if (contador >= medicamentos.length) {
        if (!silencioso) {
            System.out.println("   Error: Inventario de medicamentos lleno.");
        }
        return;
    }
    
    if (m == null) {
        if (!silencioso) {
            System.out.println("   Error: No se puede registrar un medicamento nulo.");
        }
        return;
    }
    
    //  VALIDACIÓN CON ITERACIÓN AUTOMÁTICA PARA CÓDIGO REPETIDO
    int codigo = m.getCodigo();
    int intentos = 0;
    
    // Verificar si el código ya existe
    while (!buscarMedicamentoPorCodigo(codigo).equals("Medicamento no encontrado")) {
        intentos++;
        if (!silencioso) {
            System.out.println("   Error: Ya existe un medicamento con el codigo " + codigo);
        }
        
        // Generar nuevo código automático
        codigo = m.getCodigo() + intentos;
        
        // Validar que el nuevo código tenga 6 dígitos
        if (String.valueOf(codigo).length() != 6) {
            if (!silencioso) {
                System.out.println("   Error: No se pudo generar un codigo de 6 digitos.");
                System.out.println("   Intente con otro codigo manualmente.");
            }
            return;
        }
        
        if (!silencioso) {
            System.out.println("   Asignando nuevo codigo sugerido: " + codigo);
        }
    }
    
    // Si el código cambió, actualizar el objeto
    if (codigo != m.getCodigo()) {
        m.setCodigo(codigo);
        if (!silencioso) {
            System.out.println("   Codigo asignado: " + codigo);
        }
    }
    
    // Registrar el medicamento
    medicamentos[contador] = m;
    contador++;
    if (!silencioso) {
        System.out.println("Medicamento registrado correctamente.");
    }
    }

    // ===== MÉTODO PARA BUSCAR UN MEDICAMENTO POR CÓDIGO =====
    /**
     * Busca un medicamento por su código y devuelve su nombre
     * @param codigo Código del medicamento a buscar
     * @return El nombre del medicamento o un mensaje de error
     */
    public String buscarMedicamentoPorCodigo(int codigo) {
        // Recorre todos los medicamentos registrados
        for (int i = 0; i < contador; i++) {
            // Verifica si el medicamento existe y si su código coincide
            if (medicamentos[i] != null && medicamentos[i].getCodigo()==codigo) {
                return medicamentos[i].getNombre(); //  Lo encontró, retorna el nombre
            }
        }
        return "Medicamento no encontrado"; //  No se encontró coincidencia
    }

    // ===== MÉTODO PARA LISTAR TODOS LOS MEDICAMENTOS =====
    /**
     * Muestra en consola la lista completa de medicamentos registrados
     * Usa StringBuilder para construir el texto de manera eficiente
     */
    public void listarMedicamentos() {
        // Verifica si hay medicamentos registrados
        if (contador == 0) {
            System.out.println("No existen medicamentos registrados.");
            return;
        }

        // Construye el listado usando StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("\n======================================== LISTA DE MEDICAMENTOS ========================================\n");
        
        // Recorre todos los medicamentos y agrega sus datos
        for (int i = 0; i < contador; i++) {
            sb.append("Codigo: ").append(medicamentos[i].getCodigo()).append("\n");
            sb.append("Nombre: ").append(medicamentos[i].getNombre()).append("\n");
            sb.append("Laboratorio: ").append(medicamentos[i].getLaboratorio()).append("\n");
            sb.append("Stock: ").append(medicamentos[i].getStock()).append("\n");
            sb.append("Precio: S/. ").append(medicamentos[i].getPrecio()).append("\n");
            sb.append("Vencimiento: ").append(medicamentos[i].getFechaDeVencimiento()).append("\n");
            sb.append("Categoria: ").append(medicamentos[i].getCategoria()).append("\n");
            sb.append("Receta: ").append(medicamentos[i].isRequiereReceta()).append("\n");
            sb.append("===================================\n");
        }
        
        // Muestra todo el texto construido
        System.out.println(sb.toString());
    }

    // ===== MÉTODO PARA ACTUALIZAR UN MEDICAMENTO =====
    /**
     * Actualiza el precio y stock de un medicamento buscándolo por su nombre
     * @param nombreBuscar Nombre del medicamento a actualizar
     * @param nuevoPrecio Nuevo precio del medicamento
     * @param nuevoStock Nueva cantidad en stock
     */
    public void actualizarMedicamento(String nombreBuscar, double nuevoPrecio, int nuevoStock) {
        boolean encontrado = false;
        
        // Recorre todos los medicamentos buscando coincidencia por nombre
        for (int i = 0; i < contador; i++) {
            if (medicamentos[i] != null && medicamentos[i].getNombre().equalsIgnoreCase(nombreBuscar)) {
                // Actualiza los datos del medicamento encontrado
                medicamentos[i].setPrecio(nuevoPrecio);
                medicamentos[i].setStock(nuevoStock);
                encontrado = true;
                System.out.println("Medicamento actualizado.");
                break;  // Sale del bucle
            }
        }
        
        // Si no se encontró, muestra mensaje de error
        if (!encontrado) {
            System.out.println("Medicamento no encontrado.");
        }
    }

    // ===== MÉTODO PARA ELIMINAR UN MEDICAMENTO =====
    /**
     * Elimina un medicamento del inventario buscándolo por su código
     * @param codigoBuscar Código del medicamento a eliminar
     */
    public void eliminarMedicamento(int codigoBuscar) {
        boolean encontrado = false;
        
        // Recorre todos los medicamentos buscando el código
        for (int i = 0; i < contador; i++) {
            if (medicamentos[i] != null && medicamentos[i].getCodigo()==codigoBuscar) {
                encontrado = true;
                
                // Desplaza los elementos a la izquierda para rellenar el hueco
                // Ej: [A, B, C, D] → eliminar B → [A, C, D, null]
                for (int j = i; j < contador - 1; j++) {
                    medicamentos[j] = medicamentos[j + 1];
                }
                
                // El último espacio queda null y se reduce el contador
                medicamentos[contador - 1] = null;
                contador--;
                System.out.println("Medicamento eliminado.");
                break;  // Sale del bucle
            }
        }
        
        // Si no se encontró, muestra mensaje de error
        if (!encontrado) {
            System.out.println("Codigo no encontrado.");
        }
    }

    // ===== MÉTODO PARA REALIZAR UNA VENTA =====
    /**
     * Realiza la venta de un medicamento, descontando del stock
     * @param nombreBuscar Nombre del medicamento a vender
     * @param cantidadVendida Cantidad de unidades a vender
     * @return true si la venta fue exitosa, false si hubo error
     */
    public boolean ventaMedicamento(String nombreBuscar, int cantidadVendida) {
        // Recorre todos los medicamentos buscando por nombre
        for (int i = 0; i < contador; i++) {
            if (medicamentos[i] != null && medicamentos[i].getNombre().equalsIgnoreCase(nombreBuscar)) {
                
                // Verifica si hay stock suficiente
                if (cantidadVendida <= medicamentos[i].getStock()) {
                    // ✅ Hay stock: descuenta la cantidad vendida
                    medicamentos[i].setStock(medicamentos[i].getStock() - cantidadVendida);
                    System.out.println("Stock actualizado en el inventario.");
                    return true;  // ÉXITO
                } else {
                    // Stock insuficiente
                    System.out.println("Error: Stock insuficiente.");
                    return false;  // FALLO
                }
            }
        }
        // Medicamento no encontrado
        System.out.println("Error: Medicamento no encontrado.");
        return false;  // FALLO
    }

    // ===== MÉTODO PARA OBTENER REPORTE DE STOCK BAJO =====
    /**
     * Obtiene todos los medicamentos con stock por debajo del límite
     * @param limiteAlerta Límite de stock para considerar "bajo"
     * @return Arreglo con los medicamentos que tienen stock bajo
     */
    public Medicamento[] obtenerReporteStockBajo(int limiteAlerta) {
        // Arreglo temporal para almacenar los medicamentos con stock bajo
        Medicamento[] temporal = new Medicamento[contador];
        int cantidad = 0;
        
        // Recorre todos los medicamentos filtrando por stock
        for (int i = 0; i < contador; i++) {
            if (medicamentos[i] != null && medicamentos[i].getStock() < limiteAlerta) {
                temporal[cantidad] = medicamentos[i];
                cantidad++;
            }
        }
        
        // Crea el arreglo final con el tamaño exacto
        Medicamento[] resultado = new Medicamento[cantidad];
        for (int i = 0; i < cantidad; i++) {
            resultado[i] = temporal[i];
        }
        return resultado;
    }

    // ===== MÉTODO PARA MOSTRAR MEDICAMENTOS POR VENCER =====
    /**
     * Muestra los medicamentos vencidos o próximos a vencer (menos de 1 mes)
     * Compara la fecha actual con la fecha de vencimiento de cada medicamento
     */
    public void mostrarMedicamentosPorVencer() {
        // Obtiene fechas de referencia
        LocalDate hoy = LocalDate.now();                   // Fecha actual
        LocalDate unMesDespues = hoy.plusMonths(1);        // Fecha dentro de 1 mes
        boolean existe = false;
        
        // Recorre todos los medicamentos
        for (int i = 0; i < contador; i++) {
            if (medicamentos[i] != null) {
                // Caso 1: Medicamento ya vencido
                if (medicamentos[i].getFechaDeVencimiento().isBefore(hoy)) {
                    System.out.println("MEDICAMENTO VENCIDO: " + medicamentos[i].getNombre());
                    existe = true;
                } 
                // Caso 2: Medicamento próximo a vencer (menos de 1 mes)
                else if (medicamentos[i].getFechaDeVencimiento().isBefore(unMesDespues)) {
                    System.out.println("MEDICAMENTO PROXIMO A VENCER: " + medicamentos[i].getNombre());
                    existe = true;
                }
            }
        }
        
        // Si no se encontró ningún medicamento en estado crítico
        if (!existe) {
            System.out.println("No existen medicamentos próximos a vencer.");
        }
    }
}
