/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.time.LocalDate;
import model.Venta;
import model.Cliente;
/**
 *
 * @author GestionFarmacia
 */
public class GestionVentas{   
private Venta[] ventas;
    private int contador;

    public GestionVentas() {
        this.ventas = new Venta[100];
        this.contador = 0;
    }

    public void registrarVenta(Venta venta) {
        if (contador < ventas.length) {
            ventas[contador] = venta;
            contador++;
            System.out.println("Venta registrada correctamente.");
        } else {
            System.out.println("No hay espacio para registrar más ventas.");
        }
    }

    public Venta obtenerVenta(int codigo) {
        for (int i = 0; i < contador; i++) {
            if (ventas[i] != null && ventas[i].getId() == codigo) {
                return ventas[i];
            }
        }
        return null;
    }

    public void buscarVenta(int codigo) {
        Venta venta = obtenerVenta(codigo);
        if (venta != null) {
            System.out.println("======================= VENTA ========================");
            System.out.println("= Código: " + venta.getId()+"                        =");
            System.out.println("= Fecha: " + venta.getFechaVendida()+"               =");
            System.out.println("= Medicamento: " + venta.getNombreDelMedicamento()+" =");
            System.out.println("= Cantidad: " + venta.getCantidadDeProductos()+"     =");
            System.out.println("= Total: S/. " + venta.getPrecioDeVenta()+"          =");
            System.out.println("======================================================");
        } else {
            System.out.println("Venta no encontrada.");
        }
    }

    public void listarVentas() {
           if(contador == 0){
            System.out.println("No existen ventas registradas.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < contador; i++) {
            sb.append("\n==============================LISTA DE VENTAS==========================\n");
            sb.append("= Código:                                                               =").append(ventas[i].getId()).append("\n");
            sb.append("= Fecha:                                                                =").append(ventas[i].getFechaVendida()).append("\n");
            sb.append("= Medicamento:                                                          =").append(ventas[i].getNombreDelMedicamento()).append("\n");                                          
            sb.append("= Cantidad:                                                             =").append(ventas[i].getCantidadDeProductos()).append("\n");                                          
            sb.append("= Total: S/.                                                            =").append(ventas[i].getPrecioDeVenta()).append("\n");                                          
            sb.append("\n========================================================================");
        }
        System.out.println(sb.toString());
    }
    
    public String calcularImpuesto(){;
        double subtotal=0;
        double igv=0.0;
        double total=0;
        for(int i = 0;i<contador;i++){
            if(ventas[i] != null){
                subtotal = ventas[i].getPrecioDeVenta() * ventas[i].getCantidadDeProductos();
                igv = subtotal * 0.18;
                total = subtotal + igv;
            }else{
                return ""; 
            }
        }
        System.out.println("==============================");
        System.out.println("El subtotal es : "+subtotal);
        System.out.println("El IGV es : "+igv);
        System.out.println("El monto total sería: "+total);
        System.out.println("==============================");
        return "";
    }

    public void fechaMayorIngreso() {
        if (contador == 0) {
            System.out.println("No existen ventas registradas.");
            return;
        }
        LocalDate fechaMayor = ventas[0].getFechaVendida();
        double mayorIngreso = 0;
        for (int i = 0; i < contador; i++) {
            double suma = 0;
            for (int j = 0; j < contador; j++) {
                if (ventas[i].getFechaVendida().equals(ventas[j].getFechaVendida())) {
                    suma += ventas[j].getPrecioDeVenta();
                }
            }
            if (suma > mayorIngreso) {
                mayorIngreso = suma;
                fechaMayor = ventas[i].getFechaVendida();
            }
        }
        System.out.println("Fecha con mayor ingreso: " + fechaMayor);
        System.out.println("Ingreso total: S/. " + mayorIngreso);
    }
}

