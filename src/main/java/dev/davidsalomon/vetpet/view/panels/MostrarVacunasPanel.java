package dev.davidsalomon.vetpet.view.panels;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.controller.VacunaController;
import dev.davidsalomon.vetpet.model.Vacuna;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

public class MostrarVacunasPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final PacienteController pacienteController;
    private final VacunaController vacunaController;
    private final JTable vacunasTable;
    private final DefaultTableModel tableModel;

    public MostrarVacunasPanel(PacienteController pacienteController, VacunaController vacunaController) {
        this.pacienteController = pacienteController;
        this.vacunaController = vacunaController;

        setLayout(new BorderLayout());

        JButton actualizarButton = new JButton("Actualizar Lista");
        actualizarButton.addActionListener(e -> actualizarListaVacunas());

        // Panel para contener el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(actualizarButton);

        // Crear DefaultTableModel y JTable para mostrar pacientes
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

        vacunasTable.getColumnModel().getColumn(0).setPreferredWidth(80); // Ancho preferido para la columna del número de fila
        vacunasTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(vacunasTable);
        vacunasTable.setRowHeight(50);
        // Obtener y mostrar información de pacientes
        actualizarListaVacunas();

        add(buttonPanel, BorderLayout.NORTH);
        // Agregar JTable al panel
        add(scrollPane, BorderLayout.CENTER);

        autoAjustarColumnas(vacunasTable);
    }

    public void actualizarListaVacunas() {
        // Obtener y mostrar información de pacientes
        List<Vacuna> vacunas = vacunaController.getVacunas();

        // Limpiar la tabla antes de agregar nuevos datos
        tableModel.setRowCount(0);

        // Agregar cada paciente como una fila en la tabla
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
