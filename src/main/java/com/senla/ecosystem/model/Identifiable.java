package com.senla.ecosystem.model;

/**
 * Интерфейс, представляющий объекты, имеющие уникальный идентификатор.
 */
public interface Identifiable {
    /**
     * Возвращает уникальный идентификатор объекта.
     *
     * @return уникальный идентификатор
     */
    int getId();
}
