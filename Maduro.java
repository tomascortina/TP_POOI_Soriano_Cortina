package crepusculo;

class Maduro extends Vampiro {
    private int experiencia;
    public Maduro(String nombre, int fuerza, int velocidad, int hambre, String colorOjos, int experiencia) {
        super(nombre, fuerza, velocidad, hambre, colorOjos);
        this.experiencia = experiencia;
    }

    @Override
    public void comer() {
        // Imp
    }

    @Override
    public void habilidadEspecial() {
        // Imp
    }

    @Override
    public void comerAnimal(Animal animal) {
        //
    }
}
