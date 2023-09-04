package com.swirlds.spi.use;

import com.swirlds.spi.services.ServiceWithName;
import java.util.ServiceLoader;

public class ServiceWithNameUser {

    public static void loadAndUseServices() {
        ServiceLoader<ServiceWithName> serviceLoader = ServiceLoader.load(ServiceWithName.class);
        serviceLoader.stream().forEach(serviceProvider -> {
            ServiceWithName service = serviceProvider.get();
            System.out.println("ServiceWithName: " + service.getName() + " - provided by '" + service.getClass().getSimpleName() + "' loaded from module '" + service.getClass().getModule().getName() + "'");
        });
    }

    public static void loadAndUseInstalledServices() {
        ServiceLoader<ServiceWithName> serviceLoader = ServiceLoader.loadInstalled(ServiceWithName.class);
        serviceLoader.stream().forEach(serviceProvider -> {
            ServiceWithName service = serviceProvider.get();
            System.out.println("ServiceWithName: " + service.getName() + " - provided by '" + service.getClass().getSimpleName() + "' loaded from module '" + service.getClass().getModule().getName() + "'");
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
