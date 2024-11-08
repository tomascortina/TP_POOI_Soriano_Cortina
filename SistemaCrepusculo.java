import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        cargarAnimalesDesdeCSV();
        agregarVariosVampirosCSV();
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
        boolean esVolturi = ingresoNumeroValidado("¿Es Volturi? (1: Sí, 2: No): ", 1, 2) == 1;
        if (esVolturi) {
            // Si es Volturi, se le permite ingresar al clan Volturi
            ingresarAVolturi(vampiro);
        } else {
            // Si no es Volturi, se le asigna a otro clan
            asignarClan(vampiro);
        }
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
        String tipoDeAnimal = ingresoStringValidado("Ingrese el tipo de animal que el vampiro " + nombreVampiroHambriento + " va a comer: ");
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
        while (true) {
            input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Por favor, ingrese un texto.");
            } else if (input.matches("\\d+")) {
                System.out.println("El texto no puede ser un número. Intente de nuevo.");
            } else {
                break;
            }
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
        while (opcion == 4) {
            System.out.println("Este vampiro no es Volturi y no puede unirse al clan Volturi.");
            opcion = ingresoNumeroValidado("Ingrese su opción: ", 1, 4);
        }
        clanes.get(opcion - 1).add(vampiro);
        System.out.println("Vampiro admitido exitosamente.");
    }

    private void ingresarAVolturi(Vampiro vampiro) {
        int influenciaPolitica = ingresoNumeroValidado("Ingrese la influencia política del vampiro: ", 1, 99999);
        vampiro.setVolturi(new Volturi(influenciaPolitica));
        clanes.get(3).add(vampiro);  // Suponiendo que Volturi es el cuarto clan en la lista
    }


    // Metodos para creacion de animales e inicializacion de clanes

    private void cargarAnimalesDesdeCSV() {
        String archivoCSV = "animales.csv"; // Ruta del archivo CSV

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false; // Omitir la primera línea que contiene los encabezados
                    continue;
                }

                String[] datos = linea.split(",");
                String nombre = datos[0];
                int puntajeEnergia = Integer.parseInt(datos[1]);

                animalesComida.add(new Animal(nombre, puntajeEnergia));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }


    private void agregarVariosVampirosCSV() {
        String archivoCSV = "vampiros.csv"; // Ruta del archivo CSV

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] datos = linea.split(",");
                Vampiro vampiro = crearVampiroCSV(datos);
                asignarClanCSV(vampiro, Integer.parseInt(datos[2]));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private Vampiro crearVampiroCSV(String[] datos) {
        int tipo = Integer.parseInt(datos[0]);
        boolean esVolturi = Boolean.parseBoolean(datos[1]);
        String nombre = datos[3];
        int fuerza = Integer.parseInt(datos[4]);
        int velocidad = Integer.parseInt(datos[5]);
        int hambre = Integer.parseInt(datos[6]);
        String colorOjos = datos[7];
        String extra = datos[8];

        Vampiro vampiro;
        switch (tipo) {
            case 1 -> vampiro = new RecienConvertido(nombre, fuerza, velocidad, hambre, colorOjos, Integer.parseInt(extra));
            case 2 -> vampiro = new Maduro(nombre, fuerza, velocidad, hambre, colorOjos, Integer.parseInt(extra));
            case 3 -> vampiro = new Adulto(nombre, fuerza, velocidad, hambre, colorOjos, extra);
            default -> throw new IllegalArgumentException("Tipo de vampiro no válido.");
        }

        if (esVolturi) {
            vampiro.setVolturi(new Volturi(Integer.parseInt(datos[9])));
        }

        return vampiro;
    }

    private void asignarClanCSV(Vampiro vampiro, int clan) {
        switch (clan) {
            case 1 -> cullenClan.add(vampiro);
            case 2 -> demetriusClan.add(vampiro);
            case 3 -> volturiClan.add(vampiro);
            case 4 -> sinClan.add(vampiro);
            default -> throw new IllegalArgumentException("Clan no válido en el archivo CSV.");
        }
    }


    

    private void crearClanes(){
        clanes.add(demetriusClan);
        clanes.add(volturiClan);
        clanes.add(cullenClan);
        clanes.add(sinClan);
    }
}
