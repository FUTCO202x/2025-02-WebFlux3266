import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (Connection conn = Conexion.getConnection()) {
            EstudianteService dao = new EstudianteService(conn);
            Scanner sc = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n----- MENU -----");
                System.out.println("1. Insertar Estudiante");
                System.out.println("2. Actualizar Estudiante");
                System.out.println("3. Eliminar Estudiante");
                System.out.println("4. Consultar todos los estudiantes");
                System.out.println("5. Consultar Estudiante por correo");
                System.out.println("6. Salir");
                System.out.print("Elige una opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1: insertar(sc, dao); break;
                    case 2: actualizar(sc, dao); break;
                    case 3: eliminar(sc, dao); break;
                    case 4: consultarTodos(dao); break;
                    case 5: consultarPorCorreo(sc, dao); break;
                    case 6: System.out.println("Saliendo..."); break;
                    default: System.out.println("Opción inválida.");
                }
            } while (opcion != 6);

            sc.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private static void insertar(Scanner sc, EstudianteService dao) throws SQLException {
        System.out.print("Nombre: "); String nombre = sc.nextLine();
        System.out.print("Apellido: "); String apellido = sc.nextLine();
        System.out.print("Edad: "); int edad = sc.nextInt(); sc.nextLine();
        System.out.print("Estado Civil: "); String estado = sc.nextLine();
        System.out.print("Correo: "); String correo = sc.nextLine();
        dao.insertarEstudiante(new Estudiante(0, nombre, apellido, edad, estado, correo));
        System.out.println("Estudiante insertado.");
    }

    private static void actualizar(Scanner sc, EstudianteService dao) throws SQLException {
        System.out.print("Correo del estudiante a actualizar: "); String correo = sc.nextLine();
        System.out.print("Nuevo nombre: "); String nombre = sc.nextLine();
        System.out.print("Nuevo apellido: "); String apellido = sc.nextLine();
        System.out.print("Nueva edad: "); int edad = sc.nextInt(); sc.nextLine();
        System.out.print("Nuevo estado civil: "); String estado = sc.nextLine();
        dao.actualizarEstudiante(new Estudiante(0, nombre, apellido, edad, estado, correo));
        System.out.println("Estudiante actualizado.");
    }

    private static void eliminar(Scanner sc, EstudianteService dao) throws SQLException {
        System.out.print("Correo del estudiante a eliminar: "); String correo = sc.nextLine();
        dao.eliminarEstudiante(correo);
        System.out.println("Estudiante eliminado.");
    }

    private static void consultarTodos(EstudianteService dao) throws SQLException {
        List<Estudiante> lista = dao.consultarTodos();
        lista.forEach(System.out::println);
    }

    private static void consultarPorCorreo(Scanner sc, EstudianteService dao) throws SQLException {
        System.out.print("Correo del estudiante: "); String correo = sc.nextLine();
        Estudiante e = dao.consultarPorCorreo(correo);
        if (e != null) System.out.println(e);
        else System.out.println("No se encontró el estudiante.");
    }
}
