package model;

public class Estudiante {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private int edad;
    private EstadosCiviles estadosCivil;

    public Estudiante(String nombre, String apellido, String correo, int edad, EstadosCiviles estadosCivil) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.edad = edad;
        this.estadosCivil = estadosCivil;
    }

    public Estudiante() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public EstadosCiviles getEstadosCivil() {
        return estadosCivil;
    }

    public void setEstadosCivil(EstadosCiviles estadosCivil) {
        this.estadosCivil = estadosCivil;
    }
}
