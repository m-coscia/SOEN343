package src.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import src.components.*;
import src.logic.*;

import java.io.File;
import java.util.ArrayList;

public class DataBaseTest {
    private DataBase db;

    @Before
    public void setUp() {
        db = DataBase.getDataBase();
        db.setRooms(new ArrayList<>()); // Clear existing rooms for testing
        db.getProfiles().clear(); // Clear existing profiles for testing
    }

    @Test
    public void testAddAndDeleteAccount() {
        Profile profile = new Profile("Alice", null);
        db.addAccount(profile);

        assertTrue(db.profileExists(profile));
        assertEquals(1, db.getProfiles().size());

        db.deleteAccount(profile);

        assertFalse(db.profileExists(profile));
        assertEquals(0, db.getProfiles().size());
    }

    @Test
    public void testAddAndDeleteRoom() {
        Room room = new Room(RoomType.BEDROOM, 1, 1, 1, new ArrayList<>());
        db.addRoom(room);

        assertEquals(1, db.getRooms().size());

        db.deleteRoom(room);

        assertEquals(0, db.getRooms().size());
    }

    @Test
    public void testFindProfile() {
        Profile profile = new Profile("Alice", null);
        db.addAccount(profile);

        assertEquals(profile, db.findProfile("Alice"));
    }

    @Test
    public void testFindRoom() {
        Room room = new Room(RoomType.BEDROOM, 1, 1, 1, new ArrayList<>());
        db.addRoom(room);

        assertEquals(room, db.findRoom(room.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidZoneType() {
        new Zone(new ArrayList<>(), 25.0, "INVALID");
    }

    @Test
    public void testPrintAllProfiles() {
        Profile profile1 = new Profile("Alice", null);
        Profile profile2 = new Profile("Bob", null);

        db.addAccount(profile1);
        db.addAccount(profile2);

        db.printAllProfiles(); // Prints profiles to console
    }

    @Test
    public void testPrintAllRooms() {
        Room room1 = new Room(RoomType.BEDROOM, 1, 1, 1, new ArrayList<>());
        Room room2 = new Room(RoomType.LIVINGROOM, 2, 2, 2, new ArrayList<>());

        db.addRoom(room1);
        db.addRoom(room2);

        db.printAllRooms(); // Prints rooms to console
    }
}
