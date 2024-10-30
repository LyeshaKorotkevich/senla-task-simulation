package com.senla.ecosystem.controller;

import com.senla.ecosystem.model.animal.Animal;
import com.senla.ecosystem.model.plant.Plant;
import com.senla.ecosystem.service.LivingEntityService;
import com.senla.ecosystem.utils.Randomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.senla.ecosystem.model.*;
import com.senla.ecosystem.repository.Repository;
import com.senla.ecosystem.simulation.Simulator;
import com.senla.ecosystem.view.ConsoleView;

import java.io.IOException;
import java.util.*;

public class EcosystemController {

    private static final Logger logger = LoggerFactory.getLogger(EcosystemController.class);

    private final Repository<Animal> animalRepository;
    private final Repository<Plant> plantRepository;
    private final LivingEntityService entityService;
    private final ConsoleView view;
    private final Simulator simulator;

    public EcosystemController(ConsoleView view, Repository<Animal> animalRepository, Repository<Plant> plantRepository) {
        this.view = view;
        this.animalRepository = animalRepository;
        this.plantRepository = plantRepository;

        this.entityService = new LivingEntityService(view, animalRepository, plantRepository);
        this.simulator = new Simulator(animalRepository, plantRepository);
    }

    public void run() {
        view.displayMessage("Welcome to the Ecosystem Simulator!");
        logger.info("Ecosystem Simulator started.");
        boolean running = true;

        while (running) {
            int choice = view.getUserChoice();

            switch (choice) {
                case 1 -> {
                    simulateEcosystem();
                    view.displayEcosystem(animalRepository.getAll(), plantRepository.getAll(), simulator.getEcosystemResources());
                }
                case 2 -> showPredictions();
                case 3 -> {
                    adjustEnvironment();
                    view.displayEcosystem(simulator.getEcosystemResources());
                }
                case 4 -> {
                    entityService.addLivingEntity();
                    view.displayEcosystem(animalRepository.getAll(), plantRepository.getAll(), simulator.getEcosystemResources());
                }
                case 5 -> {
                    entityService.deleteLivingEntityById();
                    view.displayEcosystem(animalRepository.getAll(), plantRepository.getAll(), simulator.getEcosystemResources());
                }
                case 6 -> {
                    entityService.updateLivingEntityById();
                    view.displayEcosystem(animalRepository.getAll(), plantRepository.getAll(), simulator.getEcosystemResources());
                }
                case 0 -> running = false;
                default -> {
                    view.displayMessage("Invalid option. Please try again.");
                    logger.error("User selected an invalid option: " + choice);
                }
            }
        }
        logger.info("Ecosystem Simulator ended.");
    }

    private void simulateEcosystem() {
        simulator.simulateAutomatically(1);

        logger.info("Starting ecosystem simulation...");
        view.displayMessage("Starting ecosystem simulation...");

        List<Animal> currentAnimals = animalRepository.getAll();
        List<Plant> currentPlants = plantRepository.getAll();

        int survivingAnimals = currentAnimals.size();
        int survivingPlants = currentPlants.size();

        view.displayMessage("\n--- Ecosystem Simulation Summary ---");
        view.displayMessage("Surviving Animals: " + survivingAnimals);
        view.displayMessage("Surviving Plants: " + survivingPlants);
        logger.info("Ecosystem simulated successfully with " + survivingAnimals + " animals and " + survivingPlants + " plants surviving.");
        view.displayMessage("Ecosystem simulated successfully.");
    }


    private void showPredictions() {
        String predictions = simulator.predictPopulationTrends();
        if (predictions.isEmpty()) {
            view.displayMessage("There is nothing to predict.");
            logger.info("No predictions available.");
        } else {
            view.displayMessage(predictions);
            logger.info("Predictions shown: " + predictions);
        }
    }

    private void adjustEnvironment() {
        simulator.adjustEcosystemConditions();
        view.displayMessage("Environment adjusted.");
        logger.info("Ecosystem conditions adjusted.");
    }
}
