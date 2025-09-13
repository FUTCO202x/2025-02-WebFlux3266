import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EstudianteServices {


    public void insertarEstudiante(Connection conn) throws SQLException {
        String sql = "INSERT INTO Estudiantes (nombre, apellido, correo, edad, estado_civil) " +
                "VALUES ('Jim', 'Carrey', 'jim@mail.com', 40, 'CASADO')";
        var stm = conn.prepareStatement(sql);
        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("✅ Registro insertado correctamente");
        } else {
            System.out.println("❌ Error en inserción");
        }
    }


    public void insertarEstudianteConValores(Connection conn) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Nombre: ");
        String nombre = in.nextLine();
        System.out.print("Apellido: ");
        String apellido = in.nextLine();
        System.out.print("Correo: ");
        String correo = in.nextLine();
        System.out.print("Edad: ");
        int edad = in.nextInt();
        in.nextLine();
        System.out.print("Estado civil (SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO): ");
        String estadoCivil = in.nextLine().toUpperCase();

        String sql = "INSERT INTO Estudiantes (nombre, apellido, correo, edad, estado_civil) VALUES (?,?,?,?,?)";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2, apellido);
        stm.setString(3, correo);
        stm.setInt(4, edad);
        stm.setString(5, estadoCivil);

        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("✅ Estudiante insertado correctamente");
        } else {
            System.out.println("❌ Fallo en la inserción");
        }
    }

    // Actualizar estudiante
    public void actualizarEstudianteConValores(Connection conn) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Correo del estudiante a actualizar: ");
        String correo = in.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = in.nextLine();
        System.out.print("Nuevo apellido: ");
        String apellido = in.nextLine();
        System.out.print("Nueva edad: ");
        int edad = in.nextInt();
        in.nextLine();
        System.out.print("Nuevo estado civil: ");
        String estadoCivil = in.nextLine().toUpperCase();

        String sql = "UPDATE Estudiantes SET nombre=?, apellido=?, edad=?, estado_civil=? WHERE correo=?";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2, apellido);
        stm.setInt(3, edad);
        stm.setString(4, estadoCivil);
        stm.setString(5, correo);

        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("✅ Estudiante actualizado correctamente");
        } else {
            System.out.println("⚠ No se encontró un estudiante con ese correo");
        }
    }

    // Eliminar estudiante
    public void eliminarEstudianteConValores(Connection conn) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Correo del estudiante a eliminar: ");
        String correo = in.nextLine();

        String sql = "DELETE FROM Estudiantes WHERE correo=?";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, correo);
        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("✅ Estudiante eliminado correctamente");
        } else {
            System.out.println("⚠ No se encontró estudiante con ese correo");
        }
    }

    // Consultar todos los estudiantes
    public void obtenerEstudiantes(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Estudiantes";
        var stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String correo = rs.getString("correo");
            int edad = rs.getInt("edad");
            String estadoCivil = rs.getString("estado_civil");
            System.out.println(String.format("ID: %d | %s %s | %s | %d años | %s",
                    id, nombre, apellido, correo, edad, estadoCivil));
        }
        System.out.println("Consulta Finalizada ✅");
    }

    // Consultar estudiante por correo
    public void obtenerEstudiantePorCorreo(Connection conn) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Digite el correo: ");
        String correo = in.nextLine();

        String sql = "SELECT * FROM Estudiantes WHERE correo=?";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, correo);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            int edad = rs.getInt("edad");
            String estadoCivil = rs.getString("estado_civil");
            System.out.println(String.format("ID: %d | %s %s | %s | %d años | %s",
                    id, nombre, apellido, correo, edad, estadoCivil));
        } else {
            System.out.println("⚠ No se encontró estudiante con ese correo");
        }
    }
}

