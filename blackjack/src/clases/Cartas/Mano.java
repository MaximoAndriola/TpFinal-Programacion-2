package clases.Cartas;

import java.util.ArrayList;

public class Mano {
    //Solo las cartas de un jugador
    private ArrayList<Carta> mano = new ArrayList<>();

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public void setMano(Carta carta1, Carta carta2) {//Siempre se inicializa con 2 cartas
        mano.add(carta1);
        mano.add(carta2);
    }

    //Metodos personalizados
    public void verMano() {
        for (Carta carta : mano) {
            System.out.println(carta);
        }
    }

    public void pedirCarta(Carta carta){
        mano.add(carta);
    }

    @Override
    public String toString() {
        return mano.toString();
    }
}
