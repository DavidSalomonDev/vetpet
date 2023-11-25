package dev.davidsalomon.vetpet.view.windows;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.view.panels.AgregarPacientePanel;
import dev.davidsalomon.vetpet.view.panels.EditarPacientePanel;
import dev.davidsalomon.vetpet.view.panels.EliminarPacientePanel;
import dev.davidsalomon.vetpet.view.panels.MostrarPacientesPanel;
import java.awt.*;
import javax.swing.*;

/**
 * Window for managing patients in VetPet Clinic.
 *
 * <p>
 * This window provides tabs for adding, displaying, editing, and removing
 * patients.</p>
 *
 * @author davidsalomon
 * @version 1.0
 */
public class PacienteWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JTabbedPane tabbedPane;
    private final PacienteController pacienteController;

    /**
     * Constructor for the patient management window.
     *
     * @param pacienteController The patient controller to manage
     * patient-related actions.
     */
    public PacienteWindow(PacienteController pacienteController) {
        this.pacienteController = pacienteController;
        tabbedPane = new JTabbedPane();

        setTitle("Gestión de Pacientes - VetPet Clinic");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create panels for different menus
        JPanel agregarPanel = createAgregarPanel();
        JPanel mostrarPanel = createMostrarPanel();
        JPanel editarPanel = createEditarPanel();
        JPanel borrarPanel = createBorrarPanel();

        // Add panels to the JTabbedPane with descriptive names
        tabbedPane.addTab("Agregar Pacientes", agregarPanel);
        tabbedPane.addTab("Mostrar Pacientes", mostrarPanel);
        tabbedPane.addTab("Editar Pacientes", editarPanel);
        tabbedPane.addTab("Dar de baja a Pacientes", borrarPanel);

        // Add the JTabbedPane to the window
        add(tabbedPane);

        // Make the window visible
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createAgregarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(800, 600);

        // Create an instance of AgregarPacientePanel
        AgregarPacientePanel agregarPacientePanel = new AgregarPacientePanel(pacienteController);

        // Add AgregarPacientePanel to the main panel
        panel.add(agregarPacientePanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createMostrarPanel() {
        // Create a new MostrarPacientesPanel passing the controller
        MostrarPacientesPanel mostrarPacientesPanel = new MostrarPacientesPanel(pacienteController);

        // Create a container panel
        JPanel panel = new JPanel(new BorderLayout());

        // Add the MostrarPacientesPanel to the container panel
        panel.add(mostrarPacientesPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createEditarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(800, 600);

        // Create an instance of EditarPacientePanel
        EditarPacientePanel editarPacientePanel = new EditarPacientePanel(pacienteController);

        // Add EditarPacientePanel to the main panel
        panel.add(editarPacientePanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createBorrarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(800, 600);

        // Create an instance of EliminarPacientePanel
        EliminarPacientePanel eliminarPacientePanel = new EliminarPacientePanel(pacienteController);

        // Add EliminarPacientePanel to the main panel
        panel.add(eliminarPacientePanel, BorderLayout.CENTER);

        return panel;
    }
}
