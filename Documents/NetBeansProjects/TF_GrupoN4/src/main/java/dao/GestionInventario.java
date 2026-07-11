package dao;

import model.Medicamento;
/**
 *
 * @author Anderson
 */
//Clase que gestiona el inventario de medicamentos
public class GestionInventario {
    // Atributos privados: un entero y una lista que contiene objetos de la clase Medicamento
    private final Medicamento[] medicamentos;
    // Controla cuántos productos reales hay
    private int contador=0;
    
    //Constructor para soportar 100 medicamentos
    public GestionInventario(){
        this.medicamentos = new Medicamento[100];  
        this.contador = 0;
    }
    // Método void que agrega un objeto Medicamento a la lista medicamentos
    public void agregarMedicamento(Medicamento m){
        if (contador < medicamentos.length) {
            medicamentos[contador] = m;
            contador++;
        } else {
            System.out.println("Se alcanzó el límite de medicamentos");
        }
    }
    //Método que busca en el arreglo y devuelve el nombre del medicamento
    public String buscarMedicamento(String codigo){
        for (int i =0;i<contador;i++) {
            if (medicamentos[i] !=null && medicamentos[i].getCodigo().equalsIgnoreCase(codigo)){
                return medicamentos[i].getNombre();
            }
        }
        return "Medicamento no existente";
    }
    //Método que recorre el arreglo y valida que no este vacío
    public void listarMedicamentos(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<contador;i++){
            sb.append("Mediacmento N°: ").append(medicamentos[i].getCodigo());
            sb.append("Nombre del medicamento: ").append(medicamentos[i].getNombre());
            sb.append("Nombre del laboratorio: ").append(medicamentos[i].getLaboratorio());
            sb.append("Stock: ").append(medicamentos[i].getStock());
            sb.append("Precio: ").append(medicamentos[i].getPrecio());
            sb.append("Fecha de vencimiento: ").append(medicamentos[i].getFechaDeVencimiento());
            sb.append("Categoria: ").append(medicamentos[i].getCategoria());        
        }
    }   
    // Método que actualiza los datos de un medicamento
    public void actualizarMedicamento(String nombreBuscar, double nuevoPrecio, int nuevoStock){   
        boolean encontrado= false;
        for (int i = 0; i < medicamentos.length; i++){
        // Verificamos si esta vacio, comparamos el nombre y usamos los setters.
            if (medicamentos[i].getNombre().equalsIgnoreCase(nombreBuscar)) {
                medicamentos[i].setStock(nuevoStock);
                medicamentos[i].setPrecio(nuevoPrecio);
                encontrado=true;
                break;  //Para detener el bucle al encontrar el medicamento
            }
        }
           
        //Verificamos si existe el medicamento
        if (!encontrado){
             System.out.println("Error: El medicamento '" + nombreBuscar + "' no existe.");
        }
        listarMedicamentos();        
    }

    //Método que elimina un medicamento desde su código
    public void eliminarMedicamento(String codigoBuscar){
        boolean encontrado = false;
        for (int i = 0; i < contador; i++) {
            if (medicamentos[i] != null && medicamentos[i].getCodigo().equals(codigoBuscar)) {
                encontrado = true;
                break;
            }
            // Recorremos desde la posición eliminada y movemos a la izquierda los elementos
            for (int j = i; j < contador- 1; j++){
                medicamentos[j] = medicamentos[j + 1];
            }
        }
        // El último espacio del arreglo debe quedar limpio
        medicamentos[contador- 1] = null;
        contador--;                
        System.out.println("Medicamento eliminado");    
         //Valida que haya encontrado el medicamento
        if (!encontrado){
            System.out.println("Error: Código " + codigoBuscar + " no encontrado.");
        }
    }
    public void ventadeMedicamento(String nombreBuscar, int cantidadVendida){
        boolean encontrado = false;
        for(int i=0;i<contador;i++){
            if(medicamentos[i]!=null && medicamentos[i].getNombre().equalsIgnoreCase(nombreBuscar)){
                encontrado = true;
                if(cantidadVendida<=medicamentos[i].getStock()){
                    int nuevoStock=medicamentos[i].getStock() - cantidadVendida;
                    medicamentos[i].setStock(nuevoStock);
                    System.out.println("Venta realizada correctamente.");
                }else{
                    System.out.println("No hay suficiente stock.");
                }
            break;
            }
        }
    
        if(!encontrado){
            System.out.println("El medicamento no existe.");
        }   
        System.out.println("\nInventario actualizado:");
        listarMedicamentos();
    }

    public Medicamento[] obtenerReporteStockBajo(int limiteAlerta){
        // Creamos un arreglo temporal
        Medicamento[] resultadoTemporal = new Medicamento[contador];
        int contadorBajos = 0;
        // Filtramos los medicamentos con stock bajo
        for (int i = 0; i < contador; i++){
            if (medicamentos[i].getStock() < limiteAlerta) {
                resultadoTemporal[contadorBajos] = medicamentos[i];
                 contadorBajos++;
            }
        }
        // Creamos el arreglo final con la cantidad de medicamenos con stocks bajos
        Medicamento[] listaFinal = new Medicamento[contadorBajos];
        for (int i = 0; i < contadorBajos; i++){
            listaFinal[i] = resultadoTemporal[i];
        }
        return listaFinal;   
    }
}