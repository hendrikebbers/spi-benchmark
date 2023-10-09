package com.swirlds.spi.use;

import com.swirlds.spi.services.ServiceWithName;
import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServiceWithNameUser {

    private static List<ServiceWithName> allServices = new CopyOnWriteArrayList<>();

    public static void loadAndUseServices() {
        ServiceLoader<ServiceWithName> serviceLoader = ServiceLoader.load(ServiceWithName.class);
        serviceLoader.stream().forEach(serviceProvider -> {
            ServiceWithName service = serviceProvider.get();
            allServices.add(service);
        });
    }

    public static void loadAndUseInstalledServices() {
        ServiceLoader<ServiceWithName> serviceLoader = ServiceLoader.loadInstalled(ServiceWithName.class);
        serviceLoader.stream().forEach(serviceProvider -> {
            ServiceWithName service = serviceProvider.get();
            allServices.add(service);
        });
    }

    public static void reload() {
        ServiceLoader<ServiceWithName> serviceLoader = ServiceLoader.load(ServiceWithName.class);
        serviceLoader.reload();
    }

    public static void reloadInstalled() {
        ServiceLoader<ServiceWithName> serviceLoader = ServiceLoader.loadInstalled(ServiceWithName.class);
        serviceLoader.reload();
    }

}
