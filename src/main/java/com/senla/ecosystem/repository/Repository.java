package com.senla.ecosystem.repository;

import com.senla.ecosystem.model.Identifiable;

import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для хранения объектов типа T.
 * Позволяет добавлять, удалять, обновлять и извлекать элементы по идентификатору.
 *
 * @param <T> тип элементов в репозитории
 */
public class Repository<T> {

    private final List<T> items = new ArrayList<>();

    /**
     * Возвращает все элементы из репозитория.
     *
     * @return список всех элементов
     */
    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    /**
     * Получает элемент из репозитория по его идентификатору.
     *
     * @param id уникальный идентификатор элемента
     * @return элемент с заданным идентификатором или null, если элемент не найден
     */
    public T getById(int id) {
        return items.stream()
                .filter(item -> item instanceof Identifiable && ((Identifiable) item).getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Добавляет элемент в репозиторий.
     *
     * @param item элемент, который нужно добавить
     */
    public void add(T item) {
        items.add(item);
    }

    /**
     * Обновляет существующий элемент в репозитории.
     *
     * @param updatedItem обновленный элемент
     */
    public void update(T updatedItem) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Identifiable) {
                if (((Identifiable) items.get(i)).getId() == ((Identifiable) updatedItem).getId()) {
                    items.set(i, updatedItem);
                    return;
                }
            }
        }
    }

    /**
     * Удаляет элемент из репозитория по его идентификатору.
     *
     * @param id уникальный идентификатор элемента, который нужно удалить
     */
    public void remove(int id) {
        items.removeIf(item -> item instanceof Identifiable && ((Identifiable) item).getId() == id);
    }
}
