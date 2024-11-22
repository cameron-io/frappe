package io.cameron.dependency_injection;

public class RoadCarType implements CarType {
    private static RoadCarType instance;
    public int count = 0;

    private RoadCarType() {}

    public static RoadCarType getInstance() {
        if (instance == null) {
            instance = new RoadCarType();
        }
        return instance;
    }

    @Override
    public String getName() {
        return "Road Car";
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void registerCar() {
        this.count += 1;
    }
}
