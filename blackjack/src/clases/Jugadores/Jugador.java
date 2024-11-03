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

    public void ingresarApuesta(double valor) throws ValorInvalidoException{
        if(valor <= 0) {
            throw new ValorInvalidoException("El monto a ingresar debe ser mayor a 0.");
        }
        apuesta = valor;
    }

    public void apostar(double monto) {

        try {
            ingresarApuesta(monto);
        } catch (ValorInvalidoException e) {
            System.out.println("No se pudo cargar el valor ingresado." );
            return;
        }

        saldo = saldo - apuesta;
    }

    @Override
    public void verSaldo() {
        System.out.println(saldo);
    }

    @Override
    public void cargarSaldo(double monto) throws ValorInvalidoException{
        if (monto <= 0) {
            throw new ValorInvalidoException("El monto ingresado debe ser mayor que cero.");
        }

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
