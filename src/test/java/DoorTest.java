import org.junit.*;
import components.Doors;

import static org.junit.Assert.*;

public class DoorTest {

    @Test
    public void testCloseDoorsCommand() {
        Doors doors = new Doors(false);
        doors.closeDoorsCommand();
        assertFalse(doors.open);
    }

    @Test
    public void testOpenDoorsCommand() {
        Doors doors = new Doors(false);
        doors.openDoorsCommand();
        assertTrue(doors.open);
    }

    @Test
    public void testIsGarageDoor() {
        Doors doors = new Doors(true);
        assertTrue(doors.getIsGarageDoor());
    }

    @Test
    public void testSetGarageDoor() {
        Doors doors = new Doors(false);
        doors.setGarageDoor(true);
        assertTrue(doors.getIsGarageDoor());
    }
}
