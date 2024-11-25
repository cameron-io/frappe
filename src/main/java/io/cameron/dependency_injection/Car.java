package io.cameron.dependency_injection;

public class Car {
    private Service service;

    /*
     * Car does not initialize the injected class. This leads to better separation of concerns.
     * Also, using the CarType interface allows us to easily test the application by mocking the
     * CarType, then binding the implementations at runtime rather than compile time.
     */
    public Car(Service service) {
        service.registerCar();
        this.service = service;
    }

    public Service getService() {
        return this.service;
    }
}
