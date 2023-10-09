module com.swirlds.spi.implementation.thousands.two {
    requires com.swirlds.spi.services;
    requires java.compiler;
    requires com.google.auto.service;
    provides com.swirlds.spi.services.ServiceWithId with com.swirlds.spi.implementation.thousands.two.set0.Bar0ServiceWithId;
    provides com.swirlds.spi.services.ServiceWithName with com.swirlds.spi.implementation.thousands.two.set0.Bar0ServiceWithName;
}
