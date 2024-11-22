package io.cameron.dependency_injection;

public class RacingCarType implements CarType {
    private static RacingCarType instance;
    public int count = 0;

    private RacingCarType() {}

    public static RacingCarType getInstance() {
        if (instance == null) {
            instance = new RacingCarType();
        }
        return instance;
    }

    @Override
    public String getName() {
        return "Racing Car";
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
