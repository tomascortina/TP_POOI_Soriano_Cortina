public class Maduro extends Vampiro {
    private final int experiencia;

    public Maduro(String nombre, int fuerza, int velocidad, int hambre, String colorOjos, int experiencia) {
        super(nombre, fuerza, velocidad, hambre, colorOjos);
        this.experiencia = experiencia;
    }

    @Override
    public void comer() { /* Implementación específica */ }

    @Override
    public void habilidadEspecial() { /* Implementación específica */ }

    @Override
    public void comerAnimal(Animal animal) { /* Implementación específica */ }
}