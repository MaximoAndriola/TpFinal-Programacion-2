package clases.Cartas;

import enums.PaloCarta;
import enums.RangoCarta;

public class Carta {
    private final PaloCarta palo;
    private final RangoCarta rango;
    private int valor;

    //constructor
    public Carta(PaloCarta palo, RangoCarta rango) {
        this.palo = palo;
        this.rango = rango;
    }

    public int getValor() {
        // Calcular valor de cada carta
        if (rango.equals(RangoCarta.DOS)) {
            valor = 2;
        }else if(rango.equals(RangoCarta.TRES)) {
            valor = 3;
        }else if(rango.equals(RangoCarta.CUATRO)) {
            valor = 4;
        }else if(rango.equals(RangoCarta.CINCO)) {
            valor = 5;
        }else if(rango.equals(RangoCarta.SEIS)) {
            valor = 6;
        }else if(rango.equals(RangoCarta.SIETE)) {
            valor = 7;
        }else if(rango.equals(RangoCarta.OCHO)) {
            valor = 8;
        }else if (rango.equals(RangoCarta.NUEVE)){
            valor = 9;
        }else if(rango.equals(RangoCarta.DIEZ) || rango.equals(RangoCarta.JOTA)
                || rango.equals(RangoCarta.REY) || rango.equals(RangoCarta.REINA)){
            valor = 10;
        }else {
            valor = 11;
        }

        return valor;
    }

    @Override
    public String toString() {
        return rango + " de " + palo;
    }
}