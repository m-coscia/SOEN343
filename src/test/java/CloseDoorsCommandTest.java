package src.test.java;

import static org.junit.Assert.assertFalse;

import org.junit.*;

import src.main.java.commands.CloseDoorsCommand;
import src.main.java.components.Doors;

public class CloseDoorsCommandTest {
    // test method while having no doors, no users in the room and no caller to the method
    @Test 
    public void testCloseDoorsCommand() {
        CloseDoorsCommand command = new CloseDoorsCommand(null, null, null);
        Doors doors = new Doors(false);
        doors.openDoorsCommand();
        doors.closeDoorsCommand();
        assertFalse(doors.open);
    }
}