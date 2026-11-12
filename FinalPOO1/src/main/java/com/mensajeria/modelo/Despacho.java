package com.mensajeria.modelo;

import com.mensajeria.excepciones.GuiaDuplicadaException;
import com.mensajeria.excepciones.PesoExcedidoException;
import com.mensajeria.interfaces.Imprimible;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Despacho implements Imprimible {
    private static int contadordespachos = 1;

    private final String codigo;
    private final String mensajero;
    private final List<Paquete> paquetes;
    private static final double limitepeso = 30.0;

    public Despacho(String mensajero) {
        this.codigo = "D-" + contadordespachos++;
        this.mensajero = mensajero;
        this.paquetes = new ArrayList<>();
    }
    public String getCodigo() {
        return codigo;
    }
    public String getMensajero() {
        return mensajero;
    }
    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void agregarPaquete(Paquete paquete) {
        for (Paquete p : paquetes) {
            if (p.getNumeroGuia().equals(paquete.getNumeroGuia())) {
                throw new GuiaDuplicadaException("la guia " + paquete.getNumeroGuia() + " ya estaa registrada en el despacho " + codigo);
            }
        }
        double pesoActual = calcularPesoTotal();
        if (pesoActual + paquete.getPeso() > limitepeso) {
            throw new PesoExcedidoException("el paquete " + paquete.getNumeroGuia() + " (" + paquete.getPeso() + " kg) supera el limite total de la moto, peso actual: " + pesoActual + " kg");
        }

        paquetes.add(paquete);
    }

    public double calcularPesoTotal() {
        double total = 0;
        for (Paquete p : paquetes) {
            total += p.getPeso();
        }
        return total;
    }
    public double calcularCostoTotal() {
        double total = 0;
        for (Paquete p : paquetes) {
            total += p.calcularCostoEnvio();
        }
        return total;
    }
    public List<String> getAdvertencias() {
        List<String> advertencias = new ArrayList<>();
        for (Paquete p : paquetes) {
            if (p instanceof PaqueteFragil) {
                PaqueteFragil pf = (PaqueteFragil) p;
                advertencias.add(pf.getNumeroGuia() + " fragil (" + pf.getContenido() + ")");
            }
        }
        return advertencias;
    }

    public void ordenarPaquetes() {
        Collections.sort(paquetes, new Comparator<Paquete>() {
            @Override
            public int compare(Paquete p1, Paquete p2) {
                boolean p1Express = p1 instanceof PaqueteExpress;
                boolean p2Express = p2 instanceof PaqueteExpress;

                if (p1Express != p2Express) {
                    return p1Express ? -1 : 1;
                }

                int r1 = Zona.getOrdenRuta(p1.getZona());
                int r2 = Zona.getOrdenRuta(p2.getZona());
                if (r1 != r2) {
                    return Integer.compare(r1, r2);
                }

                return p1.getNumeroGuia().compareTo(p2.getNumeroGuia());
            }
        });
    }

    @Override
    public void imprimirManifiesto() {
        ordenarPaquetes();
        DecimalFormat df = new DecimalFormat("#,###");

        System.out.println("despacho " + codigo + ", mensajero: " + mensajero);
        System.out.println("guia | servicio | zona | peso | costo base | ajuste | costo envio");
        System.out.println("");

        for (Paquete p : paquetes) {
            String costoBaseStr = df.format(p.getCostoBase());
            String costoEnvioStr = df.format(p.calcularCostoEnvio());
            String ajuste = p.getAjusteStr();

            System.out.println(p.getNumeroGuia() + " | " +
                    p.getNombreServicio() + " | " +
                    p.getZona().getNombre() + " | " +
                    p.getPeso() + " kg | $" +
                    costoBaseStr + " | " +
                    ajuste + " | $" +
                    costoEnvioStr);
        }
        System.out.println("");
        System.out.println("peso total: " + calcularPesoTotal() + " kg, costo total del despacho: $" + df.format(calcularCostoTotal()));

        List<String> advs = getAdvertencias();
        if (!advs.isEmpty()) {
            System.out.print("advertencias: ");
            for (int i = 0; i < advs.size(); i++) {
                System.out.print(advs.get(i));
                if (i < advs.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        } else {
            System.out.println("ninguna advertencia");
        }
        System.out.println();
    }
}