package dev.davidsalomon.vetpet.view.windows;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.controller.VacunaController;
import dev.davidsalomon.vetpet.view.panels.AgregarVacunaPanel;
import dev.davidsalomon.vetpet.view.panels.MostrarVacunasPanel;
import java.awt.BorderLayout;
import javax.swing.*;

/**
 * Window for managing vaccines in VetPet Clinic.
 *
 * <p>
 * This window provides tabs for applying vaccines and displaying vaccine
 * information.</p>
 *
 * @author davidsalomon
 * @version 1.0
 */
public class VacunaWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JTabbedPane tabbedPane;
    private final VacunaController vacunaController;
    private final PacienteController pacienteController;

    /**
     * Constructor for the vaccine management window.
     *
     * @param pacienteController The patient controller for managing
     * patient-related actions.
     * @param vacunaController The vaccine controller for managing
     * vaccine-related actions.
     */
    public VacunaWindow(PacienteController pacienteController, VacunaController vacunaController) {
        this.pacienteController = pacienteController;
        this.vacunaController = vacunaController;
        tabbedPane = new JTabbedPane();

        // Basic JFrame configuration
        setTitle("Gestión de Vacunas - VetPetClinic");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create panels for different menus
        JPanel agregarPanel = createAgregarPanel();
        JPanel mostrarPanel = createMostrarPanel();

        // Add tabs to the JTabbedPane with descriptive names
        tabbedPane.addTab("Aplicar Vacuna", agregarPanel);
        tabbedPane.addTab("Mostrar vacunas", mostrarPanel);

        // Add the JTabbedPane to the JFrame
        add(tabbedPane);
        setLocationRelativeTo(null);
        // Make the JFrame visible
        setVisible(true);
    }

    private JPanel createAgregarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(800, 600);

        // Create an instance of AgregarVacunaPanel
        AgregarVacunaPanel agregarVacunaPanel = new AgregarVacunaPanel(pacienteController, vacunaController);

        // Add AgregarVacunaPanel to the main panel
        panel.add(agregarVacunaPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createMostrarPanel() {
        // Create a new MostrarVacunasPanel passing the controllers
        MostrarVacunasPanel mostrarVacunasPanel = new MostrarVacunasPanel(pacienteController, vacunaController);

        // Create a container panel
        JPanel panel = new JPanel(new BorderLayout());

        // Add the MostrarVacunasPanel to the container panel
        panel.add(mostrarVacunasPanel, BorderLayout.CENTER);

        return panel;
    }
}
