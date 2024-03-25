package src.tests;

import org.junit.Test;
import src.components.Room;
import src.components.RoomType;
import src.logic.Profile;
import src.logic.Permissions;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ProfileTest {

    @Test
    public void testGetName() {
        Room room = new Room(RoomType.LIVINGROOM, 2, 3, 1, new ArrayList<>());
        Profile profile = new Profile("Alice", room);
        assertEquals("Alice", profile.getName());
    }

    @Test
    public void testSetName() {
        Room room = new Room(RoomType.BEDROOM, 1, 2, 1, new ArrayList<>());
        Profile profile = new Profile("Bob", room);
        profile.setName("Robert");
        assertEquals("Robert", profile.getName());
    }

    @Test
    public void testGetLocation() {
        Room room = new Room(RoomType.BATHROOM, 1, 1, 1, new ArrayList<>());
        Profile profile = new Profile("Charlie", room);
        assertEquals(room, profile.getLocation());
    }

    @Test
    public void testSetLocation() {
        Room room1 = new Room(RoomType.KITCHEN, 2, 2, 1, new ArrayList<>());
        Room room2 = new Room(RoomType.LIVINGROOM, 3, 3, 2, new ArrayList<>());
        Profile profile = new Profile("David", room1);
        profile.setRoom(room2);
        assertEquals(room2, profile.getLocation());
    }

    @Test
    public void testGetPermissions() {
        Room room = new Room(RoomType.BEDROOM, 1, 1, 1, new ArrayList<>());
        Permissions permissions = new Permissions(true, true, false, false, false);
        Profile profile = new Profile("Eve", room);
        profile.setPermissions(permissions);
        assertEquals(permissions, profile.getPermissions());
    }

    @Test
    public void testSetPermissions() {
        Room room = new Room(RoomType.GARAGE, 0, 1, 1, new ArrayList<>());
        Permissions permissions = new Permissions(false, false, true, true, false);
        Profile profile = new Profile("Frank", room);
        profile.setPermissions(permissions);
        assertEquals(permissions, profile.getPermissions());
    }

    @Test
    public void testToString() {
        Room room = new Room(RoomType.GARAGE, 1, 2, 1, new ArrayList<>());
        Permissions permissions = new Permissions(false, true, false, true, true);
        Profile profile = new Profile("Grace", room);
        profile.setPermissions(permissions);
        String expectedString = "Profile{name='Grace', permissions=" + permissions + ", located in room=" + room.getId() + '}';
        assertEquals(expectedString, profile.toString());
    }
}
