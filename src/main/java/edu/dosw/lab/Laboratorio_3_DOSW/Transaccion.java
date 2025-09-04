package edu.dosw.lab.Laboratorio_3_DOSW;

public interface Transaccion {
    /** Ejecuta la transacción aplicando sus efectos. */
    void ejecutar();
    /** Informe legible de la transacción. */
    String informe();
}
