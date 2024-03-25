import static org.junit.Assert.*;
import org.junit.*;
import components.Room;
import logic.Guest;

public class GuestTest {

    @Test
    public void testSetAndGetUserName() {
        Room room = new Room(null, 0, 0, 0, null);
        Guest guest = new Guest("John Doe", "guest123", "password", room);

        guest.setUserName("newGuest123");

        assertEquals("newGuest123", guest.getUserName());
    }

    @Test
    public void testSetAndGetPassword() {
        Room room = new Room(null, 0, 0, 0, null);
        Guest guest = new Guest("John Doe", "guest123", "password", room);

        guest.setPassword("newPassword");

        assertEquals("newPassword", guest.getPassword());
    }
}
