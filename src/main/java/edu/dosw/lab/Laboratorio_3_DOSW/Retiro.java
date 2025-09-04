package edu.dosw.lab.Laboratorio_3_DOSW;
import java.time.LocalDateTime;

/**
 * Implementación de una transacción de retiro.
 * 
 * Esta transacción disminuye el saldo de una cuenta por el monto especificado,
 * registrando la fecha y hora en que se realizó la operación.
 */
public class Retiro implements Transaccion {
    /** Cuenta de la que se retirará el dinero. */
    private Cuenta cuenta;
    
    /** Monto a retirar de la cuenta. */
    private double monto;
    
    /** Fecha y hora en que se creó/realizó el retiro. */
    private LocalDateTime fecha;

    /**
     * Construye un nuevo retiro para la cuenta y monto especificados.
     * La fecha se establece automáticamente al momento actual.
     *
     * @param cuenta cuenta de la que se retirará el dinero
     * @param monto cantidad a retirar
     */
    public Retiro(Cuenta cuenta, double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }

    /**
     * Ejecuta el retiro disminuyendo el saldo de la cuenta.
     * Si no hay fondos suficientes, la cuenta maneja el error internamente.
     */
    @Override
    public void ejecutar() {
        cuenta.retirar(monto);
    }

    /**
     * Genera un informe del retiro realizado.
     *
     * @return cadena con el monto retirado y la fecha de la operación
     */
    @Override
    public String informe() {
        return "Se hizo un retiro de: " + this.monto + " en la fecha " + fecha;
    }

}

