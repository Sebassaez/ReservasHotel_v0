package org.iesalandalus.programacion.reservashotel.negocio;


import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reservas {


    private static final int CAPACIDAD_RESERVAS = 10;
    private Reserva[] coleccionReservas;
    private int capacidad;
    private int tamano;

    public Reservas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionReservas = new Reserva[capacidad];
    }

    public Reservas() {
        this(CAPACIDAD_RESERVAS);
    }

    public Reserva[] getColeccionReservas() {
        return copiaProfundaReservas(coleccionReservas);
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede insertar una reserva nula.");
        }
        if (buscarIndice(reserva) != -1) {
            throw new IllegalArgumentException("No se admiten reservas duplicadas.");
        }
        if (tamanoSuperado()) {
            throw new IllegalStateException("El tamaño de la colección ha sido superado.");
        }
        if (capacidadSuperada()) {
            throw new IllegalStateException("La capacidad de la colección ha sido superada.");
        }

        int indice = buscarIndice();
        coleccionReservas[indice] = reserva;
        tamano++;
    }

    public Reserva buscar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede buscar una reserva nula.");
        }
        int indice = buscarIndice(reserva);
        return (indice != -1) ? coleccionReservas[indice] : null;
    }

    public void borrar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede borrar una reserva nula.");
        }
        int indice = buscarIndice(reserva);
        if (indice != -1) {
            coleccionReservas[indice] = null;
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        }
    }

    private int buscarIndice() {
        for (int i = 0; i < coleccionReservas.length; i++) {
            if (coleccionReservas[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int buscarIndice(Reserva reserva) {
        for (int i = 0; i < tamano; i++) {
            if (reserva.equals(coleccionReservas[i])) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionReservas[i] = coleccionReservas[i + 1];
        }
        coleccionReservas[tamano - 1] = null;
    }

    private boolean tamanoSuperado() {
        return tamano >= capacidad;
    }

    private boolean capacidadSuperada() {
        return tamano > capacidad;
    }

    private Reserva[] copiaProfundaReservas(Reserva[] reservas) {
        return Arrays.copyOf(reservas, reservas.length);
    }
    public List<Reserva> getReservas(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede obtener reservas para un huésped nulo.");
        }

        List<Reserva> reservasHuesped = new ArrayList<>();
        for (Reserva reserva : coleccionReservas) {
            if (reserva != null && reserva.getHuesped().equals(huesped)) {
                reservasHuesped.add(reserva);
            }
        }
        return reservasHuesped;
    }

    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new IllegalArgumentException("No se puede obtener reservas para un tipo de habitación nulo.");
        }

        List<Reserva> reservasTipoHabitacion = new ArrayList<>();
        for (Reserva reserva : coleccionReservas) {
            if (reserva != null && reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                reservasTipoHabitacion.add(reserva);
            }
        }
        return reservasTipoHabitacion;
    }

    public List<Reserva> getReservasFuturas(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("No se puede obtener reservas futuras para una habitación nula.");
        }

        List<Reserva> reservasFuturas = new ArrayList<>();
        LocalDate hoy = LocalDate.now();
        for (Reserva reserva : coleccionReservas) {
            if (reserva != null && reserva.getHabitacion().equals(habitacion) && reserva.getFechaInicioReserva().isAfter(hoy)) {
                reservasFuturas.add(reserva);
            }
        }
        return reservasFuturas;
    }
}