public class Persona {
    private String nombre;
    private int DNI;
    Persona(String nombre,int DNI){
        this.nombre = nombre;
        this.DNI = DNI;
    }
    public int getDNI() {
        return DNI;
    }
    public String getNombre() {
        return nombre;
    }
}
