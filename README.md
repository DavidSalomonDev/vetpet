# VetPet Clinic

## Universidad de El Salvador
### Programación Orientada a Objetos

### David Salomón Martínez Valladares - MV12013

## Introducción
El presente describe el proyecto final de la asignatura programación
orientada a objetos, con el fin de poder aplicar los conocimientos obtenidos en las
diferentes temáticas obtenidas durante la realización dicho curso.

## Descripción
La clínica Vet-Pet es una clínica dedicada al cuido de diferentes tipos de mascotas y
diferentes tipos de servicios, en la actualidad el control de asistencias, expedientes
médicos de los pacientes, cobros, citas, etc. Es realizado vía papel con lo cual la
acumulación del mismo ha llevado a la clínica a tener problemas ya que en ocasiones no
se encuentran los papeles necesarios, los expedientes se echan a perder, las citas de igual
manera se agendan en horarios iguales para pacientes diferentes, etc.

La clínica considera la implementación de un sistema que permita un mayor, control y
rapidez en los procesos que son realizados de forma manual. Es por ello que se lanza a
concurso público para poder elegir la empresa que podrá presentar el proyecto que mejor
se acople a las necesidades de dicha clínica.

## Módulos
Dentro de los requerimientos principales que la clínica plantea tenemos los siguientes:
1. **Módulo de Pacientes**. Dicho modulo debe permitir poder ingresar un nuevo
paciente, editar o dar seguimiento a pacientes antes inscritos o dar de baja a
pacientes que ya no estarán en la clínica. Para pacientes nuevos se solicitan los
siguientes datos: Nombre del paciente, nombre del dueño del paciente, edad del
paciente, categoría del paciente (recordando que puede ser un gato, perro, ave, etc),
raza del paciente (recordando que dentro de las diferentes categorías existen
razas especificas), sexo, fecha en la cual fue inscrito al sistema, medidas (altura y
peso), Número de identificación único, pelaje del paciente, fecha de nacimiento.
Para un paciente antiguo debe poseerse la capacidad de editar toda la información
a excepción del número de identificación único del paciente, su fecha de
nacimiento, y para dar de baja a un paciente deberán desaparecer todos sus datos.

2. **Modulo Citas**. En este módulo los encargados podrán agendar las citas para cada
paciente, en el caso de ser paciente nuevo deberá existir una opción que lo marque
como paciente nuevo y permita realizar su inscripción en el sistema (módulo de
pacientes para pacientes nuevos), los campos para reservar una cita deberán ser:
día y hora de la cita, nombre del paciente y motivo de la cita, debemos aclarar que
no se pueden programar más de dos citas el mismo día, pueden editarse las citas
y eliminarse, para pacientes que ya tiene registro no se deberá pedir que registren
nuevamente.

3. **Módulo de Vacunas**: En este módulo deberemos guardar la información referente
a las diferentes vacunas de los pacientes, así como también la fecha en la cual
fueron puestas, la información de este módulo será: Fecha de la vacuna, nombre
de la vacuna, peso y altura en ese momento y edad.

4. **Módulo de Expediente**: En este módulo se tomará la información del módulo de
vacunas y paciente, para generar el expediente del paciente, la información que
este módulo tendrá además de la ya mencionada es: Fecha de la cita del paciente,
diagnostico, medicamentes (si fueran necesarios), agregado a la información del
paciente, las vacunas.

5. Finalmente, tentativamente un módulo de cobros el cual se considerará como
opcional en la presentación de las soluciones de cada empresa.

La información debe poder ser guardada en archivos planos para su futura lectura, así
como también poder ser escalable de manera fácil, rápido y sobre todo que cuente con
una interfaz gráfica con la que el usuario pueda realizar todas las interacciones necesarias
antes mencionadas.