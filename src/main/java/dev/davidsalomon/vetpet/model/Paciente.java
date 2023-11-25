package dev.davidsalomon.vetpet.model;

import dev.davidsalomon.vetpet.utils.Data;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representa un paciente en un veterinario.
 *
 * <p>
 * La clase Paciente contiene información como nombre, dueño, edad, categoría,
 * raza, sexo, altura, peso, pelaje, fecha de nacimiento y fecha de
 * inscripción.</p>
 *
 * @author davidsalomon
 * @version 2.0
 */
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
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Constructor predeterminado que genera un ID único para el paciente.
     */
    public Paciente() {
        this.uniqueId = Data.generarIDUnico();
    }

    /**
     * Constructor que inicializa un paciente con información específica.
     *
     * @param nombre Nombre del paciente.
     * @param dueno Nombre del dueño del paciente.
     * @param edad Edad del paciente.
     * @param categoria Categoría del paciente.
     * @param raza Raza del paciente.
     * @param sexo Sexo del paciente.
     * @param altura Altura del paciente.
     * @param peso Peso del paciente.
     * @param pelaje Pelaje del paciente.
     * @param fechaNacimiento Fecha de nacimiento del paciente en formato
     * "yyyy-MM-dd".
     */
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

        try {
            this.fechaNacimiento = dateFormat.parse(fechaNacimiento);
        } catch (ParseException e) {
            e.printStackTrace(); // Manejar la excepción de formato de fecha incorrecto según sea necesario
        }
    }

    /**
     * Obtiene el nombre del paciente.
     *
     * @return Nombre del paciente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del paciente.
     *
     * @param nombre Nuevo nombre del paciente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre del dueño del paciente.
     *
     * @return Nombre del dueño del paciente.
     */
    public String getDueno() {
        return dueno;
    }

    /**
     * Establece el nombre del dueño del paciente.
     *
     * @param dueno Nuevo nombre del dueño del paciente.
     */
    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    /**
     * Obtiene la edad del paciente.
     *
     * @return Edad del paciente.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del paciente.
     *
     * @param edad Nueva edad del paciente.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene la categoría del paciente.
     *
     * @return Categoría del paciente.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del paciente.
     *
     * @param categoria Nueva categoría del paciente.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene la raza del paciente.
     *
     * @return Raza del paciente.
     */
    public String getRaza() {
        return raza;
    }

    /**
     * Establece la raza del paciente.
     *
     * @param raza Nueva raza del paciente.
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /**
     * Obtiene el sexo del paciente.
     *
     * @return Sexo del paciente.
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Establece el sexo del paciente.
     *
     * @param sexo Nuevo sexo del paciente.
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Obtiene la fecha de inscripción del paciente en formato "yyyy-MM-dd".
     *
     * @return Fecha de inscripción del paciente.
     */
    public String getFechaInscripcion() {
        return dateFormat.format(fechaInscripcion);
    }

    /**
     * Establece la fecha de inscripción del paciente.
     *
     * @param fechaInscripcion Nueva fecha de inscripción del paciente en
     * formato "yyyy-MM-dd".
     * @throws ParseException Si hay un error al parsear la fecha de
     * inscripción.
     */
    public void setFechaInscripcion(String fechaInscripcion) throws ParseException {
        this.fechaInscripcion = dateFormat.parse(fechaInscripcion);
    }

    /**
     * Obtiene la altura del paciente.
     *
     * @return Altura del paciente.
     */
    public double getAltura() {
        return altura;
    }

    /**
     * Establece la altura del paciente.
     *
     * @param altura Nueva altura del paciente.
     */
    public void setAltura(double altura) {
        this.altura = altura;
    }

    /**
     * Obtiene el peso del paciente.
     *
     * @return Peso del paciente.
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Establece el peso del paciente.
     *
     * @param peso Nuevo peso del paciente.
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Obtiene el pelaje del paciente.
     *
     * @return Pelaje del paciente.
     */
    public String getPelaje() {
        return pelaje;
    }

    /**
     * Establece el pelaje del paciente.
     *
     * @param pelaje Nuevo pelaje del paciente.
     */
    public void setPelaje(String pelaje) {
        this.pelaje = pelaje;
    }

    /**
     * Obtiene la fecha de nacimiento del paciente en formato "yyyy-MM-dd".
     *
     * @return Fecha de nacimiento del paciente.
     */
    public String getFechaNacimiento() {
        return dateFormat.format(fechaNacimiento);
    }

    /**
     * Establece la fecha de nacimiento del paciente.
     *
     * @param fechaNacimiento Nueva fecha de nacimiento del paciente en formato
     * "yyyy-MM-dd".
     * @throws ParseException Si hay un error al parsear la fecha de nacimiento.
     */
    public void setFechaNacimiento(String fechaNacimiento) throws ParseException {
        this.fechaNacimiento = dateFormat.parse(fechaNacimiento);
    }

    /**
     * Obtiene el identificador único del paciente.
     *
     * @return Identificador único del paciente.
     */
    public String getUniqueId() {
        return uniqueId;
    }

}
