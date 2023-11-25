package dev.davidsalomon.vetpet.controller;

import dev.davidsalomon.vetpet.model.Vacuna;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para gestionar las vacunas en el sistema.
 *
 * <p>
 * Este controlador proporciona métodos para agregar, obtener y eliminar
 * vacunas.</p>
 *
 * @author davidsalomon
 * @version 2.0
 */
public class VacunaController {

    private List<Vacuna> vacunas;
    private static final String ARCHIVO_VACUNAS = "src/data/vacunas.dat";

    /**
     * Constructor que inicializa la lista de vacunas cargándolas desde el
     * archivo.
     */
    public VacunaController() {
        this.vacunas = cargarVacunasDesdeArchivo();
    }

    /**
     * Obtiene la lista de vacunas almacenadas en el sistema.
     *
     * @return Lista de vacunas.
     */
    public List<Vacuna> getVacunas() {
        return vacunas;
    }

    /**
     * Agrega una nueva vacuna a la lista y guarda la lista actualizada en el
     * archivo.
     *
     * @param vacuna Vacuna a agregar.
     */
    public void agregarVacuna(Vacuna vacuna) {
        vacunas.add(vacuna);
        guardarVacunasEnArchivo();
    }

    /**
     * Elimina una vacuna existente por su identificador único y guarda la lista
     * actualizada en el archivo.
     *
     * @param id Identificador único de la vacuna a eliminar.
     */
    public void eliminarVacuna(String id) {
        vacunas.removeIf(vacuna -> vacuna.getUniqueId().equals(id));
        guardarVacunasEnArchivo();
    }

    private List<Vacuna> cargarVacunasDesdeArchivo() {
        List<Vacuna> vacunasCargadas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_VACUNAS))) {
            vacunasCargadas = (List<Vacuna>) ois.readObject();
        } catch (FileNotFoundException e) {
            // El archivo aún no existe, se creará cuando se guarde la primera vacuna.
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades.
        }
        return vacunasCargadas;
    }

    private void guardarVacunasEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_VACUNAS))) {
            oos.writeObject(vacunas);
        } catch (IOException e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades.
        }
    }
}
