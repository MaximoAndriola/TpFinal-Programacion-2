package clases.Jugadores;

import clases.Cartas.Mano;
import clases.Cartas.Mazo;

import java.util.ArrayList;

public class Croupier extends ActorBlackjack{
    //Recibe 2 cartas, una boca arriba y otra boca abajo

    public Croupier(String nombre) {
        super(nombre);
    }


    public ActorBlackjack repartir (Mazo mazo, ActorBlackjack actor){
        Mano mano = new Mano();
        mano.setMano(mazo.sacarCarta(), mazo.sacarCarta());
        actor.setMano(mano);
        return actor;
    }

    @Override
    public String toString() {
        return "Croupier: {" +
                super.toString() +
                "}";
    }
}
