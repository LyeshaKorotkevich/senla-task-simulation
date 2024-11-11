package com.senla.ecosystem.simulation;

import com.senla.ecosystem.model.Interactable;
import com.senla.ecosystem.model.animal.Animal;
import com.senla.ecosystem.model.animal.Herbivore;
import com.senla.ecosystem.model.animal.Predator;
import com.senla.ecosystem.model.plant.Plant;
import com.senla.ecosystem.repository.Repository;
import com.senla.ecosystem.simulation.ecosystem.Ecosystem;
import com.senla.ecosystem.simulation.ecosystem.EcosystemResources;
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

    private final Ecosystem ecosystem;

    public Simulator(Repository<Animal> animalRepository, Repository<Plant> plantRepository) {
        this.animalRepository = animalRepository;
        this.plantRepository = plantRepository;

        this.ecosystem = new Ecosystem(new EcosystemResources(RESOURCE_WATER, RESOURCE_FOOD, RESOURCE_SHELTER));
    }

    public void simulateAutomatically(int cycles) {
        logger.info("Starting automated simulation...");

        for (int i = 1; i <= cycles; i++) {
            logger.info("Cycle {}: ", i);

            ecosystem.changeState();
            logger.info("New ecosystem state: " + ecosystem.getCurrentState().getClass().getSimpleName());

            ecosystem.adjustConditions();
            logger.info(ecosystem.getEcosystemResources().toString());

            performInteractions();

            ecosystem.replenishResources();
            logger.info("Cycle {} completed.\n", i);
        }

        logger.info("Automated simulation ended.");
    }

    public void adjustEcosystemConditions() {
        double tempChange = Math.random() * 2 - 1;
        double humidityChange = Math.random() * 2 - 1;
        double waterChange = -2;

        ecosystem.getEcosystemResources().setTemperature(ecosystem.getEcosystemResources().getTemperature() + tempChange);
        ecosystem.getEcosystemResources().setHumidity(ecosystem.getEcosystemResources().getHumidity() + humidityChange);
        ecosystem.getEcosystemResources().setAvailableWater(ecosystem.getEcosystemResources().getAvailableWater() + waterChange);

        logger.info(ecosystem.getEcosystemResources().toString());
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
            plant.adaptToEnvironment(ecosystem.getEcosystemResources());
            if (plant.getHealth() == 0) {
                plantsToRemove.add(plant);
            }
        }

        plantsToRemove.forEach(plant -> plantRepository.remove(plant.getId()));
        animalsToRemove.forEach(animal -> animalRepository.remove(animal.getId()));
    }

    public String predictPopulationTrends() {
        StringBuilder predictions = new StringBuilder("Population Trends Prediction:\n");

        double availableWater = ecosystem.getEcosystemResources().getAvailableWater();
        double currentHumidity = ecosystem.getEcosystemResources().getHumidity();
        double currentTemperature = ecosystem.getEcosystemResources().getTemperature();


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
