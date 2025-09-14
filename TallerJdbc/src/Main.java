import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        EstudianteDAO dao = new EstudianteDAO();
        Scanner sc = new Scanner(System.in);
        int op;
        do {
            System.out.println("1.Insertar  2.Actualizar  3.Eliminar  4.Listar  5.Buscar por correo  6.Salir");
            op = sc.nextInt(); sc.nextLine();
            switch (op) {
                case 1 -> {
                    System.out.print("Nombre: "); String n = sc.nextLine();
                    System.out.print("Apellido: "); String a = sc.nextLine();
                    System.out.print("Correo: "); String c = sc.nextLine();
                    System.out.print("Edad: "); int e = sc.nextInt(); sc.nextLine();
                    System.out.print("Estado Civil: "); String ec = sc.nextLine();
                    dao.insertar(new Estudiante(0,n,a,c,e,Estudiante.EstadoCivil.valueOf(ec)));
                }
                case 2 -> {
                    System.out.print("Correo a actualizar: "); String c = sc.nextLine();
                    System.out.print("Nuevo nombre: "); String n = sc.nextLine();
                    System.out.print("Nuevo apellido: "); String a = sc.nextLine();
                    System.out.print("Nueva edad: "); int e = sc.nextInt(); sc.nextLine();
                    System.out.print("Nuevo estado civil: "); String ec = sc.nextLine();
                    dao.actualizar(new Estudiante(0,n,a,c,e,Estudiante.EstadoCivil.valueOf(ec)));
                }
                case 3 -> {
                    System.out.print("Correo a eliminar: "); String c = sc.nextLine();
                    dao.eliminar(c);
                }
                case 4 -> dao.listar().forEach(s ->
                        System.out.println(s.getId()+" "+s.getNombre()+" "+s.getApellido()+" "+s.getCorreo()+" "+s.getEdad()+" "+s.getEstadoCivil()));
                case 5 -> {
                    System.out.print("Correo: "); String c = sc.nextLine();
                    var s = dao.buscarPorCorreo(c);
                    if (s != null) System.out.println(s.getNombre()+" "+s.getApellido()+" "+s.getEdad()+" "+s.getEstadoCivil());
                    else System.out.println("No encontrado");
                }
            }
        } while (op != 6);
    }
}
