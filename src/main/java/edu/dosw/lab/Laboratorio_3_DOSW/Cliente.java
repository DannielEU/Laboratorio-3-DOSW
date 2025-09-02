package edu.dosw.lab.Laboratorio_3_DOSW;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private List<Cuenta> cuentas= new ArrayList<>();
    private final int ID;
    private final String Nombre;

    public Cliente(int ID, String Nombre){
        this.ID = ID;
        this.Nombre = Nombre;
    }
    public void agregarCuenta(Cuenta cuenta){
        this.cuentas.add(cuenta);
    }
    public List<Cuenta> listarCuentas(){
        return this.cuentas;
    }
    public int getID(){
        return this.ID;
    }
    public String getNombre(){
        return this.Nombre;
    }

}
