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

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestionInventario inventario = new GestionInventario();
        GestionClientes clientes = new GestionClientes();
        GestionProveedores proveedores = new GestionProveedores();
        GestionVentas ventas = new GestionVentas();
        int opcion;
        do{
            System.out.println("\n========= FARMACIA =========");
            System.out.println("1. Gestionar Inventario");
            System.out.println("2. Gestionar Clientes");
            System.out.println("3. Gestionar Proveedores");
            System.out.println("4. Gestionar Ventas");
            System.out.println("5. Reportes");
            System.out.println("0. Salir");
            System.out.println("Que desea hacer?: ");
            System.out.println("===============================");
            opcion=sc.nextInt();
            switch(opcion){
                case 1:
                    menuInventario(sc,inventario);
                    break;
                case 2:
                    menuClientes(sc,clientes);
                    break;
                case 3:
                    menuProveedores(sc,proveedores);
                    break;
                case 4:
                    menuVentas(sc,ventas);
                    break;
                case 5:
                    System.out.println("Generando reportes...");
                    break;
                case 0:
                    System.out.println("Hasta luego");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        }while(opcion!=0);
    }
    public static void menuInventario(Scanner sc, GestionInventario inventario){
       int op;
       do{
           System.out.println("\n====== INVENTARIO ======");
           System.out.println("1. Registrar medicamento");
           System.out.println("2. Buscar medicamento");
           System.out.println("3. Modificar medicamento");
           System.out.println("4. Eliminar medicamento");
           System.out.println("5. Listar medicamentos");
           System.out.println("6. Actualizar stock");
           System.out.println("0. Regresar");
           System.out.print("Opción: ");
           op=sc.nextInt();
           switch(op){
                case 1:
                      //inventario.registrarMedicamento(...)
                      break;

                case 2:
                     //inventario.buscarMedicamento(...)
                     break;
                case 3:
                     //inventario.modificarMedicamento(...)
                     break;
                case 4:
                     //inventario.eliminarMedicamento(...)
                     break;
                case 5:
                    inventario.listarMedicamentos();
                    break;
                case 6:
                    //inventario.actualizarStock(...)
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
           }
        }while(op!=0);
    }
    public static void menuClientes(Scanner sc, GestionClientes clientes){
        int op;
        do{
            System.out.println("\n====== CLIENTES ======");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Buscar cliente");
            System.out.println("3. Modificar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Listar clientes");
            System.out.println("0. Regresar");
            System.out.print("Opción: ");
            op=sc.nextInt();
            switch(op){
                case 1:
                    //clientes.registrarCliente(...)
                    break;
                case 2:
                    //clientes.buscarCliente(...)
                    break;
                case 3:
                    //clientes.modificarCliente(...)
                   break;
                case 4:
                    //clientes.eliminarCliente(...)
                   break;
                case 5:
                   clientes.listarClientes();
                   break;
                case 0:
                   break;
                default:
                   System.out.println("Opción inválida.");
                }
        }while(op!=0);
    }
    public static void menuProveedores(Scanner sc, GestionProveedores proveedores){
        int op;
        do{
            System.out.println("\n====== PROVEEDORES ======");
            System.out.println("1. Registrar proveedor");
            System.out.println("2. Buscar proveedor");
            System.out.println("3. Modificar proveedor");
            System.out.println("4. Eliminar proveedor");
            System.out.println("5. Listar proveedores");
            System.out.println("0. Regresar");
            System.out.print("Opción: ");
            op=sc.nextInt();
            switch(op){
                case 1:
                    //proveedores.registrarProveedor(...)
                     break;
                case 2:
                    //proveedores.buscarProveedor(...)
                    break;
                case 3:
                    //proveedores.modificarProveedor(...)
                    break;
                case 4:
                    //proveedores.eliminarProveedor(...)
                    break;
                case 5:
                    proveedores.listarProveedores();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
                }
        }while(op!=0);
    }
    public static void menuVentas(Scanner sc, GestionVentas ventas){
        int op;
        do{
            System.out.println("\n====== VENTAS ======");
            System.out.println("1. Registrar venta");
            System.out.println("2. Buscar venta");
            System.out.println("3. Listar ventas");
            System.out.println("4. Mostrar total vendido");
            System.out.println("0. Regresar");
            System.out.print("Opción: ");
            op=sc.nextInt();
            switch(op){
                case 1:
                    //ventas.registrarVenta(...)
                    break;
                case 2:
                    //ventas.buscarVenta(...)
                    break;
                case 3:
                    ventas.listarVentas();
                    break;
                case 4:
                    //ventas.mostrarTotalVentas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
                }
        }while(op!=0);
    }
}