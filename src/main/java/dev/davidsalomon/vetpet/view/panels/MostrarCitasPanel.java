package dev.davidsalomon.vetpet.view.panels;

import dev.davidsalomon.vetpet.controller.CitaController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.model.Cita;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

public class MostrarCitasPanel extends JPanel {

    private final CitaController citaController;
    private final PacienteController pacienteController;
    private final JTable citasTable;
    private final DefaultTableModel tableModel;

    public MostrarCitasPanel(PacienteController pacienteController, CitaController citaController) {
        this.pacienteController = pacienteController;
        this.citaController = citaController;

        setLayout(new BorderLayout());

        JButton actualizarButton = new JButton("Actualizar Lista");
        actualizarButton.addActionListener(e -> actualizarListaCitas());

        // Panel para contener el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(actualizarButton);

        // Crear DefaultTableModel y JTable para mostrar pacientes
        tableModel = new DefaultTableModel();
        tableModel.addColumn("No.");
        tableModel.addColumn("Número de Identificación de Cita");
        tableModel.addColumn("Paciente");
        tableModel.addColumn("Dueño");
        tableModel.addColumn("Fecha y Hora");
        tableModel.addColumn("Motivo");

        citasTable = new JTable(tableModel);
        citasTable.setAutoCreateRowSorter(true);
        citasTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Deshabilitar el ajuste automático de tamaño de columna
        citasTable.getColumnModel().getColumn(0).setPreferredWidth(80); // Ancho preferido para la columna del número de fila
        citasTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(citasTable);

        citasTable.setRowHeight(50);
        // Obtener y mostrar información de pacientes
        actualizarListaCitas();

        // Agregar JTable y botón al panel
        add(buttonPanel, BorderLayout.NORTH);
        // Agregar JTable al panel
        add(scrollPane, BorderLayout.CENTER);

        autoAjustarColumnas(citasTable);

    }

    public void actualizarListaCitas() {
        // Obtener y mostrar información de pacientes
        List<Cita> citas = citaController.getCitas();

        // Limpiar la tabla antes de agregar nuevos datos
        tableModel.setRowCount(0);

        // Agregar cada paciente como una fila en la tabla
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
