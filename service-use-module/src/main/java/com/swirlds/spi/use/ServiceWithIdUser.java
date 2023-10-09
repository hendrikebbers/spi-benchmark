package com.swirlds.spi.use;

import com.swirlds.spi.services.ServiceWithId;
import com.swirlds.spi.services.ServiceWithName;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServiceWithIdUser {

    private static List<ServiceWithId> allServices = new CopyOnWriteArrayList<>();


    public static void loadAndUseServices() {
        ServiceLoader<ServiceWithId> serviceLoader = ServiceLoader.load(ServiceWithId.class);
        serviceLoader.stream().forEach(serviceProvider -> {
            ServiceWithId service = serviceProvider.get();
            allServices.add(service);
        });
    }

    public static void loadAndUseInstalledServices() {
        ServiceLoader<ServiceWithId> serviceLoader = ServiceLoader.loadInstalled(ServiceWithId.class);
        serviceLoader.stream().forEach(serviceProvider -> {
            ServiceWithId service = serviceProvider.get();
            allServices.add(service);
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
