public class Estudiante {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String estadoCivil;
    private String correo;

    public Estudiante(int id, String nombre, String apellido, int edad, String estadoCivil, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.estadoCivil = estadoCivil;
        this.correo = correo;
    }

    // Getters y setters...
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public int getEdad() { return edad; }
    public String getEstadoCivil() { return estadoCivil; }
    public String getCorreo() { return correo; }

    @Override
    public String toString() {
        return "Estudiante [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido +
               ", edad=" + edad + ", estadoCivil=" + estadoCivil + ", correo=" + correo + "]";
    }
}
