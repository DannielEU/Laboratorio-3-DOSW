package edu.dosw.lab.Laboratorio_3_DOSW;

import java.util.List;
import java.util.Optional;

public class Bankify {
    private List<Cliente> clientes;
    private List<Banco> bancos;

    /**
     * Crea una nueva instancia de Bankify con listas de clientes y bancos.
     *
     * @param clientes lista inicial de clientes
     * @param bancos lista inicial de bancos
     */
    public Bankify(List<Cliente> clientes, List<Banco> bancos){
        this.bancos = bancos;
        this.clientes = clientes;
    }
    
    /**
     * Crea un nuevo cliente con el identificador y nombre proporcionados.
     *
     * @param id identificador del cliente
     * @param nombre nombre del cliente
     * @return la instancia de {@link Cliente} creada
     */
    public Cliente crearCliente(int id, String nombre) {
        return new Cliente(id, nombre);
    }
    
    /**
     * Registra un cliente nuevo y le asigna una cuenta.
     * Crea el cliente y delega la asociación de la cuenta a {@link #agregarCuentaACliente(Cliente, Cuenta)}.
     *
     * @param id identificador del cliente a crear
     * @param nombre nombre del cliente
     * @param cuenta cuenta a asociar al cliente
     */    
    public void RegistrarCliente(int id, String nombre, Cuenta cuenta) {
        Cliente cliente = this.crearCliente(id,nombre);
        this.agregarCuentaACliente(cliente, cuenta);
    }
    
    /**
     * Agrega una nueva cuenta a un cliente existente identificado por su id.
     * Si el cliente no se encuentra, la operación es silenciosa.
     *
     * @param id identificador del cliente existente
     * @param cuenta cuenta a agregar
     */
    public void nuevaCuentaClienteAntiguo(int id, Cuenta cuenta) {
        this.buscarClientePorId(id)
                .ifPresent(c -> c.agregarCuenta(cuenta));
    }

    /**
     * Verifica si una cuenta pertenece a alguno de los bancos conocidos por Bankify.
     *
     * @param cuenta la cuenta a verificar
     * @return {@code true} si algún banco reconoce la cuenta; {@code false} en caso contrario
     */
    public boolean verificarCuenta(Cuenta cuenta) {
        String numero = cuenta.getNumeroCuenta();
        // Reglas: 10 dígitos y debe iniciar con el código completo de algún banco registrado
        if (numero == null || numero.length() != 10) return false;
        if (!numero.matches("\\d{10}")) return false;
        return this.bancos.stream().anyMatch(b -> numero.startsWith(b.getCodigo()));
    }

    /**
     * Agrega una cuenta a un cliente existente después de verificar que la cuenta
     * pertenece a uno de los bancos registrados.
     *
     * @param cliente el cliente al que se asignará la cuenta
     * @param cuenta la cuenta a asignar
     * @throws IllegalArgumentException si la cuenta no es verificada por ningún banco
     */
    public void agregarCuentaACliente(Cliente cliente, Cuenta cuenta) throws IllegalArgumentException {
        if (!verificarCuenta(cuenta)) {
    
            throw new IllegalArgumentException("Cuenta no verificada");
        }
        cliente.agregarCuenta(cuenta);
    }

    /**
     * Devuelve la lista de cuentas de un cliente.
     *
     * @param cliente cliente cuya lista de cuentas se desea obtener
     * @return lista de cuentas del cliente
     */
    public List<Cuenta> listarCuentasCliente(Cliente cliente) {
        return cliente.listarCuentas();
    }

    /**
     * Consulta el saldo de una cuenta de un cliente.
     *
     * @param cliente cliente dueño de la cuenta
     * @param numeroCuenta número de la cuenta a consultar
     * @return el saldo actual de la cuenta
     * @throws IllegalArgumentException si la cuenta no existe para el cliente
     */
    public double consultarSaldo(Cliente cliente, String numeroCuenta) {
        Cuenta cuenta = buscarCuentaPorNumero(cliente, numeroCuenta)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        return cuenta.consultarSaldo();
    }

