package UnitTests;

import Model.Meal;
import Model.PreparedMealLoader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;

public class PreparedMealLoaderTest {

    @Test
    public void testLoadMealsFromValidFile() throws IOException {
        File tempFile = File.createTempFile("test_meals", ".txt");
        tempFile.deleteOnExit();

        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        writer.write("Eggs and Toast;400;25;20;30\n");
        writer.write("Yogurt and Banana;300;15;5;40\n");
        writer.close();


        ArrayList<Meal> meals = PreparedMealLoader.loadMealsFromFile(tempFile.getAbsolutePath());

        assertEquals(2, meals.size());

        Meal first = meals.get(0);
        assertEquals("Eggs and Toast", first.getName());
        assertEquals(400, first.getKcal());
        assertEquals(25, first.getProtein());
        assertEquals(20, first.getFat());
        assertEquals(30, first.getCarbs());
    }
}
