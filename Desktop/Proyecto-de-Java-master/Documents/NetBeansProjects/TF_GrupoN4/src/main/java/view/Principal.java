package view;

import dao.GestionClientes;
import dao.GestionInventario;
import dao.GestionProveedores;
import dao.GestionVentas;
import java.util.Scanner;
import model.Cliente;
import model.Medicamento;
import model.Proveedor;
import model.Venta;
import java.time.LocalDate;
import util.Validador;

/**
 * Clase principal del sistema de gestión de farmacia
 * SOLO CONTIENE INTERFAZ DE USUARIO (menús y presentación)
 * @author Grupo33
 */
public class Principal {
    
    // ===== CONSTANTES DE DISEÑO =====
    private static final String LINEA = "=====================================================";
    private static final String LINEA_SIMPLE = "-----------------------------------------------------";
    
    // ===== SCANNER GLOBAL =====
    private static Scanner sc = new Scanner(System.in);
    
    /**
     * Método principal - Punto de entrada del sistema
     */
    public static void main(String[] args) {
        GestionInventario inventario = new GestionInventario();
        GestionClientes clientes = new GestionClientes();
        GestionProveedores proveedores = new GestionProveedores();
        GestionVentas ventas = new GestionVentas();
        
        cargarDatosPrueba(inventario, clientes, proveedores, ventas);
        menuPrincipal(inventario, clientes, proveedores, ventas);
    }
    
    // ==================== MÉTODOS DE DECORACIÓN ====================
    
    private static void mostrarTitulo(String titulo) {
        System.out.println("\n" + LINEA);
        System.out.println("        " + titulo);
        System.out.println(LINEA);
    }
    
    private static void mostrarSubtitulo(String subtitulo) {
        System.out.println("\n" + LINEA_SIMPLE);
        System.out.println("     " + subtitulo);
        System.out.println(LINEA_SIMPLE);
    }
    
    // ==================== CARGA DE DATOS DE PRUEBA ====================
    
    private static void cargarDatosPrueba(GestionInventario inventario, GestionClientes clientes, 
                                       GestionProveedores proveedores, GestionVentas ventas) {
    try {
        // === MEDICAMENTOS (true = modo silencioso) ===
        inventario.agregarMedicamento(new Medicamento("101", "Paracetamol", "Medifarma", 10, 5.0, 
                LocalDate.of(2027, 5, 20), "Analgesico", false), true);
        inventario.agregarMedicamento(new Medicamento("102", "Ibuprofeno", "Medifarma", 5, 4.0, 
                LocalDate.of(2027, 8, 15), "Antiinflamatorio", false), true);
        inventario.agregarMedicamento(new Medicamento("103", "Amoxicilina", "Roxfarma", 15, 9.0, 
                LocalDate.of(2030, 4, 21), "Antibiotico", false), true);
        inventario.agregarMedicamento(new Medicamento("104", "Loratadina", "Roxfarma", 10, 10.0, 
                LocalDate.of(2030, 2, 11), "Antihistaminico", true), true);
        inventario.agregarMedicamento(new Medicamento("105", "Omeprazol", "Laboratorios Unidos", 15, 6.0, 
                LocalDate.of(2028, 7, 25), "Inhibidor", true), true);
        inventario.agregarMedicamento(new Medicamento("106", "Naproxeno", "Laboratorios Unidos", 2, 10.0, 
                LocalDate.of(2026, 7, 18), "Analgesico", true), true);
        
        // === CLIENTES (true = modo silencioso) ===
        clientes.agregarCliente(new Cliente(1, "Juan Pérez", false, 987654321, "juan@gmail.com"), true);
        clientes.agregarCliente(new Cliente(2, "María López", true, 912345678, "maria@gmail.com"), true);
        clientes.agregarCliente(new Cliente(3, "Carlos Torres", false, 998877665, "carlos@gmail.com"), true);
        
        // === PROVEEDORES (true = modo silencioso) ===
        proveedores.registrarProveedor(new Proveedor(1, "Laboratorio Bayer", 987111222, "bayer@gmail.com", "Analgésicos"), true);
        proveedores.registrarProveedor(new Proveedor(2, "Medifarma", 999333444, "medifarma@gmail.com", "Antibióticos"), true);
        proveedores.registrarProveedor(new Proveedor(3, "Inkafarma Distribución", 988555666, "inkafarma@gmail.com", "Vitaminas"), true);
        
        // === VENTAS (true = modo silencioso) ===
        Cliente c1 = new Cliente(); c1.setNombre("Mario Vargas");
        Cliente c2 = new Cliente(); c2.setNombre("Abraham Valdelomar");
        Cliente c3 = new Cliente(); c3.setNombre("Cesar Vallejo");
        
        ventas.registrarVenta(new Venta(1, LocalDate.of(2026, 7, 11), "Panadol", 3, 4.5, c1), true);
        ventas.registrarVenta(new Venta(2, LocalDate.of(2026, 7, 10), "Omeprazol", 4, 5.0, c2), true);
        ventas.registrarVenta(new Venta(3, LocalDate.of(2026, 7, 9), "Panadol", 5, 6.0, c3), true);
        
        System.out.println(" Datos de prueba cargados correctamente.");
        
    } catch (Exception e) {
        System.out.println(" Error al cargar datos: " + e.getMessage());
        }
    }
    
