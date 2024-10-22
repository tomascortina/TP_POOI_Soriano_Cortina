public abstract class Vampiro {
    public String nombre;
    public int fuerza;
    public int velocidad;
    public int hambre;
    public String colorOjos;

    public Vampiro(String nombre, int fuerza, int velocidad, int hambre, String colorOjos) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
        this.hambre = hambre;
        this.colorOjos = colorOjos;
    }

    // Métodos abstractos que las subclases deben implementar
    public abstract void atacar();
    public abstract void alimentarse();
}