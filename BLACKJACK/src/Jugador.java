import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private ArrayList<Carta> mano;
    private int puntos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
        this.puntos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void recibirCarta(Carta carta) {
        mano.add(carta);
    }

    public Carta jugarCarta() {
        if (mano.isEmpty()) {
            return null;
        }
        return mano.remove(0); // Juega la primera carta de la mano
    }

    public int getPuntos() {
        return puntos;
    }

    public void sumarPunto() {
        puntos++;
    }

    public void mostrarMano() {
        System.out.println(nombre + " tiene las siguientes cartas:");
        for (Carta carta : mano) {
            System.out.println(carta);
        }
    }
}