package dao;

import java.time.LocalDate;
import model.Venta;
import util.Validador;

/**
 * Clase que gestiona las operaciones relacionadas con las ventas
 * @author GestionFarmacia
 */
public class GestionVentas {
    
    private static final int CAPACIDAD_MAXIMA = 100;
    private Venta[] ventas;
    private int contador;

    public GestionVentas() {
        this.ventas = new Venta[CAPACIDAD_MAXIMA];
        this.contador = 0;
    }

    // ===== MÉTODO PARA REGISTRAR UNA VENTA =====
    public boolean registrarVenta(Venta venta) {
    return registrarVenta(venta, false);
    }

    public boolean registrarVenta(Venta venta, boolean silencioso) {
    try {
        // Validación: Espacio disponible
        if (!Validador.validarEspacioDisponible(contador, CAPACIDAD_MAXIMA, 
                venta != null ? venta.getNombreDelMedicamento() : "venta")) {
            return false;
        }
        
        if (venta == null) {
            if (!silencioso) System.out.println("   Error: No se puede registrar una venta nula.");
            return false;
        }
        
        if (!Validador.validarNombreMedicamento(venta.getNombreDelMedicamento())) return false;
        if (!Validador.validarCantidad(venta.getCantidadDeProductos())) return false;
        if (!Validador.validarPrecio(venta.getPrecioDeVenta())) return false;
        if (!Validador.validarFechaVenta(venta.getFechaVendida())) return false;
        
        //  VALIDACIÓN CON ITERACIÓN AUTOMÁTICA PARA ID REPETIDO
        int id = venta.getId();
        int intentos = 0;
        
        // Verificar si el ID ya existe
        while (obtenerVenta(id, true) != null) {
            intentos++;
            if (!silencioso) {
                System.out.println("   Error: Ya existe una venta con el ID " + id);
            }
            
            // Generar nuevo ID automático
            id = venta.getId() + intentos;
            
            if (!silencioso) {
                System.out.println("   Asignando nuevo ID sugerido: " + id);
            }
        }
        
        // Si el ID cambió, actualizar el objeto
        if (id != venta.getId()) {
            venta.setId(id);
            if (!silencioso) {
                System.out.println("   ID asignado: " + id);
            }
        }
        
        // Registrar la venta
        ventas[contador] = venta;
        contador++;
        if (!silencioso) {
            System.out.println(" Venta registrada correctamente.");
        }
        return true;
        
    } catch (NullPointerException e) {
        if (!silencioso) {
            System.out.println("   Error critico: Datos de venta incompletos.");
        }
        return false;
    } catch (Exception e) {
        if (!silencioso) {
            System.out.println("   Error inesperado: " + e.getMessage());
        }
        return false;
    }
    }

    // ===== MÉTODO PARA OBTENER UNA VENTA =====
    public Venta obtenerVenta(int codigo, boolean silencioso) {
    try {
        if (!Validador.validarIdVenta(codigo)) return null;
        
        // Validación: Existen ventas - USANDO VERSIÓN SILENCIOSA
        if (!Validador.validarExistenElementos(contador, "ventas", silencioso)) {
            return null;
        }
        
        for (int i = 0; i < contador; i++) {
            if (ventas[i] != null && ventas[i].getId() == codigo) {
                return ventas[i];
            }
        }
        
        if (!silencioso) {
            System.out.println(" Error: No se encontro venta con ID " + codigo);
        }
        return null;
        
    } catch (Exception e) {
        if (!silencioso) {
            System.out.println(" Error inesperado: " + e.getMessage());
        }
        return null;
    } finally {
        if (!silencioso) {
            System.out.println(" Busqueda finalizada.");
        }
    }
    }
    // ===== MÉTODO PARA BUSCAR Y MOSTRAR UNA VENTA =====
    public boolean buscarVenta(int codigo) {
    Venta venta = obtenerVenta(codigo, false);  // ← usa la versión con parámetro
    if (venta != null) {
        System.out.println("\n======================= VENTA ENCONTRADA =======================");
        System.out.println("| ID:                    | " + venta.getId());
        System.out.println("| Fecha:                 | " + venta.getFechaVendida());
        System.out.println("| Medicamento:           | " + venta.getNombreDelMedicamento());
        System.out.println("| Cantidad:              | " + venta.getCantidadDeProductos());
        System.out.println("| Precio unitario:       | S/. " + String.format("%.2f", venta.getPrecioDeVenta()));
        System.out.println("| Subtotal:              | S/. " + String.format("%.2f", venta.getPrecioDeVenta() * venta.getCantidadDeProductos()));
        System.out.println("==================================================================");
        return true;
    }
    return false;
}

