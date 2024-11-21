package io.cameron.builder;

public interface IBicycleBuilder {
    IBicycleBuilder setRaceNumber(int raceNumber);
    Bicycle build();
}