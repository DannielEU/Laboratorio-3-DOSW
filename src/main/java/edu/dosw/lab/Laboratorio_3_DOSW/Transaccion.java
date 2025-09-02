package edu.dosw.lab.Laboratorio_3_DOSW;

public interface Transaccion {
    public void ejecutar(Cuenta cuenta);
    public String informe();
}
