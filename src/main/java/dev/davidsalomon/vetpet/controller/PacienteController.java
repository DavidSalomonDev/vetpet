package dev.davidsalomon.vetpet.controller;

import dev.davidsalomon.vetpet.model.Paciente;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para gestionar los pacientes en el sistema.
 *
 * <p>
 * Este controlador proporciona métodos para agregar, obtener, editar y dar de
 * baja pacientes.</p>
 *
 * @author davidsalomon
 * @version 2.0
 */
public class PacienteController {

    private List<Paciente> pacientes;
    private static final String ARCHIVO_PACIENTES = "src/data/pacientes.dat";

    /**
     * Constructor que inicializa la lista de pacientes cargándolos desde el
     * archivo.
     */
    public PacienteController() {
        this.pacientes = cargarPacientesDesdeArchivo();
    }

    /**
     * Obtiene la lista de pacientes almacenados en el sistema.
     *
     * @return Lista de pacientes.
     */
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    /**
     * Agrega un nuevo paciente a la lista y guarda la lista actualizada en el
     * archivo.
     *
     * @param paciente Paciente a agregar.
     */
    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        guardarPacientesEnArchivo();
    }

    /**
     * Edita un paciente existente por su identificador único y guarda la lista
     * actualizada en el archivo.
     *
     * @param uniqueId Identificador único del paciente a editar.
     * @param nuevoPaciente Nuevo objeto Paciente con los datos actualizados.
     * @throws ParseException Si hay un error al parsear las fechas.
     */
    public void editarPaciente(String uniqueId, Paciente nuevoPaciente) throws ParseException {
        for (Paciente paciente : pacientes) {
            if (paciente.getUniqueId().equals(uniqueId)) {
                // Actualizar todos los datos del paciente
                paciente.setNombre(nuevoPaciente.getNombre());
                paciente.setDueno(nuevoPaciente.getDueno());
                paciente.setEdad(nuevoPaciente.getEdad());
                paciente.setCategoria(nuevoPaciente.getCategoria());
                paciente.setRaza(nuevoPaciente.getRaza());
                paciente.setSexo(nuevoPaciente.getSexo());
                paciente.setAltura(nuevoPaciente.getAltura());
                paciente.setPeso(nuevoPaciente.getPeso());
                paciente.setPelaje(nuevoPaciente.getPelaje());
                paciente.setFechaNacimiento(nuevoPaciente.getFechaNacimiento());
                // Guardar los cambios en el archivo
                guardarPacientesEnArchivo();
                break;
            }
        }
    }

    /**
     * Da de baja a un paciente existente por su identificador único y guarda la
     * lista actualizada en el archivo.
     *
     * @param uniqueId Identificador único del paciente a dar de baja.
     */
    public void darDeBajaPaciente(String uniqueId) {
        pacientes.removeIf(paciente -> paciente.getUniqueId().equals(uniqueId));
        guardarPacientesEnArchivo();
    }

    private List<Paciente> cargarPacientesDesdeArchivo() {
        List<Paciente> pacientesCargados = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_PACIENTES))) {
            pacientesCargados = (List<Paciente>) ois.readObject();
        } catch (FileNotFoundException e) {
            // El archivo aún no existe, se creará cuando se guarde el primer paciente.
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pacientesCargados;
    }

    private void guardarPacientesEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PACIENTES))) {
            oos.writeObject(pacientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