    // ===== MÉTODO PARA LISTAR TODAS LAS VENTAS =====
    public boolean listarVentas() {
    try {
        // USANDO VERSIÓN NO SILENCIOSA (el usuario quiere ver el error)
        if (!Validador.validarExistenElementos(contador, "ventas", false)) {
            return false;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n=====================================================\n");
        sb.append("                    LISTA DE VENTAS (").append(contador).append(")\n");
        sb.append("=====================================================\n");
        sb.append("|  ID  |    FECHA    |     MEDICAMENTO     | CANT |  TOTAL  |\n");
        sb.append("=====================================================\n");
        
        for (int i = 0; i < contador; i++) {
            if (ventas[i] != null) {
                sb.append(String.format("| %-4d | %-10s | %-19s | %4d | S/.%6.2f |\n",
                    ventas[i].getId(),
                    ventas[i].getFechaVendida().toString(),
                    ventas[i].getNombreDelMedicamento().length() > 19 ? 
                        ventas[i].getNombreDelMedicamento().substring(0, 16) + "..." : 
                        ventas[i].getNombreDelMedicamento(),
                    ventas[i].getCantidadDeProductos(),
                    ventas[i].getPrecioDeVenta() * ventas[i].getCantidadDeProductos()
                ));
            }
        }
        sb.append("========================================================\n");
        System.out.println(sb.toString());
        return true;
        
    } catch (Exception e) {
        System.out.println(" Error inesperado al listar ventas: " + e.getMessage());
        return false;
    }
}

    // ===== MÉTODO PARA CALCULAR IGV =====
    public boolean calcularImpuesto() {
    try {
        if (!Validador.validarExistenElementos(contador, "ventas", false)) {
            return false;
        }
        
        double subtotal = 0;
        for (int i = 0; i < contador; i++) {
            if (ventas[i] != null) {
                subtotal += ventas[i].getPrecioDeVenta() * ventas[i].getCantidadDeProductos();
            }
        }
        
        double igv = subtotal * 0.18;
        double total = subtotal + igv;
        
        System.out.println("\n=======================================");
        System.out.println("         RESUMEN DE IMPUESTOS");
        System.out.println("========================================");
        System.out.println("| Subtotal (sin IGV):    | S/. " + String.format("%10.2f", subtotal));
        System.out.println("| IGV (18%):              | S/. " + String.format("%10.2f", igv));
        System.out.println("| TOTAL (con IGV):        | S/. " + String.format("%10.2f", total));
        System.out.println("========================================");
        return true;
        
    } catch (Exception e) {
        System.out.println(" Error inesperado: " + e.getMessage());
        return false;
    }
}

    // ===== MÉTODO PARA FECHA CON MAYOR INGRESO =====
    public boolean fechaMayorIngreso() {
    try {
        if (!Validador.validarExistenElementos(contador, "ventas", false)) {
            return false;
        }
        
        LocalDate fechaMayor = ventas[0].getFechaVendida();
        double mayorIngreso = 0;
        
        for (int i = 0; i < contador; i++) {
            double suma = 0;
            for (int j = 0; j < contador; j++) {
                if (ventas[i] != null && ventas[j] != null &&
                    ventas[i].getFechaVendida().equals(ventas[j].getFechaVendida())) {
                    suma += ventas[j].getPrecioDeVenta() * ventas[j].getCantidadDeProductos();
                }
            }
            if (suma > mayorIngreso) {
                mayorIngreso = suma;
                fechaMayor = ventas[i].getFechaVendida();
            }
        }
        
        System.out.println("\n=======================================");
        System.out.println("         FECHA CON MAYOR INGRESO");
        System.out.println("========================================");
        System.out.println("| Fecha:                | " + fechaMayor);
        System.out.println("| Ingreso total:        | S/. " + String.format("%10.2f", mayorIngreso));
        System.out.println("========================================");
        return true;
        
    } catch (Exception e) {
        System.out.println(" Error inesperado: " + e.getMessage());
        return false;
    }
}

    // ===== MÉTODO PARA ELIMINAR UNA VENTA =====
    public boolean eliminarVenta(int id) {
    try {
        if (!Validador.validarIdVenta(id)) {
            return false;
        }
        if (!Validador.validarExistenElementos(contador, "ventas", false)) {
            return false;
        }
        
        for (int i = 0; i < contador; i++) {
            if (ventas[i] != null && ventas[i].getId() == id) {
                for (int j = i; j < contador - 1; j++) {
                    ventas[j] = ventas[j + 1];
                }
                ventas[contador - 1] = null;
                contador--;
                System.out.println(" Venta con ID " + id + " eliminada correctamente.");
                return true;
            }
        }
        
        System.out.println(" Error: No se encontró venta con ID " + id);
        return false;
        
    } catch (Exception e) {
        System.out.println(" Error inesperado: " + e.getMessage());
        return false;
    }
}

    public int getTotalVentas() {
        return contador;
    }

    public boolean hayVentas() {
        return contador > 0;
    }
}