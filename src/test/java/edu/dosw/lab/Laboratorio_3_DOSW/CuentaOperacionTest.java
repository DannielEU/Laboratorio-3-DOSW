package edu.dosw.lab.Laboratorio_3_DOSW;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests iniciales (paso ROJO TDD) para validar reglas de cuentas y operaciones.
 * Aún no existen los métodos depositar/retirar ni las validaciones completas.
 */
public class CuentaOperacionTest {

    private Bankify bankify;
    private Banco banco01;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        banco01 = new Banco("01", "Banco Principal");
        List<Banco> bancos = new ArrayList<>();
        bancos.add(banco01);
        bankify = new Bankify(new ArrayList<>(), bancos);
        cliente = new Cliente(10, "Carlos");
        bankify.agregarCliente(cliente);
    }

    @Test
    @DisplayName("No debe permitir crear cuenta con longitud != 10")
    void cuentaLongitudInvalida() {
        Cuenta cuenta = new Cuenta("01123"); // muy corta
        assertThrows(IllegalArgumentException.class, () -> bankify.agregarCuentaACliente(cliente, cuenta));
    }

    @Test
    @DisplayName("No debe permitir cuenta con caracteres no numéricos")
    void cuentaConCaracteres() {
        Cuenta cuenta = new Cuenta("01ABC56789");
        assertThrows(IllegalArgumentException.class, () -> bankify.agregarCuentaACliente(cliente, cuenta));
    }

    @Test
    @DisplayName("No debe permitir cuenta con prefijo de banco no registrado")
    void cuentaBancoNoRegistrado() {
        Cuenta cuenta = new Cuenta("9912345678");
        assertThrows(IllegalArgumentException.class, () -> bankify.agregarCuentaACliente(cliente, cuenta));
    }

    @Test
    @DisplayName("Debe permitir cuenta válida y luego depositar/retirar/consultar")
    void flujoBasicoCuentaValida() {
        Cuenta cuenta = new Cuenta("0112345678");
        bankify.agregarCuentaACliente(cliente, cuenta);
        // Paso 1: saldo inicial 0
        assertEquals(0.0, cuenta.consultarSaldo());
        // Paso 2: depositar
        cuenta.depositar(500.0);
        assertEquals(500.0, cuenta.consultarSaldo());
        // Paso 3: retirar
        cuenta.retirar(200.0);
        assertEquals(300.0, cuenta.consultarSaldo());
    }

    @Test
    @DisplayName("Depositar monto negativo lanza excepción")
    void depositarNegativo() {
        Cuenta cuenta = new Cuenta("0112345678");
        bankify.agregarCuentaACliente(cliente, cuenta);
        assertThrows(IllegalArgumentException.class, () -> cuenta.depositar(-10));
    }

    @Test
    @DisplayName("Retirar monto mayor que saldo lanza excepción")
    void retirarMayorSaldo() {
        Cuenta cuenta = new Cuenta("0112345678");
        bankify.agregarCuentaACliente(cliente, cuenta);
        cuenta.depositar(100);
        assertThrows(IllegalStateException.class, () -> cuenta.retirar(150));
    }
}
