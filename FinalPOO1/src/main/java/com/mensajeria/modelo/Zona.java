package com.mensajeria.modelo;

public enum Zona {
    centro("centro", 6000),
    norte("norte", 8000),
    sur("sur", 8000),
    oriente("oriente", 15000);

    private final String nombre;
    private final double tarifafija;

    Zona(String nombre, double tarifafija) {
        this.nombre = nombre;
        this.tarifafija = tarifafija;
    }

    public String getNombre() {
        return nombre;
    }
    public double getTarifaFija() {
        return tarifafija;
    }
    public static int getOrdenRuta(Zona zona) {
        switch (zona) {
            case centro: return 1;
            case norte: return 2;
            case sur: return 3;
            case oriente: return 4;
            default: return 5;
        }
    }
}