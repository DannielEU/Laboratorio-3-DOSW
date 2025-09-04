package edu.dosw.lab.Laboratorio_3_DOSW;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas específicas de las implementaciones de Transaccion: Deposito, Retiro y Consulta
 * para asegurar cobertura de sus métodos ejecutar() e informe().
 */
public class TransaccionesTest {

    private Cuenta cuenta;

    @BeforeEach
    void init(){
        cuenta = new Cuenta("0112345678");
    }

    @Test
    void testDepositoEjecutarInforme(){
        Deposito d = new Deposito(cuenta, 300);
        d.ejecutar();
        assertEquals(300.0, cuenta.consultarSaldo());
        String info = d.informe();
        assertTrue(info.contains("deposito"));
        assertTrue(info.contains("300"));
    }

    @Test
    void testRetiroEjecutarInforme(){
        cuenta.depositar(500);
        Retiro r = new Retiro(cuenta, 200);
        r.ejecutar();
        assertEquals(300.0, cuenta.consultarSaldo());
        String info = r.informe();
        assertTrue(info.contains("retiro"));
        assertTrue(info.contains("200"));
    }

    @Test
    void testConsultaActualizaFechaEInforme(){
        Consulta c = new Consulta(cuenta);
        double saldo = cuenta.consultarSaldo();
        c.ejecutar(); // debe refrescar fecha y no cambiar saldo
        assertEquals(saldo, cuenta.consultarSaldo());
        String info = c.informe();
        assertTrue(info.contains("consulta"));
    }
}
