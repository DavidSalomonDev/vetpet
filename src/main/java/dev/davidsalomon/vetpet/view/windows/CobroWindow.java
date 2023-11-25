package dev.davidsalomon.vetpet.view.windows;

import dev.davidsalomon.vetpet.controller.CobroController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.view.panels.AgregarCobroPanel;
import dev.davidsalomon.vetpet.view.panels.MostrarCobrosPanel;
import java.awt.BorderLayout;
import javax.swing.*;

/**
 * Window for managing payments (Cobros) in VetPetClinic.
 *
 * <p>
 * This window includes tabs for adding and displaying payments.</p>
 *
 * @author davidsalomon
 * @version 1.0
 */
public class CobroWindow extends JFrame {

    private final PacienteController pacienteController;
    private final CobroController cobroController;
    private final JTabbedPane tabbedPane;

    /**
     * Constructor for the payment management window.
     *
     * @param pacienteController Patient controller.
     * @param cobroController Payment controller.
     */
    public CobroWindow(PacienteController pacienteController, CobroController cobroController) {
        this.pacienteController = pacienteController;
        this.cobroController = cobroController;

        tabbedPane = new JTabbedPane();

        // Basic JFrame configuration
        setTitle("Gestión de Cobros - VetPetClinic");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create panels for different menus
        JPanel agregarPanel = createAgregarPanel();
        JPanel mostrarPanel = createMostrarPanel();

        tabbedPane.addTab("Agregar cobro", agregarPanel);
        tabbedPane.addTab("Mostrar cobros", mostrarPanel);

        // Add JTabbedPane to the JFrame
        add(tabbedPane);
        setLocationRelativeTo(null);
        // Make the JFrame visible
        setVisible(true);
    }

    private JPanel createAgregarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(800, 600);

        // Create an instance of AgregarCobroPanel
        AgregarCobroPanel agregarCobroPanel = new AgregarCobroPanel(pacienteController, cobroController);

        // Add AgregarCobroPanel to the main panel
        panel.add(agregarCobroPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createMostrarPanel() {
        // Create a new MostrarCobrosPanel passing the controller
        MostrarCobrosPanel mostrarCobrosPanel = new MostrarCobrosPanel(pacienteController, cobroController);

        // Create a container panel
        JPanel panel = new JPanel(new BorderLayout());

        // Add MostrarCobrosPanel to the container panel
        panel.add(mostrarCobrosPanel, BorderLayout.CENTER);

        return panel;
    }
}
