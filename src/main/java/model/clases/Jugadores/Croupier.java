package model.clases.Jugadores;

import model.clases.Cartas.Mano;
import model.clases.Cartas.Mazo;

import java.util.ArrayList;

public class Croupier extends ActorBlackjack{
    //Recibe 2 cartas, una boca arriba y otra boca abajo
    public final static int MAX_MANO = 17;

    public Croupier(String nombre) {
        super(nombre);
    }

    //Metodos custom
    public ActorBlackjack repartirUnJugador (Mazo mazo, ActorBlackjack actor){
        Mano mano = new Mano();
        mano.setMano(mazo.sacarCarta(), mazo.sacarCarta());
        actor.setMano(mano);
        return actor;
    }

    public void repartir (Mazo mazo, ArrayList<Jugador> jugadores, Croupier croupier){
        for (Jugador jugador : jugadores) {
            croupier.repartirUnJugador(mazo, jugador);
        }
        croupier.repartirUnJugador(mazo, croupier);
    }

    public void jugar (Mazo mazo){
        while (getMano().getValor() < MAX_MANO){
            pedirCarta(mazo);
        }
    }

    @Override
    public String toString() {
        return "\nCroupier: {" +
                super.toString() +
                "}";
    }
}
