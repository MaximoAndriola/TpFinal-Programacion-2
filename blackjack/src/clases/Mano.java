package clases;

public class Mano {
    // No se pueden tener ni mas ni menos que 2 cartas en la mano
    private static final int TAMANIO_MANO = 2;
    private Carta[] cartas = new Carta[TAMANIO_MANO];

    /**
     * Muestra las cartas actualmente en la mano
     */
    public void mostrarMano() {
        for (Carta carta : cartas) {
            System.out.println(carta);
        }
    }
    
    
}
