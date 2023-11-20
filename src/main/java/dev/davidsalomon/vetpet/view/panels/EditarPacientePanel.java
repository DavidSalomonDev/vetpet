package dev.davidsalomon.vetpet.view.panels;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.model.Paciente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class EditarPacientePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final PacienteController pacienteController;

    private JTextField idTextField, nombreTextField, duenoTextField, edadTextField, categoriaTextField,
            razaTextField, sexoTextField, alturaTextField, pesoTextField, pelajeTextField;

    public EditarPacientePanel(PacienteController pacienteController) {
        this.pacienteController = pacienteController;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campo de texto para ingresar el ID del paciente a editar
        JLabel idLabel = new JLabel("ID del Paciente a Editar:");
        add(idLabel, gbc);

        idTextField = new JTextField();
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(idTextField, gbc);

        JButton cargarPacienteButton = new JButton("Cargar Paciente");
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        cargarPacienteButton.addActionListener((ActionEvent e) -> {
            cargarPaciente();
        });
        add(cargarPacienteButton, gbc);

        gbc.gridy++;

        // Campos de texto para mostrar los datos del paciente
        addLabelAndTextField("Nombre del Paciente:", gbc);
        addLabelAndTextField("Nombre del Dueño:", gbc);
        addLabelAndTextField("Edad del Paciente:", gbc);
        addLabelAndTextField("Categoría del Paciente:", gbc);
        addLabelAndTextField("Raza del Paciente:", gbc);
        addLabelAndTextField("Sexo del Paciente:", gbc);
        addLabelAndTextField("Altura del Paciente:", gbc);
        addLabelAndTextField("Peso del Paciente:", gbc);
        addLabelAndTextField("Pelaje del Paciente:", gbc);

        JButton editarPacienteButton = new JButton("Editar Paciente");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        editarPacienteButton.addActionListener((ActionEvent e) -> {
            try {
                editarPaciente();
            } catch (ParseException ex) {
                Logger.getLogger(EditarPacientePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(editarPacienteButton, gbc);

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
            default -> {
            }
        }

        gbc.gridy++;
    }

    private void cargarPaciente() {
        String idPaciente = idTextField.getText();

        // Verificar que el ID no esté vacío
        if (idPaciente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ID del paciente a cargar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar el paciente con el ID proporcionado
        Paciente pacienteExistente = buscarPacientePorID(idPaciente);

        if (pacienteExistente == null) {
            JOptionPane.showMessageDialog(this, "No se encontró un paciente con el ID proporcionado", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Mostrar los datos del paciente en los campos de texto
            nombreTextField.setText(pacienteExistente.getNombre());
            duenoTextField.setText(pacienteExistente.getDueno());
            edadTextField.setText(String.valueOf(pacienteExistente.getEdad()));
            categoriaTextField.setText(pacienteExistente.getCategoria());
            razaTextField.setText(pacienteExistente.getRaza());
            sexoTextField.setText(pacienteExistente.getSexo());
            alturaTextField.setText(String.valueOf(pacienteExistente.getAltura()));
            pesoTextField.setText(String.valueOf(pacienteExistente.getPeso()));
            pelajeTextField.setText(pacienteExistente.getPelaje());
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

    private void editarPaciente() throws ParseException {
        String idPaciente = idTextField.getText();

        // Verificar que el ID no esté vacío
        if (idPaciente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ID del paciente a editar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar el paciente con el ID proporcionado
        Paciente pacienteExistente = buscarPacientePorID(idPaciente);
        Paciente nuevaDataPaciente;

        if (pacienteExistente == null) {
            JOptionPane.showMessageDialog(this, "No se encontró un paciente con el ID proporcionado", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            nuevaDataPaciente = new Paciente(
                    nombreTextField.getText(),
                    duenoTextField.getText(),
                    Integer.parseInt(edadTextField.getText()),
                    categoriaTextField.getText(),
                    razaTextField.getText(),
                    sexoTextField.getText(),
                    Double.parseDouble(alturaTextField.getText()),
                    Double.parseDouble(pesoTextField.getText()),
                    pelajeTextField.getText(),
                    pacienteExistente.getFechaNacimiento());

            pacienteController.editarPaciente(idPaciente, nuevaDataPaciente);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Paciente editado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar los campos de texto después de editar el paciente
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        idTextField.setText("");
        nombreTextField.setText("");
        duenoTextField.setText("");
        edadTextField.setText("");
        categoriaTextField.setText("");
        razaTextField.setText("");
        sexoTextField.setText("");
        pesoTextField.setText("");
        alturaTextField.setText("");
        pelajeTextField.setText("");

    }
}
