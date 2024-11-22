package io.cameron.dependency_injection;

public class Car {
    private CarType carType;

    /*
     * Car does not initialize the injected class. This leads to better separation of concerns.
     * Also, using the CarType interface allows us to easily test the application by mocking the
     * CarType, then binding the implementations at runtime rather than compile time.
     */
    public Car(CarType carType) {
        carType.registerCar();
        this.carType = carType;
    }

    public CarType getCarType() {
        return this.carType;
    }
}
