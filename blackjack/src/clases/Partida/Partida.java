package clases.Partida;

import clases.Cartas.Mazo;
import clases.Jugadores.ActorBlackjack;
import clases.Jugadores.Croupier;
import clases.Jugadores.Jugador;
import clases.Usuario.InicioSesion;
import clases.Usuario.Usuario;
import exepciones.UsuarioInvalidoExeption;

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

        //juega el croupier
        croupier.jugar(mazo);

        System.out.println(jugadores);
        System.out.println(croupier);
        //Prueba del metodo de determinar ganador, TODO: cuando este el metodo de pedir carta se va a modificar
        System.out.println("GANADORES: ");
        for (Jugador jugador : jugadores) {
            ActorBlackjack actor = ManejoPartida.determinarGanador(croupier, jugador);
            System.out.println(actor);
        }
    }
}
