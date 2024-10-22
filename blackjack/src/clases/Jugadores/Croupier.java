package clases.Jugadores;

import clases.Cartas.Mano;
import clases.Cartas.Mazo;

import java.util.ArrayList;

public class Croupier extends ActorBlackjack{
    //Recibe 2 cartas, una boca arriba y otra boca abajo

    public Croupier(String nombre) {
        super(nombre);
    }


    public ArrayList<ActorBlackjack> repartir (Mazo mazo, ArrayList<ActorBlackjack> jugadores){
        ArrayList<ActorBlackjack> jugadoresConMano = new ArrayList<>();

        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i) instanceof Jugador jugador) {
                Mano mano = new Mano();

                mano.setMano(mazo.sacarCarta(), mazo.sacarCarta());
                jugador.setMano(mano);
                jugadoresConMano.add(jugador);
            }else{
                Croupier croupier = (Croupier) jugadores.get(i);
                Mano mano = new Mano();

                mano.setMano(mazo.sacarCarta(), mazo.sacarCarta());
                croupier.setMano(mano);
                jugadoresConMano.add(croupier);
            }
        }
        return jugadoresConMano;
    }

    @Override
    public String toString() {
        return "Croupier: {" +
                super.toString() +
                "}";
    }
}
