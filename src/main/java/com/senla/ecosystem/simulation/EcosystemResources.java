package com.senla.ecosystem.simulation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий ресурсы экосистемы, включая температуру, влажность и доступную воду.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EcosystemResources {
    private double temperature; // Температура в градусах
    private double humidity;    // Влажность в процентах
    private double availableWater; // Доступная вода в литрах

    @Override
    public String toString() {
        return String.format("EcosystemResources{temperature=%.2f, humidity=%.2f%%, availableWater=%.2fL}",
                temperature, humidity, availableWater);
    }
}
