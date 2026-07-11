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
 * @author Anderson
 */
public class GestionVentas{   
private Venta[] ventas;
private int contador;

public GestionVentas() {
    this.ventas = new Venta[50];
    this.contador = 0;
}

public void listarVentas(){
    StringBuilder sb = new StringBuilder();
    Cliente cl = new Cliente();
    for(int i=0;i<contador;i++){
           sb.append("Venta N°: ").append(ventas[i].getId()).append("\n");
           sb.append("Cliente: ").append(cl.getNombre()).append("\n");
           sb.append("Monto: ").append(ventas[i].getPrecioDeVenta()).append("\n\n");
    }
    System.out.println(sb.toString());
    System.out.println("Cantidad de ventas realizadas: "+ contador);
}

public void buscarVenta(int codigo){
    for(int i=0; i<contador;i++){  
       if(ventas[i]!=null && ventas[i].getId()==codigo){
           System.out.println("Venta encontrada");    
           System.out.println("Fecha del medicamento vendido: "+ventas[i].getFechaVendida());    
           System.out.println("Nombre del medicamento: "+ ventas[i].getNombreDelMedicamento());    
           System.out.println("Cantidad comprada: "+ventas[i].getCantidadDeProductos());    
           System.out.println("Monto total pagado: "+ventas[i].getPrecioDeVenta());    
       } 
    } 
}

public double calcularIngresosTotales(){
  double ingresototal=0.0;
  for(int i=0; i<contador;i++){
      if(ventas[i]!=null){
         ingresototal+=ventas[i].getPrecioDeVenta(); 
      }
  }
    return ingresototal;
} 

public void fechaMayorIngreso() {
    if (contador == 0){
        System.out.println("No hay ventas registradas.");
        return;
    }
    LocalDate fechaMayor = ventas[0].getFechaVendida();
    double mayorIngreso = 0;
    for(int i=0; i<contador;i++){
    double suma = 0;
        for(int j=0;j<contador;j++) {
            if(ventas[i].getFechaVendida().equals(ventas[j].getFechaVendida())) {
                 suma+=ventas[j].getPrecioDeVenta();
            }
        }
    if(suma>mayorIngreso) {
    mayorIngreso=suma;
    fechaMayor=ventas[i].getFechaVendida();
    }
}
    System.out.println("Fecha con mayor ingreso: " + fechaMayor);
    System.out.println("Ingreso total: S/. " + mayorIngreso);
}


}
