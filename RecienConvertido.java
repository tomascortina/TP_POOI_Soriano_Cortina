
public class RecienConvertido extends Vampiro {
    private final String inestabilidad;

    public RecienConvertido(String nombre, int fuerza, int velocidad, int hambre, String colorOjos, String inestabilidad) {
        super(nombre, fuerza, velocidad, hambre, colorOjos);
        this.inestabilidad = inestabilidad;
    }

    @Override
    public void comer() { /* Implementación específica */ }

    @Override
    public void habilidadEspecial() { /* Implementación específica */ }

    @Override
    public void comerAnimal(Animal animal) { /* Implementación específica */ }
}