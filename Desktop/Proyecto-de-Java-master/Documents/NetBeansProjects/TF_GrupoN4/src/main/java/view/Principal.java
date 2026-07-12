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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import util.Validador;

/**
 * Clase principal del sistema de gestión de farmacia
 * Contiene el menú principal y todos los submenús
 * @author Grupo
 */
public class Principal {   
    /**
     * Método principal - Punto de entrada del sistema
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestionInventario inventario = new GestionInventario();
        GestionClientes clientes = new GestionClientes();
        GestionProveedores proveedores = new GestionProveedores();
        GestionVentas ventas = new GestionVentas();       
        // Cargar datos de prueba al iniciar
        cargarDatosPrueba(inventario, clientes, proveedores, ventas);
        // Mostrar menú principal
        menuPrincipal(sc, inventario, clientes, proveedores, ventas);
    }
    
    /**
     * Carga datos de ejemplo para pruebas
     */
    public static void cargarDatosPrueba(GestionInventario inventario, GestionClientes clientes, GestionProveedores proveedores,GestionVentas ventas) {
        try {
            // ===== MEDICAMENTOS =====
            inventario.agregarMedicamento(new Medicamento("101", "Paracetamol", "Medifarma", 10, 5.0, 
                    LocalDate.of(2027, 5, 20), "Analgesico", false));
            inventario.agregarMedicamento(new Medicamento("102", "Ibuprofeno", "Medifarma", 5, 4.0, 
                    LocalDate.of(2027, 8, 15), "antiinflamatorio", false));
            inventario.agregarMedicamento(new Medicamento("103", "Amoxicilina", "Roxfarma", 15, 9.0, 
                    LocalDate.of(2030, 4, 21), "antibiotico", false));
            inventario.agregarMedicamento(new Medicamento("104", "Loratadina", "Roxfarma", 10, 10.0, 
                    LocalDate.of(2030, 2, 11), "antihistaminico", true));
            inventario.agregarMedicamento(new Medicamento("105", "Omeprazol", "Laboratorios Unidos", 15, 6.0, 
                    LocalDate.of(2028, 7, 25), "inhibidor", true));
            inventario.agregarMedicamento(new Medicamento("106", "Naproxeno", "Laboratorios Unidos", 2, 10.0, 
                    LocalDate.of(2026, 7, 18), "analgesico", true));
            
            // ===== CLIENTES =====
            clientes.agregarCliente(new Cliente(1, "Juan Pérez", false, 987654321, "juan@gmail.com"));
            clientes.agregarCliente(new Cliente(2, "María López", true, 912345678, "maria@gmail.com"));
            clientes.agregarCliente(new Cliente(3, "Carlos Torres", false, 998877665, "carlos@gmail.com"));
            
            // ===== PROVEEDORES =====
            proveedores.registrarProveedor(new Proveedor(1, "Laboratorio Bayer", 987111222, "bayer@gmail.com", "Analgésicos"));
            proveedores.registrarProveedor(new Proveedor(2, "Medifarma", 999333444, "medifarma@gmail.com", "Antibióticos"));
            proveedores.registrarProveedor(new Proveedor(3, "Inkafarma Distribución", 988555666, "inkafarma@gmail.com", "Vitaminas"));
            
            // ===== VENTAS =====
            Cliente cliente1 = new Cliente();
            cliente1.setNombre("Mario Vargas");
            Cliente cliente2 = new Cliente();
            cliente2.setNombre("Abraham Valdelomar");
            Cliente cliente3 = new Cliente();
            cliente3.setNombre("Cesar Vallejo");
            
            ventas.registrarVenta(new Venta(1, LocalDate.of(2026, 7, 11), "Panadol", 3, 4.5, cliente1));
            ventas.registrarVenta(new Venta(2, LocalDate.of(2026, 7, 10), "Omeprazol", 4, 5.0, cliente2));
            ventas.registrarVenta(new Venta(3, LocalDate.of(2026, 7, 9), "Panadol", 5, 6.0, cliente3));
            
            System.out.println("✅ Datos de prueba cargados correctamente.");
            
        } catch (Exception e) {
            System.out.println("Error al cargar datos de prueba: " + e.getMessage());
        }
    }
    
