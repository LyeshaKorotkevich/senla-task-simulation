package com.senla.ecosystem.io.impl;

import com.senla.ecosystem.io.AbstractSaveLoad;
import com.senla.ecosystem.model.animal.Animal;
import com.senla.ecosystem.model.animal.Herbivore;
import com.senla.ecosystem.model.animal.Omnivore;
import com.senla.ecosystem.model.animal.Predator;

/**
 * Реализация интерфейса сохранения и загрузки для животных.
 */
public class AnimalSaveLoad extends AbstractSaveLoad<Animal> {

    /**
     * Сериализует объект животного в строку.
     *
     * @param animal объект животного, который нужно сериализовать
     * @return строковое представление животного
     */
    @Override
    protected String serializeItem(Animal animal) {
        return animal.getId() + "," + animal.getClass().getSimpleName() + "," + animal.getName() + "," + animal.getConsumptionRate();
    }

    /**
     * Десериализует строку в объект животного.
     *
     * @param line строка, представляющая объект животного
     * @return десериализованный объект животного
     * @throws IllegalArgumentException если тип животного неизвестен
     */
    @Override
    protected Animal deserializeItem(String line) {
        String[] parts = line.split(",");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }

        int id = Integer.parseInt(parts[0]);
        String type = parts[1];
        String name = parts[2];
        int consumptionRate = Integer.parseInt(parts[3]);

        switch (type.toLowerCase()) {
            case "herbivore":
                return new Herbivore(id, name, consumptionRate);
            case "predator":
                return new Predator(id, name, consumptionRate);
            case "omnivore":
                return new Omnivore(id, name, consumptionRate);
            default:
                throw new IllegalArgumentException("Unknown animal type: " + type);
        }
    }
}
