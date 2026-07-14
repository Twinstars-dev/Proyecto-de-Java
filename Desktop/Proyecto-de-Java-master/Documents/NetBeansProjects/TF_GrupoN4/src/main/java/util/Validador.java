package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Clase con validaciones reutilizables para todo el sistema
 * Centraliza todas las reglas de validación en un solo lugar
 * @author GestionFarmacia
 */
public class Validador {
    
    // ===== CONSTANTES =====
    private static final int CAPACIDAD_MAXIMA_VENTAS = 100;
    private static final int CAPACIDAD_MAXIMA_PROVEEDORES = 50;
    private static final int CAPACIDAD_MAXIMA_MEDICAMENTOS = 100;
    private static final int CAPACIDAD_MAXIMA_CLIENTES = 100;
    private static final int STOCK_MINIMO = 5;
    private static final double PRECIO_MAXIMO = 1000000;
    private static final int CANTIDAD_MAXIMA = 1000;
    private static final int LONGITUD_MAXIMA_NOMBRE = 100;
    private static final int LONGITUD_MINIMA_TELEFONO = 7;
    private static final int LONGITUD_MAXIMA_TELEFONO = 15;
    private static final int TELEFONO_DIGITOS_EXACTOS = 9;
    
    // ===== SCANNER GLOBAL PARA MÉTODOS DE LECTURA =====
    private static Scanner sc = new Scanner(System.in);
    
    // ==================== VALIDACIONES DE ID ====================
    
    public static boolean validarId(int id, String entidad) {
        if (id <= 0) {
            System.out.println(" Error: El ID de " + entidad + " debe ser un numero positivo.");
            return false;
        }
        return true;
    }
    
    public static boolean validarIdVenta(int id) {
        return validarId(id, "venta");
    }
    
    public static boolean validarIdProveedor(int id) {
        return validarId(id, "proveedor");
    }
    
    public static boolean validarIdMedicamento(int id) {
        return validarId(id, "medicamento");
    }
    
    public static boolean validarIdCliente(int id) {
        return validarId(id, "cliente");
    }
    
    // ==================== VALIDACIONES DE CÓDIGO ====================
    
    public static boolean validarCodigo(String codigo, String entidad) {
        if (codigo == null || codigo.trim().isEmpty()) {
            System.out.println(" Error: El codigo de " + entidad + " no puede estar vacio.");
            return false;
        }
        if (codigo.length() > 20) {
            System.out.println(" Error: El codigo no puede exceder los 20 caracteres.");
            return false;
        }
        return true;
    }
    
    public static boolean validarCodigoMedicamento(String codigo) {
        return validarCodigo(codigo, "medicamento");
    }
    
    // ==================== VALIDACIONES DE NOMBRE ====================
    
