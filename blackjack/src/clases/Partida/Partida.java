package clases.Partida;

import clases.Cartas.Mano;
import clases.Cartas.Mazo;
import clases.Jugadores.ActorBlackjack;
import clases.Jugadores.Croupier;
import clases.Jugadores.Jugador;
import clases.Usuario.InicioSesion;

import java.util.ArrayList;
import java.util.Scanner;

public class Partida {
    //Aca se manejan los metodos para el desarrollo de la partida

    public static void empezarPartida (Scanner scanner) {
        Croupier croupier = new Croupier("Croupier");
        Mazo mazo = new Mazo();
        mazo.mezclar();

        //Instancia de jugadores
        ArrayList<Jugador> jugadores = ManejoPartida.elegirJugadores(scanner);
        System.out.println(jugadores);

        System.out.println("Comenzo la partida");
        //Repartir
        for (Jugador jugador : jugadores) {
            croupier.repartir(mazo, jugador);
        }
        croupier.repartir(mazo, croupier);

        jugadores.getFirst().pedirCarta(mazo);
        System.out.println(jugadores);
        System.out.println(croupier);
        //Prueba del metodo de determinar ganador, TODO: cuando este el metodo de pedir carta se va a modificar
        System.out.println("GANADORES: ");
        for (Jugador jugador : jugadores) {
            if (ManejoPartida.determinarGanador(croupier, jugador) instanceof Jugador && jugador.getNombre().equals("empate")) {
                System.out.println(jugador);
            } else if (ManejoPartida.determinarGanador(croupier, jugador) instanceof Jugador) {
                System.out.println(jugador);
            } else
                System.out.println(croupier);
        }
    }
}
