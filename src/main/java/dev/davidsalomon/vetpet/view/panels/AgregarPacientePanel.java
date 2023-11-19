package dev.davidsalomon.vetpet.view.panels;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.model.Paciente;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class AgregarPacientePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final PacienteController pacienteController;
    private JTextField nombreTextField, duenoTextField, edadTextField, categoriaTextField,
            razaTextField, sexoTextField, alturaTextField, pesoTextField, pelajeTextField, fechaNacimientoTextField;

    public AgregarPacientePanel(PacienteController pacienteController) {
        this.pacienteController = pacienteController;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campos de texto para ingresar los datos del nuevo paciente
        addLabelAndTextField("Nombre del Paciente:", gbc);
        addLabelAndTextField("Nombre del Dueño:", gbc);
        addLabelAndTextField("Edad del Paciente:", gbc);
        addLabelAndTextField("Categoría del Paciente:", gbc);
        addLabelAndTextField("Raza del Paciente:", gbc);
        addLabelAndTextField("Sexo del Paciente:", gbc);
        addLabelAndTextField("Altura del Paciente:", gbc);
        addLabelAndTextField("Peso del Paciente:", gbc);
        addLabelAndTextField("Pelaje del Paciente:", gbc);
        addLabelAndTextField("Fecha de Nacimiento del Paciente:", gbc);

        JButton agregarPacienteButton = new JButton("Agregar Paciente");
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        agregarPacienteButton.addActionListener((ActionEvent e) -> {
            agregarNuevoPaciente();
        });
        add(agregarPacienteButton, gbc);

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

        switch (labelText) {
            case "Nombre del Paciente:" ->
                nombreTextField = textField;
            case "Nombre del Dueño:" ->
                duenoTextField = textField;
            case "Edad del Paciente:" ->
                edadTextField = textField;
            case "Categoría del Paciente:" ->
                categoriaTextField = textField;
            case "Raza del Paciente:" ->
                razaTextField = textField;
            case "Sexo del Paciente:" ->
                sexoTextField = textField;
            case "Altura del Paciente:" ->
                alturaTextField = textField;
            case "Peso del Paciente:" ->
                pesoTextField = textField;
            case "Pelaje del Paciente:" ->
                pelajeTextField = textField;
            case "Fecha de Nacimiento del Paciente:" ->
                fechaNacimientoTextField = textField;
            default -> {
            }
        }

        gbc.gridy++;
    }

    private void agregarNuevoPaciente() {
        try {
            String nombre = nombreTextField.getText();
            String dueno = duenoTextField.getText();
            int edad = Integer.parseInt(edadTextField.getText());
            String categoria = categoriaTextField.getText();
            String raza = razaTextField.getText();
            String sexo = sexoTextField.getText();
            double altura = Double.parseDouble(alturaTextField.getText());
            double peso = Double.parseDouble(pesoTextField.getText());
            String pelaje = pelajeTextField.getText();
            String fechaNacimiento = fechaNacimientoTextField.getText();

            // Crear un nuevo paciente
            Paciente nuevoPaciente = new Paciente(nombre, dueno, edad, categoria, raza, sexo, altura, peso, pelaje, fechaNacimiento);

            // Agregar el paciente al controlador
            pacienteController.agregarPaciente(nuevoPaciente);

            pacienteController.getPacientes();

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Paciente agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar los campos de texto después de agregar el paciente
            limpiarCampos();
        } catch (NumberFormatException e) {
            // En caso de error de formato, mostrar cuadro de diálogo
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos para Edad, Altura, Peso, y asegúrese de usar el formato correcto.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        nombreTextField.setText("");
        duenoTextField.setText("");
        edadTextField.setText("");
        categoriaTextField.setText("");
        razaTextField.setText("");
        sexoTextField.setText("");
        pesoTextField.setText("");
        alturaTextField.setText("");
        pelajeTextField.setText("");
        fechaNacimientoTextField.setText("");

    }
}
