package model.clases.Ranking;

import model.exepciones.NombreInvalidoExeption;

import java.util.ArrayList;
import java.util.Collections;

public class Ranking {
    private ArrayList<Apuesta> apuestas;

    public void setApuestas(ArrayList<Apuesta> apuestas) {
        this.apuestas = apuestas;
    }

    public ArrayList<Apuesta> getApuestas() {
        return apuestas;
    }

    public Ranking() {
        this.apuestas = new ArrayList<>();
    }

    private void ordenarApuestas() {
        Collections.sort(apuestas);
    }

    public ArrayList<Apuesta> filtrarTop10() {
        ArrayList<Apuesta> filtrados = new ArrayList<>();

        ordenarApuestas();

        for (int i = 0; i < Math.min(10, apuestas.size()); i++) {
            // Con el Math.min evitamos que si hay menos de 10 apuestas se siga ejecutando el bucle
            filtrados.add(apuestas.get(i));
        }
        return filtrados;
    }

    public ArrayList<Apuesta> filtrarPorNombre(String nombreUsuario) throws NombreInvalidoExeption {

        ordenarApuestas();
        ArrayList<Apuesta> filtrados = new ArrayList<>();


        for(Apuesta apuesta : apuestas) {
            if(apuesta.getNombreUsuario().toLowerCase().contains(nombreUsuario.toLowerCase())){
                filtrados.add(apuesta);
            }
        }

        if (filtrados.isEmpty()) {
            throw new NombreInvalidoExeption("El usuario no se encuentra en el ranking");
        }
        return filtrados;
    }

}

