package com.senla.ecosystem.view;

import com.senla.ecosystem.model.animal.Animal;
import com.senla.ecosystem.model.plant.Plant;
import com.senla.ecosystem.simulation.EcosystemResources;

import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Класс для отображения информации об экосистеме в консоли и получения ввода от пользователя.
 */
public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Отображает сообщение пользователю.
     *
     * @param message сообщение для отображения
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Отображает текущее состояние экосистемы.
     *
     * @param animals  список животных
     * @param plants   список растений
     * @param resources ресурсы экосистемы
     */
    public void displayEcosystem(List<Animal> animals, List<Plant> plants, EcosystemResources resources) {
        System.out.println("Current ecosystem status:");
        System.out.println("Ecosystem resources: " + resources);

        System.out.println("Animals:");
        for (Animal animal : animals) {
            System.out.printf("%s (ID: %d): Diet: %s, Consumption Rate: %d\n",
                    animal.getName(), animal.getId(), animal.getDiet(), animal.getConsumptionRate());
        }

        System.out.println("Plants:");
        for (Plant plant : plants) {
            System.out.printf("%s (ID: %d): Growth Conditions: %s\n",
                    plant.getSpecies(), plant.getId(), plant.getGrowthConditions());
        }
    }

    /**
     * Отображает текущее состояние экосистемы без животных и растений.
     *
     * @param resources ресурсы экосистемы
     */
    public void displayEcosystem(EcosystemResources resources) {
        System.out.println("Current ecosystem status:");
        System.out.println("Ecosystem resources: " + resources);
    }

    /**
     * Получает строковый ввод от пользователя с заданным сообщением.
     *
     * @param prompt сообщение для отображения
     * @return введенная пользователем строка
     */
    public String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.next();
    }

    /**
     * Получает выбор пользователя из предложенных вариантов.
     *
     * @return выбор пользователя
     */
    public int getUserChoice() {
        System.out.println("\nEnter 1 to simulate, 2 to predict, 3 to configure the environment, 4 to add a creature, 5 to remove a creature, 6 to update a creature, or 0 to exit:");
        return getValidatedIntInput();
    }

    /**
     * Получает целочисленный ввод от пользователя с заданным сообщением.
     *
     * @param prompt сообщение для отображения
     * @return введенное пользователем число
     */
    public int getInputInt(String prompt) {
        System.out.println(prompt);
        return getValidatedIntInput();
    }

    /**
     * Метод для получения и проверки целочисленного ввода от пользователя.
     *
     * @return проверенное целое число
     */
    private int getValidatedIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: please enter a valid integer.");
                scanner.next();
            }
        }
    }
}