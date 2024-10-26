package clases.Usuario;

import exepciones.UsuarioInvalidoExeption;

import java.util.ArrayList;

public abstract class InicioSesion {

    public static Usuario registrarse(String nombre, String contrasenia, ArrayList<Usuario> usuarios) throws UsuarioInvalidoExeption {
        validarDatos(nombre, contrasenia, usuarios);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setContrasenia(contrasenia);
        usuario.setSaldo(50);

        return usuario;
    }

    public static void ingresar(String nombre, String contrasenia, ArrayList<Usuario> usuarios) throws UsuarioInvalidoExeption {

        boolean usuarioEncontrado = false;

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if (usuario != null && usuario.getNombre().equals(nombre) && usuario.getContrasenia().equals(contrasenia)) {
                usuarioEncontrado = true;
            }
        }

        if (!usuarioEncontrado) {
            throw new UsuarioInvalidoExeption("No existe un usuario con los datos ingresados.");
        }
        System.out.println("Ingreso exitoso.");

    }

    public static void validarDatos(String nombre, String contrasenia, ArrayList<Usuario> usuarios) throws UsuarioInvalidoExeption {
        if (nombre == null || nombre.length() < 5) {
            throw new UsuarioInvalidoExeption("El nombre no puede estar vacio, al menos 5 caracteres.");
        }
        for(int i = 0; i < usuarios.size();i++) {
            if(usuarios.get(i).getNombre().equalsIgnoreCase(nombre)) {
                throw new UsuarioInvalidoExeption("El nombre ingresado ya existe");
            }
        }

        if (contrasenia == null || contrasenia.length() < 8) {
            throw new UsuarioInvalidoExeption("La contrasenia tiene que tener 8 caracteres minimo.");
        }
        if (!Character.isUpperCase(contrasenia.charAt(0))) {
            throw new UsuarioInvalidoExeption("La contrasenia debe comenzar con una letra mayuscula.");
        }
        if (!Character.isDigit(contrasenia.charAt(contrasenia.length() - 1)) || !Character.isDigit(contrasenia.charAt(contrasenia.length() - 2))) {
            throw new UsuarioInvalidoExeption("Los dos ultimos caracteres de la contrasenia deben ser numeros.");
        }
    }
}