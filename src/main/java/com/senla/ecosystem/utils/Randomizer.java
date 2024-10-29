package com.senla.ecosystem.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

/**
 * Утилита для генерации случайных чисел и вероятностей.
 */
@UtilityClass
public class Randomizer {
    private static final Random random = new Random();

    /**
     * Генерирует случайное целое число в заданном диапазоне.
     *
     * @param min минимальное значение (включительно)
     * @param max максимальное значение (включительно)
     * @return случайное целое число от min до max
     */
    public static int getRandomInt(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min должен быть меньше или равен max");
        }
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Генерирует случайное дробное число в заданном диапазоне.
     *
     * @param min минимальное значение
     * @param max максимальное значение
     * @return случайное дробное число от min до max
     */
    public static double getRandomDouble(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException("min должен быть меньше или равен max");
        }
        return min + (max - min) * random.nextDouble();
    }

    /**
     * Определяет, произошла ли случайная "удача" на основе заданной вероятности.
     *
     * @param probability вероятность в диапазоне от 0.0 до 1.0
     * @return true, если "удача" произошла, иначе false
     */
    public static boolean chance(double probability) {
        if (probability < 0.0 || probability > 1.0) {
            throw new IllegalArgumentException("вероятность должна быть в диапазоне от 0.0 до 1.0");
        }
        return random.nextDouble() < probability;
    }
}
