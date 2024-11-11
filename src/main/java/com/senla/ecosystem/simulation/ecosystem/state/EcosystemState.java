package com.senla.ecosystem.simulation.ecosystem.state;

import com.senla.ecosystem.simulation.ecosystem.EcosystemResources;

public interface EcosystemState {
    void adjustConditions(EcosystemResources ecosystemResources);
}
