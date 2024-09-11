package io.cameron.builder;

public class Computer {
    public String CPU;
    public String RAM;
	public String HDD;
	public boolean discreteGraphicsCard;
	public boolean bluetoothCard;

    Computer(ComputerBuilder builder) {
        this.CPU = builder.CPU;
		this.HDD = builder.HDD;
		this.RAM = builder.RAM;
		this.discreteGraphicsCard = builder.discreteGraphicsCard;
		this.bluetoothCard = builder.bluetoothCard;
	}
}