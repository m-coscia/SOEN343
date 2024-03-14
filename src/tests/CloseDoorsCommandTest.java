package src.tests;

import static org.junit.Assert.assertFalse;

import org.junit.*;

import src.commands.CloseDoorsCommand;
import src.components.Doors;
import src.logic.Profile;

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