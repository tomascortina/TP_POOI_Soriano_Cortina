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
        hambre -= experiencia;  // Come con eficiencia debido a su experiencia
        System.out.println(nombre + " usa su experiencia para comer y reducir el hambre de manera efectiva. Hambre actual: " + hambre);
    }

    @Override
    public void habilidadEspecial() {
        fuerza += 15;  // Aumento temporal de fuerza debido a la experiencia en combate
        System.out.println(nombre + " usa su experiencia para fortalecerse temporalmente. Fuerza actual: " + fuerza);
    }
    public void planificarAtaque() {
        if (hambre > 10) {
            int energiaUtilizada = experiencia / 3; 
            fuerza += energiaUtilizada;
            velocidad += 5; 
            hambre -= 10; 
            System.out.println(nombre + " planifica un ataque cuidadoso, usando su experiencia.");
            System.out.println("Fuerza aumentada en: " + energiaUtilizada + ", nueva fuerza: " + fuerza);
            System.out.println("Velocidad incrementada a: " + velocidad + " y hambre reducida a: " + hambre);
        } else {
            System.out.println(nombre + " decide conservar energía debido a su bajo nivel de hambre.");
        }
    }
}