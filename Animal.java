package crepusculo;

// Clase Animal (alimento para los vampiros)
class Animal {
    private String tipo;
    private int energia;

    public Animal(String tipo, int energia) {
        this.energia = energia;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
