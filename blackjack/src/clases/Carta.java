package clases;

import enums.PaloCarta;
import enums.RangoCarta;

public class Carta {
    private final PaloCarta palo;
    private final RangoCarta rango;

    public Carta(PaloCarta palo, RangoCarta rango) {
        this.palo = palo;
        this.rango = rango;
    }

    public int getValor() {
        // Calcular puntos segun carta
        return 0;
    }

    @Override
    public String toString() {
        return rango + " de " + palo;
    }
}
