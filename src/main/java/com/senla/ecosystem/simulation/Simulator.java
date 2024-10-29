package com.senla.ecosystem.simulation;

import com.senla.ecosystem.model.animal.Animal;
import com.senla.ecosystem.model.animal.Herbivore;
import com.senla.ecosystem.model.animal.Predator;
import com.senla.ecosystem.model.plant.Plant;
import com.senla.ecosystem.repository.Repository;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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
        List<Plant> plantsToRemove = new ArrayList<>();
        List<Animal> animalsToRemove = new ArrayList<>();

        int interactions = Math.min(copyOfAnimals.size(), 5);

        for (int i = 0; i < interactions; i++) {
            Animal animal = copyOfAnimals.get((int) (Math.random() * copyOfAnimals.size()));
            if (animal instanceof Herbivore) {
                if (ecosystemResources.getHumidity() >= 20 && !copyOfPlants.isEmpty()) {
                    Plant plant = copyOfPlants.get((int) (Math.random() * copyOfPlants.size()));
                    animal.eat(plant, plantsToRemove);
                    plantsToRemove.add(plant);
                    logger.info("{} ate {}", animal.getName(), plant.getSpecies());
                } else {
                    logger.info("{} found no plants to eat or conditions are unfavorable.", animal.getName());
                }
            } else if (animal instanceof Predator) {
                List<Animal> herbivores = copyOfAnimals.stream()
                        .filter(a -> a instanceof Herbivore && a != animal)
                        .toList();
                if (!herbivores.isEmpty()) {
                    Animal prey = herbivores.get((int) (Math.random() * herbivores.size()));
                    animal.eat(prey, animalsToRemove);
                    animalsToRemove.add(prey);
                    logger.info("{} hunted {}", animal.getName(), prey.getName());
                } else {
                    logger.info("{} found no herbivores to hunt.", animal.getName());
                    if (Math.random() < 0.3) {
                        animalsToRemove.add(animal);
                        logger.info("{} has died from starvation.", animal.getName());
                    }
                }
            }
        }

        for (Plant plant : copyOfPlants) {
            plant.adaptToEnvironment(ecosystemResources);
            if (plant.getHealth() <= 0) {
                plantsToRemove.add(plant);
                logger.info("{} has died due to unfavorable conditions.", plant.getSpecies());
            }
        }

        for (Animal animal : copyOfAnimals) {
            if (animal instanceof Herbivore) {
                if (Math.random() < 0.1) {
                    animalsToRemove.add(animal);
                    logger.info("{} has died.", animal.getName());
                }
            }
        }

        plantsToRemove.forEach(this::removePlant);
        animalsToRemove.forEach(this::removeAnimal);
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
