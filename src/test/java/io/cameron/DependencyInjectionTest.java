package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import io.cameron.dependency_injection.Car;
import io.cameron.dependency_injection.CarType;
import io.cameron.dependency_injection.RacingCarType;
import io.cameron.dependency_injection.RoadCarType;

public class DependencyInjectionTest {
    @Test
    public void carTypesTest() {
        CarType racingCarType = RacingCarType.getInstance();
        CarType roadCarType = RoadCarType.getInstance();

        Car roadCar = new Car(roadCarType);
        assertEquals("Road Car", roadCar.getCarType().getName());

        Car racingCar1 = new Car(racingCarType);
        Car racingCar2 = new Car(racingCarType);
        assertEquals("Racing Car", racingCar1.getCarType().getName());
        assertEquals(2, racingCar2.getCarType().getCount());
    }
}
