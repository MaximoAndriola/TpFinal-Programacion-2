package model.clases.Ranking;

public class Apuesta implements Comparable<Apuesta> {
    String nombreUsuario;
    double apuestaRealizada;

    public void setApuestaRealizada(double apuestaRealizada) {
        this.apuestaRealizada = apuestaRealizada;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public double getApuestaRealizada() {
        return apuestaRealizada;
    }

    @Override
    public int compareTo(Apuesta otraApuesta) {
        return Double.compare(otraApuesta.apuestaRealizada, this.apuestaRealizada);
    }
}

