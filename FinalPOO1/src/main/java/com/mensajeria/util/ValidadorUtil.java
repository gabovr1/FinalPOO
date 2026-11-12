package com.mensajeria.util;

import com.mensajeria.excepciones.GuiaInvalidaException;
import java.util.regex.Pattern;

public final class ValidadorUtil {
    private static final Pattern patron = Pattern.compile("^EY\\d{4}$");

    private ValidadorUtil() {

    }

    public static void validarGuia(String numeroguia) {
        if (numeroguia == null || !patron.matcher(numeroguia).matches()) {
            throw new GuiaInvalidaException("guia invalida '" + numeroguia + "': debe iniciar con 'EY' seguido de exactamente 4 dígitos");
        }
    }
}
