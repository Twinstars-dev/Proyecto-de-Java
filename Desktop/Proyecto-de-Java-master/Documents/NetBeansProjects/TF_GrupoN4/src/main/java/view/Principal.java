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

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestionInventario inventario = new GestionInventario();
        GestionClientes clientes = new GestionClientes();
        GestionProveedores proveedores = new GestionProveedores();
        GestionVentas ventas = new GestionVentas();
        menuPrincipal(sc, inventario, clientes, proveedores, ventas);

    }

    public static void menuPrincipal(
            Scanner sc,
            GestionInventario inventario,
            GestionClientes clientes,
            GestionProveedores proveedores,
            GestionVentas ventas) {
        int opcion;
        do {
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
                    System.out.println("Gracias por utilizar el sistema.");
                    break;
                default:
                    System.out.println("Opción invalida.");
            }
        } while (opcion != 0);
    }

    public static void menuMedicamentos(Scanner sc, GestionInventario inventario) {
        int opcion;
        do {
            System.out.println("\n========== MEDICAMENTOS ==========");
            System.out.println("1. Registrar medicamento");
            System.out.println("2. Buscar medicamento");
            System.out.println("3. Modificar medicamento");
            System.out.println("4. Eliminar medicamento");
            System.out.println("5. Listar medicamentos");
            System.out.println("6. Venta de medicamento");
            System.out.println("7. Mostrar medicamentos por vencer");
            System.out.println("0. Regresar");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.print("Codigo: ");
                    String codigo = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Laboratorio: ");
                    String laboratorio = sc.nextLine();
                    System.out.print("Stock: ");
                    int stock = sc.nextInt();
                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Fecha de vencimiento (AAAA-MM-DD): ");
                    LocalDate fecha = LocalDate.parse(sc.nextLine());
                    System.out.print("Categoria: ");
                    String categoria = sc.nextLine();
                    System.out.print(" ¿Requiere receta? (true/false): ");
                    boolean receta = sc.nextBoolean();
                    sc.nextLine();
                    Medicamento medicamento = new Medicamento(codigo,nombre,laboratorio,stock,precio,fecha,categoria,receta);
                    inventario.agregarMedicamento(medicamento);
                    break;
                case 2:
                    System.out.print("Ingrese codigo: ");
                    codigo = sc.nextLine();
                    System.out.println(inventario.buscarMedicamento(codigo));
                    break;
                case 3:
                    System.out.print("Nombre del medicamento: ");
                    nombre = sc.nextLine();
                    System.out.print("Nuevo precio: ");
                    precio = sc.nextDouble();
                    System.out.print("Nuevo stock: ");
                    stock = sc.nextInt();
                    sc.nextLine();
                    inventario.actualizarMedicamento(nombre,precio,stock);
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
                case 7:
                    inventario.mostrarMedicamentosPorVencer();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción invalida.");
            }
        } while (opcion != 0);
    }

    public static void menuClientes(Scanner sc, GestionClientes clientes) {
        int opcion;
        do {
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
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("¿Tiene receta medica? (true/false): ");
                    boolean receta = sc.nextBoolean();
                    System.out.print("Telefono: ");
                    int telefono = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    Cliente cliente = new Cliente(id,nombre,receta,telefono,email);
                    clientes.agregarCliente(cliente);
                    break;
                case 2:
                    System.out.print("Ingrese ID: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.println(clientes.buscarCliente(id));
                    break;
                case 3:
                    System.out.print("Nombre a buscar: ");
                    nombre = sc.nextLine();
                    clientes.buscarClientePorNombre(nombre);
                    break;
                case 4:
                    System.out.print("ID del cliente: ");
                    id = sc.nextInt();
                    System.out.print("Nuevo teléfono: ");
                    telefono = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuevo email: ");
                    email = sc.nextLine();
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
                    System.out.println("Opción invalida.");
            }
        } while (opcion != 0);
    }

    public static void menuProveedores(Scanner sc, GestionProveedores proveedores) {
        int opcion;
        do {
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
                    Proveedor proveedor = new Proveedor(id,nombre,telefono,email,tipo);
                    proveedores.registrarProveedor(proveedor);
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
                    proveedores.actualizarProveedor(nombre,telefono,email);
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
                    System.out.println("Opción invalida.");
            }
        } while (opcion != 0);
    }

    public static void menuVentas(Scanner sc, GestionVentas ventas, GestionInventario inventario) {
        int opcion;
        do {
            System.out.println("\n========== VENTAS ==========");
            System.out.println("1. Registrar venta");
            System.out.println("2. Buscar venta");
            System.out.println("3. Listar ventas");
            System.out.println("4. Mostrar ingresos totales");
            System.out.println("5. Mostrar fecha con mayor ingreso");
            System.out.println("0. Regresar");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.next();
            
            switch (opcion) {
                case 1:
                    System.out.print("Codigo de venta: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nombre del medicamento: ");
                    String nombre = sc.nextLine();
                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();
                    System.out.print("Total de la venta: ");
                    double total = sc.nextDouble();
                    sc.nextLine();
                    Venta venta = new Venta(id,fechaVendida,nombre,cantidad,total);
                    ventas.registrarVenta(venta);
                    inventario.ventaMedicamento(nombre, cantidad);
                    break;
                case 2:
                    System.out.print("Codigo de venta: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    ventas.buscarVenta(id);
                    break;
                case 3:
                    ventas.listarVentas();
                    break;
                case 4:
                    System.out.println("Ingresos totales: S/. "+ ventas.calcularIngresosTotales());
                    break;
                case 5:
                    ventas.fechaMayorIngreso();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion inválida.");
            }
        } while (opcion != 0);
    }
    public static void menuReportes(GestionInventario inventario, GestionVentas ventas) {
        int opcion;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n========== REPORTES ==========");
            System.out.println("1. Medicamentos con stock bajo");
            System.out.println("2. Medicamentos proximos a vencer");
            System.out.println("3. Ingresos totales");
            System.out.println("4. Fecha con mayor ingreso");
            System.out.println("0. Regresar");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el limite de stock: ");
                    int limite = sc.nextInt();
                    Medicamento[] lista = inventario.obtenerReporteStockBajo(limite);
                    if (lista.length == 0) {
                        System.out.println("No existen medicamentos con stock bajo.");
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
                case 3:
                    System.out.println("Ingresos totales: S/. "+ ventas.calcularIngresosTotales());
                    break;
                case 4:
                    ventas.fechaMayorIngreso();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion inválida.");
            }
        } while (opcion != 0);
    }
}
