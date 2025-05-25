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
     * @throws IOException if a temporary test file cannot be created
     */
    @Test
    public void testLoadSingleValidWorkout() throws IOException {
        String fileContent = """
                Workout: Bench Day;60;2024-05-01;BEGINNER
                Exercise: Bench Press;4;8;80
                Exercise: Incline Press;3;10;60
                ---
                """;

        Path tempFile = Files.createTempFile("workout", ".txt");
        Files.writeString(tempFile, fileContent);

        ArrayList<Workout> workouts = PreparedWorkoutLoader.loadPreparedWorkouts(tempFile.toString());

        assertEquals(1, workouts.size());
        Workout workout = workouts.get(0);
        assertEquals("Bench Day", workout.getName());
        assertEquals(2, workout.getExercises().size());
    }


    /**
     * Tests that an empty workout file results in an empty list being returned.
     *
     * @throws IOException if a temporary test file cannot be created
     */
    @Test
    public void testEmptyFileReturnsEmptyList() throws IOException {
        Path tempFile = Files.createTempFile("empty", ".txt");
        Files.writeString(tempFile, "");

        ArrayList<Workout> workouts = PreparedWorkoutLoader.loadPreparedWorkouts(tempFile.toString());

        assertTrue(workouts.isEmpty());
    }


    /**
     * Tests that multiple workouts in one file are correctly loaded and separated.
     *
     * @throws IOException if a temporary test file cannot be created
     */
    @Test
    public void testMultipleWorkoutsLoadCorrectly() throws IOException {
        String fileContent = """
                Workout: Bench Day;60;2024-05-01;BEGINNER
                Exercise: Bench Press;4;8;80
                ---
                Workout: Leg Day;45;2024-05-03;INTERMEDIATE
                Exercise: Squat;5;10;100
                ---
                """;

        Path tempFile = Files.createTempFile("multi", ".txt");
        Files.writeString(tempFile, fileContent);

        ArrayList<Workout> workouts = PreparedWorkoutLoader.loadPreparedWorkouts(tempFile.toString());

        assertEquals(2, workouts.size());
        assertEquals("Bench Day", workouts.get(0).getName());
        assertEquals("Leg Day", workouts.get(1).getName());
        assertEquals(1, workouts.get(1).getExercises().size());
    }
}