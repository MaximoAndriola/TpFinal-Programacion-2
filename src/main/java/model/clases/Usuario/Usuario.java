package model.clases.Usuario;

import model.exepciones.ValorInvalidoException;
import model.interfaces.I_ConSaldo;

public class Usuario implements I_ConSaldo {
    private String nombre;
    private String contrasenia;
    private double saldo;
    private  boolean esAdmin = false;

    // Constructor vacio
    public Usuario() {}

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean getEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public void devolverApuesta (double monto){
        saldo += monto;
    }

    @Override
    public void agregarSaldo(String monto) throws ValorInvalidoException {
        validarMonto(monto);

        saldo += Double.parseDouble(monto);
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    public void validarMonto(String monto) throws ValorInvalidoException{
        if (!monto.matches("[0-9]+")){
            throw new ValorInvalidoException("INGRESAR NUMEROS.");
        }

        double montoValidado = Integer.parseInt(monto);

        if(montoValidado <= 0) {
            throw new ValorInvalidoException("INGRESAR UN MONTO.");
        }
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}

