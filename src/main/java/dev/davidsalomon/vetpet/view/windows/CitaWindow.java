package dev.davidsalomon.vetpet.view.windows;

import dev.davidsalomon.vetpet.controller.CitaController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.view.panels.AgregarCitaPanel;
import dev.davidsalomon.vetpet.view.panels.MostrarCitasPanel;
import java.awt.BorderLayout;
import javax.swing.*;

public class CitaWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JTabbedPane tabbedPane;
    private final CitaController citaController;
    private final PacienteController pacienteController;

    public CitaWindow(PacienteController pacienteController, CitaController citaController) {
        this.pacienteController = pacienteController;
        this.citaController = citaController;
        tabbedPane = new JTabbedPane();
        // Configuración básica del JFrame
        setTitle("Gestión de Citas - VetPetClinic");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear los paneles para los diferentes menús
        JPanel agregarPanel = createAgregarPanel();

        JPanel mostrarPanel = createMostrarPanel();
        /*
        JPanel editarPanel = createEditarPanel();
        JPanel borrarPanel = createBorrarPanel();
         */

        tabbedPane.addTab("Agendar cita", agregarPanel);
        tabbedPane.addTab("Mostrar citas", mostrarPanel);

        // Agregar el JTabbedPane al JFrame
        add(tabbedPane);

        // Hacer visible el JFrame
        setVisible(true);
    }

    private JPanel createAgregarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(800, 600);

        // Crear instancia de AgregarPacientePanel
        AgregarCitaPanel agregarPacientePanel = new AgregarCitaPanel(pacienteController, citaController);

        // Agregar AgregarPacientePanel al panel principal
        panel.add(agregarPacientePanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createMostrarPanel() {
        // Crear un nuevo MostrarCitasPanel pasándole el controlador
        MostrarCitasPanel mostrarCitasPanel = new MostrarCitasPanel(citaController, pacienteController);

        // Crear un panel contenedor
        JPanel panel = new JPanel(new BorderLayout());

        // Agregar el MostrarPacientesPanel al panel contenedor
        panel.add(mostrarCitasPanel, BorderLayout.CENTER);

        return panel;
    }

}
