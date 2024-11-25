package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import io.cameron.dependency_injection.Car;
import io.cameron.dependency_injection.Service;
import io.cameron.dependency_injection.RacingService;
import io.cameron.dependency_injection.RoadService;

public class DependencyInjectionTest {
    @Test
    public void carServiceTest() {
        Service racingService = RacingService.getInstance();
        Service roadService = RoadService.getInstance();

        Car roadCar = new Car(roadService);
        assertEquals("Road Service", roadCar.getService().getName());

        Car racingCar1 = new Car(racingService);
        Car racingCar2 = new Car(racingService);
        assertEquals("Racing Service", racingCar1.getService().getName());
        assertEquals(2, racingCar2.getService().getCount());
    }
}
