package clases.Partida;

import clases.Cartas.Mazo;
import clases.Jugadores.ActorBlackjack;
import clases.Jugadores.Croupier;
import clases.Jugadores.Jugador;
import clases.Usuario.Usuario;
import exepciones.ValorInvalidoException;

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

        System.out.println("-----------------------------------------------" +
                            "Comenzo la partida" +
                            "-----------------------------------------------");

        //Repartir
        croupier.repartir(mazo, jugadores, croupier);

        //Mostrar participantes
        System.out.println(jugadores);
        System.out.println(croupier.getNombre() + ": " + croupier.getMano().getCartas().getFirst() + " -");

        //Pedir carta
        try {
            for (int i = 0; i < jugadores.size(); i++){
                jugadores.get(i).quierePedirUnaCartaMas(scanner, mazo);
            }
        } catch (ValorInvalidoException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(jugadores.getFirst().getMano().getValor());

        //juega el croupier
        croupier.jugar(mazo);

        //mostrar croupier
        System.out.println(croupier);

        //Prueba del metodo de determinar ganador
        System.out.println("GANADORES: ");
        for (Jugador jugador : jugadores) {
            ActorBlackjack actor = ManejoPartida.determinarGanador(croupier, jugador);
            System.out.println(actor);
        }
    }
}
