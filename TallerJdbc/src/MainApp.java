import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/escuela";
        String user = "root";
        String password = "Pass_123";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            EstudianteServices service = new EstudianteServices();
            Scanner sc = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n===== MENÚ ESTUDIANTES =====");
                System.out.println("1. Insertar Estudiante");
                System.out.println("2. Actualizar Estudiante");
                System.out.println("3. Eliminar Estudiante");
                System.out.println("4. Consultar Todos los Estudiantes");
                System.out.println("5. Consultar Estudiante por Email");
                System.out.println("6. Salir");
                System.out.print("Elige una opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1 -> service.insertarEstudianteConValores(conn);
                    case 2 -> service.actualizarEstudianteConValores(conn);
                    case 3 -> service.eliminarEstudianteConValores(conn);
                    case 4 -> service.obtenerEstudiantes(conn);
                    case 5 -> service.obtenerEstudiantePorCorreo(conn);
                    case 6 -> System.out.println("👋 Saliendo del programa...");
                    default -> System.out.println("⚠ Opción inválida");
                }
            } while (opcion != 6);

        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        }
    }
}

