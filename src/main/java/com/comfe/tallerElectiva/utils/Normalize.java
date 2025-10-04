package com.comfe.tallerElectiva.utils;

import java.text.Normalizer;

public class Normalize{

    public static String normalizar(String texto) {
        if (texto == null) return null;
        String sinTildes = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return sinTildes.toLowerCase();
    }
}

