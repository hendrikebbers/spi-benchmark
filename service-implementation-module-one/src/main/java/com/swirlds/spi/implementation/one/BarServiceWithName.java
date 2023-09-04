package com.swirlds.spi.implementation.one;

import com.google.auto.service.AutoService;
import com.swirlds.spi.services.ServiceWithName;

@AutoService(ServiceWithName.class)
public class BarServiceWithName implements ServiceWithName {
    @Override
    public String getName() {
        return "Bar";
    }
}