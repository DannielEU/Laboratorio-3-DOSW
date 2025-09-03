package edu.dosw.lab.Laboratorio_3_DOSW;
import java.time.LocalDateTime;
public class Deposito implements Transaccion{

    private double monto;
    private Cuenta cuenta;
    private LocalDateTime fecha;

    public Deposito(Cuenta cuenta, double monto){
        this.cuenta = cuenta;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }
    @Override
    public void ejecutar() {
        this.cuenta.AumentarSaldo(this.monto);

    }
    @Override
    public String informe() {
        return "Se hizo un deposito de " + this.monto + " en la fecha, " + fecha;
    }
}
