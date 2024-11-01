class Adulto extends Vampiro {
    private final String estrategia;

    public Adulto(String nombre, int fuerza, int velocidad, int hambre, String colorOjos, String estrategia) {
        super(nombre, fuerza, velocidad, hambre, colorOjos);
        this.estrategia = estrategia;
    }

    @Override
    public void comerAnimal(Animal animal) {
        if(hambre - animal.getEnergia() <= 0){
            hambre = 0;
        }
        else{
            hambre -= animal.getEnergia();
        }
        System.out.println(nombre + " se comió a un " + animal.getTipo() + " y se le restó " + animal.getEnergia() + " de hambre. Hambre actual: " + hambre);
    }

    @Override
    public void comer() {
        hambre -= 30;  // Come de manera estratégica y eficiente
        System.out.println(nombre + " aplica su estrategia" + estrategia + " para reducir el hambre de forma óptima. Hambre actual: " + hambre);
    }

    @Override
    public void habilidadEspecial() {
        velocidad += 10;  // Incremento de velocidad temporal
        System.out.println(nombre + " usa una estrategia avanzada para moverse rápidamente. Velocidad actual: " + velocidad);
    }
}