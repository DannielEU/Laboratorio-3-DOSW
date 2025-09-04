package edu.dosw.lab.Laboratorio_3_DOSW;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un cliente del sistema bancario Bankify.
 * 
 * Un cliente puede tener múltiples cuentas bancarias asociadas y se identifica
 * únicamente por su ID y nombre.
 */
public class Cliente {
    private List<Cuenta> cuentas= new ArrayList<>();
    
    private final int ID;
    
    private final String Nombre;

    /**
     * Construye un nuevo cliente con el ID y nombre proporcionados.
     *
     * @param ID identificador único del cliente
     * @param Nombre nombre del cliente
     */
    public Cliente(int ID, String Nombre){
        this.ID = ID;
        this.Nombre = Nombre;
    }
    
    /**
     * Agrega una cuenta bancaria a la lista de cuentas del cliente.
     *
     * @param cuenta la cuenta a agregar
     */
    public void agregarCuenta(Cuenta cuenta){
        this.cuentas.add(cuenta);
    }
    
    /**
     * Retorna la lista de todas las cuentas asociadas al cliente.
     *
     * @return lista de cuentas del cliente
     */
    public List<Cuenta> listarCuentas(){
        return this.cuentas;
    }
    
    /**
     * Obtiene el identificador único del cliente.
     *
     * @return el ID del cliente
     */
    public int getID(){
        return this.ID;
    }
    
    /**
     * Obtiene el nombre del cliente.
     *
     * @return el nombre del cliente
     */
    public String getNombre(){
        return this.Nombre;
    }

}
