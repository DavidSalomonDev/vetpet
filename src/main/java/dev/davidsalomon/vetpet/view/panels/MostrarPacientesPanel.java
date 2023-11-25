package dev.davidsalomon.vetpet.view.panels;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.model.Paciente;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * Panel for displaying a list of patients.
 *
 * <p>
 * This panel includes a button to update the list and a table to display
 * patient information.</p>
 *
 * @author davidsalomon
 * @version 1.0
 */
public final class MostrarPacientesPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private PacienteController pacienteController;
    private final JTable pacientesTable;
    private final DefaultTableModel tableModel;

    /**
     * Constructor for the panel to display patients.
     *
     * @param pacienteController Patient controller.
     */
    public MostrarPacientesPanel(PacienteController pacienteController) {
        this.pacienteController = pacienteController;

        setLayout(new BorderLayout());

        JButton actualizarButton = new JButton("Actualizar Lista");
        actualizarButton.addActionListener(e -> actualizarListaPacientes());

        // Panel to contain the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(actualizarButton);

        // Create DefaultTableModel and JTable to display patients
        tableModel = new DefaultTableModel();
        tableModel.addColumn("No.");
        tableModel.addColumn("Número de Identificación");
        tableModel.addColumn("Fecha de Inscripción");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Dueño");
        tableModel.addColumn("Edad");
        tableModel.addColumn("Categoría");
        tableModel.addColumn("Raza");
        tableModel.addColumn("Sexo");
        tableModel.addColumn("Altura");
        tableModel.addColumn("Peso");
        tableModel.addColumn("Pelaje");
        tableModel.addColumn("Fecha de Nacimiento");

        pacientesTable = new JTable(tableModel);
        pacientesTable.setAutoCreateRowSorter(true);
        pacientesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Disable automatic column size adjustment
        pacientesTable.getColumnModel().getColumn(0).setPreferredWidth(80); // Preferred width for the row number column
        pacientesTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(pacientesTable);

        pacientesTable.setRowHeight(50);
        // Get and display patient information
        actualizarListaPacientes();

        // Add JTable and button to the panel
        add(buttonPanel, BorderLayout.NORTH);
        // Add JTable to the panel
        add(scrollPane, BorderLayout.CENTER);

        autoAjustarColumnas(pacientesTable);
    }

    /**
     * Update the list of patients in the table.
     */
    public void actualizarListaPacientes() {
        // Get and display patient information
        List<Paciente> pacientes = pacienteController.getPacientes();

        // Clear the table before adding new data
        tableModel.setRowCount(0);

        // Add each patient as a row in the table
        int rowNum = 1;
        for (Paciente paciente : pacientes) {
            Object[] rowData = {
                rowNum++,
                paciente.getUniqueId(),
                paciente.getFechaInscripcion(),
                paciente.getNombre(),
                paciente.getDueno(),
                paciente.getEdad(),
                paciente.getCategoria(),
                paciente.getRaza(),
                paciente.getSexo(),
                paciente.getAltura(),
                paciente.getPeso(),
                paciente.getPelaje(),
                paciente.getFechaNacimiento()
            };
            tableModel.addRow(rowData);
        }
    }

    // Method to automatically adjust the columns of the JTable
    private void autoAjustarColumnas(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 30; // Minimum width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
