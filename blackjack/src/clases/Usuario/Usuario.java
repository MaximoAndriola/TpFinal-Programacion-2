package clases.Usuario;

public class Usuario {
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
    
}

