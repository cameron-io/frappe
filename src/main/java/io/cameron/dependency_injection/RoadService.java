package io.cameron.dependency_injection;

public class RoadService implements Service {

    private static RoadService instance;
    public String name;
    public int count = 0;

    private RoadService() {
        this.name = "Road Service";
    }

    public static RoadService getInstance() {
        synchronized (RoadService.class) {
            if (instance == null) {
                instance = new RoadService();
            }
            return instance;
        }
    }

    @Override
    public String getName() {
        return this.name;
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
