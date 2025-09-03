package edu.dosw.lab.Laboratorio_3_DOSW;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CuentaTest {

    private Cuenta cuenta;

    @BeforeEach
    void setUp() {
        cuenta = new Cuenta("12345");
    }

    @Test
    void testCrearCuenta() {
        assertEquals("12345", cuenta.getNumeroCuenta());
        assertEquals(0.0, cuenta.consultarSaldo());
        assertTrue(cuenta.revisarHistorial().isEmpty(), "El historial debe estar vacío al inicio");
    }

    @Test
    void testAumentarSaldo() {
        cuenta.AumentarSaldo(500.0);
        assertEquals(500.0, cuenta.consultarSaldo());
    }

    @Test
    void testDisminuirSaldoConFondos() {
        cuenta.AumentarSaldo(1000.0);
        cuenta.disminuirSaldo(400.0);
        assertEquals(600.0, cuenta.consultarSaldo());
    }

    @Test
    void testDisminuirSaldoSinFondos() {
        cuenta.AumentarSaldo(100.0);
        cuenta.disminuirSaldo(200.0); // no debería poder retirar más
        assertEquals(100.0, cuenta.consultarSaldo(), "El saldo no debe cambiar");
    }

}
