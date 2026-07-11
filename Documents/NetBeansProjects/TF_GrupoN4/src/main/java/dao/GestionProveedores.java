/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Proveedor;

/**
 *
 * @author Anderson
 */
//Clase que gestiona los proveedores 
public class GestionProveedores {
//Atributos privados: una lista que contiene un objeto de la clsse provedor y un entero    
private Proveedor[] provedores;
private int contandor=0;
 //Constructor para soportar 50 proveedores
 public GestionProveedores(){
   this.provedores = new Proveedor[50];
   this.contandor=0;
}
 //
 public void registarProveedor(Proveedor p){
    if(contandor<provedores.length){
       provedores[contandor] = p;
       contandor++;
    }else{
     System.out.println("Se alcanzó el límite de proveedores");
    }   
 }
 //
 public void buscarProveedor(int codigo){
    //
    for(int i=0;i<contandor;i++){
    //    
    if(provedores[i]!=null && provedores[i].getId()== codigo){
        System.out.println("Provedor encontrado");
        System.out.println("Nombre: "+ provedores[i].getNombre());
        System.out.println("Email: "+ provedores[i].getEmail());
        System.out.println("Número de teléfono: "+ provedores[i].getTelefono());
        System.out.println("Yipo de medicamento: "+ provedores[i].getTipodemedicamento());
        return;
    }
     
}//   
     System.out.println("Proveedor no encontrado");
}
//
 public void listarProveedor(){
//  
    StringBuilder sb = new StringBuilder();
    for(int i=0;i<contandor;i++){
        sb.append("Mediacmento N°: ").append(provedores[i].getId());
        sb.append("Nombre del medicamento: ").append(provedores[i].getNombre());
        sb.append("Nombre del medicamento: ").append(provedores[i].getTelefono());
        sb.append("Nombre del medicamento: ").append(provedores[i].getEmail());
  }    
}

 public void actualizarProveedor(String NombreBuscar, int NuevoTeléfono, String NuevoEmail){
 boolean encontrado = false;
    for(int i=0;i<contandor;i++){
//
    if(provedores[i].getNombre().equalsIgnoreCase(NombreBuscar)){
      provedores[i].setTelefono(NuevoTeléfono);
      provedores[i].setEmail(NuevoEmail);
      encontrado=true;
    }
  }
//
    if(!encontrado){
      System.out.println("Error: El Proveedor "+NombreBuscar+" no existe");
    }
   }
//
 public void eliminarProveedor(int CodigoBuscar){
 boolean encontrado = false;
    for(int i=0;i<contandor;i++){
    if(provedores[i] != null && provedores[i].getId()==CodigoBuscar){
       encontrado= true;
//
    for(int j=0;j<contandor-1;j++){
       provedores[j] = provedores[j+1];
    }
       provedores[contandor-1] = null;
       contandor--;
       System.out.println("Proveedor eliminado");
       break;
    }   
 }
//
    if(!encontrado){
    System.out.println("Error: El Proveedor "+CodigoBuscar+" no existe");
    } 
 }
 public int cantidaddeProveedorRegistrados(){
   return contandor;
 }






}
