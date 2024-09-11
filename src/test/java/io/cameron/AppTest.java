package io.cameron;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.cameron.builder.Computer;
import io.cameron.builder.ComputerBuilder;

public class AppTest {
    @Test
    public void computerBuilderTest() {
        Computer computer = new ComputerBuilder("AMD", "500 GB", "2 GB")
            .setDiscreteGraphicsCard(true)
			.setBluetoothCard(true)
            .build();
        assertTrue(computer.discreteGraphicsCard);
        assertTrue(computer.bluetoothCard);
    }
}
