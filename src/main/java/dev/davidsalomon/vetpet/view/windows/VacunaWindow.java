package dev.davidsalomon.vetpet.view.windows;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.controller.VacunaController;
import dev.davidsalomon.vetpet.view.panels.AgregarVacunaPanel;
import dev.davidsalomon.vetpet.view.panels.MostrarVacunasPanel;
import java.awt.BorderLayout;
import javax.swing.*;

public class VacunaWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JTabbedPane tabbedPane;
    private final VacunaController vacunaController;
    private final PacienteController pacienteController;

    public VacunaWindow(PacienteController pacienteController, VacunaController vacunaController) {
        this.pacienteController = pacienteController;
        this.vacunaController = vacunaController;
        tabbedPane = new JTabbedPane();

        // Configuración básica del JFrame
        setTitle("Gestión de Vacunas - VetPetClinic");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear los paneles para los diferentes menús
        JPanel agregarPanel = createAgregarPanel();
        JPanel mostrarPanel = createMostrarPanel();

        tabbedPane.addTab("Aplicar Vacuna", agregarPanel);
        tabbedPane.addTab("Mostrar vacunas", mostrarPanel);

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
        AgregarVacunaPanel agregarVacunaPanel = new AgregarVacunaPanel(pacienteController, vacunaController);

        // Agregar AgregarPacientePanel al panel principal
        panel.add(agregarVacunaPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createMostrarPanel() {
        // Crear un nuevo MostrarCitasPanel pasándole el controlador
        MostrarVacunasPanel mostrarVacunasPanel = new MostrarVacunasPanel(pacienteController, vacunaController);

        // Crear un panel contenedor
        JPanel panel = new JPanel(new BorderLayout());

        // Agregar el MostrarPacientesPanel al panel contenedor
        panel.add(mostrarVacunasPanel, BorderLayout.CENTER);

        return panel;
    }

}
