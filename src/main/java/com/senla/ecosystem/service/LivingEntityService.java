package com.senla.ecosystem.service;

import com.senla.ecosystem.model.Identifiable;
import com.senla.ecosystem.model.animal.*;
import com.senla.ecosystem.model.plant.FloweringPlant;
import com.senla.ecosystem.model.plant.Plant;
import com.senla.ecosystem.repository.Repository;
import com.senla.ecosystem.view.ConsoleView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LivingEntityService {
    private static final Logger logger = LoggerFactory.getLogger(LivingEntityService.class);

    private final Repository<Animal> animalRepository;
    private final Repository<Plant> plantRepository;
    private final ConsoleView view;

    public LivingEntityService(ConsoleView view, Repository<Animal> animalRepository, Repository<Plant> plantRepository) {
        this.view = view;
        this.animalRepository = animalRepository;
        this.plantRepository = plantRepository;
    }

    public void addLivingEntity() {
        int entityType = view.getInputInt("Choose the type to add (1 - Animal, 2 - Plant): ");
        int id = entityType == 1 ? generateUniqueId(animalRepository) : generateUniqueId(plantRepository);

        if (entityType == 1) {
            String name = view.getInput("Enter the animal's name: ");
            String type = view.getInput("Enter animal type (Herbivore/Predator/Omnivore): ");
            int consumptionRate = Integer.parseInt(view.getInput("Enter the animal's consumption rate: "));

            Animal animal;
            switch (type.toLowerCase()) {
                case "herbivore" -> animal = new Herbivore(id, name, consumptionRate);
                case "predator" -> animal = new Predator(id, name, consumptionRate);
                case "omnivore" -> animal = new Omnivore(id, name, consumptionRate);
                default -> {
                    view.displayMessage("Unknown animal type.");
                    return;
                }
            }
            animalRepository.add(animal);
            view.displayMessage(name + " has been added as an Animal.");
            logger.info(name + " added to ecosystem as Animal.");
        } else if (entityType == 2) {
            String species = view.getInput("Enter the plant's species: ");
            String growthConditions = view.getInput("Enter the plant's growth conditions: ");
            Plant plant = new FloweringPlant(species, id, growthConditions);
            plantRepository.add(plant);
            view.displayMessage(species + " has been added as a Plant.");
            logger.info(species + " added to ecosystem as Plant.");
        } else {
            view.displayMessage("Invalid type selected.");
        }
    }

    public void deleteLivingEntityById() {
        int entityType = view.getInputInt("Choose the type to delete (1 - Animal, 2 - Plant): ");
        int id = Integer.parseInt(view.getInput("Enter the ID of the entity to delete: "));

        if (entityType == 1) {
            Animal animal = animalRepository.getById(id);
            if (animal != null) {
                animalRepository.remove(id);
                view.displayMessage("Animal with ID " + id + " has been deleted.");
                logger.info("Animal with ID " + id + " removed.");
            } else {
                view.displayMessage("No animal found with ID " + id);
            }
        } else if (entityType == 2) {
            Plant plant = plantRepository.getById(id);
            if (plant != null) {
                plantRepository.remove(id);
                view.displayMessage("Plant with ID " + id + " has been deleted.");
                logger.info("Plant with ID " + id + " removed.");
            } else {
                view.displayMessage("No plant found with ID " + id);
            }
        } else {
            view.displayMessage("Invalid type selected.");
        }
    }

    public void updateLivingEntityById() {
        int entityType = view.getInputInt("Choose the type to update (1 - Animal, 2 - Plant): ");
        int id = Integer.parseInt(view.getInput("Enter the ID of the entity to update: "));

        if (entityType == 1) {
            Animal animal = animalRepository.getById(id);
            if (animal != null) {
                String newName = view.getInput("Enter the new name for the animal: ");
                int newConsumptionRate = Integer.parseInt(view.getInput("Enter the new consumption rate for the animal: "));
                animal.setName(newName);
                animal.setConsumptionRate(newConsumptionRate);
                animalRepository.update(animal);
                view.displayMessage("Animal updated successfully.");
                logger.info("Animal ID " + id + " updated to " + newName);
            } else {
                view.displayMessage("No animal found with ID " + id);
            }
        } else if (entityType == 2) {
            Plant plant = plantRepository.getById(id);
            if (plant != null) {
                String newSpecies = view.getInput("Enter the new species for the plant: ");
                String newGrowthConditions = view.getInput("Enter the new growth conditions for the plant: ");
                plant.setSpecies(newSpecies);
                plant.setGrowthConditions(newGrowthConditions);
                plantRepository.update(plant);
                view.displayMessage("Plant updated successfully.");
                logger.info("Plant ID " + id + " updated to " + newSpecies);
            } else {
                view.displayMessage("No plant found with ID " + id);
            }
        } else {
            view.displayMessage("Invalid type selected.");
        }
    }

    private int generateUniqueId(Repository<? extends Identifiable> repository) {
        return repository.getAll().stream()
                .mapToInt(Identifiable::getId)
                .max()
                .orElse(0) + 1;
    }
}
