package io.cameron.dependency_injection;

public class RacingService implements Service {

    private static RacingService instance;
    public String name;
    public int count = 0;

    private RacingService() {
        this.name = "Racing Service";
    }

    public static RacingService getInstance() {
        synchronized (RacingService.class) {
            if (instance == null) {
                instance = new RacingService();
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
