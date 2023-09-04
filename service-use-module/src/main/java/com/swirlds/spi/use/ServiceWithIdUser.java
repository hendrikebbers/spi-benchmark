package com.swirlds.spi.use;

import com.swirlds.spi.services.ServiceWithId;
import com.swirlds.spi.services.ServiceWithName;
import java.util.ServiceLoader;

public class ServiceWithIdUser {

    public static void loadAndUseServices() {
        ServiceLoader<ServiceWithId> serviceLoader = ServiceLoader.load(ServiceWithId.class);
        serviceLoader.stream().forEach(serviceProvider -> {
            ServiceWithId service = serviceProvider.get();
            System.out.println("ID: " + service.getId() + " - provided by '" + service.getClass().getSimpleName() + "' loaded from module '" + service.getClass().getModule().getName() + "'");
        });
    }

    public static void loadAndUseInstalledServices() {
        ServiceLoader<ServiceWithId> serviceLoader = ServiceLoader.loadInstalled(ServiceWithId.class);
        serviceLoader.stream().forEach(serviceProvider -> {
            ServiceWithId service = serviceProvider.get();
            System.out.println("ID: " + service.getId() + " - provided by '" + service.getClass().getSimpleName() + "' loaded from module '" + service.getClass().getModule().getName() + "'");
        });
    }

    public static void reload() {
        ServiceLoader<ServiceWithId> serviceLoader = ServiceLoader.load(ServiceWithId.class);
        serviceLoader.reload();
    }

    public static void reloadInstalled() {
        ServiceLoader<ServiceWithId> serviceLoader = ServiceLoader.loadInstalled(ServiceWithId.class);
        serviceLoader.reload();
    }

}
