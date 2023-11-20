package dev.davidsalomon.vetpet.model;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.utils.Data;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public Cita() {
        this.uniqueId = Data.generarIDUnico();
    }

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
    public String getFechaHora() {
        return dateFormat.format(fechaHora);
    }

    public void setFechaHora(String dia, String hora) throws ParseException {
        this.dia = dia;
        this.hora = hora;
        this.fechaHora = dateFormat.parse(this.dia + " " + this.hora);
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Paciente getPaciente(PacienteController pacienteController) {
        for (Paciente paciente : pacienteController.getPacientes()) {
            if (paciente.getUniqueId().equals(this.idPaciente)) {
                return paciente;
            }
        }
        return null;
    }

}
