import com.swirlds.spi.implementation.one.BarServiceWithName;
import com.swirlds.spi.implementation.one.FooServiceWithName;
import com.swirlds.spi.implementation.one.SimpleServiceWithId;

module com.swirlds.spi.implementation.one {
    requires com.swirlds.spi.services;
    requires static com.google.auto.service;

    provides com.swirlds.spi.services.ServiceWithId
        with SimpleServiceWithId;
    provides com.swirlds.spi.services.ServiceWithName
        with FooServiceWithName,
                BarServiceWithName;
}