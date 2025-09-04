package edu.dosw.lab.Laboratorio_3_DOSW;

/**
 * Interfaz que define el comportamiento común de todas las transacciones bancarias.
 *
 * Todas las implementaciones de transacciones (depósitos, retiros, consultas)
 * deben poder ejecutarse y generar un informe de la operación realizada.
 */
public interface Transaccion {
    /**
     * Ejecuta la transacción, realizando la operación correspondiente en la cuenta.
     */
    public void ejecutar();
    
    /**
     * Genera un informe descriptivo de la transacción realizada.
     *
     * @return cadena de texto con los detalles de la transacción
     */
    public String informe();
}
