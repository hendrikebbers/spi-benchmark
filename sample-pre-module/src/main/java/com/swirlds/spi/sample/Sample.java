package com.swirlds.spi.sample;

import com.swirlds.spi.use.ServiceWithIdUser;
import com.swirlds.spi.use.ServiceWithNameUser;

public class Sample {

    public static void main(String[] args) {
        System.out.println("Is this app running on module path? -> " + Sample.class.getModule().isNamed());
        ServiceWithNameUser.loadAndUseServices();
        ServiceWithIdUser.loadAndUseServices();
    }
}
