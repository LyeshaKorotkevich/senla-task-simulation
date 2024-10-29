package com.senla.ecosystem.model.plant;

import lombok.AllArgsConstructor;

import java.io.Serial;

/**
 * Класс, представляющий цветущее растение.
 * Наследуется от абстрактного класса Plant.
 */
@AllArgsConstructor
public class FloweringPlant extends Plant {
    @Serial
    private static final long serialVersionUID = 6L;

    /**
     * Конструктор для создания экземпляра цветущего растения.
     *
     * @param species         название вида растения
     * @param id              уникальный идентификатор растения
     * @param growthConditions условия для роста растения
     */
    public FloweringPlant(String species, int id, String growthConditions) {
        super(species, id);
        this.growthConditions = growthConditions;
    }
}
