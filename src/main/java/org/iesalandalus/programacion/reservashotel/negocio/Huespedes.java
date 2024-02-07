package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.Huesped;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class Huespedes {

    private static final int CAPACIDAD_HUESPEDES = 10;
    private Huesped[] coleccionHuespedes;
    private int capacidad;
    private int tamano;


    public Huesped[] getColeccionHuespedes() {
        return copiaProfundaHuespedes(coleccionHuespedes);
    }

    public Huespedes( int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionHuespedes = new Huesped[capacidad];
    }

    public Huespedes() {
        this(CAPACIDAD_HUESPEDES);
    }
    public Huesped[] get () {
        return copiaProfundaHuespedes(coleccionHuespedes);
    }

    public int getTamano () {
        return tamano;
    }

    public int getCapacidad () {
        return capacidad;
    }

    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede insertar un huésped nulo.");
        }
        if (indexOfHuesped(huesped) != -1) {
            throw new IllegalArgumentException("No se admiten huéspedes duplicados.");
        }
        if (tamanoSuperado()) {
            throw new IllegalArgumentException("El tamaño de la colección ha sido superado.");
        }
        if (capacidadSuperada()) {
            throw new IllegalArgumentException("La capacidad de la colección ha sido superada.");
        }

        int indice = buscarIndice();
        coleccionHuespedes[indice] = huesped;
        tamano++;
    }

    public Huesped buscar (Huesped huesped){
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede buscar un huésped nulo.");
        }
        int indice = indexOfHuesped(huesped);
        return (indice != -1) ? coleccionHuespedes[indice] : null;
    }

    public void borrar (Huesped huesped){
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede borrar un huésped nulo.");
        }
        int indice = indexOfHuesped(huesped);
        if (indice != -1) {
            coleccionHuespedes[indice] = null;
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        }
    }

    private int buscarIndice () {
        for (int i = 0; i < coleccionHuespedes.length; i++) {
            if (coleccionHuespedes[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarUnaPosicionHaciaIzquierda ( int indice){
        for (int i = indice; i < tamano - 1; i++) {
            coleccionHuespedes[i] = coleccionHuespedes[i + 1];
        }
        coleccionHuespedes[tamano - 1] = null;
    }

    private int indexOfHuesped (Huesped huesped){
        for (int i = 0; i < tamano; i++) {
            if (huesped.equals(coleccionHuespedes[i])) {
                return i;
            }
        }
        return -1;
    }

    private boolean tamanoSuperado () {
        return tamano >= capacidad;
    }

    private boolean capacidadSuperada () {
        return tamano > capacidad;
    }

    private Huesped[] copiaProfundaHuespedes (Huesped[]get) {
        return Arrays.copyOf(get, get.length);
    }
}