package dev.davidsalomon.vetpet.view.panels;

import dev.davidsalomon.vetpet.controller.CobroController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.model.Cobro;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

public class MostrarCobrosPanel extends JPanel {

    private final PacienteController pacienteController;
    private final CobroController cobroController;
    private final JTable cobrosTable;
    private final DefaultTableModel tableModel;

    public MostrarCobrosPanel(PacienteController pacienteController, CobroController cobroController) {
        this.pacienteController = pacienteController;
        this.cobroController = cobroController;

        setLayout(new BorderLayout());

        JButton actualizarButton = new JButton("Actualizar Lista");
        actualizarButton.addActionListener(e -> actualizarListaCobros());

        // Panel para contener el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(actualizarButton);

        // Crear DefaultTableModel y JTable para mostrar pacientes
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

        cobrosTable.getColumnModel().getColumn(0).setPreferredWidth(80); // Ancho preferido para la columna del número de fila
        cobrosTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(cobrosTable);
        cobrosTable.setRowHeight(50);
        // Obtener y mostrar información de pacientes
        actualizarListaCobros();

        add(buttonPanel, BorderLayout.NORTH);
        // Agregar JTable al panel
        add(scrollPane, BorderLayout.CENTER);

        autoAjustarColumnas(cobrosTable);
    }

    public void actualizarListaCobros() {
        // Obtener y mostrar información de pacientes
        List<Cobro> cobros = cobroController.getCobros();

        // Limpiar la tabla antes de agregar nuevos datos
        tableModel.setRowCount(0);

        // Agregar cada paciente como una fila en la tabla
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

    // Método para autoajustar las columnas de la JTable
    private void autoAjustarColumnas(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50; // Ancho mínimo
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

}
