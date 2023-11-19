package dev.davidsalomon.vetpet.view.panels;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.model.Paciente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EliminarPacientePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final PacienteController pacienteController;
    private JTextField idTextField;
    private final JTextArea infoTextArea;

    public EliminarPacientePanel(PacienteController pacienteController) {
        this.pacienteController = pacienteController;

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
        cargarInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarInformacionPaciente();
            }
        });
        add(cargarInfoButton, gbc);

        // Botón para dar de baja al paciente
        JButton darDeBajaButton = new JButton("Dar de Baja");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        darDeBajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarDarDeBaja();
            }
        });
        add(darDeBajaButton, gbc);

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
            String info = "Nombre: " + paciente.getNombre() + "\n"
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

    private void confirmarDarDeBaja() {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de dar de baja al paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            darDeBajaPaciente();
        }
    }

    private void darDeBajaPaciente() {
        String id = idTextField.getText();
        Paciente paciente = buscarPaciente(id);

        if (paciente != null) {
            pacienteController.darDeBajaPaciente(id);
            JOptionPane.showMessageDialog(this, "Paciente dado de baja exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
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

    private void limpiarCampos() {
        idTextField.setText("");
        infoTextArea.setText("");
    }
}
