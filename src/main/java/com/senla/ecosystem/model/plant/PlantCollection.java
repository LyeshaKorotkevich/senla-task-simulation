package com.senla.ecosystem.model.plant;

import com.senla.ecosystem.model.Iterable;
import com.senla.ecosystem.model.Iterator;

import java.util.List;

public class PlantCollection implements Iterable<Plant> {
    private List<Plant> plants;

    public PlantCollection(List<Plant> plants) {
        this.plants = plants;
    }

    @Override
    public Iterator<Plant> createIterator() {
        return new PlantIterator(plants);
    }
}
