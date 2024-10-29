package com.senla.ecosystem.model.animal;

import com.senla.ecosystem.model.Interactable;
import com.senla.ecosystem.utils.Randomizer;

import java.io.Serial;
import java.util.List;

/**
 * Класс, представляющий хищное животное.
 * Наследуется от класса Animal и реализует интерфейс Interactable.
 */
public class Predator extends Animal implements Interactable {
    @Serial
    private static final long serialVersionUID = 4L;

    /**
     * Конструктор для создания экземпляра хищного животного.
     *
     * @param id             уникальный идентификатор животного
     * @param name           имя животного
     * @param consumptionRate скорость потребления пищи
     */
    public Predator(int id, String name, int consumptionRate) {
        super(id, name);
        this.diet = "Carnivore";
        this.consumptionRate = consumptionRate;
    }

    /**
     * Метод взаимодействия с другим животным.
     * Хищник может преследовать и съесть травоядное или напасть на всеядное животное.
     *
     * @param otherAnimal     другое животное для взаимодействия
     * @param animalsToRemove список животных для удаления, если одно из них будет съедено
     */
    @Override
    public void interact(Animal otherAnimal, List<Animal> animalsToRemove) {
        if (otherAnimal instanceof Herbivore && Randomizer.chance(0.7)) {
            System.out.println(this.getName() + " chases and eats " + otherAnimal.getName());
            this.eat(otherAnimal, animalsToRemove);
        } else if (otherAnimal instanceof Omnivore && Randomizer.chance(0.5)) {
            System.out.println(this.getName() + " attacks " + otherAnimal.getName() + ", considering it as food.");
            this.eat(otherAnimal, animalsToRemove);
        } else {
            System.out.println(this.getName() + " is not interested in " + otherAnimal.getName());
        }
    }

    /**
     * Метод, позволяющий хищнику издавать звук.
     */
    @Override
    public void makeSound() {
        System.out.println("Roar");
    }
}
