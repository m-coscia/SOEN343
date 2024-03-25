import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import logic.DataBase;
import logic.Login;
import logic.Profile;

import java.util.ArrayList;

public class LoginTest {
    private DataBase db;
    private Profile testProfile;

    @Before
    public void setUp() {
        // Initialize test database
        db = DataBase.getDataBase();

        // Create a test profile
        testProfile = new Profile("TestUser", null);
    }

    @Test
    public void testLoginWithExistingUser() {
        // Add test profile to database
        db.addAccount(testProfile);

        // Attempt login with the test profile
        Login login = new Login(testProfile);

        // Check if the current user is set to the test profile
        assertEquals(testProfile, login.getCurrentUser());
    }

    @Test
    public void testLoginWithNonExistingUser() {
        // Attempt login with a profile that does not exist in the database
        Profile nonExistingProfile = new Profile("NonExistingUser", null);
        Login login = new Login(nonExistingProfile);

        // Check if the current user is null (since the profile does not exist)
        assertNull(login.getCurrentUser());
    }

    @Test
    public void testSetCurrentUser() {
        // Add test profile to database
        db.addAccount(testProfile);

        // Create a new profile
        Profile newProfile = new Profile("NewUser", null);

        // Attempt to set the current user to the new profile
        Login login = new Login(testProfile);
        login.setCurrentUser(newProfile);

        // Check if the current user is set to the new profile
        assertEquals(newProfile, login.getCurrentUser());
    }
}
