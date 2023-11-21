package dev.davidsalomon.vetpet.view.windows;

import dev.davidsalomon.vetpet.controller.CobroController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.view.panels.AgregarCobroPanel;
import dev.davidsalomon.vetpet.view.panels.MostrarCobrosPanel;
import java.awt.BorderLayout;
import javax.swing.*;

public class CobroWindow extends JFrame {

    private final PacienteController pacienteController;
    private final CobroController cobroController;
    private final JTabbedPane tabbedPane;

    public CobroWindow(PacienteController pacienteController, CobroController cobroController) {
        this.pacienteController = pacienteController;
        this.cobroController = cobroController;

        tabbedPane = new JTabbedPane();

        // Configuración básica del JFrame
        setTitle("Gestión de Cobros - VetPetClinic");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear los paneles para los diferentes menús
        JPanel agregarPanel = createAgregarPanel();
        JPanel mostrarPanel = createMostrarPanel();

        tabbedPane.addTab("Agregar cobro", agregarPanel);
        tabbedPane.addTab("Mostrar cobros", mostrarPanel);

        // Agregar el JTabbedPane al JFrame
        add(tabbedPane);
        setLocationRelativeTo(null);
        // Hacer visible el JFrame
        setVisible(true);
    }

    private JPanel createAgregarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(800, 600);

        // Crear instancia de AgregarPacientePanel
        AgregarCobroPanel agregarCobroPanel = new AgregarCobroPanel(pacienteController, cobroController);

        // Agregar AgregarPacientePanel al panel principal
        panel.add(agregarCobroPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createMostrarPanel() {
        // Crear un nuevo MostrarCitasPanel pasándole el controlador
        MostrarCobrosPanel mostrarCobrosPanel = new MostrarCobrosPanel(pacienteController, cobroController);

        // Crear un panel contenedor
        JPanel panel = new JPanel(new BorderLayout());

        // Agregar el MostrarPacientesPanel al panel contenedor
        panel.add(mostrarCobrosPanel, BorderLayout.CENTER);

        return panel;
    }
}
