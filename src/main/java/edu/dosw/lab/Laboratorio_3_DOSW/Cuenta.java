package edu.dosw.lab.Laboratorio_3_DOSW;

import java.util.ArrayList;
import java.util.List;
    public class Cuenta {
    private double saldo;
    private  final String nCuenta;
    private List<Transaccion> historial = new ArrayList<>();

    public Cuenta(String nCuenta) {
        this.saldo = 0;
        this.nCuenta = nCuenta;
    }
    public String getNumeroCuenta() {
        return this.nCuenta;
    }
    public double consultarSaldo(){
        return this.saldo;
    }

    public void disminuirSaldo(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }
    public void AumentarSaldo(double monto){
        this.saldo += monto;
    }
    public void agregarTransaccion(Transaccion t) {
        historial.add(t);
    }
    public List<Transaccion> revisarHistorial() {
        return  historial;
    }

}
