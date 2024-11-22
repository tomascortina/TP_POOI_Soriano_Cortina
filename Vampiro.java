abstract class Vampiro implements ComerAnimal{
    protected String nombre;
    protected int fuerza;
    protected int velocidad;
    protected int hambre;
    protected String colorOjos;
    protected Volturi volturi;

    public Vampiro(String nombre, int fuerza, int velocidad, int hambre, String colorOjos) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
        this.hambre = hambre;
        this.colorOjos = colorOjos;
    }

    public abstract void comer();
    public abstract void habilidadEspecial();

    public void setVolturi(Volturi volturi) {
        this.volturi = volturi;
    }

    public boolean isVolturi() {
        return volturi != null;
    }

    @Override
    public void comerAnimal(Animal animal) {
        System.out.println("Vampiro comiendo animal");
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Fuerza: " + fuerza + ", Velocidad: " + velocidad + ", Hambre: " + hambre + ", Color de Ojos: " + colorOjos;
    }

    public String getNombre(){
        return nombre;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getHambre() {
        return hambre;
    }
}
