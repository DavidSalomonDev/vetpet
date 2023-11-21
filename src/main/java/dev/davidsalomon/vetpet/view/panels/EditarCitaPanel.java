package dev.davidsalomon.vetpet.view.panels;

import dev.davidsalomon.vetpet.controller.CitaController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.model.Cita;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class EditarCitaPanel extends JPanel {

    private CitaController citaController;
    private PacienteController pacienteController;
    private JTextArea nombrePacienteTextArea, duenoTextArea;
    private JTextField idTextField, diaTextField, horaTextField, motivoTextField;

    public EditarCitaPanel(PacienteController pacienteController, CitaController citaController) {
        this.pacienteController = pacienteController;
        this.citaController = citaController;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campo de texto para ingresar el ID de la cita a editar
        JLabel idLabel = new JLabel("Id de la cita:");
        add(idLabel, gbc);

        idTextField = new JTextField();
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(idTextField, gbc);

        JButton cargarCitaButton = new JButton("Cargar Cita");
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        cargarCitaButton.addActionListener((ActionEvent e) -> {
            cargarCita();
        });
        add(cargarCitaButton, gbc);

        gbc.gridy++;

        // Campos de texto para mostrar los datos de la cita
        addLabelAndTextField("Nombre del paciente:", gbc);
        addLabelAndTextField("Nombre del dueño:", gbc);
        addLabelAndTextField("Dia de la cita:", gbc);
        addLabelAndTextField("Hora de la cita:", gbc);
        addLabelAndTextField("Motivo de la cita:", gbc);

        JButton editarCitaButton = new JButton("Editar Cita");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        editarCitaButton.addActionListener((ActionEvent e) -> {
            try {
                editarCita();
            } catch (ParseException ex) {
                Logger.getLogger(EditarCitaPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(editarCitaButton, gbc);

        setVisible(true);

    }

    private void addLabelAndTextField(String labelText, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText);
        gbc.gridx = 0;
        add(label, gbc);

        JTextField textField = new JTextField();
        JTextArea textArea = new JTextArea();

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        if (labelText.equals("Nombre del paciente:") || labelText.equals("Nombre del dueño:")) {
            textArea.setEditable(false); // Hacer el JTextArea no editable
            add(textArea, gbc);

            switch (labelText) {
                case "Nombre del paciente:" ->
                    nombrePacienteTextArea = textArea;
                case "Nombre del dueño:" ->
                    duenoTextArea = textArea;
            }
        } else {
            textField.setEditable(true); // Hacer el JTextField editable
            add(textField, gbc);

            switch (labelText) {
                case "Id de la cita:" ->
                    idTextField = textField;
                case "Dia de la cita:" ->
                    diaTextField = textField;
                case "Hora de la cita:" ->
                    horaTextField = textField;
                case "Motivo de la cita:" ->
                    motivoTextField = textField;
            }
        }

        gbc.gridy++;
    }

    private void cargarCita() {
        String idCita = idTextField.getText();

        // Verificar que el ID no esté vacío
        if (idCita.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ID de la cita a cargar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar el paciente con el ID proporcionado
        Cita citaExistente = buscarCitaPorID(idCita);

        if (citaExistente == null) {
            JOptionPane.showMessageDialog(this, "No se encontró un paciente con el ID proporcionado", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Mostrar los datos del paciente en los campos de texto
            nombrePacienteTextArea.setText(citaExistente.getPaciente(pacienteController).getNombre());
            duenoTextArea.setText(citaExistente.getPaciente(pacienteController).getDueno());
            diaTextField.setText(citaExistente.getDia());
            horaTextField.setText(String.valueOf(citaExistente.getHora()));
            motivoTextField.setText(citaExistente.getMotivo());
        }
    }

    private Cita buscarCitaPorID(String id) {
        for (Cita cita : citaController.getCitas()) {
            if (cita.getUniqueId().equals(id)) {
                return cita;
            }
        }
        return null;
    }

    private void editarCita() throws ParseException {
        String idCita = idTextField.getText();

        // Verificar que el ID no esté vacío
        if (idCita.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ID de la cita a editar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar la cita con el ID proporcionado
        Cita citaExistente = buscarCitaPorID(idCita);
        Cita nuevaDataCita;

        if (citaExistente == null) {
            JOptionPane.showMessageDialog(this, "No se encontró una cita con el ID proporcionado", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Validar el formato del día (yyyy-mm-dd)
            if (!validarFormatoDia(diaTextField.getText())) {
                JOptionPane.showMessageDialog(this, "Formato de día no válido. Use yyyy-mm-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si la condición no se cumple
            }

            // Validar el formato de la hora (hh:mm AM/PM)
            if (!validarFormatoHora(horaTextField.getText())) {
                JOptionPane.showMessageDialog(this, "Formato de hora no válido. Use hh:mm AM/PM.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si la condición no se cumple
            }

            nuevaDataCita = new Cita(
                    null,
                    diaTextField.getText(),
                    horaTextField.getText(),
                    motivoTextField.getText());

            // Verificar disponibilidad de la cita
            if (!citaController.validarDisponibilidadCita(citaExistente.getIdPaciente(), nuevaDataCita.getDia(), nuevaDataCita.getHora())) {
                JOptionPane.showMessageDialog(this, "Ya hay una cita programada para el mismo día y hora", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            citaController.editarCita(idCita, nuevaDataCita);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Cita editada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar los campos de texto después de editar la cita
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        idTextField.setText("");
        nombrePacienteTextArea.setText("");
        duenoTextArea.setText("");
        diaTextField.setText("");
        horaTextField.setText("");
        motivoTextField.setText("");
    }

    private boolean validarFormatoDia(String dia) {
        // Utiliza una expresión regular para validar el formato yyyy-mm-dd
        String formatoDiaRegex = "\\d{4}-\\d{2}-\\d{2}";
        return dia.matches(formatoDiaRegex);
    }

    private boolean validarFormatoHora(String hora) {
        // Utiliza una expresión regular para validar el formato hh:mm AM/PM
        String formatoHoraRegex = "(1?[0-9]):[0-5][0-9]\\s?(AM|PM|am|pm)";
        return hora.matches(formatoHoraRegex);
    }
}
