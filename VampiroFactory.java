public class VampiroFactory {

    public static Vampiro crearVampiro(int tipo, String nombre, int fuerza, int velocidad, int hambre, String colorOjos, String atributoExtra) {
        switch (tipo) {
            case 1 -> {
                return new RecienConvertido(nombre, fuerza, velocidad, hambre, colorOjos, atributoExtra);
            }
            case 2 -> {
                int experiencia = Integer.parseInt(atributoExtra);
                return new Maduro(nombre, fuerza, velocidad, hambre, colorOjos, experiencia);
            }
            case 3 -> {
                return new Adulto(nombre, fuerza, velocidad, hambre, colorOjos, atributoExtra);
            }
            default -> throw new IllegalArgumentException("Tipo de vampiro no válido");
        }
    }
}
