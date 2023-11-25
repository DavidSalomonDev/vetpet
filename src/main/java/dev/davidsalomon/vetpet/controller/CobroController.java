package dev.davidsalomon.vetpet.controller;

import dev.davidsalomon.vetpet.model.Cobro;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para gestionar los cobros en el sistema.
 *
 * <p>
 * Este controlador proporciona métodos para agregar y obtener cobros.</p>
 *
 * @author davidsalomon
 * @version 2.0
 */
public class CobroController {

    private List<Cobro> cobros;
    private static final String ARCHIVO_COBROS = "src/data/cobros.dat";

    /**
     * Constructor que inicializa la lista de cobros cargándolos desde el
     * archivo.
     */
    public CobroController() {
        this.cobros = cargarCobrosDesdeArchivo();
    }

    /**
     * Obtiene la lista de cobros almacenados en el sistema.
     *
     * @return Lista de cobros.
     */
    public List<Cobro> getCobros() {
        return cobros;
    }

    /**
     * Agrega un nuevo cobro a la lista y guarda la lista actualizada en el
     * archivo.
     *
     * @param cobro Cobro a agregar.
     */
    public void agregarCobro(Cobro cobro) {
        cobros.add(cobro);
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
}
