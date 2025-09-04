package edu.dosw.lab.Laboratorio_3_DOSW;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class ClienteTest {

    private Cliente cliente;
    private Cuenta cuenta1;
    private Cuenta cuenta2;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(1, "Daniel");
        cuenta1 = new Cuenta("001");
        cuenta2 = new Cuenta("002");
    }

    @Test
    void testCrearCliente() {
        assertEquals(1, cliente.getID());
        assertEquals("Daniel", cliente.getNombre());
        assertTrue(cliente.listarCuentas().isEmpty(), "Al inicio no debe tener cuentas");
    }

    @Test
    void testAgregarCuenta() {
        cliente.agregarCuenta(cuenta1);
        assertEquals(1, cliente.listarCuentas().size());
        assertTrue(cliente.listarCuentas().contains(cuenta1));
    }

    @Test
    void testListarCuentasVarias() {
        cliente.agregarCuenta(cuenta1);
        cliente.agregarCuenta(cuenta2);

        List<Cuenta> cuentas = cliente.listarCuentas();

        assertEquals(2, cuentas.size());
        assertTrue(cuentas.contains(cuenta1));
        assertTrue(cuentas.contains(cuenta2));
    }
}

