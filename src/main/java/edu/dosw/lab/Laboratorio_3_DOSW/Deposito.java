package edu.dosw.lab.Laboratorio_3_DOSW;
import java.time.LocalDateTime;

/**
 * Implementación de una transacción de depósito.
 * 
 * Esta transacción aumenta el saldo de una cuenta por el monto especificado,
 * registrando la fecha y hora en que se realizó la operación.
 */
public class Deposito implements Transaccion{

    private double monto;
    
    private Cuenta cuenta;
    
    private LocalDateTime fecha;

    /**
     * Construye un nuevo depósito para la cuenta y monto especificados.
     * La fecha se establece automáticamente al momento actual.
     *
     * @param cuenta cuenta en la que se depositará el dinero
     * @param monto cantidad a depositar
     */
    public Deposito(Cuenta cuenta, double monto){
        this.cuenta = cuenta;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }
    
    /**
     * Ejecuta el depósito aumentando el saldo de la cuenta.
     */
    @Override
    public void ejecutar() {
        this.cuenta.depositar(this.monto);

    }
    
    /**
     * Genera un informe del depósito realizado.
     *
     * @return cadena con el monto depositado y la fecha de la operación
     */
    @Override
    public String informe() {
        return "Se hizo un deposito de " + this.monto + " en la fecha " + fecha;
    }
}