    // ==================== MENÚ PRINCIPAL ====================
    
    private static void menuPrincipal(GestionInventario inventario, GestionClientes clientes,
                                       GestionProveedores proveedores, GestionVentas ventas) {
        int opcion;
        
        do {
            mostrarTitulo(" SISTEMA DE GESTIÓN DE FARMACIA");
            System.out.println("  1.  Gestion de Medicamentos");
            System.out.println("  2.  Gestion de Clientes");
            System.out.println("  3.  Gestion de Proveedores");
            System.out.println("  4.  Gestion de Ventas");
            System.out.println("  5.  Reportes");
            System.out.println("  0.  Salir");
            System.out.println(LINEA_SIMPLE);
            
            opcion = Validador.leerOpcion("  Seleccione una opcion: ", 0, 5);
            
            switch (opcion) {
                case 1 -> menuMedicamentos(inventario);
                case 2 -> menuClientes(clientes);
                case 3 -> menuProveedores(proveedores);
                case 4 -> menuVentas(ventas, inventario);
                case 5 -> menuReportes(inventario, ventas);
                case 0 -> System.out.println("\n  ¡Gracias por utilizar el sistema! ");
            }
            
        } while (opcion != 0);
    }
    
    // ==================== MENÚ MEDICAMENTOS ====================
    
    private static void menuMedicamentos(GestionInventario inventario) {
        int opcion;
        
        do {
            mostrarSubtitulo(" GESTIÓN DE MEDICAMENTOS");
            System.out.println("  1. Registrar medicamento");
            System.out.println("  2. Buscar medicamento");
            System.out.println("  3. Modificar medicamento");
            System.out.println("  4. Eliminar medicamento");
            System.out.println("  5. Listar medicamentos");
            System.out.println("  6. Realizar venta");
            System.out.println("  0. Regresar");
            System.out.println(LINEA_SIMPLE);
            
            opcion = Validador.leerOpcion("  Opcion: ", 0, 6);
            
            switch (opcion) {
                case 1 -> registrarMedicamento(inventario);
                case 2 -> buscarMedicamento(inventario);
                case 3 -> modificarMedicamento(inventario);
                case 4 -> eliminarMedicamento(inventario);
                case 5 -> inventario.listarMedicamentos();
                case 6 -> venderMedicamento(inventario);
                case 0 -> System.out.println("   Regresando...");
            }
            
        } while (opcion != 0);
    }
    
    // ==================== OPERACIONES DE MEDICAMENTOS ====================
    
