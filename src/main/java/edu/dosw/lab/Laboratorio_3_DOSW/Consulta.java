package edu.dosw.lab.Laboratorio_3_DOSW;
import java.time.LocalDateTime;

public class Consulta implements Transaccion{
    private LocalDateTime fecha;
    private Cuenta cuenta;

    public Consulta(Cuenta cuenta){
        this.cuenta = cuenta;
        this.fecha = LocalDateTime.now();
    }
    @Override
    public void ejecutar() {
        this.cuenta.consultarSaldo();
        this.fecha = LocalDateTime.now();
    }

    @Override
    public String informe() {
        return "Se realizó una consulta el día "+ this.fecha;
    }
}
