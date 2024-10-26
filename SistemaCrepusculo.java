import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaCrepusculo {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Vampiro> demetriusClan = new ArrayList<>();
    private final List<Vampiro> volturiClan = new ArrayList<>();
    private final List<Vampiro> cullenClan = new ArrayList<>();
    public final List<Animal> animalesComida = new ArrayList<>();

    public void run() {
        int opcion;
        do {
            mostrarMenu();
            opcion = numeroValidado(0, 6);
            procesarOpcion(opcion);
        } while (opcion != 0);
    }

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

    private void admitirVampiro() {
        Vampiro vampiro = crearVampiro();
        if (vampiro == null) return;

        if (vampiro.isVolturi()) {
            volturiClan.add(vampiro);
            System.out.println("El vampiro se ha unido al clan Volturi.");
        } else {
            asignarClan(vampiro);
        }

        System.out.println("Vampiro admitido exitosamente.");
    }

    private Vampiro crearVampiro() {
        System.out.print("Ingrese el nombre del vampiro: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la fuerza del vampiro: ");
        int fuerza = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese la velocidad del vampiro: ");
        int velocidad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el hambre del vampiro: ");
        int hambre = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el color de ojos del vampiro: ");
        String colorOjos = scanner.nextLine();

        boolean isVolturi = esVolturi();
        Volturi volturi = null;
        if (isVolturi) {
            System.out.print("Ingrese la influencia política del vampiro: ");
            int influenciaPolitica = scanner.nextInt();
            scanner.nextLine();
            volturi = new Volturi(influenciaPolitica);
        }

        System.out.print("Ingrese el tipo de vampiro (1: RecienConvertido, 2: Maduro, 3: Adulto): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        Vampiro vampiro = crearTipoVampiro(nombre, fuerza, velocidad, hambre, colorOjos, tipo);
        if (vampiro == null) return null;

        if (volturi != null) {
            vampiro.setVolturi(volturi);
        }

        return vampiro;
    }

    private boolean esVolturi() {
        while (true) {
            System.out.print("¿Es un vampiro Volturi? (1: sí, 2: no): ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1" -> {
                    return true;
                }
                case "2" -> {
                    return false;
                }
                default -> {
                    System.out.println("Opción no válida. Por favor, ingrese 1 para sí o 2 para no.");
                }
            }
        }
    }

    private Vampiro crearTipoVampiro(String nombre, int fuerza, int velocidad, int hambre, String colorOjos, int tipo) {
        switch (tipo) {
            case 1 -> {
                System.out.print("Ingrese la inestabilidad del vampiro: ");
                String inestabilidad = scanner.nextLine();
                return new RecienConvertido(nombre, fuerza, velocidad, hambre, colorOjos, inestabilidad);
            }
            case 2 -> {
                System.out.print("Ingrese la experiencia del vampiro: ");
                int experiencia = scanner.nextInt();
                scanner.nextLine();
                return new Maduro(nombre, fuerza, velocidad, hambre, colorOjos, experiencia);
            }
            case 3 -> {
                System.out.print("Ingrese la estrategia del vampiro: ");
                String estrategia = scanner.nextLine();
                return new Adulto(nombre, fuerza, velocidad, hambre, colorOjos, estrategia);
            }
            default -> {
                System.out.println("Tipo de vampiro no válido.");
                return null;
            }
        }
    }

    private void asignarClan(Vampiro vampiro) {
        System.out.println("Seleccione el clan al que desea unirse:");
        System.out.println("2. Cullen");
        System.out.println("3. Demetrius");
        int clanOption = scanner.nextInt();
        scanner.nextLine();

        switch (clanOption) {
            case 2 -> {
                cullenClan.add(vampiro);
                System.out.println("El vampiro se ha unido al clan Cullen.");
            }
            case 3 -> {
                demetriusClan.add(vampiro);
                System.out.println("El vampiro se ha unido al clan Demetrius.");
            }
            default -> System.out.println("Opción de clan no válida.");
        }
    }

    private void expulsarVampiro() {
        System.out.print("Ingrese el nombre del vampiro a expulsar: ");
        String nombre = scanner.nextLine();
        Vampiro vampiroAExpulsar = buscarVampiro(nombre, volturiClan);
        if (vampiroAExpulsar != null) {
            volturiClan.remove(vampiroAExpulsar);
            System.out.println("Vampiro expulsado exitosamente.");
        } else {
            System.out.println("Vampiro no encontrado.");
        }
    }

    private Vampiro buscarVampiro(String nombre, List<Vampiro> clan) {
        for (Vampiro vampiro : clan) {
            if (vampiro.getNombre().equals(nombre)) {
                return vampiro;
            }
        }
        return null;
    }

    private void comerAnimal() {
        System.out.print("Ingrese el tipo de animal: ");
        String tipo = scanner.nextLine();
        // Animal animal = new Animal(tipo);
        System.out.println("El vampiro ha comido un " + tipo);
    }

    private void listarVampiros() {
        System.out.println("Vampiros en el clan Volturi:");
        volturiClan.forEach(System.out::println);
        System.out.println("Vampiros en el clan Cullen:");
        cullenClan.forEach(System.out::println);
        System.out.println("Vampiros en el clan Demetrius:");
        demetriusClan.forEach(System.out::println);
    }

    private void obtenerVampiroMasApto() {
        // Implementación pendiente
    }

    // Menú

    private void mostrarMenu() {
        System.out.println("Sistema de Gestión de Crepúsculo");
        System.out.println("1. Admitir Vampiro");
        System.out.println("2. Expulsar Vampiro");
        System.out.println("3. Comer animal");
        System.out.println("4. Listar Vampiros");
        System.out.println("5. Obtener Vampiro más apto");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // Logica de validación de datos

    private int numeroValidado(int limInferior, int limSuperior) {
        int numero = 0;
        boolean esValido = false;
        while (!esValido) {
            try {
                numero = scannerNextInt();
                esValido = estaEnRango(numero, limInferior, limSuperior);
            } catch (Exception e) {
                System.out.println("Número inválido. Intente de nuevo.");
                scanner.nextLine();
            }
        }
        return numero;
    }

    private boolean estaEnRango(int numero, int limInferior, int limSuperior) {
        if (numero >= limInferior && numero <= limSuperior) {
            return true;
        } else {
            System.out.println("Número fuera de rango. Intente de nuevo.");
            return false;
        }
    }

    private int scannerNextInt(){
        int n = scanner.nextInt();
        scanner.nextLine();
        return n;
    }
}