    private static void registrarMedicamento(GestionInventario inventario) {
        mostrarSubtitulo(" REGISTRAR MEDICAMENTO");
        
        String codigo = Validador.leerCodigoMedicamento("  Codigo: ");
        String nombre = Validador.leerTexto("  Nombre: ");
        String laboratorio = Validador.leerTexto("  Laboratorio: ");
        int stock = Validador.leerEnteroPositivo("  Stock: ");
        double precio = Validador.leerDoublePositivo("  Precio: ");
        LocalDate fechaVencimiento = Validador.leerFechaVencimiento("  Fecha de vencimiento (yyyy-MM-dd): ");
        String categoria = Validador.leerTexto("  Categoría: ");
        boolean requiereReceta = Validador.leerConfirmacion("  ¿Requiere receta médica? (S/N): ");
        
        Medicamento m = new Medicamento(codigo, nombre, laboratorio, stock, precio, 
                                        fechaVencimiento, categoria, requiereReceta);
        inventario.agregarMedicamento(m);
    }
    
    private static void buscarMedicamento(GestionInventario inventario) {
        mostrarSubtitulo(" BUSCAR MEDICAMENTO");
        String codigo = Validador.leerCodigoMedicamento("  Ingrese el código: ");
        
        String resultado = inventario.buscarMedicamentoPorCodigo(codigo);
        System.out.println("   Resultado: " + resultado);
    }
    
    private static void modificarMedicamento(GestionInventario inventario) {
        mostrarSubtitulo(" MODIFICAR MEDICAMENTO");
        String nombre = Validador.leerTexto("  Nombre del medicamento: ");
        double precio = Validador.leerDoublePositivo("  Nuevo precio: ");
        int stock = Validador.leerEnteroPositivo("  Nuevo stock: ");
        
        inventario.actualizarMedicamento(nombre, precio, stock);
    }
    
    private static void eliminarMedicamento(GestionInventario inventario) {
        mostrarSubtitulo(" ELIMINAR MEDICAMENTO");
        String codigo = Validador.leerCodigoMedicamento("  Código del medicamento: ");
        
        if (Validador.leerConfirmacion("  ¿Está seguro de eliminar este medicamento? (S/N): ")) {
            inventario.eliminarMedicamento(codigo);
        } else {
            System.out.println("   Operación cancelada.");
        }
    }
    
    private static void venderMedicamento(GestionInventario inventario) {
        mostrarSubtitulo(" VENDER MEDICAMENTO");
        String nombre = Validador.leerTexto("  Nombre del medicamento: ");
        int cantidad = Validador.leerEnteroPositivo("  Cantidad a vender: ");
        
        boolean exito = inventario.ventaMedicamento(nombre, cantidad);
        if (exito) {
            System.out.println("   Venta realizada correctamente.");
        } else {
            System.out.println("   No se pudo realizar la venta.");
        }
    }
    
    // ==================== MENÚ CLIENTES ====================
    
    private static void menuClientes(GestionClientes clientes) {
        int opcion;
        
        do {
            mostrarSubtitulo(" GESTIÓN DE CLIENTES");
            System.out.println("  1. Registrar cliente");
            System.out.println("  2. Buscar cliente por ID");
            System.out.println("  3. Buscar cliente por nombre");
            System.out.println("  4. Modificar cliente");
            System.out.println("  5. Eliminar cliente");
            System.out.println("  6. Listar clientes");
            System.out.println("  0. Regresar");
            System.out.println(LINEA_SIMPLE);
            
            opcion = Validador.leerOpcion("  Opción: ", 0, 6);
            
            switch (opcion) {
                case 1 -> registrarCliente(clientes);
                case 2 -> buscarClientePorId(clientes);
                case 3 -> buscarClientePorNombre(clientes);
                case 4 -> modificarCliente(clientes);
                case 5 -> eliminarCliente(clientes);
                case 6 -> clientes.listarClientes();
                case 0 -> System.out.println("   Regresando...");
            }
            
        } while (opcion != 0);
    }
    
    // ==================== OPERACIONES DE CLIENTES ====================
    
