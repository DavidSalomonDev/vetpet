package dev.davidsalomon.vetpet.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Representa el expediente médico de un paciente en un veterinario.
 *
 * <p>
 * La clase Expediente contiene información sobre vacunas, paciente, fecha de la
 * cita, diagnóstico y medicamentos.</p>
 *
 * @author davidsalomon
 * @version 2.0
 */
public class Expediente implements Serializable {

    // Atributos
    private List<Vacuna> vacunas;
    private Paciente paciente;
    private Date fechaCita;
    private String diagnostico;
    private List<String> medicamentos;

    // Constructor
    /**
     * Constructor que inicializa un expediente médico con información
     * específica.
     *
     * @param vacunas Lista de vacunas aplicadas al paciente.
     * @param paciente Paciente asociado al expediente.
     * @param fechaCita Fecha de la cita médica.
     * @param diagnostico Diagnóstico médico del paciente.
     * @param medicamentos Lista de medicamentos recetados al paciente.
     */
    public Expediente(List<Vacuna> vacunas, Paciente paciente, Date fechaCita, String diagnostico, List<String> medicamentos) {
        this.vacunas = vacunas;
        this.paciente = paciente;
        this.fechaCita = fechaCita;
        this.diagnostico = diagnostico;
        this.medicamentos = medicamentos;
    }

    // Métodos de acceso (getters y setters)
    /**
     * Obtiene la lista de vacunas aplicadas al paciente.
     *
     * @return Lista de vacunas.
     */
    public List<Vacuna> getVacunas() {
        return vacunas;
    }

    /**
     * Establece la lista de vacunas aplicadas al paciente.
     *
     * @param vacunas Nueva lista de vacunas.
     */
    public void setVacunas(List<Vacuna> vacunas) {
        this.vacunas = vacunas;
    }

    /**
     * Obtiene el paciente asociado al expediente.
     *
     * @return Objeto Paciente asociado al expediente.
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Establece el paciente asociado al expediente.
     *
     * @param paciente Nuevo paciente asociado al expediente.
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * Obtiene la fecha de la cita médica.
     *
     * @return Fecha de la cita médica.
     */
    public Date getFechaCita() {
        return fechaCita;
    }

    /**
     * Establece la fecha de la cita médica.
     *
     * @param fechaCita Nueva fecha de la cita médica.
     */
    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    /**
     * Obtiene el diagnóstico médico del paciente.
     *
     * @return Diagnóstico médico.
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Establece el diagnóstico médico del paciente.
     *
     * @param diagnostico Nuevo diagnóstico médico.
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * Obtiene la lista de medicamentos recetados al paciente.
     *
     * @return Lista de medicamentos.
     */
    public List<String> getMedicamentos() {
        return medicamentos;
    }

    /**
     * Establece la lista de medicamentos recetados al paciente.
     *
     * @param medicamentos Nueva lista de medicamentos.
     */
    public void setMedicamentos(List<String> medicamentos) {
        this.medicamentos = medicamentos;
    }
}
