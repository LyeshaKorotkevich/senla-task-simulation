package com.senla.ecosystem.model.plant;

import com.senla.ecosystem.model.Identifiable;
import com.senla.ecosystem.simulation.EcosystemResources;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * Абстрактный класс, представляющий растение в экосистеме.
 */
@Data
@NoArgsConstructor
public abstract class Plant implements Serializable, Identifiable {
    @Serial
    private static final long serialVersionUID = 5L;

    @Getter
    protected String species;
    private int id;
    @Getter
    protected String growthConditions;
    protected double health;
    protected double growthRate;

    /**
     * Конструктор для создания экземпляра растения с заданными параметрами.
     *
     * @param species название вида растения
     * @param id      уникальный идентификатор растения
     */
    public Plant(String species, int id) {
        this.species = species;
        this.id = id;
        this.growthConditions = "Unknown";
        this.health = 100.0;
        this.growthRate = 1.0;
    }

    @Override
    public int getId() {
        return id;
    }

    /**
     * Метод, позволяющий растению адаптироваться к условиям окружающей среды.
     *
     * @param resources ресурсы экосистемы, влияющие на здоровье растения
     */
    public void adaptToEnvironment(EcosystemResources resources) {
        if (resources.getTemperature() > 30 || resources.getAvailableWater() < 20) {
            this.health -= 10;
            System.out.println(this.species + " suffers from unfavorable conditions.");
        } else {
            this.health += 5;
            System.out.println(this.species + " is thriving.");
        }
    }
}
