package dev.davidsalomon.vetpet.view.windows;

import dev.davidsalomon.vetpet.controller.CitaController;
import dev.davidsalomon.vetpet.controller.CobroController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.controller.VacunaController;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * Main window for VetPet Clinic management application.
 *
 * <p>
 * This window provides a menu for managing patients, appointments,
 * vaccinations, patient records, and payments.</p>
 *
 * @author davidsalomon
 * @version 1.0
 */
public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for the main window.
     */
    public MainWindow() {
        setTitle("Menú Principal - VetPet Clinic");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel descriptionLabel = new JLabel("<html><div style='text-align: center;'>Bienvenido a la aplicación de gestión de VetPet Clinic.</div></html>");
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionLabel.setVerticalAlignment(SwingConstants.CENTER);

        JButton patientsButton = new JButton("Gestión de Pacientes");
        patientsButton.addActionListener((ActionEvent e) -> {
            openPatientsWindow();
        });

        JButton appointmentsButton = new JButton("Gestión de Citas");
        appointmentsButton.addActionListener((ActionEvent e) -> {
            openAppointmentsWindow();
        });

        JButton vaccinesButton = new JButton("Gestión de Vacunas");
        vaccinesButton.addActionListener((ActionEvent e) -> {
            openVaccinesWindow();
        });

        JButton recordsButton = new JButton("Gestión de Expedientes");
        recordsButton.addActionListener((ActionEvent e) -> {
            openRecordsWindow();
        });

        JButton paymentsButton = new JButton("Gestión de Cobros");
        paymentsButton.addActionListener((ActionEvent e) -> {
            openPaymentsWindow();
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(descriptionLabel, gbc);

        gbc.gridy = 1;
        mainPanel.add(patientsButton, gbc);

        gbc.gridy = 2;
        mainPanel.add(appointmentsButton, gbc);

        gbc.gridy = 3;
        mainPanel.add(vaccinesButton, gbc);

        gbc.gridy = 4;
        mainPanel.add(recordsButton, gbc);

        gbc.gridy = 5;
        mainPanel.add(paymentsButton, gbc);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void openPatientsWindow() {
        new PacienteWindow(new PacienteController());
    }

    private void openAppointmentsWindow() {
        new CitaWindow(new PacienteController(), new CitaController());
    }

    private void openVaccinesWindow() {
        new VacunaWindow(new PacienteController(), new VacunaController());
    }

    private void openRecordsWindow() {
        new ExpedienteWindow(new PacienteController(), new CitaController(), new VacunaController(), new CobroController());
    }

    private void openPaymentsWindow() {
        new CobroWindow(new PacienteController(), new CobroController());
    }
}
