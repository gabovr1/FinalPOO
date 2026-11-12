package com.mensajeria.modelo;

public class PaqueteExpress extends Paquete {

    public PaqueteExpress(String numeroguia, double peso, Zona zona) {
        super(numeroguia, peso, zona);
    }
    @Override
    public double calcularCostoEnvio() {
        return getCostoBase() * 1.5;
    }
    @Override
    public String getNombreServicio() {
        return "express";
    }
    @Override
    public String getAjusteStr() {
        return "+50%";
    }
}
