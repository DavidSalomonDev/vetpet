package dev.davidsalomon.vetpet.controller;

import dev.davidsalomon.vetpet.model.Expediente;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controlador para gestionar los expedientes en el sistema.
 *
 * <p>
 * Este controlador proporciona métodos para agregar, obtener y eliminar
 * expedientes.</p>
 *
 * @author davidsalomon
 * @version 2.0
 */
public class ExpedienteController {

    private List<Expediente> expedientes;
    private static final String ARCHIVO_EXPEDIENTES = "src/data/expedientes.dat";

    /**
     * Constructor que inicializa la lista de expedientes cargándolos desde el
     * archivo.
     */
    public ExpedienteController() {
        this.expedientes = cargarExpedientesDesdeArchivo();
    }

    /**
     * Obtiene la lista de expedientes almacenados en el sistema.
     *
     * @return Lista de expedientes.
     */
    public List<Expediente> getExpedientes() {
        return expedientes;
    }

    /**
     * Agrega un nuevo expediente a la lista y guarda la lista actualizada en el
     * archivo.
     *
     * @param expediente Expediente a agregar.
     */
    public void agregarExpediente(Expediente expediente) {
        expedientes.add(expediente);
        guardarExpedientesEnArchivo();
    }

    /**
     * Elimina un expediente existente por su fecha de cita y guarda la lista
     * actualizada en el archivo.
     *
     * @param fechaCita Fecha de cita del expediente a eliminar.
     */
    public void eliminarExpediente(Date fechaCita) {
        expedientes.removeIf(expediente -> expediente.getFechaCita().equals(fechaCita));
        guardarExpedientesEnArchivo();
    }

    private List<Expediente> cargarExpedientesDesdeArchivo() {
        List<Expediente> expedientesCargados = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_EXPEDIENTES))) {
            expedientesCargados = (List<Expediente>) ois.readObject();
        } catch (FileNotFoundException e) {
            // El archivo aún no existe, se creará cuando se guarde el primer expediente.
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades.
        }
        return expedientesCargados;
    }

    private void guardarExpedientesEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_EXPEDIENTES))) {
            oos.writeObject(expedientes);
        } catch (IOException e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades.
        }
    }
}
