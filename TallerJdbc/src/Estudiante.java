public class Estudiante {
    public enum EstadoCivil { SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO }

    private int id, edad;
    private String nombre, apellido, correo;
    private EstadoCivil estadoCivil;

    public Estudiante(int id, String nombre, String apellido, String correo, int edad, EstadoCivil estadoCivil) {
        this.id = id; this.nombre = nombre; this.apellido = apellido;
        this.correo = correo; this.edad = edad; this.estadoCivil = estadoCivil;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCorreo() { return correo; }
    public int getEdad() { return edad; }
    public EstadoCivil getEstadoCivil() { return estadoCivil; }
}
