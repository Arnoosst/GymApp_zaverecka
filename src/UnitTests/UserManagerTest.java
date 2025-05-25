package UnitTests;

import Model.User;
import Model.UserManager;
import org.junit.jupiter.api.*;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the UserManager class.
 * This test verifies that user registration and persistent saving/loading works correctly.
 * It uses the actual file "users.txt", which is cleared before each test.
 *
 * @author Vojtěch Malínek
 */
public class UserManagerTest {

    /**
     * Deletes the user file before each test to ensure a clean test environment.
     */
    @BeforeEach
    public void clearFile() {
        File file = new File("users.txt");
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Tests that a user can be registered, saved, and then loaded again by creating
     * a new instance of UserManager. Ensures persistence works.
     */
    @Test
    public void testSaveAndLoadUser() {

        UserManager manager1 = new UserManager();
        User testUser = new User("testuser", "pass123");
        boolean registered = manager1.registerUser(testUser);
        assertTrue(registered);


        UserManager manager2 = new UserManager();
        User loaded = manager2.login("testuser", "pass123");

        assertNotNull(loaded);
        assertEquals("testuser", loaded.getUserName());
    }
}


