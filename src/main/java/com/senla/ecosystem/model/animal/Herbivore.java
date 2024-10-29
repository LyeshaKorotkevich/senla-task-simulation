package com.senla.ecosystem.model.animal;

import com.senla.ecosystem.model.Interactable;
import com.senla.ecosystem.utils.Randomizer;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.List;

/**
 * Класс, представляющий травоядное животное.
 * Наследуется от класса Animal и реализует интерфейс Interactable.
 */
@EqualsAndHashCode(callSuper = true)
public class Herbivore extends Animal implements Interactable {
    @Serial
    private static final long serialVersionUID = 2L;

    /**
     * Конструктор для создания экземпляра травоядного животного.
     *
     * @param id             уникальный идентификатор животного
     * @param name           имя животного
     * @param consumptionRate скорость потребления пищи
     */
    public Herbivore(int id, String name, int consumptionRate) {
        super(id, name);
        this.diet = "Herbivore";
        this.consumptionRate = consumptionRate;
    }

    /**
     * Метод взаимодействия с другим животным.
     * Травоядное может убежать от хищника или продолжить поедание травы.
     *
     * @param otherAnimal     другое животное для взаимодействия
     * @param animalsToRemove список животных для удаления, если необходимо
     */
    @Override
    public void interact(Animal otherAnimal, List<Animal> animalsToRemove) {
        if (otherAnimal instanceof Predator && Randomizer.chance(0.6)) {
            System.out.println(this.getName() + " flees from " + otherAnimal.getName());
            this.flee();
        } else {
            System.out.println(this.getName() + " continues grazing and ignores " + otherAnimal.getName());
        }
    }

    /**
     * Метод, позволяющий травоядному животному издавать звук.
     */
    @Override
    public void makeSound() {
        System.out.println("Graze");
    }
}