package com.senla.ecosystem.data;

import com.senla.ecosystem.io.impl.AnimalSaveLoad;
import com.senla.ecosystem.io.impl.PlantSaveLoad;
import com.senla.ecosystem.model.animal.Animal;
import com.senla.ecosystem.model.plant.Plant;
import com.senla.ecosystem.repository.Repository;
import com.senla.ecosystem.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Класс DataManager отвечает за управление загрузкой и сохранением данных симуляции
 * как для животных, так и для растений. Он взаимодействует с репозиториями и
 * обрабатывает операции с файлами для сохранения или получения состояния симуляции.
 */
public class DataManager {
    private static final Logger logger = LoggerFactory.getLogger(DataManager.class);
    private final AnimalSaveLoad animalSaveLoad;
    private final PlantSaveLoad plantSaveLoad;

    /**
     * Конструктор DataManager с указанными реализациями AnimalSaveLoad и PlantSaveLoad.
     *
     * @param animalSaveLoad реализация AnimalSaveLoad, используемая для сохранения/загрузки животных
     * @param plantSaveLoad реализация PlantSaveLoad, используемая для сохранения/загрузки растений
     */
    public DataManager(AnimalSaveLoad animalSaveLoad, PlantSaveLoad plantSaveLoad) {
        this.animalSaveLoad = animalSaveLoad;
        this.plantSaveLoad = plantSaveLoad;
    }

    /**
     * Загружает данные симуляции из текстовых файлов в предоставленные репозитории.
     * Запрашивает у пользователя выбор файла для загрузки данных животных и растений.
     *
     * @param animalRepository репозиторий, в который будут сохранены загруженные данные животных
     * @param plantRepository репозиторий, в который будут сохранены загруженные данные растений
     */
    public void loadSimulationData(Repository<Animal> animalRepository, Repository<Plant> plantRepository) {
        try {
            File baseFolder = new File("src/main/resources/");
            File[] directories = baseFolder.listFiles(File::isDirectory);

            if (directories != null && directories.length > 0) {
                System.out.println("Select a directory to load the simulation:");
                for (int i = 0; i < directories.length; i++) {
                    System.out.println((i + 1) + ". " + directories[i].getName());
                }
                Scanner scanner = new Scanner(System.in);
                int dirChoice = scanner.nextInt();

                if (dirChoice >= 1 && dirChoice <= directories.length) {
                    File chosenDirectory = directories[dirChoice - 1];
                    String animalFilePath = chosenDirectory.getPath() + "/Animal.txt";
                    String plantFilePath = chosenDirectory.getPath() + "/Plant.txt";

                    File animalFile = new File(animalFilePath);
                    File plantFile = new File(plantFilePath);

                    if (animalFile.exists() && plantFile.exists()) {
                        List<Animal> loadedAnimals = animalSaveLoad.load(animalFilePath);
                        List<Plant> loadedPlants = plantSaveLoad.load(plantFilePath);

                        loadedAnimals.forEach(animalRepository::add);
                        loadedPlants.forEach(plantRepository::add);
                        System.out.println("Simulation loaded from directory: " + chosenDirectory.getName());
                        logger.info("Simulation loaded from directory: " + chosenDirectory.getPath());
                    } else {
                        System.out.println("Animal.txt and Plant.txt files were not found in the selected directory.");
                        logger.warn("Animal.txt or Plant.txt files are missing in directory: " + chosenDirectory.getPath());
                    }
                } else {
                    System.out.println("Invalid directory selection.");
                    logger.warn("User selected an invalid directory for loading simulation.");
                }
            } else {
                System.out.println("No available simulations found.");
                logger.warn("No simulation directories found.");
            }
        } catch (IOException e) {
            logger.error("Error loading simulation data", e);
        }
    }

    /**
     * Сохраняет текущее состояние симуляции в текстовые файлы.
     * Имена файлов формируются с использованием временной метки для обеспечения уникальности.
     *
     * @param animalRepository репозиторий, содержащий текущие данные животных для сохранения
     * @param plantRepository репозиторий, содержащий текущие данные растений для сохранения
     */
    public void saveSimulationData(Repository<Animal> animalRepository, Repository<Plant> plantRepository) {
        try {
            String animalFileName = AppConstants.getAnimalDataPath();
            String plantFileName = AppConstants.getPlantDataPath();

            File animalFile = new File(animalFileName);
            File plantFile = new File(plantFileName);
            animalFile.getParentFile().mkdirs();
            plantFile.getParentFile().mkdirs();

            animalSaveLoad.save(animalRepository.getAll(), animalFileName);
            plantSaveLoad.save(plantRepository.getAll(), plantFileName);

            System.out.println("Simulation saved as " + animalFileName + " and " + plantFileName);
            logger.info("Simulation saved as " + animalFileName + " and " + plantFileName);
        } catch (IOException e) {
            logger.error("Error saving simulation data", e);
        }
    }

}
