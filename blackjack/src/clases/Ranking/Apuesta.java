package clases.Ranking;

public class Apuesta implements Comparable<Apuesta> {
    String nombreUsuario;
    double apuestaRealizada;

    public Apuesta(String nombreUsuario, double apuestaRealizada) {
        this.nombreUsuario = nombreUsuario;
        this.apuestaRealizada = apuestaRealizada;
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

