public abstract class Vampiro {
    protected final String nombre;
    protected final int fuerza;
    protected final int velocidad;
    protected final int hambre;
    protected final String colorOjos;
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
    public abstract void comerAnimal(Animal animal);

    public void setVolturi(Volturi volturi) {
        this.volturi = volturi;
    }

    public boolean isVolturi() {
        return volturi != null;
    }

    public String getNombre() {
        return nombre;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getVelocidad() {
        return velocidad;
    }
}