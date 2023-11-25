package dev.davidsalomon.vetpet.view.windows;

import dev.davidsalomon.vetpet.controller.CitaController;
import dev.davidsalomon.vetpet.controller.CobroController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.controller.VacunaController;
import dev.davidsalomon.vetpet.model.Cita;
import dev.davidsalomon.vetpet.model.Cobro;
import dev.davidsalomon.vetpet.model.Paciente;
import dev.davidsalomon.vetpet.model.Vacuna;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Window for viewing patient records in VetPetClinic.
 *
 * <p>
 * This window allows users to enter a patient's ID and view their information,
 * including appointments, vaccinations, and payments.</p>
 *
 * @author davidsalomon
 * @version 1.0
 */
public class ExpedienteWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private final PacienteController pacienteController;
    private CitaController citaController;
    private final VacunaController vacunaController;
    private final CobroController cobroController;

    private JTextField idTextField;
    private final JEditorPane infoEditorPane;
    private final JScrollPane infoScrollPane;

    /**
     * Constructor for the patient record window.
     *
     * @param pacienteController Patient controller.
     * @param citaController Appointment controller.
     * @param vacunaController Vaccination controller.
     * @param cobroController Payment controller.
     */
    public ExpedienteWindow(PacienteController pacienteController, CitaController citaController, VacunaController vacunaController, CobroController cobroController) {
        this.pacienteController = pacienteController;
        this.citaController = citaController;
        this.vacunaController = vacunaController;
        this.cobroController = cobroController;

        // Basic JFrame configuration
        setTitle("Expediente de pacientes - VetPetClinic");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Text field for entering the patient's ID
        addLabelAndTextField("ID del Paciente:", gbc);

        // Text area for displaying patient information
        infoEditorPane = new JEditorPane();
        infoScrollPane = new JScrollPane(infoEditorPane);
        infoScrollPane.setPreferredSize(new Dimension(350, 450));
        infoScrollPane.setMinimumSize(new Dimension(350, 450));
        infoEditorPane.setEditable(false);
        infoEditorPane.setContentType("text/html");

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(infoScrollPane, gbc);

        // Button to load patient information
        JButton cargarInfoButton = new JButton("Cargar Información del Paciente");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        cargarInfoButton.addActionListener((ActionEvent e) -> {
            cargarInformacion();
        });
        add(cargarInfoButton, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addLabelAndTextField(String labelText, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText);
        gbc.gridx = 0;
        add(label, gbc);

        JTextField textField = new JTextField();
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(textField, gbc);

        if (labelText.equals("ID del Paciente:")) {
            idTextField = textField;
        }

        gbc.gridy++;
    }

    private void cargarInformacion() {
        String id = idTextField.getText();
        Paciente paciente = buscarPaciente(id);
        List<Cita> citas = obtenerCitasPorIdPaciente(id);
        List<Vacuna> vacunas = obtenerVacunasPorIdPaciente(id);
        List<Cobro> cobros = obtenerCobrosPorIdPaciente(id);

        if (paciente != null) {
            // Display patient information
            String info = "<html><body style='font-family: Arial, sans-serif; font-size: 14px;'>"
                    + "<div>"
                    + "<h1><strong>Información del paciente</strong></h1><hr>"
                    + "Nombre: <span style='color: #1E90FF;'>" + paciente.getNombre() + "</span><br>"
                    + "Dueño: <span style='color: #1E90FF;'>" + paciente.getDueno() + "</span><br>"
                    + "Edad: <span style='color: #1E90FF;'>" + paciente.getEdad() + "</span><br>"
                    + "Categoría: <span style='color: #1E90FF;'>" + paciente.getCategoria() + "</span><br>"
                    + "Raza: <span style='color: #1E90FF;'>" + paciente.getRaza() + "</span><br>"
                    + "Sexo: <span style='color: #1E90FF;'>" + paciente.getSexo() + "</span><br>"
                    + "Altura: <span style='color: #1E90FF;'>" + paciente.getAltura() + "</span><br>"
                    + "Peso: <span style='color: #1E90FF;'>" + paciente.getPeso() + "</span><br>"
                    + "Pelaje: <span style='color: #1E90FF;'>" + paciente.getPelaje() + "</span><br>"
                    + "Fecha de Nacimiento: <span style='color: #1E90FF;'>" + paciente.getFechaNacimiento() + "</span>"
                    + "</div>"
                    + generarHTMLCitas(citas)
                    + generarHTMLVacunas(vacunas)
                    + generarHTMLCobros(cobros);

            infoEditorPane.setText(info);

        } else {
            // Clear the text area if the patient is not found
            infoEditorPane.setText("");
            JOptionPane.showMessageDialog(this, "Paciente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Paciente buscarPaciente(String id) {
        for (Paciente paciente : pacienteController.getPacientes()) {
            if (paciente.getUniqueId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }

    /**
     *
     * @param idPaciente
     * @return
     */
    public List<Cita> obtenerCitasPorIdPaciente(String idPaciente) {
        // Get and display patient information
        List<Cita> citas = citaController.getCitas();

        // Filter appointments by patient ID
        List<Cita> citasDelPaciente = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getIdPaciente().equals(idPaciente)) {
                citasDelPaciente.add(cita);
            }
        }

        return citasDelPaciente;
    }

    /**
     *
     * @param citas
     * @return
     */
    public String generarHTMLCitas(List<Cita> citas) {
        StringBuilder htmlCitas = new StringBuilder();

        htmlCitas.append("<div>")
                .append("<h1><strong>Citas</strong></h1><hr>");

        for (Cita cita : citas) {
            String infoCita = "Fecha:  <span style='color: #1E90FF;'>" + cita.getFechaHora() + "</span><br>"
                    + "Motivo:  <span style='color: #1E90FF;'>" + cita.getMotivo() + "</span><br>";
            htmlCitas.append(infoCita);
        }

        htmlCitas.append("</div>");

        return htmlCitas.toString();
    }

    /**
     *
     * @param idPaciente
     * @return
     */
    public List<Vacuna> obtenerVacunasPorIdPaciente(String idPaciente) {
        List<Vacuna> vacunas = vacunaController.getVacunas();

        // Filter vaccinations by patient ID
        List<Vacuna> vacunasDelPaciente = new ArrayList<>();
        for (Vacuna vacuna : vacunas) {
            if (vacuna.getIdPaciente().equals(idPaciente)) {
                vacunasDelPaciente.add(vacuna);
            }
        }

        return vacunasDelPaciente;
    }

    /**
     *
     * @param vacunas
     * @return
     */
    public String generarHTMLVacunas(List<Vacuna> vacunas) {
        StringBuilder htmlVacunas = new StringBuilder();

        htmlVacunas.append("<div>")
                .append("<h1><strong>Vacunas</strong></h1><hr>");

        for (Vacuna vacuna : vacunas) {
            String infoVacuna = "Fecha:  <span style='color: #1E90FF;'>" + vacuna.getFechaVacuna() + "</span><br>"
                    + "Nombre: <span style='color: #1E90FF;'> " + vacuna.getNombreVacuna() + "</span><br>"
                    + "Altura al momento: <span style='color: #1E90FF;'> " + vacuna.getAlturaEnMomento() + "</span><br>"
                    + "Peso al momento:  <span style='color: #1E90FF;'>" + vacuna.getPesoEnMomento() + "</span><br>"
                    + "Edad al momento:  <span style='color: #1E90FF;'>" + vacuna.getEdadEnMomento() + "</span><br>";

            htmlVacunas.append(infoVacuna);
        }

        htmlVacunas.append("</div>");

        return htmlVacunas.toString();
    }

    /**
     *
     * @param idPaciente
     * @return
     */
    public List<Cobro> obtenerCobrosPorIdPaciente(String idPaciente) {
        List<Cobro> cobros = cobroController.getCobros();

        // Filter payments by patient ID
        List<Cobro> cobrosDelPaciente = new ArrayList<>();
        for (Cobro cobro : cobros) {
            if (cobro.getIdPaciente().equals(idPaciente)) {
                cobrosDelPaciente.add(cobro);
            }
        }

        return cobrosDelPaciente;
    }

    /**
     *
     * @param cobros
     * @return
     */
    public String generarHTMLCobros(List<Cobro> cobros) {
        StringBuilder htmlCobros = new StringBuilder();

        htmlCobros.append("<div>")
                .append("<h1><strong>Cobros</strong></h1><hr>");

        for (Cobro cobro : cobros) {
            String infoCobro = "Fecha:  <span style='color: #1E90FF;'>" + cobro.getFechaCobro() + "</span><br>"
                    + "Monto:  <span style='color: #1E90FF;'>" + cobro.getMonto() + "</span><br>"
                    + "Descripción:  <span style='color: #1E90FF;'>" + cobro.getDescripcion() + "</span><br>";

            htmlCobros.append(infoCobro);
        }

        htmlCobros.append("</div>");

        return htmlCobros.toString();
    }
}
