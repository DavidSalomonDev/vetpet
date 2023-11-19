package dev.davidsalomon.vetpet.model;

import java.io.Serializable;
import java.util.Date;

public class Vacuna implements Serializable {

    // Atributos
    private Date fechaVacuna;
    private String nombreVacuna;
    private double pesoEnMomento;
    private double alturaEnMomento;
    private int edadEnMomento;

    // Constructores
    public Vacuna() {
    }

    public Vacuna(Date fechaVacuna, String nombreVacuna, double pesoEnMomento, double alturaEnMomento, int edadEnMomento) {
        this.fechaVacuna = fechaVacuna;
        this.nombreVacuna = nombreVacuna;
        this.pesoEnMomento = pesoEnMomento;
        this.alturaEnMomento = alturaEnMomento;
        this.edadEnMomento = edadEnMomento;
    }

    // Métodos de acceso (getters y setters)
    public Date getFechaVacuna() {
        return fechaVacuna;
    }

    public void setFechaVacuna(Date fechaVacuna) {
        this.fechaVacuna = fechaVacuna;
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
