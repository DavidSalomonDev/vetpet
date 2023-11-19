package dev.davidsalomon.vetpet.model;

import java.io.Serializable;
import java.util.Date;

public class Cobro implements Serializable {

    // Atributos
    private Paciente paciente;
    private Date fechaCobro;
    private double monto;
    private String descripcion;

    // Constructor
    public Cobro() {
    }

    public Cobro(Paciente paciente, Date fechaCobro, double monto, String descripcion) {
        this.paciente = paciente;
        this.fechaCobro = fechaCobro;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    // Métodos de acceso (getters y setters)
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
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

    // Otros métodos según sea necesario
    @Override
    public String toString() {
        return "Cobro{"
                + "paciente=" + paciente.getNombre()
                + // Se muestra el nombre del paciente
                ", fechaCobro=" + fechaCobro
                + ", monto=" + monto
                + ", descripcion='" + descripcion + '\''
                + '}';
    }
}
