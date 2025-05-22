package UnitTests;
import Model.PreparedWorkoutLoader;
import Model.Workout;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit testy pro metodu loadPreparedWorkouts ze třídy PreparedWorkoutLoader.
 * Ověřují různé scénáře: správné načtení, prázdný soubor, chybějící data, neexistující soubor a víc workoutů.
 */
public class PreparedWorkoutLoaderTest {

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

    @Test
    public void testEmptyFileReturnsEmptyList() throws IOException {
        Path tempFile = Files.createTempFile("empty", ".txt");
        Files.writeString(tempFile, "");

        ArrayList<Workout> workouts = PreparedWorkoutLoader.loadPreparedWorkouts(tempFile.toString());

        assertTrue(workouts.isEmpty());
    }

    @Test
    public void testWorkoutMissingFields() throws IOException {
        String fileContent = """
            Workout: BadWorkout;60
            ---
            """;

        Path tempFile = Files.createTempFile("bad", ".txt");
        Files.writeString(tempFile, fileContent);

        ArrayList<Workout> workouts = PreparedWorkoutLoader.loadPreparedWorkouts(tempFile.toString());

        assertTrue(workouts.isEmpty());
    }

    @Test
    public void testFileNotFound() {
        ArrayList<Workout> workouts = PreparedWorkoutLoader.loadPreparedWorkouts("non_existing_file.txt");
        assertTrue(workouts.isEmpty());
    }

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