package model.clases.Partida;

import model.clases.Jugadores.Jugador;
import model.clases.Jugadores.ActorBlackjack;
import model.clases.Jugadores.Croupier;

public abstract class ManejoPartida {
    //Metodos Para las funcionalidades de la partida
    public static final int VALOR_MAXIMO_MANO = 21;

    public static ActorBlackjack determinarGanador (Croupier croupier, Jugador jugador){
        ActorBlackjack ganador;
        //Se compara el valor de la mano de cada uno
        if(jugador.getMano().getCartas().size() == 2 && jugador.getMano().getValor() == 21){
            //Si saca 21 a la primera
            if (croupier.getMano().getCartas().size() == 2 && croupier.getMano().getValor() == 21) {
                //Si el croupier tambien saco 21 a la primera gana la casa
                ganador = croupier;
            }else {
                //Si no gana el jugador
                jugador.setNombre("BLACKJACK");
                ganador = jugador;
            }
        } else if(!validarValorMano(croupier) && validarValorMano(jugador)){
            //Si la mano del croupier se pasa y la del jugador no, gana el jugador
            ganador = jugador;
        } else if (validarValorMano(croupier) && !validarValorMano(jugador)) {
            //Si la mano del jugador se pasa y la del croupier no, gana el croupier
            ganador = croupier;
        }else if (validarValorMano(croupier) && validarValorMano(jugador)){
            //Si ninguno de los dos se pasa, se compara el valor para saber cual gana
            if (croupier.getMano().getValor() >= jugador.getMano().getValor()){
                //Si gano el croupier
                ganador = croupier;
            }else {
                //Si gano el jugador
                ganador = jugador;
            }
        }else {
            //Si se pasan los 2 gana la casa
            ganador = croupier;
        }
        return ganador;
    }

    public static boolean validarValorMano (ActorBlackjack actor){
        boolean esValido = false;

        if (actor.getMano().getValor() <= VALOR_MAXIMO_MANO){
            esValido = true;
        }

        return esValido;
    }
}