    public static boolean validarNombre(String nombre, String entidad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println(" Error: El nombre de " + entidad + " no puede estar vacio.");
            return false;
        }
        if (nombre.length() > LONGITUD_MAXIMA_NOMBRE) {
            System.out.println(" Error: El nombre no puede exceder los " + LONGITUD_MAXIMA_NOMBRE + " caracteres.");
            return false;
        }
        // Validar que no tenga caracteres especiales (opcional)
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s.]+")) {
            System.out.println(" Error: El nombre solo debe contener letras, espacios y puntos.");
            return false;
        }
        return true;
    }
    
    public static boolean validarNombreMedicamento(String nombre) {
        return validarNombre(nombre, "medicamento");
    }
    
    public static boolean validarNombreProveedor(String nombre) {
        return validarNombre(nombre, "proveedor");
    }
    
    public static boolean validarNombreCliente(String nombre) {
        return validarNombre(nombre, "cliente");
    }
    
    // ==================== VALIDACIONES DE CANTIDAD ====================
    
    public static boolean validarCantidad(int cantidad) {
        if (cantidad <= 0) {
            System.out.println(" Error: La cantidad debe ser mayor a 0.");
            return false;
        }
        if (cantidad > CANTIDAD_MAXIMA) {
            System.out.println(" Error: La cantidad no puede exceder las " + CANTIDAD_MAXIMA + " unidades.");
            return false;
        }
        return true;
    }
    
    // ==================== VALIDACIONES DE PRECIO ====================
    
    public static boolean validarPrecio(double precio) {
        if (precio <= 0) {
            System.out.println(" Error: El precio debe ser mayor a 0.");
            return false;
        }
        if (precio > PRECIO_MAXIMO) {
            System.out.println(" Error: El precio no puede exceder S/. " + PRECIO_MAXIMO + ".");
            return false;
        }
        return true;
    }
    
    // ==================== VALIDACIONES DE STOCK ====================
    
    public static boolean validarStock(int stock) {
        if (stock < 0) {
            System.out.println(" Error: El stock no puede ser negativo.");
            return false;
        }
        if (stock > 1000000) {
            System.out.println(" Error: El stock no puede exceder 1,000,000 unidades.");
            return false;
        }
        return true;
    }
    
    public static boolean esStockBajo(int stock) {
        return stock < STOCK_MINIMO;
    }
    
    public static int getStockMinimo() {
        return STOCK_MINIMO;
    }
    
    public static boolean validarStockSuficiente(int stockActual, int cantidadVendida) {
        if (cantidadVendida <= 0) {
            System.out.println(" Error: La cantidad a vender debe ser mayor a 0.");
            return false;
        }
        if (stockActual < cantidadVendida) {
            System.out.println(" Error: Stock insuficiente. Disponible: " + stockActual + ", Solicitado: " + cantidadVendida);
            return false;
        }
        return true;
    }
    
    // ==================== VALIDACIONES DE FECHAS ====================
    
    public static boolean validarFechaVenta(LocalDate fecha) {
        if (fecha == null) {
            System.out.println(" Error: La fecha de venta no puede ser nula.");
            return false;
        }
        if (fecha.isAfter(LocalDate.now())) {
            System.out.println(" Error: La fecha de venta no puede ser futura.");
            return false;
        }
        return true;
    }
    
    public static boolean validarFechaVencimiento(LocalDate fecha) {
        if (fecha == null) {
            System.out.println(" Error: La fecha de vencimiento no puede ser nula.");
            return false;
        }
        return true;
    }
    
    public static boolean estaProximoAVencer(LocalDate fecha) {
        if (fecha == null) return false;
        LocalDate hoy = LocalDate.now();
        LocalDate limite = hoy.plusDays(30);
        return fecha.isBefore(limite) && fecha.isAfter(hoy);
    }
    
    public static boolean estaVencido(LocalDate fecha) {
        if (fecha == null) return false;
        return fecha.isBefore(LocalDate.now());
    }
    
    public static boolean validarFechaString(String fechaStr) {
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            System.out.println(" Error: La fecha no puede estar vacía.");
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(fechaStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println(" Error: Formato de fecha invalido. Use yyyy-MM-dd (ej: 2026-12-31)");
            return false;
        }
    }
    
    public static LocalDate parseFecha(String fechaStr) {
        if (!validarFechaString(fechaStr)) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(fechaStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println(" Error: No se pudo convertir la fecha.");
            return null;
        }
    }
    
    // ==================== VALIDACIONES DE RUC ====================
    
    public static boolean validarRuc(String ruc) {
        if (ruc == null || ruc.trim().isEmpty()) {
            System.out.println(" Error: El RUC no puede estar vacío.");
            return false;
        }
        // Eliminar espacios y guiones
        String rucLimpio = ruc.replaceAll("[\\s-]", "");
        if (!rucLimpio.matches("\\d{11}")) {
            System.out.println(" Error: El RUC debe tener 11 dígitos numéricos.");
            return false;
        }
        return true;
    }
    
    // ==================== VALIDACIONES DE TELÉFONO ====================
    
    public static boolean validarTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            System.out.println(" Error: El telefono no puede estar vacío.");
            return false;
        }
        // Eliminar espacios, guiones y paréntesis
        String telefonoLimpio = telefono.replaceAll("[\\s()\\-]", "");
        if (telefonoLimpio.length() < LONGITUD_MINIMA_TELEFONO || 
            telefonoLimpio.length() > LONGITUD_MAXIMA_TELEFONO) {
            System.out.println(" Error: El telefono debe tener entre " + 
                LONGITUD_MINIMA_TELEFONO + " y " + LONGITUD_MAXIMA_TELEFONO + " dígitos.");
            return false;
        }
        if (!telefonoLimpio.matches("\\d+")) {
            System.out.println(" Error: El telefono solo debe contener numeros.");
            return false;
        }
        return true;
    }
    
    public static boolean validarTelefonoCliente(int telefono) {
        String telefonoStr = String.valueOf(telefono);
        if (telefonoStr.length() != TELEFONO_DIGITOS_EXACTOS) {
            System.out.println(" Error: El telefono debe tener exactamente " + TELEFONO_DIGITOS_EXACTOS + " digitos.");
            return false;
        }
        return true;
    }
    
    // ==================== VALIDACIONES DE EMAIL ====================
    
    public static boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            System.out.println(" Error: El email no puede estar vacio.");
            return false;
        }
        // Validación básica de email
        if (!email.contains("@") || !email.contains(".")) {
            System.out.println(" Error: El email debe tener formato valido (ejemplo@dominio.com).");
            return false;
        }
        // Validar que no tenga espacios
        if (email.contains(" ")) {
            System.out.println(" Error: El email no debe contener espacios.");
            return false;
        }
        // Validar que tenga .com o .pe
        if (!email.endsWith(".com") && !email.endsWith(".pe")) {
            System.out.println(" Error: El email debe terminar en .com o .pe");
            return false;
        }
        return true;
    }
    
    // ==================== VALIDACIONES DE CATEGORÍA ====================
    
    public static boolean validarCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            System.out.println(" Error: La categoria no puede estar vacia.");
            return false;
        }
        if (categoria.length() > 50) {
            System.out.println(" Error: La categoria no puede exceder los 50 caracteres.");
            return false;
        }
        return true;
    }
    
    // ==================== VALIDACIONES DE TIPO DE MEDICAMENTO ====================
    
    public static boolean validarTipoMedicamento(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            System.out.println(" Error: El tipo de medicamento no puede estar vacio.");
            return false;
        }
        if (tipo.length() > 50) {
            System.out.println(" Error: El tipo de medicamento no puede exceder los 50 caracteres.");
            return false;
        }
        // Validar que solo tenga letras y espacios
        if (!tipo.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            System.out.println(" Error: El tipo de medicamento solo debe contener letras.");
            return false;
        }
        return true;
    }
    
    // ==================== VALIDACIONES DE CAPACIDAD ====================
    
    public static boolean validarEspacioDisponible(int contador, int capacidad, String entidad) {
        if (contador >= capacidad) {
            System.out.println(" Error: No hay espacio disponible para " + entidad + 
                ". Capacidad maxima: " + capacidad);
            return false;
        }
        return true;
    }
    
    public static boolean validarEspacioVentas(int contador) {
        return validarEspacioDisponible(contador, CAPACIDAD_MAXIMA_VENTAS, "ventas");
    }
    
    public static boolean validarEspacioProveedores(int contador) {
        return validarEspacioDisponible(contador, CAPACIDAD_MAXIMA_PROVEEDORES, "proveedores");
    }
    
    public static boolean validarEspacioMedicamentos(int contador) {
        return validarEspacioDisponible(contador, CAPACIDAD_MAXIMA_MEDICAMENTOS, "medicamentos");
    }
    
    public static boolean validarEspacioClientes(int contador) {
        return validarEspacioDisponible(contador, CAPACIDAD_MAXIMA_CLIENTES, "clientes");
    }
    
    // ==================== VALIDACIONES DE EXISTENCIA ====================

    public static boolean validarExistenElementos(int contador, String entidad) {
        return validarExistenElementos(contador, entidad, false);
    }

    public static boolean validarExistenElementos(int contador, String entidad, boolean silencioso) {
        if (contador == 0) {
            if (!silencioso) {
                System.out.println(" Error: No existen " + entidad + " registrados.");
            }
            return false;
        }
        return true;
    }
    public static boolean validarExistenMedicamentos(int contador) {
        return validarExistenElementos(contador, "medicamentos");
    }
    
    public static boolean validarExistenClientes(int contador) {
        return validarExistenElementos(contador, "clientes");
    }
    
    // ==================== VALIDACIÓN DE ID ÚNICO ====================
    
    public static boolean validarIdUnico(int id, String entidad, java.util.function.IntPredicate existe) {
        if (existe.test(id)) {
            System.out.println(" Error: Ya existe un " + entidad + " con el ID " + id);
            return false;
        }
        return true;
    }
    
    public static boolean validarIdClienteUnico(int id, java.util.function.IntPredicate existeCliente) {
        return validarIdUnico(id, "cliente", existeCliente);
    }
    
    // ==================== MÉTODOS DE LECTURA CON DO-WHILE ====================
    
    /**
     * Lee un entero con validación usando Scanner (do-while)
     * @param mensaje Mensaje a mostrar
     * @return Número entero válido
     */
    public static int leerEntero(String mensaje) {
        int numero;
        boolean valido;
        
        do {
            try {
                System.out.print(mensaje);
                numero = Integer.parseInt(sc.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("   Error: Debe ingresar un numero entero valido.");
                numero = 0;
                valido = false;
            }
        } while (!valido);
        
        return numero;
    }
    
    /**
     * Lee un entero positivo con validación (do-while)
     * @param mensaje Mensaje a mostrar
     * @return Número entero positivo válido
     */
    public static int leerEnteroPositivo(String mensaje) {
        int numero;
        boolean valido;
        
        do {
            numero = leerEntero(mensaje);
            if (numero <= 0) {
                System.out.println("   Error: Debe ingresar un numero mayor a 0.");
                valido = false;
            } else {
                valido = true;
            }
        } while (!valido);
        
        return numero;
    }
    
    /**
     * Lee un double con validación usando Scanner (do-while)
     * @param mensaje Mensaje a mostrar
     * @return Número decimal válido
     */
    public static double leerDouble(String mensaje) {
        double numero;
        boolean valido;
        
        do {
            try {
                System.out.print(mensaje);
                numero = Double.parseDouble(sc.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("   Error: Debe ingresar un numero decimal valido.");
                numero = 0;
                valido = false;
            }
        } while (!valido);
        
        return numero;
    }
    
    /**
     * Lee un double positivo con validación (do-while)
     * @param mensaje Mensaje a mostrar
     * @return Número decimal positivo válido
     */
    public static double leerDoublePositivo(String mensaje) {
        double numero;
        boolean valido;
        
        do {
            numero = leerDouble(mensaje);
            if (numero <= 0) {
                System.out.println("   Error: Ingrese un numero mayor a 0.");
                valido = false;
            } else {
                valido = true;
            }
        } while (!valido);
        
        return numero;
    }
    
    /**
     * Lee un texto no vacío (do-while)
     * @param mensaje Mensaje a mostrar
     * @return Texto ingresado
     */
    public static String leerTexto(String mensaje) {
        String texto;
        boolean valido;
        
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("   Error: El texto no puede estar vacio.");
                valido = false;
            } else {
                valido = true;
            }
        } while (!valido);
        
        return texto;
    }
    
    /**
     * Lee un teléfono de exactamente 9 dígitos (do-while)
     * @param mensaje Mensaje a mostrar
     * @return Teléfono válido
     */
    public static int leerTelefono(String mensaje) {
        int telefono;
        boolean valido;
        
        do {
            telefono = leerEntero(mensaje);
            String telefonoStr = String.valueOf(telefono);
            if (telefonoStr.length() != TELEFONO_DIGITOS_EXACTOS) {
                System.out.println("   Error: El telefono debe tener exactamente " + TELEFONO_DIGITOS_EXACTOS + " digitos.");
                valido = false;
            } else {
                valido = true;
            }
        } while (!valido);
        
        return telefono;
    }
    
    /**
     * Lee un email con validación de formato (do-while)
     * @param mensaje Mensaje a mostrar
     * @return Email válido
     */
    public static String leerEmail(String mensaje) {
        String email;
        boolean valido;
        
        do {
            System.out.print(mensaje);
            email = sc.nextLine().trim();
            
            if (email.isEmpty()) {
                System.out.println("   Error: El email no puede estar vacio.");
                valido = false;
            } else if (!email.contains("@")) {
                System.out.println("   Error: El email debe contener '@'.");
                valido = false;
            } else if (!email.contains(".")) {
                System.out.println("   Error: El email debe contener un punto.");
                valido = false;
            } else if (!email.endsWith(".com") && !email.endsWith(".pe")) {
                System.out.println("   Error: El email debe terminar en .com o .pe");
                valido = false;
            } else if (email.contains(" ")) {
                System.out.println("   Error: El email no debe contener espacios.");
                valido = false;
            } else {
                valido = true;
            }
        } while (!valido);
        
        return email;
    }
    
    /**
     * Lee una fecha con formato yyyy-MM-dd (do-while)
     * @param mensaje Mensaje a mostrar
     * @return Fecha válida
     */
    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean valido;
        
        do {
            try {
                System.out.print(mensaje);
                fecha = LocalDate.parse(sc.nextLine());
                valido = true;
            } catch (DateTimeParseException e) {
                System.out.println("   Error: Formato invalido. Use yyyy-MM-dd (ej: 2026-12-31)");
                valido = false;
            }
        } while (!valido);
        
        return fecha;
    }
    /**
 * Lee una fecha de vencimiento con validación de mínimo 30 días después de hoy
 * @param mensaje Mensaje a mostrar al usuario
 * @return Fecha de vencimiento válida
 */
    public static LocalDate leerFechaVencimiento(String mensaje) {
    LocalDate fecha = null;
    boolean valido;
    LocalDate hoy = LocalDate.now();
    LocalDate fechaMinima = hoy.plusDays(30);
    
    do {
        try {
            System.out.print(mensaje);
            fecha = LocalDate.parse(sc.nextLine());
            
            // Validar que no sea fecha pasada
            if (fecha.isBefore(hoy)) {
                System.out.println("   Error: La fecha de vencimiento no puede ser anterior a hoy.");
                valido = false;
                continue;
            }
            
            // Validar que sea al menos 30 días después
            if (fecha.isBefore(fechaMinima)) {
                System.out.println("   Error: La fecha de vencimiento debe ser al menos 30 dias despues de hoy.");
                System.out.println("   Fecha minima permitida: " + fechaMinima);
                valido = false;
                continue;
            }
            
            valido = true;
            
        } catch (DateTimeParseException e) {
            System.out.println("   Error: Formato invalido. Use yyyy-MM-dd (ej: 2026-12-31)");
            valido = false;
        }
    } while (!valido);
    
    return fecha;
    }
    /**
 * Valida que una fecha de vencimiento sea válida (mínimo 30 días después de hoy)
 * @param fecha Fecha a validar
 * @return true si es válida
 */

    /**
     * Lee una opción de menú dentro de un rango (do-while)
     * @param mensaje Mensaje a mostrar
     * @param min Valor mínimo permitido
     * @param max Valor máximo permitido
     * @return Opción seleccionada
     */
    public static int leerOpcion(String mensaje, int min, int max) {
        int opcion;
        boolean valido;
        
        do {
            opcion = leerEntero(mensaje);
            if (opcion < min || opcion > max) {
                System.out.println("   Error: La opcion debe estar entre " + min + " y " + max + ".");
                valido = false;
            } else {
                valido = true;
            }
        } while (!valido);
        
        return opcion;
    }
    
    /**
     * Lee una confirmación (S/N) (do-while)
     * @param mensaje Mensaje a mostrar
     * @return true si es S, false si es N
     */
    public static boolean leerConfirmacion(String mensaje) {
        String respuesta;
        boolean valido;
        
        do {
            System.out.print(mensaje);
            respuesta = sc.nextLine().trim().toLowerCase();
            
            if (respuesta.equals("s") || respuesta.equals("si")) {
                return true;
            } else if (respuesta.equals("n") || respuesta.equals("no")) {
                return false;
            } else {
                System.out.println("   Error: Ingrese 'S' para Si o 'N' para No.");
                valido = false;
            }
        } while (!valido);
        
        return false;
    }
    
    /**
     * Lee un RUC de 11 dígitos (do-while)
     * @param mensaje Mensaje a mostrar
     * @return RUC válido
     */
    public static String leerRuc(String mensaje) {
        String ruc;
        boolean valido;
        
        do {
            System.out.print(mensaje);
            ruc = sc.nextLine().trim().replaceAll("[\\s-]", "");
            
            if (ruc.isEmpty()) {
                System.out.println("   Error: El RUC no puede estar vacio.");
                valido = false;
            } else if (!ruc.matches("\\d{11}")) {
                System.out.println("   Error: El RUC debe tener 11 dígitos numericos.");
                valido = false;
            } else {
                valido = true;
            }
        } while (!valido);
        
        return ruc;
    }
    
    /**
 * Lee un código de medicamento de exactamente 6 dígitos
 * @param mensaje Mensaje a mostrar al usuario
 * @return Código de medicamento válido (int de 6 dígitos)
 */
