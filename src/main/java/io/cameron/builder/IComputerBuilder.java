package io.cameron.builder;

public interface IComputerBuilder {
    IComputerBuilder setDiscreteGraphicsCard(boolean discreteGraphicsCard);
    IComputerBuilder setBluetoothCard(boolean bluetoothCard);
    Computer build();
}