package crepusculo;


// Clases que extienden de Vampiro
class RecienConvertido extends Vampiro implements ComerAnimal{
    private String inestabilidad;
    public RecienConvertido(String nombre, int fuerza, int velocidad, int hambre, String colorOjos, String inestabilidad) {
        super(nombre, fuerza, velocidad, hambre, colorOjos);
        this.inestabilidad = inestabilidad;
    }



    @Override
    public void comerAnimal(Animal animal) {
        //
    }

    @Override
    public void comer() {
        // Implemen
    }

    @Override
    public void habilidadEspecial() {
        // Implem
    }
}