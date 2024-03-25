package src.tests;

import org.junit.Test;
import src.components.Lights;

import static org.junit.Assert.*;

public class LightsTest {

    @Test
    public void testSwitchLightsOn() {
        Lights lights = new Lights();
        lights.switchLightsOn();
        assertTrue(lights.getSwitchedOn());
    }

    @Test
    public void testSwitchLightsOff() {
        Lights lights = new Lights();
        lights.switchLightsOn(); // Turn on lights first
        lights.switchLightsOff();
        assertFalse(lights.getSwitchedOn());
    }

    @Test
    public void testIsAutoMode() {
        Lights lights = new Lights();
        assertFalse(lights.getIsAutoMode());
    }

    @Test
    public void testSetIsAutoMode() {
        Lights lights = new Lights();
        lights.setIsAutoMode(true);
        assertTrue(lights.getIsAutoMode());
    }
}
