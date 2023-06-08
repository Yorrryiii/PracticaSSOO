package ficheros;
import java.util.Scanner;

public class Menu {

    private SistemaFicheros sistemaFicheros; // Instancia de la clase SistemaFicheros
    private Scanner scanner; // Instancia de Scanner para leer la entrada del usuario

    public Menu() {
        scanner = new Scanner(System.in); // Inicializar la instancia de Scanner
    }

    public void CantidadClusters() {
        System.out.print("Ingresa el tamaño del sistema de archivos: ");
        int tamaño = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        
        sistemaFicheros = new SistemaFicheros(tamaño); 
        int opcion;
        
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    do {
                        mostrarSubMenuCrear();
                        opcion = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        switch (opcion) {
                            case 1:
                                System.out.println("Introduce el nombre del archivo");
                                String nombreCrear = scanner.nextLine();
                                System.out.println("Introduce el tamaño del archivo");
                                int tamanoCrear = scanner.nextInt();
                                scanner.nextLine(); // Limpiar el buffer
                                System.out.println("Introduce la ruta donde crear el archivo");
                                String rutaCrear = scanner.nextLine();
                                sistemaFicheros.crearArchivo(nombreCrear, tamanoCrear, rutaCrear);
                                break;
                            case 2:
                                System.out.println("Introduce el nombre del directorio");
                                String nombreDirCrear = scanner.nextLine();
                                System.out.println("Introduce la ruta donde crear el directorio");
                                String rutaDirCrear = scanner.nextLine();
                                sistemaFicheros.crearDirectorio(nombreDirCrear, rutaDirCrear);
                                break;
                        }
                        System.out.println();
                    } while (opcion != 3);

                    break;
                case 2:
                    System.out.println("Introduce el nombre del archivo/directorio a mover");
                    String nombreMover = scanner.nextLine();
                    System.out.println("Introduce la ruta de destino");
                    String rutaMover = scanner.nextLine();
                    sistemaFicheros.mover(nombreMover, rutaMover);

                    break;
                case 3:
                    do {
                        mostrarSubMenuBorrar();
                        opcion = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        switch (opcion) {
                            case 1:
                                System.out.println("Introduce la ruta del archivo a borrar");
                                String RutaArchivoBorrar = scanner.nextLine();
                                sistemaFicheros.borrarArchivo(RutaArchivoBorrar);
                                break;
                            case 2:
                                System.out.println("Introduce la ruta del directorio a borrar");
                                String RutaDirectorioBorrar = scanner.nextLine();
                                sistemaFicheros.borrarDirectorio(RutaDirectorioBorrar);
                                break;
                        }
                        System.out.println();
                    } while (opcion != 3);

                    break;
                case 4:
                    System.out.println("Introduce la ruta del archivo a copiar");
                    String rutaCopiar = scanner.nextLine();
                    System.out.println("Introduce la carpeta de destino de la copia");
                    String destinoCopia = scanner.nextLine();
                    sistemaFicheros.copiarArchivo(rutaCopiar, destinoCopia);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor introduce una opción válida");
                    break;
            }
            System.out.println();
        } while (opcion != 5);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("======== MENÚ ========");
        System.out.println("1. Crear");
        System.out.println("2. Mover");
        System.out.println("3. Borrar");
        System.out.println("4. Copiar");
        System.out.println("5. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private static void mostrarSubMenuCrear() {
        System.out.println("======== MENÚ CREAR ========");
        System.out.println("1. Crear archivo");
        System.out.println("2. Crear directorio");
        System.out.println("3. Salir del menu de Creación");
        System.out.print("Selecciona una opción: ");
    }

    private static void mostrarSubMenuBorrar() {
        System.out.println("======== MENÚ BORRAR ========");
        System.out.println("1. Borrar archivo");
        System.out.println("2. Borrar directorio");
        System.out.println("3. Salir del menu de Borrado");
        System.out.print("Selecciona una opción: ");
    }
}

