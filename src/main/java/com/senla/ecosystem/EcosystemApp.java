package com.senla.ecosystem;

import com.senla.ecosystem.controller.EcosystemController;
import com.senla.ecosystem.data.DataManager;
import com.senla.ecosystem.io.impl.AnimalSaveLoad;
import com.senla.ecosystem.io.impl.PlantSaveLoad;
import com.senla.ecosystem.model.animal.Animal;
import com.senla.ecosystem.model.plant.Plant;
import com.senla.ecosystem.repository.Repository;
import com.senla.ecosystem.utils.AppConstants;
import com.senla.ecosystem.utils.LoggerManager;
import com.senla.ecosystem.view.ConsoleView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EcosystemApp {

    public static void main(String[] args) {

        AnimalSaveLoad animalSaveLoad = new AnimalSaveLoad();
        PlantSaveLoad plantSaveLoad = new PlantSaveLoad();
        DataManager dataManager = new DataManager(animalSaveLoad, plantSaveLoad);
        ConsoleView view = new ConsoleView();

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Create new simulation \n2. Load simulation");
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();

            Repository<Animal> animalRepository = new Repository<>();
            Repository<Plant> plantRepository = new Repository<>();
            EcosystemController controller = new EcosystemController(view, animalRepository, plantRepository);

            if (choice == 1) {
                System.out.println("Creating new simulation...");
                String timestamp = new SimpleDateFormat(AppConstants.LOG_DATE_FORMAT).format(new Date());
                String logFileName = AppConstants.LOG_DIR + AppConstants.LOG_FILE_PREFIX + timestamp + AppConstants.LOG_FILE_EXTENSION;
                System.setProperty("log.file.name", logFileName);
                LoggerManager.setupLogger(logFileName);
            } else if (choice == 2) {
                dataManager.loadSimulationData(animalRepository, plantRepository);
            } else {
                System.out.println("Wrong choice, exit.");
                return;
            }

            controller.run();
            dataManager.saveSimulationData(animalRepository, plantRepository);
        } else {
            System.out.println("Ошибка: ожидалось ввод целого числа.");
            return;
        }

        scanner.close();
    }
}
