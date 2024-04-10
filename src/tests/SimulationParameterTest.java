//package src.tests;
//
//import org.junit.Before;
//import org.junit.Test;
//import src.Observer.Events.TimeEvent;
//import src.Observer.Events.UserEvent;
//import src.components.Clock;
//import src.components.Room;
//import src.components.Zone;
//import src.logic.*;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.sql.Time;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import src.Observer.*;
//
//import static org.junit.Assert.*;
//
//public class SimulationParameterTest {
//    private SimulationParameter simulationParameter;
//    private String layoutFile = "houseLayoutFile.txt";
//    private String tempFile = "src/UI/july_temp.csv";
//    private LocalDate date = LocalDate.of(2024, 3, 28);
//    private LocalTime time = LocalTime.of(12, 0);
//    private double insideTemp = 25.0;
//    private double outsideTemp = 20.0;
//    private Login loggedInUser;
//
//
//    @Before
//    public void setUp() throws FileNotFoundException {
//        loggedInUser = new Login(new Parent("John", "john123", "password", new Room()));
//        simulationParameter = new SimulationParameter(layoutFile, tempFile, date, time, insideTemp, outsideTemp, loggedInUser);
//        TimeObserver timeObserver = new TimeObserver();
//        ActionObserver actionObserver = new ActionObserver();
//        simulationParameter.attachActionObserver(actionObserver);
//        simulationParameter.attachTimeObserver(timeObserver);
//
//    }
//
//    @Test
//    public void testGetClock() {
//        Clock clock = simulationParameter.getClock();
//        assertEquals(time, clock.getTime());
//        assertEquals(date, clock.getDate());
//    }
//
//    @Test
//    public void testNotifyAccountObserver() {
//        // Not testing the actual notification mechanism, just checking if method is invoked without errors
//        try {
//            simulationParameter.notifyAccountObserver(new UserEvent("add", new Parent("Alice", "alice123", "password", new Room())));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testNotifyTimeObserver() {
//        // Not testing the actual notification mechanism, just checking if method is invoked without errors
//        try {
//            simulationParameter.notifyTimeObserver(new TimeEvent("temperature", simulationParameter));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetWeatherData() {
//        Map<String, Double> weatherData = simulationParameter.getWeatherData();
//        assertTrue(weatherData instanceof HashMap);
//    }
//
//
//    @Test
//    public void testGetLoggedIn() {
//        assertEquals(loggedInUser.getCurrentUser(), simulationParameter.getLoggedIn());
//    }
//
//    @Test
//    public void testUploadTempFile() {
//        // Test if uploading temp file adds data to weatherData map
//        simulationParameter.uploadTempFile(tempFile);
//        assertFalse(simulationParameter.getWeatherData().isEmpty());
//    }
//
//    @Test
//    public void testStartSimulation() throws IOException {
//        // Test if starting simulation starts the clock
//        simulationParameter.startSimulation();
//        assertTrue(simulationParameter.getClock().isRunning().get());
//    }
//
//    @Test
//    public void testStopSimulation() throws IOException {
//        // Test if stopping simulation pauses the clock
//        simulationParameter.startSimulation();
//        simulationParameter.stopSimulation();
//        assertFalse(simulationParameter.getClock().isRunning().get());
//    }
//
//
//    @Test
//    public void testSetZoneTemperature() throws IOException {
//        // Test if setting temperature of a zone updates correctly
//        ArrayList<Room> rooms = new ArrayList<>();
//        Room room = new Room();
//        rooms.add(room);
//        Zone z = new Zone(rooms, 20.0, "HEATING");
//        simulationParameter.addZone(z);
//        simulationParameter.setZoneTemperature(25.0, z);
//        assertEquals(25.0, z.getTemperature(), 0.0);
//    }
//
//    @Test
//    public void testGetRoomTemp() throws IOException {
//        // Test if getting room temperature returns correct temperature
//        ArrayList<Room> rooms = new ArrayList<>();
//        Room room = new Room();
//        rooms.add(room);
//        Zone zone = new Zone(rooms, 20.0, "HEATING");
//        simulationParameter.addZone(zone);
//        assertEquals(20.0, simulationParameter.getRoomTemp(room), 0.0);
//    }
//}