    private static void registrarCliente(GestionClientes clientes) {
        mostrarSubtitulo(" REGISTRAR CLIENTE");
        
        int id = Validador.leerIdCliente("  ID del cliente: ");
        
        // Verificar ID único
        if (clientes.buscarClientePorId(id) != null) {
            System.out.println("   Error: Ya existe un cliente con el ID " + id);
            return;
        }
        
        String nombre = Validador.leerTexto("  Nombre: ");
        boolean requiereReceta = Validador.leerConfirmacion("  ¿Requiere receta médica? (S/N): ");
        int telefono = Validador.leerTelefono("  Teléfono (9 dígitos): ");
        String email = Validador.leerEmail("  Email (debe contener @ y .com/.pe): ");
        
        Cliente c = new Cliente(id, nombre, requiereReceta, telefono, email);
        clientes.agregarCliente(c);
        System.out.println("   Cliente registrado correctamente.");
    }
    
    private static void buscarClientePorId(GestionClientes clientes) {
        mostrarSubtitulo(" BUSCAR CLIENTE POR ID");
        int id = Validador.leerIdCliente("  Ingrese ID del cliente: ");
        
        Cliente encontrado = clientes.buscarClientePorId(id);
        if (encontrado != null) {
            System.out.println("\n  " + LINEA_SIMPLE);
            System.out.println("     CLIENTE ENCONTRADO");
            System.out.println("  " + LINEA_SIMPLE);
            System.out.println("    ID: " + encontrado.getId());
            System.out.println("    Nombre: " + encontrado.getNombre());
            System.out.println("    Receta médica: " + (encontrado.isRecetamedica() ? "Sí" : "No"));
            System.out.println("    Teléfono: " + encontrado.getTelefono());
            System.out.println("    Email: " + encontrado.getEmail());
            System.out.println("  " + LINEA_SIMPLE);
        } else {
            System.out.println("   No se encontró cliente con ID: " + id);
        }
    }
    
    private static void buscarClientePorNombre(GestionClientes clientes) {
        mostrarSubtitulo(" BUSCAR CLIENTE POR NOMBRE");
        String nombre = Validador.leerTexto("  Nombre a buscar: ");
        clientes.buscarClientePorNombre(nombre);
    }
    
    private static void modificarCliente(GestionClientes clientes) {
        mostrarSubtitulo(" MODIFICAR CLIENTE");
        int id = Validador.leerIdCliente("  ID del cliente: ");
        
        // Verificar que existe
        if (clientes.buscarClientePorId(id) == null) {
            System.out.println("   Error: No existe cliente con ID " + id);
            return;
        }
        
        int telefono = Validador.leerTelefono("  Nuevo telefono (9 dígitos): ");
        String email = Validador.leerEmail("  Nuevo email (debe contener @ y .com/.pe): ");
        
        clientes.actualizarCliente(id, telefono, email);
    }
    
    private static void eliminarCliente(GestionClientes clientes) {
        mostrarSubtitulo(" ELIMINAR CLIENTE");
        int id = Validador.leerIdCliente("  ID del cliente: ");
        
        // Verificar que existe
        if (clientes.buscarClientePorId(id) == null) {
            System.out.println("   Error: No existe cliente con ID " + id);
            return;
        }
        
        if (Validador.leerConfirmacion("  ¿Está seguro de eliminar este cliente? (S/N): ")) {
            clientes.eliminarCliente(id);
        } else {
            System.out.println("   Operación cancelada.");
        }
    }
    
    // ==================== MENÚ PROVEEDORES ====================
    
    private static void menuProveedores(GestionProveedores proveedores) {
        int opcion;
        
        do {
            mostrarSubtitulo(" GESTIÓN DE PROVEEDORES");
            System.out.println("  1. Registrar proveedor");
            System.out.println("  2. Buscar proveedor");
            System.out.println("  3. Modificar proveedor");
            System.out.println("  4. Eliminar proveedor");
            System.out.println("  5. Listar proveedores");
            System.out.println("  0. Regresar");
            System.out.println(LINEA_SIMPLE);
            
            opcion = Validador.leerOpcion("  Opción: ", 0, 5);
            
            switch (opcion) {
                case 1 -> registrarProveedor(proveedores);
                case 2 -> buscarProveedor(proveedores);
                case 3 -> modificarProveedor(proveedores);
                case 4 -> eliminarProveedor(proveedores);
                case 5 -> proveedores.listarProveedores();
                case 0 -> System.out.println("   Regresando...");
            }
            
        } while (opcion != 0);
    }
    
