// Clase Animal (alimento para los vampiros)
class Animal {
    private final String tipo;
    private final int energia;

    public Animal(String tipo, int energia) {
        this.energia = energia;
        this.tipo = tipo;
    }
    
    public int getEnergia() {
        return energia;
    }

    public String getTipo() {
        return tipo;
    }
}
