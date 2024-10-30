package com.senla.ecosystem.simulation;

import com.senla.ecosystem.model.Interactable;
import com.senla.ecosystem.model.animal.Animal;
import com.senla.ecosystem.model.animal.Herbivore;
import com.senla.ecosystem.model.animal.Predator;
import com.senla.ecosystem.model.plant.Plant;
import com.senla.ecosystem.repository.Repository;
import com.senla.ecosystem.utils.Randomizer;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.senla.ecosystem.utils.AppConstants.RESOURCE_FOOD;
import static com.senla.ecosystem.utils.AppConstants.RESOURCE_SHELTER;
import static com.senla.ecosystem.utils.AppConstants.RESOURCE_WATER;

@Data
public class Simulator {
    private static final Logger logger = LoggerFactory.getLogger(Simulator.class);

    private final Repository<Animal> animalRepository;
    private final Repository<Plant> plantRepository;

    private final EcosystemResources ecosystemResources;

    public Simulator(Repository<Animal> animalRepository, Repository<Plant> plantRepository) {
        this.animalRepository = animalRepository;
        this.plantRepository = plantRepository;

        ecosystemResources = new EcosystemResources(RESOURCE_WATER, RESOURCE_FOOD, RESOURCE_SHELTER);
    }

    private void removeAnimal(Animal animal) {
        animalRepository.remove(animal.getId());
    }

    private void removePlant(Plant plant) {
        plantRepository.remove(plant.getId());
    }

    public void simulateAutomatically(int cycles) {
        logger.info("Starting automated simulation...");

        for (int i = 1; i <= cycles; i++) {
            logger.info("Cycle {}: ", i);

            adjustEcosystemConditions();
            logger.info("Ecosystem conditions adjusted: Temperature = {}, Humidity = {}, Water = {}",
                    ecosystemResources.getTemperature(), ecosystemResources.getHumidity(), ecosystemResources.getAvailableWater());

            performInteractions();
            replenishResources();

            logger.info("Cycle {} completed.\n", i);
        }

        logger.info("Automated simulation ended.");
    }

    public void adjustEcosystemConditions() {
        double tempChange = Math.random() * 2 - 1;
        double humidityChange = Math.random() * 2 - 1;
        double waterChange = -2;

        ecosystemResources.setTemperature(ecosystemResources.getTemperature() + tempChange);
        ecosystemResources.setHumidity(ecosystemResources.getHumidity() + humidityChange);
        ecosystemResources.setAvailableWater(ecosystemResources.getAvailableWater() + waterChange);

        logger.info("Ecosystem conditions adjusted: Temperature = {}, Humidity = {}, Water = {}",
                ecosystemResources.getTemperature(), ecosystemResources.getHumidity(), ecosystemResources.getAvailableWater());
    }

    private void performInteractions() {
        List<Animal> copyOfAnimals = new ArrayList<>(animalRepository.getAll());
        List<Plant> copyOfPlants = new ArrayList<>(plantRepository.getAll());
        List<Animal> animalsToRemove = new ArrayList<>();
        List<Plant> plantsToRemove = new ArrayList<>();

        Collections.shuffle(copyOfAnimals);
        Collections.shuffle(copyOfPlants);

        for (Animal currentAnimal : copyOfAnimals) {
            if (currentAnimal instanceof Interactable && !animalsToRemove.contains(currentAnimal)) {
                int interactionsLimit = 2;
                int interactionsCount = 0;

                Set<Animal> interactedAnimals = new HashSet<>();

                while (interactionsCount < interactionsLimit) {
                    int randomIndex = Randomizer.getRandomInt(0, copyOfAnimals.size() - 1);
                    Animal otherAnimal = copyOfAnimals.get(randomIndex);

                    if (otherAnimal != currentAnimal && !interactedAnimals.contains(otherAnimal) && !animalsToRemove.contains(otherAnimal)) {
                        interactedAnimals.add(otherAnimal);
                        ((Interactable) currentAnimal).interact(otherAnimal, animalsToRemove);
                        interactionsCount++;
                    }

                    if (interactionsCount >= copyOfAnimals.size() - 1) {
                        break;
                    }
                }
            }
        }

        List<Plant> plants = plantRepository.getAll();
        for (Plant plant : plants) {
            plant.adaptToEnvironment(ecosystemResources);
            if (plant.getHealth() == 0) {
                plantsToRemove.add(plant);
            }
        }

        plantsToRemove.forEach(plant -> plantRepository.remove(plant.getId()));
        animalsToRemove.forEach(animal -> animalRepository.remove(animal.getId()));
    }


    private void replenishResources() {
        ecosystemResources.setAvailableWater(ecosystemResources.getAvailableWater() + 50);
        logger.info("Resources replenished: Water levels increased.");
    }

    public String predictPopulationTrends() {
        StringBuilder predictions = new StringBuilder("Population Trends Prediction:\n");

        double availableWater = ecosystemResources.getAvailableWater();
        double currentHumidity = ecosystemResources.getHumidity();
        double currentTemperature = ecosystemResources.getTemperature();


        List<Animal> animals = animalRepository.getAll();
        List<Plant> plants = plantRepository.getAll();

        if (animals.isEmpty() && plants.isEmpty()) {
            predictions.append("No animals or plants present in the ecosystem.\n");
            return predictions.toString();
        }

        for (Animal animal : animals) {
            double waterRequirement = animal.getConsumptionRate();
            boolean hasFood = false;

            if (animal instanceof Herbivore) {
                hasFood = !plants.isEmpty() && currentHumidity >= 20;
            } else if (animal instanceof Predator) {
                hasFood = animals.stream().anyMatch(a -> a instanceof Herbivore && !a.equals(animal));
            }

            double growthRate = 1.0;
            if (availableWater < waterRequirement * 1.2 || !hasFood) {
                growthRate -= 0.5;
            } else if (availableWater > waterRequirement * 1.5 && hasFood) {
                growthRate += 0.3;
            }

            if (currentTemperature < 5 || currentTemperature > 35) {
                growthRate -= 0.2;
            }

            if (growthRate > 1.0) {
                predictions.append(animal.getName()).append(" population is likely to increase.\n");
            } else if (growthRate < 1.0) {
                predictions.append(animal.getName()).append(" population is likely to decrease.\n");
            } else {
                predictions.append(animal.getName()).append(" population is stable.\n");
            }
        }


        for (Plant plant : plants) {
            double growthRate = 1.0;

            if (currentHumidity < 20) {
                growthRate -= 0.5;
            } else if (currentHumidity > 50 && currentTemperature > 15 && currentTemperature < 30) {
                growthRate += 0.3;
            }

            if (currentTemperature < 10 || currentTemperature > 35) {
                growthRate -= 0.2;
            }

            if (growthRate > 1.0) {
                predictions.append(plant.getSpecies()).append(" population is likely to increase.\n");
            } else if (growthRate < 1.0) {
                predictions.append(plant.getSpecies()).append(" population is likely to decrease.\n");
            } else {
                predictions.append(plant.getSpecies()).append(" population is stable.\n");
            }
        }

        return predictions.toString();
    }
}
