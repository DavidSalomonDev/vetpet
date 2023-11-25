package dev.davidsalomon.vetpet;

import dev.davidsalomon.vetpet.view.windows.MainWindow;
import javax.swing.SwingUtilities;

/**
 * Clase principal para la aplicación VetPet Clinic. Inicializa controladores
 * para gestionar pacientes, citas, vacunas, expedientes de pacientes y
 * facturación. Inicia la ventana principal de la aplicación utilizando Swing.
 */
public class Vetpet {

    /**
     * Punto de entrada principal para la aplicación VetPet Clinic.
     *
     * @param args Los argumentos de línea de comandos (no se utilizan en esta
     * aplicación).
     */
    public static void main(String[] args) {

        // Inicia la ventana principal de la aplicación utilizando Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
    }

}
