import clases.Jugadores.Jugador;
import clases.Partida.Partida;
import exepciones.ValorInvalidoException;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Partida.empezarPartida(scanner);
    }
}