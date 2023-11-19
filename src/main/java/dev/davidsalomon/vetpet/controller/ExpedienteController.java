package dev.davidsalomon.vetpet.controller;

import dev.davidsalomon.vetpet.model.Expediente;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpedienteController {

    private List<Expediente> expedientes;
    private static final String ARCHIVO_EXPEDIENTES = "src/data/expedientes.dat";

    public ExpedienteController() {
        this.expedientes = cargarExpedientesDesdeArchivo();
    }

    public List<Expediente> getExpedientes() {
        return expedientes;
    }

    public void agregarExpediente(Expediente expediente) {
        expedientes.add(expediente);
        guardarExpedientesEnArchivo();
    }

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
