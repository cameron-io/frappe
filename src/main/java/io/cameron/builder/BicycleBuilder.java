package io.cameron.builder;

public class BicycleBuilder implements IBicycleBuilder {
    public String frame;
    public String wheels;
    public String tires;
	public String crankSet;
	public String handlebars;
    public int raceNumber;

    public BicycleBuilder(String frame, String wheels, String tires, String crankSet, String handlebars) {
        this.frame = frame;
        this.wheels = wheels;
        this.tires = tires;
        this.crankSet = crankSet;
        this.handlebars = handlebars;
    }

    @Override
    public BicycleBuilder setRaceNumber(int raceNumber) {
        this.raceNumber = raceNumber;
        return this;
    }

    public Bicycle build() {
        return new Bicycle(this);
    }
}