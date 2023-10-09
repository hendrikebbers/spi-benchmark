module com.swirlds.spi.implementation.thousands.one {
    requires com.swirlds.spi.services;
    requires java.compiler;
    requires com.google.auto.service;
    provides com.swirlds.spi.services.ServiceWithId with com.swirlds.spi.implementation.thousands.one.set0.Foo0ServiceWithId;
    provides com.swirlds.spi.services.ServiceWithName with com.swirlds.spi.implementation.thousands.one.set0.Foo0ServiceWithName;
}
