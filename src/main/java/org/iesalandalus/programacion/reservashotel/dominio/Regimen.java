package org.iesalandalus.programacion.reservashotel.dominio;

public enum Regimen {
    SOLO_ALOJAMIENTO("Solo Alojamiento", 0),
    ALOJAMIENTO_DESAYUNO("Alojamiento y Desayuno", 15),
    MEDIA_PENSION("Media Pensión", 30),
    PENSION_COMPLETA("Pensión Completa", 50);

    private final String tipoRegimen;
    private final double incrementoPrecio;

    private Regimen(String tipoRegimen, double incrementoPrecio) {
        this.tipoRegimen = tipoRegimen;
        this.incrementoPrecio = incrementoPrecio;
    }

    public String getTipoRegimen() {
        return tipoRegimen;
    }

    public double getIncrementoPrecio() {
        return incrementoPrecio;
    }

    @Override
    public String toString() {
        return "Regimen{" +
                "tipoRegimen='" + tipoRegimen + '\'' +
                ", incrementoPrecio=" + incrementoPrecio +
                '}';
    }
}
