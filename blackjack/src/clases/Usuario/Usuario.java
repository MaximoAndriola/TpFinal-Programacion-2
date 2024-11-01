package clases.Usuario;

import interfaces.I_ConSaldo;

public class Usuario implements I_ConSaldo {
    private String nombre;
    private String contrasenia;
    private double saldo;
    private final boolean esAdmin = false;

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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    @Override
    public void verSaldo() {
        System.out.println(saldo);
    }

    @Override
    public void cargarSaldo(double monto) {
        saldo += monto;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}

