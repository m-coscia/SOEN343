package src.tests;

import org.junit.Before;
import org.junit.Test;
import src.components.Room;
import src.logic.Parent;

import static org.junit.Assert.*;

public class ParentTest {
    private Parent parent;
    private final String name = "John";
    private final String userName = "john123";
    private final String password = "password";
    private Room room;

    @Before
    public void setUp() {
        room = new TestRoom(); // Using a simple test implementation of the Room class
        parent = new Parent(name, userName, password, room);
    }

    @Test
    public void testSetUserName() {
        String newUserName = "newJohn";
        parent.setUserName(newUserName);
        assertEquals(newUserName, parent.getUserName());
    }

    @Test
    public void testSetPassword() {
        String newPassword = "newPassword";
        parent.setPassword(newPassword);
        assertEquals(newPassword, parent.getPassword());
    }

    @Test
    public void testGetUserName() {
        assertEquals(userName, parent.getUserName());
    }

    @Test
    public void testGetPassword() {
        assertEquals(password, parent.getPassword());
    }

    // Simple test implementation of the Room class for testing purposes
    private class TestRoom extends Room {
        // Override methods as needed for testing
        public int getId() {
            return 1; // Example ID
        }
    }
}
