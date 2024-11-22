package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.cameron.builder.Bicycle;
import io.cameron.builder.BicycleBuilder;
import io.cameron.builder.IBicycleBuilder;

public class BuilderTest {
    @Test
    public void bicycleBuilderTest() {
        String frame = "Specialized Diverge E5 Premium Aluminum";
        String wheels = "AXIS Elite Disc";
        String tires = "Specialized Pathfinder Sport, 700x38c";
        String crankSet = "Shimano Claris R200";
        String handlebars = "Specialized Shallow Drop, 6061, 70x125mm, 31.8mm clamp";

        IBicycleBuilder bicycleBuilder =
                new BicycleBuilder(frame, wheels, tires, crankSet, handlebars);

        Bicycle bicycle = bicycleBuilder.setRaceNumber(46).build();

        assertEquals(46, bicycle.raceNumber);
    }
}
