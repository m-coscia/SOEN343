import org.junit.Test;
import components.Room;
import components.RoomType;
import components.Zone;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class ZoneTest {

    @Test
    public void testConstructor() {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(RoomType.LIVINGROOM, 2, 2, 1, new ArrayList<>()));
        rooms.add(new Room(RoomType.KITCHEN, 1, 1, 1, new ArrayList<>()));
        Zone zone = new Zone(rooms, 25.0, "HEATING");

        assertEquals(25.0, zone.getTemperature(), 0.01);
        assertEquals("HEATING", zone.getType());
        assertEquals(2, zone.getRooms().size());
    }

    @Test
    public void testSetTypeValid() {
        ArrayList<Room> rooms = new ArrayList<>();
        Zone zone = new Zone(rooms, 22.0, "COOLING");

        zone.setType("HEATING");
        assertEquals("HEATING", zone.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTypeInvalid() {
        ArrayList<Room> rooms = new ArrayList<>();
        Zone zone = new Zone(rooms, 22.0, "COOLING");

        // Attempt to set an invalid type
        zone.setType("INVALID_TYPE");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidType() {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(RoomType.LIVINGROOM, 2, 2, 1, new ArrayList<>()));
        rooms.add(new Room(RoomType.KITCHEN, 1, 1, 1, new ArrayList<>()));

        // Attempt to create a Zone with an invalid type
        Zone zone = new Zone(rooms, 25.0, "INVALID_TYPE");
    }

}
