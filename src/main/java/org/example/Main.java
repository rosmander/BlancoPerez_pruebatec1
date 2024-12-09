package org.example;

import org.example.logica.Empleado;
import org.example.persistencia.ControladoraPersistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    //Definir las expresiones regulares para validar los datos introducidos por el usuario
    //evitando repeticiones de código para validar los datos introducidos por el usuario
    private static final String REGEX_NOMBRE = "[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+";
    private static final String REGEX_FECHA = "\\d{2}/\\d{2}/\\d{4}";

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        //Invocar a la controladora de la persistencia
        ControladoraPersistencia controlpersis = new ControladoraPersistencia();

        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("\n- - - - Bienvenido a la aplicación de gestión de empleados - - - -");
            System.out.println("1- Agregar un nuevo empleado");
            System.out.println("2- Listar empleados");
            System.out.println("3- Actualizar información de un empleado");
            System.out.println("4- Eliminar un empleado");
            System.out.println("5- Buscar empleados por cargo");
            System.out.println("6- Salir");
            System.out.print(">");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Agregar un nuevo empleado
                    System.out.print("Ingrese el nombre del empleado: ");
                    String nombre = scanner.nextLine();
                    while (!nombre.matches(REGEX_NOMBRE)) {
                        System.out.print("Nombre no válido. Ingrese nuevamente el nombre (solo letras y espacios están permitidos): ");
                        nombre = scanner.nextLine();
                    }

                    System.out.print("Ingrese el apellido del empleado: ");
                    String apellido = scanner.nextLine();
                    while (!apellido.matches(REGEX_NOMBRE)) {
                        System.out.print("Apellido no válido. Ingrese nuevamente el apellido (solo letras y espacios están permitidos): ");
                        apellido = scanner.nextLine();

                    }
                    System.out.print("Ingrese el cargo del empleado: ");
                    String cargo = scanner.nextLine();
                    while (!cargo.matches(REGEX_NOMBRE)) {
                        System.out.print("Cargo no válido. Ingrese nuevamente el cargo (solo letras y espacios están permitidos): ");
                        cargo = scanner.nextLine();

                    }
                    System.out.print("Ingrese el salario del empleado: ");
                    while (!scanner.hasNextDouble()) {
                        System.out.print("Valor introducido no válido. Ingrese nuevamente el salario (solo números están permitidos): ");
                        scanner.next();
                    }
                    double salario = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Ingrese la fecha de inicio del empleado (dd/mm/yyyy): ");
                    String fechaDeInicioStr = scanner.nextLine();
                    while (!fechaDeInicioStr.matches(REGEX_FECHA)) {
                        System.out.print("Formato de fecha no válido. Ingrese nuevamente la fecha (dd/mm/yyyy): ");
                        fechaDeInicioStr = scanner.nextLine();
                    }

                    // Validación de día y mes
                    String[] partesFecha = fechaDeInicioStr.split("/");
                    int dia = Integer.parseInt(partesFecha[0]);
                    int mes = Integer.parseInt(partesFecha[1]);
                    while (dia < 1 || dia > 31 || mes < 1 || mes > 12) {
                        System.out.print("Datos introducidos para día o mes no válidos. Ingrese nuevamente la fecha de inicio (dd/mm/yyyy): ");
                        fechaDeInicioStr = scanner.nextLine();
                        while (!fechaDeInicioStr.matches(REGEX_FECHA)) {
                            System.out.print("Formato de fecha no válido. Ingrese nuevamente la fecha (dd/mm/yyyy): ");
                            fechaDeInicioStr = scanner.nextLine();
                        }
                        partesFecha = fechaDeInicioStr.split("/");
                        dia = Integer.parseInt(partesFecha[0]);
                        mes = Integer.parseInt(partesFecha[1]);
                    }
                    // Parsear la fecha
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date fechaDeInicio = sdf.parse(fechaDeInicioStr);

                    //Dar de alta los datos introducidos en la base de datos
                    Empleado empleado = new Empleado();
                    empleado.setNombre(nombre);
                    empleado.setApellido(apellido);
                    empleado.setCargo(cargo);
                    empleado.setSalario(salario);
                    empleado.setFechaDeInicio(fechaDeInicio);
                    controlpersis.crearEmpleado(empleado);

                    // Mensaje de confirmación
                    System.out.println("Se han introducido todos los datos correctamente.");
                    break;

                case 2:
                    // Mostrar todos los empleados
                    List<Empleado> empleados = controlpersis.traerEmpleados();
                    for (Empleado trabajador : empleados) {
                        System.out.println(trabajador);
                    }
                    break;

                case 3:
                    // Listar los ID de los empleados disponibles
                    System.out.println("ID de empleados disponibles:");
                    List<Empleado> empleadosDisponibles = controlpersis.traerEmpleados();
                    for (Empleado emp : empleadosDisponibles) {
                        System.out.println(emp);
                    }

                    // Solicitar ID del empleado a actualizar
                    long id;
                    boolean idValido = false;
                    while (!idValido) {
                        System.out.print("Ingrese el ID del empleado a actualizar: ");
                        id = scanner.nextLong();
                        scanner.nextLine();

                        Empleado empleadomodificado = controlpersis.traerEmpleadoPorID(id);
                        if (empleadomodificado != null) {
                            idValido = true;

                            // Actualizar los datos del empleado
                            System.out.print("Ingrese el nuevo nombre del empleado: ");
                            String nuevoNombre = scanner.nextLine();
                            while (!nuevoNombre.matches(REGEX_NOMBRE)) {
                                System.out.print("Nombre no válido. Ingrese nuevamente el nombre (solo letras y espacios están permitidos): ");
                                nuevoNombre = scanner.nextLine();
                            }

                            System.out.print("Ingrese el nuevo apellido del empleado: ");
                            String nuevoApellido = scanner.nextLine();
                            while (!nuevoApellido.matches(REGEX_NOMBRE)) {
                                System.out.print("Apellido no válido. Ingrese nuevamente el apellido (solo letras y espacios están permitidos): ");
                                nuevoApellido = scanner.nextLine();
                            }

                            System.out.print("Ingrese el nuevo cargo del empleado: ");
                            String nuevoCargo = scanner.nextLine();
                            while (!nuevoCargo.matches(REGEX_NOMBRE)) {
                                System.out.print("Cargo no válido. Ingrese nuevamente el cargo (solo letras y espacios están permitidos): ");
                                nuevoCargo = scanner.nextLine();
                            }

                            System.out.print("Ingrese el nuevo salario del empleado: ");
                            while (!scanner.hasNextDouble()) {
                                System.out.print("Salario no valido. Ingrese nuevamente el salario (solo números están permitidos): ");
                                scanner.next();
                            }
                            double nuevoSalario = scanner.nextDouble();
                            scanner.nextLine();

                            System.out.print("Ingrese la nueva fecha de inicio del empleado (dd/mm/yyyy): ");
                            String nuevaFechaInicioStr = scanner.nextLine();
                            while (!nuevaFechaInicioStr.matches(REGEX_FECHA)) {
                                System.out.print("Formato de fecha no válido. Ingrese nuevamente la fecha (dd/mm/yyyy): ");
                                nuevaFechaInicioStr = scanner.nextLine();
                            }

                            // Validación de día y mes
                            String[] nuevaPartesFecha = nuevaFechaInicioStr.split("/");
                            int nuevoDia = Integer.parseInt(nuevaPartesFecha[0]);
                            int nuevoMes = Integer.parseInt(nuevaPartesFecha[1]);

                            while (nuevoDia < 1 || nuevoDia > 31 || nuevoMes < 1 || nuevoMes > 12) {
                                System.out.print("Día o mes no válido. Ingrese nuevamente la fecha (dd/mm/yyyy): ");
                                nuevaFechaInicioStr = scanner.nextLine();
                                while (!nuevaFechaInicioStr.matches(REGEX_FECHA)) {
                                    System.out.print("Formato de fecha no válido. Ingrese nuevamente la fecha (dd/mm/yyyy): ");
                                    nuevaFechaInicioStr = scanner.nextLine();
                                }
                                nuevaPartesFecha = nuevaFechaInicioStr.split("/");
                                nuevoDia = Integer.parseInt(nuevaPartesFecha[0]);
                                nuevoMes = Integer.parseInt(nuevaPartesFecha[1]);
                            }

                            // Parsear la fecha
                            SimpleDateFormat nsdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date nuevaFechaDeInicio = nsdf.parse(nuevaFechaInicioStr);

                            // Actualizar los detalles del empleado
                            empleadomodificado.setNombre(nuevoNombre);
                            empleadomodificado.setApellido(nuevoApellido);
                            empleadomodificado.setCargo(nuevoCargo);
                            empleadomodificado.setSalario(nuevoSalario);
                            empleadomodificado.setFechaDeInicio(nuevaFechaDeInicio);
                            controlpersis.modificarEmpleado(empleadomodificado);
                            System.out.println("Información del empleado actualizada correctamente.");

                        } else {
                            System.out.println("ID no válido. Por favor, ingrese un ID existente.");
                        }
                    }
                    break;

                case 4:
                    // Listar ID's de los empleados disponibles
                    System.out.println("ID de empleados disponibles:");
                    List<Empleado> empleadosDisponiblesBorrar = controlpersis.traerEmpleados();
                    for (Empleado emp : empleadosDisponiblesBorrar) {
                        System.out.println(emp);
                    }

                    // Solicitar ID del empleado a eliminar
                    long idBorrar;
                    boolean idValidoBorrar = false;
                    while (!idValidoBorrar) {
                        System.out.print("Ingrese el ID del empleado a eliminar: ");
                        idBorrar = scanner.nextLong();
                        scanner.nextLine();

                        Empleado empleadoBorrar = controlpersis.traerEmpleadoPorID(idBorrar);
                        if (empleadoBorrar != null) {
                            idValidoBorrar = true;
                            controlpersis.borrarEmpleado(idBorrar);
                            System.out.println("Empleado eliminado correctamente.");

                        } else {
                            System.out.println("ID no válido. Por favor, ingrese un ID existente.");
                        }
                    }
                    break;

                case 5:
                    // Listar cargos disponibles en la empresa
                    System.out.println("Cargos disponibles en la empresa:");
                    List<String> cargosDisponibles = controlpersis.traerCargosDisponibles();
                    System.out.println(String.join(", ", cargosDisponibles));

                    // Solicitar el cargo de los empleados a buscar
                    System.out.print("Ingrese el cargo de los empleados a buscar: ");
                    String cargoBuscar = scanner.nextLine();
                    while (!cargoBuscar.matches(REGEX_NOMBRE)) {
                        System.out.print("Cargo no válido. Ingrese nuevamente el cargo (solo letras y espacios están permitidos): ");
                        cargoBuscar = scanner.nextLine();
                    }

                    // Mostrar empleados por cargo
                    List<Empleado> empleadosPorCargo = controlpersis.traerEmpleadosPorCargo(cargoBuscar);
                    if (empleadosPorCargo.isEmpty()) {
                        System.out.println("No se encontraron empleados con el cargo " + cargoBuscar);
                    } else {
                        for (Empleado emp : empleadosPorCargo) {
                            System.out.println(emp);
                        }
                    }
                    break;

                case 6:
                    // Salir y cerrar conexión
                    ejecutando = false;
                    System.out.println("Saliendo de la aplicación.");
                    scanner.close();
                    controlpersis.cerrarConexion();
                    break;
            }
        }
    }
}
