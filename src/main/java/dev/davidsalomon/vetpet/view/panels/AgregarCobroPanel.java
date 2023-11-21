package dev.davidsalomon.vetpet.view.panels;

import dev.davidsalomon.vetpet.controller.CobroController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.model.Cobro;
import dev.davidsalomon.vetpet.model.Paciente;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class AgregarCobroPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final PacienteController pacienteController;
    private final CobroController cobroController;

    private JTextField idPacienteTextField, fechaTextField, montoTextField, descripcionTextField;
    private final JTextArea infoTextArea;

    public AgregarCobroPanel(PacienteController pacienteController, CobroController cobroController) {
        this.pacienteController = pacienteController;
        this.cobroController = cobroController;

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
        addLabelAndTextField("Fecha de cobro:", gbc);
        addLabelAndTextField("Monto:", gbc);
        addLabelAndTextField("Descripción:", gbc);

        JButton aplicarVacunaButton = new JButton("Realizar Cobro");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        aplicarVacunaButton.addActionListener((ActionEvent e) -> {
            agregarNuevoCobro();
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
            case "Fecha de cobro:" ->
                fechaTextField = textField;
            case "Monto:" ->
                montoTextField = textField;
            case "Descripción:" ->
                descripcionTextField = textField;
            default -> {
            }

        }
        gbc.gridy++;
    }

    private void agregarNuevoCobro() {
        try {
            String idPaciente = idPacienteTextField.getText();
            String monto = montoTextField.getText();
            String fecha = fechaTextField.getText();
            String descripcion = descripcionTextField.getText();

            Cobro nuevoCobro = new Cobro(idPaciente, fecha, Double.parseDouble(monto), descripcion);

            // Agregar el paciente al controlador
            cobroController.agregarCobro(nuevoCobro);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Cobro aplicado exitósamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

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
        descripcionTextField.setText("");
        fechaTextField.setText("");
        montoTextField.setText("");
        infoTextArea.setText("");
    }
}
