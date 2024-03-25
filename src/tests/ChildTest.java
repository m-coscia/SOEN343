package src.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import src.components.Room;
import src.components.RoomType;
import src.logic.Child;

import java.util.ArrayList;

public class ChildTest {

    @Test
    public void testConstructor() {
        Room room = new Room(RoomType.BEDROOM, 1, 1, 1, new ArrayList<>());
        Child child = new Child("Alice", "alice123", "password123", room);

        assertEquals("Alice", child.getName());
        assertEquals("alice123", child.getUserName());
        assertEquals("password123", child.getPassword());
        assertEquals(room, child.getLocation());
    }

    @Test
    public void testSetUserName() {
        Room room = new Room(RoomType.KITCHEN, 1, 1, 1, new ArrayList<>());
        Child child = new Child("Bob", "bob456", "password456", room);

        child.setUserName("new_bob456");
        assertEquals("new_bob456", child.getUserName());
    }

    @Test
    public void testSetPassword() {
        Room room = new Room(RoomType.LIVINGROOM, 1, 1, 1, new ArrayList<>());
        Child child = new Child("Charlie", "charlie789", "oldpassword", room);

        child.setPassword("newpassword");
        assertEquals("newpassword", child.getPassword());
    }
}
