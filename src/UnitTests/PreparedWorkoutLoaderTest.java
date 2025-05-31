package UnitTests;
import Model.PreparedWorkoutLoader;
import Model.Workout;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit tests for the PreparedWorkoutLoader class.
 * These tests verify that workout data is correctly loaded from files into Workout objects.
 *
 * The tested method is:
 * - PreparedWorkoutLoader.loadPreparedWorkouts(String path)
 *
 * Temporary files are used to simulate workout files for various test scenarios.
 *
 * @author Vojtěch Malínek
 */
public class PreparedWorkoutLoaderTest {

    /**
     * Tests that a single valid workout with two exercises is correctly loaded.
     *
     */
    @Test
    public void testLoadSingleValidWorkout(){
        ArrayList<Workout> workouts = PreparedWorkoutLoader.loadPreparedWorkouts("src/data/prepared_workouts.txt");

        assertEquals(9, workouts.size());
        Workout workout = workouts.get(0);
        assertEquals("Full Body Beginner", workout.getName());
        assertEquals(3, workout.getExercises().size());
    }



    /**
     * Tests that multiple workouts in one file are correctly loaded and separated.
     *
     * @throws IOException if a temporary test file cannot be created
     */
    @Test
    public void testMultipleWorkoutsLoadCorrectly() throws IOException {
        ArrayList<Workout> workouts = PreparedWorkoutLoader.loadPreparedWorkouts("src/data/prepared_workouts.txt");

        assertEquals(9, workouts.size());
        assertEquals("Beginner Core", workouts.get(1).getName());
        assertEquals("Beginner Upper Body", workouts.get(2).getName());
        assertEquals(3, workouts.get(1).getExercises().size());
    }
}