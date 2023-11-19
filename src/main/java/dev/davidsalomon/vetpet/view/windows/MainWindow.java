package dev.davidsalomon.vetpet.view.windows;

import dev.davidsalomon.vetpet.controller.CitaController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    public MainWindow() {
        setTitle("Menú Principal - VetPet Clinic");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel descripcionLabel = new JLabel("<html><div style='text-align: center;'>Bienvenido a la aplicación de gestión de VetPet Clinic.</div></html>");
        descripcionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descripcionLabel.setVerticalAlignment(SwingConstants.CENTER);

        JButton pacientesButton = new JButton("Gestión de Pacientes");
        pacientesButton.addActionListener((ActionEvent e) -> {
            abrirVentanaPacientes();
        });

        JButton citasButton = new JButton("Gestión de Citas");
        citasButton.addActionListener((ActionEvent e) -> {
            abrirVentanaCitas();
        });

        JButton vacunasButton = new JButton("Gestión de Vacunas");
        vacunasButton.addActionListener((ActionEvent e) -> {
            abrirVentanaVacunas();
        });

        JButton expedientesButton = new JButton("Gestión de Expedientes");
        expedientesButton.addActionListener((ActionEvent e) -> {
            abrirVentanaExpedientes();
        });

        JButton cobrosButton = new JButton("Gestión de Cobros");
        cobrosButton.addActionListener((ActionEvent e) -> {
            abrirVentanaCobros();
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPrincipal.add(descripcionLabel, gbc);

        gbc.gridy = 1;
        panelPrincipal.add(pacientesButton, gbc);

        gbc.gridy = 2;
        panelPrincipal.add(citasButton, gbc);

        gbc.gridy = 3;
        panelPrincipal.add(vacunasButton, gbc);

        gbc.gridy = 4;
        panelPrincipal.add(expedientesButton, gbc);

        gbc.gridy = 5;
        panelPrincipal.add(cobrosButton, gbc);

        setLayout(new BorderLayout());
        add(panelPrincipal, BorderLayout.CENTER);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void abrirVentanaPacientes() {
        new PacienteWindow(new PacienteController());
    }

    private void abrirVentanaCitas() {
        new CitaWindow(new PacienteController(), new CitaController());
    }

    private void abrirVentanaVacunas() {
        // Aquí puedes implementar la lógica para abrir la ventana de gestión de vacunas
        JOptionPane.showMessageDialog(this, "Implementa la lógica para la gestión de vacunas", "Gestión de Vacunas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void abrirVentanaExpedientes() {
        // Aquí puedes implementar la lógica para abrir la ventana de gestión de expedientes
        JOptionPane.showMessageDialog(this, "Implementa la lógica para la gestión de expedientes", "Gestión de Expedientes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void abrirVentanaCobros() {
        // Aquí puedes implementar la lógica para abrir la ventana de gestión de cobros
        JOptionPane.showMessageDialog(this, "Implementa la lógica para la gestión de cobros", "Gestión de Cobros", JOptionPane.INFORMATION_MESSAGE);
    }

}
