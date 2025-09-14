package control;

import model.Estudiante;

import java.sql.Connection;
import java.util.List;

public interface CrudMethods {
    void agregarEstudiante(Estudiante estudiante);
    void eliminarEstudiante(int id);
    void listarEstudiantes();
    void editarEstudiante(int id,String nombre,String apellido,String correo,int edad,String estadoCivil);
}
