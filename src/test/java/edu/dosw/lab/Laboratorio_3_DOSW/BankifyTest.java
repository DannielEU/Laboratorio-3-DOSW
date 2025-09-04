package edu.dosw.lab.Laboratorio_3_DOSW;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
public class BankifyTest {

    private Bankify bankify;
    private Cliente cliente1;
    private Cliente cliente2;
    private Banco banco1;
    private Banco banco2;
    private Cuenta cuenta1;
    private Cuenta cuenta2;

    @BeforeEach
    public void setUp() {
        cliente1 = new Cliente(1, "Juan");
        cliente2 = new Cliente(2, "Ana");

        banco1 = new Banco("001", "Banco Uno");
        banco2 = new Banco("002", "Banco Dos");

    cuenta1 = new Cuenta("0012345678");
    cuenta2 = new Cuenta("0020000000");

        banco1.agregarCuenta(cuenta1);  
        banco2.agregarCuenta(cuenta2);  

        List<Cliente> clientes = new ArrayList<>(Arrays.asList(cliente1, cliente2));
        List<Banco> bancos = new ArrayList<>(Arrays.asList(banco1, banco2));

        bankify = new Bankify(clientes, bancos);
    }

    @Test
    public void testConstructorInicializaListas() {
        assertNotNull(bankify);
    }

   
    @Test
    public void testAgregarCuentaAClienteCuentaVerificada() {

    bankify.agregarCuentaACliente(cliente1, cuenta1);
    assertTrue(cliente1.listarCuentas().contains(cuenta1));
    }

    @Test
    public void testAgregarCuentaAClienteCuentaNoVerificadaLanzaExcepcion() {
        Cuenta cuentaNoVerificada = new Cuenta("999");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bankify.agregarCuentaACliente(cliente1, cuentaNoVerificada);
        });
        assertEquals("Cuenta no verificada", exception.getMessage());
    }

    @Test
    public void testListarCuentasCliente() {
        cliente1.agregarCuenta(cuenta1);
        List<Cuenta> cuentas = bankify.listarCuentasCliente(cliente1);
        assertEquals(1, cuentas.size());
        assertTrue(cuentas.contains(cuenta1));
    }

    @Test
    public void testConsultarSaldoCuentaExistente() {
        cliente1.agregarCuenta(cuenta1);
        Deposito deposito = new Deposito(cuenta1,500.0);
        deposito.ejecutar();

        double saldo = bankify.consultarSaldo(cliente1, cuenta1.getNumeroCuenta());
        assertEquals(500.0, saldo);
    }

    @Test
    public void testConsultarSaldoCuentaNoExistenteLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bankify.consultarSaldo(cliente1, "noexiste");
        });
        assertEquals("Cuenta no encontrada", exception.getMessage());
    }

    @Test
    public void testRealizarDeposito() {
        cliente1.agregarCuenta(cuenta1);
        bankify.realizarDeposito(cliente1, cuenta1.getNumeroCuenta(), 200.0);
        assertEquals(200.0, cuenta1.consultarSaldo());
    }

    @Test
    public void testRealizarDepositoCuentaNoExistenteLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bankify.realizarDeposito(cliente1, "noexiste", 100);
        });
        assertEquals("Cuenta no encontrada", exception.getMessage());
    }

    @Test
    public void testRealizarRetiro() {
        cliente1.agregarCuenta(cuenta1);
        bankify.realizarDeposito(cliente1, cuenta1.getNumeroCuenta(), 500.0);
        bankify.realizarRetiro(cliente1, cuenta1.getNumeroCuenta(), 300.0);
        assertEquals(200.0, cuenta1.consultarSaldo());
    }

    @Test
    public void testRealizarRetiroCuentaNoExistenteLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bankify.realizarRetiro(cliente1, "noexiste", 50);
        });
        assertEquals("Cuenta no encontrada", exception.getMessage());
    }

    @Test
    public void testRevisarHistorialCuentaNoExistenteLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bankify.revisarHistorial(cliente1, "noexiste");
        });
        assertEquals("Cuenta no encontrada", exception.getMessage());
    }

    @Test
    public void testBuscarClientePorIdExistente() {
        Optional<Cliente> clienteOpt = bankify.buscarClientePorId(1);
        assertTrue(clienteOpt.isPresent());
        assertEquals("Juan", clienteOpt.get().getNombre());
    }

    @Test
    public void testBuscarClientePorIdNoExistente() {
        Optional<Cliente> clienteOpt = bankify.buscarClientePorId(999);
        assertFalse(clienteOpt.isPresent());
    }

    @Test
    public void testBuscarBancoPorCodigoExistente() {
        Optional<Banco> bancoOpt = bankify.buscarBancoPorCodigo("001");
        assertTrue(bancoOpt.isPresent());
        assertEquals("Banco Uno", bancoOpt.get().getNombre());
    }

    @Test
    public void testBuscarBancoPorCodigoNoExistente() {
        Optional<Banco> bancoOpt = bankify.buscarBancoPorCodigo("999");
        assertFalse(bancoOpt.isPresent());
    }

    @Test
    public void testAgregarCliente() {
        Cliente clienteNuevo = new Cliente(3, "Luis");
        bankify.agregarCliente(clienteNuevo);
        Optional<Cliente> clienteOpt = bankify.buscarClientePorId(3);
        assertTrue(clienteOpt.isPresent());
        assertEquals("Luis", clienteOpt.get().getNombre());
    }

    @Test
    public void testAgregarBanco() {
        Banco bancoNuevo = new Banco("003", "Banco Tres");
        bankify.agregarBanco(bancoNuevo);
        Optional<Banco> bancoOpt = bankify.buscarBancoPorCodigo("003");
        assertTrue(bancoOpt.isPresent());
        assertEquals("Banco Tres", bancoOpt.get().getNombre());
    }
    @Test
    public void testRevisarHistorial() {
        cliente1.agregarCuenta(cuenta1);
        bankify.realizarDeposito(cliente1, cuenta1.getNumeroCuenta(), 500.0);
        bankify.realizarRetiro(cliente1, cuenta1.getNumeroCuenta(), 300.0);
        bankify.realizarDeposito(cliente1, cuenta1.getNumeroCuenta(), 900.0);
        bankify.realizarConsulta(cliente1,cuenta1.getNumeroCuenta());
        bankify.realizarRetiro(cliente1, cuenta1.getNumeroCuenta(), 200.0);
        bankify.realizarConsulta(cliente1,cuenta1.getNumeroCuenta());
        bankify.revisarHistorial(cliente1, cuenta1.getNumeroCuenta())
                .stream()
                .map(Transaccion::informe)
                .forEach(System.out::println);

        assertNotNull(bankify.revisarHistorial(cliente1, cuenta1.getNumeroCuenta()));
    }

}
