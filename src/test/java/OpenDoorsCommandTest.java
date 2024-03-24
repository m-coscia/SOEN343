package src.test.java;

import static org.junit.Assert.assertFalse;

import org.junit.*;

import src.main.java.commands.OpenDoorsCommand;
import src.main.java.components.Doors;

public class OpenDoorsCommandTest {
    // test method while having no doors, no users in the room and no caller to the method
    @Test 
    public void testCloseDoorsCommand() {
        OpenDoorsCommand command = new OpenDoorsCommand(null, null, null);
        Doors doors = new Doors(false);
        doors.closeDoorsCommand();
        doors.openDoorsCommand();
        assertFalse(!doors.open);
    }
}