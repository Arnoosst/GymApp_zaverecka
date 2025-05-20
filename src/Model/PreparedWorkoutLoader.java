package Model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreparedWorkoutLoader {

    public static ArrayList<Workout> loadPreparedWorkouts(String path) {
        ArrayList<Workout> workouts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            Workout currentWorkout = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("Workout:")) {
                    String[] mainParts = line.split(":", 2);
                    String[] parts = mainParts[1].trim().split(";");

                    String name = parts[0].trim();
                    int duration = Integer.parseInt(parts[1].trim());
                    LocalDate date = LocalDate.parse(parts[2].trim());
                    WorkoutLevel level = WorkoutLevel.valueOf(parts[3].trim().toUpperCase());

                    currentWorkout = new Workout(name, duration, date, level);
                }



                else if (line.startsWith("Exercise:") && currentWorkout != null) {
                    String[] mainParts = line.split(":", 2);
                    String[] parts = mainParts[1].trim().split(";");

                    String exName = parts[0].trim();
                    int sets = Integer.parseInt(parts[1]);
                    int reps = Integer.parseInt(parts[2]);
                    double weight = Double.parseDouble(parts[3]);

                    Exercise exercise = new Exercise(exName);
                    exercise.initializeSets(sets);
                    for (ExerciseSets set : exercise.getSets()) {
                        set.setReps(reps);
                        set.setWeight(weight);
                    }
                    currentWorkout.addExercise(exercise);
                }

                else if (line.equals("---") && currentWorkout != null) {
                    workouts.add(currentWorkout);
                    currentWorkout = null;
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading prepared workouts");
        }

        return workouts;
    }
}