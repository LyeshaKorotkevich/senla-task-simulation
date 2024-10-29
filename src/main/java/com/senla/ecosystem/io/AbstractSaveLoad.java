package com.senla.ecosystem.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактный класс для реализации сохранения и загрузки сериализуемых объектов.
 *
 * @param <T> тип данных, который будет сохраняться и загружаться
 */
public abstract class AbstractSaveLoad<T extends Serializable> implements Saveable<T> {

    /**
     * Сохраняет список элементов в файл.
     *
     * @param items    список элементов для сохранения
     * @param filename имя файла, в который будут сохранены данные
     * @throws IOException если произошла ошибка ввода-вывода при сохранении
     */
    @Override
    public void save(List<T> items, String filename) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            for (T item : items) {
                writer.write(serializeItem(item));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении в файл: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Загружает список элементов из файла.
     *
     * @param filename имя файла, из которого будут загружены данные
     * @return список загруженных элементов
     * @throws IOException если произошла ошибка ввода-вывода при загрузке
     */
    @Override
    public List<T> load(String filename) throws IOException {
        List<T> items = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                items.add(deserializeItem(line));
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке из файла: " + e.getMessage());
            throw e;
        }
        return items;
    }

    /**
     * Сериализует элемент в строку.
     *
     * @param item элемент, который нужно сериализовать
     * @return строковое представление элемента
     */
    protected abstract String serializeItem(T item);

    /**
     * Десериализует элемент из строки.
     *
     * @param line строка, представляющая элемент
     * @return десериализованный элемент
     */
    protected abstract T deserializeItem(String line);
}
