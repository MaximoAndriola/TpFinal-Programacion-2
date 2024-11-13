package model.clases.Jugadores;

import model.clases.Cartas.Mano;
import model.clases.Cartas.Mazo;

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

    //Metodos custom
    public Mano pedirCarta (Mazo mazo){
        mano.agregarCarta(mazo.sacarCarta());
        return mano;
    }

    public void vaciarMano (){
        setMano(new Mano());
    }

    @Override
    public String toString() {
        return  "Nombre: " + nombre +
                ", Mano: " + mano;
    }
}