    // ==================== OPERACIONES DE PROVEEDORES ====================
    
    private static void registrarProveedor(GestionProveedores proveedores) {
        mostrarSubtitulo(" REGISTRAR PROVEEDOR");
        
        int id = Validador.leerIdProveedor("  ID del proveedor: ");
        
        // Verificar ID único
        if (proveedores.obtenerProveedor(id,false) != null) {
            System.out.println("   Error: Ya existe un proveedor con el ID " + id);
            return;
        }
        
        String nombre = Validador.leerTexto("  Nombre: ");
        int telefono = Validador.leerTelefono("  Teléfono (9 dígitos): ");
        String email = Validador.leerEmail("  Email (debe contener @ y .com/.pe): ");
        String tipoMedicamento = Validador.leerTexto("  Tipo de medicamento: ");
        
        Proveedor p = new Proveedor(id, nombre, telefono, email, tipoMedicamento);
        proveedores.registrarProveedor(p);
    }
    
    private static void buscarProveedor(GestionProveedores proveedores) {
        mostrarSubtitulo(" BUSCAR PROVEEDOR");
        int id = Validador.leerIdProveedor("  ID del proveedor: ");
        proveedores.buscarProveedor(id);
    }
    
    private static void modificarProveedor(GestionProveedores proveedores) {
        mostrarSubtitulo(" MODIFICAR PROVEEDOR");
        int id = Validador.leerIdProveedor("  ID del proveedor: ");
        
        // Verificar que existe
        if (proveedores.obtenerProveedor(id,false) == null) {
            System.out.println("   Error: No existe proveedor con ID " + id);
            return;
        }
        
        String nombre = Validador.leerTexto("  Nuevo nombre: ");
        int telefono = Validador.leerTelefono("  Nuevo teléfono (9 dígitos): ");
        String email = Validador.leerEmail("  Nuevo email (debe contener @ y .com/.pe): ");
        String tipoMedicamento = Validador.leerTexto("  Nuevo tipo de medicamento: ");
        
        proveedores.actualizarProveedor(id, nombre, telefono, email, tipoMedicamento);
    }
    
    private static void eliminarProveedor(GestionProveedores proveedores) {
        mostrarSubtitulo(" ELIMINAR PROVEEDOR");
        int id = Validador.leerIdProveedor("  ID del proveedor: ");
        
        // Verificar que existe
        if (proveedores.obtenerProveedor(id,false) == null) {
            System.out.println("   Error: No existe proveedor con ID " + id);
            return;
        }
        
        if (Validador.leerConfirmacion("  ¿Está seguro de eliminar este proveedor? (S/N): ")) {
            proveedores.eliminarProveedor(id);
        } else {
            System.out.println("   Operación cancelada.");
        }
    }
    
    // ==================== MENÚ VENTAS ====================
    
    private static void menuVentas(GestionVentas ventas, GestionInventario inventario) {
        int opcion;
        
        do {
            mostrarSubtitulo(" GESTIÓN DE VENTAS");
            System.out.println("  1. Registrar venta");
            System.out.println("  2. Buscar venta");
            System.out.println("  3. Listar ventas");
            System.out.println("  4. Mostrar ingresos totales");
            System.out.println("  5. Mostrar fecha con mayor ingreso");
            System.out.println("  0. Regresar");
            System.out.println(LINEA_SIMPLE);
            
            opcion = Validador.leerOpcion("  Opción: ", 0, 5);
            
            switch (opcion) {
                case 1 -> registrarVenta(ventas, inventario);
                case 2 -> buscarVenta(ventas);
                case 3 -> ventas.listarVentas();
                case 4 -> ventas.calcularImpuesto();
                case 5 -> ventas.fechaMayorIngreso();
                case 0 -> System.out.println("   Regresando...");
            }
            
        } while (opcion != 0);
    }
    
