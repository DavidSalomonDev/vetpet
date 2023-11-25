package dev.davidsalomon.vetpet.view.panels;

import dev.davidsalomon.vetpet.controller.CitaController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.model.Cita;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * Panel for displaying a list of appointments.
 *
 * <p>
 * This panel includes a button to update the list and a table to display
 * appointment information.</p>
 *
 * @author davidsalomon
 * @version 1.0
 */
public class MostrarCitasPanel extends JPanel {

    private final CitaController citaController;
    private final PacienteController pacienteController;
    private final JTable citasTable;
    private final DefaultTableModel tableModel;

    /**
     * Constructor for the panel to display appointments.
     *
     * @param pacienteController Patient controller.
     * @param citaController Appointment controller.
     */
    public MostrarCitasPanel(PacienteController pacienteController, CitaController citaController) {

        this.pacienteController = pacienteController;
        this.citaController = citaController;

        setLayout(new BorderLayout());

        JButton actualizarButton = new JButton("Actualizar Lista");
        actualizarButton.addActionListener(e -> actualizarListaCitas());

        // Panel to contain the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(actualizarButton);

        // Create DefaultTableModel and JTable to display appointments
        tableModel = new DefaultTableModel();
        tableModel.addColumn("No.");
        tableModel.addColumn("Número de Identificación de Cita");
        tableModel.addColumn("Paciente");
        tableModel.addColumn("Dueño");
        tableModel.addColumn("Fecha y Hora");
        tableModel.addColumn("Motivo");

        citasTable = new JTable(tableModel);
        citasTable.setAutoCreateRowSorter(true);
        citasTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Disable automatic column size adjustment
        citasTable.getColumnModel().getColumn(0).setPreferredWidth(80); // Preferred width for the row number column
        citasTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(citasTable);

        citasTable.setRowHeight(50);
        // Get and display appointment information
        actualizarListaCitas();

        // Add JTable and button to the panel
        add(buttonPanel, BorderLayout.NORTH);
        // Add JTable to the panel
        add(scrollPane, BorderLayout.CENTER);

        autoAjustarColumnas(citasTable);
    }

    /**
     * Update the list of appointments in the table.
     */
    public void actualizarListaCitas() {
        // Get and display appointment information
        List<Cita> citas = citaController.getCitas();

        // Clear the table before adding new data
        tableModel.setRowCount(0);

        // Add each appointment as a row in the table
        int rowNum = 1;
        for (Cita cita : citas) {
            Object[] rowData = {
                rowNum++,
                cita.getUniqueId(),
                cita.getPaciente(pacienteController).getNombre(),
                cita.getPaciente(pacienteController).getDueno(),
                cita.getFechaHora(),
                cita.getMotivo()
            };
            tableModel.addRow(rowData);
        }
    }

    // Method to automatically adjust the columns of the JTable
    private void autoAjustarColumnas(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50; // Minimum width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
