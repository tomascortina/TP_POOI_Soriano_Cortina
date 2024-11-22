class RecienConvertido extends Vampiro implements ComerAnimal {
    private final int inestabilidad;

    public RecienConvertido(String nombre, int fuerza, int velocidad, int hambre, String colorOjos, int inestabilidad) {
        super(nombre, fuerza, velocidad, hambre, colorOjos);
        this.inestabilidad = inestabilidad;
    }

    @Override
    public void comerAnimal(Animal animal) {
        if(hambre - 3 * animal.getEnergia() <= 0){
            hambre = 0;
        }
        else{
            hambre -= 3 * animal.getEnergia();
        }
        
        System.out.println(nombre + " se comió a un " + animal.getTipo() + " y se le restó " + 3 * animal.getEnergia() + " de hambre. Hambre actual: " + hambre);
    }

    @Override
    public void comer() {
        hambre -= 10;  // Come de manera menos eficiente
        System.out.println(nombre + " intenta calmar su hambre con una caza rápida. Hambre actual: " + hambre);
    }

    @Override
    public void habilidadEspecial() {
        hambre += inestabilidad;  // Aumento de hambre debido a la inestabilidad
        System.out.println(nombre + " pierde el control y su hambre aumenta debido a un ataque desenfrenado. Hambre actual: " + hambre);
    }
    public void perderControl() {
        if (inestabilidad > 5) {
            hambre += inestabilidad; 
            fuerza -= 10; 
            System.out.println(nombre + " pierde el control debido a su inestabilidad. Hambre aumentada a: " + hambre);
            System.out.println("Por el ataque impulsivo, pierde 10 puntos de fuerza. Fuerza actual: " + fuerza);
            System.out.println("El entorno sufre daños debido al frenesí del ataque.");
        } else {
            System.out.println(nombre + " logra mantener un mínimo de control, aunque sigue siendo inestable.");
        }
    }
}