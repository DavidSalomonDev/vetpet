package dev.davidsalomon.vetpet.view;

import dev.davidsalomon.vetpet.controller.PacienteController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        JPanel panelPacientes = new JPanel(new BorderLayout());

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
        // Agregar componentes y lógica para el panel de dar de baja pacientes
        JButton borrarButton = new JButton("Dar de Baja");
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para dar de baja paciente utilizando PacienteController
                // Ejemplo: pacienteController.darDeBajaPaciente(...);
            }
        });
        panel.add(borrarButton, BorderLayout.CENTER);
        return panel;
    }

}
