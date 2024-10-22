package clases.Jugadores;

import clases.Cartas.Mano;

public abstract class ActorBlackjack {
    private String nombre;
    private Mano mano;

    public ActorBlackjack(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Mano getMano() {
        return mano;
    }

    public void setMano(Mano mano) {
        this.mano = mano;
    }

    @Override
    public String toString() {
        return  "nombre: " + nombre +
                ", mano: " + mano;
    }
}
