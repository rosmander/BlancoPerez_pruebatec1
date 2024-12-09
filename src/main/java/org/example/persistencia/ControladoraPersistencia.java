package org.example.persistencia;

import org.example.logica.Empleado;
import org.example.persistencia.exceptions.NonexistentEntityException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {

    EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();

    public void crearEmpleado(Empleado empleado) {
        empleadoJPA.create(empleado);
    }

    public void borrarEmpleado(Long id) {
        try {
            empleadoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Empleado> traerEmpleados() {
        return empleadoJPA.findEmpleadoEntities();
    }

    public void modificarEmpleado(Empleado empleado) {
        try {
            empleadoJPA.edit(empleado);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarConexion() {
        try {
            empleadoJPA.getEntityManagerFactory().close();
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Empleado traerEmpleadoPorID(Long id) {
        return empleadoJPA.findEmpleado(id);
    }

    public List<String> traerCargosDisponibles() {
        List<Empleado> todosLosEmpleados = traerEmpleados();
        Set<String> cargos = new HashSet<>();
        for (Empleado empleado : todosLosEmpleados) {
            cargos.add(empleado.getCargo());
        }
        return new ArrayList<>(cargos);
    }

    public List<Empleado> traerEmpleadosPorCargo(String empleadoBuscado) {
        List<Empleado> todos = empleadoJPA.findEmpleadoEntities();
        List<Empleado> buscados = new ArrayList<>();
        // Recorro la lista y verifico el tipo
        for (Empleado empleado : todos) {
            if (empleado.getCargo().equalsIgnoreCase(empleadoBuscado)) {
                buscados.add(empleado);
            }
        }
        return buscados;
    }
}
