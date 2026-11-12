package com.mensajeria.modelo;

import com.mensajeria.excepciones.PesoInvalidoException;
import com.mensajeria.excepciones.PesoExcedidoException;
import com.mensajeria.util.ValidadorUtil;
import java.util.Objects;

public abstract class Paquete {
    private final String numeroguia;
    private final double peso;
    private final Zona zona;

    public Paquete(String numeroguia, double peso, Zona zona) {
        ValidadorUtil.validarGuia(numeroguia);
        if (peso <= 0) {
            throw new PesoInvalidoException("el paquete " + numeroguia + " debe pesar mas de 0 kg (peso ingresado: " + peso + " kg).");
        }
        if (peso > 20) {
            throw new PesoExcedidoException("el paquete " + numeroguia + " pesa " + peso + " kg y los de mas de 20 kg no se pueden llevar en moto.");
        }
        this.numeroguia = numeroguia;
        this.peso = peso;
        this.zona = zona;
    }

    public String getNumeroGuia() {
        return numeroguia;
    }
    public double getPeso() {
        return peso;
    }
    public Zona getZona() {
        return zona;
    }
    public double getCostoBase() {
        return zona.getTarifaFija() + (1000 * peso);
    }

    public abstract double calcularCostoEnvio();

    public abstract String getNombreServicio();

    public abstract String getAjusteStr();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paquete paquete = (Paquete) o;
        return Objects.equals(numeroguia, paquete.numeroguia);
    }
    @Override
    public int hashCode() {
        return Objects.hash(numeroguia);
    }
    @Override
    public String toString() {
        return numeroguia + " | " + getNombreServicio() + " | " + zona.getNombre() + " | " + peso + " kg";
    }
}
