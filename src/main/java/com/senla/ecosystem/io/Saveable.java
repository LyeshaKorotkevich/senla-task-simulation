package com.senla.ecosystem.io;

import java.io.IOException;
import java.util.List;

/**
 * Интерфейс для классов, которые могут сохранять и загружать данные.
 *
 * @param <T> тип данных, который будет сохраняться и загружаться
 */
public interface Saveable<T> {

    /**
     * Сохраняет список элементов в файл.
     *
     * @param items    список элементов для сохранения
     * @param filename имя файла, в который будут сохранены данные
     * @throws IOException если произошла ошибка ввода-вывода при сохранении
     */
    void save(List<T> items, String filename) throws IOException;

    /**
     * Загружает список элементов из файла.
     *
     * @param filename имя файла, из которого будут загружены данные
     * @return список загруженных элементов
     * @throws IOException если произошла ошибка ввода-вывода при загрузке
     */
    List<T> load(String filename) throws IOException;
}