    // ==================== OPERACIONES DE VENTAS ====================
    
    private static void registrarVenta(GestionVentas ventas, GestionInventario inventario) {
        mostrarSubtitulo(" REGISTRAR VENTA");
        
        int id = Validador.leerIdVenta("  Código de venta: ");
        
        // Verificar ID único
        if (ventas.obtenerVenta(id,true) != null) {
            System.out.println("   Error: Ya existe una venta con el código " + id);
            return;
        }
        
        LocalDate fecha = Validador.leerFecha("  Fecha de venta (yyyy-MM-dd): ");
        
        // Validar que no sea fecha futura
        if (fecha.isAfter(LocalDate.now())) {
            System.out.println("   Error: La fecha no puede ser futura.");
            return;
        }
        
        String nombreMedicamento = Validador.leerTexto("  Nombre del medicamento: ");
        int cantidad = Validador.leerEnteroPositivo("  Cantidad: ");
        double total = Validador.leerDoublePositivo("  Total de la venta: ");
        String nombreCliente = Validador.leerTexto("  Nombre del cliente: ");
        
        Cliente cliente = new Cliente();
        cliente.setNombre(nombreCliente);
        
        // Intentar descontar stock
        boolean stockDescontado = inventario.ventaMedicamento(nombreMedicamento, cantidad);
        
        if (stockDescontado) {
            Venta venta = new Venta(id, fecha, nombreMedicamento, cantidad, total, cliente);
            ventas.registrarVenta(venta);
        } else {
            System.out.println("   La venta ha sido cancelada por problemas de inventario.");
        }
    }
    
    private static void buscarVenta(GestionVentas ventas) {
        mostrarSubtitulo(" BUSCAR VENTA");
        int id = Validador.leerIdVenta("  Código de venta: ");
        ventas.buscarVenta(id);
    }
    
    // ==================== MENÚ REPORTES ====================
    
    private static void menuReportes(GestionInventario inventario, GestionVentas ventas) {
        int opcion;
        
        do {
            mostrarSubtitulo(" REPORTES");
            System.out.println("  1. Medicamentos con stock bajo");
            System.out.println("  2. Medicamentos próximos a vencer");
            System.out.println("  3. Reporte de ventas");
            System.out.println("  0. Regresar");
            System.out.println(LINEA_SIMPLE);
            
            opcion = Validador.leerOpcion("  Opción: ", 0, 3);
            
            switch (opcion) {
                case 1 -> reporteStockBajo(inventario);
                case 2 -> inventario.mostrarMedicamentosPorVencer();
                case 3 -> ventas.listarVentas();
                case 0 -> System.out.println("   Regresando...");
            }
            
        } while (opcion != 0);
    }
    
    private static void reporteStockBajo(GestionInventario inventario) {
        mostrarSubtitulo(" STOCK BAJO");
        int limite = Validador.leerEnteroPositivo("  Ingrese el límite de stock: ");
        
        Medicamento[] lista = inventario.obtenerReporteStockBajo(limite);
        
        if (lista.length == 0) {
            System.out.println("   No existen medicamentos con stock por debajo de " + limite + ".");
        } else {
            System.out.println("\n  " + LINEA_SIMPLE);
            System.out.println("     MEDICAMENTOS CON STOCK BAJO (Menor a " + limite + ")");
            System.out.println("  " + LINEA_SIMPLE);
            int contador = 1;
            for (Medicamento m : lista) {
                if (m != null) {
                    System.out.println("    " + contador + ". " + m.getNombre() + 
                                     " (Stock: " + m.getStock() + ")");
                    contador++;
                }
            }
            System.out.println("  " + LINEA_SIMPLE);
            System.out.println("   Total: " + lista.length + " medicamentos con stock bajo.");
        }
    }
}