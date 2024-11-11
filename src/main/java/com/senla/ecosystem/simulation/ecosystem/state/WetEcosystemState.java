package com.senla.ecosystem.simulation.ecosystem.state;

import com.senla.ecosystem.simulation.ecosystem.EcosystemResources;
import lombok.ToString;

@ToString
public class WetEcosystemState implements EcosystemState {

    @Override
    public void adjustConditions(EcosystemResources ecosystemResources) {
        ecosystemResources.setTemperature(ecosystemResources.getTemperature() - 1);
        ecosystemResources.setHumidity(ecosystemResources.getHumidity() + 15);
        ecosystemResources.setAvailableWater(ecosystemResources.getAvailableWater() + 10);
        System.out.println("Ecosystem is wet. Water levels and humidity are high.");
    }
}
