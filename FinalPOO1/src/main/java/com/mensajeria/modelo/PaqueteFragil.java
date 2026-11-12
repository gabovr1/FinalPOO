package com.mensajeria.modelo;

import com.mensajeria.excepciones.ContenidoFragilFaltanteException;

public class PaqueteFragil extends Paquete {
    private final String contenido;

    public PaqueteFragil(String numeroguia, double peso, Zona zona, String contenido) {
        super(numeroguia, peso, zona);
        if (contenido == null) {
            throw new ContenidoFragilFaltanteException("el paquete fragil " + numeroguia + " no tiene contenido especificado y no se acepta.");
        }
        this.contenido = contenido.trim();
    }
    public String getContenido() {
        return contenido;
    }

    @Override
    public double calcularCostoEnvio() {
        return getCostoBase() + 5000;
    }
    @Override
    public String getNombreServicio() {
        return "fragil (" + contenido + ")";
    }
    @Override
    public String getAjusteStr() {
        return "+5.000";
    }
}
