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
    public static final int VALOR_MAXIMO_MANO = 21;

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
        if (!validarValorMano(croupier) && validarValorMano(jugador)){
            //Si la mano del croupier se pasa y la del jugador no, gana el jugador
            return jugador;
        } else if (validarValorMano(croupier) && !validarValorMano(jugador)) {
            //Si la mano del jugador se pasa y la del croupier no, gana el croupier
            return croupier;
        }else if (validarValorMano(croupier) && validarValorMano(jugador)){
            //Si ninguno de los dos se pasa, se compara el valor para saber cual gana
            if (croupier.getMano().getValor() >= jugador.getMano().getValor()){
                //Si gano el croupier
                return croupier;
            }else {
                //Si gano el jugador
                return jugador;
            }
        }else {
            //Si se pasan los 2 empatan y se instancia un jugador que va a guardar
            // la apuesta para la siguiente mano //TODO agregar apuesta aca
            return new Jugador("empate");
        }
    }

    public static boolean validarValorMano (ActorBlackjack actor){
        boolean esValido = false;

        if (actor.getMano().getValor() <= VALOR_MAXIMO_MANO){
            esValido = true;
        }

        return esValido;
    }
}