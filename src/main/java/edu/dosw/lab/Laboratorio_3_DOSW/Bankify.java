package edu.dosw.lab.Laboratorio_3_DOSW;

import java.util.List;
import java.util.Optional;

public class Bankify {
    private List<Cliente> clientes;
    private List<Banco> bancos;

    public Bankify(List<Cliente> clientes, List<Banco> bancos){
        this.bancos = bancos;
        this.clientes = clientes;
    }
    public Cliente crearCliente(int id, String nombre) {
        return new Cliente(id, nombre);
    }
    public boolean verificarCuenta(Cuenta cuenta) {
        return this.bancos.stream()
                .anyMatch(banco -> banco.verificarCuenta(cuenta));
    }

    public void agregarCuentaACliente(Cliente cliente, Cuenta cuenta) throws IllegalArgumentException {
        if (!verificarCuenta(cuenta)) {
            throw new IllegalArgumentException("Cuenta no verificada");
        }
        cliente.agregarCuenta(cuenta);
    }



    public List<Cuenta> listarCuentasCliente(Cliente cliente) {
        return cliente.listarCuentas();
    }


    public double consultarSaldo(Cliente cliente, String numeroCuenta) {
        Cuenta cuenta = buscarCuentaPorNumero(cliente, numeroCuenta)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        return cuenta.consultarSaldo();
    }


    public void realizarDeposito(Cliente cliente, String numeroCuenta, double monto) {
        Cuenta cuenta = buscarCuentaPorNumero(cliente, numeroCuenta)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        cuenta.depositar(monto);
    }


    public void realizarRetiro(Cliente cliente, String numeroCuenta, double monto) {
        Cuenta cuenta = buscarCuentaPorNumero(cliente, numeroCuenta)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        cuenta.retirar(monto);
    }



    public List<Transaccion> revisarHistorial(Cliente cliente, String numeroCuenta) {
        return buscarCuentaPorNumero(cliente, numeroCuenta)
                .map(Cuenta::revisarHistorial)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
    }



    private Optional<Cuenta> buscarCuentaPorNumero(Cliente cliente, String numeroCuenta) {
        return cliente.listarCuentas().stream()
                .filter(c -> c.getNumeroCuenta().equals(numeroCuenta))
                .findFirst();
    }

    public void agregarCliente(Cliente nuevoCliente) {
        this.clientes.add(nuevoCliente);
    }


    public Optional<Cliente> buscarClientePorId(int id) {
        return this.clientes.stream()
                .filter(cliente -> cliente.getID() == id)
                .findFirst();
    }


    public Optional<Banco> buscarBancoPorCodigo(String codigo) {
        return this.bancos.stream()
                .filter(banco -> banco.getCodigo().equals(codigo))
                .findFirst();
    }

    public void agregarBanco(Banco nuevoBanco) {
        this.bancos.add(nuevoBanco);
    }


}

