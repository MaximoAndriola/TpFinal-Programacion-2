package clases.Cartas;

import enums.RangoCarta;

import java.util.ArrayList;

public class Mano {
    //Solo las cartas de un jugador
    private ArrayList<Carta> cartas = new ArrayList<>();

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setMano(Carta carta1, Carta carta2) {//Siempre se inicializa con 2 cartas
        cartas.add(carta1);
        cartas.add(carta2);
    }

    //Metodos personalizados
    public int getValor (){
        int valor = 0;
        int cantAS = 0;
        for (int i = 0; i < cartas.size(); i++){
            valor += cartas.get(i).getValor();
            if (cartas.get(i).getRango().equals(RangoCarta.AS)){
                cantAS++;
            }
        }

        while (valor > 21 && cantAS > 0){
            valor -= 10;
            cantAS--;
        }

        return valor;
    }

    public void verMano() {
        for (Carta carta : cartas) {
            System.out.println(carta);
        }
    }

    public void agregarCarta(Carta carta){
        cartas.add(carta);
    }

    @Override
    public String toString() {
        return cartas.toString();
    }
}
