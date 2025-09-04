package edu.dosw.lab.Laboratorio_3_DOSW;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

/**
 * Pruebas funcionales que validan reglas y flujo de operaciones de Bankify.
 * Se enfocan en: validación de número de cuenta, depósitos, retiros, consultas
 * e historial de transacciones.
 */
public class BankifyFunctionalTest {

    private Bankify bankify;
    private Banco banco01;
    private Cliente cliente;

    @BeforeEach
    void init() {
        banco01 = new Banco("01", "Banco Principal");
        List<Banco> bancos = new ArrayList<>();
        bancos.add(banco01);
        bankify = new Bankify(new ArrayList<>(), bancos);
        cliente = new Cliente(100, "Cliente Pruebas");
        bankify.agregarCliente(cliente);
    }

    @Test
    @DisplayName("Cuenta válida se agrega y saldo inicia en 0")
    void cuentaValidaSaldoInicial() {
        Cuenta c = new Cuenta("0112345678");
        bankify.agregarCuentaACliente(cliente, c);
        Assertions.assertEquals(0.0, c.consultarSaldo());
        Assertions.assertTrue(cliente.listarCuentas().contains(c));
    }

    @Test
    @DisplayName("Cuenta inválida (longitud) lanza excepción")
    void cuentaLongitudInvalida() {
        Cuenta c = new Cuenta("01234");
        Assertions.assertThrows(IllegalArgumentException.class, () -> bankify.agregarCuentaACliente(cliente, c));
    }

    @Test
    @DisplayName("Cuenta inválida (no numérica) lanza excepción")
    void cuentaConLetras() {
        Cuenta c = new Cuenta("01AB345678");
        Assertions.assertThrows(IllegalArgumentException.class, () -> bankify.agregarCuentaACliente(cliente, c));
    }

    @Test
    @DisplayName("Deposito y retiro válidos reflejan saldo e historial")
    void depositoRetiroHistorial() {
        Cuenta c = new Cuenta("0111111111");
        bankify.agregarCuentaACliente(cliente, c);
        bankify.realizarDeposito(cliente, c.getNumeroCuenta(), 500);
        bankify.realizarRetiro(cliente, c.getNumeroCuenta(), 200);
        bankify.realizarConsulta(cliente, c.getNumeroCuenta());
        Assertions.assertEquals(300.0, c.consultarSaldo());
        Assertions.assertEquals(3, c.revisarHistorial().size(), "Debe haber 3 transacciones (dep, ret, consulta)");
    }

    @Test
    @DisplayName("Retiro mayor al saldo lanza excepción y no cambia saldo")
    void retiroMayorSaldo() {
        Cuenta c = new Cuenta("0199999999");
        bankify.agregarCuentaACliente(cliente, c);
        bankify.realizarDeposito(cliente, c.getNumeroCuenta(), 100);
        Assertions.assertThrows(IllegalStateException.class, () -> bankify.realizarRetiro(cliente, c.getNumeroCuenta(), 150));
        Assertions.assertEquals(100.0, c.consultarSaldo());
    }

    @Test
    @DisplayName("Consultar saldo de cuenta inexistente lanza excepción")
    void consultarCuentaNoExiste() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> bankify.consultarSaldo(cliente, "0000000000"));
    }
}
