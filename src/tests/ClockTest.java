package src.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import src.components.Clock;

public class ClockTest {
    private Clock clock;

    @Before
    public void setUp() {
        clock = new Clock();
    }

    @After
    public void tearDown() {
        clock.shutdown();
    }

    @Test
    public void testInitialDate() {
        assertEquals(LocalDate.now(), clock.getDate());
    }

    @Test
    public void testInitialTime() {
        assertEquals(LocalTime.now().getHour(), clock.getTime().getHour());
        assertEquals(LocalTime.now().getMinute(), clock.getTime().getMinute());
    }

    @Test
    public void testSetTime() {
        LocalTime newTime = LocalTime.of(12, 30);
        clock.setTime(newTime);
        assertEquals(newTime, clock.getTime());
    }

    @Test
    public void testSetDate() {
        LocalDate newDate = LocalDate.of(2024, 3, 25);
        clock.setDate(newDate);
        assertEquals(newDate, clock.getDate());
    }

    @Test
    public void testStart() throws InterruptedException {
        clock.start();
        Thread.sleep(1000); // Sleep for 1 second to allow clock to start
        assertTrue(clock.isRunning().get());
    }

    @Test
    public void testPause() throws InterruptedException {
        clock.start();
        Thread.sleep(1000); // Sleep for 1 second to allow clock to start
        clock.pause();
        assertFalse(clock.isRunning().get());
    }

    @Test
    public void testChangeSpeed() throws InterruptedException {
        clock.start();
        clock.changeSpeed(2);
        assertEquals(2.0, clock.getSpeedMultiplier(), 0.01);
    }

    @Test
    public void testShutdown() {
        clock.shutdown();
        assertFalse(clock.isRunning().get());
    }
}
