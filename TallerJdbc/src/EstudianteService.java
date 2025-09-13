import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class EstudianteService {

    private final Connection conn;

    public EstudianteService(Connection conn) {
        this.conn = conn;
    }

    public void insertarEstudiante(String nombre, String apellido, String correo, int edad, EstadoCivil estadoCivil) {
        String sql = "INSERT INTO estudiantes (nombre, apellido, correo, edad, estado_civil) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, correo);
            ps.setInt(4, edad);
            ps.setString(5, estadoCivil.name());
            ps.executeUpdate();
            System.out.println();
            System.out.println(" Estudiante insertado correctamente.");
        } catch (SQLException e) {
            System.out.println();
            System.out.println(" Error al insertar: " + e.getMessage());
        }
    }

    public void actualizarEstudiante(String correo, String campo, String nuevoValor) {
        String sql = "UPDATE estudiantes SET " + campo + " = ? WHERE correo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoValor);
            ps.setString(2, correo);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println();
                System.out.println(" Estudiante actualizado correctamente.");
            } else {
                System.out.println();
                System.out.println(" No se encontró un estudiante con ese correo.");
            }
        } catch (SQLException e) {
            System.out.println();
            System.out.println(" Error al actualizar: " + e.getMessage());
        }
    }

    public void eliminarEstudiante(String correo) {
        String sql = "DELETE FROM estudiantes WHERE correo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println();
                System.out.println(" Estudiante eliminado correctamente.");
            } else {
                System.out.println();
                System.out.println(" No se encontró un estudiante con ese correo.");
            }
        } catch (SQLException e) {
            System.out.println();
            System.out.println(" Error al eliminar: " + e.getMessage());
        }
    }

    public void consultarTodosLosEstudiantes() {
        String sql = "SELECT * FROM estudiantes";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            List<String> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(rs.getInt("id") + " - " +
                        rs.getString("nombre") + " " +
                        rs.getString("apellido") + " | " +
                        rs.getString("correo") + " | " +
                        rs.getInt("edad") + " | " +
                        rs.getString("estado_civil"));
            }

            if (lista.isEmpty()) {
                System.out.println();
                System.out.println(" No hay estudiantes registrados.");
            } else {
                System.out.println();
                System.out.println(" Lista de estudiantes:");
                lista.forEach(System.out::println);
            }

        } catch (SQLException e) {
            System.out.println();
            System.out.println(" Error al consultar: " + e.getMessage());
        }
    }

    public void consultarEstudiantePorCorreo(String correo) {
        String sql = "SELECT * FROM estudiantes WHERE correo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println();
                System.out.println(" Estudiante encontrado:");
                System.out.println(rs.getInt("id") + " - " +
                        rs.getString("nombre") + " " +
                        rs.getString("apellido") + " | " +
                        rs.getString("correo") + " | " +
                        rs.getInt("edad") + " | " +
                        rs.getString("estado_civil"));
            } else {
                System.out.println();
                System.out.println(" No se encontró un estudiante con ese correo.");
            }
        } catch (SQLException e) {
            System.out.println();
            System.out.println(" Error al consultar: " + e.getMessage());
        }
    }

}
