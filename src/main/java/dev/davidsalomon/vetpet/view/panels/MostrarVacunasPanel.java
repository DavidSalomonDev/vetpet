package dev.davidsalomon.vetpet.view.panels;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.controller.VacunaController;
import dev.davidsalomon.vetpet.model.Vacuna;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * Panel for displaying a list of vaccines.
 *
 * <p>
 * This panel includes a button to update the list and a table to display
 * vaccine information.</p>
 *
 * @author davidsalomon
 * @version 1.0
 */
public class MostrarVacunasPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final PacienteController pacienteController;
    private final VacunaController vacunaController;
    private final JTable vacunasTable;
    private final DefaultTableModel tableModel;

    /**
     * Constructor for the panel to display vaccines.
     *
     * @param pacienteController Patient controller.
     * @param vacunaController Vaccine controller.
     */
    public MostrarVacunasPanel(PacienteController pacienteController, VacunaController vacunaController) {
        this.pacienteController = pacienteController;
        this.vacunaController = vacunaController;

        setLayout(new BorderLayout());

        JButton actualizarButton = new JButton("Actualizar Lista");
        actualizarButton.addActionListener(e -> actualizarListaVacunas());

        // Panel to contain the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(actualizarButton);

        // Create DefaultTableModel and JTable to display vaccines
        tableModel = new DefaultTableModel();
        tableModel.addColumn("No.");
        tableModel.addColumn("Número de Identificación de Vacuna");
        tableModel.addColumn("Paciente");
        tableModel.addColumn("Dueño");
        tableModel.addColumn("Fecha de Vacuna");
        tableModel.addColumn("Nombre de Vacuna");
        tableModel.addColumn("Altura al momento");
        tableModel.addColumn("Peso al momento");
        tableModel.addColumn("Edad al momento");

        vacunasTable = new JTable(tableModel);

        vacunasTable.setAutoCreateRowSorter(true);

        vacunasTable.getColumnModel().getColumn(0).setPreferredWidth(80); // Preferred width for the row number column
        vacunasTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(vacunasTable);
        vacunasTable.setRowHeight(50);
        // Get and display vaccine information
        actualizarListaVacunas();

        add(buttonPanel, BorderLayout.NORTH);
        // Add JTable to the panel
        add(scrollPane, BorderLayout.CENTER);

        autoAjustarColumnas(vacunasTable);
    }

    /**
     * Update the list of vaccines in the table.
     */
    public void actualizarListaVacunas() {
        // Get and display vaccine information
        List<Vacuna> vacunas = vacunaController.getVacunas();

        // Clear the table before adding new data
        tableModel.setRowCount(0);

        // Add each vaccine as a row in the table
        int rowNum = 1;
        for (Vacuna vacuna : vacunas) {
            Object[] rowData = {
                rowNum++,
                vacuna.getUniqueId(),
                vacuna.getPaciente(pacienteController).getNombre(),
                vacuna.getPaciente(pacienteController).getDueno(),
                vacuna.getFechaVacuna(),
                vacuna.getNombreVacuna(),
                vacuna.getAlturaEnMomento(),
                vacuna.getPesoEnMomento(),
                vacuna.getEdadEnMomento()
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
