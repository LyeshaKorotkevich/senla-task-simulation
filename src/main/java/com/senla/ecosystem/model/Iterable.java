package com.senla.ecosystem.model;

public interface Iterable<T> {
    Iterator<T> createIterator();
}
