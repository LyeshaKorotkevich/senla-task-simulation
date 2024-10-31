package com.senla.ecosystem.utils;

import lombok.experimental.UtilityClass;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class AppConstants {
    // Логи
    public static final String LOG_DIR = "logs" + java.io.File.separator;
    public static final String LOG_FILE_PREFIX = "simulation_log_";
    public static final String LOG_FILE_EXTENSION = ".txt";
    public static final String LOG_DATE_FORMAT = "yyyyMMdd_HHmm";

    // Экосистема
    public static final double RESOURCE_WATER = 25.0;
    public static final double RESOURCE_FOOD = 50.0;
    public static final double RESOURCE_SHELTER = 100.0;

    // Файлы данных
    public static String getAnimalDataPath() {
        return Paths.get("src", "main", "resources", "simulation" + getCurrentTimestamp(), "Animal.txt").toString();
    }

    public static String getPlantDataPath() {
        return Paths.get("src", "main", "resources", "simulation" + getCurrentTimestamp(), "Plant.txt").toString();
    }

    private static String getCurrentTimestamp() {
        return new SimpleDateFormat(LOG_DATE_FORMAT).format(new Date());
    }
}
