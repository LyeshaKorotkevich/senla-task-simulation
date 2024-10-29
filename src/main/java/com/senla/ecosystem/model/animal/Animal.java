package com.senla.ecosystem.model.animal;

import com.senla.ecosystem.model.Identifiable;
import com.senla.ecosystem.model.plant.Plant;
import com.senla.ecosystem.utils.Randomizer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Абстрактный класс, представляющий животное в экосистеме.
 */
@Data
@NoArgsConstructor
public abstract class Animal implements Serializable, Identifiable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    protected String diet;
    protected int consumptionRate;
    protected double health;
    protected double age;
    protected double reproductionRate;
    protected double aggressiveness;
    protected double speed;

    /**
     * Конструктор для создания экземпляра животного с заданными параметрами.
     *
     * @param id   уникальный идентификатор животного
     * @param name имя животного
     */
    public Animal(int id, String name) {
        this.id = id;
        this.name = name;
        this.diet = "Unknown";
        this.consumptionRate = 0;
        this.health = Randomizer.getRandomDouble(70.0, 100.0);
        this.age = 0.0;
        this.reproductionRate = Randomizer.getRandomDouble(0.05, 0.2);
        this.aggressiveness = Randomizer.getRandomDouble(0.3, 0.7);
        this.speed = Randomizer.getRandomDouble(4.0, 8.0);
    }

    @Override
    public int getId() {
        return id;
    }

    /**
     * Метод, который позволяет животному издавать звук.
     * Этот метод должен быть реализован в подклассах.
     */
    public abstract void makeSound();

    /**
     * Метод, позволяющий одному животному съесть другое животное.
     *
     * @param otherAnimal     животное, которое будет съедено
     * @param animalsToRemove список животных для удаления
     */
    public void eat(Animal otherAnimal, List<Animal> animalsToRemove) {
        System.out.println(this.name + " is eating " + otherAnimal.getName());
        animalsToRemove.add(otherAnimal);
    }

    /**
     * Метод, позволяющий животному съесть растение.
     *
     * @param plant          растение, которое будет съедено
     * @param plantsToRemove список растений для удаления
     */
    public void eat(Plant plant, List<Plant> plantsToRemove) {
        System.out.println(this.name + " is eating " + plant.getSpecies());
        plantsToRemove.add(plant);
    }

    /**
     * Метод, позволяющий животному убежать.
     */
    public void flee() {
        System.out.println(this.name + " runs away!");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal animal)) return false;
        return id == animal.id &&
                consumptionRate == animal.consumptionRate &&
                Double.compare(health, animal.health) == 0 &&
                Double.compare(age, animal.age) == 0 &&
                Double.compare(reproductionRate, animal.reproductionRate) == 0 &&
                Double.compare(aggressiveness, animal.aggressiveness) == 0 &&
                Double.compare(speed, animal.speed) == 0 &&
                Objects.equals(name, animal.name) &&
                Objects.equals(diet, animal.diet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, diet, consumptionRate, health, age, reproductionRate, aggressiveness, speed);
    }
}
