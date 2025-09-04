package edu.dosw.lab.Laboratorio_3_DOSW;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una cuenta bancaria con saldo e historial de transacciones.
 * Reglas:
 *  - El número de cuenta debe validarse externamente antes de agregarse a un cliente.
 *  - Saldo inicial 0.
 */
public class Cuenta {
    private double saldo;
    private final String nCuenta;
    private final List<Transaccion> historial = new ArrayList<>();

    /**
     * Crea una cuenta con saldo cero.
     * @param nCuenta número de cuenta (no se valida aquí para mantener SRP)
     */
    public Cuenta(String nCuenta) {
        this.saldo = 0;
        this.nCuenta = nCuenta;
    }

    /**
     * @return número de cuenta.
     */
    public String getNumeroCuenta() {
        return this.nCuenta;
    }

    /**
     * Consulta el saldo actual.
     * @return saldo
     */
    public double consultarSaldo(){
        return this.saldo;
    }

    /**
     * Deposita un monto positivo.
     * @param monto monto a acreditar
     * @throws IllegalArgumentException si monto <= 0
     */
    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("Monto debe ser positivo");
        }
        this.saldo += monto;
    }

    /**
     * Retira un monto si hay fondos suficientes.
     * @param monto monto a debitar
     * @throws IllegalArgumentException si monto <= 0
     * @throws IllegalStateException si no hay saldo suficiente
     */
    public void retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("Monto debe ser positivo");
        }
        if (saldo < monto) {
            throw new IllegalStateException("Fondos insuficientes");
        }
        this.saldo -= monto;
    }

    /**
     * Agrega una transacción al historial.
     * @param t transacción
     */
    public void agregarTransaccion(Transaccion t) {
        historial.add(t);
    }

    /**
     * @return historial de transacciones (lista viva)
     */
    public List<Transaccion> revisarHistorial() {
        return  historial;
    }

    // Métodos antiguos mantenidos para compatibilidad interna (deprecados)
    /** @deprecated usar {@link #retirar(double)} */
    @Deprecated
    public void disminuirSaldo(double monto) { retirar(monto); }
    /** @deprecated usar {@link #depositar(double)} */
    @Deprecated
    public void AumentarSaldo(double monto){ depositar(monto); }
}