    /**
     * Menú principal del sistema
     */
    public static void menuPrincipal(Scanner sc, GestionInventario inventario, GestionClientes clientes, GestionProveedores proveedores, GestionVentas ventas) {
        int opcion = -1;        
        do {
            try {
                System.out.println("\n======================================");
                System.out.println("     SISTEMA DE GESTION FARMACIA");
                System.out.println("======================================");
                System.out.println("1. Gestion de Medicamentos");
                System.out.println("2. Gestion de Clientes");
                System.out.println("3. Gestion de Proveedores");
                System.out.println("4. Gestion de Ventas");
                System.out.println("5. Reportes");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opcion: ");
                opcion = sc.nextInt();
                sc.nextLine();
                
                switch (opcion) {
                    case 1:
                        menuMedicamentos(sc, inventario);
                        break;
                    case 2:
                        menuClientes(sc, clientes);
                        break;
                    case 3:
                        menuProveedores(sc, proveedores);
                        break;
                    case 4:
                        menuVentas(sc, ventas, inventario);
                        break;
                    case 5:
                        menuReportes(inventario, ventas);
                        break;
                    case 0:
                        System.out.println(" Gracias por utilizar el sistema.");
                        break;
                    default:
                        System.out.println(" Opción inválida.");
                }
                
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido.");
                sc.nextLine(); // Limpiar buffer
                opcion = -1;
            }
            
        } while (opcion != 0);
    }
    
    /**
     * Menú de gestión de medicamentos
     */
    public static void menuMedicamentos(Scanner sc, GestionInventario inventario) {
        int opcion = -1;
        
        do {
            try {
                System.out.println("\n========== MEDICAMENTOS ==========");
                System.out.println("1. Registrar medicamento");
                System.out.println("2. Buscar medicamento");
                System.out.println("3. Modificar medicamento");
                System.out.println("4. Eliminar medicamento");
                System.out.println("5. Listar medicamentos");
                System.out.println("6. Venta de medicamento");
                System.out.println("0. Regresar");
                System.out.print("Opcion: ");
                opcion = sc.nextInt();
                sc.nextLine();
                
                switch (opcion) {
                    case 1:
                        registrarMedicamento(sc, inventario);
                        break;
                        
                    case 2:
                        System.out.print("Ingrese el codigo del medicamento: ");
                        String codigo = sc.nextLine();
                        System.out.println(inventario.buscarMedicamentoPorCodigo(codigo));
                        break;
                        
                    case 3:
                        System.out.print("Nombre del medicamento: ");
                        String nombre = sc.nextLine();
                        System.out.print("Nuevo precio: ");
                        double precio = sc.nextDouble();
                        System.out.print("Nuevo stock: ");
                        int stock = sc.nextInt();
                        sc.nextLine();
                        inventario.actualizarMedicamento(nombre, precio, stock);
                        break;
                        
                    case 4:
                        System.out.print("Codigo del medicamento a eliminar: ");
                        codigo = sc.nextLine();
                        inventario.eliminarMedicamento(codigo);
                        break;
                        
                    case 5:
                        inventario.listarMedicamentos();
                        break;
                        
                    case 6:
                        System.out.print("Nombre del medicamento: ");
                        nombre = sc.nextLine();
                        System.out.print("Cantidad a vender: ");
                        int cantidad = sc.nextInt();
                        sc.nextLine();
                        inventario.ventaMedicamento(nombre, cantidad);
                        break;
                        
                    case 0:
                        break;
                        
                    default:
                        System.out.println(" Opcion invalida.");
                }
                
            } catch (Exception e) {
                System.out.println("Error: Ingrese un dato válido.");
                sc.nextLine(); // Limpiar buffer
                opcion = -1;
            }
            
        } while (opcion != 0);
    }
    
