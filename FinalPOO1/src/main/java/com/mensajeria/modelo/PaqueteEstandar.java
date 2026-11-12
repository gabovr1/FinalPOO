package com.mensajeria.modelo;

public class PaqueteEstandar extends Paquete {

    public PaqueteEstandar(String numeroguia, double peso, Zona zona) {
        super(numeroguia, peso, zona);
    }

    @Override
    public double calcularCostoEnvio() {
        return getCostoBase();
    }
    @Override
    public String getNombreServicio() {
        return "estandar";
    }
    @Override
    public String getAjusteStr() {
        return "";
    }
}