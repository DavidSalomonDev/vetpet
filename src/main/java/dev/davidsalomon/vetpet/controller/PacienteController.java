package dev.davidsalomon.vetpet.controller;

import dev.davidsalomon.vetpet.model.Paciente;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PacienteController {

    private List<Paciente> pacientes;
    private static final String ARCHIVO_PACIENTES = "src/data/pacientes.dat";

    public PacienteController() {
        this.pacientes = cargarPacientesDesdeArchivo();
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        guardarPacientesEnArchivo();
    }

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
