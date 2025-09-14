package control;

import model.Estudiante;
import java.sql.*;

public class service implements CrudMethods {

    static final String url = "jdbc:mysql://localhost:3306/escuela";
    static final String username = "root";
    static final String password = "Pass_123";

    private Connection connection;

    public service() {
        try {
            this.connection = DriverManager.getConnection(url,username,password);
            System.out.println("Coneccion establecida");
        }catch (SQLException e){
            throw new RuntimeException("No se pudo establecer la conexion.");
        }
    }

    @Override
    public void agregarEstudiante(Estudiante estudiante)  {
        String peticion = "INSERT INTO Estudiantes (nombre,apellido,correo,edad,estado_civil) VALUES (?,?,?,?,?);";
        try ( PreparedStatement pre = connection.prepareStatement(peticion)) {

            pre.setString(1,estudiante.getNombre());
            pre.setString(2,estudiante.getApellido());
            pre.setString(3,estudiante.getCorreo());
            pre.setInt(4,estudiante.getEdad());
            pre.setString(5,estudiante.getEstadosCivil().toString());

            pre.executeUpdate();

            System.out.println("Estudiante agregado.");

        } catch (SQLException e) {
            System.out.println("No se puede escribir en la base de datos.");
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarEstudiante(int id) {
        String peticion = "DELETE FROM Estudiantes WHERE id = ?;";

        try (PreparedStatement pre = connection.prepareStatement(peticion)) {
            pre.setInt(1,id);
            pre.executeUpdate();
            System.out.println("Eliminado con exito");

        } catch (SQLException e){
            System.out.println("No se puede leer en la base de datos.");
            e.printStackTrace();
        }
    }

    @Override
    public void listarEstudiantes() {
        String peticion = "SELECT * FROM Estudiantes;";

        try (PreparedStatement pre = connection.prepareStatement(peticion)) {

            ResultSet set = pre.executeQuery();

            System.out.println("""
                    +----+--------+----------+----------+------+--------------+
                    | id | nombre | apellido | correo   | edad | estado_civil |
                    +----+--------+----------+----------+------+--------------+
                    """);

            while (set.next()) {
                int id = set.getInt("id");
                String nombre = set.getString("nombre");
                String apellido = set.getString("apellido");
                String correo = set.getString("correo");
                int edad = set.getInt("edad");
                String estadoCivil = set.getString("estado_civil");

                System.out.println(" |  " + id + " | " + nombre + " | " + apellido + "  | " + correo + " |   " + edad + " | " + estadoCivil + "      | ");
            }
            System.out.println("+----+--------+----------+----------+------+--------------+");
        } catch (SQLException e) {
            System.out.println("No se puede leer en la base de datos.");
            e.printStackTrace();
        }

    }

    @Override
    public void editarEstudiante(int id,String nombre,String apellido,String correo,int edad,String estadoCivil) {
        String peticion = """
                UPDATE nombre_tabla
                SET nombre = ?,
                    apellido = ?,
                    correo = ?,
                    edad = ?,
                    estado_civil = ?
                WHERE id = ?;      
                """;
        try (PreparedStatement pre = connection.prepareStatement(peticion)){

            pre.setString(1,nombre);
            pre.setString(2,apellido);
            pre.setString(3,correo);
            pre.setInt(4,edad);
            pre.setString(5,estadoCivil);
            pre.setInt(6,id);

        } catch (SQLException e){
            System.out.println("No se puede sobrescribir en la base de datos.");
            e.printStackTrace();
        }
    }
}
