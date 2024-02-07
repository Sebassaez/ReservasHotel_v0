package org.iesalandalus.programacion.reservashotel;

import org.iesalandalus.programacion.reservashotel.dominio.*;
import org.iesalandalus.programacion.reservashotel.vista.Consola;
import org.iesalandalus.programacion.reservashotel.vista.Opcion;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static final int CAPACIDAD = 100;
    private Huesped[] huespedes;
    private Habitacion[] habitaciones;
    private Reserva[] reservas;
    private int numHuespedes;
    private int numHabitaciones;
    private int numReservas;

    public static void main(String[] args) {
        MainApp mainApp = new MainApp();


        Opcion opcionSeleccionada;
        do {
            Consola.mostrarMenu();
            opcionSeleccionada = Consola.elegirOpcion();
            mainApp.ejecutarOpcion(opcionSeleccionada);
        } while (opcionSeleccionada != Opcion.SALIR);

        System.out.println("¡Gracias por usar la aplicación de reservas de hotel!");
    }

    public MainApp() {
        huespedes = new Huesped[CAPACIDAD];
        habitaciones = new Habitacion[CAPACIDAD];
        reservas = new Reserva[CAPACIDAD];
        numHuespedes = 0;
        numHabitaciones = 0;
        numReservas = 0;
    }


    public void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_HUESPED:
                insertarHuesped();
                break;
            case BUSCAR_HUESPED:
                buscarHuesped();
                break;
            case BORRAR_HUESPED:
                borrarHuesped();
                break;
            case MOSTRAR_HUESPEDES:
                mostrarHuespedes();
                break;
            case INSERTAR_HABITACION:
                insertarHabitacion();
                break;
            case BUSCAR_HABITACION:
                buscarHabitacion();
                break;
            case BORRAR_HABITACION:
                borrarHabitacion();
                break;
            case MOSTRAR_HABITACIONES:
                mostrarHabitaciones();
                break;
            case INSERTAR_RESERVA:
                insertarReserva();
                break;
            case ANULAR_RESERVA:
                anularReserva();
                break;
            case MOSTRAR_RESERVAS:
                listarReservasHuesped();
                break;
            case CONSULTAR_DISPONIBILIDAD:
                ejecutarConsultarDisponibilidad();
                break;
            case SALIR:
                System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void insertarHuesped() {
        Huesped nuevoHuesped = Consola.leerHuesped();

        if (getNumElementosNoNulos(huespedes) < CAPACIDAD) {
            huespedes[numHuespedes] = nuevoHuesped;
            numHuespedes++;
            System.out.println("Huésped insertado correctamente.");
        } else {
            System.out.println("No hay capacidad para más huéspedes.");
        }
    }

    private void buscarHuesped() {
        System.out.print("Introduce el DNI del huésped a buscar: ");
        String dniBuscar = Entrada.cadena();
        Huesped huespedEncontrado = null;

        for (int i = 0; i < numHuespedes; i++) {
            if (huespedes[i] != null && huespedes[i].getDni().equals(dniBuscar)) {
                huespedEncontrado = huespedes[i];
                break;
            }
        }

        if (huespedEncontrado != null) {
            System.out.println("Huésped encontrado:\n" + huespedEncontrado);
        } else {
            System.out.println("No se encontró un huésped con ese DNI.");
        }
    }

    private void borrarHuesped() {
        System.out.print("Introduce el DNI del huésped a borrar: ");
        String dniBorrar = Entrada.cadena();
        boolean huespedBorrado = false;

        for (int i = 0; i < numHuespedes; i++) {
            if (huespedes[i] != null && huespedes[i].getDni().equals(dniBorrar)) {
                huespedes[i] = null;
                huespedBorrado = true;
                numHuespedes--;
                break;
            }
        }

        if (huespedBorrado) {
            System.out.println("Huésped borrado correctamente.");
        } else {
            System.out.println("No se encontró un huésped con ese DNI.");
        }
    }

    private void mostrarHuespedes() {
        if (getNumElementosNoNulos(huespedes) > 0) {
            System.out.println("Listado de Huéspedes:");
            for (int i = 0; i < numHuespedes; i++) {
                if (huespedes[i] != null) {
                    System.out.println(huespedes[i]);
                }
            }
        } else {
            System.out.println("No hay huéspedes registrados.");
        }
    }

    private void insertarHabitacion() {
        Habitacion nuevaHabitacion = Consola.leerHabitacion();

        if (getNumElementosNoNulos(habitaciones) < CAPACIDAD) {
            habitaciones[numHabitaciones] = nuevaHabitacion;
            numHabitaciones++;
            System.out.println("Habitación insertada correctamente.");
        } else {
            System.out.println("No hay capacidad para más habitaciones.");
        }
    }

    private void buscarHabitacion() {
        System.out.print("Introduce el número de puerta de la habitación a buscar: ");
        int puertaBuscar = Entrada.entero();
        System.out.print("Introduce el número de planta de la habitación a buscar: ");
        int plantaBuscar = Entrada.entero();
        Habitacion habitacionEncontrada = null;

        for (int i = 0; i < numHabitaciones; i++) {
            if (habitaciones[i] != null && habitaciones[i].getPuerta() == puertaBuscar
                    && habitaciones[i].getPlanta() == plantaBuscar) {
                habitacionEncontrada = habitaciones[i];
                break;
            }
        }

        if (habitacionEncontrada != null) {
            System.out.println("Habitación encontrada:\n" + habitacionEncontrada);
        } else {
            System.out.println("No se encontró una habitación con ese número de puerta y planta.");
        }
    }

    private void borrarHabitacion() {
        System.out.print("Introduce el número de puerta de la habitación a borrar: ");
        int puertaBorrar = Entrada.entero();
        System.out.print("Introduce el número de planta de la habitación a borrar: ");
        int plantaBorrar = Entrada.entero();
        boolean habitacionBorrada = false;

        for (int i = 0; i < numHabitaciones; i++) {
            if (habitaciones[i] != null && habitaciones[i].getPuerta() == puertaBorrar
                    && habitaciones[i].getPlanta() == plantaBorrar) {
                habitaciones[i] = null;
                habitacionBorrada = true;
                numHabitaciones--;
                break;
            }
        }

        if (habitacionBorrada) {
            System.out.println("Habitación borrada correctamente.");
        } else {
            System.out.println("No se encontró una habitación con ese número de puerta y planta.");
        }
    }

    private void mostrarHabitaciones() {
        if (getNumElementosNoNulos(habitaciones) > 0) {
            System.out.println("Listado de Habitaciones:");
            for (int i = 0; i < numHabitaciones; i++) {
                if (habitaciones[i] != null) {
                    System.out.println(habitaciones[i]);
                }
            }
        } else {
            System.out.println("No hay habitaciones registradas.");
        }
    }


    private void insertarReserva() {
        Reserva nuevaReserva = Consola.leerReserva();

        // Verificar disponibilidad antes de insertar
        if (getNumElementosNoNulos(reservas) < CAPACIDAD && consultarDisponibilidad(nuevaReserva)) {
            reservas[numReservas] = nuevaReserva;
            numReservas++;
            System.out.println("Reserva insertada correctamente.");
        } else {
            System.out.println("No hay capacidad para más reservas o no hay disponibilidad en el período indicado.");
        }
    }

    private boolean consultarDisponibilidad(Reserva reserva) {
        for (Reserva reservaExistente : reservas) {
            if (reservaExistente != null && reservaExistente.getHabitacion().equals(reserva.getHabitacion())
                    && ((reservaExistente.getFechaInicioReserva().isBefore(reserva.getFechaInicioReserva())
                    && reservaExistente.getFechaFinReserva().isAfter(reserva.getFechaInicioReserva()))
                    || (reservaExistente.getFechaInicioReserva().isBefore(reserva.getFechaFinReserva())
                    && reservaExistente.getFechaFinReserva().isAfter(reserva.getFechaFinReserva()))
                    || (reservaExistente.getFechaInicioReserva().isEqual(reserva.getFechaInicioReserva())
                    && reservaExistente.getFechaFinReserva().isEqual(reserva.getFechaFinReserva())))) {
                return false;
            }
        }
        return true;
    }

    private void listarReservasHuesped() {
        System.out.print("Introduce el DNI del huésped para listar sus reservas: ");
        String dniBuscar = Entrada.cadena();

        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getHuesped().getDni().equals(dniBuscar)) {
                System.out.println(reserva);
            }
        }
    }

    private void listarReservasTipoHabitacion() {
        TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();

        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                System.out.println(reserva);
            }
        }
    }


    private Reserva[] getReservasAnulables(String dni) {
        List<Reserva> anulables = new ArrayList<>();
        LocalDate fechaActual = LocalDate.now();

        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getHuesped().getDni().equals(dni) &&
                    reserva.getFechaInicioReserva().isAfter(fechaActual)) {
                anulables.add(reserva);
            }
        }

        return anulables.toArray(new Reserva[0]);
    }

    private void anularReserva() {
        System.out.print("Introduce el DNI del huésped para anular una reserva: ");
        String dniBuscar = Entrada.cadena();
        Reserva[] reservasAnulables = getReservasAnulables(dniBuscar);

        if (reservas.length == 0) {
            System.out.println("No hay reservas anulables para ese huésped.");
        } else if (reservasAnulables.length == 1) {
            System.out.print("¿Desea anular la siguiente reserva? (S/N)\n" + reservas[0]);
            char opcion = Entrada.caracter();
            if (opcion == 'S' || opcion == 's') {
                anularReserva();
                System.out.println("Reserva anulada con éxito.");
            } else {
                System.out.println("Anulación de reserva cancelada.");
            }
        } else {
            System.out.println("Seleccione la reserva a anular (1-" + reservasAnulables.length + "):");
            for (int i = 0; i < reservasAnulables.length; i++) {
                System.out.println((i + 1) + ". " + reservasAnulables[i]);
            }
            int opcion = Entrada.entero();
            if (opcion >= 1 && opcion <= reservasAnulables.length) {
                anularReserva();
                System.out.println("Reserva anulada con éxito.");
            } else {
                System.out.println("Opción no válida. Anulación de reserva cancelada.");
            }
        }
    }

    public void mostrarReservas() {
        System.out.println("Lista de reservas:");

        // Verificar si hay reservas almacenadas
        if (getNumElementosNoNulos(reservas) == 0) {
            System.out.println("No hay reservas almacenadas.");
        } else {
            // Mostrar todas las reservas almacenadas
            for (Reserva reserva : reservas) {
                if (reserva != null) {
                    System.out.println(reserva);
                }
            }
        }
    }

    private int getNumElementosNoNulos(Object[] array) {
        int count = 0;
        for (Object element : array) {
            if (element != null) {
                count++;
            }
        }
        return count;
    }

    private Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion != null && habitacion.getTipoHabitacion() == tipoHabitacion &&
                    consultarDisponibilidad(habitacion, fechaInicio, fechaFin)) {
                return habitacion;
            }
        }
        throw new NullPointerException("No hay habitaciones disponibles del tipo " + tipoHabitacion +
                " para el rango de fechas proporcionado.");
    }

    private boolean consultarDisponibilidad(Habitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getHabitacion().equals(habitacion) &&
                    ((reserva.getFechaInicioReserva().isBefore(fechaInicio) && reserva.getFechaFinReserva().isAfter(fechaInicio))
                            || (reserva.getFechaInicioReserva().isBefore(fechaFin) && reserva.getFechaFinReserva().isAfter(fechaFin))
                            || (reserva.getFechaInicioReserva().isEqual(fechaInicio) && reserva.getFechaFinReserva().isEqual(fechaFin)))) {
                return false;
            }
        }
        return true;
    }

    private void ejecutarConsultarDisponibilidad() {
        Habitacion habitacion = Consola.leerHabitacion();
        LocalDate fechaInicio = Consola.leerFecha("Introduce la fecha de inicio (YYYY-MM-DD): ");
        LocalDate fechaFin = Consola.leerFecha("Introduce la fecha de fin (YYYY-MM-DD): ");

        if (consultarDisponibilidad(habitacion, fechaInicio, fechaFin)) {
            System.out.println("La habitación está disponible en el período indicado.");
        } else {
            System.out.println("La habitación no está disponible en el período indicado.");
        }
    }
}
