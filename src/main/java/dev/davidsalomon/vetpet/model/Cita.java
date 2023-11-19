package dev.davidsalomon.vetpet.model;

import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable {

    // Atributos
    private Date fechaHora;
    private Paciente paciente;
    private String motivo;

    // Constructores
    public Cita() {
    }

    public Cita(Date fechaHora, Paciente paciente, String motivo) {
        this.fechaHora = fechaHora;
        this.paciente = paciente;
        this.motivo = motivo;
    }

    // Métodos de acceso (getters y setters)
    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

}
