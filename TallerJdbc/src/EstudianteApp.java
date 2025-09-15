import java.util.List;
import java.util.Scanner;

import model.EstadoCivil;
import model.Estudiante;
import repository.EstudianteRepository;

public class EstudianteApp {
    public static void main(String[] args) {
        // Instancia del repositorio para acceder a la base de datos
        EstudianteRepository repo = new EstudianteRepository();

        // Prueba de conexión inicial
        repo.probarConexion();

        // Scanner para leer entradas del usuario
        Scanner sc = new Scanner(System.in);

        int opcion = 0;

        // Bucle principal del menú
        do {
            System.out.println("--- MENÚ ESTUDIANTES ---");
            System.out.println("1. Insertar estudiante");
            System.out.println("2. Actualizar estudiante");
            System.out.println("3. Eliminar estudiante");
            System.out.println("4. Consultar todos los estudiantes");
            System.out.println("5. Consultar estudiante por correo");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            // Validación de entrada vacía o inválida
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("❌ Debes ingresar un número.");
                continue;
            }

            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Ingresa un número del 1 al 6.");
                continue;
            }

            // Validación de rango
            if (opcion < 1 || opcion > 6) {
                System.out.println("❌ Opción fuera de rango. Intenta de nuevo.");
                continue;
            }

            // Lógica de cada opción del menú
            switch (opcion) {
                case 1:
                    Estudiante nuevo = leerEstudiante(sc, repo, false);
                    repo.insertar(nuevo);
                    break;

                case 2:
                    Estudiante actualizado = leerEstudiante(sc, repo, true);
                    if (actualizado != null) {
                        repo.actualizar(actualizado.getCorreo(), actualizado);
                    }
                    break;

                case 3:
                    System.out.print("Correo del estudiante a eliminar: ");
                    String correoEliminar = sc.nextLine();
                    repo.eliminar(correoEliminar);
                    break;

                case 4:
                    List<Estudiante> estudiantes = repo.consultarTodos();
                    if (estudiantes.isEmpty()) {
                        System.out.println("⚠️ No hay estudiantes registrados.");
                    } else {
                        for (Estudiante e : estudiantes) {
                            System.out.println(e);
                        }
                    }
                    break;

                case 5:
                    System.out.print("Correo del estudiante a consultar: ");
                    String correo = sc.nextLine();
                    Estudiante estudiante = repo.consultarPorCorreo(correo);
                    if (estudiante != null) {
                        System.out.println(estudiante);
                    } else {
                        System.out.println("⚠️ No se encontró estudiante con ese correo.");
                    }
                    break;

                case 6:
                    System.out.println("👋 Saliendo del sistema...");
                    break;
            }

        } while (opcion != 6);
    }

    /**
     * Método para leer los datos de un estudiante desde consola.
     * Si es actualización, permite dejar campos vacíos para conservar los valores actuales.
     * Si es inserción, solicita todos los campos obligatorios.
     *
     * @param sc Scanner para leer entradas del usuario
     * @param repo Repositorio para consultar estudiantes existentes
     * @param esActualizacion true si se está actualizando, false si se está insertando
     * @return Estudiante con los datos capturados
     */
    private static Estudiante leerEstudiante(Scanner sc, EstudianteRepository repo, boolean esActualizacion) {
        if (esActualizacion) {
            System.out.print("Correo actual del estudiante: ");
            String correoOriginal = sc.nextLine();

            Estudiante actual = repo.consultarPorCorreo(correoOriginal);
            if (actual == null) {
                System.out.println("⚠️ No se encontró estudiante con ese correo.");
                return null;
            }

            System.out.print("Nuevo nombre (" + actual.getNombre() + "): ");
            String nombre = sc.nextLine();
            if (nombre.isEmpty()) nombre = actual.getNombre();

            System.out.print("Nuevo apellido (" + actual.getApellido() + "): ");
            String apellido = sc.nextLine();
            if (apellido.isEmpty()) apellido = actual.getApellido();

            System.out.print("Nuevo correo (" + actual.getCorreo() + "): ");
            String nuevoCorreo = sc.nextLine();
            if (nuevoCorreo.isEmpty()) nuevoCorreo = actual.getCorreo();

            System.out.print("Nueva edad (" + actual.getEdad() + "): ");
            String edadStr = sc.nextLine();
            int edad = edadStr.isEmpty() ? actual.getEdad() : Integer.parseInt(edadStr);

            System.out.print("Nuevo estado civil (" + actual.getEstadoCivil() + "): ");
            String estadoStr = sc.nextLine();
            EstadoCivil estadoCivil = estadoStr.isEmpty() ? actual.getEstadoCivil() : EstadoCivil.desdeTexto(estadoStr);

            return new Estudiante(actual.getId(), nombre, apellido, nuevoCorreo, edad, estadoCivil);

        } else {
            System.out.print("Correo del nuevo estudiante: ");
            String correo = sc.nextLine();

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Apellido: ");
            String apellido = sc.nextLine();

            System.out.print("Edad: ");
            int edad = 0;
            try {
                edad = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Edad inválida. Se usará 0 por defecto.");
            }

            System.out.print("Estado civil (SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO): ");
            EstadoCivil estadoCivil = EstadoCivil.desdeTexto(sc.nextLine());

            return new Estudiante(0, nombre, apellido, correo, edad, estadoCivil);
        }
    }
}
