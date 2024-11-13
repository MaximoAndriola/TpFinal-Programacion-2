package model.clases.Jugadores;

import model.exepciones.ValorInvalidoException;
import model.interfaces.I_ConSaldo;

public class Jugador extends ActorBlackjack implements I_ConSaldo{
    private double apuesta;
    private double saldo;
    private String id;

    public Jugador(String nombre) {
        super(nombre);
    }

    //get y set

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getApuesta() {
        return apuesta;
    }

// metodos

    @Override
    public void agregarSaldo(String monto) throws ValorInvalidoException{
        validarMonto(monto);

        saldo += Double.parseDouble(monto);
    }

    public void apostar(String monto) throws ValorInvalidoException {
        validarMonto(monto);

        saldo = saldo - apuesta;
    }

    public void validarMonto(String monto) throws ValorInvalidoException{
        if (!monto.matches("\\d+")){
            throw new ValorInvalidoException("INGRESAR NUMEROS.");
        }

        double montoValidado = Integer.parseInt(monto);

        if(montoValidado <= 0) {
            throw new ValorInvalidoException("INGRESAR UN MONTO.");
        }

        validarSuficiente(montoValidado);

        apuesta = montoValidado;
    }

    public void validarSuficiente(double montoValidado) throws ValorInvalidoException{
        if (montoValidado > saldo/2){
            throw new ValorInvalidoException("INGRESAR MENOS DE\nLA MITAD DEL SALDO");
        }
    }

    @Override
    public double getSaldo() {
        return saldo;
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
