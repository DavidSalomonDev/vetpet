package dev.davidsalomon.vetpet.model;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.utils.Data;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public Vacuna() {
        this.uniqueId = Data.generarIDUnico();
    }

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
    public String getFechaVacuna() {
        return dateFormat.format(fechaVacuna);
    }

    public void setFechaVacuna(String fechaVacuna) throws ParseException {
        this.fechaVacuna = dateFormat.parse(fechaVacuna);
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    public double getPesoEnMomento() {
        return pesoEnMomento;
    }

    public void setPesoEnMomento(double pesoEnMomento) {
        this.pesoEnMomento = pesoEnMomento;
    }

    public double getAlturaEnMomento() {
        return alturaEnMomento;
    }

    public void setAlturaEnMomento(double alturaEnMomento) {
        this.alturaEnMomento = alturaEnMomento;
    }

    public int getEdadEnMomento() {
        return edadEnMomento;
    }

    public void setEdadEnMomento(int edadEnMomento) {
        this.edadEnMomento = edadEnMomento;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Paciente getPaciente(PacienteController pacienteController) {
        for (Paciente paciente : pacienteController.getPacientes()) {
            if (paciente.getUniqueId().equals(this.idPaciente)) {
                return paciente;
            }
        }
        return null;
    }

    // Otros métodos según sea necesario
    @Override
    public String toString() {
        return "Vacuna{"
                + "fechaVacuna=" + fechaVacuna
                + ", nombreVacuna='" + nombreVacuna + '\''
                + ", pesoEnMomento=" + pesoEnMomento
                + ", alturaEnMomento=" + alturaEnMomento
                + ", edadEnMomento=" + edadEnMomento
                + '}';
    }
}
