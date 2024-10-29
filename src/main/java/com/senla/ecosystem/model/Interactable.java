package com.senla.ecosystem.model;

import com.senla.ecosystem.model.animal.Animal;

import java.util.List;

/**
 * Интерфейс, представляющий объекты, которые могут взаимодействовать с животными.
 */
public interface Interactable {
    /**
     * Метод для взаимодействия с другим животным.
     *
     * @param otherAnimal     другое животное, с которым происходит взаимодействие
     * @param animalsToRemove список животных для удаления, если необходимо
     */
    void interact(Animal otherAnimal, List<Animal> animalsToRemove);
}
