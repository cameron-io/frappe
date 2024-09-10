package io.cameron.builder;

public interface IComputerBuilder {
    IComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled);
    IComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled);
    Computer build();
}