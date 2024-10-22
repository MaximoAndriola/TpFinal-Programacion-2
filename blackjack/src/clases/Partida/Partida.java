package clases.Partida;

import clases.Cartas.Mano;
import clases.Cartas.Mazo;
import clases.Jugadores.ActorBlackjack;
import clases.Jugadores.Croupier;
import clases.Jugadores.Jugador;

import java.util.ArrayList;
import java.util.Scanner;

public class Partida {
    //Aca se manejan los metodos para el desarrollo de la partida

    public static void empezarPartida (Scanner scanner){
        Mazo mazo = new Mazo();
        mazo.mezclar();

        System.out.println("Cuantos jugadores van a jugar (1-4)");

        int cantJugadores = scanner.nextInt();
        scanner.nextLine();

        Croupier croupier = new Croupier("HOLA");

        ArrayList<ActorBlackjack> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("Mazi"));
        jugadores.add(new Jugador("Mauro"));
        jugadores.add(new Jugador("Bubbi"));
        jugadores.add(new Jugador("Tobias"));

        jugadores = croupier.repartir(mazo, jugadores);

        System.out.println("Comenzo la partida");
        System.out.println(jugadores);
    }

}
