package com.swirlds.spi.implementation.one;

import com.google.auto.service.AutoService;
import com.swirlds.spi.services.ServiceWithId;
import java.util.UUID;

@AutoService(ServiceWithId.class)
public class SimpleServiceWithId implements ServiceWithId {

    private final UUID uuid = UUID.randomUUID();

    @Override
    public UUID getId() {
        return uuid;
    }
}
