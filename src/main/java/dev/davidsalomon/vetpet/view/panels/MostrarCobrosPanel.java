package dev.davidsalomon.vetpet.view.panels;

import dev.davidsalomon.vetpet.controller.CobroController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.model.Cobro;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * Panel for displaying a list of billing records.
 *
 * <p>
 * This panel includes a button to update the list and a table to display
 * billing record information.</p>
 *
 * @author davidsalomon
 * @version 1.0
 */
public class MostrarCobrosPanel extends JPanel {

    private final PacienteController pacienteController;
    private final CobroController cobroController;
    private final JTable cobrosTable;
    private final DefaultTableModel tableModel;

    /**
     * Constructor for the panel to display billing records.
     *
     * @param pacienteController Patient controller.
     * @param cobroController Billing record controller.
     */
    public MostrarCobrosPanel(PacienteController pacienteController, CobroController cobroController) {
        this.pacienteController = pacienteController;
        this.cobroController = cobroController;

        setLayout(new BorderLayout());

        JButton actualizarButton = new JButton("Actualizar Lista");
        actualizarButton.addActionListener(e -> actualizarListaCobros());

        // Panel to contain the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(actualizarButton);

        // Create DefaultTableModel and JTable to display billing records
        tableModel = new DefaultTableModel();
        tableModel.addColumn("No.");
        tableModel.addColumn("Número de Identificación de Cobro");
        tableModel.addColumn("Paciente");
        tableModel.addColumn("Dueño");
        tableModel.addColumn("Fecha de Cobro");
        tableModel.addColumn("Monto");
        tableModel.addColumn("Descripción");

        cobrosTable = new JTable(tableModel);

        cobrosTable.setAutoCreateRowSorter(true);

        cobrosTable.getColumnModel().getColumn(0).setPreferredWidth(80); // Preferred width for the row number column
        cobrosTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(cobrosTable);
        cobrosTable.setRowHeight(50);
        // Get and display billing record information
        actualizarListaCobros();

        add(buttonPanel, BorderLayout.NORTH);
        // Add JTable to the panel
        add(scrollPane, BorderLayout.CENTER);

        autoAjustarColumnas(cobrosTable);
    }

    /**
     * Update the list of billing records in the table.
     */
    public void actualizarListaCobros() {
        // Get and display billing record information
        List<Cobro> cobros = cobroController.getCobros();

        // Clear the table before adding new data
        tableModel.setRowCount(0);

        // Add each billing record as a row in the table
        int rowNum = 1;
        for (Cobro cobro : cobros) {
            Object[] rowData = {
                rowNum++,
                cobro.getUniqueId(),
                cobro.getPaciente(pacienteController).getNombre(),
                cobro.getPaciente(pacienteController).getDueno(),
                cobro.getFechaCobro(),
                cobro.getMonto(),
                cobro.getDescripcion()
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
