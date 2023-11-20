package dev.davidsalomon.vetpet.view.panels;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.controller.VacunaController;
import dev.davidsalomon.vetpet.model.Paciente;
import dev.davidsalomon.vetpet.model.Vacuna;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class AgregarVacunaPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final VacunaController vacunaController;
    private final PacienteController pacienteController;

    private JTextField idPacienteTextField, nombreVacunaTextField, fechaTextField;
    private final JTextArea infoTextArea;

    public AgregarVacunaPanel(PacienteController pacienteController, VacunaController vacunaController) {
        this.pacienteController = pacienteController;
        this.vacunaController = vacunaController;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campos de texto para ingresar los datos de la vacuna
        addLabelAndTextField("Id del Paciente:", gbc);

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

        addLabelAndTextField("", gbc);
        addLabelAndTextField("Nombre de la vacuna:", gbc);
        addLabelAndTextField("Fecha:", gbc);

        JButton aplicarVacunaButton = new JButton("Aplicar vacuna");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        aplicarVacunaButton.addActionListener((ActionEvent e) -> {
            agregarNuevaVacuna();
        });
        add(aplicarVacunaButton, gbc);
        setVisible(true);
    }

    private void addLabelAndTextField(String labelText, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(label, gbc);

        JTextField textField = new JTextField();
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(textField, gbc);

        switch (labelText) {
            case "Id del Paciente:" ->
                idPacienteTextField = textField;
            case "Nombre de la vacuna:" ->
                nombreVacunaTextField = textField;
            case "Fecha:" ->
                fechaTextField = textField;
            default -> {
            }

        }
        gbc.gridy++;
    }

    private void agregarNuevaVacuna() {
        try {
            String idPaciente = idPacienteTextField.getText();
            String nombreVacuna = nombreVacunaTextField.getText();
            String fecha = fechaTextField.getText();

            Vacuna nuevaVacuna = new Vacuna(nombreVacuna, fecha, idPaciente);
            Paciente pacienteEncontrado = nuevaVacuna.getPaciente(pacienteController);
            nuevaVacuna.setAlturaEnMomento(pacienteEncontrado.getAltura());
            nuevaVacuna.setPesoEnMomento(pacienteEncontrado.getPeso());
            nuevaVacuna.setEdadEnMomento(pacienteEncontrado.getEdad());

            // Agregar el paciente al controlador
            vacunaController.agregarVacuna(nuevaVacuna);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Vacuna aplicada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar los campos de texto después de agregar el paciente
            limpiarCampos();
        } catch (NumberFormatException e) {
            // En caso de error de formato, mostrar cuadro de diálogo
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos, y asegúrese de usar el formato correcto.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarInformacionPaciente() {
        String id = idPacienteTextField.getText();
        Paciente paciente = buscarPacientePorID(id);

        if (paciente != null) {
            // Mostrar información del paciente
            String info = "Nombre: " + paciente.getNombre() + "\n"
                    + "Dueño: " + paciente.getDueno() + "\n"
                    + "Edad al momento: " + paciente.getEdad() + "\n"
                    + "Altura al momento: " + paciente.getAltura() + "\n"
                    + "Peso al momento: " + paciente.getPeso() + "\n";
            infoTextArea.setText(info);
        } else {
            // Limpiar el área de texto si no se encuentra el paciente
            infoTextArea.setText("");
            JOptionPane.showMessageDialog(this, "Paciente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Paciente buscarPacientePorID(String id) {
        for (Paciente paciente : pacienteController.getPacientes()) {
            if (paciente.getUniqueId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }

    private void limpiarCampos() {

        idPacienteTextField.setText("");
        nombreVacunaTextField.setText("");
        fechaTextField.setText("");
    }

}
