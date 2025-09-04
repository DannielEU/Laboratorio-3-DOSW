package edu.dosw.lab.Laboratorio_3_DOSW;
import java.time.LocalDateTime;

/**
 * Implementación de una transacción de consulta de saldo.
 * 
 * Esta transacción permite consultar el saldo de una cuenta sin modificarlo,
 * registrando la fecha y hora en que se realizó la consulta.
 */
public class Consulta implements Transaccion{
    private LocalDateTime fecha;
    
    private Cuenta cuenta;

    /**
     * Construye una nueva consulta para la cuenta especificada.
     * La fecha se establece automáticamente al momento actual.
     *
     * @param cuenta cuenta a consultar
     */
    public Consulta(Cuenta cuenta){
        this.cuenta = cuenta;
        this.fecha = LocalDateTime.now();
    }
    
    /**
     * Ejecuta la consulta de saldo y actualiza la fecha de ejecución.
     */
    @Override
    public void ejecutar() {
        this.cuenta.consultarSaldo();
        this.fecha = LocalDateTime.now();
    }

    /**
     * Genera un informe de la consulta realizada.
     *
     * @return cadena con la fecha en que se realizó la consulta
     */
    @Override
    public String informe() {
        return "Se realizó una consulta el día "+ this.fecha;
    }
}
