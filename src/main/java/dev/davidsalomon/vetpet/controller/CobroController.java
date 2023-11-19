package dev.davidsalomon.vetpet.controller;

import dev.davidsalomon.vetpet.model.Cobro;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CobroController {

    private List<Cobro> cobros;
    private static final String ARCHIVO_COBROS = "src/data/cobros.dat";

    public CobroController() {
        this.cobros = cargarCobrosDesdeArchivo();
    }

    public List<Cobro> getCobros() {
        return cobros;
    }

    public void agregarCobro(Cobro cobro) {
        cobros.add(cobro);
        guardarCobrosEnArchivo();
    }

    public void eliminarCobro(Date fechaCobro) {
        cobros.removeIf(cobro -> cobro.getFechaCobro().equals(fechaCobro));
        guardarCobrosEnArchivo();
    }

    private List<Cobro> cargarCobrosDesdeArchivo() {
        List<Cobro> cobrosCargados = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_COBROS))) {
            cobrosCargados = (List<Cobro>) ois.readObject();
        } catch (FileNotFoundException e) {
            // El archivo aún no existe, se creará cuando se guarde el primer cobro.
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades.
        }
        return cobrosCargados;
    }

    private void guardarCobrosEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_COBROS))) {
            oos.writeObject(cobros);
        } catch (IOException e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades.
        }
    }

    // Otros métodos según sea necesario
}
