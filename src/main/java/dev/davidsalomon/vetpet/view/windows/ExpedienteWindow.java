package dev.davidsalomon.vetpet.view.windows;

import dev.davidsalomon.vetpet.controller.CitaController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.controller.VacunaController;
import dev.davidsalomon.vetpet.model.Paciente;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ExpedienteWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private final PacienteController pacienteController;
    private CitaController citaController;
    private final VacunaController vacunaController;
    private JTextField idTextField;
    private final JTextArea infoTextArea;

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
        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(infoTextArea, gbc);

        // Botón para cargar la información del paciente
        JButton cargarInfoButton = new JButton("Cargar Información del Paciente");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        cargarInfoButton.addActionListener((ActionEvent e) -> {
            cargarInformacionPaciente();
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

    private void cargarInformacionPaciente() {
        String id = idTextField.getText();
        Paciente paciente = buscarPaciente(id);

        if (paciente != null) {
            // Mostrar información del paciente
            String info = "**Información del paciente**" + "\n"
                    + "Nombre: " + paciente.getNombre() + "\n"
                    + "Dueño: " + paciente.getDueno() + "\n"
                    + "Edad: " + paciente.getEdad() + "\n"
                    + "Categoría: " + paciente.getCategoria() + "\n"
                    + "Raza: " + paciente.getRaza() + "\n"
                    + "Sexo: " + paciente.getSexo() + "\n"
                    + "Altura: " + paciente.getAltura() + "\n"
                    + "Peso: " + paciente.getPeso() + "\n"
                    + "Pelaje: " + paciente.getPelaje() + "\n"
                    + "Fecha de Nacimiento: " + paciente.getFechaNacimiento() + "\n";

            infoTextArea.setText(info);

        } else {
            // Limpiar el área de texto si no se encuentra el paciente
            infoTextArea.setText("");
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

}
