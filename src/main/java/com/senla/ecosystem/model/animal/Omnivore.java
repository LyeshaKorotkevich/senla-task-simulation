package com.senla.ecosystem.model.animal;

import com.senla.ecosystem.model.Interactable;
import com.senla.ecosystem.utils.Randomizer;

import java.io.Serial;
import java.util.List;

/**
 * Класс, представляющий всеядное животное.
 * Наследуется от класса Animal и реализует интерфейс Interactable.
 */
public class Omnivore extends Animal implements Interactable {
    @Serial
    private static final long serialVersionUID = 3L;

    /**
     * Конструктор для создания экземпляра всеядного животного.
     *
     * @param id             уникальный идентификатор животного
     * @param name           имя животного
     * @param consumptionRate скорость потребления пищи
     */
    public Omnivore(int id, String name, int consumptionRate) {
        super(id, name);
        this.diet = "Omnivore";
        this.consumptionRate = consumptionRate;
    }

    /**
     * Метод взаимодействия с другим животным.
     * Всеядное животное может съесть травоядное или осторожно взаимодействовать с хищником.
     *
     * @param otherAnimal     другое животное для взаимодействия
     * @param animalsToRemove список животных для удаления, если одно из них будет съедено
     */
    @Override
    public void interact(Animal otherAnimal, List<Animal> animalsToRemove) {
        if (otherAnimal instanceof Herbivore && Randomizer.chance(0.5)) {
            System.out.println(this.getName() + " decides to eat " + otherAnimal.getName());
            this.eat(otherAnimal, animalsToRemove);
        } else if (otherAnimal instanceof Predator && Randomizer.chance(0.3)) {
            System.out.println(this.getName() + " cautiously interacts with " + otherAnimal.getName());
        } else {
            System.out.println(this.getName() + " ignores " + otherAnimal.getName());
        }
    }

    /**
     * Метод, позволяющий всеядному животному издавать звук.
     */
    @Override
    public void makeSound() {
        System.out.println("Chirp");
    }
}
