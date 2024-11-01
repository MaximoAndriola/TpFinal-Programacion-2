package clases.Jugadores;

import clases.Cartas.Mano;
import clases.Cartas.Mazo;
import exepciones.ValorInvalidoException;
import interfaces.I_ConSaldo;

import java.util.Scanner;

public class Jugador extends ActorBlackjack implements I_ConSaldo{
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

    public String quierePedirUnaCartaMas(Scanner scanner, Mazo mazo)throws ValorInvalidoException {
        String opcion ;

        do {
            System.out.println("\n" + getNombre() + " quieres pedir otra carta? SI/NO");
            opcion = scanner.nextLine();

            if (opcion.equalsIgnoreCase("si")) {
                if (getMano().getValor() < 21) {
                    pedirCarta(mazo);
                    System.out.println(getMano());
                } else {
                    throw new ValorInvalidoException("\nSuperaste los 21 puntos, ya no puedes pedir cartas.");
                }
            }
        }while (opcion.equalsIgnoreCase("si"));

        return opcion;
    }

    //TODO acomodar error de logica de estos metodos
    public void ingresarApuesta(Scanner scanner) {
        System.out.println("\nIngrese su apuesta:");
        apuesta = scanner.nextDouble();
    }

    public void apostar(Scanner scanner) {

        ingresarApuesta(scanner);

        saldo = saldo - apuesta;
    }

    @Override
    public void verSaldo() {
        System.out.println(saldo);
    }

    @Override
    public void cargarSaldo(double monto) {
        saldo += monto;
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
