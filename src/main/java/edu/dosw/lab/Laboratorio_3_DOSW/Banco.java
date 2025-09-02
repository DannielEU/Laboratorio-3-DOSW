package edu.dosw.lab.Laboratorio_3_DOSW;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private final String nombre;
    private String codigo;
    private List<Cuenta> cuentas = new ArrayList<>();
    public Banco(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }
    public String getCodigo(){ return this.codigo;}

    public boolean verificarCuenta(Cuenta cuenta) {
        return cuenta.getNumeroCuenta().startsWith(this.codigo);
    }

    public void agregarCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }
}
