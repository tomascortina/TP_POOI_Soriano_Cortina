package crepusculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaCrepusculo {
    private Scanner scanner = new Scanner(System.in);
    private List<Vampiro> demetriusClan = new ArrayList<>(); // Vampiros Cualquieras, no volturi
    private List<Vampiro> volturiClan = new ArrayList<>();   // Vampiros solo Volturi
    private List<Vampiro> cullenClan = new ArrayList<>();    // Vampiros no Volturi
    public List<Animal> animalesComida = new ArrayList<>();

    public void run() {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    admitirVampiro();
                    break;
                case 2:
                    expulsarVampiro();
                    break;
                case 3:
                    comerAnimal();
                    break;
                case 4:
                    listarVampiros();
                    break;
                case 5:
                    obtenerVampiroMasApto();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

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

    private void admitirVampiro() {
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
        scanner.nextLine(); // Consumir nueva línea
        System.out.print("Ingrese el color de ojos del vampiro: ");
        String colorOjos = scanner.nextLine();
    
        // Logic for checking if the vampire is a Volturi
        boolean isVolturi = false;
        while (true) {
            System.out.print("¿Es un vampiro Volturi? (1: sí, 2: no): ");
            String input = scanner.nextLine().trim();
            if (input.equals("1")) {
                isVolturi = true;
                break;
            } else if (input.equals("2")) {
                isVolturi = false;
                break;
            } else {
                System.out.println("Opción no válida. Por favor, ingrese 1 para sí o 2 para no.");
            }
        }
    
        Volturi volturi = null;
        if (isVolturi) {
            System.out.print("Ingrese la influencia política del vampiro: ");
            int influenciaPolitica = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea
            volturi = new Volturi(influenciaPolitica);
        }
    
        System.out.print("Ingrese el tipo de vampiro (1: RecienConvertido, 2: Maduro, 3: Adulto): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 
    
        String inestabilidad = "";
        int experiencia = 0;
        String estrategia = "";
    
        if (tipo == 1) {
            System.out.print("Ingrese la inestabilidad del vampiro: ");
            inestabilidad = scanner.nextLine();
        } else if (tipo == 2) {
            System.out.print("Ingrese la experiencia del vampiro: ");
            experiencia = scanner.nextInt();
            scanner.nextLine();
        } else if (tipo == 3) {
            System.out.print("Ingrese la estrategia del vampiro: ");
            estrategia = scanner.nextLine();
        }
    
        Vampiro vampiro;
        switch (tipo) {
            case 1:
                vampiro = new RecienConvertido(nombre, fuerza, velocidad, hambre, colorOjos, inestabilidad);
                break;
            case 2:
                vampiro = new Maduro(nombre, fuerza, velocidad, hambre, colorOjos, experiencia);
                break;
            case 3:
                vampiro = new Adulto(nombre, fuerza, velocidad, hambre, colorOjos, estrategia);
                break;
            default:
                System.out.println("Tipo de vampiro no válido.");
                return;
        }
    
        if (volturi != null) {
            vampiro.setVolturi(volturi);
            volturiClan.add(vampiro);
            System.out.println("El vampiro se ha unido al clan Volturi.");
        } else {
            System.out.println("Seleccione el clan al que desea unirse:");
            System.out.println("2. Cullen");
            System.out.println("3. Demetrius");
            int clanOption = scanner.nextInt();
            scanner.nextLine(); 
    
            switch (clanOption) {
                case 2:
                    cullenClan.add(vampiro);
                    System.out.println("El vampiro se ha unido al clan Cullen.");
                    break;
                case 3:
                    demetriusClan.add(vampiro);
                    System.out.println("El vampiro se ha unido al clan Demetrius.");
                    break;
                default:
                    System.out.println("Opción de clan no válida.");
                    return;
            }
        }
    
        System.out.println("Vampiro admitido exitosamente.");
    }
    

    private void expulsarVampiro() {
        System.out.print("Ingrese el nombre del vampiro a expulsar: ");
        String nombre = scanner.nextLine();
        Vampiro vampiroAExpulsar = null;
        for (Vampiro vampiro : volturiClan) {
            // if (vampiro.getNombre().equals(nombre)) {
            //     vampiroAExpulsar = vampiro;
            //     break;
            // }
        }
        if (vampiroAExpulsar != null) {
            volturiClan.remove(vampiroAExpulsar);
            System.out.println("Vampiro expulsado exitosamente.");
        } else {
            System.out.println("Vampiro no encontrado.");
        }
    }

    private void comerAnimal() {
        System.out.print("Ingrese el tipo de animal: ");
        String tipo = scanner.nextLine();
        // Animal animal = new Animal(tipo);
        System.out.println("El vampiro ha comido un " );
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
    }
}
