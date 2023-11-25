package dev.davidsalomon.vetpet.controller;

import dev.davidsalomon.vetpet.model.Cita;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para gestionar las citas en el sistema.
 *
 * <p>
 * Este controlador proporciona métodos para agregar, editar, eliminar y
 * verificar la disponibilidad de citas.</p>
 *
 * @author davidsalomon
 * @version 2.0
 */
public class CitaController {

    private List<Cita> citas;
    private static final String ARCHIVO_CITAS = "src/data/citas.dat";

    /**
     * Constructor que inicializa la lista de citas cargándolas desde el
     * archivo.
     */
    public CitaController() {
        this.citas = cargarCitasDesdeArchivo();
    }

    /**
     * Obtiene la lista de citas almacenadas en el sistema.
     *
     * @return Lista de citas.
     */
    public List<Cita> getCitas() {
        return citas;
    }

    /**
     * Agrega una nueva cita a la lista y guarda la lista actualizada en el
     * archivo.
     *
     * @param cita Cita a agregar.
     */
    public void agregarCita(Cita cita) {
        citas.add(cita);
        guardarCitasEnArchivo();
    }

    /**
     * Edita una cita existente con nuevos datos y guarda la lista actualizada
     * en el archivo.
     *
     * @param idCita Identificador único de la cita a editar.
     * @param nuevaCita Nuevos datos de la cita.
     * @throws ParseException Si hay un error al parsear la fecha y hora de la
     * cita.
     */
    public void editarCita(String idCita, Cita nuevaCita) throws ParseException {
        for (Cita cita : citas) {
            if (cita.getUniqueId().equals(idCita)) {
                // Actualizar los datos de la cita
                cita.setFechaHora(nuevaCita.getDia(), nuevaCita.getHora());
                cita.setMotivo(nuevaCita.getMotivo());

                guardarCitasEnArchivo();
                break;
            }
        }
    }

    /**
     * Elimina una cita existente por su identificador único y guarda la lista
     * actualizada en el archivo.
     *
     * @param id Identificador único de la cita a eliminar.
     */
    public void eliminarCita(String id) {
        citas.removeIf(cita -> cita.getUniqueId().equals(id));
        guardarCitasEnArchivo();
    }

    private List<Cita> cargarCitasDesdeArchivo() {
        List<Cita> citasCargadas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_CITAS))) {
            citasCargadas = (List<Cita>) ois.readObject();
        } catch (FileNotFoundException e) {
            // El archivo aún no existe, se creará cuando se guarde la primera cita.
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades.
        }
        return citasCargadas;
    }

    private void guardarCitasEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_CITAS))) {
            oos.writeObject(citas);
        } catch (IOException e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades.
        }
    }

    /**
     * Verifica si ya existen dos citas para un paciente en el mismo día.
     *
     * @param idPaciente Identificador único del paciente.
     * @param dia Día de la cita.
     * @return true si ya hay dos citas en el mismo día para el paciente, false
     * de lo contrario.
     */
    public boolean existeDosCitasEnMismoDia(String idPaciente, String dia) {
        int contadorCitas = 0;

        for (Cita cita : getCitas()) {
            if (cita.getIdPaciente().equals(idPaciente) && cita.getDia().equals(dia)) {
                contadorCitas++;
                if (contadorCitas >= 2) {
                    return true; // Ya hay dos citas en el mismo día
                }
            }
        }

        return false; // No hay dos citas en el mismo día
    }

    /**
     * Verifica la disponibilidad de una cita para un paciente en un día y hora
     * específicos.
     *
     * @param idPaciente Identificador único del paciente.
     * @param nuevoDia Nuevo día de la cita.
     * @param nuevaHora Nueva hora de la cita.
     * @return true si la cita está disponible, false si ya está ocupada.
     */
    public boolean validarDisponibilidadCita(String idPaciente, String nuevoDia, String nuevaHora) {
        // Obtener y mostrar información de citas
        List<Cita> citas = getCitas();

        // Verificar si ya existe una cita para el mismo día y hora
        for (Cita cita : citas) {
            if (cita.getIdPaciente().equals(idPaciente) && cita.getDia().equals(nuevoDia) && cita.getHora().equalsIgnoreCase(nuevaHora)) {
                return false; // La cita ya está ocupada
            }
        }

        return true; // La cita está disponible
    }
}
