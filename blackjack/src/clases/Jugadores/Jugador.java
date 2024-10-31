package clases.Jugadores;

import clases.Cartas.Mano;
import clases.Cartas.Mazo;
import exepciones.ValorInvalidoException;

import java.util.Scanner;

public class Jugador extends ActorBlackjack {
    private double apuesta;
    private double saldo;

    public Jugador(String nombre) {
        super(nombre);
    }

    //get y set

    public double getApuesta() {
        return apuesta;
    }

    public void setApuesta(double apuesta) {
        this.apuesta = apuesta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    // metodos

    public String quierePedirUnaCartaMas(Mano mano, Scanner scanner, Mazo mazo)throws ValorInvalidoException {
        String desicion = "si";

        while (desicion != "no") {
            if(mano.getValor() < 21) {
                System.out.println("\nSu puntaje es de: " + mano.getValor());
                System.out.println("\nQuiere pedir otra carta? SI/NO");
                desicion = scanner.nextLine();

            }else{
                throw new ValorInvalidoException("\nSuperaste los 21 puntos, ya no puedes pedir cartas.");
            }
        }
        scanner.close();
        return desicion;
    }

    public void ingresarApuesta(Scanner scanner) {
        System.out.println("\nIngrese su apuesta:");
        apuesta = scanner.nextDouble();
    }

    public void apostar( Scanner scanner) {

        ingresarApuesta(scanner);

        saldo = saldo - apuesta;
    }

    @Override
    public String toString() {
        return "\nJugador{" +
                super.toString() +
                "Apuesta: " + apuesta +
                ", Saldo: " + saldo +
                "}";
    }
}
