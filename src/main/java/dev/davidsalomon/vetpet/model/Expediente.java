package dev.davidsalomon.vetpet.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Expediente implements Serializable {

    // Atributos
    private List<Vacuna> vacunas;
    private Paciente paciente;
    private Date fechaCita;
    private String diagnostico;
    private List<String> medicamentos;

    public Expediente() {
    }

    // Constructor
    public Expediente(List<Vacuna> vacunas, Paciente paciente, Date fechaCita, String diagnostico, List<String> medicamentos) {
        this.vacunas = vacunas;
        this.paciente = paciente;
        this.fechaCita = fechaCita;
        this.diagnostico = diagnostico;
        this.medicamentos = medicamentos;
    }

    // Métodos de acceso (getters y setters)
    public List<Vacuna> getVacunas() {
        return vacunas;
    }

    public void setVacunas(List<Vacuna> vacunas) {
        this.vacunas = vacunas;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public List<String> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<String> medicamentos) {
        this.medicamentos = medicamentos;
    }

    // Otros métodos según sea necesario
    @Override
    public String toString() {
        return "Expediente{"
                + "vacunas=" + vacunas
                + ", paciente=" + paciente.getNombre()
                + // Se muestra el nombre del paciente
                ", fechaCita=" + fechaCita
                + ", diagnostico='" + diagnostico + '\''
                + ", medicamentos=" + medicamentos
                + '}';
    }
}
