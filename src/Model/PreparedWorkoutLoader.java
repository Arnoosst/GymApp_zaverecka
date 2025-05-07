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
                    List<String> parts = Arrays.asList(line.substring(8).split(";"));
                    String name = parts.get(0);
                    int duration = Integer.parseInt(parts.get(1));
                    LocalDate date = LocalDate.parse(parts.get(2));
                    WorkoutLevel level = WorkoutLevel.valueOf(parts.get(3).toUpperCase());

                    currentWorkout = new Workout(name, duration, date, level);
                }

                else if (line.startsWith("Exercise:") && currentWorkout != null) {
                    List<String> parts = Arrays.asList(line.substring(9).split(";"));
                    String exName = parts.get(0);
                    int sets = Integer.parseInt(parts.get(1));
                    int reps = Integer.parseInt(parts.get(2));
                    double weight = Double.parseDouble(parts.get(3));

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
            e.printStackTrace();
        }

        return workouts;
    }
}