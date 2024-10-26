


class Adulto extends Vampiro {
    private String estratregia;
    public Adulto(String nombre, int fuerza, int velocidad, int hambre, String colorOjos, String estrategia) {
        super(nombre, fuerza, velocidad, hambre, colorOjos);
        this.estratregia = estrategia;  
    }

    @Override
    public void comer() {
        // Imp
    }

    @Override
    public void habilidadEspecial() {
        // Imp
    }
}