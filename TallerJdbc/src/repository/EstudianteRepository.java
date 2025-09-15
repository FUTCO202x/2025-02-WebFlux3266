package repository;

import model.EstadoCivil;
import model.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class EstudianteRepository {
    private final String url = "jdbc:mysql://localhost:3306/escuela?useSSL=false&serverTimezone=UTC";
    private final String user = "root";
    private final String password = "root";

    public void probarConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("¡Conexión exitosa!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void insertar(Estudiante estudiante) {
        String sql = "INSERT INTO estudiante(nombre, apellido, correo, edad, estado_civil) VALUES (?, ?, ?, ?, ?)";


        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getCorreo());
            ps.setInt(4, estudiante.getEdad());
            ps.setString(5, estudiante.getEstadoCivil() != null ? estudiante.getEstadoCivil().name() : null);

            ps.executeUpdate();
            System.out.println("✅ Estudiante insertado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(String correoOriginal, Estudiante estudiante) {
        String sql = "UPDATE estudiante SET nombre=?, apellido=?, correo=?, edad=?, estado_civil=? WHERE correo=?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getCorreo());
            ps.setInt(4, estudiante.getEdad());
            ps.setString(5, estudiante.getEstadoCivil().name());
            ps.setString(6, correoOriginal);

            int filas = ps.executeUpdate();
            System.out.println(filas > 0 ? "✅ Estudiante actualizado." : "⚠️ No se encontró estudiante con ese correo.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void eliminar(String correo) {
        String sql = "DELETE FROM estudiante WHERE correo=?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            int filas = ps.executeUpdate();
            System.out.println(filas > 0 ? "🗑️ Estudiante eliminado." : "⚠️ No se encontró estudiante con ese correo.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Estudiante> consultarTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";



        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                EstadoCivil estadoCivil = EstadoCivil.desdeTexto(rs.getString("estado_civil"));

                Estudiante e = new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("edad"),
                        estadoCivil
                );
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }




    public Estudiante consultarPorCorreo(String correo) {
        String sql = "SELECT * FROM estudiante WHERE correo=?";
        Estudiante estudiante = null;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    EstadoCivil estadoCivil = EstadoCivil.desdeTexto(rs.getString("estado_civil"));

                    estudiante = new Estudiante(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("correo"),
                            rs.getInt("edad"),
                            estadoCivil
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estudiante;
    }




}