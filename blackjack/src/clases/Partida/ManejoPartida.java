package clases.Partida;

import clases.Cartas.Carta;
import clases.Cartas.Mano;
import clases.Cartas.Mazo;
import clases.Jugadores.ActorBlackjack;
import clases.Jugadores.Croupier;
import clases.Jugadores.Jugador;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class ManejoPartida {
    //Metodos Para las funcionalidades de la partida
    public static final int TAMANIO_INICIAL_MANO = 2;

    public static ArrayList<Jugador> elegirJugadores (Scanner scanner){
        ArrayList<Jugador> jugadores = new ArrayList<>();
        boolean continuar = true;
        int opcion;
        int i = 0;

        do{
            System.out.print("NOMBRE DE JUGADOR: ");
            String nombre = scanner.nextLine();

            if (!nombre.isEmpty()){
                jugadores.add(new Jugador(nombre));
                i++;
            }

            if (i < 4){
                System.out.println("CARGAR NUEVO JUGADOR:" +
                        "\n1- si" +
                        "\n2- no");
                opcion = scanner.nextInt();
                scanner.nextLine();
                if (opcion == 2){
                    continuar = false;
                }
            }

        }while (continuar && i < 4);

        return jugadores;
    }

    public static ActorBlackjack determinarGanador (Croupier croupier, Jugador jugador){
        //Se compara el valor de la mano de cada uno
        if (croupier.getMano().getValor() >= jugador.getMano().getValor()){
            //Si gano el croupier
            return croupier;
        }else {
            //Si gano el jugador
            return jugador;
        }
    }
}
