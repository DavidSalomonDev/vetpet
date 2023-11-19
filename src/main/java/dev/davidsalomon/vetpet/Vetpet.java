package dev.davidsalomon.vetpet;

import dev.davidsalomon.vetpet.controller.CitaController;
import dev.davidsalomon.vetpet.controller.CobroController;
import dev.davidsalomon.vetpet.controller.ExpedienteController;
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.controller.VacunaController;
import dev.davidsalomon.vetpet.view.windows.MainWindow;
import javax.swing.SwingUtilities;

public class Vetpet {

    public static void main(String[] args) {

        PacienteController pacienteController = new PacienteController();
        CitaController citaController = new CitaController();
        VacunaController vacunaController = new VacunaController();
        ExpedienteController expedienteController = new ExpedienteController();
        CobroController cobroController = new CobroController();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new MainWindow();
            }
        });
    }

}
