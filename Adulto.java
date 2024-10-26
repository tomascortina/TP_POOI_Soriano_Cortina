public class Adulto extends Vampiro {
    private final String estrategia;

    public Adulto(String nombre, int fuerza, int velocidad, int hambre, String colorOjos, String estrategia) {
        super(nombre, fuerza, velocidad, hambre, colorOjos);
        this.estrategia = estrategia;
    }

    @Override
    public void comer() { /* Implementación específica */ }

    @Override
    public void habilidadEspecial() { /* Implementación específica */ }

    @Override
    public void comerAnimal(Animal animal) { /* Implementación específica */ }
}