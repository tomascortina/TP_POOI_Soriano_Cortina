package crepusculo;

public class Volturi {
    private int influenciaPolitica;
    public Volturi(int influenciaPolitica) {
        this.influenciaPolitica = influenciaPolitica;
    }

    public int leerPoder(Vampiro vampiro) {
        return vampiro.getFuerza() + vampiro.getVelocidad();
    }
}