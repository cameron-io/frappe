package io.cameron.builder;

public class Computer {
	private String HDD;
	private String RAM;
	private boolean isGraphicsCardEnabled;
	private boolean isBluetoothEnabled;

    Computer(ComputerBuilder builder) {
		this.HDD = builder.HDD;
		this.RAM = builder.RAM;
		this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
		this.isBluetoothEnabled = builder.isBluetoothEnabled;
	}

	public String getHDD() {
        return HDD;
    }
	public String getRAM() {
        return RAM;
    }
	public boolean isGraphicsCardEnabled() {
        return isGraphicsCardEnabled;
    }
	public boolean isBluetoothEnabled() {
        return isBluetoothEnabled;
    }
}