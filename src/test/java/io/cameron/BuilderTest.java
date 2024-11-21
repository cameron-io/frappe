package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.cameron.builder.Bicycle;
import io.cameron.builder.BicycleBuilder;

public class BuilderTest {
    @Test
    public void bicycleBuilderTest() {
        Bicycle bicycle = new BicycleBuilder("Specialized Diverge E5 Premium Aluminum",
                "AXIS Elite Disc", "Specialized Pathfinder Sport, 700x38c",
                "Specialized Shallow Drop, 6061, 70x125mm, 31.8mm clamp", "Shimano Claris R200")
                        .setRaceNumber(46).build();
        assertEquals(46, bicycle.raceNumber);
    }
}
