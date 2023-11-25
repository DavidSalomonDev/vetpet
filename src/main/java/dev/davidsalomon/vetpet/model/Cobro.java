package dev.davidsalomon.vetpet.model;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.utils.Data;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representa un registro de cobro asociado a un paciente en un veterinario.
 *
 * <p>
 * La clase Cobro contiene información sobre el monto, la fecha de cobro, la
 * descripción y el paciente asociado al cobro.</p>
 *
 * @author davidsalomon
 * @version 2.0
 */
public class Cobro implements Serializable {

    // Atributos
    private String uniqueId;
    private String idPaciente;
    private Date fechaCobro;
    private double monto;
    private String descripcion;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Constructor
    /**
     * Constructor predeterminado que genera un ID único para el cobro.
     */
    public Cobro() {
        this.uniqueId = Data.generarIDUnico();
    }

    /**
     * Constructor que inicializa un registro de cobro con información
     * específica.
     *
     * @param idPaciente Identificador único del paciente asociado al cobro.
     * @param fechaCobro Fecha del cobro en formato "yyyy-MM-dd".
     * @param monto Monto del cobro.
     * @param descripcion Descripción del cobro.
     */
    public Cobro(String idPaciente, String fechaCobro, double monto, String descripcion) {
        this.uniqueId = Data.generarIDUnico();
        this.idPaciente = idPaciente;
        this.monto = monto;
        this.descripcion = descripcion;

        try {
            this.fechaCobro = dateFormat.parse(fechaCobro);
        } catch (ParseException e) {
            e.printStackTrace(); // Manejar la excepción de formato de fecha incorrecto según sea necesario
        }
    }

    // Métodos de acceso (getters y setters)
    /**
     * Obtiene el identificador único del paciente asociado al cobro.
     *
     * @return Identificador único del paciente.
     */
    public String getIdPaciente() {
        return idPaciente;
    }

    /**
     * Establece el identificador único del paciente asociado al cobro.
     *
     * @param idPaciente Nuevo identificador único del paciente.
     */
    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * Obtiene la fecha del cobro en formato "yyyy-MM-dd".
     *
     * @return Fecha del cobro.
     */
    public String getFechaCobro() {
        return dateFormat.format(this.fechaCobro);
    }

    /**
     * Establece la fecha del cobro.
     *
     * @param fechaCobro Nueva fecha del cobro en formato "yyyy-MM-dd".
     * @throws ParseException Si hay un error al parsear la fecha del cobro.
     */
    public void setFechaCobro(String fechaCobro) throws ParseException {
        this.fechaCobro = dateFormat.parse(fechaCobro);
    }

    /**
     * Obtiene el monto del cobro.
     *
     * @return Monto del cobro.
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Establece el monto del cobro.
     *
     * @param monto Nuevo monto del cobro.
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * Obtiene la descripción del cobro.
     *
     * @return Descripción del cobro.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del cobro.
     *
     * @param descripcion Nueva descripción del cobro.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el identificador único del cobro.
     *
     * @return Identificador único del cobro.
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * Obtiene el objeto Paciente asociado al cobro.
     *
     * @param pacienteController Controlador de pacientes para buscar el
     * paciente.
     * @return Objeto Paciente asociado al cobro, o null si no se encuentra.
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
