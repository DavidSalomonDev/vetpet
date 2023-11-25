package dev.davidsalomon.vetpet.model;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.utils.Data;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representa una vacuna aplicada a un paciente en un veterinario.
 *
 * <p>
 * La clase Vacuna contiene información sobre la fecha de la vacuna, el nombre
 * de la vacuna, el peso, altura y edad del paciente en el momento de la
 * aplicación.</p>
 *
 * @author davidsalomon
 * @version 2.0
 */
public class Vacuna implements Serializable {

    private static final long serialVersionUID = 1L;

    // Atributos
    private String uniqueId;
    private String idPaciente;
    private Date fechaVacuna;
    private String nombreVacuna;
    private double pesoEnMomento;
    private double alturaEnMomento;
    private int edadEnMomento;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Constructores
    /**
     * Constructor predeterminado que genera un ID único para la vacuna.
     */
    public Vacuna() {
        this.uniqueId = Data.generarIDUnico();
    }

    /**
     * Constructor que inicializa una vacuna con información específica.
     *
     * @param nombreVacuna Nombre de la vacuna.
     * @param fechaVacuna Fecha de la vacuna en formato "yyyy-MM-dd".
     * @param idPaciente Identificador único del paciente asociado a la vacuna.
     */
    public Vacuna(String nombreVacuna, String fechaVacuna, String idPaciente) {
        this.uniqueId = Data.generarIDUnico();
        this.nombreVacuna = nombreVacuna;
        this.idPaciente = idPaciente;
        try {
            this.fechaVacuna = dateFormat.parse(fechaVacuna);
        } catch (ParseException e) {
            e.printStackTrace(); // Manejar la excepción de formato de fecha incorrecto según sea necesario
        }
    }

    // Métodos de acceso (getters y setters)
    /**
     * Obtiene la fecha de la vacuna en formato "yyyy-MM-dd".
     *
     * @return Fecha de la vacuna.
     */
    public String getFechaVacuna() {
        return dateFormat.format(fechaVacuna);
    }

    /**
     * Establece la fecha de la vacuna.
     *
     * @param fechaVacuna Nueva fecha de la vacuna en formato "yyyy-MM-dd".
     * @throws ParseException Si hay un error al parsear la fecha de la vacuna.
     */
    public void setFechaVacuna(String fechaVacuna) throws ParseException {
        this.fechaVacuna = dateFormat.parse(fechaVacuna);
    }

    /**
     * Obtiene el nombre de la vacuna.
     *
     * @return Nombre de la vacuna.
     */
    public String getNombreVacuna() {
        return nombreVacuna;
    }

    /**
     * Establece el nombre de la vacuna.
     *
     * @param nombreVacuna Nuevo nombre de la vacuna.
     */
    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    /**
     * Obtiene el peso del paciente en el momento de la aplicación de la vacuna.
     *
     * @return Peso del paciente en el momento de la vacuna.
     */
    public double getPesoEnMomento() {
        return pesoEnMomento;
    }

    /**
     * Establece el peso del paciente en el momento de la aplicación de la
     * vacuna.
     *
     * @param pesoEnMomento Nuevo peso del paciente en el momento de la vacuna.
     */
    public void setPesoEnMomento(double pesoEnMomento) {
        this.pesoEnMomento = pesoEnMomento;
    }

    /**
     * Obtiene la altura del paciente en el momento de la aplicación de la
     * vacuna.
     *
     * @return Altura del paciente en el momento de la vacuna.
     */
    public double getAlturaEnMomento() {
        return alturaEnMomento;
    }

    /**
     * Establece la altura del paciente en el momento de la aplicación de la
     * vacuna.
     *
     * @param alturaEnMomento Nueva altura del paciente en el momento de la
     * vacuna.
     */
    public void setAlturaEnMomento(double alturaEnMomento) {
        this.alturaEnMomento = alturaEnMomento;
    }

    /**
     * Obtiene la edad del paciente en el momento de la aplicación de la vacuna.
     *
     * @return Edad del paciente en el momento de la vacuna.
     */
    public int getEdadEnMomento() {
        return edadEnMomento;
    }

    /**
     * Establece la edad del paciente en el momento de la aplicación de la
     * vacuna.
     *
     * @param edadEnMomento Nueva edad del paciente en el momento de la vacuna.
     */
    public void setEdadEnMomento(int edadEnMomento) {
        this.edadEnMomento = edadEnMomento;
    }

    /**
     * Obtiene el identificador único de la vacuna.
     *
     * @return Identificador único de la vacuna.
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * Obtiene el identificador único del paciente asociado a la vacuna.
     *
     * @return Identificador único del paciente asociado a la vacuna.
     */
    public String getIdPaciente() {
        return idPaciente;
    }

    /**
     * Establece el identificador único del paciente asociado a la vacuna.
     *
     * @param idPaciente Nuevo identificador único del paciente asociado a la
     * vacuna.
     */
    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * Obtiene el objeto Paciente asociado a la vacuna.
     *
     * @param pacienteController Controlador de pacientes que contiene la lista
     * de pacientes.
     * @return Objeto Paciente asociado a la vacuna.
     */
    public Paciente getPaciente(PacienteController pacienteController) {
        for (Paciente paciente : pacienteController.getPacientes()) {
            if (paciente.getUniqueId().equals(this.idPaciente)) {
                return paciente;
            }
        }
        return null;
    }
}
