package dev.davidsalomon.vetpet.view.windows;

import dev.davidsalomon.vetpet.controller.CitaController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.controller.VacunaController;
import dev.davidsalomon.vetpet.model.Cita;
import dev.davidsalomon.vetpet.model.Paciente;
import dev.davidsalomon.vetpet.model.Vacuna;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ExpedienteWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private final PacienteController pacienteController;
    private CitaController citaController;
    private final VacunaController vacunaController;
    private JTextField idTextField;
    private final JEditorPane infoEditorPane;
    private final JScrollPane infoScrollPane;

    public ExpedienteWindow(PacienteController pacienteController, CitaController citaController, VacunaController vacunaController) {
        this.pacienteController = pacienteController;
        this.citaController = citaController;
        this.vacunaController = vacunaController;

        // Configuración básica del JFrame
        setTitle("Expediente de pacientes - VetPetClinic");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campo de texto para ingresar el ID del paciente
        addLabelAndTextField("ID del Paciente:", gbc);

        // Área de texto para mostrar la información del paciente
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

        // Botón para cargar la información del paciente
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

        if (paciente != null) {
            // Mostrar información del paciente
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
                    + generarHTMLVacunas(vacunas);

            infoEditorPane.setText(info);

        } else {
            // Limpiar el área de texto si no se encuentra el paciente
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

    public List<Cita> obtenerCitasPorIdPaciente(String idPaciente) {
        // Obtener y mostrar información de pacientes
        List<Cita> citas = citaController.getCitas();

        // Filtrar las citas por el ID del paciente
        List<Cita> citasDelPaciente = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getIdPaciente().equals(idPaciente)) {
                citasDelPaciente.add(cita);
            }
        }

        return citasDelPaciente;
    }

    public String generarHTMLCitas(List<Cita> citas) {
        StringBuilder htmlCitas = new StringBuilder();

        htmlCitas.append("<div>")
                .append("<h1><strong>Citas</strong></h1><hr>");

        for (Cita cita : citas) {
            String infoCita = "<p>Fecha: " + cita.getFechaHora() + "<br>"
                    + "Motivo: " + cita.getMotivo() + "</p>";
            htmlCitas.append(infoCita);
        }

        htmlCitas.append("</div>");

        return htmlCitas.toString();
    }

    public List<Vacuna> obtenerVacunasPorIdPaciente(String idPaciente) {
        List<Vacuna> vacunas = vacunaController.getVacunas();

        // Filtrar las vacunas por el ID del paciente
        List<Vacuna> vacunasDelPaciente = new ArrayList<>();
        for (Vacuna vacuna : vacunas) {
            if (vacuna.getIdPaciente().equals(idPaciente)) {
                vacunasDelPaciente.add(vacuna);
            }
        }

        return vacunasDelPaciente;
    }

    public String generarHTMLVacunas(List<Vacuna> vacunas) {
        StringBuilder htmlVacunas = new StringBuilder();

        htmlVacunas.append("<div>")
                .append("<h1><strong>Vacunas</strong></h1><hr>");

        for (Vacuna vacuna : vacunas) {
            String infoVacuna = "<p>Fecha: " + vacuna.getFechaVacuna() + "<br>"
                    + "Nombre: " + vacuna.getNombreVacuna() + "</p>";
            htmlVacunas.append(infoVacuna);
        }

        htmlVacunas.append("</div>");

        return htmlVacunas.toString();
    }

}
