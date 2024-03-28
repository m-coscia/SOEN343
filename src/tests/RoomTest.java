package src.tests;

import org.junit.Test;
import src.components.Room;
import src.components.RoomType;
import src.logic.Profile;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class RoomTest {

    @Test
    public void testParameterizedConstructor() {
        ArrayList<Profile> users = new ArrayList<>();
        Room room = new Room(RoomType.LIVINGROOM, 2, 3, 1, users);
        assertEquals(RoomType.LIVINGROOM, room.getType());
        assertEquals(2, room.getNumWindows());
        assertEquals(3, room.getNumLights());
        assertEquals(1, room.getNumDoors());
        assertNotNull(room.getLights());
        assertNotNull(room.getWindows());
        assertNotNull(room.getDoors());
        assertEquals(users, room.getUsers());
    }

    @Test
    public void testIsInZone() {
        Room room = new Room(RoomType.BEDROOM, 1, 1, 1, new ArrayList<>());
        assertFalse(room.isInZone());
        room.setIsInZone(true);
        assertTrue(room.isInZone());
    }

    @Test
    public void testIsOccupied() {
        Room room = new Room(RoomType.KITCHEN, 1, 1, 1, new ArrayList<>());
        assertFalse(room.isOccupied());

        Profile john = new Profile("John", room); // Create a profile named John located in the kitchen
        ArrayList<Profile> users = new ArrayList<>();
        users.add(john); // Add John to the list of users in the kitchen
        room.setUsers(users); // Assign users to the kitchen
        assertTrue(room.isOccupied()); // Check if the kitchen is occupied
    }

    @Test
    public void testSetAwayMode() {
        Room room = new Room(RoomType.BATHROOM, 1, 1, 1, new ArrayList<>());
        assertFalse(room.isAwayMode());
        room.setAwayMode(true);
        assertTrue(room.isAwayMode());
    }

    @Test
    public void testGenerateUniqueId() {
        Room room1 = new Room(RoomType.BEDROOM, 1, 1, 1, new ArrayList<>());
        Room room2 = new Room(RoomType.LIVINGROOM, 2, 2, 2, new ArrayList<>());
        assertNotEquals(room1.getId(), room2.getId());
    }
}
