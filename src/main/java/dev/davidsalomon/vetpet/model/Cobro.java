package dev.davidsalomon.vetpet.model;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.utils.Data;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cobro implements Serializable {

    // Atributos
    private String uniqueId;
    private String idPaciente;
    private Date fechaCobro;
    private double monto;
    private String descripcion;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Constructor
    public Cobro() {
        this.uniqueId = Data.generarIDUnico();
    }

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
    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getFechaCobro() {
        return dateFormat.format(this.fechaCobro);
    }

    public void setFechaCobro(String fechaCobro) throws ParseException {
        this.fechaCobro = dateFormat.parse(fechaCobro);
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUniqueId() {
        return uniqueId;
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
