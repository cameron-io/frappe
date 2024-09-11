package io.cameron.builder;

public class ComputerBuilder implements IComputerBuilder {
    public String CPU;
	public String RAM;
	public String HDD;
    public boolean discreteGraphicsCard;
	public boolean bluetoothCard;

    public ComputerBuilder(String cpu, String hdd, String ram) {
        this.CPU = cpu;
        this.RAM = ram;
        this.HDD = hdd;
    }

    @Override
    public ComputerBuilder setDiscreteGraphicsCard(boolean discreteGraphicsCard) {
        this.discreteGraphicsCard = discreteGraphicsCard;
        return this;
    }

    @Override
    public ComputerBuilder setBluetoothCard(boolean bluetoothCard) {
        this.bluetoothCard = bluetoothCard;
        return this;
    }

    public Computer build() {
        return new Computer(this);
    }
}