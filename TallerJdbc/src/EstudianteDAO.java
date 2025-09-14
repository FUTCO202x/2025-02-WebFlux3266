import java.sql.*;
import java.util.*;

public class EstudianteDAO {

    public void insertar(Estudiante e) throws Exception {
        String sql = "INSERT INTO Estudiantes(nombre,apellido,correo,edad,estado_civil) VALUES(?,?,?,?,?)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getCorreo());
            ps.setInt(4, e.getEdad());
            ps.setString(5, e.getEstadoCivil().name());
            ps.executeUpdate();
        }
    }

    public void actualizar(Estudiante e) throws Exception {
        String sql = "UPDATE Estudiantes SET nombre=?, apellido=?, edad=?, estado_civil=? WHERE correo=?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setInt(3, e.getEdad());
            ps.setString(4, e.getEstadoCivil().name());
            ps.setString(5, e.getCorreo());
            ps.executeUpdate();
        }
    }

    public void eliminar(String correo) throws Exception {
        String sql = "DELETE FROM Estudiantes WHERE correo=?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.executeUpdate();
        }
    }

    public List<Estudiante> listar() throws Exception {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM Estudiantes";
        try (Connection c = Conexion.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("edad"),
                        Estudiante.EstadoCivil.valueOf(rs.getString("estado_civil"))
                ));
            }
        }
        return lista;
    }

    public Estudiante buscarPorCorreo(String correo) throws Exception {
        String sql = "SELECT * FROM Estudiantes WHERE correo=?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("edad"),
                        Estudiante.EstadoCivil.valueOf(rs.getString("estado_civil"))
                );
            }
        }
        return null;
    }
}