    /**
     * Registra un nuevo medicamento
     */
    private static void registrarMedicamento(Scanner sc, GestionInventario inventario) {
        try {
            System.out.print("Codigo: ");
            String codigo = sc.nextLine();
            
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            
            System.out.print("Laboratorio: ");
            String laboratorio = sc.nextLine();
            
            System.out.print("Stock: ");
            int stock = sc.nextInt();
            
            System.out.print("Precio por unidad: ");
            double precio = sc.nextDouble();
            sc.nextLine();
            
            System.out.print("Fecha de vencimiento (AAAA-MM-DD): ");
            LocalDate fecha = LocalDate.parse(sc.nextLine());
            
            System.out.print("Categoria: ");
            String categoria = sc.nextLine();
            
            // Validar respuesta de receta médica
            String respuesta;
            boolean receta;
            do {
                System.out.print("¿Requiere receta? (Si/No): ");
                respuesta = sc.next();
                if (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no")) {
                    System.out.println("⚠ Ingrese únicamente 'Si' o 'No'.");
                }
            } while (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no"));
            
            receta = respuesta.equalsIgnoreCase("si");
            sc.nextLine();
            
            // Crear y agregar el medicamento
            Medicamento medicamento = new Medicamento(codigo, nombre, laboratorio, stock,precio, fecha, categoria, receta);
            inventario.agregarMedicamento(medicamento);
            
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha inválido. Use AAAA-MM-DD");
        } catch (Exception e) {
            System.out.println("Error al registrar medicamento: " + e.getMessage());
        }
    }
    
    /**
     * Menú de gestión de clientes
     */
    public static void menuClientes(Scanner sc, GestionClientes clientes) {
        int opcion = -1;
        
        do {
            try {
                System.out.println("\n========== CLIENTES ==========");
                System.out.println("1. Registrar cliente");
                System.out.println("2. Buscar cliente por ID");
                System.out.println("3. Buscar cliente por nombre");
                System.out.println("4. Modificar cliente");
                System.out.println("5. Eliminar cliente");
                System.out.println("6. Listar clientes");
                System.out.println("0. Regresar");
                System.out.print("Opcion: ");
                opcion = sc.nextInt();
                sc.nextLine();
                
                switch (opcion) {
                    case 1:
                        registrarCliente(sc, clientes);
                        break;
                        
                    case 2:
                        System.out.print("Ingrese ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.println(clientes.buscarCliente(id));
                        break;
                        
                    case 3:
                        System.out.print("Nombre a buscar: ");
                        String nombre = sc.nextLine();
                        clientes.buscarClientePorNombre(nombre);
                        break;
                        
                    case 4:
                        System.out.print("ID del cliente: ");
                        id = sc.nextInt();
                        System.out.print("Nuevo teléfono: ");
                        int telefono = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nuevo email: ");
                        String email = sc.nextLine();
                        clientes.actualizarCliente(id, telefono, email);
                        break;
                        
                    case 5:
                        System.out.print("ID del cliente: ");
                        id = sc.nextInt();
                        sc.nextLine();
                        clientes.eliminarCliente(id);
                        break;
                        
                    case 6:
                        clientes.listarClientes();
                        break;
                        
                    case 0:
                        break;
                        
                    default:
                        System.out.println(" Opción inválida.");
                }
                
            } catch (Exception e) {
                System.out.println(" Error: Ingrese un dato válido.");
                sc.nextLine(); // Limpiar buffer
                opcion = -1;
            }
            
        } while (opcion != 0);
    }
    
    /**
     * Registra un nuevo cliente
     */
    private static void registrarCliente(Scanner sc, GestionClientes clientes) {
        try {
            System.out.print("ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            
            String respuesta;
            boolean receta;
            do {
                System.out.print("¿Requiere receta? (Si/No): ");
                respuesta = sc.next();
                if (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no")) {
                    System.out.println(" Ingrese únicamente 'Si' o 'No'.");
                }
            } while (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no"));
            
            receta = respuesta.equalsIgnoreCase("si");
            
            System.out.print("Telefono: ");
            int telefono = sc.nextInt();
            sc.nextLine();
            
            System.out.print("Email: ");
            String email = sc.nextLine();
            
            Cliente cliente = new Cliente(id, nombre, receta, telefono, email);
            clientes.agregarCliente(cliente);
            
        } catch (Exception e) {
            System.out.println(" Error al registrar cliente: " + e.getMessage());
        }
    }
    
    /**
     * Menú de gestión de proveedores
     */
    public static void menuProveedores(Scanner sc, GestionProveedores proveedores) {
        int opcion = -1;
        
        do {
            try {
                System.out.println("\n========== PROVEEDORES ==========");
                System.out.println("1. Registrar proveedor");
                System.out.println("2. Buscar proveedor");
                System.out.println("3. Modificar proveedor");
                System.out.println("4. Eliminar proveedor");
                System.out.println("5. Listar proveedores");
                System.out.println("0. Regresar");
                System.out.print("Opción: ");
                opcion = sc.nextInt();
                sc.nextLine();
                
                switch (opcion) {
                    case 1:
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Telefono: ");
                        int telefono = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("Tipo de medicamento: ");
                        String tipo = sc.nextLine();
                        
                        Proveedor proveedor = new Proveedor(id, nombre, telefono, email, tipo);
                        proveedores.registrarProveedor(proveedor);
                        System.out.println("✅ Proveedor registrado correctamente.");
                        break;
                        
                    case 2:
                        System.out.print("Ingrese el ID del proveedor: ");
                        id = sc.nextInt();
                        sc.nextLine();
                        proveedores.buscarProveedor(id);
                        break;
                        
                    case 3:
                        System.out.print("Nombre del proveedor: ");
                        nombre = sc.nextLine();
                        System.out.print("Nuevo telefono: ");
                        telefono = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nuevo email: ");
                        email = sc.nextLine();
                        proveedores.actualizarProveedor(nombre, telefono, email);
                        break;
                        
                    case 4:
                        System.out.print("ID del proveedor: ");
                        id = sc.nextInt();
                        sc.nextLine();
                        proveedores.eliminarProveedor(id);
                        break;
                        
                    case 5:
                        proveedores.listarProveedor();
                        break;
                        
                    case 0:
                        break;
                        
                    default:
                        System.out.println(" Opción inválida.");
                }
                
            } catch (Exception e) {
                System.out.println(" Error: Ingrese un dato válido.");
                sc.nextLine(); // Limpiar buffer
                opcion = -1;
            }
            
        } while (opcion != 0);
    }
    
    /**
     * Menú de gestión de ventas
     */
    public static void menuVentas(Scanner sc, GestionVentas ventas, GestionInventario inventario) {
        int opcion = -1;
        
        do {
            try {
                System.out.println("\n========== VENTAS ==========");
                System.out.println("1. Registrar venta");
                System.out.println("2. Buscar venta");
                System.out.println("3. Listar ventas");
                System.out.println("4. Mostrar ingresos totales");
                System.out.println("5. Mostrar fecha con mayor ingreso");
                System.out.println("0. Regresar");
                System.out.print("Opcion: ");
                opcion = sc.nextInt();
                sc.nextLine();
                
                switch (opcion) {
                    case 1:
                        registrarVenta(sc, ventas, inventario);
                        break;
                        
                    case 2:
                        System.out.print("Codigo de venta: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        ventas.buscarVenta(id);
                        break;
                        
                    case 3:
                        ventas.listarVentas();
                        break;
                        
                    case 4:
                        System.out.println("Las estadísticas muestran que: ");
                        ventas.calcularImpuesto();
                        break;
                        
                    case 5:
                        ventas.fechaMayorIngreso();
                        break;
                        
                    case 0:
                        break;
                        
                    default:
                        System.out.println(" Opción inválida.");
                }
                
            } catch (Exception e) {
                System.out.println(" Error: Ingrese un dato válido.");
                sc.nextLine(); // Limpiar buffer
                opcion = -1;
            }
            
        } while (opcion != 0);
    }
    
    /**
     * Registra una nueva venta
     */
    private static void registrarVenta(Scanner sc, GestionVentas ventas, GestionInventario inventario) {
        try {
            System.out.print("Código de venta: ");
            int id = Validador.leerInt(sc);
            
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.print("Fecha de venta (dd/MM/yyyy): ");
            String fechaVendida = sc.next();
            LocalDate fecha = LocalDate.parse(fechaVendida, formato);
            
            System.out.print("Nombre del medicamento: ");
            String nombre = sc.next();
            
            System.out.print("Cantidad: ");
            int cantidad = Validador.leerInt(sc);
            
            System.out.print("Total de la venta: ");
            double total = Validador.leerDouble(sc);
            
            System.out.print("Cliente: ");
            sc.nextLine(); // Limpiar buffer
            Cliente cliente = new Cliente();
            cliente.setNombre(sc.nextLine());
            
            // 1. Intentar descontar del inventario
            boolean stockDescontado = inventario.ventaMedicamento(nombre, cantidad);
            
            // 2. Solo si el inventario fue exitoso, registrar la venta
            if (stockDescontado) {
                Venta venta = new Venta(id, fecha, nombre, cantidad, total, cliente);
                ventas.registrarVenta(venta);
                System.out.println(" Venta realizada y registrada correctamente.");
            } else {
                System.out.println(" La venta ha sido cancelada por problemas de inventario.");
            }
            
        } catch (DateTimeParseException e) {
            System.out.println(" Error: Formato de fecha inválido. Use dd/MM/yyyy");
        } catch (Exception e) {
            System.out.println(" Error al registrar venta: " + e.getMessage());
        }
    }
    
    /**
     * Menú de reportes
     */
    public static void menuReportes(GestionInventario inventario, GestionVentas ventas) {
        int opcion = -1;
        Scanner sc = new Scanner(System.in);
        
        do {
            try {
                System.out.println("\n========== REPORTES ==========");
                System.out.println("1. Medicamentos con stock bajo");
                System.out.println("2. Medicamentos proximos a vencer");
                System.out.println("0. Regresar");
                System.out.println("================================");
                System.out.print("Opcion: ");
                opcion = sc.nextInt();
                
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el limite de stock: ");
                        int limite = sc.nextInt();
                        Medicamento[] lista = inventario.obtenerReporteStockBajo(limite);
                        
                        if (lista.length == 0) {
                            System.out.println("✅ No existen medicamentos con stock bajo.");
                        } else {
                            System.out.println("\n===== STOCK BAJO =====");
                            for (Medicamento m : lista) {
                                System.out.println(m);
                            }
                        }
                        break;
                        
                    case 2:
                        inventario.mostrarMedicamentosPorVencer();
                        break;
                        
                    case 0:
                        break;
                        
                    default:
                        System.out.println(" Opción inválida.");
                }
                
            } catch (Exception e) {
                System.out.println(" Error: Ingrese un número válido.");
                sc.nextLine(); // Limpiar buffer
                opcion = -1;
            }
            
        } while (opcion != 0);
    }
}
