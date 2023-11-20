package dev.davidsalomon.vetpet.view.panels;

import dev.davidsalomon.vetpet.controller.CitaController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.model.Cita;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class EliminarCitaPanel extends JPanel {

    private CitaController citaController;
    private PacienteController pacienteController;

    private JTextField idTextField;
    private JTextArea infoTextArea;

    public EliminarCitaPanel(PacienteController pacienteController, CitaController citaController) {
        this.pacienteController = pacienteController;
        this.citaController = citaController;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campo de texto para ingresar el ID de la cita
        addLabelAndTextField("ID de la cita:", gbc);

        // Área de texto para mostrar la información de la cita
        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(infoTextArea, gbc);

        // Botón para cargar la información de la cita
        JButton cargarInfoButton = new JButton("Cargar información de la cita");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        cargarInfoButton.addActionListener((ActionEvent e) -> {
            cargarInformacionCita();
        });
        add(cargarInfoButton, gbc);

        // Botón para declinar la cita
        JButton declinarCitaButton = new JButton("Declinar la cita");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        declinarCitaButton.addActionListener((ActionEvent e) -> {
            confirmarDeclinarCita();
        });
        add(declinarCitaButton, gbc);

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

        if (labelText.equals("ID de la cita:")) {
            idTextField = textField;
        }

        gbc.gridy++;
    }

    private void cargarInformacionCita() {
        String id = idTextField.getText();
        Cita cita = buscarCita(id);

        if (cita != null) {
            // Mostrar información de la cita
            String info = "ID del Paciente: " + cita.getIdPaciente() + "\n"
                    + "Nombre del Paciente: " + cita.getPaciente(pacienteController).getNombre() + "\n"
                    + "Dueño: " + cita.getPaciente(pacienteController).getDueno() + "\n"
                    + "Día de la Cita: " + cita.getDia() + "\n"
                    + "Hora de la Cita: " + cita.getHora() + "\n"
                    + "Motivo de la Cita: " + cita.getMotivo() + "\n";
            infoTextArea.setText(info);
        } else {
            // Limpiar el área de texto si no se encuentra la cita
            infoTextArea.setText("");
            JOptionPane.showMessageDialog(this, "Cita no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Cita buscarCita(String id) {
        for (Cita cita : citaController.getCitas()) {
            if (cita.getUniqueId().equals(id)) {
                return cita;
            }
        }
        return null;
    }

    private void declinarCita() {
        String id = idTextField.getText();
        Cita cita = buscarCita(id);

        if (cita != null) {
            citaController.eliminarCita(id);
            JOptionPane.showMessageDialog(this, "Cita declinada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Cita no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void confirmarDeclinarCita() {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de declinar la cita al paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            declinarCita();
        }
    }

    private void limpiarCampos() {
        idTextField.setText("");
        infoTextArea.setText("");
    }

}
