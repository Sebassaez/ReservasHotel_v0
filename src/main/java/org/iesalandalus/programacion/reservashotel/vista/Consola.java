package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Regimen;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.LocalTime;


public class Consola {


    private Consola() {
    }

    public static void mostrarMenu() {
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {
        int opcion;
        do {
            System.out.print("Elige una opci�n: ");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion >= Opcion.values().length);
        return Opcion.values()[opcion];
    }

    public static Huesped leerHuesped() {
        System.out.println("Introduce los datos del hu�sped:");

        System.out.print("Nombre: ");
        String nombre = Entrada.cadena();

        System.out.print("DNI: ");
        String dni = Entrada.cadena();

        System.out.println("Telefono: ");
        String telefono = Entrada.cadena();

        System.out.println("Correo electronico: ");
        String correo = Entrada.cadena();

        System.out.println("Fecha de nacimiento (formato " + Huesped.FORMATO_FECHA + "): ");
        LocalDate fechaNacimiento = LocalDate.parse(Entrada.cadena());


        // Crear una instancia de Huesped con los datos le�dos
        try {
            return new Huesped(nombre, dni, telefono, correo, fechaNacimiento);
        } catch (IllegalArgumentException e) {
            // En caso de datos incorrectos, propagar la excepci�n
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public static Huesped leerClientePorDni() {
        System.out.println("Introduce el DNI del hu�sped:");

        try {
            System.out.print("DNI: ");
            String dni = Entrada.cadena();

            // Solicitar y leer el nombre ficticio desde la consola
            System.out.print("Nombre ficticio: ");
            String nombreFicticio = Entrada.cadena();

            Huesped huesped = new Huesped(nombreFicticio, dni, "correo@ejemplo.com", "666666666", LocalDate.of(2000, 1, 1));

            return huesped;
        } catch (IllegalArgumentException e) {
            // En caso de DNI incorrecto, propagar la excepci�n
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }


    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean fechaValida = false;

        do {
            try {
                System.out.print(mensaje);
                String fechaStr = Entrada.cadena();
                fecha = LocalDate.parse(Huesped.FORMATO_FECHA);
                fechaValida = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Formato de fecha incorrecto. Introduce la fecha en formato (dd/MM/yyyy)");
            }
        } while (!fechaValida);

        return fecha;
    }


    public static Habitacion leerHabitacion() {
        System.out.println("Introduce los datos de la habitaci�n:");

        System.out.print("Planta: ");
        int planta = Entrada.entero();

        System.out.print("Puerta: ");
        int puerta = Entrada.entero();

        System.out.print("Precio: ");
        double precio = Entrada.real();

        TipoHabitacion tipoHabitacion = leerTipoHabitacion();

        // Crear una instancia de Habitacion con los datos le�dos
        try {
            return new Habitacion(planta, puerta, precio, tipoHabitacion);
        } catch (IllegalArgumentException e) {
            // En caso de datos incorrectos, propagar la excepci�n
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }


    public static Habitacion leerHabitacionPorIdentificador() {
        System.out.println("Introduce el n�mero de planta y puerta de la habitaci�n:");

        System.out.print("N�mero de planta: ");
        int planta = Entrada.entero();

        System.out.print("N�mero de puerta: ");
        int puerta = Entrada.entero();

        // Crear una instancia de Habitacion ficticia con los datos le�dos
        try {
            return new Habitacion(planta, puerta, 80.00, TipoHabitacion.SIMPLE);
        } catch (IllegalArgumentException e) {
            // En caso de datos incorrectos, propagar la excepci�n
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }


    public static TipoHabitacion leerTipoHabitacion() {
        TipoHabitacion tipoHabitacion = null;
        boolean tipoValido = false;

        do {
            System.out.println("Elija el tipo de habitaci�n:");
            System.out.println("1. SIMPLE");
            System.out.println("2. DOBLE");
            System.out.println("3. TRIPLE");
            System.out.println("4. SUITE");

            int opcion = Entrada.entero();

            switch (opcion) {
                case 1:
                    tipoHabitacion = TipoHabitacion.SIMPLE;
                    tipoValido = true;
                    break;
                case 2:
                    tipoHabitacion = TipoHabitacion.DOBLE;
                    tipoValido = true;
                    break;
                case 3:
                    tipoHabitacion = TipoHabitacion.TRIPLE;
                    tipoValido = true;
                    break;
                case 4:
                    tipoHabitacion = TipoHabitacion.SUITE;
                    tipoValido = true;
                    break;
                default:
                    System.out.println("Opci�n no v�lida. Int�ntelo de nuevo.");
                    break;
            }
        } while (!tipoValido);

        return tipoHabitacion;
    }

    public static Regimen leerRegimen() {
        Regimen regimen = null;
        boolean regimenValido = false;

        do {
            System.out.println("Elija el tipo de r�gimen:");
            System.out.println("1. Solo Alojamiento");
            System.out.println("2. Alojamiento y Desayuno");
            System.out.println("3. Media Pensi�n");
            System.out.println("4. Pensi�n Completa");

            try {
                int opcion = Entrada.entero();

                switch (opcion) {
                    case 1:
                        regimen = Regimen.SOLO_ALOJAMIENTO;
                        regimenValido = true;
                        break;
                    case 2:
                        regimen = Regimen.ALOJAMIENTO_DESAYUNO;
                        regimenValido = true;
                        break;
                    case 3:
                        regimen = Regimen.MEDIA_PENSION;
                        regimenValido = true;
                        break;
                    case 4:
                        regimen = Regimen.PENSION_COMPLETA;
                        regimenValido = true;
                        break;
                    default:
                        System.out.println("Opci�n no v�lida. Int�ntelo de nuevo.");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Debe ingresar un n�mero entero. Int�ntelo de nuevo.");
                // Limpiar el buffer de la consola (no es necesario aqu�)
            }
        } while (!regimenValido);

        return regimen;
    }


    public static Reserva leerReserva() {
        System.out.println("Introduce los datos de la reserva:");

        // Obtener datos para la reserva
        Habitacion habitacion = leerHabitacionPorIdentificador();
        LocalDate fechaInicio = leerFecha("Fecha de inicio (dd/MM/yyyy): ");
        LocalDate fechaFin = leerFecha("Fecha de fin (dd/MM/yyyy): ");
        Huesped huesped = leerHuesped();
        Regimen regimen = leerRegimen();

        // Crear una instancia de Reserva con los datos le�dos
        try {
            Reserva reserva = new Reserva(habitacion, fechaInicio, fechaFin, 0, regimen);
            reserva.setCheckin(LocalDate.now());
            reserva.setCheckout(LocalTime.now());
            return reserva;
        } catch (IllegalArgumentException e) {
            // En caso de datos incorrectos, propagar la excepci�n
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }
}