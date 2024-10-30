import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaCrepusculo {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Vampiro> cullenClan = new ArrayList<>();
    private final List<Vampiro> demetriusClan = new ArrayList<>();
    private final List<Vampiro> volturiClan = new ArrayList<>();
    private final List<Vampiro> sinClan = new ArrayList<>();
    private final List<List> clanes = new ArrayList<>();
    private final List<Animal> animalesComida = new ArrayList<>();

    public void run() {
        crearAnimales();
        agregarVariosVampiros();
        crearClanes();
        int opcion;
        do {
            printMenu();
            opcion = ingresoNumeroValidado("Seleccione una opción: ", 0, 5);
            procesarOpcion(opcion);
        } while (opcion != 0);
    }

    // Metodos del el menu

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> admitirVampiro();
            case 2 -> expulsarVampiro();
            case 3 -> comerAnimal();
            case 4 -> listarVampiros();
            case 5 -> obtenerVampiroMasApto();
            case 0 -> System.out.println("Saliendo del sistema...");
        }
    }

    private void printMenu() {
        System.out.println("Sistema de Gestión de Crepúsculo");
        System.out.println("1. Admitir Vampiro\n2. Expulsar Vampiro\n3. Comer animal\n4. Listar Vampiros\n5. Obtener Vampiro más apto\n0. Salir");
    }

    private void admitirVampiro() {
        Vampiro vampiro = crearVampiro();
        asignarClan(vampiro);
    }

    private void expulsarVampiro() {
        String nombre = ingresoStringValidado("Ingrese el nombre del vampiro a expulsar: ");
        for (List<Vampiro> clan : clanes) {
            Vampiro vampiroAExpulsar = clan.stream().filter(v -> v.getNombre().equals(nombre)).findFirst().orElse(null);
            if (vampiroAExpulsar != null) {
                clan.remove(vampiroAExpulsar);
                System.out.println("Vampiro expulsado exitosamente.");
                return;
            }
        }
        System.out.println("Vampiro no encontrado.");
    }


    private void comerAnimal() {
        String nombreVampiroHambriento = ingresoStringValidado("Ingrese el nombre del vampiro que va a comer: ");
        String tipoDeAnimal = ingresoStringValidado("Ingrese el tipo de animal que el vampiro" + nombreVampiroHambriento + " va a comer: ");
        Animal animal = encontrarAnimalPorTipo(tipoDeAnimal);
        Vampiro vampiroHambriento = encontrarVampiroPorNombre(nombreVampiroHambriento);
        vampiroHambriento.comerAnimal(animal);
    }

    private void listarVampiros() {
        String[] nombresClanes = {"Clan Cullen", "Clan Demetrius", "Clan Volturi", "Sin Clan"};
        for (int i = 0; i < clanes.size(); i++) {
            System.out.println("Vampiros en el " + nombresClanes[i] + ":");
            List<Vampiro> clan = clanes.get(i);
            clan.forEach(System.out::println);
        }
    }

    private void obtenerVampiroMasApto() {
        Vampiro vampiroMasApto = null;
        for (List<Vampiro> clan : clanes) {
            for (Vampiro vampiro : clan) {
                if (vampiroMasApto == null || 
                    (vampiro.getFuerza() > vampiroMasApto.getFuerza() && 
                    vampiro.getVelocidad() > vampiroMasApto.getVelocidad() && 
                    vampiro.getHambre() < vampiroMasApto.getHambre())) {
                        vampiroMasApto = vampiro;
                }
            }
        }
        if (vampiroMasApto != null) {
            System.out.println("El vampiro más apto es: " + vampiroMasApto);
        } else {
            System.out.println("No hay vampiros en el sistema.");
        }
    }

    // Metodos para validacion de inputs

    private int ingresoNumeroValidado(String mensaje, int limInferior, int limSuperior) {
        int numero;
        do {
            numero = scannerNextInt(mensaje);
        } while (!estaEnRango(numero, limInferior, limSuperior));
        return numero;
    }

    private boolean estaEnRango(int numero, int limInferior, int limSuperior) {
        if (numero >= limInferior && numero <= limSuperior) {
            return true;
        }
        System.out.println("Número fuera de rango. Intente de nuevo.");
        return false;
    }

    private int scannerNextInt(String mensaje) {
        System.out.println(mensaje);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Intente de nuevo.");
            }
        }
    }

    private String ingresoStringValidado(String mensaje) {
        System.out.println(mensaje);
        String input;
        while ((input = scanner.nextLine()).isEmpty()) {
            System.out.println("Por favor, ingrese un texto.");
        }
        return input;
    }

    // Metodos para busqueda de vampiros y animales

    private Vampiro encontrarVampiroPorNombre(String nombre) {
        for (List<Vampiro> clan : clanes) {
            for (Vampiro vampiro : clan) {
                if (vampiro.getNombre().equals(nombre)) {
                    return vampiro;
                }
            }
        }
        return null;
    }
    
    private Animal encontrarAnimalPorTipo(String tipo) {
        for (Animal animal : animalesComida) {
            if (animal.getTipo().equalsIgnoreCase(tipo)) {
                return animal;
            }
        }
        return null;
    }

    // Metodos para creacion de vampiros

    private Vampiro crearVampiro() {
        String nombre = ingresoStringValidado("Ingrese el nombre del vampiro: ");
        int fuerza = ingresoNumeroValidado("Ingrese la fuerza del vampiro: ", 1, 99999);
        int velocidad = ingresoNumeroValidado("Ingrese la velocidad del vampiro: ", 1, 99999);
        int hambre = ingresoNumeroValidado("Ingrese el hambre del vampiro: ", 1, 99999);
        String colorOjos = ingresoStringValidado("Ingrese el color de ojos del vampiro: ");
        int tipo = ingresoNumeroValidado("Ingrese el tipo de vampiro (1: RecienConvertido, 2: Maduro, 3: Adulto): ", 1, 3);
        return tipoDeVampiro(nombre,fuerza,velocidad,hambre,colorOjos,tipo);
    }

    private Vampiro tipoDeVampiro(String nombre, int fuerza, int velocidad, int hambre, String colorOjos, int tipo){
        return switch (tipo) {
            case 1 -> new RecienConvertido(nombre, fuerza, velocidad, hambre, colorOjos, ingresoNumeroValidado("Ingrese la inestabilidad del vampiro: ", 1, 99999));
            case 2 -> new Maduro(nombre, fuerza, velocidad, hambre, colorOjos, ingresoNumeroValidado("Ingrese la experiencia del vampiro: ", 1, 99999));
            case 3 -> new Adulto(nombre, fuerza, velocidad, hambre, colorOjos, ingresoStringValidado("Ingrese la estrategia del vampiro: "));
            default -> throw new IllegalArgumentException("Tipo de vampiro no válido.");
        };
    }

    private void asignarClan(Vampiro vampiro) {
        System.out.println("Seleccione el clan al que desea unirse:\n1. Cullen\n2. Demetrius\n3. Sin Clan\n4. Volturi");
        int opcion = ingresoNumeroValidado("Ingrese su opción: ", 1, 4);
        if (opcion == 4) {
            ingresarAVolturi(vampiro);
        } else {
            clanes.get(opcion - 1).add(vampiro);
        }
        System.out.println("Vampiro admitido exitosamente.");
    }

    private void ingresarAVolturi(Vampiro vampiro) {
        int influenciaPolitica = ingresoNumeroValidado("Ingrese la influencia política del vampiro: ", 1, 99999);
        vampiro.setVolturi(new Volturi(influenciaPolitica));
        clanes.get(3).add(vampiro);  // Suponiendo que Volturi es el cuarto clan en la lista
    }


    // Metodos para creacion de animales e inicializacion de clanes

    private void crearAnimales() {
        animalesComida.add(new Animal("Venado", 80));
        animalesComida.add(new Animal("Conejo", 30));
        animalesComida.add(new Animal("Lobo", 70));
        animalesComida.add(new Animal("Zorro", 50));
        animalesComida.add(new Animal("Jabalí", 90));
        animalesComida.add(new Animal("Puma", 100));
        animalesComida.add(new Animal("Búho", 20));
        animalesComida.add(new Animal("Águila", 60));
    }

    private void agregarVariosVampiros() {
        Vampiro vampiro1 = new RecienConvertido("Edward", 90, 85, 30, "Dorado", 90);
        Vampiro vampiro2 = new Maduro("Alice", 70, 95, 20, "Dorado", 50);
        Vampiro vampiro3 = new Adulto("Carlisle", 80, 80, 10, "Dorado", "Estratega");
        Vampiro vampiro4 = new RecienConvertido("Jane", 85, 80, 25, "Rojo", 60);
        Vampiro vampiro5 = new Maduro("Aro", 95, 70, 15, "Rojo", 100);
        Vampiro vampiro6 = new Adulto("Marcus", 75, 75, 5, "Rojo", "Diplomático");
        Vampiro vampiro7 = new RecienConvertido("Demetri", 85, 90, 20, "Rojo", 90);
        Vampiro vampiro8 = new Maduro("Felix", 80, 85, 25, "Rojo", 60);
        Vampiro vampiro9 = new Adulto("Heidi", 75, 80, 15, "Rojo", "Cazadora");
        Vampiro vampiro10 = new RecienConvertido("Garrett", 70, 75, 30, "Rojo", 30);
        Vampiro vampiro11 = new Maduro("Riley", 65, 70, 35, "Rojo", 40);
        Vampiro vampiro12 = new Adulto("Victoria", 90, 95, 10, "Rojo", "Estratega");

        demetriusClan.add(vampiro7);
        demetriusClan.add(vampiro8);
        demetriusClan.add(vampiro9);
        sinClan.add(vampiro10);
        sinClan.add(vampiro11);
        sinClan.add(vampiro12);
        cullenClan.add(vampiro1);
        cullenClan.add(vampiro2);
        cullenClan.add(vampiro3);
        volturiClan.add(vampiro4);
        volturiClan.add(vampiro5);
        volturiClan.add(vampiro6);
    }

    private void crearClanes(){
        clanes.add(demetriusClan);
        clanes.add(volturiClan);
        clanes.add(cullenClan);
        clanes.add(sinClan);
    }
}
