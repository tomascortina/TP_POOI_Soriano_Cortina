public class Animal {
    private final String tipo;
    private final int energia;

    public Animal(String tipo, int energia) {
        this.tipo = tipo;
        this.energia = energia;
    }

    public String getTipo() {
        return tipo;
    }

    public int getEnergia() {
        return energia;
    }
}
