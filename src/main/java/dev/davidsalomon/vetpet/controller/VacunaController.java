package dev.davidsalomon.vetpet.controller;

import dev.davidsalomon.vetpet.model.Vacuna;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VacunaController {

    private List<Vacuna> vacunas;
    private static final String ARCHIVO_VACUNAS = "src/data/vacunas.dat";

    public VacunaController() {
        this.vacunas = cargarVacunasDesdeArchivo();
    }

    public List<Vacuna> getVacunas() {
        return vacunas;
    }

    public void agregarVacuna(Vacuna vacuna) {
        vacunas.add(vacuna);
        guardarVacunasEnArchivo();
    }

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
