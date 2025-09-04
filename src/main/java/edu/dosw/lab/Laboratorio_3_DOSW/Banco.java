package edu.dosw.lab.Laboratorio_3_DOSW;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un banco que contiene cuentas asociadas.
 * 
 * La responsabilidad principal de esta clase es mantener información básica
 * del banco (código y nombre) y gestionar una lista de cuentas.
 */
public class Banco {
    private final String nombre;
    private final String codigo;
    private final List<Cuenta> cuentas = new ArrayList<>();

    /**
     * @param codigo código del banco (2 dígitos)
     * @param nombre nombre comercial
     */
    public Banco(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre del banco.
     *
     * @return el nombre del banco
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Obtiene el código del banco.
     *
     * @return el código del banco
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Verifica si la cuenta pertenece a este banco comprobando si el número de cuenta
     * comienza con el código del banco.
     *
     * @param cuenta la cuenta a verificar (no debe ser {@code null})
     * @return {@code true} si el número de la cuenta empieza con el código del banco,
     *         {@code false} en caso contrario
     */
    public boolean verificarCuenta(Cuenta cuenta) {
        return cuenta.getNumeroCuenta().startsWith(this.codigo);
    }

    /**
     * Agrega una cuenta a la lista de cuentas asociadas a este banco.
     *
     * @param cuenta la cuenta a agregar
     */
    public void agregarCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }
}
