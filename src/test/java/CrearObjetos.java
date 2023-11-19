
import dev.davidsalomon.vetpet.controller.PacienteController;
import dev.davidsalomon.vetpet.model.Paciente;
import dev.davidsalomon.vetpet.utils.Data;
import java.util.List;

public class CrearObjetos {

    public static void main(String[] args) {

        PacienteController pacienteController = new PacienteController();
        /*
        Paciente paciente1 = new Paciente("Pluton", "David", 3, "Perro", "Pastor Alemán", "M", 5, 5, "Profundo", "2022-01-01");
        Paciente paciente2 = new Paciente("Luna", "Maria", 2, "Gato", "Siames", "H", 3, 4, "Corto", "2022-02-15");
        Paciente paciente3 = new Paciente("Max", "Juan", 4, "Perro", "Golden Retriever", "M", 6, 7, "Largo", "2021-12-10");
        Paciente paciente4 = new Paciente("Nina", "Ana", 1, "Gato", "Persa", "H", 2, 3, "Largo", "2022-03-20");
        Paciente paciente5 = new Paciente("Rocky", "Carlos", 5, "Perro", "Bulldog Francés", "M", 4, 6, "Corto", "2022-05-05");
        Paciente paciente6 = new Paciente("Mochi", "Isabel", 2, "Gato", "Ragdoll", "H", 3, 5, "Semi-largo", "2022-04-12");
        Paciente paciente7 = new Paciente("Bella", "Roberto", 6, "Perro", "Labrador Retriever", "H", 7, 8, "Corto", "2021-11-08");
        Paciente paciente8 = new Paciente("Simba", "Laura", 4, "Gato", "Maine Coon", "M", 5, 7, "Largo", "2021-10-03");
        Paciente paciente9 = new Paciente("Thor", "Sofia", 3, "Perro", "Husky Siberiano", "M", 6, 6, "Largo", "2022-06-18");
        Paciente paciente10 = new Paciente("Mia", "Pedro", 2, "Gato", "Bengala", "H", 4, 4, "Corto", "2022-07-22");

        pacienteController.agregarPaciente(paciente1);
        pacienteController.agregarPaciente(paciente2);
        pacienteController.agregarPaciente(paciente3);
        pacienteController.agregarPaciente(paciente4);
        pacienteController.agregarPaciente(paciente5);
        pacienteController.agregarPaciente(paciente6);
        pacienteController.agregarPaciente(paciente7);
        pacienteController.agregarPaciente(paciente8);
        pacienteController.agregarPaciente(paciente9);
        pacienteController.agregarPaciente(paciente10);
         */
        List<Paciente> pacientes = pacienteController.getPacientes();
        System.out.println("Pacientes registrados:");
        pacientes.forEach(paciente -> System.out.println(Data.objectToJson(paciente)));
    }

}
