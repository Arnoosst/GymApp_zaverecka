package UnitTests;

import Model.Meal;
import Model.PreparedMealLoader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;


/**
 * Unit test for the PreparedMealLoader class.
 * This test verifies that the method correctly parses a file with valid meal entries.
 *
 * The tested method is:
 * - PreparedMealLoader.loadMealsFromFile(String path)
 *
 * Temporary file is created and populated with valid meal data for the test.
 *
 * @author Vojtěch Malínek
 */
public class PreparedMealLoaderTest {


    /**
     * Tests loading meals from a valid file.
     * Asserts that the parsed meals match the expected values in the file.
     *
     */
    @Test
    public void testLoadMealsFromValidFile() {
        ArrayList<Meal> meals = PreparedMealLoader.loadMealsFromFile("src/data/prepared_meals.txt");

        assertEquals(23, meals.size());

        Meal first = meals.get(0);
        assertEquals("Chicken Breast", first.getName());
        assertEquals(200, first.getKcal());
        assertEquals(30, first.getProtein());
        assertEquals(3, first.getFat());
        assertEquals(0, first.getCarbs());
    }
}
