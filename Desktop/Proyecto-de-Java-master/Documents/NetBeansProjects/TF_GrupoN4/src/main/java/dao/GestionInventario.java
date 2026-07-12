package dao;

import model.Medicamento;
import java.time.LocalDate;
/**
 *
 * @author Anderson
 */
//Clase que gestiona el inventario de medicamentos
public class GestionInventario {
    private Medicamento[] medicamentos;
    private int contador;

    public GestionInventario() {
        medicamentos = new Medicamento[100];
        contador = 0;
    }

    public void agregarMedicamento(Medicamento m) {
        if (contador < medicamentos.length) {
            medicamentos[contador] = m;
            contador++;
            System.out.println("Medicamento registrado correctamente.");
        } else {
            System.out.println("Inventario lleno.");
        }
    }

    public String buscarMedicamento(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (medicamentos[i] != null &&
                medicamentos[i].getCodigo().equalsIgnoreCase(codigo)) {
                return medicamentos[i].getNombre();
            }
        }
        return "Medicamento no encontrado.";
    }

    public Medicamento obtenerMedicamento(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (medicamentos[i] != null &&
                medicamentos[i].getCodigo().equalsIgnoreCase(codigo)) {
                return medicamentos[i];
            }
        }
        return null;
    }

    public void listarMedicamentos() {

        if (contador == 0) {
            System.out.println("No existen medicamentos registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < contador; i++) {

            sb.append("\n==============================\n");
            sb.append("Código: ")
              .append(medicamentos[i].getCodigo())
              .append("\n");

            sb.append("Nombre: ")
              .append(medicamentos[i].getNombre())
              .append("\n");

            sb.append("Laboratorio: ")
              .append(medicamentos[i].getLaboratorio())
              .append("\n");

            sb.append("Stock: ")
              .append(medicamentos[i].getStock())
              .append("\n");

            sb.append("Precio: S/. ")
              .append(medicamentos[i].getPrecio())
              .append("\n");

            sb.append("Vencimiento: ")
              .append(medicamentos[i].getFechaDeVencimiento())
              .append("\n");

            sb.append("Categoría: ")
              .append(medicamentos[i].getCategoria())
              .append("\n");

            sb.append("Receta: ")
              .append(medicamentos[i].isRequiereReceta())
              .append("\n");
        }

        System.out.println(sb.toString());
    }

    public void actualizarMedicamento(String nombreBuscar,
                                      double nuevoPrecio,
                                      int nuevoStock) {

        boolean encontrado = false;

        for (int i = 0; i < contador; i++) {

            if (medicamentos[i] != null &&
                medicamentos[i].getNombre()
                .equalsIgnoreCase(nombreBuscar)) {

                medicamentos[i].setPrecio(nuevoPrecio);
                medicamentos[i].setStock(nuevoStock);

                encontrado = true;

                System.out.println("Medicamento actualizado.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Medicamento no encontrado.");
        }
    }

    public void eliminarMedicamento(String codigoBuscar) {

        boolean encontrado = false;

        for (int i = 0; i < contador; i++) {

            if (medicamentos[i] != null &&
                medicamentos[i].getCodigo()
                .equalsIgnoreCase(codigoBuscar)) {

                encontrado = true;

                for (int j = i; j < contador - 1; j++) {
                    medicamentos[j] = medicamentos[j + 1];
                }

                medicamentos[contador - 1] = null;
                contador--;

                System.out.println("Medicamento eliminado.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Código no encontrado.");
        }
    }
       public void ventaMedicamento(String nombreBuscar, int cantidadVendida) {

        boolean encontrado = false;

        for (int i = 0; i < contador; i++) {

            if (medicamentos[i] != null &&
                medicamentos[i].getNombre().equalsIgnoreCase(nombreBuscar)) {

                encontrado = true;

                if (cantidadVendida <= medicamentos[i].getStock()) {

                    medicamentos[i].setStock(
                            medicamentos[i].getStock() - cantidadVendida);

                    System.out.println("Venta realizada correctamente.");

                } else {

                    System.out.println("Stock insuficiente.");

                }

                break;
            }
        }

        if (!encontrado) {
            System.out.println("Medicamento no encontrado.");
        }
    }

    public Medicamento[] obtenerReporteStockBajo(int limiteAlerta) {

        Medicamento[] temporal = new Medicamento[contador];

        int cantidad = 0;

        for (int i = 0; i < contador; i++) {

            if (medicamentos[i] != null &&
                medicamentos[i].getStock() < limiteAlerta) {

                temporal[cantidad] = medicamentos[i];
                cantidad++;
            }
        }

        Medicamento[] resultado = new Medicamento[cantidad];

        for (int i = 0; i < cantidad; i++) {
            resultado[i] = temporal[i];
        }

        return resultado;
    }

    public void mostrarMedicamentosPorVencer() {

        LocalDate hoy = LocalDate.now();

        boolean existe = false;

        for (int i = 0; i < contador; i++) {

            if (medicamentos[i] != null) {

                if (medicamentos[i].getFechaDeVencimiento()
                        .isBefore(hoy.plusMonths(1))) {

                    System.out.println(medicamentos[i]);
                    existe = true;

                }

            }

        }

        if (!existe) {
            System.out.println("No existen medicamentos próximos a vencer.");
        }

    }

    public int cantidadMedicamentos() {
        return contador;
    }

}
