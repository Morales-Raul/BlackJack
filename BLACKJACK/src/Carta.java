public class Carta {
    private String baraja;
    private String valor;

    public Carta(String baraja, String valor) {
        this.baraja = baraja;
        this.valor = valor;
    }

    public String getBaraja() {
        return baraja;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor + " de " + baraja;
    }
}