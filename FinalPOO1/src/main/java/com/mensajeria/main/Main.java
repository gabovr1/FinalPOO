package com.mensajeria.main;

import com.mensajeria.excepciones.MensajeriaException;
import com.mensajeria.modelo.*;

public class Main {
    public static void printCaseHeader(int casoNum, String descripcion) {
        System.out.println("");
        System.out.println("caso 1" + casoNum + ": " + descripcion);
        System.out.println("");
    }

    public static void registrarConControl(Despacho despacho, Paquete paquete) {
        try {
            despacho.agregarPaquete(paquete);
            System.out.println("registrado, paquete " + paquete.getNumeroGuia() + " agregado exitosamente");
        } catch (MensajeriaException e) {
            System.out.println("error: no se pudo registrar el paquete: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

// caso 2

        Despacho despachoAndres = new Despacho("andres");

        registrarConControl(despachoAndres, new PaqueteEstandar("EY2001", 5.0, Zona.oriente));
        registrarConControl(despachoAndres, new PaqueteExpress("EY2002", 1.5, Zona.norte));
        registrarConControl(despachoAndres, new PaqueteFragil("EY2003", 2.0, Zona.centro, "vajilla"));
        registrarConControl(despachoAndres, new PaqueteEstandar("EY2004", 8.0, Zona.centro));
        registrarConControl(despachoAndres, new PaqueteExpress("EY2005", 6.0, Zona.oriente));
        registrarConControl(despachoAndres, new PaqueteFragil("EY2006", 3.5, Zona.sur, "televisor"));

        despachoAndres.imprimirManifiesto();

// caso 3

        System.out.println("intentando crear paquete:");
        try {
            new PaqueteEstandar("EY3001", -2.0, Zona.centro);
        } catch (MensajeriaException e) {
            System.out.println("error: " + e.getMessage());
        }

        System.out.println("intentando crear paquete:");
        try {
            new PaqueteFragil("EY3002", 2.0, Zona.norte, "");
        } catch (MensajeriaException e) {
            System.out.println("error: " + e.getMessage());
        }

        System.out.println("intentando crear paquete:");
        try {
            new PaqueteEstandar("EY12A4", 3.0, Zona.sur);
        } catch (MensajeriaException e) {
            System.out.println("error: " + e.getMessage());
        }

        System.out.println("intentando crear paquete:");
        try {
            new PaqueteExpress("EY3003", 22.0, Zona.norte);
        } catch (MensajeriaException e) {
            System.out.println("error: " + e.getMessage());
        }
        System.out.println();

// caso 4

        Despacho despachoCamila = new Despacho("camila");

        registrarConControl(despachoCamila, new PaqueteEstandar("EY4001", 12.0, Zona.centro));
        registrarConControl(despachoCamila, new PaqueteExpress("EY4002", 10.0, Zona.sur));
        registrarConControl(despachoCamila, new PaqueteFragil("EY4003", 9.0, Zona.norte, "espejo"));
        registrarConControl(despachoCamila, new PaqueteEstandar("EY4004", 8.0, Zona.oriente));

        despachoCamila.imprimirManifiesto();

// caso 5

        Despacho despachoMateo = new Despacho("mateo");

        registrarConControl(despachoMateo, new PaqueteFragil("EY5001", 3.0, Zona.sur, "lampara"));
        registrarConControl(despachoMateo, new PaqueteEstandar("EY5001", 1.0, Zona.centro));
        registrarConControl(despachoMateo, new PaqueteExpress("EY5002", 2.0, Zona.centro));

        despachoMateo.imprimirManifiesto();


    }
}

// nota: profe, gracias por lo aprendido durante la materia, un abrazo.
