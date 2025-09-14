//DANIEL MUNOZ y KATHERINE CORREA


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class App {
    static String url = "jdbc:mysql://localhost:3306/escuela"; 
    static String userName = "root";
    static String password = "Pass_123";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(url, userName, password);
             Scanner in = new Scanner(System.in)) {

            EstudianteServices estudianteServices = new EstudianteServices();
            int opcion = 0;

            while (opcion != 6) {
                System.out.println("\n===== MENÚ ESTUDIANTES =====");
                System.out.println("1. Insertar Estudiante");
                System.out.println("2. Actualizar Estudiante");
                System.out.println("3. Eliminar Estudiante");
                System.out.println("4. Consultar todos los Estudiantes");
                System.out.println("5. Consultar Estudiante por Email");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(in.nextLine());

                switch (opcion) {
                    case 1 -> estudianteServices.insertarEstudiante(conn);
                    case 2 -> estudianteServices.actualizarEstudiante(conn);
                    case 3 -> estudianteServices.eliminarEstudiante(conn);
                    case 4 -> estudianteServices.obtenerTodos(conn);
                    case 5 -> estudianteServices.obtenerPorCorreo(conn);
                    case 6 -> System.out.println("Saliendo del programa...");
                    default -> System.out.println("Opción no válida.");
                }
            }

        } catch (Exception e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }
}