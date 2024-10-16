package clases;

import enums.PaloCarta;
import enums.RangoCarta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {
    private static final int TAMANIO_MAZO = 52; // 13 cartas de 4 palos
    private final List<Carta> cartas = new ArrayList<>();

    public Mazo() {
        // Instancia las cartas del mazo
        for(PaloCarta palo : PaloCarta.values()) {
            for(RangoCarta rango : RangoCarta.values()) {
                cartas.add(new Carta(palo, rango));
            }
        }
    }

    public void mezclar() {
        Collections.shuffle(cartas);
    }
    
    public void devolverCarta(Carta carta) {
        cartas.add(carta);
    }
    
    public Carta sacarCarta() {
        return cartas.removeFirst();
    }

    @Override
    public String toString() {
        StringBuilder mazo = new StringBuilder();
        
        for(Carta carta : cartas) {
            mazo.append(carta.toString()).append("\n");
        }
        
        return mazo.toString();
    }
    
    public int contarCartas() {
        return cartas.size();
    }
}