    /**
     * Realiza un depósito en la cuenta especificada del cliente y registra la transacción.
     *
     * @param cliente cliente dueño de la cuenta
     * @param numeroCuenta número de cuenta en la que se depositará
     * @param monto monto a depositar
     * @throws IllegalArgumentException si la cuenta no existe para el cliente
     */
    public void realizarDeposito(Cliente cliente, String numeroCuenta, double monto) {
        Cuenta cuenta = buscarCuentaPorNumero(cliente, numeroCuenta)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        Deposito deposito = new Deposito(cuenta, monto);
        deposito.ejecutar();
        cuenta.agregarTransaccion(deposito);
    }

    /**
     * Realiza una consulta de saldo en la cuenta indicada y registra la operación como transacción.
     *
     * @param cliente cliente dueño de la cuenta
     * @param numeroCuenta número de la cuenta a consultar
     * @throws IllegalArgumentException si la cuenta no existe para el cliente
     */
    public void realizarConsulta(Cliente cliente, String numeroCuenta) {
        Cuenta cuenta = buscarCuentaPorNumero(cliente, numeroCuenta)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        Consulta consulta = new Consulta(cuenta);
        consulta.ejecutar();
        cuenta.agregarTransaccion(consulta);
    }

    /**
     * Realiza un retiro en la cuenta especificada del cliente y registra la transacción.
     *
     * @param cliente cliente dueño de la cuenta
     * @param numeroCuenta número de la cuenta de la que se retirará
     * @param monto monto a retirar
     * @throws IllegalArgumentException si la cuenta no existe para el cliente
     */
    public void realizarRetiro(Cliente cliente, String numeroCuenta, double monto) {
        Cuenta cuenta = buscarCuentaPorNumero(cliente, numeroCuenta)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        Retiro retiro = new Retiro(cuenta, monto);
        retiro.ejecutar();
        cuenta.agregarTransaccion(retiro);
    }

    /**
     * Retorna el historial de transacciones de una cuenta del cliente.
     *
     * @param cliente cliente dueño de la cuenta
     * @param numeroCuenta número de la cuenta cuyo historial se desea revisar
     * @return lista de transacciones asociadas a la cuenta
     * @throws IllegalArgumentException si la cuenta no existe para el cliente
     */
    public List<Transaccion> revisarHistorial(Cliente cliente, String numeroCuenta) {
        Cuenta cuenta = buscarCuentaPorNumero(cliente, numeroCuenta)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        return cuenta.revisarHistorial();
    }

    /**
     * Busca una cuenta por su número dentro de las cuentas de un cliente.
     *
     * @param cliente cliente donde buscar
     * @param numeroCuenta número de cuenta buscado
     * @return {@link Optional} con la cuenta si se encuentra, o vacío si no
     */
    private Optional<Cuenta> buscarCuentaPorNumero(Cliente cliente, String numeroCuenta) {
        return cliente.listarCuentas().stream()
                .filter(c -> c.getNumeroCuenta().equals(numeroCuenta))
                .findFirst();
    }

    /**
     * Añade un cliente a la lista de clientes gestionados por Bankify.
     *
     * @param nuevoCliente cliente a agregar
     */
    public void agregarCliente(Cliente nuevoCliente) {
        this.clientes.add(nuevoCliente);
    }

    /**
     * Busca un cliente por su identificador.
     *
     * @param id identificador del cliente
     * @return {@link Optional} con el cliente si se encuentra, o vacío
     */
    public Optional<Cliente> buscarClientePorId(int id) {
        return this.clientes.stream()
                .filter(cliente -> cliente.getID() == id)
                .findFirst();
    }

    /**
     * Busca un banco por su código.
     *
     * @param codigo código del banco
     * @return {@link Optional} con el banco si se encuentra, o vacío
     */
    public Optional<Banco> buscarBancoPorCodigo(String codigo) {
        return this.bancos.stream()
                .filter(banco -> banco.getCodigo().equals(codigo))
                .findFirst();
    }
    
    /**
     * Añade un banco a la lista de bancos gestionados por Bankify.
     *
     * @param nuevoBanco banco a agregar
     */
    public void agregarBanco(Banco nuevoBanco) {
        this.bancos.add(nuevoBanco);
    }

}

