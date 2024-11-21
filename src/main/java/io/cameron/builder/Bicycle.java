package io.cameron.builder;

public class Bicycle {
    public String frame;
    public String wheels;
    public String tires;
    public String handlebars;
	public String crankSet;
    // Not required
    public int raceNumber;

    Bicycle(BicycleBuilder builder) {
        this.frame = builder.frame;
        this.wheels = builder.wheels;
        this.tires = builder.wheels;
		this.handlebars = builder.handlebars;
		this.crankSet = builder.crankSet;
        this.raceNumber = builder.raceNumber;
	}
}