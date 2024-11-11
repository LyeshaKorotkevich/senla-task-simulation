package com.senla.ecosystem.simulation.ecosystem;

import com.senla.ecosystem.simulation.ecosystem.state.DryEcosystemState;
import com.senla.ecosystem.simulation.ecosystem.state.EcosystemState;
import com.senla.ecosystem.simulation.ecosystem.state.HotEcosystemState;
import com.senla.ecosystem.simulation.ecosystem.state.WetEcosystemState;
import com.senla.ecosystem.utils.Randomizer;
import lombok.Data;

@Data
public class Ecosystem {
    private EcosystemState currentState;
    private EcosystemResources ecosystemResources;

    public Ecosystem(EcosystemResources ecosystemResources) {
        this.ecosystemResources = ecosystemResources;
        this.currentState = new WetEcosystemState();
    }

    public void adjustConditions() {
        currentState.adjustConditions(ecosystemResources);
    }

    public void changeState() {
        EcosystemState[] states = new EcosystemState[]{
                new DryEcosystemState(),
                new WetEcosystemState(),
                new HotEcosystemState()
        };

        int randomIndex = Randomizer.getRandomInt(0, 2);
        currentState = states[randomIndex];
    }

    public void replenishResources() {
        ecosystemResources.setAvailableWater(ecosystemResources.getAvailableWater() + 50);
    }
}

