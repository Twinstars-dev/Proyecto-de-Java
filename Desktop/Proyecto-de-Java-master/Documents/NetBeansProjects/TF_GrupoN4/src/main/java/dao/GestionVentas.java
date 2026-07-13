/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.time.LocalDate;
import model.Venta;
import model.Cliente;

/**
 * Clase que gestiona las operaciones relacionadas con las ventas
 * @author GestionFarmacia
 */
public class GestionVentas {
    
    // ===== ATRIBUTOS =====
    private Venta[] ventas;  // Arreglo para almacenar las ventas
    private int contador;    // Controla cuántas ventas hay registradas

    // ===== CONSTRUCTOR =====
    // Inicializa el arreglo con capacidad para 100 ventas
    public GestionVentas() {
        this.ventas = new Venta[100];
        this.contador = 0;
    }

    // ===== MÉTODO PARA REGISTRAR UNA VENTA =====
    /**
     * Agrega una nueva venta al arreglo
     * @param venta Objeto Venta a registrar
     */
    public void registrarVenta(Venta venta) {
        // Verifica si hay espacio disponible
        if (contador < ventas.length) {
            ventas[contador] = venta;  // Guarda la venta en la posición actual
            contador++;                 // Incrementa el contador
            System.out.println("Venta registrada correctamente.");
        } else {
            System.out.println("No hay espacio para registrar más ventas.");
        }
    }

    // ===== MÉTODO PARA OBTENER UNA VENTA POR SU ID =====
    /**
     * Busca una venta por su código (ID)
     * @param codigo ID de la venta a buscar
     * @return El objeto Venta si lo encuentra, o null si no existe
     */
    public Venta obtenerVenta(int codigo) {
        // Recorre todas las ventas registradas
        for (int i = 0; i < contador; i++) {
            // Verifica si la venta existe y si su ID coincide con el buscado
            if (ventas[i] != null && ventas[i].getId() == codigo) {
                return ventas[i];  // Retorna la venta encontrada
            }
        }
        return null;  // Retorna null si no se encontró
    }

    // ===== MÉTODO PARA BUSCAR Y MOSTRAR UNA VENTA =====
    /**
     * Busca una venta por su ID y muestra sus datos en consola
     * @param codigo ID de la venta a buscar
     */
    public void buscarVenta(int codigo) {
        // Obtiene la venta usando el método auxiliar
        Venta venta = obtenerVenta(codigo);
        
        // Si la venta existe, muestra sus datos en formato de tabla
        if (venta != null) {
            System.out.println("======================= VENTA ========================");
            System.out.println("= Código: " + venta.getId() + "                        =");
            System.out.println("= Fecha: " + venta.getFechaVendida() + "               =");
            System.out.println("= Medicamento: " + venta.getNombreDelMedicamento() + " =");
            System.out.println("= Cantidad: " + venta.getCantidadDeProductos() + "     =");
            System.out.println("= Total: S/. " + venta.getPrecioDeVenta() + "          =");
            System.out.println("======================================================");
        } else {
            System.out.println("Venta no encontrada.");
        }
    }

    // ===== MÉTODO PARA LISTAR TODAS LAS VENTAS =====
    /**
     * Muestra en consola la lista completa de ventas registradas
     * Usa StringBuilder para construir el texto de manera eficiente
     */
    public void listarVentas() {
        // Verifica si hay ventas registradas
        if (contador == 0) {
            System.out.println("No existen ventas registradas.");
            return;
        }
        
        // Construye el reporte usando StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < contador; i++) {
            // Agrega los datos de cada venta al StringBuilder
            sb.append("\n==============================LISTA DE VENTAS==========================\n");
            sb.append("= Codigo:         =").append(ventas[i].getId()).append("\n");
            sb.append("= Fecha:          =").append(ventas[i].getFechaVendida()).append("\n");
            sb.append("= Medicamento:    =").append(ventas[i].getNombreDelMedicamento()).append("\n");
            sb.append("= Cantidad:       =").append(ventas[i].getCantidadDeProductos()).append("\n");
            sb.append("= Total: S/.      =").append(ventas[i].getPrecioDeVenta()).append("\n");
            sb.append("\n========================================================================");
        }
        // Muestra todo el texto construido
        System.out.println(sb.toString());
    }

    // ===== MÉTODO PARA CALCULAR EL IGV DE LAS VENTAS =====
    /**
     * Calcula y muestra el subtotal, IGV (18%) y total de las ventas
     * @return String vacío (solo muestra en consola)
     */
    public String calcularImpuesto() {
        double subtotal = 0;
        double igv = 0.0;
        double total = 0;
        
        // Recorre todas las ventas y suma los precios
        for (int i = 0; i < contador; i++) {
            if (ventas[i] != null) {
                subtotal = ventas[i].getPrecioDeVenta() * ventas[i].getCantidadDeProductos();
                igv = subtotal * 0.18;  // 18% de IGV
                total = subtotal + igv;
            } else {
                return "";  // Si no hay ventas, retorna vacío
            }
        }
        
        // Muestra los resultados en consola
        System.out.println("==============================");
        System.out.println("El subtotal es : " + subtotal);
        System.out.println("El IGV es : " + igv);
        System.out.println("El monto total sería: " + total);
        System.out.println("==============================");
        return "";
    }

    // ===== MÉTODO PARA ENCONTRAR LA FECHA CON MAYOR INGRESO =====
    /**
     * Encuentra y muestra la fecha en la que se generó el mayor ingreso
     * Compara todas las fechas y suma los precios de cada una
     */
    public void fechaMayorIngreso() {
        // Verifica si hay ventas registradas
        if (contador == 0) {
            System.out.println("No existen ventas registradas.");
            return;
        }
        
        // Inicializa con la primera venta
        LocalDate fechaMayor = ventas[0].getFechaVendida();
        double mayorIngreso = 0;
        
        // Recorre todas las ventas para comparar fechas
        for (int i = 0; i < contador; i++) {
            double suma = 0;
            // Suma todos los precios que tengan la misma fecha
            for (int j = 0; j < contador; j++) {
                if (ventas[i].getFechaVendida().equals(ventas[j].getFechaVendida())) {
                    suma += ventas[j].getPrecioDeVenta();
                }
            }
            // Si el ingreso de esta fecha es mayor, se actualiza
            if (suma > mayorIngreso) {
                mayorIngreso = suma;
                fechaMayor = ventas[i].getFechaVendida();
            }
        }
        
        // Muestra el resultado
        System.out.println("Fecha con mayor ingreso: " + fechaMayor);
        System.out.println("Ingreso total: S/. " + mayorIngreso);
    }
}

