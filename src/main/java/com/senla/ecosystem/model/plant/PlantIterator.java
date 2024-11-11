package com.senla.ecosystem.model.plant;

import com.senla.ecosystem.model.Iterator;

import java.util.List;

public class PlantIterator implements Iterator<Plant> {
    private List<Plant> plants;
    private int currentIndex = 0;

    public PlantIterator(List<Plant> plants) {
        this.plants = plants;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < plants.size();
    }

    @Override
    public Plant next() {
        return hasNext() ? plants.get(currentIndex++) : null;
    }
}
