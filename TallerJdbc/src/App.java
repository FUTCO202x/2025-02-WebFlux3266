import com.mysql.cj.jdbc.ConnectionImpl;
import control.service;
import model.EstadosCiviles;
import model.Estudiante;

import java.sql.Connection;
import java.util.Scanner;

public class App {


    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);
        service serv = new service();

        while (true){
            System.out.println("""
                \n
                Bienvenido a la plataforma
                
                Acciones:
                1. Ver estudiantes.
                2. Agregar Estudiantes.
                3. Editar estudiante por id.
                4. Eliminar estudiante por id. 
                5. Salir
                """);

            int opcion = Integer.parseInt(entrada.nextLine());

            switch (opcion) {

                case 1:
                    serv.listarEstudiantes();
                    break;

                case 2:
                    System.out.println("Ingrese los datos del estudiante.");
                    Estudiante est = new Estudiante();


                    System.out.println("Ingrese su nombre:");
                    est.setNombre(entrada.nextLine());

                    System.out.println("Ingrese su apellido:");
                    est.setApellido(entrada.nextLine());

                    System.out.println("Ingrese su correo:");
                    est.setCorreo(entrada.nextLine());

                    System.out.println("Ingrese su edad:");
                    est.setEdad(Integer.parseInt(entrada.nextLine()));


                    System.out.println("Ingrese su estado civil:");
                    String estadoCivil = (entrada.nextLine()).toUpperCase();
                    EstadosCiviles estado = EstadosCiviles.valueOf(estadoCivil);
                    est.setEstadosCivil(estado);

                    serv.agregarEstudiante(est);

                    break;
                case 3:
                    System.out.println("Ingrese el id del estudiante que va a modificar");
                    int id = Integer.parseInt(entrada.nextLine());

                    System.out.println("Rellene los siguientes datos.");

                    System.out.println("Ingrese su nombre:");
                    String nombre = entrada.nextLine();
                    System.out.println("Ingrese su apellido:");
                    String apellido = entrada.nextLine();
                    System.out.println("Ingrese su correo:");
                    String correo = entrada.nextLine();
                    System.out.println("Ingrese su edad:");
                    int edad = Integer.parseInt(entrada.nextLine());
                    System.out.println("Ingrese su estado civil:");
                    String estadoCivill = entrada.nextLine();

                    break;
                case 4:
                    System.out.println("Ingrese el id del estudiante que desea eliminar.");
                    serv.eliminarEstudiante(Integer.parseInt(entrada.nextLine()));
                    break;
                case 5:
                    break;
                default:

            }
            break;
        }





    }
}

