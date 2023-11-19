package dev.davidsalomon.vetpet.model;

import dev.davidsalomon.vetpet.utils.Data;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Paciente implements Serializable {

    private String uniqueId;
    private String nombre;
    private String dueno;
    private int edad;
    private String categoria;
    private String raza;
    private String sexo;
    private Date fechaInscripcion;
    private double altura;
    private double peso;
    private String pelaje;
    private Date fechaNacimiento;

    public Paciente() {
        this.uniqueId = Data.generarIDUnico();
    }

    public Paciente(String nombre, String dueno, int edad, String categoria, String raza, String sexo,
            double altura, double peso, String pelaje, String fechaNacimiento) {
        this.uniqueId = Data.generarIDUnico();
        this.nombre = nombre;
        this.dueno = dueno;
        this.edad = edad;
        this.categoria = categoria;
        this.raza = raza;
        this.sexo = sexo;
        this.altura = altura;
        this.peso = peso;
        this.pelaje = pelaje;
        this.fechaInscripcion = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            this.fechaNacimiento = dateFormat.parse(fechaNacimiento);
        } catch (ParseException e) {
            e.printStackTrace(); // Manejar la excepción de formato de fecha incorrecto según sea necesario
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getPelaje() {
        return pelaje;
    }

    public void setPelaje(String pelaje) {
        this.pelaje = pelaje;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getUniqueId() {
        return uniqueId;
    }

}
