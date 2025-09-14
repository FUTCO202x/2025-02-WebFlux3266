import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EstudianteServices {

    public void insertarEstudiante(Connection conn) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Nombre: ");
        String nombre = in.nextLine();
        System.out.print("Apellido: ");
        String apellido = in.nextLine();
        System.out.print("Correo: ");
        String correo = in.nextLine();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(in.nextLine());
        System.out.print("Estado Civil (SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO): ");
        String estadoCivil = in.nextLine();

        String sql = "INSERT INTO estudiantes (nombre, apellido, correo, edad, estado_civil) VALUES (?, ?, ?, ?, ?)";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2, apellido);
        stm.setString(3, correo);
        stm.setInt(4, edad);
        stm.setString(5, estadoCivil);

        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("Estudiante insertado correctamente.");
        } else {
            System.out.println("Error al insertar estudiante.");
        }
    }

    public void actualizarEstudiante(Connection conn) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Correo del estudiante a actualizar: ");
        String correo = in.nextLine();

        System.out.print("Nuevo nombre: ");
        String nombre = in.nextLine();
        System.out.print("Nuevo apellido: ");
        String apellido = in.nextLine();
        System.out.print("Nueva edad: ");
        int edad = Integer.parseInt(in.nextLine());
        System.out.print("Nuevo estado civil: ");
        String estadoCivil = in.nextLine();

        String sql = "UPDATE estudiantes SET nombre=?, apellido=?, edad=?, estado_civil=? WHERE correo=?";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2, apellido);
        stm.setInt(3, edad);
        stm.setString(4, estadoCivil);
        stm.setString(5, correo);

        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("Estudiante actualizado correctamente.");
        } else {
            System.out.println("No se encontró estudiante con ese correo.");
        }
    }

    public void eliminarEstudiante(Connection conn) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Correo del estudiante a eliminar: ");
        String correo = in.nextLine();

        String sql = "DELETE FROM estudiantes WHERE correo=?";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, correo);

        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("Estudiante eliminado correctamente.");
        } else {
            System.out.println("No se encontró estudiante con ese correo.");
        }
    }

    public void obtenerTodos(Connection conn) throws SQLException {
        String sql = "SELECT * FROM estudiantes";
        var stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        System.out.println("\n=== LISTA DE ESTUDIANTES ===");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String correo = rs.getString("correo");
            int edad = rs.getInt("edad");
            String estadoCivil = rs.getString("estado_civil");
            System.out.printf("[%d] %s %s | %s | Edad: %d | Estado civil: %s%n",
                    id, nombre, apellido, correo, edad, estadoCivil);
        }
        System.out.println("Consulta finalizada.");
    }

    public void obtenerPorCorreo(Connection conn) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Correo del estudiante a consultar: ");
        String correo = in.nextLine();

        String sql = "SELECT * FROM estudiantes WHERE correo=?";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, correo);
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            int edad = rs.getInt("edad");
            String estadoCivil = rs.getString("estado_civil");
            System.out.printf("[%d] %s %s | %s | Edad: %d | Estado civil: %s%n",
                    id, nombre, apellido, correo, edad, estadoCivil);
        } else {
            System.out.println("No se encontró estudiante con ese correo.");
        }
    }
}
