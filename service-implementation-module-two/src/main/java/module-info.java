import com.swirlds.spi.implementation.two.AppleService;
import com.swirlds.spi.implementation.two.BananaService;
import com.swirlds.spi.implementation.two.BlueberriesService;
import com.swirlds.spi.implementation.two.CherryService;
import com.swirlds.spi.implementation.two.MelonService;
import com.swirlds.spi.services.ServiceWithId;
import com.swirlds.spi.services.ServiceWithName;

module com.swirlds.spi.implementation.two {
    requires com.swirlds.spi.services;
    requires static com.google.auto.service;

    provides ServiceWithName with AppleService, BananaService, BlueberriesService, CherryService, MelonService;
    provides ServiceWithId with AppleService, BananaService, BlueberriesService, CherryService, MelonService;
}