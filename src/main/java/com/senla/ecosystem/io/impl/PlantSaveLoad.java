package com.senla.ecosystem.io.impl;

import com.senla.ecosystem.io.AbstractSaveLoad;
import com.senla.ecosystem.model.animal.Herbivore;
import com.senla.ecosystem.model.animal.Omnivore;
import com.senla.ecosystem.model.animal.Predator;
import com.senla.ecosystem.model.plant.Plant;
import com.senla.ecosystem.model.plant.FloweringPlant;

/**
 * Реализация интерфейса сохранения и загрузки для растений.
 */
public class PlantSaveLoad extends AbstractSaveLoad<Plant> {

    /**
     * Сериализует объект растения в строку.
     *
     * @param plant объект растения, который нужно сериализовать
     * @return строковое представление растения
     */
    @Override
    protected String serializeItem(Plant plant) {
        return plant.getId() + "," + plant.getClass().getSimpleName() + ","+ plant.getSpecies() + "," + plant.getGrowthConditions();
    }

    /**
     * Десериализует строку в объект растения.
     *
     * @param line строка, представляющая объект растения
     * @return десериализованный объект растения
     * @throws IllegalArgumentException если строка имеет неверный формат
     */
    @Override
    protected Plant deserializeItem(String line) {
        String[] parts = line.split(",");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }

        int id = Integer.parseInt(parts[0]);
        String type = parts[1];
        String species = parts[2];
        String growthConditions = parts.length > 3 ? parts[3] : "Unknown";

        switch (type.toLowerCase()) {
            case "floweringplant":
                return new FloweringPlant(species, id, growthConditions);
            default:
                throw new IllegalArgumentException("Unknown animal type: " + type);
        }
    }
}
