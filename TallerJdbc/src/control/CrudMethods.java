package control;

import model.Estudiante;

public interface CrudMethods {
    void agregarEstudiante(Estudiante estudiante);
    void eliminarEstudiante(int id);
    void listarEstudiantes();
    void editarEstudiante(int id,String nombre,String apellido,String correo,int edad,String estadoCivil);
}
