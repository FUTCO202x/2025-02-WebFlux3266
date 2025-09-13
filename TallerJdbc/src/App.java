import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class App {

    static String url = "jdbc:mysql://localhost:3306/escuela";
    static String userName = "root";
    static String password = "020049";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(url, userName, password);
             Scanner in = new Scanner(System.in)) {

            EstudianteService estudianteService = new EstudianteService(conn);

            int opcion;
            do {
                System.out.println();
                System.out.println("Menu principal");
                System.out.println("1. Insertar estudiante.");
                System.out.println("2. Actualizar estudiante.");
                System.out.println("3. Eliminar estudiante.");
                System.out.println("4. Consultar todos los estudiantes.");
                System.out.println("5. Consultar estudiante por correo.");
                System.out.println("0. Salir.");
                System.out.print(" Elige una opcion: ");
                opcion = in.nextInt();
                in.nextLine();

                switch (opcion) {
                    case 1 -> {
                        System.out.println();
                        System.out.print("Nombre: ");
                        String nombre = in.nextLine();
                        System.out.print("Apellido: ");
                        String apellido = in.nextLine();
                        System.out.print("Correo: ");
                        String correo = in.nextLine();
                        System.out.print("Edad: ");
                        int edad = in.nextInt();
                        in.nextLine();

                        System.out.println("Estado civil (1.Soltero 2.Casado 3.Viudo 4.Union libre 5.Divorciado): ");
                        int ec = in.nextInt();
                        in.nextLine();
                        EstadoCivil estadoCivil = EstadoCivil.values()[ec - 1];

                        estudianteService.insertarEstudiante(nombre, apellido, correo, edad, estadoCivil);
                    }
                    case 2 -> {
                        System.out.println();
                        System.out.print("Correo del estudiante a actualizar: ");
                        String correo = in.nextLine();
                        System.out.println("Campo a actualizar (nombre, apellido, correo, edad, estado_civil): ");
                        String campo = in.nextLine();
                        System.out.print("Nuevo valor: ");
                        String nuevoValor = in.nextLine();

                        estudianteService.actualizarEstudiante(correo, campo, nuevoValor);
                    }
                    case 3 -> {
                        System.out.println();
                        System.out.print("Correo del estudiante a eliminar: ");
                        String correo = in.nextLine();
                        estudianteService.eliminarEstudiante(correo);
                    }
                    case 4 -> estudianteService.consultarTodosLosEstudiantes();
                    case 5 -> {
                        System.out.println();
                        System.out.print("Correo a buscar: ");
                        String correo = in.nextLine();
                        estudianteService.consultarEstudiantePorCorreo(correo);
                    }
                    case 0 -> {
                        System.out.println();
                        System.out.println(" Saliendo...");
                    }
                    default -> System.out.println(" Opción no válida.");
                }
            } while (opcion != 0);

        } catch (Exception e) {
            System.out.println();
            System.out.println(" Error de conexión: " + e.getMessage());
        }
    }

}