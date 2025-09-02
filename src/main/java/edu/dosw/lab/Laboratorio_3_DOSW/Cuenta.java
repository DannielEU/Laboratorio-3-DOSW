package edu.dosw.lab.Laboratorio_3_DOSW;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    private List<Transaccion> historial = new ArrayList<>();
    private  final String nCuenta;
    private double saldo = 0;

    public Cuenta(String nCuenta) {
        this.nCuenta = nCuenta;
    }

    public List<Transaccion> revisarHistorial() {
        return null;
    }
    public void agregarTransaccion(Transaccion transaccion){
        this.historial.add(transaccion);
    }
    public boolean validarNumeroDeCuenta(){
        return false;
    }
    public double consultarSaldo(){
        return this.saldo;
    }
    public void retirar(double monto){
        this.saldo -= monto;
    }
    public void depositar(double monto){
        this.saldo += monto;
    }

    public String getNumeroCuenta() {
        return this.nCuenta;
    }
}
