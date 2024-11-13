package model.clases.Usuario;
import model.clases.Jugadores.Jugador;
import model.exepciones.ContraseniaInvalidaExeption;
import model.exepciones.NombreInvalidoExeption;

import java.util.ArrayList;

public abstract class InicioSesion {

    public static void registrarse(String nombre, String contrasenia, ArrayList<Usuario> usuarios) throws NombreInvalidoExeption, ContraseniaInvalidaExeption {
        validarNombreUsuario(nombre, usuarios);

        validarContrasenia(contrasenia);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setContrasenia(contrasenia);
        usuario.setSaldo(50);

        usuarios.add(usuario);
        System.out.println("Registro exitoso");
    }

    public static void ingresar(String nombre, String contrasenia, ArrayList<Usuario> usuarios) throws NombreInvalidoExeption {

        boolean usuarioEncontrado = false;
        int i = 0;

        while (i < usuarios.size() && !usuarioEncontrado) {
            Usuario usuario = usuarios.get(i);
            if (usuario != null && usuario.getNombre().equals(nombre) && usuario.getContrasenia().equals(contrasenia)) {
                usuarioEncontrado = true;
            }else i++;
        }

        if (!usuarioEncontrado) {
            throw new NombreInvalidoExeption("los datos ingresados no coinciden con ningun usuario");
        }
        System.out.println("Ingreso exitoso.");

    }

    public static void validarNombreUsuario(String nombre, ArrayList<Usuario> usuarios) throws NombreInvalidoExeption{
        if (nombre == null || nombre.length() < 5) {
            throw new NombreInvalidoExeption("El nombre debe tener al menos 5 caracteres.");
        }
        for(int i = 0; i < usuarios.size();i++) {
            if(usuarios.get(i).getNombre().equalsIgnoreCase(nombre)) {
                throw new NombreInvalidoExeption("El nombre ingresado ya existe");
            }
        }
    }

    public static void validarNombreJugador (String nombre, ArrayList<Jugador> jugadores) throws NombreInvalidoExeption{
        if (nombre == null || nombre.length() < 5) {
            throw new NombreInvalidoExeption("El nombre debe tener al menos 5 caracteres.");
        }
        if (!jugadores.isEmpty()) {
            for (Jugador jugador : jugadores) {
                if (jugador.getNombre().equalsIgnoreCase(nombre)) {
                    throw new NombreInvalidoExeption("El nombre ingresado ya existe");
                }
            }
        }
    }

    public static void validarContrasenia (String contrasenia) throws ContraseniaInvalidaExeption {
        if (contrasenia == null || contrasenia.length() < 8) {
            throw new ContraseniaInvalidaExeption("La contrasenia tiene que tener 8 caracteres minimo.");
        }
        if (!Character.isUpperCase(contrasenia.charAt(0))) {
            throw new ContraseniaInvalidaExeption("La contrasenia debe comenzar con una letra mayuscula.");
        }
        if (!Character.isDigit(contrasenia.charAt(contrasenia.length() - 1)) || !Character.isDigit(contrasenia.charAt(contrasenia.length() - 2))) {
            throw new ContraseniaInvalidaExeption("Los dos ultimos caracteres de la contrasenia deben ser numeros.");
        }
    }
}