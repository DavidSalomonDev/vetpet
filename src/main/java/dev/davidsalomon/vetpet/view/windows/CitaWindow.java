package dev.davidsalomon.vetpet.view.windows;

import dev.davidsalomon.vetpet.controller.CitaController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.view.panels.AgregarCitaPanel;
import dev.davidsalomon.vetpet.view.panels.EditarCitaPanel;
import dev.davidsalomon.vetpet.view.panels.EliminarCitaPanel;
import dev.davidsalomon.vetpet.view.panels.MostrarCitasPanel;
import java.awt.*;
import javax.swing.*;

/**
 * Window for managing appointments in VetPetClinic.
 *
 * <p>
 * This window includes tabs for adding, displaying, editing, and declining
 * appointments.</p>
 *
 * @author davidsalomon
 * @version 1.0
 */
public class CitaWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JTabbedPane tabbedPane;
    private final CitaController citaController;
    private final PacienteController pacienteController;

    /**
     * Constructor for the appointment management window.
     *
     * @param pacienteController Patient controller.
     * @param citaController Appointment controller.
     */
    public CitaWindow(PacienteController pacienteController, CitaController citaController) {
        this.pacienteController = pacienteController;
        this.citaController = citaController;
        tabbedPane = new JTabbedPane();

        // Basic JFrame configuration
        setTitle("Gestión de Citas - VetPetClinic");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create panels for different menus
        JPanel agregarPanel = createAgregarPanel();
        JPanel mostrarPanel = createMostrarPanel();
        JPanel editarPanel = createEditarPanel();
        JPanel declinarPanel = createDeclinarPanel();

        tabbedPane.addTab("Agendar cita", agregarPanel);
        tabbedPane.addTab("Mostrar citas", mostrarPanel);
        tabbedPane.addTab("Editar citas", editarPanel);
        tabbedPane.addTab("Declinar citas", declinarPanel);

        // Add JTabbedPane to the JFrame
        add(tabbedPane);
        setLocationRelativeTo(null);
        // Make the JFrame visible
        setVisible(true);
    }

    private JPanel createAgregarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(800, 600);

        // Create an instance of AgregarCitaPanel
        AgregarCitaPanel agregarCitaPanel = new AgregarCitaPanel(pacienteController, citaController);

        // Add AgregarCitaPanel to the main panel
        panel.add(agregarCitaPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createMostrarPanel() {
        // Create a new MostrarCitasPanel passing the controller
        MostrarCitasPanel mostrarCitasPanel = new MostrarCitasPanel(pacienteController, citaController);

        // Create a container panel
        JPanel panel = new JPanel(new BorderLayout());

        // Add MostrarCitasPanel to the container panel
        panel.add(mostrarCitasPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createEditarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(800, 600);

        // Create an instance of EditarCitaPanel
        EditarCitaPanel editarCitaPanel = new EditarCitaPanel(pacienteController, citaController);

        // Add EditarCitaPanel to the main panel
        panel.add(editarCitaPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createDeclinarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(800, 600);

        // Create an instance of EliminarCitaPanel
        EliminarCitaPanel eliminarCitaPanel = new EliminarCitaPanel(pacienteController, citaController);

        // Add EliminarCitaPanel to the main panel
        panel.add(eliminarCitaPanel, BorderLayout.CENTER);

        return panel;
    }
}
