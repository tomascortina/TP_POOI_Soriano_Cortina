

public class Volturi {
    private final int influenciaPolitica;
    public Volturi(int influenciaPolitica) {
        this.influenciaPolitica = influenciaPolitica;
    }

    public int leerPoder(Vampiro vampiro) {
        return vampiro.getFuerza() + vampiro.getVelocidad();
    }

    public int getInfluenciaPolitica() {
        return influenciaPolitica;
    }
}