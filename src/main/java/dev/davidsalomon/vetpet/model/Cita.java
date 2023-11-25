package dev.davidsalomon.vetpet.model;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.utils.Data;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representa una cita para un paciente en un veterinario.
 *
 * <p>
 * La clase Cita contiene información sobre la fecha, hora, motivo y el paciente
 * asociado a la cita.</p>
 *
 * @author davidsalomon
 * @version 2.0
 */
public class Cita implements Serializable {

    // Atributos
    private String uniqueId;
    private Date fechaHora;
    private String dia;
    private String hora;
    private String idPaciente;
    private String motivo;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");

    // Constructores
    /**
     * Constructor predeterminado que genera un ID único para la cita.
     */
    public Cita() {
        this.uniqueId = Data.generarIDUnico();
    }

    /**
     * Constructor que inicializa una cita con información específica.
     *
     * @param idPaciente Identificador único del paciente asociado a la cita.
     * @param dia Día de la cita en formato "yyyy-MM-dd".
     * @param hora Hora de la cita en formato "hh:mm a".
     * @param motivo Motivo de la cita.
     */
    public Cita(String idPaciente, String dia, String hora, String motivo) {
        this.uniqueId = Data.generarIDUnico();
        this.idPaciente = idPaciente;
        this.motivo = motivo;
        this.dia = dia;
        this.hora = hora;

        try {
            this.fechaHora = dateFormat.parse(this.dia + " " + this.hora);
        } catch (ParseException e) {
            e.printStackTrace(); // Manejar la excepción de formato de fecha incorrecto según sea necesario
        }
    }

    // Métodos de acceso (getters y setters)
    /**
     * Obtiene la fecha y hora de la cita en formato "yyyy-MM-dd hh:mm a".
     *
     * @return Fecha y hora de la cita.
     */
    public String getFechaHora() {
        return dateFormat.format(fechaHora);
    }

    /**
     * Establece la fecha y hora de la cita.
     *
     * @param dia Nuevo día de la cita en formato "yyyy-MM-dd".
     * @param hora Nueva hora de la cita en formato "hh:mm a".
     * @throws ParseException Si hay un error al parsear la fecha y hora.
     */
    public void setFechaHora(String dia, String hora) throws ParseException {
        this.dia = dia;
        this.hora = hora;
        this.fechaHora = dateFormat.parse(this.dia + " " + this.hora);
    }

    /**
     * Obtiene el identificador único del paciente asociado a la cita.
     *
     * @return Identificador único del paciente.
     */
    public String getIdPaciente() {
        return idPaciente;
    }

    /**
     * Establece el identificador único del paciente asociado a la cita.
     *
     * @param idPaciente Nuevo identificador único del paciente.
     */
    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * Obtiene el motivo de la cita.
     *
     * @return Motivo de la cita.
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Establece el motivo de la cita.
     *
     * @param motivo Nuevo motivo de la cita.
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Obtiene el identificador único de la cita.
     *
     * @return Identificador único de la cita.
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * Obtiene el día de la cita en formato "yyyy-MM-dd".
     *
     * @return Día de la cita.
     */
    public String getDia() {
        return dia;
    }

    /**
     * Establece el día de la cita.
     *
     * @param dia Nuevo día de la cita en formato "yyyy-MM-dd".
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    /**
     * Obtiene la hora de la cita en formato "hh:mm a".
     *
     * @return Hora de la cita.
     */
    public String getHora() {
        return hora;
    }

    /**
     * Establece la hora de la cita.
     *
     * @param hora Nueva hora de la cita en formato "hh:mm a".
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Obtiene el objeto Paciente asociado a la cita.
     *
     * @param pacienteController Controlador de pacientes para buscar el
     * paciente.
     * @return Objeto Paciente asociado a la cita, o null si no se encuentra.
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
