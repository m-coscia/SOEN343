import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import components.Room;
import components.RoomType;
import logic.HouseLayout;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class HouseLayoutTest {
    private static final String TEST_LAYOUT_FILE = "src/test/resources/houseLayoutFile.txt";

    @Test
    public void testSetHouseLayout() throws FileNotFoundException {
        HouseLayout houseLayout = HouseLayout.getHouseLayout();
        houseLayout.setHouseLayout(TEST_LAYOUT_FILE);

        ArrayList<Room> rooms = houseLayout.getRooms();

        assertEquals(2, rooms.size());

        Room firstRoom = rooms.get(0);
        assertEquals(RoomType.BEDROOM, firstRoom.getType());
        assertEquals(3, firstRoom.getNumLights());
        assertEquals(1, firstRoom.getNumWindows());
        assertEquals(1, firstRoom.getNumDoors());

        Room secondRoom = rooms.get(1);
        assertEquals(RoomType.BATHROOM, secondRoom.getType());
        assertEquals(2, secondRoom.getNumLights());
        assertEquals(0, secondRoom.getNumWindows());
        assertEquals(1, secondRoom.getNumDoors());
    }
}

