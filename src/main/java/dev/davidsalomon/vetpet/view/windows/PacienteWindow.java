package dev.davidsalomon.vetpet.view.windows;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.view.panels.AgregarPacientePanel;
import dev.davidsalomon.vetpet.view.panels.EditarPacientePanel;
import dev.davidsalomon.vetpet.view.panels.EliminarPacientePanel;
import dev.davidsalomon.vetpet.view.panels.MostrarPacientesPanel;
import java.awt.*;
import javax.swing.*;

public class PacienteWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JTabbedPane tabbedPane;
    private final PacienteController pacienteController;

    public PacienteWindow(PacienteController pacienteController) {
        this.pacienteController = pacienteController;
        tabbedPane = new JTabbedPane();

        setTitle("Gestión de Pacientes - VetPet Clinic");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear los paneles para los diferentes menús
        JPanel agregarPanel = createAgregarPanel();
        JPanel mostrarPanel = createMostrarPanel();
        JPanel editarPanel = createEditarPanel();
        JPanel borrarPanel = createBorrarPanel();

        // Agregar los paneles al JTabbedPane con nombres descriptivos
        tabbedPane.addTab("Agregar Pacientes", agregarPanel);
        tabbedPane.addTab("Mostrar Pacientes", mostrarPanel);
        tabbedPane.addTab("Editar Pacientes", editarPanel);
        tabbedPane.addTab("Dar de baja a Pacientes", borrarPanel);

        // Agregar el JTabbedPane a la ventana
        add(tabbedPane);

        // Hacer visible la ventana
        setVisible(true);

        //JPanel panelPacientes = new JPanel(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createAgregarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(800, 600);

        // Crear instancia de AgregarPacientePanel
        AgregarPacientePanel agregarPacientePanel = new AgregarPacientePanel(pacienteController);

        // Agregar AgregarPacientePanel al panel principal
        panel.add(agregarPacientePanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createMostrarPanel() {
        // Crear un nuevo MostrarPacientesPanel pasándole el controlador
        MostrarPacientesPanel mostrarPacientesPanel = new MostrarPacientesPanel(pacienteController);

        // Crear un panel contenedor
        JPanel panel = new JPanel(new BorderLayout());

        // Agregar el MostrarPacientesPanel al panel contenedor
        panel.add(mostrarPacientesPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createEditarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(800, 600);

        // Crear instancia de AgregarPacientePanel
        EditarPacientePanel editarPacientePanel = new EditarPacientePanel(pacienteController);

        // Agregar AgregarPacientePanel al panel principal
        panel.add(editarPacientePanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createBorrarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(800, 600);

        // Crear instancia de AgregarPacientePanel
        EliminarPacientePanel eliminarPacientePanel = new EliminarPacientePanel(pacienteController);

        // Agregar AgregarPacientePanel al panel principal
        panel.add(eliminarPacientePanel, BorderLayout.CENTER);

        return panel;
    }

}
