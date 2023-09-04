module com.swirlds.spi.use {
    exports com.swirlds.spi.use;
    requires com.swirlds.spi.services;

    uses com.swirlds.spi.services.ServiceWithId;
    uses com.swirlds.spi.services.ServiceWithName;
}