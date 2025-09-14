import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteService {
    private Connection conn;

    public EstudianteService(Connection conn) {
        this.conn = conn;
    }

    public void insertarEstudiante(Estudiante e) throws SQLException {
        String sql = "INSERT INTO Estudiantes (nombre, apellido, edad, estado_civil, correo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setInt(3, e.getEdad());
            ps.setString(4, e.getEstadoCivil());
            ps.setString(5, e.getCorreo());
            ps.executeUpdate();
        }
    }

    public void actualizarEstudiante(Estudiante e) throws SQLException {
        String sql = "UPDATE Estudiantes SET nombre=?, apellido=?, edad=?, estado_civil=? WHERE correo=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setInt(3, e.getEdad());
            ps.setString(4, e.getEstadoCivil());
            ps.setString(5, e.getCorreo());
            ps.executeUpdate();
        }
    }

    public void eliminarEstudiante(String correo) throws SQLException {
        String sql = "DELETE FROM Estudiantes WHERE correo=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.executeUpdate();
        }
    }

    public List<Estudiante> consultarTodos() throws SQLException {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM Estudiantes";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Estudiante(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getInt("edad"),
                    rs.getString("estado_civil"),
                    rs.getString("correo")
                ));
            }
        }
        return lista;
    }

    public Estudiante consultarPorCorreo(String correo) throws SQLException {
        String sql = "SELECT * FROM Estudiantes WHERE correo=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("edad"),
                        rs.getString("estado_civil"),
                        rs.getString("correo")
                    );
                }
            }
        }
        return null;
    }
}
