package dev.davidsalomon.vetpet.controller;

import dev.davidsalomon.vetpet.model.Cita;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
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

}
