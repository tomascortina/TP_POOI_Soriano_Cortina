class Maduro extends Vampiro {
    private final int experiencia;

    public Maduro(String nombre, int fuerza, int velocidad, int hambre, String colorOjos, int experiencia) {
        super(nombre, fuerza, velocidad, hambre, colorOjos);
        this.experiencia = experiencia;
    }

    @Override
    public void comerAnimal(Animal animal) {
        if(hambre - 2 * animal.getEnergia()<=0){
            hambre = 0;
            System.out.println("hambre = 0");
        }
        else{
            System.out.println("hambre > 0");
            hambre -= 2 * animal.getEnergia();
        }
        System.out.println(nombre + " se comió a un " + animal.getTipo() + " y se le restó " + 2 * animal.getEnergia() + " de hambre. Hambre actual: " + hambre);
    }

    @Override
    public void comer() {
        hambre -= experiencia; 
        System.out.println(nombre + " usa su experiencia para comer y reducir el hambre de manera efectiva. Hambre actual: " + hambre);
    }

    @Override
    public void habilidadEspecial() {
        fuerza += 15;
        System.out.println(nombre + " usa su experiencia para fortalecerse temporalmente. Fuerza actual: " + fuerza);
    }
}