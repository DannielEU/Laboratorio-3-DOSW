package edu.dosw.lab.Laboratorio_3_DOSW;
import java.time.LocalDateTime;

public class Retiro implements Transaccion {
    private Cuenta cuenta;
    private double monto;
    private LocalDateTime fecha;

    public Retiro(Cuenta cuenta, double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }

    @Override
    public void ejecutar() {
        cuenta.retirar(monto);
    }

    @Override
    public String informe() {
        return "Se hizo un retiro de: " + this.monto + " en la fecha " + fecha;
    }

}

