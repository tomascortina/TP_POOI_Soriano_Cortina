import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeerCSV {

    // Paso 1: Declarar la ruta del archivo CSV
    // Explicación: Antes de leer el archivo, necesitamos especificar su ubicación.
    // Aquí, estamos usando una ruta relativa, asumiendo que el archivo está en el
    // mismo directorio que el programa.
    public static void main(String[] args) {
        String archivoCSV = "personas.csv"; // Ruta del archivo CSV

        // Paso 2: Configurar el lector de archivos
        // Explicación: Usamos FileReader para abrir el archivo y BufferedReader para
        // leerlo línea por línea. BufferedReader es eficiente para leer grandes
        // archivos de texto.
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {

            // Paso 3: Inicializar variables necesarias
            // Explicación: Definimos variables antes de entrar en el bucle. Aquí,
            // 'linea' almacenará cada línea leída del archivo, y 'primeraLinea' nos
            // ayudará a identificar y omitir la cabecera del CSV.
            String linea;
            boolean primeraLinea = true;

            // Paso 4: Leer el archivo línea por línea
            // Explicación: Utilizamos un bucle while para leer todas las líneas del
            // archivo. El método readLine() devuelve null cuando se alcanzan todas las
            // líneas, lo que termina el bucle.
            while ((linea = br.readLine()) != null) {

                // Paso 5: Omitir la primera línea (cabecera)
                // Explicación: Los archivos CSV suelen tener una primera línea que
                // contiene los nombres de las columnas (cabecera). Esta línea no es
                // útil para el procesamiento de datos, por lo que la omitimos.
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                // Paso 6: Separar los datos de cada línea
                // Explicación: Cada línea del archivo CSV contiene múltiples valores
                // separados por comas. Usamos split(",") para dividir la línea en un
                // array de cadenas, donde cada elemento del array corresponde a un
                // valor del CSV.
                String[] datos = linea.split(",");

                // Paso 7: Convertir los datos a los tipos adecuados
                // Explicación: Los valores leídos desde el archivo son cadenas (String).
                // Para trabajar con estos datos, necesitamos convertirlos a sus tipos
                // correspondientes (int para la edad, double para el salario).
                String nombre = datos[0];
                int edad = Integer.parseInt(datos[1]); // Conversión de cadena a entero
                double salario = Double.parseDouble(datos[2]); // Conversión de cadena a decimal

                // Paso 8: Imprimir los datos leídos
                // Explicación: Una vez que los datos se han leído y convertido, los
                // imprimimos en la consola para verificar que todo funcione
                // correctamente.
                System.out.println("Nombre: " + nombre);
                System.out.println("Edad: " + edad);
                System.out.println("Salario: " + salario);
                System.out.println(); // Línea en blanco para separar cada registro
            }

        // Paso 9: Manejo de excepciones
        // Explicación: Es posible que ocurran errores al intentar leer un archivo,
        // como si el archivo no se encuentra en la ubicación especificada. Usamos un
        // bloque try-catch para manejar estas situaciones y evitar que el programa
        // se bloquee inesperadamente.
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage()); // Imprime un mensaje de error en la consola
        }
    }
}
