
import dev.davidsalomon.vetpet.controller.CobroController;
import dev.davidsalomon.vetpet.model.Cobro;
import dev.davidsalomon.vetpet.utils.Data;
import java.util.List;

/**
 *
 * @author salo
 */
public class CrearCobros {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        CobroController cobroController = new CobroController();

        Cobro cobro1 = new Cobro("2cbbd711-e1d8-45fb-a2bf-5123cc8be5bc",
                "2023-11-15",
                3500,
                "Operación de cabeza");
        cobroController.agregarCobro(cobro1);

        List<Cobro> cobros = cobroController.getCobros();
        System.out.println("Cobros registrados:");
        cobros.forEach(cobro -> System.out.println(Data.objectToJson(cobro)));
    }

}
