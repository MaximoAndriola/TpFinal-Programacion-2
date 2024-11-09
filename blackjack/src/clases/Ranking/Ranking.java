package clases.Ranking;

import clases.Usuario.Usuario;
import exepciones.UsuarioInvalidoExeption;

import java.util.ArrayList;
import java.util.Collections;

public class Ranking {

    private ArrayList<Apuesta> apuestas;

    public Ranking() {
        this.apuestas = new ArrayList<>();
    }

    public void agregarApuesta(Apuesta apuesta) {
        apuestas.add(apuesta);
    }

    private void ordenarApuestas() {
        Collections.sort(apuestas);
    }

    public void mostrarTop10Apuestas() {

        ordenarApuestas();

        System.out.println("Top 10:");
        for (int i = 0; i < Math.min(10, apuestas.size()); i++) {
            // Con el Math.min evitamos que si hay menos de 10 apuestas se siga ejecutando el bucle
            Apuesta apuesta = apuestas.get(i);
            System.out.println((i + 1) + ". Usuario: " + apuesta.getNombreUsuario() + ", Apuesta: $" + apuesta.getApuestaRealizada());
        }
    }

    public ArrayList<Apuesta> filtrarPorNombre(String nombreUsuario) throws UsuarioInvalidoExeption {

        ordenarApuestas();
        ArrayList<Apuesta> filtrados = new ArrayList<>();


        for(Apuesta apuesta : apuestas) {
            if(apuesta.getNombreUsuario().equalsIgnoreCase(nombreUsuario)){
                filtrados.add(apuesta);
            }
        }

        if (filtrados.isEmpty()) {
            throw new UsuarioInvalidoExeption("El usuario no se encuentra en el ranking");
        }
        return filtrados;
    }

}

