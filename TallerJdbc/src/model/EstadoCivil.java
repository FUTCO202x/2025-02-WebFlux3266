package model;

public enum EstadoCivil {
    SOLTERO,
    CASADO,
    VIUDO,
    UNION_LIBRE,
    DIVORCIADO;

    public static EstadoCivil desdeTexto(String texto) {
        if (texto == null) return null;
        try {
            return EstadoCivil.valueOf(texto.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("⚠️ Estado civil inválido: " + texto);
            return null;
        }
    }
}
