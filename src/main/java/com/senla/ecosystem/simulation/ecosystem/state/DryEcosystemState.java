package com.senla.ecosystem.simulation.ecosystem.state;

import com.senla.ecosystem.simulation.ecosystem.EcosystemResources;
import lombok.ToString;

@ToString
public class DryEcosystemState implements EcosystemState {

    @Override
    public void adjustConditions(EcosystemResources ecosystemResources) {
        ecosystemResources.setTemperature(ecosystemResources.getTemperature() + 2);
        ecosystemResources.setHumidity(ecosystemResources.getHumidity() - 10);
        ecosystemResources.setAvailableWater(ecosystemResources.getAvailableWater() - 5);
        System.out.println("Ecosystem is dry. Water level and humidity are low.");
    }
}
