package dev.davidsalomon.vetpet.view.panels;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.model.Paciente;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

public final class MostrarPacientesPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private PacienteController pacienteController;
    private final JTable pacientesTable;
    private final DefaultTableModel tableModel;

    public MostrarPacientesPanel(PacienteController pacienteController) {
        this.pacienteController = pacienteController;

        setLayout(new BorderLayout());

        JButton actualizarButton = new JButton("Actualizar Lista");
        actualizarButton.addActionListener(e -> actualizarListaPacientes());

        // Panel para contener el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(actualizarButton);

        // Crear DefaultTableModel y JTable para mostrar pacientes
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
        pacientesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Deshabilitar el ajuste automático de tamaño de columna
        pacientesTable.getColumnModel().getColumn(0).setPreferredWidth(80); // Ancho preferido para la columna del número de fila
        pacientesTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(pacientesTable);

        pacientesTable.setRowHeight(50);
        // Obtener y mostrar información de pacientes
        actualizarListaPacientes();

        // Agregar JTable y botón al panel
        add(buttonPanel, BorderLayout.NORTH);
        // Agregar JTable al panel
        add(scrollPane, BorderLayout.CENTER);

        autoAjustarColumnas(pacientesTable);
    }

    // Método para actualizar la lista de pacientes en la JTable
    public void actualizarListaPacientes() {
        // Obtener y mostrar información de pacientes
        List<Paciente> pacientes = pacienteController.getPacientes();

        // Limpiar la tabla antes de agregar nuevos datos
        tableModel.setRowCount(0);

        // Agregar cada paciente como una fila en la tabla
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

    // Método para autoajustar las columnas de la JTable
    private void autoAjustarColumnas(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 30; // Ancho mínimo
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
