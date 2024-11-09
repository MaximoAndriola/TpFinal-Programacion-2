import clases.GestionJSON.GestionJSON;
import clases.Jugadores.Jugador;
import clases.Partida.Partida;
import clases.Ranking.Apuesta;
import clases.Ranking.Ranking;
import clases.Usuario.InicioSesion;
import clases.Usuario.Usuario;
import exepciones.UsuarioInvalidoExeption;
import exepciones.ValorInvalidoException;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Partida.empezarPartida(scanner);

    }
}