
import dev.davidsalomon.vetpet.controller.CitaController;
import dev.davidsalomon.vetpet.model.Cita;
import dev.davidsalomon.vetpet.utils.Data;
import java.util.List;

/**
 *
 * @author salo
 */
public class CrearCitas {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        CitaController citaController = new CitaController();

        Cita cita1 = new Cita("2cbbd711-e1d8-45fb-a2bf-5123cc8be5bc",
                "2023-11-15",
                "4:00 PM",
                "Dolor de estómago");
        citaController.agregarCita(cita1);

        List<Cita> citas = citaController.getCitas();
        System.out.println("Citas registradas:");
        citas.forEach(paciente -> System.out.println(Data.objectToJson(paciente)));
    }

}
