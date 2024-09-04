import java.util.ArrayList;
import java.util.Scanner;

public class JuegoDeCartas {
    private Baraja baraja;
    private ArrayList<Jugador> jugadores;
    private int numeroDeRondas;

    public JuegoDeCartas(int numeroDeJugadores, int numeroDeRondas) {
        baraja = new Baraja();
        baraja.barajar();
        jugadores = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= numeroDeJugadores; i++) {
            System.out.print("Ingrese el nombre del jugador " + i + ": ");
            String nombre = scanner.nextLine();
            jugadores.add(new Jugador(nombre));
        }

        this.numeroDeRondas = numeroDeRondas;
    }

    public void repartirCartas(int numeroDeCartas) {
        for (int i = 0; i < numeroDeCartas; i++) {
            for (Jugador jugador : jugadores) {
                jugador.recibirCarta(baraja.repartirCarta());
            }
        }
    }

    public void jugarRonda() {
        Jugador ganador = null;
        Carta cartaGanadora = null;

        for (Jugador jugador : jugadores) {
            Carta cartaJugada = jugador.jugarCarta();
            System.out.println(jugador.getNombre() + " jugó " + cartaJugada);

            if (cartaGanadora == null || compararCartas(cartaJugada, cartaGanadora) > 0) {
                cartaGanadora = cartaJugada;
                ganador = jugador;
            } else if (compararCartas(cartaJugada, cartaGanadora) == 0) {
                ganador = null; // Empate
            }
        }

        if (ganador != null) {
            ganador.sumarPunto();
            System.out.println("Ganador de la ronda: " + ganador.getNombre());
        } else {
            System.out.println("Ronda empatada, no se asignan puntos.");
        }
    }

    private int compararCartas(Carta carta1, Carta carta2) {
        String[] valores = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int valor1 = -1;
        int valor2 = -1;

        for (int i = 0; i < valores.length; i++) {
            if (carta1.getValor().equals(valores[i])) {
                valor1 = i;
            }
            if (carta2.getValor().equals(valores[i])) {
                valor2 = i;
            }
        }

        return Integer.compare(valor1, valor2);
    }

    public void jugar() {
        repartirCartas(5); // Reparte 5 cartas a cada jugador

        for (int i = 0; i < numeroDeRondas; i++) {
            System.out.println("\nRonda " + (i + 1));
            jugarRonda();
        }

        mostrarGanador();
    }

    public void mostrarGanador() {
        Jugador ganador = null;
        int maxPuntos = -1;

        for (Jugador jugador : jugadores) {
            if (jugador.getPuntos() > maxPuntos) {
                maxPuntos = jugador.getPuntos();
                ganador = jugador;
            } else if (jugador.getPuntos() == maxPuntos) {
                ganador = null; // Empate
            }
        }

        if (ganador != null) {
            System.out.println("\nGanador del juego: " + ganador.getNombre() + " con " + ganador.getPuntos() + " puntos.");
        } else {
            System.out.println("\nEl juego terminó en empate.");
        }
    }
}
