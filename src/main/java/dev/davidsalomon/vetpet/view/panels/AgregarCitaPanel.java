package dev.davidsalomon.vetpet.view.panels;

import dev.davidsalomon.vetpet.controller.CitaController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.model.Cita;
import dev.davidsalomon.vetpet.model.Paciente;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class AgregarCitaPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final CitaController citaController;
    private final PacienteController pacienteController;

    private JTextField idPacienteTextField, diaTextField, horaTextField, motivoTextField;
    private final JTextArea infoTextArea;

    public AgregarCitaPanel(PacienteController pacienteController, CitaController citaController) {
        this.pacienteController = pacienteController;
        this.citaController = citaController;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campos de texto para ingresar los datos de la nueva cita
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
        addLabelAndTextField("Día de la Cita:", gbc);
        addLabelAndTextField("Hora de la Cita:", gbc);
        addLabelAndTextField("Motivo de la Cita:", gbc);

        JButton agregarCitaButton = new JButton("Agendar Cita");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        agregarCitaButton.addActionListener((ActionEvent e) -> {
            agregarNuevaCita();
        });
        add(agregarCitaButton, gbc);

        // Centrar la ventana
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setLocation(x, y);

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
            case "Día de la Cita:" ->
                diaTextField = textField;
            case "Hora de la Cita:" ->
                horaTextField = textField;
            case "Motivo de la Cita:" ->
                motivoTextField = textField;
            default -> {
            }

        }
        gbc.gridy++;
    }

    private void agregarNuevaCita() {
        try {
            String idPaciente = idPacienteTextField.getText();
            String dia = diaTextField.getText();
            String hora = horaTextField.getText();
            String motivo = motivoTextField.getText();

            // Verificar si ya hay dos citas en el mismo día
            if (citaController.existeDosCitasEnMismoDia(idPaciente, dia)) {
                JOptionPane.showMessageDialog(this, "No se puede agregar una tercera cita en el mismo día.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si la condición no se cumple
            }

            Cita nuevaCita = new Cita(idPaciente, dia, hora, motivo);

            // Agregar el paciente al controlador
            citaController.agregarCita(nuevaCita);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Cita agregada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

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
        diaTextField.setText("");
        horaTextField.setText("");
        motivoTextField.setText("");
        infoTextArea.setText("");

    }

}