public static int leerCodigoMedicamento(String mensaje) {
    int codigo = 0;
    boolean valido;
    
    do {
        try {
            System.out.print(mensaje);
            codigo = Integer.parseInt(sc.nextLine());
            
            // Validar que sea positivo
            if (codigo <= 0) {
                System.out.println("   Error: El codigo debe ser un numero positivo.");
                valido = false;
                continue;
            }
            
            // Validar que tenga exactamente 6 dígitos
            String codigoStr = String.valueOf(codigo);
            if (codigoStr.length() != 6) {
                System.out.println("   Error: El codigo debe tener exactamente 6 digitos.");
                System.out.println("   Ingresaste: " + codigoStr.length() + " dígito(s).");
                valido = false;
                continue;
            }
            
            valido = true;
            
        } catch (NumberFormatException e) {
            System.out.println("   Error: Debe ingresar un numero entero válido.");
            valido = false;
        }
    } while (!valido);
    
    return codigo;
    }
    
    /**
     * Lee un ID con validación de que sea positivo (do-while)
     * @param mensaje Mensaje a mostrar
     * @param entidad Nombre de la entidad para mensajes
     * @return ID válido
     */
    public static int leerId(String mensaje, String entidad) {
        int id;
        boolean valido;
        
        do {
            id = leerEntero(mensaje);
            if (id <= 0) {
                System.out.println("   Error: El ID de " + entidad + " debe ser un numero positivo.");
                valido = false;
            } else {
                valido = true;
            }
        } while (!valido);
        
        return id;
    }
    
    /**
     * Lee un ID de cliente con validación de que sea positivo (do-while)
     * @param mensaje Mensaje a mostrar
     * @return ID de cliente válido
     */
    public static int leerIdCliente(String mensaje) {
        return leerId(mensaje, "cliente");
    }
    
    /**
     * Lee un ID de medicamento con validación de que sea positivo (do-while)
     * @param mensaje Mensaje a mostrar
     * @return ID de medicamento válido
     */
    public static int leerIdMedicamento(String mensaje) {
        return leerId(mensaje, "medicamento");
    }
    
    /**
     * Lee un ID de proveedor con validación de que sea positivo (do-while)
     * @param mensaje Mensaje a mostrar
     * @return ID de proveedor válido
     */
    public static int leerIdProveedor(String mensaje) {
        return leerId(mensaje, "proveedor");
    }
    
    /**
     * Lee un ID de venta con validación de que sea positivo (do-while)
     * @param mensaje Mensaje a mostrar
     * @return ID de venta válido
     */
    public static int leerIdVenta(String mensaje) {
        return leerId(mensaje, "venta");
    }
}