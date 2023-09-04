package com.swirlds.spi.implementation.two;

import com.google.auto.service.AutoService;
import com.swirlds.spi.services.ServiceWithId;
import com.swirlds.spi.services.ServiceWithName;
import java.util.UUID;

@AutoService({ServiceWithId.class, ServiceWithName.class})
public final class BlueberriesService implements ServiceWithId, ServiceWithName {

    private final UUID uuid = UUID.randomUUID();

    @Override
    public UUID getId() {
        return uuid;
    }

    @Override
    public String getName() {
        return "Blueberries";
    }
}
