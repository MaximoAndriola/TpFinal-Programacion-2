package model.clases.Cartas;

import model.enums.PaloCarta;
import model.enums.RangoCarta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {
    private static final int TAMANIO_MAZO = 52; // 13 cartas de 4 palos
    private static final int CANTIDAD_MAZOS = 5; // Se juega con 5 mazos
    private static final int CANTIDAD_CARTAS = TAMANIO_MAZO*CANTIDAD_MAZOS; //Cantidad total de cartas en juego
    private final List<Carta> mazo = new ArrayList<>(CANTIDAD_CARTAS);

    //Constructor
    public Mazo() {
        //Son 5 mazos, por lo tanto se instancia cada carta 5 veces
        for(int i = 0; i < CANTIDAD_MAZOS; i++) {
            // Instancia las cartas del mazo
            for (PaloCarta palo : PaloCarta.values()) {
                for (RangoCarta rango : RangoCarta.values()) {
                    mazo.add(new Carta(palo, rango));
                }
            }
        }
        this.mezclar();
    }

    //Metodos personalizados
    public void mezclar() {
        Collections.shuffle(mazo);
    }

    public Carta sacarCarta(){
        return mazo.removeFirst();
    }

    @Override
    public String toString() {
        StringBuilder mazo = new StringBuilder();
        
        for(Carta carta : this.mazo) {
            mazo.append(carta.toString()).append("\n");
        }
        
        return mazo.toString();
    }
}
