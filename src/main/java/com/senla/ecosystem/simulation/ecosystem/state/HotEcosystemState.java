package com.senla.ecosystem.simulation.ecosystem.state;

import com.senla.ecosystem.simulation.ecosystem.EcosystemResources;
import lombok.ToString;

@ToString
public class HotEcosystemState implements EcosystemState {

    @Override
    public void adjustConditions(EcosystemResources ecosystemResources) {
        ecosystemResources.setTemperature(ecosystemResources.getTemperature() + 5);
        ecosystemResources.setHumidity(ecosystemResources.getHumidity() - 5);
        ecosystemResources.setAvailableWater(ecosystemResources.getAvailableWater() - 10);
        System.out.println("Ecosystem is hot. Temperature is high and water levels are low.");
    }
}
