package dev.davidsalomon.vetpet.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * La clase Data proporciona funciones útiles para la manipulación de datos.
 */
public class Data {

    /**
     * Constructor por defecto de la clase Data.
     */
    public Data() {

    }

    /**
     * Genera un ID único utilizando UUID.
     *
     * @return Un ID único en formato de cadena.
     */
    public static String generarIDUnico() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * Convierte un objeto en formato JSON.
     *
     * @param obj El objeto a convertir en formato JSON.
     * @return Una representación en formato JSON del objeto.
     */
    public static String objectToJson(Object obj) {
        Map<String, Object> propertyMap = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                propertyMap.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        boolean first = true;
        for (Map.Entry<String, Object> entry : propertyMap.entrySet()) {
            if (!first) {
                jsonBuilder.append(",");
            }
            jsonBuilder.append("\"").append(entry.getKey()).append("\":");
            if (entry.getValue() instanceof String) {
                jsonBuilder.append("\"").append(entry.getValue()).append("\"");
            } else {
                jsonBuilder.append(entry.getValue());
            }
            first = false;
        }
        jsonBuilder.append("}");

        return jsonBuilder.toString();
    }
}
