package dev.davidsalomon.vetpet.view;

import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.model.Paciente;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public final class MostrarPacientesPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private PacienteController pacienteController;
    private final JTable pacientesTable;
    private final DefaultTableModel tableModel;

    public MostrarPacientesPanel(PacienteController pacienteController) {
        this.pacienteController = pacienteController;

        setLayout(new BorderLayout());

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

        // Agregar JTable al panel
        add(scrollPane, BorderLayout.CENTER);
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
}
