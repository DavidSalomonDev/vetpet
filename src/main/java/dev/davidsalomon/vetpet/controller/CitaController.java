package dev.davidsalomon.vetpet.controller;

import dev.davidsalomon.vetpet.model.Cita;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CitaController {

    private List<Cita> citas;
    private static final String ARCHIVO_CITAS = "src/data/citas.dat";

    public CitaController() {
        this.citas = cargarCitasDesdeArchivo();
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void agregarCita(Cita cita) {
        citas.add(cita);
        guardarCitasEnArchivo();
    }

    public void editarCita(String idCita, Cita nuevaCita) {
        for (Cita cita : citas) {
            if (cita.getUniqueId().equals(idCita)) {
                // Actualizar los datos de la cita
                cita.setDia(nuevaCita.getDia());
                cita.setHora(nuevaCita.getHora());
                cita.setMotivo(nuevaCita.getMotivo());

                guardarCitasEnArchivo();
                break;
            }
        }
    }

    public void eliminarCita(Date fechaHora) {
        citas.removeIf(cita -> cita.getFechaHora().equals(fechaHora));
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

    // Otros métodos según sea necesario
}
