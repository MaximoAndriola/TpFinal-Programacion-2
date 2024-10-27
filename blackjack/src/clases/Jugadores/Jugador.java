package clases.Jugadores;

import clases.Cartas.Mano;
import clases.Ranking.Apuesta;

public class Jugador extends ActorBlackjack{
    private Apuesta apuesta;

    public Jugador(String nombre) {
        super(nombre);
    }

    @Override
    public String toString() {
        return "\nJugador: {" +
                super.toString() +
                "Apuesta: " + apuesta +
                "}";
    }
}